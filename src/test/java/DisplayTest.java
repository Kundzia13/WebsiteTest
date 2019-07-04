import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertNotNull;

public class DisplayTest {
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
    public void testSearch() {
        webDriver.navigate().to("https://nakanapie.pl/konto/logowanie");
        WebElement inputLogin = webDriver.findElement(By.cssSelector("#user_login"));
        inputLogin.sendKeys("WebTest");
        WebElement inputPassword = webDriver.findElement(By.cssSelector("#user_password"));
        inputPassword.sendKeys("Alamakota");
        WebElement clikSubmit = webDriver.findElement(By.xpath("//input[@value='Zaloguj się']"));
        clikSubmit.click();
        webDriver.navigate().to("https://nakanapie.pl/szukaj/ksiazki?q=das");
        WebElement buttonBook = webDriver.findElement(By.xpath("/html[1]/body[1]/div[3]/div[1]/form[1]/div[1]/div[2]/button[1]"));
        buttonBook.click();
        WebElement headText = webDriver.findElement(By.xpath("//strong[contains(text(),'das')]"));
        assertNotNull(headText);
    }
}
