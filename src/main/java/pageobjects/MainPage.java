package pageobjects;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import java.util.Objects;

import static com.codeborne.selenide.Selenide.*;

// главная страница
public class MainPage {

    // локатор для кнопки "Войти в аккаунт"
    private final SelenideElement enterToAccountButton = $x(".//button[text()='Войти в аккаунт']");
    // локатор для кнопки "Личный кабинет"
    private final SelenideElement personalAccountLink = $x(".//a[@href='/account']");
    // локатор для вкладки "Булки"
    private final SelenideElement bunTab = $x(".//span[text()='Булки']/parent::*");
    // локатор для вкладки "Соусы"
    private final SelenideElement sauceTab = $x(".//span[text()='Соусы']/parent::*");
    // локатор для вкладки "Начинки"
    private final SelenideElement fillingTab = $x(".//span[text()='Начинки']/parent::*");

    @Step("нажатие на вкладку \"Булки\" конструктора")
    public void bunTabClick(){
        bunTab.click();
    }

    @Step("нажатие на вкладку \"Соусы\" конструктора")
    public void sauceTabClick(){
        sauceTab.click();
    }

    @Step("нажатие на вкладку \"Начинки\" конструктора")
    public void fillingTabClick(){
        fillingTab.click();
    }

    // нажатие на кнопку "Личный кабинет"
    public void personalAccountLinkClick(){
        personalAccountLink.click();
    }

    // нажатие на кнопку "Войти в аккаунт"
    public void enterToAccountButtonClick(){
        enterToAccountButton.click();
    }

    @Step("Проверка, что вкладка \"Булки\" выбрана")
    public boolean isBunTabSelected(){
       return Objects.requireNonNull(bunTab.getAttribute("class")).contains("current");
    }

    @Step("Проверка, что вкладка \"Соусы\" выбрана")
    public boolean isSauceTabSelected(){
       return Objects.requireNonNull(sauceTab.getAttribute("class")).contains("current");
    }

    @Step("Проверка, что вкладка \"Начинки\" выбрана")
    public boolean isFillingTabSelected(){
       return Objects.requireNonNull(fillingTab.getAttribute("class")).contains("current");
    }

}
