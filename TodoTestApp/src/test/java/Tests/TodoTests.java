package Tests;
import java.time.Duration;
import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentTest;

import Utils.ScreenshotUtil;

public class TodoTests extends BaseTestClass {
	 String screenshotPath;
	
	 @Test  (priority = 1)
	    public void testEditTodo() throws InterruptedException {
	    	ExtentTest test = extent.createTest("EditTodo");
	    	 String initialTodo = "Todo1";
	         String updatedTodo = "EditedTodo";
	         
	         // Add a new todo
	         WebElement inputBox = driver.findElement(By.cssSelector("input.new-todo"));         
	         inputBox.sendKeys(initialTodo + "\n");
	        
	         screenshotPath = ScreenshotUtil.takeScreenshot(driver, "EditTodoTests_AddItem");
	         test.pass("Added Todo item "+ initialTodo).addScreenCaptureFromPath(screenshotPath);

	         Thread.sleep(5000);
	         // Verify the todo is added
	         WebElement todoItem = driver.findElement(By.xpath("//label[text()='" + initialTodo + "']"));
	         Assert.assertTrue(todoItem.isDisplayed(), "Todo item was added successfully!");
	         screenshotPath = ScreenshotUtil.takeScreenshot(driver, "EditTodoTests_AddItem_display");
	         test.pass("Added Todo item "+ initialTodo+"displayed successfully").addScreenCaptureFromPath(screenshotPath);
	         

	         // Double-click to edit the todo
	         Actions actions = new Actions(driver);
	         actions.doubleClick(todoItem).perform();
	   
	         Thread.sleep(2000);
	         // Find the editable input field and update the text
	         WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // 10 seconds timeout
	         WebElement editInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/section/main/ul/li/div/div/input")));
	         actions.doubleClick(editInput).perform();
	         editInput.sendKeys(Keys.BACK_SPACE);
	         Thread.sleep(2000);
	         screenshotPath = ScreenshotUtil.takeScreenshot(driver, "EditTodoTests_EditedState");
	         test.pass("Cleared the item "+ initialTodo+"successfully").addScreenCaptureFromPath(screenshotPath);
	         editInput.sendKeys(updatedTodo);
	         editInput.sendKeys(Keys.ENTER);
	         Thread.sleep(2000);
	         screenshotPath = ScreenshotUtil.takeScreenshot(driver, "EditTodoTests_EditedSuccess");
	         test.pass("Updated the item "+ updatedTodo+"successfully").addScreenCaptureFromPath(screenshotPath);
	         // Verify the todo text is updated
	         WebElement updatedTodoItem = driver.findElement(By.xpath("//label[text()='" + updatedTodo + "']"));
	         screenshotPath = ScreenshotUtil.takeScreenshot(driver, "EditTodoTests_EditedSuccess");
	         Assert.assertTrue(updatedTodoItem.isDisplayed(), "Todo item was updated successfully!");
	         WebElement deleteButton = driver.findElement(By.cssSelector("button.destroy"));
	         deleteButton.click();
	         screenshotPath = ScreenshotUtil.takeScreenshot(driver, "EditTodoTests_DeletedSuccess");
	         test.pass("Deleted the updated item successfully!").addScreenCaptureFromPath(screenshotPath);
	         test.pass("Test for editing todo passed!");
	    }

    @Test (priority = 2)
    public void testAddTodo() throws InterruptedException
    {    	  
    	  ExtentTest test = extent.createTest("AddTodo");
    	  WebElement inputBox = driver.findElement(By.className("new-todo"));
          String todoText = "Learn Selenium";          
          // Add a todo
          inputBox.sendKeys(todoText + "\n");  
          screenshotPath = ScreenshotUtil.takeScreenshot(driver, "AddTodoTests_AddItem");
          test.pass("Entered Todo item "+todoText).addScreenCaptureFromPath(screenshotPath);
          // Verify the todo is added
          WebElement todoItem = driver.findElement(By.xpath("//label[text()='" + todoText + "']"));
          Assert.assertTrue(todoItem.isDisplayed(), "Todo item was added successfully!");
          screenshotPath = ScreenshotUtil.takeScreenshot(driver, "AddTodoTests_AddItemDisplay");
          test.pass("Todo item was added successfully!").addScreenCaptureFromPath(screenshotPath);
          Thread.sleep(5000);         
        
    
    }
    @Test  (priority = 3)
    public void testMarkTodoCompleted() throws InterruptedException {
    	ExtentTest test = extent.createTest("MarkTodoasCompleted");
        WebElement checkbox = driver.findElement(By.cssSelector("input.toggle"));
        checkbox.click();
        screenshotPath = ScreenshotUtil.takeScreenshot(driver, "testMarkTodoCompleted_ClickonComplete");
        test.pass("Clicked on complete!").addScreenCaptureFromPath(screenshotPath);
        // Verify the todo is marked as completed
        WebElement completedTodo = driver.findElement(By.cssSelector("li.completed"));
        Assert.assertTrue(completedTodo.isDisplayed(), "Todo was marked as completed!");
        screenshotPath = ScreenshotUtil.takeScreenshot(driver, "testMarkTodoCompleted_Complete");       
        test.pass("Todo was marked as completed!").addScreenCaptureFromPath(screenshotPath);  
        driver.findElement(By.linkText("Completed")).click();
        int completedCount = driver.findElements(By.cssSelector("ul.todo-list li")).size();
        Assert.assertEquals(completedCount, 1, "Completed todos filtering Passed!");
        screenshotPath = ScreenshotUtil.takeScreenshot(driver, "testMarkTodoCompleted_CompleteFilter");       
        test.pass("Completed todos filtering!").addScreenCaptureFromPath(screenshotPath);  
        Thread.sleep(5000);        
        
    }
    
    @Test  (priority = 4)
    public void testDeleteTodo() throws InterruptedException {
    	ExtentTest test = extent.createTest("DeleteTodo");
    	Actions actions = new Actions(driver);
    	WebElement inputBox = driver.findElement(By.xpath("//div[@class ='view']/label"));       
        actions.moveToElement(inputBox).perform();
        WebElement deleteButton = driver.findElement(By.cssSelector("button.destroy"));
        deleteButton.click();
        screenshotPath = ScreenshotUtil.takeScreenshot(driver, "testDeleteTodo_clickDelete");       
        test.pass("Delete Button Clicked Successfully!").addScreenCaptureFromPath(screenshotPath);         
        // Verify the todo is deleted
        int remainingTodos = driver.findElements(By.cssSelector("ul.todo-list li")).size();
        Assert.assertEquals(remainingTodos, 0, "Todo was deleted successfully!");
        screenshotPath = ScreenshotUtil.takeScreenshot(driver, "testDeleteTodo_TodoCount");       
        test.pass("Todo was deleted!").addScreenCaptureFromPath(screenshotPath);     
        Thread.sleep(5000);       

    }
    

    @Test (priority = 5)
    public void testEmptyTodoTest()
    {
    	ExtentTest test = extent.createTest("EmptyTodotest");
    	WebElement inputBox = driver.findElement(By.xpath("//input[@id = 'todo-input']"));   
    	System.out.println("GetEmpty*************"+inputBox.getAttribute("placeholder"));
    	Assert.assertEquals(inputBox.getAttribute("placeholder"), "What needs to be done?", "Todo page with emptytodo tests are tested successfully!");
    	screenshotPath = ScreenshotUtil.takeScreenshot(driver, "testEmptyTodoTest_Emptytodo");     
    	test.pass("Test for EmptyTodotest passed!").addScreenCaptureFromPath(screenshotPath); 
    	
    }
    
    @Test (priority = 6)
    public void testVerifyClearCompleted() throws InterruptedException
    {
    	ExtentTest test = extent.createTest("VerifyClearCompleted");
    	
         ArrayList<String> items = new ArrayList<String>();
         items.add("Todo1");
         items.add("Todo2");
         WebElement checkbox;
         WebElement inputBox;
         WebElement todoItem;
         WebElement completedTodo;
         
         for(int i=0;i<items.size();i++)
         {       	 
        
         // Add a new todo
         inputBox = driver.findElement(By.cssSelector("input.new-todo"));         
         inputBox.sendKeys(items.get(i) + "\n");

         Thread.sleep(5000);
         // Verify the todo is added
         driver.findElement(By.linkText("All")).click();
         Thread.sleep(5000);
         todoItem = driver.findElement(By.xpath("//label[text()='" + items.get(i) + "']"));
         Assert.assertTrue(todoItem.isDisplayed(), "Todo item was added successfully!");        
         screenshotPath = ScreenshotUtil.takeScreenshot(driver, "testVerifyClearCompleted_AddItem"+i);     
         test.pass("Added Todo item "+ items.get(i)).addScreenCaptureFromPath(screenshotPath); 
    	 checkbox = driver.findElement(By.xpath("//label[text()='" + items.get(i) + "']/preceding-sibling::input"));
         checkbox.click();
         screenshotPath = ScreenshotUtil.takeScreenshot(driver, "testVerifyClearCompleted_Complete"+i);     
         test.pass("Completed Todo item "+ items.get(i)).addScreenCaptureFromPath(screenshotPath); 
         
         Thread.sleep(2000);
        // Verify the todo is marked as completed
         completedTodo = driver.findElement(By.cssSelector("li.completed"));
         Assert.assertTrue(completedTodo.isDisplayed(), "Todo was marked as completed!");
         screenshotPath = ScreenshotUtil.takeScreenshot(driver, "testVerifyClearCompleted_VerifyComplete"+i);     
         test.pass("Todo was not marked as completed!").addScreenCaptureFromPath(screenshotPath);         
        }        
        
        driver.findElement(By.linkText("Completed")).click();
        WebElement clearCompleted = driver.findElement(By.xpath("//button[text() ='Clear completed']"));
        clearCompleted.click();     
        screenshotPath = ScreenshotUtil.takeScreenshot(driver, "testVerifyClearCompleted_ClearCompleted");  
        test.pass("Clicked on ClearCompletedButton!").addScreenCaptureFromPath(screenshotPath); 
        int completedCount = driver.findElements(By.cssSelector("ul.todo-list li")).size();
        Assert.assertEquals(completedCount, 0, "Completed todos filtering Passed!");
        screenshotPath = ScreenshotUtil.takeScreenshot(driver, "testVerifyClearCompleted_CompleteList");  
        test.pass("Verified that there are no completed Todo items!").addScreenCaptureFromPath(screenshotPath); 
        Thread.sleep(5000);
    	
    }
    @Test (priority = 7)
    public void testFilterTodos() throws InterruptedException {
    	ExtentTest test = extent.createTest("FilterTodos");
    	WebElement inputBox;
    	String testval1 = "Todo Test1";
    	String testval2 = "Todo Test2";
    	int count =0;
        // Add an active todo
        inputBox = driver.findElement(By.className("new-todo"));
        inputBox.sendKeys(testval1);
        inputBox.sendKeys(Keys.ENTER);
        Thread.sleep(2000);
        inputBox.sendKeys(testval2);
        inputBox.sendKeys(Keys.ENTER);
        Thread.sleep(2000);
        //Verify filtering
        driver.findElement(By.linkText("All")).click();
        count = driver.findElements(By.cssSelector("ul.todo-list li")).size();
        Assert.assertEquals(count, 2, "All todos filtering Passed!");
        screenshotPath = ScreenshotUtil.takeScreenshot(driver, "testFilterTodos_VerifyAllFilter");  
        test.pass("All Filtering passed successfully!").addScreenCaptureFromPath(screenshotPath);          
        Thread.sleep(2000);
        driver.findElement(By.linkText("Active")).click();
        count = driver.findElements(By.cssSelector("ul.todo-list li")).size();
        Assert.assertEquals(count, 2, "Active todos filtering Passed!");
        screenshotPath = ScreenshotUtil.takeScreenshot(driver, "testFilterTodos_VerifyActiveFilter");  
        test.pass("Active Filtering passed successfully!").addScreenCaptureFromPath(screenshotPath);          
        Thread.sleep(2000);
        driver.findElement(By.linkText("Completed")).click();
        count = driver.findElements(By.cssSelector("ul.todo-list li")).size();
        Assert.assertEquals(count, 0, "Completed todos filtering Passed!");
        screenshotPath = ScreenshotUtil.takeScreenshot(driver, "testFilterTodos_VerifyCompleteFilter");  
        test.pass("Completed Filtering passed successfully!").addScreenCaptureFromPath(screenshotPath);             
        Thread.sleep(2000);
        driver.findElement(By.linkText("All")).click();
        Thread.sleep(2000);
        //Complete a Todo
        WebElement checkbox = driver.findElement(By.xpath("//label[text()='" + testval1 + "']/preceding-sibling::input"));
        checkbox.click();
        Thread.sleep(2000);
        //Verify filtering
        driver.findElement(By.linkText("All")).click();
        count = driver.findElements(By.cssSelector("ul.todo-list li")).size();
        Assert.assertEquals(count, 2, "All todos filtering Passed!");
        screenshotPath = ScreenshotUtil.takeScreenshot(driver, "testFilterTodos_VerifyAllFilter_Complete");  
        test.pass("All Filtering passed successfully after completing a todo!").addScreenCaptureFromPath(screenshotPath);          
        Thread.sleep(1000);
        
        driver.findElement(By.linkText("Active")).click();
        count = driver.findElements(By.cssSelector("ul.todo-list li")).size();
        Assert.assertEquals(count, 1, "Active todos filtering Passed!");
        screenshotPath = ScreenshotUtil.takeScreenshot(driver, "testFilterTodos_VerifyActiveFilter_Complete");  
        test.pass("Active Filtering passed successfully after completing a todo!").addScreenCaptureFromPath(screenshotPath);          
        Thread.sleep(2000);
        
        driver.findElement(By.linkText("Completed")).click();
        count = driver.findElements(By.cssSelector("ul.todo-list li")).size();
        Assert.assertEquals(count, 1, "Completed todos filtering Passed!");
        screenshotPath = ScreenshotUtil.takeScreenshot(driver, "testFilterTodos_VerifyCompletedFilter_Complete");  
        test.pass("Completed Filtering passed successfully after completing a todo!").addScreenCaptureFromPath(screenshotPath);          
       
        Thread.sleep(2000);
        
        test.pass("Completed todos filtering!");
        Thread.sleep(5000);
      
    }
    
   
}
		