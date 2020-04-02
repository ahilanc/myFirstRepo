package StepDefinitions;

import cucumber.TestContainer;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import testSupport.StartWeb;
import testSupport.WebDriverUtil;

public class Hooks {
    TestContainer testContainer;
    public static StartWeb startWeb;

    public Hooks(TestContainer context) {
        testContainer = context;
    }

    @Before
    public void beforeScenario(Scenario scenario){
        System.out.println("Starting web before every scenario!!!");
        startWeb = new StartWeb(scenario);
    }

    @After
    public void afterScenario(Scenario scenario){
        if (scenario.isFailed()) {
            WebDriverUtil.attachScreenshot();
        }
        testContainer.getWebDriverHelper().quitBrowser();
        System.out.println("Finishing Test!!!");
    }


}
