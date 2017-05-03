package etc;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

class LocalDriverFactory {
    static WebDriver createInstance(String browserName) {
        WebDriver driver = null;
        if (browserName.toLowerCase().contains("firefox")) {
        	String path = System.getProperty("user.dir");
			System.setProperty("webdriver.gecko.driver", path + "/src/test/resources/drivers/geckodriver");
            return new FirefoxDriver();
        }
        if (browserName.toLowerCase().contains("internet")) {
            driver = new InternetExplorerDriver();
            return driver;
        }
        if (browserName.toLowerCase().contains("chrome")) {
        	String path = System.getProperty("user.dir");
    		System.setProperty("webdriver.chrome.driver", path + "/src/test/resources/drivers/chromedriver3");
            return new ChromeDriver();
        }
        return driver;
    }
}
