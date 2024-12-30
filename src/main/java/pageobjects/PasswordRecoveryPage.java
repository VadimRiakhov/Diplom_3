package pageobjects;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;

// страница восстановления пароля
public class PasswordRecoveryPage {
    // локатор для кнопки "Войти"
    private final SelenideElement enterButton = $x(".//a[text()='Войти']");

    @Step("Нажатие на кнопку \"Войти\"")
    public void enterButtonClick(){
        enterButton.click();
    }
}
