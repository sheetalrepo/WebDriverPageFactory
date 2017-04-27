package etc;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class TestLog {

	
	public static void main(String [] args){
		Logger log = Logger.getLogger("TestLog");
		PropertyConfigurator.configure("log4j.properties");
		
		log.debug("i am debugger");
		log.info("i am info");
		
	}
	
}
