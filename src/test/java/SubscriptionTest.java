import com.github.javafaker.Faker;
import com.makersacademy.nevermore.Application;

import java.io.File;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class SubscriptionTest {

    WebDriver driver;
    Faker faker;

    @Before
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "/opt/homebrew/bin/chromedriver");
        driver = new ChromeDriver();
        faker = new Faker();
    }

    @After
    public void tearDown() {
        driver.close();
    }

    @Test
    public void successfulLoginRedirectsToDashboard() {
        driver.get("http://localhost:8080/");
        //Directed to login page
        driver.findElement(By.id("login")).click();
        //Sign in and get directed to dashboard
        driver.findElement(By.id("username")).sendKeys("danny");
        driver.findElement(By.id("password")).sendKeys("123456");
        driver.findElement(By.id("submit")).click();
        //Redirect to new subsctiption page
        driver.findElement(By.id("new-subscription-button")).click();
        //Check the page is on the new subscription page
        String title = driver.getTitle();
        Assert.assertEquals("New Cost", title);
        driver.findElement(By.id("content")).sendKeys("New Content");
        driver.findElement(By.id("price")).sendKeys("7.99");
        driver.findElement(By.id("submit")).click();
        
        //Test to see if the dashboard is the current branch
        String title2 = driver.getTitle();
        Assert.assertEquals("Dashboard", title2);


    }
}