package pageobjects;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Condition.*;

// страница регистрации
public class RegistrationPage {
    // локатор для поля "Имя"
    private final SelenideElement nameInput = $x(".//div/label[text()='Имя']/following-sibling::input");
    // локатор для поля "Email"
    private final SelenideElement emailInput = $x(".//div/label[text()='Email']/following-sibling::input");
    // локатор для поля "Имя"
    private final SelenideElement passwordInput = $x(".//div/label[text()='Пароль']/following-sibling::input");
    // локатор для кнопки "Зарегистрироваться"
    private final SelenideElement registerButton = $x(".//button[text()='Зарегистрироваться']");
    // локатор для кнопки "Войти"
    private final SelenideElement enterButton =  $x(".//a[text()='Войти']");
    // локатор для надписи "Некорректный пароль"
    private final SelenideElement invalidPasswordError =  $x(".//p[text()='Некорректный пароль']");

    @Step("Нажатие на кнопку \"Войти\"")
    public void enterButtonClick(){
        enterButton.click();
    }

    // Нажатие на кнопку "Зарегистрироваться"
    public void registerButtonClick(){
        registerButton.shouldBe(visible).click();
    }

    // заполнить поле "Имя"
    public void setName(String name){
        nameInput.shouldBe(visible).clear();
        nameInput.setValue(name);
    }

    // заполнить поле "Пароль"
    public void setPassword(String password){
        passwordInput.shouldBe(visible).clear();
        passwordInput.setValue(password);
    }

    // заполнить поле "Email"
    public void setEmail(String email){
        emailInput.shouldBe(visible).clear();
        emailInput.setValue(email);
    }

    // заполнить все поля и нажать кнопку "Зарегистрироваться"
    @Step("Заполнение полей на форме регистрации и нажатие кнопки \"Зарегистрироваться\"")
    public void setAllFields(String name, String password, String email){
        setName(name);
        setPassword(password);
        setEmail(email);
        registerButtonClick();
    }

    @Step("Проверка появления ошибки \"Некорректный пароль\"")
    public boolean isInvalidPasswordErrorVisible(){
        return invalidPasswordError.isDisplayed();
    }
}
