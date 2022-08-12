package ddm;

import java.io.FileInputStream;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import comcast.vtiger.genericUtility.File_Utility;
import comcast.vtiger.genericUtility.WebDriver_Utility;

public class Createopportunities {

	public static void main(String[] args) throws Throwable {
		
		File_Utility pobj=new File_Utility();
		WebDriver_Utility wlib=new WebDriver_Utility();
		
		WebDriver driver= new ChromeDriver();
		wlib.waitForPageToLoad(driver);
		
		String Browser = pobj.getPropertyKeyValue("browser");
		String URL = pobj.getPropertyKeyValue("url");
		String Username = pobj.getPropertyKeyValue("un");
		String Password = pobj.getPropertyKeyValue("pw");
		
		driver.get(URL);
		driver.findElement(By.name("user_name")).sendKeys(Username);
		driver.findElement(By.name("user_password")).sendKeys(Username);
		driver.findElement(By.id("submitButton")).click();
		
//create organization
		driver.findElement(By.linkText("Organizations")).click();
		driver.findElement(By.xpath("//img[@alt='Create Organization...']")).click();
		
		Random ran= new Random();
		int rannum = ran.nextInt(1000);
		
		FileInputStream fis = new FileInputStream("./data/VtigerTestdata.xlsx");
		Workbook wb= WorkbookFactory.create(fis);
		Sheet sh = wb.getSheet("Organization");
		 Row row = sh.getRow(1);
		 Cell cell = row.getCell(0);
		 String orgName = cell.getStringCellValue()+rannum;
		 System.out.println(orgName);
		 
		 driver.findElement(By.name("accountname")).sendKeys(orgName);
		 driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		 
	//create opportunities	
		 Thread.sleep(1000);
		 driver.findElement(By.linkText("Opportunities")).click();
		driver.findElement(By.xpath("//img[@alt='Create Opportunity...']")).click();
		
		Random ran1= new Random();
		int rannum1 = ran1.nextInt(1000);
		
		FileInputStream fis1 = new FileInputStream("./data/VtigerTestdata.xlsx");
		Workbook wb1 =WorkbookFactory.create(fis1);
		Sheet sh1 = wb1.getSheet("opportunities");
		Row row1 =sh1.getRow(1);
		Cell cell1 = row1.getCell(0);
		String opportunity = cell1.getStringCellValue()+rannum1;
		System.out.println(opportunity);
		
		driver.findElement(By.name("potentialname")).sendKeys(opportunity);
		driver.findElement(By.xpath("//img[@title='Select'][1]")).click();
		
		Set<String> allid = driver.getWindowHandles();
		Iterator<String> it = allid.iterator();
		while(it.hasNext()) {
			
			String wid = it.next();
			driver.switchTo().window(wid);
			if(driver.getTitle().contains("Accounts&action"))
			{
				break;
			}
		}
		
		driver.findElement(By.id("search_txt")).sendKeys(orgName);
		driver.findElement(By.name("search")).click();
		driver.findElement(By.xpath("//a[text()='"+orgName+"']")).click();
		
		Set<String> allId1 = driver.getWindowHandles();
		Iterator<String> it1 = allId1.iterator();
		while(it1.hasNext())
		{
			String wid1 = it1.next();
			driver.switchTo().window(wid1);
			if(driver.getTitle().contains("Potentials&action"))
			{
				break;
			}
			
		}
		
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		
		 WebElement ele = driver.findElement(By.xpath("//img[@style='padding: 0px;padding-left:5px']"));
		Actions a = new Actions(driver);
				a.moveToElement(ele).perform();
		driver.findElement(By.linkText("Sign Out")).click();
		driver.close();
		 

	}

}
