package commons;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageUIs.BaseElementUI;

import java.time.Duration;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class BasePage {
        private long longTimeout = GlobalConstants.LONG_TIMEOUT;
        private long shortTimeout = GlobalConstants.SHORT_TIMEOUT;

        public static BasePage getBasePageObject() {
            return new BasePage();
        }

        public void openPageUrl(WebDriver driver, String pageUrl) {
            driver.get(pageUrl);
        }

        public String getPageTitle(WebDriver driver) {
            return driver.getTitle();
        }

        public String getPageUrl(WebDriver driver) {
            return driver.getCurrentUrl();
        }

        public String getPageSourceCode(WebDriver driver) {
            return driver.getCurrentUrl();
        }

        public void backToPage(WebDriver driver) {
            driver.navigate().back();
        }

        public void forwardToPage(WebDriver driver) {
            driver.navigate().forward();
        }

        public void refreshCurrentPage(WebDriver driver) {
            driver.navigate().refresh();
        }

        public Alert waitForAlertPresence(WebDriver driver) {
            WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(longTimeout));
            return explicitWait.until(ExpectedConditions.alertIsPresent());
        }

        public void acceptAlert(WebDriver driver) {
            waitForAlertPresence(driver).accept();
        }

        public void cancelAlert(WebDriver driver) {
            waitForAlertPresence(driver).dismiss();
        }

        public String getAlertText(WebDriver driver) {
            return waitForAlertPresence(driver).getText();
        }

        public void sendkeysToAlert(WebDriver driver, String textValue) {
            waitForAlertPresence(driver).sendKeys(textValue);
        }

        public void switchToWindowByID(WebDriver driver, String windowID) {
            Set<String> allWindowIDs = driver.getWindowHandles();
            for (String id : allWindowIDs) {
                if (!id.equals(windowID)) {
                    driver.switchTo().window(id);
                    break;
                }
            }
        }

        public void switchToWindowByTitle(WebDriver driver, String windowTitle) {
            Set<String> allWindowIDs = driver.getWindowHandles();
            for (String id : allWindowIDs) {
                driver.switchTo().window(id);
                String actualTitle = driver.getTitle();
                if (actualTitle.equals(windowTitle)) {
                    break;
                }
            }
        }

        public void closeAllWindowWithoutParent(WebDriver driver, String parentID) {
            Set<String> allWindowIDs = driver.getWindowHandles();
            for (String id : allWindowIDs) {
                if (!id.equals(parentID)) {
                    driver.switchTo().window(id);
                    driver.close();
                }
                driver.switchTo().window(parentID);
            }
        }

        private By getByXpath(String xpathLocator) {
            return By.xpath(xpathLocator);
        }

        private WebElement getWebElement(WebDriver driver, String xpathLocator) {
            return driver.findElement(getByXpath(xpathLocator));
        }

        private String getDynamicLocator(String xpathLocator, String... dynamicValues) {
            xpathLocator = String.format(xpathLocator, (Object[]) dynamicValues);
            return xpathLocator;
        }

        public List<WebElement> getListWebElement(WebDriver driver, String xpathLocator) {
            return driver.findElements(getByXpath(xpathLocator));
        }

        public void clickToElement(WebDriver driver, String xpathLocator) {
            getWebElement(driver, xpathLocator).click();
        }

        public void clickToElement(WebDriver driver, String xpathLocator, String... dynamicValues) {
            getWebElement(driver, getDynamicLocator(xpathLocator, dynamicValues)).click();
        }

        public void sendkeysToElement(WebDriver driver, String xpathLocator, String textValue) {
            WebElement element = getWebElement(driver, xpathLocator);
            element.clear();
            element.sendKeys(textValue);
        }

        public void sendkeysToElement(WebDriver driver, String xpathLocator, String textValue, String... dynamicValues) {
            WebElement element = getWebElement(driver, getDynamicLocator(xpathLocator, dynamicValues));
            element.clear();
            element.sendKeys(textValue);
        }

        public void selectItemInDefaultDropdown(WebDriver driver, String xpathLocator, String textItem) {
            Select select = new Select(getWebElement(driver, xpathLocator));
            select.selectByVisibleText(textItem);
        }

        public void selectItemInDefaultDropdown(WebDriver driver, String xpathLocator, String textItem, String... dynamicValues) {
            Select select = new Select(getWebElement(driver, getDynamicLocator(xpathLocator, dynamicValues)));
            select.selectByVisibleText(textItem);
        }

        public String getSelectedItemDefaultDropDown(WebDriver driver, String xpathLocator) {
            Select select = new Select(getWebElement(driver, xpathLocator));
            return select.getFirstSelectedOption().getText();
        }

        public boolean isDropdownMultiple(WebDriver driver, String xpathLocator) {
            Select select = new Select(getWebElement(driver, xpathLocator));
            return select.isMultiple();
        }

        public void selectItemInCustomDropdown(WebDriver driver, String parentXpath, String childXpath, String expectedTextItem) {
            getWebElement(driver, parentXpath).click();
            sleepInSecond(1);
            WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(longTimeout));
            List<WebElement> allItems = explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByXpath(childXpath)));
            for (WebElement item : allItems) {
                if (item.getText().trim().equals(expectedTextItem)) {
                    JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
                    jsExecutor.executeScript("arguments[0].scrollIntoView(true);", item);
                    sleepInSecond(1);
                    item.click();
                    break;
                }
            }
        }

        public void sleepInSecond(long timeInSecond) {
            try {
                Thread.sleep(timeInSecond * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        public String getElementAttribute(WebDriver driver, String xpathLocator, String attributeName) {
            return getWebElement(driver, xpathLocator).getAttribute(attributeName);
        }
        public String getElementAttribute(WebDriver driver, String xpathLocator, String attributeName, String... dynamicValues) {
            return getWebElement(driver, getDynamicLocator(xpathLocator, dynamicValues)).getAttribute(attributeName);
        }

        public String getElementText(WebDriver driver, String xpathLocator) {
            return getWebElement(driver, xpathLocator).getText();
        }

        public String getElementText(WebDriver driver, String xpathLocator, String... dynamicValues) {
            return getWebElement(driver, getDynamicLocator(xpathLocator, dynamicValues)).getText();
        }

        public String getElementCSSValue(WebDriver driver, String xpathLocator, String propertyName) {
            return getWebElement(driver, xpathLocator).getCssValue(propertyName);
        }

        public String getHexaColorFromRGBa(String rgbaValue) {
            return Color.fromString(rgbaValue).asHex();
        }

        public int getElementSize(WebDriver driver, String xpathLocator) {
            return getListWebElement(driver, xpathLocator).size();
        }

        public int getElementSize(WebDriver driver, String xpathLocator, String... dynamicValues) {
            return getListWebElement(driver, getDynamicLocator(xpathLocator, dynamicValues)).size();
        }

        public void checkToDefaultCheckboxRadio(WebDriver driver, String xpathLocator) {
            WebElement element = getWebElement(driver, xpathLocator);
            if (!element.isSelected()) {
                element.click();
            }
        }
        public void checkToDefaultCheckboxRadio(WebDriver driver, String xpathLocator, String... dynamicValues) {
            WebElement element = getWebElement(driver, getDynamicLocator(xpathLocator, dynamicValues));
            if (!element.isSelected()) {
                element.click();
            }
        }
        public void uncheckToDefaultCheckboxRadio(WebDriver driver, String xpathLocator) {
            WebElement element = getWebElement(driver, xpathLocator);
            if (element.isSelected()) {
                element.click();
            }
        }
        public void uncheckToDefaultCheckboxRadio(WebDriver driver, String xpathLocator, String... dynamicValues) {
            WebElement element = getWebElement(driver, getDynamicLocator(xpathLocator, dynamicValues));
            if (element.isSelected()) {
                element.click();
            }
        }

        public boolean isElementDisplayed(WebDriver driver, String xpathLocator) {
            return getWebElement(driver, xpathLocator).isDisplayed();
        }

        public boolean isElementDisplayed(WebDriver driver, String xpathLocator, String... dynamicValues) {
            return getWebElement(driver, getDynamicLocator(xpathLocator, dynamicValues)).isDisplayed();
        }

        public boolean isElementUndisplayed(WebDriver driver, String locator){
            overrideImplicitTimeout(driver, shortTimeout);
            List<WebElement> elements = getListWebElement(driver, locator);
            overrideImplicitTimeout(driver, longTimeout);
            if (elements.size() == 0){
                return true;
            } else if (elements.size() > 0 && !elements.get(0).isDisplayed()){
                return true;
            } else {
                return false;
            }
        }

        public void overrideImplicitTimeout(WebDriver driver, long timeOut){
            driver.manage().timeouts().implicitlyWait(timeOut, TimeUnit.SECONDS);
        }
        public boolean isElementEnabled(WebDriver driver, String xpathLocator) {
            return getWebElement(driver, xpathLocator).isEnabled();
        }

        public boolean isElementSelected(WebDriver driver, String xpathLocator) {
            return getWebElement(driver, xpathLocator).isSelected();
        }

        public void switchToFrameIframe(WebDriver driver, String xpathLocator) {
            driver.switchTo().frame(getWebElement(driver, xpathLocator));
        }

        public void switchToDefaultContent(WebDriver driver) {
            driver.switchTo().defaultContent();
        }

        public void hoverMouseToElement(WebDriver driver, String xpathLocator) {
            Actions action = new Actions(driver);
            action.moveToElement(getWebElement(driver, xpathLocator)).perform();
        }

        public void pressKeyToElement(WebDriver driver, String xpathLocator, Keys key) {
            Actions action = new Actions(driver);
            action.sendKeys(getWebElement(driver, xpathLocator), key).perform();
        }

        public void pressKeyToElement(WebDriver driver, String xpathLocator, Keys key, String... dynamicValues) {
            Actions action = new Actions(driver);
            action.sendKeys(getDynamicLocator(xpathLocator, dynamicValues), key).perform();
        }

        public void scrollToBottomPage(WebDriver driver) {
            JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
            jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
        }

        public void highlightElement(WebDriver driver, String xpathLocator) {
            JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
            WebElement element = getWebElement(driver, xpathLocator);
            String originalStyle = element.getAttribute("style");
            jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", "border: 2px solid red; border-style: dashed;");
            sleepInSecond(1);
            jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", originalStyle);
        }

        public void clickToElementByJS(WebDriver driver, String xpathLocator) {
            JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
            jsExecutor.executeScript("arguments[0].click();", getWebElement(driver, xpathLocator));
        }

        public void scrollToElement(WebDriver driver, String xpathLocator) {
            JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
            jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getWebElement(driver, xpathLocator));
        }

        public void removeAttributeInDOM(WebDriver driver, String xpathLocator, String attributeRemove) {
            JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
            jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getWebElement(driver, xpathLocator));
        }

        public boolean isPageLoadedSuccess(WebDriver driver) {
            WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(longTimeout));
            JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
            ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
                @Override
                public Boolean apply(WebDriver driver) {
                    try {
                        return ((Long) jsExecutor.executeScript("return jQuery.active") == 0);
                    } catch (Exception e) {
                        return true;
                    }
                }
            };
            ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
                @Override
                public Boolean apply(WebDriver driver) {
                    return jsExecutor.executeScript("return document.readyState").toString().equals("complete");
                }
            };
            return explicitWait.until(jQueryLoad) && explicitWait.until(jsLoad);
        }

        public String getElementValidationMessage(WebDriver driver, String xpathLocator) {
            JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
            return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", getWebElement(driver, xpathLocator));
        }

        public boolean isImageLoaded(WebDriver driver, String xpathLocator) {
            JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
            boolean status = (boolean) jsExecutor.executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", getWebElement(driver, xpathLocator));
            if (status) {
                return true;
            } else {
                return false;
            }
        }

        public boolean isImageLoaded(WebDriver driver, String xpathLocator, String... dynamicValues) {
            JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
            boolean status = (boolean) jsExecutor.executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", getWebElement(driver, getDynamicLocator(xpathLocator, dynamicValues)));
            if (status) {
                return true;
            } else {
                return false;
            }
        }

        public void waitForElementVisible(WebDriver driver, String xpathLocator) {
            WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(longTimeout));
            explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getByXpath(xpathLocator)));
        }

        public void waitForElementVisible(WebDriver driver, String xpathLocator, String... dynamicValues) {
            WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(longTimeout));
            explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getByXpath(getDynamicLocator(xpathLocator, dynamicValues))));
        }

        public void waitForElementInvisible(WebDriver driver, String xpathLocator) {
            WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(longTimeout));
            explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByXpath(xpathLocator)));
        }

        /*
         * Wait for element undisplayed in DOM or not in DOM and override implicit timeout
         */
        public void waitForElementUndisplayed(WebDriver driver, String xpathLocator) {
            WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(shortTimeout));
            overrideImplicitTimeout(driver, shortTimeout);
            explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByXpath(xpathLocator)));
            overrideImplicitTimeout(driver, longTimeout);
        }
        public void waitForAllElementVisible(WebDriver driver, String xpathLocator) {
            WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(longTimeout));
            explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByXpath(xpathLocator)));
        }

        public void waitForAllElementVisible(WebDriver driver, String xpathLocator, String... dynamicValues) {
            WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(longTimeout));
            explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByXpath(getDynamicLocator(xpathLocator, dynamicValues))));
        }

        public void waitForAllElementInvisible(WebDriver driver, String xpathLocator) {
            WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(longTimeout));
            explicitWait.until(ExpectedConditions.invisibilityOfAllElements(getListWebElement(driver, xpathLocator)));
        }

        public void waitForElementClickable(WebDriver driver, String xpathLocator) {
            WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(longTimeout));
            explicitWait.until(ExpectedConditions.elementToBeClickable(getByXpath(xpathLocator)));
        }

        public void waitForElementClickable(WebDriver driver, String xpathLocator, String... dynamicValues) {
            WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(longTimeout));
            explicitWait.until(ExpectedConditions.elementToBeClickable(getByXpath(getDynamicLocator(xpathLocator, dynamicValues))));
        }

        public Set<Cookie> getBrowserCookies(WebDriver driver){
            return driver.manage().getCookies();
        }

        public void setCookies(WebDriver driver, Set<Cookie> cookies){
            for (Cookie cookie:cookies){
                driver.manage().addCookie(cookie);
            }
        }

        public void deleteAllCookies(WebDriver driver){
            driver.manage().deleteAllCookies();
        }

        public void uploadMultipleFiles(WebDriver driver, String... fileNames){
            String filePath = GlobalConstants.UPLOAD_FILE;
            String fullFileName = "";
            for (String file : fileNames){
                fullFileName = fullFileName + filePath + file + "\n";
            }
            fullFileName = fullFileName.trim();
            getWebElement(driver, BaseElementUI.UPLOAD_FILE).sendKeys(fullFileName);
        }

//    }
}
