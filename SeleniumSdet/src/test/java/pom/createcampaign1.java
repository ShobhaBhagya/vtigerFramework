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
import comcast.vtiger.objectRepository.homePage;
import comcast.vtiger.objectRepository.loginPage;

public class createcampaign1 {
	public static void main(String[] args) throws Throwable
	{
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
	
	//selecting campaign from more and creating campaign
	homePage homepage = new homePage(driver);
	homepage.clickmoreModule();
	homepage.clickcampaignModule();
	
	CampaignPage campaignpage= new CampaignPage(driver);
	campaignpage.CampaignPagePlusBtn();
	
	//random number for campaign name
	Java_Utility jlib= new Java_Utility();
	int ranNum = jlib.getRandomNum();
	
	Excel_Utility elib=new Excel_Utility();
	String campaignName = elib.getDataFromExcel("campaigns", 1, 0)+ranNum;
	System.out.println(campaignName);
	
	//entering cmpaign name into campaign field
    CreateCampaignPage createcampaign = new CreateCampaignPage(driver);
    createcampaign.CampaignNameField(campaignName);
    
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
  
    //sign out
    homepage.mouseoverOnAdminstrator(driver);
    homepage.clicksignoutBtn();
    
    driver.close();
}
}
