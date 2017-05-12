package etc;

import org.openqa.selenium.WebDriver;

public class Abc {

	WebDriver driver;
	
	public void one(){
		
		driver = LocalDriverFactory.createInstance("firefox");
		
		
		
		
	}
	
	
	public static void main(String [] args){
		Abc obj = new Abc();
		obj.one();
	}
	
	
}
