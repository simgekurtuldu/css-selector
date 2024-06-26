package tests;

import org.example.drivers.Driver;
import org.example.utils.PropertyManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.util.List;

public class DemoqaTests {

    public static WebDriver driver;
    Driver webDriver = new Driver();
    PropertyManager propertyManager = new PropertyManager();
    String url = propertyManager.getProperty("APP_URL");
    @BeforeSuite
    public void before() throws MalformedURLException{
        driver = webDriver.initializeDriver();
        driver.get("https://demoqa.com/elements");
    }
    @Test(priority = 1)
    public void openDemoqaPageTest(){
        String pageTitle = driver.getTitle();
        Assert.assertEquals(pageTitle,"DEMOQA");
    }
    @Test(priority = 2)
    public void clickButtonsButton(){
        WebElement button = driver.findElement(By.cssSelector("li.btn#item-4"));
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", button);
        WebElement buttonName = button.findElement(By.tagName("span"));
        Assert.assertEquals(buttonName.getText(),"Buttons");
    }
    @Test(priority = 3)
    public void clickClickMeButton(){
        List<WebElement> elements = driver.findElements(By.cssSelector("button.btn-primary"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", elements.get(2));
        elements.get(2).click();
        WebElement message = driver.findElement(By.cssSelector("p#dynamicClickMessage"));
        Assert.assertEquals(message.getText(), "You have done a dynamic click");
    }
    @AfterSuite
    public void after() throws MalformedURLException{
        driver.quit();
    }

}
