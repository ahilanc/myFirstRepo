package testSupport;

import io.cucumber.java.Scenario;
import library.utilLibrary;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;

import java.util.Set;

import static library.utilLibrary.*;

public class WebDriverUtil {
    public static Logger logger = Logger.getLogger("Inside helper Class " + utilLibrary.class.getName().toUpperCase());
    public static Scenario scenario;
    WebDriver driver;

    public void initialize(Scenario scenario) {
        this.scenario = scenario;
    }

    public static void attachScreenshot() {
        WebDriver driver = getLatestDriver();
        logger.debug("Capturing screenshot for scenario {}");
        System.out.println(scenario.getName());
        logger.debug(scenario.getName());
        if (driver instanceof TakesScreenshot) {
            TakesScreenshot screenshotAbleDriver = (TakesScreenshot) driver;
            try {
                byte[] screenshot = screenshotAbleDriver.getScreenshotAs(OutputType.BYTES);
                scenario.embed(screenshot, "image/png");
            } catch (WebDriverException somePlatformsDontSupportScreenshots) {
                logger.error(somePlatformsDontSupportScreenshots.getMessage());
            }
        } else {
            logger.warn("This web driver implementation {} cannot create screenshots");
        }
    }

    public static void scrollDownScreenshot(WebElement element) {
        WebDriver driver = getLatestDriver();
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("arguments[0].scrollIntoView(true);", element);

        logger.debug("Capturing screenshot for scenario {}");
        if (driver instanceof TakesScreenshot) {
            TakesScreenshot screenshotAbleDriver = (TakesScreenshot) driver;
            try {
                byte[] screenshot = screenshotAbleDriver.getScreenshotAs(OutputType.BYTES);
                scenario.embed(screenshot, "image/png");
            } catch (WebDriverException somePlatformsDontSupportScreenshots) {
                logger.error(somePlatformsDontSupportScreenshots.getMessage());
            }
        } else {
            logger.warn("This web driver implementation {} cannot create screenshots");
        }
    }

    public void quitbrowser() {
        driver = getLatestDriver();
        driver.quit();
    }

    public void closebrowser() {
        driver = getLatestDriver();
        Set<String> windowHandles = driver.getWindowHandles();
        for (String window : windowHandles) {
            driver.switchTo().window(window);
            driver.close();
        }
    }

    public void refreshBrowser() {
        driver = getLatestDriver();
        driver.navigate().refresh();
    }

}
