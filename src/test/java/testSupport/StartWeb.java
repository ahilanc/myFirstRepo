package testSupport;

import io.cucumber.java.Scenario;
import listeners.Logging;
import org.apache.commons.lang3.StringUtils;

public class StartWeb {
    public static Logging logging;
    public WebDriverUtil webDriverUtil;

    public StartWeb(Scenario scenario) {
        logging = new Logging();
        webDriverUtil = new WebDriverUtil();
        webDriverUtil.initialize(scenario);
        startlog(scenario);
    }

    public void closeWebBrowser(Scenario scenario) {
        webDriverUtil.closebrowser();
        webDriverUtil.quitbrowser();
        closelog(scenario);
    }

    // Logging ends
    private void closelog(Scenario scenario) {
        logging.endLog("Finishing scenario >>>>> " + scenario.getName() + "\n" + StringUtils.repeat("*", 120) + "\n");
    }

    // Starts logs
    private void startlog(Scenario scenario) {
        logging.startLog("Starting scenario >>>>> " + scenario.getName());
    }


}
