package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class AccountObjects {
    WebDriver driver;

    public AccountObjects(WebDriver driver) {
        PageFactory.initElements(driver,this);
    }

    @FindBy(how = How.XPATH, using = "//div[@id='block_top_menu']/ul/li/a[contains(.,'T-shirts')]")
    public WebElement tabTShirtsMenu;

    @FindBy(how = How.XPATH, using = "//div[@class='product-image-container']")
    public WebElement clickProductItem;

    @FindBy(how = How.XPATH, using = "//a[contains(.,'My personal information')]")
    public WebElement updatePersonalInfoButton;

    @FindBy(how = How.XPATH, using = "//input[@id='firstname']")
    public WebElement inputFirstName;

    @FindBy(how = How.XPATH, using = "//input[@id='old_passwd']")
    public WebElement inputOldPassword;

    @FindBy(how = How.XPATH, using = "//button[contains(.,'Save')]")
    public WebElement saveButton;

    @FindBy(how = How.XPATH, using = "//p[@class='alert alert-success']")
    public WebElement labelSuccessTextMessage;


}
