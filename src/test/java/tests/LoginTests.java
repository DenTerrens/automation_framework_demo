package tests;

import core.WebDriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class LoginTests {
    WebDriver driver;

    @BeforeClass
    @Parameters("browser")
    public void setup(@Optional("chrome") String browser) {
        driver = WebDriverFactory.createDriver(browser);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://the-internet.herokuapp.com/login");
    }

    @Test(dataProvider = "loginData")
    public void loginTest(String username, String password, String expectedMessage) {
        // Enter username
        driver.findElement(By.id("username")).sendKeys(username);
        // Enter password
        driver.findElement(By.id("password")).sendKeys(password);
        // Click Login
        driver.findElement(By.cssSelector("button[type='submit']")).click();

        // Validate result
        String actualMessage = driver.findElement(By.id("flash")).getText();
        Assert.assertTrue(actualMessage.contains(expectedMessage));
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }

    @DataProvider(name = "loginData")
    public Object[][] getLoginData() throws IOException {
        List<Object[]> testData = new ArrayList<>();
        String path = "src/test/data/loginData.csv";
        String line;
        BufferedReader br = new BufferedReader(new FileReader(path));

        while ((line = br.readLine()) != null) {
            String[] data = line.split(",");
            testData.add(data);
        }

        return testData.toArray(new Object[0][]);
    }
}

