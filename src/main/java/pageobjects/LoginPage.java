package pageobjects;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$x;

// страница авторизации
public class LoginPage {

    // локатор для поля "Email"
    private final SelenideElement emailInput = $x(".//div/label[text()='Email']/following-sibling::input");
    // локатор для поля "Пароль"
    private final SelenideElement passwordInput = $x(".//div/label[text()='Пароль']/following-sibling::input");
    // локатор для кнопки "Войти"
    private final SelenideElement enterButton =  $x(".//button[text()='Войти']");
    // локатор для кнопки "Личный кабинет"
    private final SelenideElement personalAccountButton = $x(".//a[@href='/account']");

    // нажатие на кнопку "Войти"
    public void enterButtonClick(){
        enterButton.shouldBe(visible).click();
    }

    // ввести значение в поле "Пароль"
    public void setPassword(String password){
        passwordInput.shouldBe(visible);
        passwordInput.setValue(password);
    }

    // ввести значение в поле "email"
    public void setEmail(String email){
        emailInput.shouldBe(visible);
        emailInput.setValue(email);
    }

    @Step("Заполнение полей на форме авторизации и нажатие кнопки \"Войти\"")
    public void setAllFields(String email, String password)  {
        setEmail(email);
        setPassword(password);
        enterButtonClick();
    }

    // нажатие на кнопку "Личный кабинет"
    public void personalAccountButtonClick(){
        personalAccountButton.click();
    }
}
