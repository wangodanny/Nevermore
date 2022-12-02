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
public class CostTest {

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
    public void successfulSignUpRedirectsToSignIn() {
        driver.get("http://localhost:8080/login");
        //signs user in
        driver.findElement(By.id("username")).sendKeys("danny");
        driver.findElement(By.id("password")).sendKeys("123456");
        driver.findElement(By.id("submit")).click();
        //redirected to dashboard
        String title1 = driver.getTitle();
        Assert.assertEquals("Dashboard", title1);
        //we want to go to the add cost page now
        driver.findElement(By.id("newCost")).click();

        String title2 = driver.getTitle();
        Assert.assertEquals("New Cost", title2);
        //add attributes to the cost
        driver.findElement(By.id("content")).sendKeys("New Sub");
        driver.findElement(By.id("price")).sendKeys("15.99");
        driver.findElement(By.id("submit")).click();

        //Redirect the user to the dashboard

        String title3 = driver.getTitle();
        Assert.assertEquals("Dashboard", title3);


    }
}
