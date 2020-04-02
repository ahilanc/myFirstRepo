package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ConfirmationOrderObjects {
    WebDriver driver;

    public ConfirmationOrderObjects(WebDriver driver) {
        PageFactory.initElements(driver,this);
    }

    @FindBy(how = How.XPATH, using = "//button[contains(.,'I confirm my order')]")
    public WebElement confirmOrderButton;

    @FindBy(how = How.XPATH, using = "//p[@class='alert alert-success']")
    public WebElement successTextMessage;

    @FindBy(how = How.XPATH, using = "//*[@class='box order-confirmation'][contains(.,'Do not forget to include your order reference')]")
    public WebElement orderConfirmationDetails;

    @FindBy(how = How.XPATH, using = "//a[contains(.,'Back to orders')]")
    public WebElement backToOrdersButton;

    @FindAll({
            @FindBy(how = How.XPATH, using = "//table/tbody/tr/td[1]")
    })
    public List<WebElement> tableOrderHistory;

}
