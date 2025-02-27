import api.UserApi;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import model.UserCreateData;
import model.UserGeneratorData;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pageobjects.LoginPage;
import pageobjects.MainPage;
import pageobjects.PersonalAccountPage;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverConditions.url;
import static constants.URL.*;
import static constants.URL.MAIN_PAGE_URL;

// проверка переходов между страницами
public class GoToPageTest extends BaseUITest{
    LoginPage loginPage;
    PersonalAccountPage personalAccountPage;
    MainPage mainPage;
    UserApi userApi;
    String accessToken="";

    // создаем польлзователя и авторизуемся
    @Before
    public void createAndLoginUser(){
        // создаем пользователя
        UserCreateData userCreateData = UserGeneratorData.getRandomUser();
        userApi = new UserApi();
        // получаем токен для последующего удаления пользователя
        accessToken = userApi.createUser(userCreateData);
        // открываем станицу авторизации
        loginPage = open(LOGIN_PAGE_URL, LoginPage.class);
        // заполняем поля и авторизуемся, попадаем на главную страницу
        loginPage.setAllFields(userCreateData.getEmail(), userCreateData.getPassword());
        mainPage = page(MainPage.class);
        personalAccountPage = page(PersonalAccountPage.class);
    }

    // проверка перехода авторизованного пользователя в личный кабинет по клику на кнопку "Личный кабинет" главной страницы
    @Test
    @DisplayName("Клик по кнопке \"Личный кабинет\" главной страницы авторизованным пользователем")
    @Description("Должна открыться страница \"Личный кабинет\"")
    public void personalAccountButtonClickPersonalAccountPageOpenTest(){
        // нажимаем на кнопку "Личный кабинет"
        mainPage.personalAccountLinkClick();
        // должна открыться страница личного кабинета
        webdriver().shouldHave(url(PERSONAL_ACCOUNT_PAGE_URL));
    }

    // проверка перехода из личного кабинета на главную страницу при нажатии на "Конструктор"
    @Test
    @DisplayName("Клик по кнопке \"Конструктор\" личного кабинета")
    @Description("Должен произойти переход на главную страницу")
    public void constructorLinkClickMainPageOpenTest(){
        // нажимаем на кнопку "Личный кабинет"
        mainPage.personalAccountLinkClick();
        // нажимаем на  "Конструктор"
        personalAccountPage.constructorLinkClick();
        // должна открыться главная страница
        webdriver().shouldHave(url(MAIN_PAGE_URL));
    }

    // проверка перехода из личного кабинета на главную страницу при нажатии на логотип Stellar Burgers
    @Test
    @DisplayName("Клик по логотипу \"Stellar Burgers\" личного кабинета")
    @Description("Должен произойти переход на главную страницу")
    public void stellarBurgersLogoClickMainPageOpenTest(){
        // нажимаем на кнопку "Личный кабинет"
        mainPage.personalAccountLinkClick();
        // нажимаем на логотип "Stellar Burgers"
        personalAccountPage.stellarBurgersLogoClick();
        // должна открыться главная страница
        webdriver().shouldHave(url(MAIN_PAGE_URL));
    }

    // проверка выхода из аккаунта
    @Test
    @DisplayName("Клик по кнопке \"Выход\" личного кабинета")
    @Description("Должен произойти выход пользователя из системы")
    public void exitButtonClickUserLogoutTest(){
        // нажимаем на кнопку "Личный кабинет"
        mainPage.personalAccountLinkClick();
        // нажимаем на кнопку "Выход", осуществляется переход на страницу авторизации
        personalAccountPage.exitButtonClick();
        // нажимаем на кнопку "Личный кабинет", чтобы убедиться, что мы вышли из аккаунта и вернуться в него нельзя
        loginPage.personalAccountButtonClick();
        // должны остаться на странице авторизации
        webdriver().shouldHave(url(LOGIN_PAGE_URL));
    }

    // удаляем пользователя
    @After
    public void cleanUp(){
        if(!accessToken.isEmpty()){
            userApi.deleteUser(accessToken);
            accessToken = "";
        }
    }
}
