package trs;

import commons.BaseTest;
import commons.GlobalURLs;
import commons.PageGeneratorManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import pageObject.MailLocalPageObject;
import pageObject.RegisterMailPageObject;

public class Register_Account extends BaseTest {
    private WebDriver driver;
    private String email, password, birthday;
    private RegisterMailPageObject registerMailPage;
    private MailLocalPageObject mailLocalPage;
    @Parameters({"environmentName", "browserName", "osName", "osVersion"})
    @BeforeClass
    public void beforeClass(@Optional("local") String environmentName, @Optional("firefox") String browserName, @Optional("Windows") String osName, @Optional("10") String osVersion ) {
        driver = getBrowserDriver(environmentName, browserName, osName, osVersion);
        driver.get(GlobalURLs.TRS_REGISTER_MAIL_URL);
        registerMailPage = PageGeneratorManager.getRegisterMailPage(driver);
        email = getRandomEmail();
        password = "a123456789";
        birthday = "20000101";
    }

    @Test
    public void TC_01_Register_New_Account(){
        registerMailPage.inputToEmailTextBox(email);
        registerMailPage.inputToEmailTextBox(password);
        registerMailPage.inputToBirthdayTextBox(birthday);
        registerMailPage.checkedToAgreementCheckbox();
        registerMailPage.clickToRegisterButton();
        verifyTrue(registerMailPage.checkDisplayMessageSuccess());

        registerMailPage.openPageUrl(driver, GlobalURLs.MAIL_LOCAL_URL);
        mailLocalPage = PageGeneratorManager.getMailLocalPage(driver);
        mailLocalPage.inputToEmailTextbox(email);
        mailLocalPage.inputToPasswordTextbox(password);
        mailLocalPage.clickToLoginButton();

    }

    @AfterClass(alwaysRun = true)
    public void afterClass() {
        closeBrowserAndDriver("local");
    }
}
