package com.mystore.testcases;

import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.mystore.base.BaseClass;
import com.mystore.pageobjects.AccountPage;
import com.mystore.pageobjects.HomePage;
import com.mystore.pageobjects.LoginPage;
import com.mystore.utility.Utilities;

public class LoginTest extends BaseClass {
	
	LoginPage loginPage;
	public WebDriver driver;
	
	public LoginTest() {
		super();
	}
	
	@BeforeMethod
	public void setup()
	{
		driver= initializeBrowserAndOpenApplicationURL(prop.getProperty("browserName"));
		HomePage homePage = new HomePage(driver);
		loginPage= homePage.navigateToLoginPage();
		/*
		 * driver.findElement(By.xpath("//span[text()='My Account']")).click();
		 * driver.findElement(By.linkText("Login")).click();
		 */
		
	}
	 
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	
	
	@Test(priority=1, dataProvider = "supplyTestData")
	public void verifyLoginWithValidCredentials(String email, String password)
	{
		/*
		 * driver.findElement(By.id("input-email")).sendKeys(prop.getProperty(
		 * "validEmail"));
		 * driver.findElement(By.id("input-password")).sendKeys(prop.getProperty(
		 * "validPassword"));
		 */
		/*
		 * driver.findElement(By.id("input-email")).sendKeys(email);
		 * driver.findElement(By.id("input-password")).sendKeys(password);
		 * driver.findElement(By.xpath("//input[@value='Login']")).click();
		 */
				
		AccountPage accountPage = loginPage.login(email, password);
		
		Assert.assertTrue(accountPage.getDisplayStatusOfChangeYourPassword(),"Change Your Password Link is not displayed");
			
	}
	
	@DataProvider
	public Object[][] supplyTestData()
	{
		Object[][] data= Utilities.getTestDatafromExcel("Login");
			/*{{"pd12ys12@gmail.com","Qwer@1234"},
				{"pd12ys13@gmail.com","Qwer@1234"},
				{"pd12ys14@gmail.com","Qwer@1234"}};*/
		return data;	
	}
	
	@Test(priority=2)
	public void verifyLoginWithInValidCredentials()
	{
	
		/*
		 * driver.findElement(By.id("input-email")).sendKeys("pd12ys12"+Utilities.
		 * generateEmailWithTimeStamp()+"@gmail.com");
		 * driver.findElement(By.id("input-password")).sendKeys(dataProp.getProperty(
		 * "invalidPassword"));
		 * driver.findElement(By.xpath("//input[@value='Login']")).click();
		 */
		loginPage.login(Utilities.generateEmailWithTimeStamp(), dataProp.getProperty("invalidPassword"));
		/*
		 * String actualWarningErrorMessage=
		 * loginPage.retrieveEmailPasswordNotMatchingWarningMessageText(); //String
		 * actualWarningErrorMessage=
		 * driver.findElement(By.xpath("//div[contains(@class,'alert-dismissible')]")).
		 * getText();
		 * 
		 * System.out.println("Actual Warning error message: "+actualWarningErrorMessage
		 * ); String
		 * exptdWarningErrorMessage=dataProp.getProperty("emailPasswordNoMatchWarning");
		 */
		
		Assert.assertTrue(loginPage.retrieveEmailPasswordNotMatchingWarningMessageText().contains(dataProp.getProperty("emailPasswordNoMatchWarning")), "Expected Warning message is not getting displayed");
	}
	
	@Test(priority=3)
	public void verifyLoginWithInValidEmailValidPassword()
	{
		
		//driver.findElement(By.id("input-email")).sendKeys("pd12ys12"+Utilities.generateEmailWithTimeStamp()+"@gmail.com");
		//driver.findElement(By.id("input-password")).sendKeys(prop.getProperty("validPassword"));
		//driver.findElement(By.xpath("//input[@value='Login']")).click();
		
		/*
		 * loginPage.enterEmailAddress(Utilities.generateEmailWithTimeStamp());
		 * loginPage.enterPassword(prop.getProperty("validPassword"));
		 * loginPage.clickOnLoginButton();
		 */
		
		loginPage.login(Utilities.generateEmailWithTimeStamp(), prop.getProperty("validPassword"));
		Assert.assertTrue(loginPage.retrieveEmailPasswordNotMatchingWarningMessageText().contains(dataProp.getProperty("emailPasswordNoMatchWarning")), "Expected Warning message is not getting displayed");
		
		//String actualWarningErrorMessage= loginPage.retrieveEmailPasswordNotMatchingWarningMessageText();
		
		//String actualWarningErrorMessage= driver.findElement(By.xpath("//div[contains(@class,'alert-dismissible')]")).getText();
		/*
		 * System.out.println("Actual Warning error message in Login TC3: "
		 * +actualWarningErrorMessage); String
		 * exptdWarningErrorMessage=dataProp.getProperty("emailPasswordNoMatchWarning");
		 * 
		 * Assert.assertTrue(actualWarningErrorMessage.contains(exptdWarningErrorMessage
		 * ), "Expected Warning message is not getting displayed");
		 */
		
	}
	
	@Test(priority=4)
	public void verifyLoginWithValidEmailInValidPassword()
	{
		/*
		 * driver.findElement(By.id("input-email")).sendKeys(prop.getProperty(
		 * "validEmail"));
		 * driver.findElement(By.id("input-password")).sendKeys(dataProp.getProperty(
		 * "invalidPassword"));
		 * driver.findElement(By.xpath("//input[@value='Login']")).click();
		 * 
		 * String actualWarningErrorMessage=
		 * driver.findElement(By.xpath("//div[contains(@class,'alert-dismissible')]")).
		 * getText();
		 */
		
		/*
		 * loginPage.enterEmailAddress(prop.getProperty("validEmail"));
		 * loginPage.enterPassword(dataProp.getProperty("invalidPassword"));
		 * loginPage.clickOnLoginButton();
		 */
		
		loginPage.login(prop.getProperty("validEmail"), dataProp.getProperty("invalidPassword"));
		Assert.assertTrue(loginPage.retrieveEmailPasswordNotMatchingWarningMessageText().contains(dataProp.getProperty("emailPasswordNoMatchWarning")), "Expected Warning message is not getting displayed");
		
		/*
		 * String actualWarningErrorMessage=
		 * loginPage.retrieveEmailPasswordNotMatchingWarningMessageText();
		 * 
		 * System.out.println("Actual Warning error message: "+actualWarningErrorMessage
		 * ); String
		 * exptdWarningErrorMessage=dataProp.getProperty("emailPasswordNoMatchWarning");
		 * 
		 * Assert.assertTrue(actualWarningErrorMessage.contains(exptdWarningErrorMessage
		 * ), "Expected Warning message is not getting displayed");
		 */
	}
	
	@Test(priority=5)
	public void verifyLoginWithoutProvidingCredentials()
	{	
		
		//driver.findElement(By.xpath("//input[@value='Login']")).click();
		
		loginPage.clickOnLoginButton();
		Assert.assertTrue(loginPage.retrieveEmailPasswordNotMatchingWarningMessageText().contains(dataProp.getProperty("emailPasswordNoMatchWarning")), "Expected Warning message is not getting displayed");
		
		
		/*
		 * String actualWarningErrorMessage=
		 * loginPage.retrieveEmailPasswordNotMatchingWarningMessageText();
		 * 
		 * //String actualWarningErrorMessage=
		 * driver.findElement(By.xpath("//div[contains(@class,'alert-dismissible')]")).
		 * getText();
		 * System.out.println("Actual Warning error message: "+actualWarningErrorMessage
		 * ); String
		 * exptdWarningErrorMessage=dataProp.getProperty("emailPasswordNoMatchWarning");
		 * 
		 * Assert.assertTrue(actualWarningErrorMessage.contains(exptdWarningErrorMessage
		 * ), "Expected Warning message is not getting displayed");
		 */
		
	}
	
	public String generateTimeStamp()
	{
		Date date= new Date();
		return date.toString().replace(" ","_").replace(":", "_");
	}
	public String generateEmailWithTimeStamp()
	{
		Date date= new Date();
		String timestamp= date.toString().replace(" ","_").replace(":", "_");
		return "pd7ys12"+timestamp+"@gmail.com";
		
	}
}
