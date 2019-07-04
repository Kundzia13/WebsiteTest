import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class AddingAuthorTest {
    private WebDriver webDriver;
    /*
    Elementy wymagane do dodania autora
    *Imię
    *Nazwisko
     */

    @BeforeMethod
    public void beforeTest() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Kundzia\\Desktop\\Kurs\\Tester automatyczny\\chromedriver.exe");
        webDriver = new ChromeDriver();
        webDriver.navigate().to("https://nakanapie.pl/konto/logowanie");
        WebElement inputLogin = webDriver.findElement(By.cssSelector("#user_login"));
        inputLogin.sendKeys("WebTest");
        WebElement inputPassword = webDriver.findElement(By.cssSelector("#user_password"));
        inputPassword.sendKeys("Alamakota");
        WebElement clikSubmit = webDriver.findElement(By.xpath("//input[@value='Zaloguj się']"));
        clikSubmit.click();
    }

    @AfterMethod
    public void afterTest() {
        webDriver.close();
        webDriver.quit();
    }

    @Test
    public void addBookWithNoInformation() {
        webDriver.navigate().to("https://nakanapie.pl/autorzy/dodaj");
        WebElement buttonSubmit = webDriver.findElement(By.xpath("//button[@class='btn btn-primary']"));
        buttonSubmit.click();
        assertTrue(webDriver.getCurrentUrl().contains("autorzy/dodaj"));
    }

    @Test
    public void addBookWithCorrectInformation() throws InterruptedException {
        webDriver.navigate().to("https://nakanapie.pl/autorzy/dodaj");
        WebElement inputName = webDriver.findElement(By.xpath("//input[@name='first_name']"));
        inputName.sendKeys("John Ronald Reuel");
        WebElement inputSurname = webDriver.findElement(By.xpath("//input[@name='last_name']"));
        inputSurname.sendKeys("Tolkien");
        WebElement buttonSubmit = webDriver.findElement(By.xpath("//button[@class='btn btn-primary']"));
        buttonSubmit.click();
        Thread.sleep(500);
        WebElement author = webDriver.findElement(By.xpath("//h1[@class='h hb mb-0']"));
        assertTrue(author.getText().contains("John Ronald Reuel Tolkien"));
    }
}
