package com.crm.sdet.products;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
import comcast.vtiger.objectRepository.CampaignValidation;
import comcast.vtiger.objectRepository.CreateProductPage;
import comcast.vtiger.objectRepository.ProductPage;
import comcast.vtiger.objectRepository.ProductValiation;
import comcast.vtiger.objectRepository.homePage;
import comcast.vtiger.objectRepository.loginPage;

@Listeners(comcast.vtiger.genericUtility.ListenerImplementationClass.class)
public class CreateProductTest extends BaseClass {

	@Test(retryAnalyzer = comcast.vtiger.genericUtility.ReTryImpClass.class)
	public void CreateProduct() throws Throwable {
		
		
        WebDriver_Utility wlib= new WebDriver_Utility();
        wlib.waitForPageToLoad(driver);
		
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
		
		 //Assert.assertEquals(false, true);
		
		//entering product name into product field
		CreateProductPage createProduct = new CreateProductPage(driver);
		createProduct.productNameField(productName);
		
		 //save the create product page
		createProduct.productSaveBtn();
        
        //get the header text of create product
		 ProductValiation actualProductName = new ProductValiation(driver);
	        String actualProductdata = actualProductName.actualProductName();
	        Assert.assertEquals(actualProductdata.contains(productName), true);
	       
		
        //sign out   
	      homepage.mouseoverOnAdminstrator(driver);
	      homepage.clicksignoutBtn();
	        
		  driver.close();	  
	}
}
