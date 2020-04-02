package library;

import Helpers.FileReaderHelper;
import listeners.poBaseClass;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.log4j.Logger;

public class utilLibrary {
    public static WebDriver gDriver;
    public static Logger logger = Logger.getLogger("Inside helper Class " + utilLibrary.class.getName().toUpperCase());

    public static void openUrl(String URL) {
        WebDriver webDriver = poBaseClass.poGetDriver();
        gDriver = webDriver;
        logger.info("Opening URL: "+URL);
        webDriver.get(URL);
    }

    public static String fnTakeScreenshot() {
        try {
            WebDriver driver = poBaseClass.poGetDriver();
            TakesScreenshot screenshot = (TakesScreenshot)driver;
            File scrFileObj = screenshot.getScreenshotAs(OutputType.FILE);
            String DestFilePath = FileReaderHelper.getInstance().getConfigFileHelper().getScreenshotPath()+ getTimeStamp() +".png";
            File DestFileObj = new File(DestFilePath);
            try {
                FileUtils.copyFile(scrFileObj,DestFileObj);
            } catch (IOException e) {
                logger.info("Unable to take screenshot");
            }
            return DestFilePath;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getTimeStamp() {
        String DateValue = null;
        String dtFormat = "(dd-MM-yyyy)_(HH:mm:ss)";
        SimpleDateFormat sdf = new SimpleDateFormat(dtFormat);
        Date dte = new Date();
        try {
            DateValue = sdf.format(dte);
            DateFormat DF = DateFormat.getDateTimeInstance();
            DateValue = DateValue.replaceAll(" ", "_");
            DateValue = DateValue.replaceAll("-", "_");
            DateValue = DateValue.replaceAll(":", "_");
            DateValue = DateValue.replace(".", "_");
        } catch (Exception e) {
            return DateValue;
        }
        return DateValue;
    }

    public static WebElement fluentWait(By locator) {
        WebDriver webDriver = poBaseClass.poGetDriver();
        long timeStart = System.currentTimeMillis();
        WebElement elem = (new WebDriverWait(webDriver, 20))
                .until(ExpectedConditions.presenceOfElementLocated(locator));
        long timeEnd = System.currentTimeMillis();
        logger.info("Element waited for: " + (timeEnd - timeStart) + " ms.");
        return elem;
    }

    public static WebElement fluentWait(WebElement webElement) {
        WebDriver webDriver = poBaseClass.poGetDriver();
        long timeStart = System.currentTimeMillis();
        WebElement elem = (new WebDriverWait(webDriver, 20))
                .until(ExpectedConditions.visibilityOf(webElement));
        long timeEnd = System.currentTimeMillis();
        logger.info("Element waited for: " + (timeEnd - timeStart) + " ms.");
        return elem;
    }

    public static WebDriver getLatestDriver() {
        WebDriver driver = poBaseClass.poGetDriver();
        poBaseClass.poSetDriver(driver);
        return driver;
    }

    public static void click(WebElement webElement) {
        getLatestDriver();
        String s = "";
        try {
            s = webElement.toString();
            if (s.contains("->"))
                s = s.split("->")[1];

            /*Click Event*/
            boolean flag = retryingFindClick(webElement);
            logger.info("Performed Click @: '" + s + "'");
            waitForPageLoad();
        } catch (Exception e) {
            logger.info("Not able to Perform Click @: '" + s + "'"+e.getMessage());
        }
    }

    public static boolean retryingFindClick(WebElement webElement) {
        boolean result = false;
        int attempts = 0;
        while (attempts < 10) {
            try {
                waitFor(1000);
                webElement.click();
                result = true;
                break;
            } catch (StaleElementReferenceException e) {
            }
            attempts++;
        }
        return result;
    }

    public static void waitForPageLoad() {
        WebDriver driver;// = po_BaseClass.po_GetDriver();
        driver = getLatestDriver();
        ExpectedCondition<Boolean> expect = new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
            }
        };
        Wait<WebDriver> wait = new WebDriverWait(driver, 20);
        try {
          wait.until(expect);
        } catch (Exception e) {
            logger.info("Page Load Condition failed. Continuing with test");
        }

        getLatestDriver();

    }

    public static void waitFor(long fWait ) {
        try {
            Thread.sleep(fWait);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
