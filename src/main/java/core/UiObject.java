package core;

import api.android.Android;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.touch.TouchActions;

import java.util.NoSuchElementException;

public class UiObject {

    private String locator;

    UiObject(String locator) {
        this.locator = locator;
        MyLogger.log.info("Created new UiObject: " + this.locator);
    }

    // checking if locator is xPath
    private boolean isXpath() {
        return !locator.contains("UiSelector");
    }

    // does element exist
    public boolean exists() {
        try {
            WebElement element;
            if (isXpath()) element = Android.driver.findElementByXPath(locator);
            else element = Android.driver.findElementByAndroidUIAutomator(locator);
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    // is checkBox Checked
    public boolean isChecked() {
        WebElement element;
        if (isXpath()) element = Android.driver.findElementByXPath(locator);
        else element = Android.driver.findElementByAndroidUIAutomator(locator);
        return element.getAttribute("checked").equals("true");
    }

    // is element checkable
    public boolean isCheckable() {
        WebElement element;
        if (isXpath()) element = Android.driver.findElementByXPath(locator);
        else element = Android.driver.findElementByAndroidUIAutomator(locator);
        return element.getAttribute("checkable").equals("true");
    }

    // is element clickable
    public boolean isClickable() {
        WebElement element;
        if (isXpath()) element = Android.driver.findElementByXPath(locator);
        else element = Android.driver.findElementByAndroidUIAutomator(locator);
        return element.getAttribute("clickable").equals("true");
    }

    // is element enabled
    public boolean isEnabled() {
        WebElement element;
        if (isXpath()) element = Android.driver.findElementByXPath(locator);
        else element = Android.driver.findElementByAndroidUIAutomator(locator);
        return element.getAttribute("enabled").equals("true");
    }

    // is element focusable
    public boolean isFocusable() {
        WebElement element;
        if (isXpath()) element = Android.driver.findElementByXPath(locator);
        else element = Android.driver.findElementByAndroidUIAutomator(locator);
        return element.getAttribute("focusable").equals("true");
    }

    // is element focused
    public boolean isFocused() {
        WebElement element;
        if (isXpath()) element = Android.driver.findElementByXPath(locator);
        else element = Android.driver.findElementByAndroidUIAutomator(locator);
        return element.getAttribute("focused").equals("true");
    }

    // is element scrollable
    public boolean isScrollable() {
        WebElement element;
        if (isXpath()) element = Android.driver.findElementByXPath(locator);
        else element = Android.driver.findElementByAndroidUIAutomator(locator);
        return element.getAttribute("scrollable").equals("true");
    }

    // is element long clickable
    public boolean isLongClickable() {
        WebElement element;
        if (isXpath()) element = Android.driver.findElementByXPath(locator);
        else element = Android.driver.findElementByAndroidUIAutomator(locator);
        return element.getAttribute("longClickable").equals("true");
    }

    // is element selected
    public boolean isSelected() {
        WebElement element;
        if (isXpath()) element = Android.driver.findElementByXPath(locator);
        else element = Android.driver.findElementByAndroidUIAutomator(locator);
        return element.getAttribute("selected").equals("true");
    }

    // elements location
    public Point getLocation() {
        WebElement element;
        if (isXpath()) element = Android.driver.findElementByXPath(locator);
        else element = Android.driver.findElementByAndroidUIAutomator(locator);
        return element.getLocation();
    }

    // elements text
    public String getText() {
        WebElement element;
        if (isXpath()) element = Android.driver.findElementByXPath(locator);
        else element = Android.driver.findElementByAndroidUIAutomator(locator);
        return element.getAttribute("name");
    }

    // elements resourceId
    public String getResourceId() {
        WebElement element;
        if (isXpath()) element = Android.driver.findElementByXPath(locator);
        else element = Android.driver.findElementByAndroidUIAutomator(locator);
        return element.getAttribute("resourceId");
    }

    // elements class name
    public String getClassName() {
        WebElement element;
        if (isXpath()) element = Android.driver.findElementByXPath(locator);
        else element = Android.driver.findElementByAndroidUIAutomator(locator);
        return element.getAttribute("className");
    }

    // elements content description
    public String getContentDesc() {
        WebElement element;
        if (isXpath()) element = Android.driver.findElementByXPath(locator);
        else element = Android.driver.findElementByAndroidUIAutomator(locator);
        return element.getAttribute("contentDesc");
    }

    // clear text
    public UiObject clearText() {
        if (isXpath()) Android.driver.findElementByXPath(locator).clear();
        else Android.driver.findElementByAndroidUIAutomator(locator).clear();
        return this;
    }

    // type in text
    public UiObject typeText(String value) {
        if (isXpath()) Android.driver.findElementByXPath(locator).sendKeys(value);
        else Android.driver.findElementByAndroidUIAutomator(locator).sendKeys(value);
        return this;
    }

    // tap element
    public UiObject tap() {
        if (isXpath()) Android.driver.findElementByXPath(locator).click();
        else Android.driver.findElementByAndroidUIAutomator(locator).click();
        return this;
    }

    // original scroll To method
//    public UiObject scrollTo() {
//        if (!locator.contains("text"))
//            throw new RuntimeException("Scroll to method can only be used with text attributes and curent locator: " + locator + " does not contain any text attribute");
//        if (isXpath())
//            Android.driver.scrollTo(locator.substring(locator.indexOf("@text=\""), locator.indexOf("\"")).replace("@text=\"", ""));
//        else {
//            String text;
//            if (locator.contains("textContains")) text = locator.substring(locator.indexOf(".textContains(\""), locator.indexOf("\"")).replace(".textContains(\"", "");
//            else text = locator.substring((locator.indexOf(".text(\""), locator.indexOf("\")")).replace(".text(\"", "");
//            Android.driver.scrollTo(text);
//        }
//        return this;
//    }

    // scrollTo method
    public UiObject scrollTo() {
        TouchActions action = new TouchActions(Android.driver);
        if (isXpath()) {
            action.scroll(Android.driver.findElementByXPath(locator), 10, 100);
            action.perform();
        } else {
            action.scroll(Android.driver.findElementByAndroidUIAutomator(locator), 10, 100);
            action.perform();
        }
        return this;
    }

    public UiObject waitToAppear(int seconds) {
        Timer timer = new Timer();
        timer.start();
        while (!timer.expired(seconds)) if(exists()) break;
        if(timer.expired(seconds) && !exists()) throw new AssertionError("Element " + locator +" failed to appear within " + seconds + " seconds");
        return this;
    }

    public UiObject waitToDisappear(int seconds) {
        Timer timer = new Timer();
        timer.start();
        while (!timer.expired(seconds)) if(!exists()) break;
        if(timer.expired(seconds) && exists()) throw new AssertionError("Element " + locator +" failed to disappear within " + seconds + " seconds");
        return this;
    }
}
