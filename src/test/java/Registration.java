import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Registration {

    @FindBy(xpath = "//input[@id='user_email']")
    private WebElement emailInput;

    @FindBy(xpath = "//input[@id='user_name']")
    private WebElement nameInput;

    @FindBy(xpath = "//input[@id='user_password']")
    private WebElement passwordInput;

    @FindBy(xpath = "//input[@name='user[password_confirmation]']")
    private WebElement passwordConfirmationInput;

    @FindBy(id = "user_terms")
    private WebElement policyPrivacyCheckbox;

    @FindBy(id = "user_newsletter")
    private WebElement userNewsletterCheckbox;

    @FindBy(xpath = "//input[@value='Zarejestruj się']")
    private WebElement submitButton;

    //odniesienie do strony testowanej
    private WebDriver webDriver;

    public Registration(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    public void registrationWithConfirmation(User user) {
        emailInput.sendKeys(user.getEmil());
       // nameInput.sendKeys(user.getName());
        passwordInput.sendKeys(user.getPassword());
       // passwordConfirmationInput.sendKeys(user.getPasswordConfirmation());
        policyPrivacyCheckbox.click();
        userNewsletterCheckbox.click();
        submitButton.click();


    }
}
