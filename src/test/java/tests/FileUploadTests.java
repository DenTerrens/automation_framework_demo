package tests;

import core.WebDriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

public class FileUploadTests {
    WebDriver driver;

    @BeforeClass
    @Parameters("browser")
    public void setup(@Optional("chrome") String browser) {
        driver = WebDriverFactory.createDriver(browser);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://the-internet.herokuapp.com/upload");
    }

    @Test
    public void testFileUpload() {
        // Upload a file
        WebElement fileInput = driver.findElement(By.id("file-upload"));
        fileInput.sendKeys("C:\\Users\\dente\\Downloads\\simple_excel_file_to_upload.xlsx");

        driver.findElement(By.id("file-submit")).click();

        // Verify file upload
        WebElement uploadedMessage = driver.findElement(By.id("uploaded-files"));
        Assert.assertEquals(uploadedMessage.getText(), "simple_excel_file_to_upload.xlsx");
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}