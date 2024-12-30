package pageobjects;

import static com.codeborne.selenide.Condition.*;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;


import static com.codeborne.selenide.Selenide.$x;

// страница личного кабинета
public class PersonalAccountPage {

    // локатор для поля "Email"
    private final SelenideElement nameInput = $x(".//div/label[text()='Имя']/following-sibling::input");
    // локатор для поля "Имя"
    private final SelenideElement emailInput = $x(".//div/label[text()='Логин']/following-sibling::input");
    // локатор для кнопки "Выход"
    private final SelenideElement exitButton =  $x(".//button[text()='Выход']");
    // локатор для кнопки "Конструктор"
    private final SelenideElement constructorLink =  $x(".//li/a[@href='/']");
    // локатор для логотипа Stellar Burgers в хедере
    private final SelenideElement stellarBurgersLogo =  $x(".//div/a[@href='/']");

    // получение имени из поля "Имя"
    public String getNameInputValue(){
        return nameInput.shouldBe(visible).getValue();
    }
    // получение email из поля "Email"
    public String getEmailInputValue(){
        return emailInput.shouldBe(visible).getValue();
    }

    // проверяем имя и email авторизованного пользователя
    @Step("Проверка имени и email авторизованного пользователя на странице личного кабинета")
    public boolean isUserAuthorized(String name, String email){
        return getNameInputValue().equals(name) && getEmailInputValue().equals(email.toLowerCase());
    }

    @Step("Нажатие кнопки \"Выход\" в личном кабинете")
    public void exitButtonClick(){
        exitButton.click();
        exitButton.should(disappear);
    }

    @Step("Нажатие кнопки \"Конструктор\" в личном кабинете")
    public void constructorLinkClick(){
        constructorLink.click();
        exitButton.should(disappear);
    }

    @Step("Нажатие логотипа \"Stellar Burgers\" в личном кабинете")
    public void stellarBurgersLogoClick(){
        stellarBurgersLogo.click();
        exitButton.should(disappear);
    }
}
