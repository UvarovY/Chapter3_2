package pact.selenium.chap3_2;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.concurrent.TimeUnit;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class Ex1Test {
    private WebDriver driver;
    private String baseUrl;
    private boolean acceptNextAlert = true;
    private StringBuffer verificationErrors = new StringBuffer();

    @Before
    public void setUp() throws Exception {
        //System.setProperty("webdriver.gecko.driver", "C:\\Users\\Arni\\geckodriver-v0.17.0-win64\\geckodriver.exe");
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Arni\\WebDrivers\\chromedriver_win32\\chromedriver.exe");
        driver = new ChromeDriver();
        baseUrl = "http://docs.seleniumhq.org";
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Test
    public void testExample1() throws Exception {
        driver.get(baseUrl + "/");
        driver.findElement(By.linkText("Download")).click();
        driver.findElement(By.linkText("Release Notes")).click();
        assertEquals("SeIDE Release Notes · SeleniumHQ/selenium Wiki · GitHub1245654654", driver.getTitle());
    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            fail(verificationErrorString);
        }
    }

    private boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    private boolean isAlertPresent() {
        try {
            driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

    private String closeAlertAndGetItsText() {
        try {
            Alert alert = driver.switchTo().alert();
            String alertText = alert.getText();
            if (acceptNextAlert) {
                alert.accept();
            } else {
                alert.dismiss();
            }
            return alertText;
        } finally {
            acceptNextAlert = true;
        }
    }
}
