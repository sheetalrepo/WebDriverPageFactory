package etc;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TestClass1 {

	WebDriver drv;
	WebDriver driver;

	@BeforeClass
	public void start() {
		drv = LocalDriverFactory.createInstance("firefox");
		LocalDriverManager.setWebDriver(drv);
		driver = LocalDriverManager.getDriver();
		
	}

	@AfterClass
	public void end() {
		driver.quit();
	}

	@Test
	public void one() {
		System.out.println("class1 > one:  " + Thread.currentThread().getName() + "        " + driver.toString());
		driver.get("http://www.google.com");

	}

}
