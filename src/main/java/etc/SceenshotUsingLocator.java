package etc;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import org.openqa.selenium.By.ById;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import org.openqa.selenium.By;

/**
 * todo:
 * not working with firefox
 * correct screenshot not coming as per given locator 
 */
public class SceenshotUsingLocator {

	WebDriver driver;
	LocalDriverFactory ldf;

	public SceenshotUsingLocator() {
		driver = LocalDriverFactory.createInstance("chrome");
	}

	public void test() {
		File screen = null;
		BufferedImage img = null;
		BufferedImage dest = null;
		String path = "./src/test/resources/screenshots/";

		String inputImage = "input_image" + ".png";
		String expectedImage = "expected_image" + ".png";

		File imagePath1 = new File(path + inputImage);
		File imagePath2 = new File(path + expectedImage);

		//driver.manage().window().maximize();
		driver.get("https://github.com/");
		
		String viewPort = driver.manage().window().getSize().toString();
		viewPort = viewPort.replace("(", "").replace(")", ""); 
		
		System.out.println("********* Window View Port: "+viewPort); // 1050, 813
		
		try {
			int w = Integer.parseInt(viewPort.split(",")[0].trim());  
			int h = Integer.parseInt(viewPort.split(",")[1].trim()); 
			screen = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			
			WebElement testobject = driver.findElement(By.tagName("h1"));
			
			Point P = testobject.getLocation(); // not giving correct location 
			System.out.println("********* WebElement X,Y: "+P.getX()+" "+ P.getY());
			
			int width = testobject.getSize().getWidth();
			int height = testobject.getSize().getHeight();
			System.out.println("********* WebElement Width and Height: "+width+" , "+height);
			
			img = ImageIO.read(screen);
			ImageIO.write(img, "png", imagePath1);

			Thread.sleep(2000);
			
			dest = img.getSubimage(P.getX(), P.getY(), width, height);
			//dest = img.getSubimage(300, 300, 200, 200);
			
			
			ImageIO.write(dest, "png", imagePath2);

		} catch (Exception e) {
			e.printStackTrace();
		}

		driver.quit();
	}

	public static void main(String[] args) {

		SceenshotUsingLocator obj = new SceenshotUsingLocator();
		obj.test();

	}

}
