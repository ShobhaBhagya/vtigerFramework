package Practice;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class loginforflipkart {

	public static void main(String[] args) throws IOException {
		FileInputStream fis=new FileInputStream("./data/Commondata.properties");
		Properties pboj= new Properties();
		pboj.load(fis);
		
		String Browser=pboj.getProperty("browser");
		String Url=pboj.getProperty("url1");
		
		
		WebDriver driver=new ChromeDriver();
		driver.get(Url);
		driver.findElement(By.linkText("Login")).click();
	}

}
