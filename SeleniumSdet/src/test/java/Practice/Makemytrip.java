package Practice;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import comcast.vtiger.genericUtility.File_Utility;
import comcast.vtiger.genericUtility.WebDriver_Utility;

public class Makemytrip {

	public static void main(String[] args) throws Throwable {
		
		File_Utility plib= new File_Utility();
		WebDriver_Utility wlib= new WebDriver_Utility();
		
		 WebDriver driver=new ChromeDriver();
		 driver.manage().window().maximize();
	       wlib.waitForPageToLoad(driver);
	     
	      //fetching data from property file and logging into vtiger
			String Browser=plib.getPropertyKeyValue("browser");
			String Url=plib.getPropertyKeyValue("url2");
			
	        
			//fetched data entering into vtiger login page
			driver.get(Url);
			
			//driver.findElement(By.xpath("//input[@type='text']")).click();
			
			WebElement from = driver.findElement(By.id("fromCity"));
     		from.sendKeys("Jaipur, India");
			Thread.sleep(1000);
			driver.findElement(By.xpath("//p[text()='Jaipur Airport']")).click();
			
			WebElement tocity = driver.findElement(By.id("toCity"));
			tocity.sendKeys("Bengaluru, India");
			Thread.sleep(1000);
			driver.findElement(By.xpath("//p[text()='Bengaluru International Airport']")).click();
			Thread.sleep(1000);
			
			driver.findElement(By.xpath("//label[@for='departure']")).click();
			 String requiredMonth= "October";
		        String requiredYear= "2022";
		        String requiredDate= "22";
		        
		    String currentMonthYear = driver.findElement(By.xpath("(//div[@class='DayPicker-Caption']/div)[1]")).getText();  
		    String currentMonth=currentMonthYear.split("")[0];
		    String currentYear= currentMonthYear.split("")[1];
		    
		    // !(Aug=Nov)&& (2022=2022)  !(Nov=Nov) && (2022=2022)
		    while(!(currentMonth.equalsIgnoreCase(requiredMonth)&& currentYear.equals(requiredYear)))
		    {
	    	driver.findElement(By.xpath("(//span[@role='button'])[2]")).click();
		    	//driver.findElement(By.xpath("//span[@class='DayPicker-NavButton DayPicker-NavButton--next']")).click();
		    	currentMonthYear = driver.findElement(By.xpath("(//div[@class='DayPicker-Caption']/div)[1]")).getText();
		    	currentMonth=currentMonthYear.split("")[0];
		    	currentYear= currentMonthYear.split("")[1];
		    }
		        
		    driver.findElement(By.xpath("(//p[text()='"+requiredDate+"'])[1]")).click();  
			
	}

}
