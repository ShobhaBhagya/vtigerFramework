package Practice;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class vtigerScreenshot {


	public static void main(String[] args) throws Throwable 
	{
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://localhost:8888/index.php?action=Login&module=Users");
		driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
		
		//steps:1 typecasting
		TakesScreenshot ts=(TakesScreenshot) driver;
		
		//steps:2 access the method and store in RAM
		File ram = ts.getScreenshotAs(OutputType.FILE);
		
		//Steps:3 specify the location
		File dest=new File("./photo/vtiger.png");
		
		//Steps:4 copy paste from Ram to dest location
		FileUtils.copyFile(ram, dest);
		
		driver.close();

	}

}
