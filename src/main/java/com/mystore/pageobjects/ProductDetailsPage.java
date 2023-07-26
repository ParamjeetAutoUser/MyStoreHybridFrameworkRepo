package com.mystore.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductDetailsPage {
	
	WebDriver driver;
	//comment
	@FindBy(xpath = "//div[@id='content']//div[@class='col-sm-4']/ul[2]/li[1]/h2")
	private WebElement productPriceTxt;
	
	@FindBy(xpath = "//div[@id='product-product']/div[@class='alert alert-success alert-dismissible']")
	private WebElement cartSuccessTxt;
	
	@FindBy(xpath = "//div/input[@name='quantity']")
	private WebElement quantityField;
	
	@FindBy(xpath = "//button[@id='button-cart']")
	private WebElement addToCartButton;
	
	@FindBy(xpath = "//div[@id='product-product']/div[@class='alert alert-success alert-dismissible']/a[text()='shopping cart']")
	private WebElement shoppingCartLnk;
	
	public ProductDetailsPage(WebDriver driver)
	{
		this.driver= driver;
		PageFactory.initElements(driver, this);
	}
	
	public void enterQuantity(String quantity)
	{
		quantityField.sendKeys(quantity);
	}
	
	public String fetchProductPrice()
	{
		String fetchProductPriceText= (productPriceTxt.getText()).substring(1);
		return fetchProductPriceText;
	}
	
	public String retrieveCartSuccessText()
	{
		String retrieveCartSuccessText= (cartSuccessTxt.getText());
		return retrieveCartSuccessText;
	}
	
	public Boolean getDisplayShoppingCartLink()
	{
		Boolean displayStatus= shoppingCartLnk.isDisplayed();
		return displayStatus;
	}
	
	public Boolean getDisplayRetrieveCartSuccessText()
	{
		Boolean displayStatus= cartSuccessTxt.isDisplayed();
		return displayStatus;
	}
	
	public void clickShoppingCart()
	{
		
	}
	
}
