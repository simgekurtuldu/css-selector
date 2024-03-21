package tests;

import org.example.drivers.Driver;
import org.example.utils.PropertyManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.time.Duration;
import java.util.List;

public class DemoqaWebtablesTest {
    public static WebDriver driver;
    Driver webDriver = new Driver();

    @BeforeSuite
    public void before() throws MalformedURLException {
        driver = webDriver.initializeDriver();
        driver.get("https://demoqa.com/webtables");
    }
    @Test(priority = 1)
    public void clickAddButton(){
        WebElement addButton = driver.findElement(By.cssSelector("#addNewRecordButton"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",addButton);
        addButton.click();
        WebElement formModal = driver.findElement(By.cssSelector(".modal-title#registration-form-modal"));
        Assert.assertEquals(formModal.getText(),"Registration Form");
    }
    @Test(priority = 2)
    public void addNewSubmit(){
        WebElement firstName = driver.findElement(By.cssSelector("input#firstName"));
        WebElement lastName = driver.findElement(By.cssSelector("input#lastName"));
        WebElement userEmail = driver.findElement(By.cssSelector("input#userEmail"));
        WebElement age = driver.findElement(By.cssSelector("input#age"));
        WebElement salary = driver.findElement(By.cssSelector("input#salary"));
        WebElement department = driver.findElement(By.cssSelector("input#department"));
        firstName.sendKeys("Simge");
        lastName.sendKeys("Kurtuldu");
        userEmail.sendKeys("simge@gmail.com");
        age.sendKeys("26");
        salary.sendKeys("10000");
        department.sendKeys("Test");
        WebElement submitButton = driver.findElement(By.cssSelector("button#submit"));
        submitButton.click();
        WebElement addButton = driver.findElement(By.cssSelector("#addNewRecordButton"));
        Assert.assertTrue(addButton.isDisplayed());
    }
    @Test(priority = 3)
    public void EditSubmit(){
        WebElement editButton = driver.findElement(By.cssSelector("span#edit-record-4"));
        editButton.click();
        WebElement firstText = driver.findElement(By.cssSelector("input#firstName"));
        firstText.clear();
        firstText.sendKeys("Ahmet");
        driver.findElement(By.cssSelector("button#submit")).click();
        List<WebElement> elements = driver.findElements(By.cssSelector("div.rt-tr-group"));
        String[] lines = elements.get(3).getText().split("\\n");
        String firstLine = lines[0];
        System.out.println(firstLine);
        Assert.assertEquals(firstLine,"Ahmet");
    }
    @AfterSuite
    public void after() throws MalformedURLException{
        driver.quit();
    }
}
