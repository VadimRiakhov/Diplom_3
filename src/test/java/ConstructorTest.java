import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Before;
import org.junit.Test;
import pageobjects.MainPage;

import static com.codeborne.selenide.Selenide.*;
import static constants.URL.*;
import static org.junit.Assert.assertTrue;

// проверка переходов между вкладками конструктора
public class ConstructorTest extends BaseUITest {

    MainPage mainPage;

    // открываем главную страницу
    @Before
    public void openMainPage() {
        mainPage = open(MAIN_PAGE_URL, MainPage.class);
    }

    // проверка перехода к разделу "Булки" конструктора
    @Test
    @DisplayName("Клик по вкладке \"Булки\" конструктора")
    @Description("Вкладка \"Булки\" конструктора становится активной")
    public void bunClickScrollToBunSectionTest() {
        // нажимаем на секцию "Начинки"
        mainPage.fillingTabClick();
        // нажимаем на секцию "Булки>"
        mainPage.bunTabClick();
        // проверяем, что вкладка "Булки" выбрана
        assertTrue("Вкладка \"Булки\" не выбрана", mainPage.isBunTabSelected());
    }

    // проверка перехода к разделу "Соусы" конструктора
    @Test
    @DisplayName("Клик по вкладке \"Соусы\" конструктора")
    @Description("Вкладка \"Соусы\" конструктора становится активной")
    public void sauceClickScrollToSauceSectionTest() {
        // нажимаем на вкладку "Соусы"
        mainPage.sauceTabClick();
        // проверяем, что вкладка "Соусы" выбрана
        assertTrue("Вкладка \"Соусы\" не выбрана", mainPage.isSauceTabSelected());
    }

    // проверка перехода к разделу "Начинки" конструктора
    @Test
    @DisplayName("Клик по вкладке \"Начинки\" конструктора")
    @Description("Вкладка \"Начинки\" конструктора становится активной")
    public void fillingClickScrollToFillingSectionTest() {
        // нажимаем на вкладку "Начинки"
        mainPage.fillingTabClick();
        // проверяем, что вкладка "Начинки" выбрана
        assertTrue("Вкладка \"Начинки\" не выбрана", mainPage.isFillingTabSelected());
    }
}