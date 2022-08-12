package ddm;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import comcast.vtiger.genericUtility.Excel_Utility;
import comcast.vtiger.genericUtility.File_Utility;
import comcast.vtiger.genericUtility.Java_Utility;
import comcast.vtiger.genericUtility.WebDriver_Utility;

public class Createorganization {

	public static void main(String[] args) throws Throwable {
		File_Utility plib= new File_Utility();
        Java_Utility jlib= new Java_Utility(); 
       Excel_Utility elib=new Excel_Utility();
        WebDriver_Utility wlib= new WebDriver_Utility();
        
        WebDriver driver=new ChromeDriver();
        wlib.waitForPageToLoad(driver);
     
      //fetching data from property file and logging into vtiger
		String Browser=plib.getPropertyKeyValue("browser");
		String Url=plib.getPropertyKeyValue("url");
		String Username=plib.getPropertyKeyValue("un");
		String Password=plib.getPropertyKeyValue("pw");
        
		//fetched data entering into vtiger login page
		driver.get(Url);
		driver.findElement(By.name("user_name")).sendKeys(Username);
		driver.findElement(By.name("user_password")).sendKeys(Password);
		driver.findElement(By.id("submitButton")).click();
		
		//selecting and creating products
		driver.findElement(By.linkText("Organizations")).click();
		driver.findElement(By.xpath("//img[@alt='Create Organization...' ]")).click();
		
		//random number for product name
		int ranNum = jlib.getRandomNum();
		String productName = elib.getDataFromExcel("products", 1, 0)+ranNum;
		System.out.println(productName);
		
		//entering product name into product field
        driver.findElement(By.name("accountname")).sendKeys(productName);
        
        //save the create product page
        driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
        
        //get the header text of create product
        String actData = driver.findElement(By.cssSelector("span.dvHeaderText")).getText();
        if(actData.contains(productName))
        {
        	System.out.println("pass");
        }
        else
        {
        	System.out.println("fail");
        }

      //sign out  
      WebElement ele = driver.findElement(By.xpath("//img[@style='padding: 0px;padding-left:5px']"));
     wlib.mouseHoverOnElement(driver, ele);
	  driver.findElement(By.linkText("Sign Out")).click();
	  driver.close();


	}

}
