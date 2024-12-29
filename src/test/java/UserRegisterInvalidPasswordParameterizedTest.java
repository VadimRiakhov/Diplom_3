import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import pageobjects.RegistrationPage;

import static com.codeborne.selenide.Selenide.*;
import static constants.URL.REGISTRATION_PAGE_URL;
import static org.junit.Assert.assertTrue;

// регистрация пользователя с невалидным паролем
@RunWith(Parameterized.class)
public class UserRegisterInvalidPasswordParameterizedTest extends BaseUITest {
    // имя пользователя
    private final String name;
    // пароль пользователя
    private final String password;
    // email пользователя
    private final String email;
    RegistrationPage registrationPage;

    public UserRegisterInvalidPasswordParameterizedTest(String name, String password, String email) {
        this.name = name;
        this.password = password;
        this.email = email;
    }

    @Before
    public void openRegistrationPage(){
        // открываем страницу регистрации
        registrationPage = open(REGISTRATION_PAGE_URL, RegistrationPage.class);
    }

    @Parameterized.Parameters(name="Имя - {0}, Пароль - {1}, email - {2}")
    public static Object[][] getData(){
        return new Object[][]{
                {"Ivan", "", "ivan123456@yandex.ru"},
                {"Ivan", "1", "ivan123456@yandex.ru"},
                {"Ivan", "123", "ivan123456@yandex.ru"},
                {"Ivan", "1234", "ivan123456@yandex.ru"},
                {"Ivan", "12345", "ivan123456@yandex.ru"},
        };
    }

    @Test
    @DisplayName("Регистрация пользователя с паролем меньше 6 символов")
    @Description("Должно появиться сообщение \"Некорректный пароль\"")
    public void userRegistrationInvalidPasswordTest(){
        // заполняем поля на странице регистрации
        registrationPage.setAllFields(name, password, email);
        // проверяем появление сообщения о некорректном пароле
        assertTrue("Сообщение \"Некорректный пароль\" не появилось", registrationPage.isInvalidPasswordErrorVisible());
    }

    @After
    public void cleanUp(){
        closeWindow();
    }
}
