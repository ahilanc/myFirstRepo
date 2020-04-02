package StepDefinitions;

import cucumber.TestContainer;
import io.cucumber.java8.En;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import pageObjects.ConfirmationOrderObjects;

import java.awt.*;
import java.awt.event.KeyEvent;

import static library.utilLibrary.*;
import static testSupport.WebDriverUtil.attachScreenshot;
import static testSupport.WebDriverUtil.scrollDownScreenshot;

public class ConfirmationOrderSteps implements En {
    TestContainer testContext;
    ConfirmationOrderObjects confirmationOrderObjects;

    public ConfirmationOrderSteps(TestContainer context) {
        testContext = context;
        confirmationOrderObjects = testContext.getPageObjectManager().getConfirmationOrderObjects();

        When("^I click on 'I confirm my order' button$", () -> {
            try {
                Assert.assertTrue(confirmationOrderObjects.confirmOrderButton.isDisplayed());
                scrollDownScreenshot(confirmationOrderObjects.confirmOrderButton);
                confirmationOrderObjects.confirmOrderButton.click();

            } catch (Exception e) {
                Assert.fail("Confirmation order failed due to '"+e.getMessage()+"'");
            }
        });

        Then("^I see success message (.+)$", (String successMessage) -> {
            try {
                fluentWait(confirmationOrderObjects.successTextMessage);
                String expectedSuccessMsg = confirmationOrderObjects.successTextMessage.getText();
                if (expectedSuccessMsg.contains(successMessage)) {
                    attachScreenshot();
                } else {
                    Assert.fail("Success message is not displayed after confirming order");
                }
            } catch (Exception e) {
                Assert.fail("Success text message failed due to '"+e.getMessage()+"'");
            }
        });

        And("^Verify order reference in Order History$", () -> {
            try {
                String getOrderConfirmationDetails = confirmationOrderObjects.orderConfirmationDetails.getText();
                confirmationOrderObjects.orderConfirmationDetails.click();
                Robot robot = new Robot();
                robot.keyPress(KeyEvent.VK_DOWN);
                robot.keyRelease(KeyEvent.VK_DOWN);

                attachScreenshot();
                confirmationOrderObjects.backToOrdersButton.click();
                
                waitFor(5000);
                boolean newReferenceIsPresent;
                newReferenceIsPresent = false;
                for (WebElement orderElements : confirmationOrderObjects.tableOrderHistory) {
                    String expectedRefNum = orderElements.getText();
                    if (getOrderConfirmationDetails.contains(expectedRefNum)) {
                        attachScreenshot();
                        newReferenceIsPresent = true;
                        break;
                    }
                }

                if (newReferenceIsPresent == false) {
                    Assert.fail("New reference number is not reflected in the order history");
                }

            } catch (Exception e) {
                Assert.fail("Verification of reference in order history failed due to '"+e.getMessage()+"'");
            }
        });
    }
}
