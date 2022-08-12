package com.crm.sdet.organization;

import static org.testng.Assert.assertEquals;

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
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import comcast.vtiger.genericUtility.BaseClass;
import comcast.vtiger.genericUtility.Excel_Utility;
import comcast.vtiger.genericUtility.File_Utility;
import comcast.vtiger.genericUtility.Java_Utility;
import comcast.vtiger.genericUtility.WebDriver_Utility;
import comcast.vtiger.objectRepository.CampaignPage;
import comcast.vtiger.objectRepository.CreateCampaignPage;
import comcast.vtiger.objectRepository.CreateOrganizationPage;
import comcast.vtiger.objectRepository.CreateProductPage;
import comcast.vtiger.objectRepository.OrganizationPage;
import comcast.vtiger.objectRepository.OrganizationValidation;
import comcast.vtiger.objectRepository.ProductChildWndInCampaignPage;
import comcast.vtiger.objectRepository.ProductPage;
import comcast.vtiger.objectRepository.homePage;
import comcast.vtiger.objectRepository.loginPage;
@Listeners(comcast.vtiger.genericUtility.ListenerImplementationClass.class)
public class CreateOrganizationTest extends BaseClass{
	
    @Test(groups= {"regressionTest"})
	public void CreateOrganization() throws Throwable {
	
    	 WebDriver_Utility wlib= new WebDriver_Utility();
			wlib.waitForPageToLoad(driver);
			
    	        //selecting and creating organization 
				homePage homepage = new homePage(driver);
				homepage.clickorganizationModule();
				
				OrganizationPage orgpage= new OrganizationPage(driver);
				orgpage.OrganizationPagePlusBtn();

				//random number for organization name
				Java_Utility jlib= new Java_Utility();
				int ranNum = jlib.getRandomNum();
				
				Excel_Utility elib=new Excel_Utility();
				String orgName = elib.getDataFromExcel("Organization", 1, 0)+ranNum;
		        System.out.println(orgName);
		        
		        //entering organization name into organization field
		        CreateOrganizationPage createorganization = new CreateOrganizationPage(driver);
		        createorganization.OrganizationName(orgName);
		        
		        //Assert.assertEquals(false, true);
		        
		        //save the create organization page
		        createorganization.OrganizationSave();
		        
		        //get the header text of create organization
		        OrganizationValidation actualOrganizationName = new OrganizationValidation(driver);
		        String actualOrganizationdata = actualOrganizationName.actualOrganizationName();
		        Assert.assertEquals(actualOrganizationdata.contains(orgName), true);
		        
		        
//		        String actData = driver.findElement(By.cssSelector("span.dvHeaderText")).getText();
//		        if(actData.contains(orgName))
//		        {
//		        	System.out.println("pass");
//		        }
//		        else
//		        {
//		        	System.out.println("fail");
//		        }	
		        //sign out   
			      homepage.mouseoverOnAdminstrator(driver);
			      homepage.clicksignoutBtn();
			        
				  driver.close();
				  
	}
    
}
