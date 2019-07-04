import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

public class AddingReviewTest {
    private WebDriver webDriver;
    /*
    Elementy wymagane do dodania recenzji
    * wybór ksiązki
    * tytuł
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
        webDriver.navigate().to("https://nakanapie.pl/recenzje/dodaj");
        WebElement buttonSubmit = webDriver.findElement(By.xpath("//button[@class='btn btn-primary']"));
        buttonSubmit.click();
        assertTrue(webDriver.getCurrentUrl().contains("recenzje/dodaj"));
    }
    @Test
    public void addBookWithCorrectInformation() throws InterruptedException {
        webDriver.navigate().to("https://nakanapie.pl/recenzje/dodaj");
        WebElement buttonChoice = webDriver.findElement(By.xpath("//button[@class='btn btn-sm btn-primary']"));
        buttonChoice.click();
        Thread.sleep(500);
        WebElement inputSearch = webDriver.findElement(By.xpath("//input[@placeholder='Wpisz szukaną frazę']"));
        inputSearch.sendKeys("Władca Pierścieni");
        Thread.sleep(500);
        WebElement buttonChoiceBook = webDriver.findElement(By.xpath("//div//div[3]//div[3]"));
        buttonChoiceBook.click();
        Thread.sleep(500);
        WebElement inputTitle = webDriver.findElement(By.xpath("//input[@name='title']"));
        inputTitle.sendKeys("Władca Pierścieni");
        Thread.sleep(500);
        WebElement inputText = webDriver.findElement(By.xpath("//trix-editor[@class='trix-content']"));
        inputText.sendKeys("Bardzo wciągająca fabuła. Polecam.");
        WebElement buttonSubmit = webDriver.findElement(By.xpath("//button[@class='btn btn-primary']"));
        buttonSubmit.click();
        Thread.sleep(500);
        assertTrue(webDriver.getCurrentUrl().contains("recenzje"));
        WebElement text = webDriver.findElement(By.xpath("/html[1]/body[1]/div[3]/div[1]/div[1]/h2[1]"));
        assertNotNull(text);
    }
}
