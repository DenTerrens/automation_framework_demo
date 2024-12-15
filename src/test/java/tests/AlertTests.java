package tests;

import core.WebDriverFactory;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

public class AlertTests {
    WebDriver driver;

    @BeforeClass
    @Parameters("browser")
    public void setup(@Optional("chrome") String browser) {
        driver = WebDriverFactory.createDriver(browser);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://the-internet.herokuapp.com/javascript_alerts");
    }

    @Test
    public void testJsAlert() {
        // Click the button to trigger the alert
        driver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();

        // Switch to alert and accept
        Alert alert = driver.switchTo().alert();
        Assert.assertEquals(alert.getText(), "I am a JS Alert");
        alert.accept();

        // Validate the result
        String resultText = driver.findElement(By.id("result")).getText();
        Assert.assertEquals(resultText, "You successfully clicked an alert");
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}