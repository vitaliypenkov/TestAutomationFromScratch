package setup.webdriver.testng;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

/**
 * Created by vitaliyp on 3/17/2016.
 */
public class SimpleTest {
    FirefoxDriver driver;

    @BeforeTest
    public void setup(){
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }
    
    @Test
    public void googleSearch() {
        // arrange
        driver.get("https://www.google.com/");
        
        // act
        driver.findElement(By.name("q")).sendKeys("Github");
        driver.findElement(By.name("btnG")).click();
        
        // assert
        String result = driver.findElement(By.xpath(".//*[@class='rc']//a")).getText();
        Assert.assertEquals(result, "How people build software · GitHub");
    }

    @AfterTest
    public void tearDown() {
        driver.close();
    }
}
