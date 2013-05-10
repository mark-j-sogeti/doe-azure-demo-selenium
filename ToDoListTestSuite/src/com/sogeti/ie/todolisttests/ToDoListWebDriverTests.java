package com.sogeti.ie.todolisttests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ToDoListWebDriverTests extends BaseTestWebDriver {
	private final String TODOLIST_HOME = "http://todolist-bh.azurewebsites.net/";
	private final String TODOLIST_DELETE = "http://todolist-bh.azurewebsites.net/Home/Delete/";
	
	@Test
	public void testAddToDoItem(){
		
		String newListItem = "New list item " + System.currentTimeMillis();
		
		boolean isInList  = false;
		
		assertTrue("Url Mismatch! Not on ToDo List home page!", driver.getCurrentUrl().equals(TODOLIST_HOME));
		
		driver.findElement(By.id("createID")).click();
		
		driver.findElement(By.id("Name")).sendKeys(newListItem);
		
		driver.findElement(By.id("createButton")).click();
		
		assertTrue("Url Mismatch! Not on ToDo List home page!", driver.getCurrentUrl().equals(TODOLIST_HOME));
		
		List<WebElement> itemNameList = driver.findElements(By.className("itemName"));
		
		beforefor:
		for(WebElement w : itemNameList){

			if (w.getText().equals(newListItem)){
				isInList = true;
				break beforefor;
			}
		}

		assertTrue("New Item is not in list", isInList);
	}
	
	@Test
	public void testAddAndRemoveToDoItem(){

		String newListItem = "New list item " + System.currentTimeMillis();
		
		boolean isInList  = false;
		
		assertTrue("Url Mismatch! Not on ToDo List home page!", driver.getCurrentUrl().equals(TODOLIST_HOME));
		
		driver.findElement(By.id("createID")).click();
		
		driver.findElement(By.id("Name")).sendKeys(newListItem);
		
		driver.findElement(By.id("createButton")).click();
		
		assertTrue("Url Mismatch! Not on ToDo List home page!", driver.getCurrentUrl().equals(TODOLIST_HOME));
		
		List<WebElement> itemNameList = driver.findElements(By.className("itemName"));
		
		beforefor:
		for(WebElement w : itemNameList){

			if (w.getText().equals(newListItem)){
				isInList = true;
				break beforefor;
			}
		}

		assertTrue("New Item is not in list", isInList);
		
		List<WebElement> itemDeleteLinkList = driver.findElements(By.className("deleteItemLink"));
		
		for(int i = 0; i < itemNameList.size(); i++){
			if(itemNameList.get(i).getText().equals(newListItem)){
				itemDeleteLinkList.get(i).click();
				assertTrue("Url Mismatch! Not on ToDo List delete page!", driver.getCurrentUrl().contains(TODOLIST_DELETE));
				driver.findElement(By.id("deleteButton")).click();
				assertTrue("Url Mismatch! Not on ToDo List home page!", driver.getCurrentUrl().equals(TODOLIST_HOME));
				break;
			}
		}
		
		itemNameList = driver.findElements(By.className("itemName"));
		
		for(WebElement w : itemNameList){
			assertFalse("New List Item not deleted!", w.getText().equals(newListItem));
		}
		
	}
}
