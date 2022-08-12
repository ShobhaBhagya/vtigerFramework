package pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import comcast.vtiger.genericUtility.Excel_Utility;
import comcast.vtiger.genericUtility.File_Utility;
import comcast.vtiger.genericUtility.Java_Utility;
import comcast.vtiger.genericUtility.WebDriver_Utility;
import comcast.vtiger.objectRepository.CreateOrganizationPage;
import comcast.vtiger.objectRepository.OrganizationPage;
import comcast.vtiger.objectRepository.homePage;
import comcast.vtiger.objectRepository.loginPage;

public class createorganization1 {

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
			        
			        //save the create organization page
			        createorganization.OrganizationSave();
			        
			        //get the header text of create organization
			        String actData = driver.findElement(By.cssSelector("span.dvHeaderText")).getText();
			        if(actData.contains(orgName))
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
