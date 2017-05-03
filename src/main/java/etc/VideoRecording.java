package etc;

import java.awt.AWTException;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;
import org.monte.media.Format;
import org.monte.media.FormatKeys.MediaType;
import org.monte.media.math.Rational;
import org.monte.screenrecorder.ScreenRecorder;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;
import static org.monte.media.AudioFormatKeys.*;
import static org.monte.media.VideoFormatKeys.*;

public class VideoRecording {
	WebDriver driver;
	ScreenRecorder screenRecorder;

  @Test
  public void f() throws IOException, AWTException {
	  
	  driver= LocalDriverFactory.createInstance("firefox");
	  startrecording();
	  driver.manage().window().maximize();
	  driver.get("https://www.google.com");
	  driver.navigate().to("https://www.yahoo.com");
	  driver.quit();
	  stoprecording();
  }
  
  
  public void startrecording() throws IOException, AWTException{
	  GraphicsConfiguration gc = GraphicsEnvironment
	            .getLocalGraphicsEnvironment()
	            .getDefaultScreenDevice()
	            .getDefaultConfiguration();
	  
	   screenRecorder = new ScreenRecorder(gc, 
			  gc.getBounds(),
			  new Format(MediaTypeKey, MediaType.FILE, MimeTypeKey, MIME_AVI),
			  new Format(MediaTypeKey, MediaType.VIDEO, EncodingKey, ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE,
	                    CompressorNameKey, ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE,
	                    DepthKey, 24, FrameRateKey, Rational.valueOf(15),
	                    QualityKey, 1.0f,KeyFrameIntervalKey, 15 * 60),
			  new Format(MediaTypeKey, MediaType.VIDEO, EncodingKey, "black", FrameRateKey, Rational.valueOf(30)),
			  null, 
			  new File(System.getProperty("user.dir")+ "/src/test/resources/videos/"));
	  		screenRecorder.start();
			  
  }
  
  public void stoprecording() throws IOException{
	  screenRecorder.stop();
  }
}

