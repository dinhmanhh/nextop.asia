package pageObject;

import commons.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import pageUIs.MailLocalPageUI;

public class MailLocalPageObject extends BasePage {
    private WebDriver driver;
    public MailLocalPageObject(WebDriver driver){
        this.driver = driver;
    }

    @Step("Input to Email textbox")
    public void inputToEmailTextbox(String email) {
        waitForElementVisible(driver, MailLocalPageUI.EMAIL_TEXTBOX);
        sendkeysToElement(driver, MailLocalPageUI.EMAIL_TEXTBOX, email);
    }

    @Step("Input to Password textbox")
    public void inputToPasswordTextbox(String password) {
        waitForElementVisible(driver, MailLocalPageUI.PASSWORD_TEXTBOX);
        sendkeysToElement(driver, MailLocalPageUI.PASSWORD_TEXTBOX, password);
    }

    @Step("Click to Login button")
    public void clickToLoginButton(){
        waitForElementClickable(driver, MailLocalPageUI.LOGIN_BUTTON);
        clickToElement(driver, MailLocalPageUI.LOGIN_BUTTON);
    }


}
