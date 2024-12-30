import com.codeborne.selenide.Configuration;
import org.junit.After;
import org.junit.Before;

import java.io.IOException;

import static browser.Browser.initDriver;
import static com.codeborne.selenide.Selenide.closeWebDriver;


public class BaseUITest {

    @Before
    public void startUp() throws IOException {
        initDriver();
        Configuration.timeout = 4000;
        Configuration.browserSize = "1920x1080";
    }

    @After
    public void tearDown(){
        closeWebDriver();
    }

}