package Helpers;

import org.openqa.selenium.WebDriver;
import pageObjects.AccountObjects;
import pageObjects.CheckoutObjects;
import pageObjects.ConfirmationOrderObjects;
import pageObjects.LoginObjects;

public class PageObjectHelper {

    private WebDriver driver;
    private LoginObjects loginObjects;
    private AccountObjects accountObjects;
    private CheckoutObjects checkoutObjects;
    private ConfirmationOrderObjects confirmationOrderObjects;

    public PageObjectHelper(WebDriver driver) {
        this.driver = driver;
    }

    public LoginObjects getLoginObjects() {
        return (loginObjects == null) ? loginObjects = new LoginObjects(driver) : loginObjects;
    }

    public AccountObjects getAccountObjects() {
        return (accountObjects == null) ? accountObjects = new AccountObjects(driver) : accountObjects;
    }

    public CheckoutObjects getCheckoutObjects() {
        return (checkoutObjects == null) ? checkoutObjects = new CheckoutObjects(driver) : checkoutObjects;
    }

    public ConfirmationOrderObjects getConfirmationOrderObjects() {
        return (confirmationOrderObjects == null) ? confirmationOrderObjects = new ConfirmationOrderObjects(driver) : confirmationOrderObjects;
    }



}
