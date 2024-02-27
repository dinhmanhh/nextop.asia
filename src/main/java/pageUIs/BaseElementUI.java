package pageUIs;

public class BaseElementUI {
    public static final String HEADER_USER_NAME = "//header[@class='oxd-topbar']//p[@class='oxd-userdropdown-name']";
    public static final String DYNAMIC_MAIN_MENU = "//ul[@class='oxd-main-menu']//span[text()='%s']//parent::a";
    public static final String SPINNER_LOADING_ICON = "//div[@class='oxd-loading-spinner']";
    public static final String UPLOAD_FILE = "//input[@type='file']";
    public static final String SAVED_SUCCESS_MESSAGE = "//div[@class='oxd-toast-start']//p[text()='Successfully Saved']";
    public static final String UPDATED_SUCCESS_MESSAGE = "//div[@class='oxd-toast-start']//p[text()='Successfully Updated']";
    public static final String DELETED_SUCCESS_MESSAGE = "//div[@class='oxd-toast-start']//p[text()='Successfully Deleted']";

}