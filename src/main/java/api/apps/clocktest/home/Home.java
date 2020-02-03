package api.apps.clocktest.home;

import api.android.Android;
import api.interfaces.Activity;
import core.MyLogger;

import java.util.NoSuchElementException;

public class Home implements Activity {

    public HomeUiObject uiObject = new HomeUiObject();

    public Home tapAddButton() {
        try {
            MyLogger.log.info("Tapping Add button");
            uiObject.addButton().tap();
            return this;
        }catch (NoSuchElementException e) {
            throw new AssertionError("Can't tap Add button, element absent or blocked");
        }
    }

    public void setHoursField(String hours) {
        try {
            MyLogger.log.info("Setting Hours field");
            uiObject.hoursField().tap().typeText(hours);
        }catch (NoSuchElementException e) {
            throw new AssertionError("Can't type hours, element absent or blocked");
        }
    }

    public void setMinutesField(String minutes) {
        try {
            MyLogger.log.info("Setting Minutes field");
            uiObject.minutesField().tap().typeText(minutes);
        }catch (NoSuchElementException e) {
            throw new AssertionError("Can't type minutes, element absent or blocked");
        }
    }

    public Object waitToLoad() {
        try {
            MyLogger.log.info("Waiting for home activity");
            uiObject.addButton().waitToAppear(10);
            return Android.app.clocktest.home;
        }catch (AssertionError e) {
            throw new AssertionError("Home activity failed to load/open");
        }
    }

    public Home tapSaveButton() {
        try {
            MyLogger.log.info("Tapping save button");
            uiObject.saveButton().tap();
            return Android.app.clocktest.home;
        }catch (NoSuchElementException e) {
            throw new AssertionError("Can't tap save button, element absent or blocked");
        }
    }

    public Home tapCancelButton() {
        try {
            MyLogger.log.info("Tapping cancel button");
            uiObject.cancelButton().tap();
            return Android.app.clocktest.home;
        }catch (NoSuchElementException e) {
            throw new AssertionError("Can't tap cancel button, element absent or blocked");
        }
    }

    public Home tapDeleteAlarmButton() {
        try {
            MyLogger.log.info("Tapping delete alarm button");
            uiObject.alarmClockDelete().tap();
            return Android.app.clocktest.home;
        }catch (NoSuchElementException e) {
            throw new AssertionError("Can't tap alarm delete button, element absent or blocked");
        }
    }
}
