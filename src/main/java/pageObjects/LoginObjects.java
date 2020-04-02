package pageObjects;

import Helpers.FileReaderHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class LoginObjects {
    WebDriver driver;

    public LoginObjects(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(how = How.XPATH, using = "//div/a[@class='login']")
    public WebElement tabSignIn;

    @FindBy(how = How.XPATH, using = "//div[@class='form_content clearfix']//input[@id='email']")
    public WebElement inputEmailAddress;

    @FindBy(how = How.XPATH, using = "//input[@id='passwd']")
    public WebElement inputPassword;

    @FindBy(how = How.XPATH, using = "//button[@id='SubmitLogin']")
    public WebElement submitLoginButton;

    @FindBy(how = How.XPATH, using = "//p[@class='info-account']")
    public WebElement labelHomePage;

}
