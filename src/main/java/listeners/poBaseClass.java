package listeners;

import org.openqa.selenium.WebDriver;

public abstract class poBaseClass {
    public static WebDriver driver = null;
    public static boolean bResult;

    public poBaseClass(WebDriver driver) {
        poBaseClass.driver = driver;
        poBaseClass.bResult = true;
    }

    public static WebDriver poGetDriver() {
        return poBaseClass.driver;
    }

    public static void poSetDriver(WebDriver driver) {
        poBaseClass.driver = driver;
    }
}
