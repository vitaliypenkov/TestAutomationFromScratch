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

    @Test
    public void googleSearch() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://www.google.com.ua/");
        driver.findElement(By.name("q")).sendKeys("Github");
        driver.findElement(By.name("btnG")).click();
        String result = driver.findElement(By.xpath(".//*[@class='rc']//a")).getText();

        Assert.assertEquals(result, "How people build software · GitHub");
    }

    @AfterTest
    public void tearDown() {
        driver.close();
    }
}
