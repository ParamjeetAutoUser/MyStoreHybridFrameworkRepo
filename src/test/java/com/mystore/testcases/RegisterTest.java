package com.mystore.testcases;

import java.time.Duration;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.mystore.base.BaseClass;
import com.mystore.pageobjects.AccountSuccessPage;
import com.mystore.pageobjects.HomePage;
import com.mystore.pageobjects.RegisterPage;
import com.mystore.utility.Utilities;

public class RegisterTest extends BaseClass {
	
	RegisterPage registerPage;
	AccountSuccessPage accountSuccessPage;
	public WebDriver driver;
	
	public RegisterTest() {
		super();
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	
	@BeforeMethod
	public void setup()
	{
		driver= initializeBrowserAndOpenApplicationURL(prop.getProperty("browserName"));
		//driver.findElement(By.xpath("//span[text()='My Account']")).click();
		//driver.findElement(By.linkText("Register")).click();
		HomePage homePage = new HomePage(driver);
		registerPage= homePage.navigateToRegisterPage();
		
		/*homePage.clickOnMyAccount();
		registerPage= homePage.selectRegisterOption();*/
	}
	
	
	@Test(priority=1)
	public void verifyRegisteringAnAccountWithMandatoryFields()
	{
		
		/*
		 * driver.findElement(By.id("input-firstname")).sendKeys(dataProp.getProperty(
		 * "firstName"));
		 * driver.findElement(By.id("input-lastname")).sendKeys(dataProp.getProperty(
		 * "lastName")); driver.findElement(By.id("input-email")).sendKeys(Utilities.
		 * generateEmailWithTimeStamp());
		 * driver.findElement(By.id("input-telephone")).sendKeys(dataProp.getProperty(
		 * "telephoneNumber"));
		 * driver.findElement(By.id("input-password")).sendKeys(prop.getProperty(
		 * "validPassword"));
		 * driver.findElement(By.id("input-confirm")).sendKeys(prop.getProperty(
		 * "validPassword"));
		 * 
		 * driver.findElement(By.name("agree")).click();
		 * driver.findElement(By.xpath("//input[@value='Continue']")).click();
		 */
		
		/*
		 * registerPage.enterFirstName(dataProp.getProperty("firstName"));
		 * registerPage.enterLastName(dataProp.getProperty("lastName"));
		 * registerPage.enterEmailAddress(Utilities.generateEmailWithTimeStamp());
		 * registerPage.enterTelephoneNumber(dataProp.getProperty("telephoneNumber"));
		 * registerPage.enterPassword(prop.getProperty("validPassword"));
		 * registerPage.enterConfirmPassword(prop.getProperty("validPassword"));
		 * registerPage.selectPrivacyPolicy();
		 * accountSuccessPage=registerPage.clickOnContinueButton();
		 */
		
		accountSuccessPage=registerPage.registerWithMandatoryFields(dataProp.getProperty("firstName"), dataProp.getProperty("lastName"), Utilities.generateEmailWithTimeStamp(), dataProp.getProperty("telephoneNumber"), prop.getProperty("validPassword"), prop.getProperty("validPassword"));
		Assert.assertEquals(accountSuccessPage.retrieveAccountSuccessPageHeading(),dataProp.getProperty("accountSuccessfullyCreatedHeading"),"Account Success page is not displayed");
		
		String actualSuccessHeading = accountSuccessPage.retrieveAccountSuccessPageHeading();
		System.out.println("Actual Success Header: "+actualSuccessHeading);
		
	}
	
	@Test(priority=2)
	public void verifyRegisteringAccountByProvidingAllFields()
	{
		
		/*
		 * registerPage.enterFirstName(dataProp.getProperty("firstName"));
		 * registerPage.enterLastName(dataProp.getProperty("lastName"));
		 * registerPage.enterEmailAddress(Utilities.generateEmailWithTimeStamp());
		 * registerPage.enterTelephoneNumber(dataProp.getProperty("telephoneNumber"));
		 * registerPage.enterPassword(prop.getProperty("validPassword"));
		 * registerPage.enterConfirmPassword(prop.getProperty("validPassword"));
		 * registerPage.selectYesNewsletterOption(); registerPage.selectPrivacyPolicy();
		 * accountSuccessPage= registerPage.clickOnContinueButton();
		 */
		
		accountSuccessPage=registerPage.registerWithAllFields(dataProp.getProperty("firstName"), dataProp.getProperty("lastName"), Utilities.generateEmailWithTimeStamp(), dataProp.getProperty("telephoneNumber"), prop.getProperty("validPassword"), prop.getProperty("validPassword"));
		
		String actualSuccessHeading = accountSuccessPage.retrieveAccountSuccessPageHeading();
		System.out.println("Actual Success Header for TC2: "+actualSuccessHeading);
		Assert.assertEquals(accountSuccessPage.retrieveAccountSuccessPageHeading(),dataProp.getProperty("accountSuccessfullyCreatedHeading"),"Account Success page is not displayed");
			
	}
	
	@Test(priority=3)
	public void verifyRegisteringAccountWithExistingEmailAddress()
	{
		
		registerPage.enterFirstName(dataProp.getProperty("firstName"));
		registerPage.enterLastName(dataProp.getProperty("lastName"));
		registerPage.enterEmailAddress(prop.getProperty("validEmail"));
		registerPage.enterTelephoneNumber(dataProp.getProperty("telephoneNumber"));
		registerPage.enterPassword(prop.getProperty("validPassword"));
		registerPage.enterConfirmPassword(prop.getProperty("validPassword"));
		registerPage.selectYesNewsletterOption();
		registerPage.selectPrivacyPolicy();
		registerPage.clickOnContinueButton();
		
		registerPage.registerWithAllFields(dataProp.getProperty("firstName"), dataProp.getProperty("lastName"), prop.getProperty("validEmail"), dataProp.getProperty("telephoneNumber"), prop.getProperty("validPassword"), prop.getProperty("validPassword"));
		Assert.assertTrue(registerPage.retrieveDuplicateEmailAddressWarning().contains(dataProp.getProperty("duplicatEmailWarning")), "Warning message regarding duplicate email address is not getting displayed");
		
		/*
		 * String actualWarningErrorMessage=
		 * registerPage.retrieveDuplicateEmailAddressWarning();
		 * System.out.println("Actual Warning error message: "+actualWarningErrorMessage
		 * ); String
		 * exptdWarningErrorMessage=dataProp.getProperty("duplicatEmailWarning");
		 */
		
	}
	
	@Test(priority=4)
	public void verifyRegisteringAccountWithoutFillingAnyDetails()
	{
	
		registerPage.clickOnContinueButton();	
		Assert.assertTrue(registerPage.displayStatusOfWarningMessages(dataProp.getProperty("privacyPolicyWarning"), dataProp.getProperty("firstNameWarning"), dataProp.getProperty("lastNameWarning"), dataProp.getProperty("emailAddressWarning"), dataProp.getProperty("telephoneNumberWarning"), dataProp.getProperty("passwordWarning")),"Warning message(s) are not displayed");
		
		
		/*
		 * String actualPrivacyWarningMessage=
		 * registerPage.retrievePrivacyPolicyWarning();
		 * Assert.assertTrue(registerPage.retrievePrivacyPolicyWarning().contains(
		 * dataProp.getProperty("privacyPolicyWarning")),
		 * "Expected Privacy Policy Warning message is not getting displayed");
		 * 
		 * String actualfirstNameWarningMessage =
		 * registerPage.retrieveFirstNameWarning();
		 * Assert.assertEquals(registerPage.retrieveFirstNameWarning(),
		 * dataProp.getProperty("firstNameWarning"),
		 * "Fisrt Name Warning message is not displayed");
		 * 
		 * String actualLastNameWarningMessage = registerPage.retrieveLastNameWarning();
		 * Assert.assertEquals(registerPage.retrieveLastNameWarning(),
		 * dataProp.getProperty("lastNameWarning"),
		 * "Last Name Warning message is not displayed");
		 * 
		 * String actualEmailAddressWarningMessage =
		 * registerPage.retrieveEmailWarning();
		 * Assert.assertEquals(registerPage.retrieveEmailWarning(),
		 * dataProp.getProperty("emailAddressWarning"),
		 * "Email Address Warning message is not displayed");
		 * 
		 * String actualTelephoneWarningMessage =
		 * registerPage.retrieveTelephoneWarning();
		 * Assert.assertEquals(registerPage.retrieveTelephoneWarning(),
		 * dataProp.getProperty("telephoneNumberWarning"),
		 * "Telephone Number Warning message is not displayed");
		 * 
		 * String actualPasswordWarningMessage = registerPage.retrievePasswordWarning();
		 * Assert.assertEquals(registerPage.retrievePasswordWarning(),
		 * dataProp.getProperty("passwordWarning"),
		 * "Password Warning message is not displayed");
		 */
		
	}
	
}
