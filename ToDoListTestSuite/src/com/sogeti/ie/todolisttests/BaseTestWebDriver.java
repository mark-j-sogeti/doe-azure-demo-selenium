package com.sogeti.ie.todolisttests;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;

public class BaseTestWebDriver {
public static WebDriver driver;
	
	@BeforeClass
	public static void navigateToHomePage() throws Exception {
		
		driver = new FirefoxDriver();
		driver.get("http://todolist-bh.azurewebsites.net/");

	}
	
	@AfterClass
	public static void closeBrowser() throws Exception{
		driver.close();
    }
	
	public WebElement findElementByName(String elementName){
		
		return driver.findElement(By.name(elementName));
	}

	public static <T> T  bindToPage(Class<T> clazz){
	    return  PageFactory.initElements(driver, clazz);	
	}
}
