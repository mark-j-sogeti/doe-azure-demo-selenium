package com.sogeti.ie.todolisttests;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.support.PageFactory;

public class BaseTestHtmlUnitDriver
{
    public static HtmlUnitDriver driver;
    protected final static String WEBSITE_URL = String.format("http://%s.azurewebsites.net/", System.getProperty("SOURCE_WEBSITE"));

    @BeforeClass
    public static void navigateToHomePage() throws Exception
    {
        driver = new HtmlUnitDriver();
        driver.setJavascriptEnabled(false);
        driver.get(WEBSITE_URL);
    }

    @AfterClass
    public static void closeBrowser() throws Exception
    {
        driver.close();
    }

    public WebElement findElementByName(String elementName)
    {
        return driver.findElement(By.name(elementName));
    }

    public static <T> T bindToPage(Class<T> clazz)
    {
        return PageFactory.initElements(driver, clazz);
    }
}