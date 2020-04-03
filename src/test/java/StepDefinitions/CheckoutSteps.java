package StepDefinitions;

import cucumber.TestContainer;
import io.cucumber.java8.En;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import pageObjects.CheckoutObjects;

import java.awt.*;
import java.awt.event.KeyEvent;
import static library.utilLibrary.*;
import static testSupport.WebDriverUtil.attachScreenshot;
import static testSupport.WebDriverUtil.scrollDownScreenshot;

public class CheckoutSteps implements En {
    TestContainer testContext;
    CheckoutObjects checkoutObjects;
    WebDriver driver;

    public CheckoutSteps(TestContainer context) {
        testContext = context;
        checkoutObjects = testContext.getPageObjectManager().getCheckoutObjects();

        And("^I proceed to checkout from Cart$", () -> {
            try {
                waitFor(7000);
                driver = getLatestDriver();
                driver.switchTo().frame(0);
                fluentWait(checkoutObjects.addCartButton);
                checkoutObjects.addCartButton.click();
                waitFor(4000);
                Assert.assertTrue(checkoutObjects.productTextMessage.isDisplayed());
                attachScreenshot();
                checkoutObjects.proceedToCheckOutButton.click();
                driver.switchTo().defaultContent();
            } catch (Exception e) {
                Assert.fail("Proceed to checkout failed due to '"+e.getMessage()+"'");
            }
        });

        And("^I check personal details on checkout page$", () -> {
            try {
                fluentWait(checkoutObjects.labelFirstCurrentStep);
                Assert.assertTrue(checkoutObjects.labelFirstCurrentStep.isDisplayed());
                attachScreenshot();
                checkoutObjects.labelFirstCurrentStep.click();
                Robot robot = new Robot();
                robot.keyPress(KeyEvent.VK_PAGE_DOWN);
                robot.keyRelease(KeyEvent.VK_PAGE_DOWN);

                waitFor(3000);
                fluentWait(checkoutObjects.proceedToCheckOutButtonInStep1);
                attachScreenshot();
                checkoutObjects.proceedToCheckOutButtonInStep1.click();

            } catch (Exception e) {
                Assert.fail("Check personal details failed due to '"+e.getMessage()+"'");
            }
        });

        And("^I select my same delivery address and tick the terms and conditions$", () -> {
            try {
                fluentWait(checkoutObjects.textAreaMessageAddress);
                Assert.assertTrue(checkoutObjects.textAreaMessageAddress.isDisplayed());

                checkoutObjects.textAreaMessageAddress.click();
                attachScreenshot();
                Robot robot = new Robot();
                robot.keyPress(KeyEvent.VK_PAGE_DOWN);
                robot.keyRelease(KeyEvent.VK_PAGE_DOWN);

                waitFor(3000);
                fluentWait(checkoutObjects.processAddressButton);
                checkoutObjects.processAddressButton.click();

                fluentWait(checkoutObjects.labelCurrentFourthStep);
                checkoutObjects.clickTermsAndConditions.click();
                attachScreenshot();
                checkoutObjects.processCarrierButton.click();

            }catch (Exception e) {
                Assert.fail("Delivery address failed due to '"+e.getMessage()+"'");
            }
        });

        And("^Select the payment method as 'Pay by check'$", () -> {
            try {
                fluentWait(checkoutObjects.selectPaymentByCheck);
                attachScreenshot();
                checkoutObjects.labelCurrentLastStep.click();
                scrollDownScreenshot(checkoutObjects.selectPaymentByCheck);
                checkoutObjects.selectPaymentByCheck.click();

            } catch (Exception e) {
                Assert.fail("Payment by check failed due to '"+e.getMessage()+"'");
            }
        });

    }
}
