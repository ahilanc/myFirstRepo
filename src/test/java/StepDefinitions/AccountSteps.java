package StepDefinitions;

import Helpers.JsonDataHelper;
import cucumber.TestContainer;
import io.cucumber.java8.En;
import org.junit.Assert;
import pageObjects.AccountObjects;

import static library.utilLibrary.*;
import static testSupport.WebDriverUtil.attachScreenshot;

public class AccountSteps implements En {
    TestContainer testContext;
    AccountObjects accountObjects;

    public AccountSteps(TestContainer context) {
        testContext = context;
        accountObjects = testContext.getPageObjectManager().getAccountObjects();

        When("^I click on T-Shirts menu$", () -> {
            try {
                accountObjects.tabTShirtsMenu.click();
                waitFor(3000);
                fluentWait(accountObjects.clickProductItem);
                Assert.assertTrue(accountObjects.clickProductItem.isDisplayed());
                attachScreenshot();
            } catch (Exception e) {
                Assert.fail("T-Shirts Menu failed due to '"+e.getMessage()+"'");
            }
        });

        And("^I choose to buy first item$", () -> {
            try {
                accountObjects.clickProductItem.click();
                attachScreenshot();
            } catch (Exception e) {
                Assert.fail("Product items in the list failed due to '"+e.getMessage()+"'");
            }
        });

        When("^I press 'My Personal Information' button$", () -> {
            try {
                accountObjects.updatePersonalInfoButton.click();
                waitFor(3000);
                fluentWait(accountObjects.inputFirstName);
                Assert.assertTrue(accountObjects.inputFirstName.isDisplayed());
                attachScreenshot();
            } catch (Exception e) {
                Assert.fail("Personal information failed due to '"+e.getMessage()+"'");
            }
        });

        And("^I enter (.+) in my personal information$", (String firstName) -> {
            try {
                JsonDataHelper.initTestdata("TestData");
                String getCurrPwd = JsonDataHelper.getCurrentTestData().get("CurrentPassword");
                accountObjects.inputFirstName.clear();
                accountObjects.inputFirstName.sendKeys(firstName);
                attachScreenshot();
                accountObjects.inputOldPassword.sendKeys(getCurrPwd);
                attachScreenshot();
                waitFor(3000);
            } catch (Exception e) {
                Assert.fail("Enter first name in my personal information failed due to '"+e.getMessage()+"'");
            }
        });

        And("^I click on Save button$", () -> {
            try {
                accountObjects.saveButton.click();
                attachScreenshot();
            } catch (Exception e) {
                Assert.fail("Clicking Save button failed due to '"+e.getMessage()+"'");
            }
        });

        Then("^I should see the success message (.+)$", (String successMessage) -> {
            try {
                fluentWait(accountObjects.labelSuccessTextMessage);
                waitFor(3000);

                String expectedTextMsg = accountObjects.labelSuccessTextMessage.getText();
                if (expectedTextMsg.contains(successMessage)) {
                    attachScreenshot();
                } else {
                    Assert.fail(expectedTextMsg);
                }
            } catch (Exception e) {
                Assert.fail("Success Message failed due to '"+e.getMessage()+"'");
            }
        });

    }
}
