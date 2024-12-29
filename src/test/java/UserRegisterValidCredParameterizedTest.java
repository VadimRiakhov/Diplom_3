import api.UserApi;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import model.UserLoginData;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import pageobjects.LoginPage;
import pageobjects.MainPage;
import pageobjects.PersonalAccountPage;
import pageobjects.RegistrationPage;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverConditions.url;
import static constants.URL.*;
import static org.junit.Assert.assertTrue;

// регистрация с валидными данными
@RunWith(Parameterized.class)
public class UserRegisterValidCredParameterizedTest extends BaseUITest {
    // имя пользователя
    private final String name;
    // пароль пользователя
    private final String password;
    // email пользователя
    private final String email;
    RegistrationPage registrationPage;
    LoginPage loginPage;
    MainPage mainPage;
    PersonalAccountPage personalAccountPage;
    UserApi userApi;
    String accessToken="";

    public UserRegisterValidCredParameterizedTest(String name, String password, String email) {
        this.name = name;
        this.password = password;
        this.email = email;
    }

    @Before
    public void openRegistrationPage(){
        // открываем страницу регистрации
        registrationPage = open(REGISTRATION_PAGE_URL, RegistrationPage.class);
        loginPage = page(LoginPage.class);
        mainPage = page(MainPage.class);
        personalAccountPage = page(PersonalAccountPage.class);
        userApi = new UserApi();
    }

    @Parameterized.Parameters(name="Имя - {0}, Пароль - {1}, email - {2}")
    public static Object[][] getData(){
        return new Object[][]{
                // стандартные учетные данные
                {"Ivan", "123456Ivan", "ivan123456@yandex.ru"},
                // длинное имя
                {"IvanIvanIvanIvanIvanIvanIvanIvanIvan", "123456Ivan", "ivan123456@yandex.ru"},
                // спецсимволы в имени
                {"@#$%^&*", "123456Ivan", "ivan123456@yandex.ru"},
                // длинный email
                {"Ivan", "123456Ivan", "ivan123456ivan123456ivan123456ivan123456ivan123456ivan123456ivan123456@yandex.ru"},
                // спецсимволы в email
                {"Ivan", "123456Ivan", "#$%^&*@yandex.ru"},
                // длинный пароль
                {"Ivan", "1111111111111111111111111111111111111", "ivan123456@yandex.ru"},
                // спецсимволы в пароле
                {"Ivan", "@#$%^&*", "ivan123456@yandex.ru"}
        };
    }

    @Test
    @DisplayName("Регистрация пользователя с валидными данными")
    @Description("Успешная регистрации нового пользователя с валидными данными")
    public void userRegistrationValidCredentialsTest(){
        // заполняем поля на странице регистрации, попадаем на страницу авторизации
        registrationPage.setAllFields(name, password, email);
        // ожидаем загрузку страницы авторизации
        webdriver().shouldHave(url(LOGIN_PAGE_URL));
        // заполняем поля на странице авторизации, попадаем на главную страницу
        loginPage.setAllFields(email, password);
        // ожидаем загрузку главной сраницы
        webdriver().shouldHave(url(MAIN_PAGE_URL));
        // нажимаем на кнопку "Личный кабинет"
        mainPage.personalAccountLinkClick();
        // ожидаем загрузку страницы личного кабинета
        webdriver().shouldHave(url(PERSONAL_ACCOUNT_PAGE_URL));
        // проверяем, что поля "Имя" и "Логин" заполнены данными созданного пользователя
        assertTrue("Пользователь не авторизовался", personalAccountPage.isUserAuthorized(name, email));
        // получаем токен для последующего удаления пользователя
        accessToken = userApi.loginUser(new UserLoginData(email, password));
    }

    // удаляем пользователя
    @After
    public void cleanUp(){
        if(!accessToken.isEmpty()){
            userApi.deleteUser(accessToken);
            accessToken = "";
        }
        closeWindow();
    }
}
