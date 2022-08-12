package Practice;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.TimeUnit;

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



public class organization {

	public static void main(String[] args) throws Throwable {
		//step 1: get the java representation object of the physical file(java.uti.package)
		FileInputStream fis=new FileInputStream("./data/Commondata.properties");
		
		//step 2:create an object to property class to load all the keys(java io package)
		Properties pboj= new Properties();
		
		//step3 : load the keys
		pboj.load(fis);
		
	     //step4 :read the value using getproperty("key")
		String Browser=pboj.getProperty("browser");
		String Url=pboj.getProperty("url");
		String Username=pboj.getProperty("un");
		String Password=pboj.getProperty("pw");
		
		//step5: execute the statements
		WebDriver driver=new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get(Url);
		driver.findElement(By.name("user_name")).sendKeys(Username);
		driver.findElement(By.name("user_password")).sendKeys(Password);
		driver.findElement(By.id("submitButton")).click();
		
		driver.findElement(By.linkText("Organizations")).click();
		
		driver.findElement(By.xpath("//img[@alt='Create Organization...' ]")).click();
		
		Random ran =new Random();
		int ranNum=ran.nextInt(1000);
		
		FileInputStream fis1 = new FileInputStream("./data/VtigerTestdata.xlsx");
		Workbook wb= WorkbookFactory.create(fis1);
		Sheet sh = wb.getSheet("Organization");
	    Row row = sh.getRow(1);
		Cell cell = row.getCell(0);
		String orgName = cell.getStringCellValue()+ranNum;
        System.out.println(orgName);
        
        driver.findElement(By.name("accountname")).sendKeys(orgName);
        
        driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
        
        String actData = driver.findElement(By.cssSelector("span.dvHeaderText")).getText();
        
        if(actData.contains(orgName))
        {
        	System.out.println("pass");
        }
        else
        {
        	System.out.println("fail");
        }

      WebElement ele = driver.findElement(By.xpath("//img[@style='padding: 0px;padding-left:5px']"));
	  Actions a =new Actions(driver);
	  a.moveToElement(ele).perform();
	  driver.findElement(By.linkText("Sign Out")).click();
	  driver.close();
	  
	}

}
