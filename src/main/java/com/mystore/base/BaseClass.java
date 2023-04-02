package com.mystore.base;

import java.io.File;
import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.mystore.utility.Utilities;

public class BaseClass {
	
	WebDriver driver;
	public Properties prop;
	public Properties dataProp;
	
	public BaseClass()
	{
		prop = new Properties();
		File propFile = new File(System.getProperty("user.dir")+ "\\src\\main\\java\\com\\mystore\\config\\config.properties");
		
		dataProp = new Properties();
		File dataPropFile = new File(System.getProperty("user.dir")+ "\\src\\main\\java\\com\\mystore\\testdata\\testdata.properties");
		
		try {
			FileInputStream fis = new FileInputStream(propFile);
			prop.load(fis);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		try {
			FileInputStream dataFis = new FileInputStream(dataPropFile);
			dataProp.load(dataFis);
		}catch(Throwable e)
		{
			e.printStackTrace();
		}
		
	}
	
	public WebDriver initializeBrowserAndOpenApplicationURL(String browserName)
	{
		
		if(browserName.equalsIgnoreCase("chrome"))
		{
			ChromeOptions chromeoptions= new ChromeOptions();
			chromeoptions.addArguments("--remote-allow-origins=*");
			
			driver= new ChromeDriver(chromeoptions);
		}
		else if(browserName.equalsIgnoreCase("firefox"))
		{
			driver= new FirefoxDriver();
		}
		else if (browserName.equalsIgnoreCase("edge"))
		{
			driver= new EdgeDriver();
		}
		else if (browserName.equalsIgnoreCase("safari"))
		{
			driver= new SafariDriver();
		}
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Utilities.IMPLICIT_WAIT_TIME));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Utilities.PAGE_LOAD_TIME));
		driver.get(prop.getProperty("url"));
		
		return driver;
		
	}

}
