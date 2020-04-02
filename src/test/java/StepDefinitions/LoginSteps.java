package StepDefinitions;

import Helpers.FileReaderHelper;
import Helpers.JsonDataHelper;
import cucumber.TestContainer;
import io.cucumber.java8.En;
import org.junit.Assert;
import pageObjects.LoginObjects;

import static library.utilLibrary.*;
import static testSupport.WebDriverUtil.attachScreenshot;

public class LoginSteps implements En {
    TestContainer testContext;
    LoginObjects loginObjects;

    public LoginSteps(TestContainer context) {
        testContext = context;
        loginObjects = testContext.getPageObjectManager().getLoginObjects();

        Given("^I login into Online shopping website$", () -> {
            try {
                openUrl(FileReaderHelper.getInstance().getConfigFileHelper().getApplicationUrl());

                loginObjects.tabSignIn.click();
                Thread.sleep(5000);

                JsonDataHelper.initTestdata("Users");
                String emailID = JsonDataHelper.getCurrentTestData().get("EmailID");
                String password = JsonDataHelper.getCurrentTestData().get("Password");
                loginObjects.inputEmailAddress.sendKeys(emailID);
                loginObjects.inputPassword.sendKeys(password);
                attachScreenshot();
                loginObjects.submitLoginButton.click();

                waitFor(3000);
                fluentWait(loginObjects.labelHomePage);
                if (loginObjects.labelHomePage.isDisplayed()) {
                    attachScreenshot();
                } else {
                    Assert.fail("System navigate to Home Page is not successful");
                }
            } catch (Exception e) {
                Assert.fail("Login Page failed due to '"+e.getMessage()+"'");
            }
        });

    }
}
