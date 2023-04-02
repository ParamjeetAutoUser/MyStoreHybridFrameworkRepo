package com.mystore.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.mystore.base.BaseClass;
import com.mystore.pageobjects.HomePage;
import com.mystore.pageobjects.SearchPage;

public class SearchTest extends BaseClass{
	public WebDriver driver;
	SearchPage searchPage;
	HomePage homePage;
	
	public SearchTest() {
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
		homePage= new HomePage(driver);
	}
	
	@Test(priority=1)
	public void verifySearchWithValidProduct()
	{
		searchPage=homePage.searchForAProduct(dataProp.getProperty("validProduct"));
		/*
		 * homePage.enterProductIntoSearchBox(dataProp.getProperty("validProduct"));
		 * searchPage=homePage.clickOnSearchButton();
		 */
		
		Assert.assertTrue(searchPage.displayStatusOfHPValidProduct(),"Valid HP Product is not displayed");

	}
	
	@Test(priority=2)
	public void verifySearchWithInValidProduct()
	{
		searchPage=homePage.searchForAProduct(dataProp.getProperty("invalidProduct"));
		/*
		 * homePage.enterProductIntoSearchBox(dataProp.getProperty("invalidProduct"));
		 * searchPage = homePage.clickOnSearchButton();
		 */
		
		String actualSearchMessage= searchPage.retrieveNoProductMessageText();
		Assert.assertEquals(searchPage.retrieveNoProductMessageText(),dataProp.getProperty("NoProductTextInSearchResults"), "No Product message in search results is displayed");

	}
	
	@Test(priority=3, dependsOnMethods = {"verifySearchWithInValidProduct"})
	public void verifySearchWithoutAnyProduct()
	{
		searchPage= homePage.clickOnSearchButton(); 	
		String actualSearchMessage= searchPage.retrieveNoProductMessageText();
		Assert.assertEquals(searchPage.retrieveNoProductMessageText(),dataProp.getProperty("NoProductTextInSearchResults"), "No Product message in search results is displayed");

	}

}
