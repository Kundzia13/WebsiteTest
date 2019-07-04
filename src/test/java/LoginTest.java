import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;


public class LoginTest {
    private WebDriver webDriver;

    @BeforeMethod
    public void beforeTest() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Kundzia\\Desktop\\Kurs\\Tester automatyczny\\chromedriver.exe");
        webDriver = new ChromeDriver();
    }

    @AfterMethod
    public void afterTest() {
        webDriver.close();
        webDriver.quit();
    }

    @Test
    public void testLogIn() {
        webDriver.navigate().to("https://nakanapie.pl/konto/logowanie");
        WebElement inputLogin = webDriver.findElement(By.cssSelector("#user_login"));
        inputLogin.sendKeys("WebTest");
        WebElement inputPassword = webDriver.findElement(By.cssSelector("#user_password"));
        inputPassword.sendKeys("Alamakota");
        WebElement inputClikSubmit = webDriver.findElement(By.xpath("//input[@value='Zaloguj się']"));
        inputClikSubmit.click();
        assertTrue(webDriver.getCurrentUrl().contains("pulpit"));

    }

    @Test
    public void testIncorectPassword() throws InterruptedException {
        webDriver.navigate().to("https://nakanapie.pl/konto/logowanie");
        WebElement inputLogin = webDriver.findElement(By.cssSelector("#user_login"));
        inputLogin.sendKeys("WebTest");
        WebElement inputPassword = webDriver.findElement(By.cssSelector("#user_password"));
        inputPassword.sendKeys("AlamakotaA");
        WebElement inputClikSubmit = webDriver.findElement(By.xpath("//input[@value='Zaloguj się']"));
        inputClikSubmit.click();
        Thread.sleep(500);
        assertTrue(webDriver.getCurrentUrl().contains("logowanie"));
    }
}
