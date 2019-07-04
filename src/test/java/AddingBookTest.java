import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class AddingBookTest {
    private WebDriver webDriver;
    /*
    Elementy wymagane do dodania nowej książki
    * Image
    * ISBN
    * Tytuł
    * Wydawnictwo
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
        webDriver.navigate().to("https://nakanapie.pl/ksiazka/dodaj");
        WebElement buttonSubmit = webDriver.findElement(By.xpath("//button[@type='submit']"));
        buttonSubmit.click();
        assertTrue(webDriver.getCurrentUrl().contains("ksiazka/dodaj"));
    }

    @Test
    public void addBookWithCorrectInformation() throws InterruptedException {
        webDriver.navigate().to("https://nakanapie.pl/ksiazka/dodaj");
        WebElement inputImage = webDriver.findElement(By.xpath("//body[@class='books new d-flex flex-column']/div[@class='container']/div[@class='box']/div/form[@class='row']/div[@class='form-group col-12']/div/div/input[1]"));
        inputImage.sendKeys("C:\\Users\\Kundzia\\Desktop\\CienWiatru.jpg");
        WebElement inputIsbn = webDriver.findElement(By.xpath("//input[@name='isbn']"));
        inputIsbn.sendKeys("9788328708869");
        WebElement inputTitle = webDriver.findElement(By.xpath("//input[@name='title']"));
        inputTitle.sendKeys("Cień wiatru");
        WebElement inputPublisher = webDriver.findElement(By.className("css-nbc25y"));
        inputPublisher.sendKeys("MUZA SA");
        inputPublisher.submit();
        WebElement buttonSubmit = webDriver.findElement(By.xpath("//button[@class='btn btn-primary']"));
        buttonSubmit.click();
        assertTrue(webDriver.getCurrentUrl().contains("ksiazka/dodaj"));

    }

}
