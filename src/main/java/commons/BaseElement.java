package commons;

import org.openqa.selenium.WebDriver;

public class BaseElement extends BasePage {
    private WebDriver driver;
    public BaseElement(WebDriver driver){
        this.driver = driver;
    }
}
