package pageObject;

import commons.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import pageUIs.RegisterMailPageUI;

public class RegisterMailPageObject extends BasePage {
    private WebDriver driver;
    public RegisterMailPageObject(WebDriver driver){
        this.driver = driver;
    }

    @Step("Input to Username textbox")
    public void inputToEmailTextBox(String email) {
        waitForElementVisible(driver, RegisterMailPageUI.EMAIL_TEXTBOX);
        sendkeysToElement(driver, RegisterMailPageUI.EMAIL_TEXTBOX, email);
    }

    @Step("Input to Password textbox")
    public void inputToPasswordTextBox(String password) {
        waitForElementVisible(driver, RegisterMailPageUI.PASSWORD_TEXTBOX);
        sendkeysToElement(driver, RegisterMailPageUI.PASSWORD_TEXTBOX, password);
    }

    @Step("Input to Birthday textbox")
    public void inputToBirthdayTextBox(String birthday) {
        waitForElementVisible(driver, RegisterMailPageUI.BIRTHDAY_TEXTBOX);
        sendkeysToElement(driver, RegisterMailPageUI.BIRTHDAY_TEXTBOX, birthday);
    }

    @Step("Check to agreement checkbox")
    public void checkedToAgreementCheckbox() {
        waitForElementClickable(driver, RegisterMailPageUI.AGREEMENT_BUTTON);
        clickToElement(driver, RegisterMailPageUI.AGREEMENT_BUTTON);
    }

    @Step("Click to Login button")
    public void clickToRegisterButton() {
        waitForElementClickable(driver, RegisterMailPageUI.REGISTER_BUTTON);
        clickToElement(driver, RegisterMailPageUI.REGISTER_BUTTON);
    }


    public boolean checkDisplayMessageSuccess() {
        waitForElementVisible(driver, RegisterMailPageUI.REGISTER_SUCCESS_MESSAGE);
        return isElementDisplayed(driver, RegisterMailPageUI.REGISTER_SUCCESS_MESSAGE);
    }
}
