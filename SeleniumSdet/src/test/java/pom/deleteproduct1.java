package pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import comcast.vtiger.genericUtility.Excel_Utility;
import comcast.vtiger.genericUtility.File_Utility;
import comcast.vtiger.genericUtility.Java_Utility;
import comcast.vtiger.genericUtility.WebDriver_Utility;
import comcast.vtiger.objectRepository.CreateProductPage;
import comcast.vtiger.objectRepository.ProductPage;
import comcast.vtiger.objectRepository.homePage;
import comcast.vtiger.objectRepository.loginPage;

public class deleteproduct1 {

	public static void main(String[] args) throws Throwable {
		 WebDriver_Utility wlib= new WebDriver_Utility();
			
			WebDriver driver=new ChromeDriver();
			wlib.waitForPageToLoad(driver);
	     
			//fetching data from property file and logging into vtiger
			File_Utility plib= new File_Utility();
			String Browser=plib.getPropertyKeyValue("browser");
			String Url=plib.getPropertyKeyValue("url");
			String Username=plib.getPropertyKeyValue("un");
			String Password=plib.getPropertyKeyValue("pw");
			
			//fetched data entering into vtiger login page
			driver.get(Url);
			loginPage loginpage = new loginPage(driver);
			loginpage.login(Username, Password);
			
			//selecting and creating products
			homePage homepage = new homePage(driver);
			homepage.clickproductsModule();
			
			ProductPage productpage= new ProductPage(driver);
			productpage.ProductPagePlusBtn();
			
			//random number for product name
			Java_Utility jlib= new Java_Utility(); 
			int ranNum = jlib.getRandomNum();
			
			 Excel_Utility elib=new Excel_Utility();
			String productName = elib.getDataFromExcel("products", 1, 0)+ranNum;
	        System.out.println(productName);
	        
	      //entering product name into product field
	        CreateProductPage createProduct = new CreateProductPage(driver);
			createProduct.productNameField(productName);
			
	        //save the create product page
			createProduct.productSaveBtn();
	        
	        //get the header text of create product
	        String actData = driver.findElement(By.cssSelector("span.lvtHeaderText")).getText();
	        if(actData.contains(productName))
	        {
	        	System.out.println("pass");
	        }
	        else
	        {
	        	System.out.println("fail");
	        }
	       
	        //delete the product created
	        createProduct.productDelete();
	        
	        //alertpopup
	        wlib.alertPopupok(driver);
			
			//sign out
			 
	        homepage.mouseoverOnAdminstrator(driver);
	        homepage.clicksignoutBtn();
	        
			  driver.close();

	}

}
