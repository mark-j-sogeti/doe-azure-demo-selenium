package com.sogeti.ie.todolisttests;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ToDoListHtmlUnitDriverTests extends BaseTestHtmlUnitDriver
{
    private final String TODOLIST_HOME = WEBSITE_URL;
    private final String TODOLIST_DELETE = String.format("%sHome/Delete", TODOLIST_HOME);

    @Test
    public void testAddToDoItem()
    {
        String newListItem = "New list item " + System.currentTimeMillis();

        boolean isInList = false;

        assertTrue(String.format("Url Mismatch! Not on %s home page!", WEBSITE_URL), driver.getCurrentUrl().equals(getHomePage()));

        driver.findElement(By.id("createID")).click();

        driver.findElement(By.id("Name")).sendKeys(newListItem);

        driver.findElement(By.id("createButton")).click();

        assertTrue(String.format("Url Mismatch! Not on %s home page!", WEBSITE_URL), driver.getCurrentUrl().equals(getHomePage()));

        List<WebElement> itemNameList = driver.findElements(By.className("itemName"));

        beforefor:
        for (WebElement w : itemNameList)
        {
            if (w.getText().equals(newListItem))
            {
                isInList = true;
                break beforefor;
            }
        }

        assertTrue("New Item is not in list", isInList);
    }

    @Test
    public void testAddAndRemoveToDoItem()
    {
        String newListItem = "New list item " + System.currentTimeMillis();

        boolean isInList = false;

        assertTrue(String.format("Url Mismatch! Not on %s home page!", getHomePage()), driver.getCurrentUrl().equals(getHomePage()));

        driver.findElement(By.id("createID")).click();

        driver.findElement(By.id("Name")).sendKeys(newListItem);

        driver.findElement(By.id("createButton")).click();

        assertTrue(String.format("Url Mismatch! Not on %s home page!", getHomePage()), driver.getCurrentUrl().equals(getHomePage()));

        List<WebElement> itemNameList = driver.findElements(By.className("itemName"));

        beforefor:
        for (WebElement w : itemNameList)
        {
            if (w.getText().equals(newListItem))
            {
                isInList = true;
                break beforefor;
            }
        }

        assertTrue("New Item is not in list", isInList);

        List<WebElement> itemDeleteLinkList = driver.findElements(By.className("deleteItemLink"));

        for (int i = 0; i < itemNameList.size(); i++)
        {
            if (itemNameList.get(i).getText().equals(newListItem))
            {
                itemDeleteLinkList.get(i).click();
                assertTrue(String.format("Url Mismatch! %s is not on %s delete page!", driver.getCurrentUrl(), getDeletePage()), driver.getCurrentUrl().contains(getDeletePage()));
                driver.findElement(By.id("deleteButton")).click();
                assertTrue(String.format("Url Mismatch! Not on %s home page!", getHomePage()), driver.getCurrentUrl().equals(getHomePage()));
                break;
            }
        }

        itemNameList = driver.findElements(By.className("itemName"));

        for (WebElement w : itemNameList)
        {
            assertFalse("New List Item not deleted!", w.getText().equals(newListItem));
        }
    }

    private String getDeletePage()
    {
        return TODOLIST_DELETE;
    }

    private String getHomePage()
    {
        return TODOLIST_HOME;
    }
}