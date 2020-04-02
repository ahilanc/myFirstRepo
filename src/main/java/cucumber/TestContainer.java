package cucumber;

import Helpers.PageObjectHelper;
import Helpers.WebDriverHelper;

public class TestContainer {
    private WebDriverHelper webDriverHelper;
    private PageObjectHelper pageObjectHelper;

    public TestContainer() throws Exception {
        webDriverHelper = new WebDriverHelper();
        pageObjectHelper = new PageObjectHelper(webDriverHelper.getDriver());
    }

    public WebDriverHelper getWebDriverHelper() {
        return webDriverHelper;
    }
    public PageObjectHelper getPageObjectManager() {
        return pageObjectHelper;
    }


}
