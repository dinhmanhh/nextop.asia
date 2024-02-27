package commons;

import org.openqa.selenium.WebDriver;
import pageObject.MailLocalPageObject;
import pageObject.RegisterMailPageObject;

public class PageGeneratorManager {
    public static RegisterMailPageObject getRegisterMailPage(WebDriver driver) {
        return new RegisterMailPageObject(driver);
    }
    public static MailLocalPageObject getMailLocalPage(WebDriver driver){
        return new MailLocalPageObject(driver);
    }
}
