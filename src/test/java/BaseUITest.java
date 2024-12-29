import com.codeborne.selenide.Configuration;
import org.junit.AfterClass;
import org.junit.BeforeClass;

import java.io.IOException;

import static browser.Browser.initDriver;
import static com.codeborne.selenide.Selenide.closeWebDriver;


public class BaseUITest {

    @BeforeClass
    public static void startUp() throws IOException {
        initDriver();
        Configuration.timeout = 4000;
        Configuration.browserSize = "1920x1080";
    }

    @AfterClass
    public static void tearDown(){
        closeWebDriver();
    }

}