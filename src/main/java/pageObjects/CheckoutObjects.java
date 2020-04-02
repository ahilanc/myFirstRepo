package pageObjects;

import Helpers.WebDriverHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CheckoutObjects {
    WebDriver driver;

    public CheckoutObjects(WebDriver driver) {
        PageFactory.initElements(driver,this);
    }

    @FindBy(how = How.XPATH, using = "//iframe[@class='fancybox-iframe']")
    public WebElement iframeWebPage;

    @FindBy(how = How.XPATH, using = "//button[@name='Submit']")
    public WebElement addCartButton;

    @FindBy(how = How.XPATH, using = "//select[@id='group_1']")
    public WebElement selectSize;

    @FindBy(how = How.XPATH, using = "//div[@id='layer_cart']//h2[contains(.,'Product successfully added')]")
    public WebElement productTextMessage;

    @FindBy(how = How.XPATH, using = "//div[@class='button-container']/a")
    public WebElement proceedToCheckOutButton;

    @FindBy(how = How.XPATH, using = "//li[@class='step_current  first']")
    public WebElement labelFirstCurrentStep;

    @FindBy(how = How.XPATH, using = "//textarea[@name='message']")
    public WebElement textAreaMessageAddress;

    @FindBy(how = How.XPATH, using = "//*[@class='cart_navigation clearfix']/a[1]")
    public WebElement proceedToCheckOutButtonInStep1;

    @FindBy(how = How.XPATH, using = "//button[@name='processAddress']")
    public WebElement processAddressButton;

    @FindBy(how = How.XPATH, using = "//li[@class='step_current four']")
    public WebElement labelCurrentFourthStep;

    @FindBy(how = How.XPATH, using = "//input[@id='cgv']")
    public WebElement clickTermsAndConditions;

    @FindBy(how = How.XPATH, using = "//*[@name='processCarrier']")
    public WebElement processCarrierButton;

    @FindBy(how = How.XPATH, using = "//li[@class='step_current last']")
    public WebElement labelCurrentLastStep;

    @FindBy(how = How.XPATH, using = "//p/a[@class='cheque']")
    public WebElement selectPaymentByCheck;


}
