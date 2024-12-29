import api.UserApi;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import model.UserCreateData;
import model.UserGeneratorData;
import org.assertj.core.api.SoftAssertions;
import org.junit.*;
import pageobjects.*;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverConditions.url;
import static constants.URL.*;

// проверка авторизации польлзователя
public class AuthorisationTest extends BaseUITest{
    MainPage mainPage;
    LoginPage loginPage;
    PersonalAccountPage personalAccountPage;
    UserApi userApi;
    // имя пользователя
    private String name;
    // пароль пользователя
    private String password;
    // email пользователя
    private String email;
    // токен пользователя
    String accessToken = "";

    @Before
    public void createUser() {
        // создаем пользователя
        UserCreateData userCreateData = UserGeneratorData.getRandomUser();
        name = userCreateData.getName();
        email = userCreateData.getEmail();
        password = userCreateData.getPassword();
        userApi = new UserApi();
        // получаем токен для последующего удаления пользователя
        accessToken = userApi.createUser(userCreateData);
        loginPage = page(LoginPage.class);
        personalAccountPage = page(PersonalAccountPage.class);
    }

    // проверка авторизации по клику на кнопку "Войти в аккаунт" главной страницы
    @Test
    @DisplayName("Авторизация по клику кнопки \"Войти в аккаунт\"")
    @Description("Успешная авторизация по клику на кнопку \"Войти в аккаунт\" на главной странице")
    public void loginByEnterToAccountButtonClickMainPageTest(){
        // открываем главную страницу
        mainPage = open(MAIN_PAGE_URL, MainPage.class);
        // нажимаем на кнопку "Войти в аккаунт"
        mainPage.enterToAccountButtonClick();
        checkAuthorisation();
    }

    // проверка авторизации по клику на кнопку "Личный кабинет"
    @Test
    @DisplayName("Авторизация по клику кнопки \"Личный кабинет\"")
    @Description("Успешная авторизация по клику на кнопку \"Личный кабинет\" на главной странице")
    public void loginByPersonalAccountLinkClickMainPageTest(){
        // открываем главную страницу
        mainPage = open(MAIN_PAGE_URL, MainPage.class);
        // нажимаем на кнопку "Личный кабинет"
        mainPage.personalAccountLinkClick();
        checkAuthorisation();
    }

    // проверка авторизации по клику на кнопку "Войти" на форме регистрации
    @Test
    @DisplayName("Авторизация по клику кнопки \"Войти\" на форме регистрации")
    @Description("Успешная авторизация по клику на кнопку \"Войти\" на форме регистрации")
    public void loginByEnterButtonClickRegistrationPageTest(){
        // открываем страницу регистрации
        RegistrationPage registrationPage = open(REGISTRATION_PAGE_URL, RegistrationPage.class);
        // нажимаем на кнопку "Войти"
        registrationPage.enterButtonClick();
        checkAuthorisation();

    }

    // проверка авторизации по клику на кнопку "Войти" на форме восстановления пароля
    @Test
    @DisplayName("Авторизация по клику кнопки \"Войти\" на форме восстановления пароля")
    @Description("Успешная авторизация по клику на кнопку \"Войти\" на форме восстановления пароля")
    public void loginByEnterButtonClickPasswordRecoveryPageTest(){
        // открываем страницу восстановления пароля
        PasswordRecoveryPage passwordRecoveryPage = open(PASSWORD_RECOVERY_PAGE_URL, PasswordRecoveryPage.class);
        // нажимаем на кнопку "Войти"
        passwordRecoveryPage.enterButtonClick();
        checkAuthorisation();
    }

    // удаляем пользователя
    @After
    public void cleanUp(){
        if(!accessToken.isEmpty()){
            userApi.deleteUser(accessToken);
            accessToken = "";
        }
    }

    public void checkAuthorisation(){
        // ожидаем загрузку страницы авторизации
        webdriver().shouldHave(url(LOGIN_PAGE_URL));
        // заполняем поля и авторизуемся, попадаем на главную страницу
        loginPage.setAllFields(email, password);
        // ожидаем загрузку главной сраницы
        webdriver().shouldHave(url(MAIN_PAGE_URL));
        // нажимаем на кнопку "Личный кабинет"
        mainPage = page(MainPage.class);
        mainPage.personalAccountLinkClick();
        // ожидаем загрузку страницы личного кабинета
        webdriver().shouldHave(url(PERSONAL_ACCOUNT_PAGE_URL));
        SoftAssertions softAssertions = new SoftAssertions();
        // проверяем, что открылась страница личного кабинета
        softAssertions.assertThat(WebDriverRunner.url()).isEqualTo(PERSONAL_ACCOUNT_PAGE_URL);
        // проверяем, что поля "Имя" и "Логин" заполнены данными созданного пользователя
        softAssertions.assertThat(personalAccountPage.isUserAuthorized(name, email)).isTrue();
        softAssertions.assertAll();
    }
}
