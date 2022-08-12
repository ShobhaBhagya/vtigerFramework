package pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import comcast.vtiger.genericUtility.Excel_Utility;
import comcast.vtiger.genericUtility.File_Utility;
import comcast.vtiger.genericUtility.Java_Utility;
import comcast.vtiger.genericUtility.WebDriver_Utility;
import comcast.vtiger.objectRepository.CampaignPage;
import comcast.vtiger.objectRepository.CreateCampaignPage;
import comcast.vtiger.objectRepository.CreateProductPage;
import comcast.vtiger.objectRepository.ProductChildWndInCampaignPage;
import comcast.vtiger.objectRepository.ProductPage;
import comcast.vtiger.objectRepository.homePage;
import comcast.vtiger.objectRepository.loginPage;

public class campaignwithproductnam1 {

	public static void main(String[] args) throws Throwable {
		Java_Utility jlib= new Java_Utility(); 
        Excel_Utility elib=new Excel_Utility();
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
		
		//creating product
		homePage homepage = new homePage(driver);
		homepage.clickproductsModule();
		
		ProductPage productpage= new ProductPage(driver);
		productpage.ProductPagePlusBtn();
	
		//random number for product name
		int ranNum = jlib.getRandomNum();
		String productName = elib.getDataFromExcel("products", 1, 0)+ranNum;
		System.out.println(productName);
		
		//entering product name into product field and saving 
		CreateProductPage createProduct = new CreateProductPage(driver);
		createProduct.productNameField(productName);
		createProduct.productSaveBtn();
		
        //select campaign with more option
		homepage.clickmoreModule();
		homepage.clickcampaignModule();

		CampaignPage campaignpage= new CampaignPage(driver);
		campaignpage.CampaignPagePlusBtn();
	
		//random number for campaign name
		int ranNum2 = jlib.getRandomNum();
		String campaignName = elib.getDataFromExcel("campaigns", 1, 0)+ranNum2;
        System.out.println(campaignName);
        
      //entering cmpaign name into campaign field
        CreateCampaignPage createcampaign = new CreateCampaignPage(driver);
        createcampaign.CampaignNameField(campaignName);
        
        //In create campaign page select product name in child window
        createcampaign.CreateCampaignProductPlusBtn();
    
        
        //switching to child window
        wlib.switchToWindow(driver, "Products&action");       
        
        ProductChildWndInCampaignPage searchproduct =new ProductChildWndInCampaignPage(driver);
        searchproduct.SearchFieldProductName(productName);
        searchproduct.SearchButtonClick(driver, productName);
        //driver.findElement(By.xpath("//a[text()='"+productName+"']")).click();
        
        //switch back to parent window
        wlib.switchToWindow(driver, "Campaigns&action");
        
        //save the create campaign page
        createcampaign.CampaignSaveBtn();
        
      //get the header text of create campaign
        String actData = driver.findElement(By.cssSelector("span.dvHeaderText")).getText();
        if(actData.contains(campaignName))
        {
        	System.out.println("pass");
        }
        else
        {
        	System.out.println("fail");
        }
       
    //log out
        homepage.mouseoverOnAdminstrator(driver);
        homepage.clicksignoutBtn();
        
	  driver.close();

	}

}
