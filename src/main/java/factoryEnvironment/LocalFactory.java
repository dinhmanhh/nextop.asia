package factoryEnvironment;

import factoryBrowser.*;
import org.openqa.selenium.WebDriver;

public class LocalFactory {
    private WebDriver driver;
    private String browserName;


    public LocalFactory(String browserName) {
        this.browserName = browserName;
    }

    public WebDriver createDriver(){
        BrowserList browser = BrowserList.valueOf(browserName.toUpperCase());
        switch (browser){
            case CHROME:
                driver = new ChromeDriverManager().getBrowserDriver();
                break;
            case FIREFOX:
                driver = new FirefoxDriverManager().getBrowserDriver();
                break;
            case EDGE:
                driver = new EdgeDriverManager().getBrowserDriver();
                break;
            case SAFARI:
                driver = new SafariDriverManager().getBrowserDriver();
                break;
            default:
                throw new BrowserNotSupportedException(browserName);
        }
        return driver;
    }
}
