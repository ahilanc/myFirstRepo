package Helpers;

import Enums.DriverType;
import Enums.EnvironmentType;

import listeners.poBaseClass;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.concurrent.TimeUnit;


public class WebDriverHelper {
    private WebDriver driver;
    private static DriverType driverType;
    private static EnvironmentType environmentType;
    private static final String CHROME_DRIVER_PROPERTY = "webdriver.chrome.driver";
    public static Logger logger = LogManager.getLogger("Inside helpers package " + WebDriverHelper.class.getName().toUpperCase());

    public WebDriverHelper() {
        driverType = FileReaderHelper.getInstance().getConfigFileHelper().getBrowser();
        environmentType = FileReaderHelper.getInstance().getConfigFileHelper().getEnvironment();
    }

    public WebDriver getDriver() throws Exception {
        if(driver == null) {
            driver = createDriver();
        }
        return driver;
    }

    private WebDriver createDriver() throws Exception {
        switch (environmentType) {
            case LOCAL :
                driver = createLocalDriver();
                break;
            case REMOTE :
                driver = createRemoteDriver();
                break;
        }
        return driver;
    }

    private WebDriver createRemoteDriver() {
        throw new RuntimeException("RemoteWebDriver is not yet implemented");
    }

    private WebDriver createLocalDriver() throws Exception {
        try {
            switch (driverType) {
                case FIREFOX:
                    driver = new FirefoxDriver();
                    break;
                case CHROME:
                    //System.setProperty(CHROME_DRIVER_PROPERTY, FileReaderHelper.getInstance().getConfigFileHelper().getDriverPath());
                    //driver = new ChromeDriver();
                    driver = launchChromeBrowser();
                    break;
                case INTERNETEXPLORER:
                    driver = new InternetExplorerDriver();
                    break;
            }
            try {
                poBaseClass.poSetDriver(driver);
            } catch (Exception e) {

            }
        } catch (Exception e) {
            logger.info("Error occured while setting up WebDriver");
        }

        return driver;
    }

    private static WebDriver launchChromeBrowser() {
        System.setProperty(CHROME_DRIVER_PROPERTY, FileReaderHelper.getInstance().getConfigFileHelper().getDriverPath());
        ChromeOptions opt = new ChromeOptions();
        opt.addArguments("--disable-web-security");
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        capabilities.setCapability(ChromeOptions.CAPABILITY, opt);
        WebDriver webDriver = new ChromeDriver(capabilities);
        webDriver.manage().timeouts().implicitlyWait(FileReaderHelper.getInstance().getConfigFileHelper().getImplicitlyWait(), TimeUnit.SECONDS);
        if(FileReaderHelper.getInstance().getConfigFileHelper().getBrowserWindowSize()) {
            webDriver.manage().window().maximize();
        }
        webDriver.manage().deleteAllCookies();
        return webDriver;
    }

    public void quitBrowser() {
        driver.close();
        driver.quit();
    }

}
