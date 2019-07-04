import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Random;

import static org.testng.Assert.assertTrue;
import static org.testng.AssertJUnit.assertFalse;

public class RegistrationTest {
    private WebDriver webDriver;
    private Registration registration;

    @BeforeMethod
    public void beforeTest() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Kundzia\\Desktop\\Kurs\\Tester automatyczny\\chromedriver.exe");
        webDriver = new ChromeDriver();
        registration = new Registration(webDriver);

    }

    @AfterMethod
    public void afterTest() {
        webDriver.close();
        webDriver.quit();
    }

    @Test
    public void registrationCorrectData() {
        webDriver.navigate().to("https://nakanapie.pl/konto/rejestracja");
        Random random = new Random();
        int number = random.nextInt();
        User user = new User("ola" + number + "@gmail.com", "Alamakota");
        registration.registrationWithConfirmation(user);
        assertTrue(webDriver.getCurrentUrl().contains("po-rejestracji"));
    }

    @Test
    public void registrationIncorrectEmail() throws InterruptedException {
        webDriver.navigate().to("https://nakanapie.pl/konto/rejestracja");
        Random random = new Random();
        int number = random.nextInt();
        User user = new User("ola" + number + "@gmailcom",
                "Alamakota");
        registration.registrationWithConfirmation(user);
        Thread.sleep(500);
        assertFalse(webDriver.getCurrentUrl().contains("po-rejestracji"));

    }
}
