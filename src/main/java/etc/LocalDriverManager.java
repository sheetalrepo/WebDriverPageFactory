package etc;

import org.openqa.selenium.WebDriver;

public class LocalDriverManager {
    private static ThreadLocal<WebDriver> tl = new ThreadLocal<WebDriver>();
 
    public static WebDriver getDriver() {
        return tl.get();
    }
 
    static void setWebDriver(WebDriver driver) {
        tl.set(driver);
    }
}
