package etc;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TestClass2 {
	WebDriver drv;
	WebDriver driver;

	@BeforeClass
	public void start2() {
		drv = LocalDriverFactory.createInstance("firefox");
		LocalDriverManager.setWebDriver(drv);
		driver = LocalDriverManager.getDriver();
	}

	@AfterClass
	public void end() {
		driver.quit();
	}

	@Test
	public void jan() {
		System.out.println("class2 > jan:  " + Thread.currentThread().getName() + "        " + driver.toString());
		driver.get("http://www.yahoo.com");

	}

}
