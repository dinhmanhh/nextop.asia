package commons;

import java.io.File;

public class GlobalConstants {
    public static final String RELATIVE_PROJECT_PATH = System.getProperty("user.dir");
    public static final String OS_NAME = System.getProperty("os.name");
    public static final String UPLOAD_FILE = RELATIVE_PROJECT_PATH + File.separator + "uploadFiles" + File.separator;
    public static final String DOWNLOAD_FILE = RELATIVE_PROJECT_PATH + File.separator + "downloadFiles";
    public static final String BROWSER_LOG_PATH = RELATIVE_PROJECT_PATH + File.separator + "browserLogs";
    public static final long SHORT_TIMEOUT = 5;
    public static final long LONG_TIMEOUT = 30;
    public static final long RETRY_TEST_FAIL = 3;


    public static final String BROWSER_STACK_USERNAME = "mnhnh_1qzJXo";
    public static final String BROWSER_STACK_AUTOMATE_KEY = "npJ1z67yTfyq3MxvuRi7";
    public static final String BROWSER_STACK_URL = "https://" + BROWSER_STACK_USERNAME + ":" + BROWSER_STACK_AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub";


    public static final String SAUCELAB_USERNAME = "oauth-dinhmanh.dsn-6f2f2";
    public static final String SAUCELAB_AUTOMATE_KEY = "72362410-4fc1-42bf-a2e8-dcada5ebd177";
    public static final String SAUCELAB_URL = "https://" + SAUCELAB_USERNAME + ":" + SAUCELAB_AUTOMATE_KEY + "@ondemand.eu-central-1.saucelabs.com:443/wd/hub";

}