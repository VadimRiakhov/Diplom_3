package browser;

import com.codeborne.selenide.Configuration;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Browser  {
    // инициализация драйвера через properties
    public static void initDriver() throws IOException {
        Properties properties = new Properties();
        properties.load(new FileInputStream( "src/test/resources/browser.properties"));
        String browserProperty = properties.getProperty("testBrowser");
        BrowserType browserType = BrowserType.valueOf(browserProperty);
        switch(browserType){
            case CHROME:
                Configuration.browser = "CHROME";
                break;
            case YANDEX:
                System.setProperty("webdriver.chrome.driver", "C:/Users/Vadim/Downloads/yandexdriver/yandexdriver.exe");
                Configuration.browser = "CHROME";
                break;
            default:
                throw new RuntimeException("Browser undefined");
        }
    }
}
