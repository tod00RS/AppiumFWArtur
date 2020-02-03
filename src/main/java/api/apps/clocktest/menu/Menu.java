package api.apps.clocktest.menu;

import api.android.Android;
import api.apps.clocktest.home.Home;
import core.MyLogger;

import java.util.NoSuchElementException;

public class Menu {

    public MenuUiObjects uiObjects = new MenuUiObjects();

    public Home tapAlarm() {
        try {
            MyLogger.log.info("Tapping on the Alarm Menu button");
            uiObjects.alarm().tap();
            return Android.app.clocktest.home;
        }catch (NoSuchElementException e) {
            throw new AssertionError("Can't tap alarm button, element absent or blocked");
        }
    }

    public void tapWorldClock() {
        try {
            MyLogger.log.info("Tapping on the World clock Menu button");
            uiObjects.worldClock().tap();
        }catch (NoSuchElementException e) {
            throw new AssertionError("Can't tap world clock button, element absent or blocked");
        }
    }

    public void tapStopwatch() {
        try {
            MyLogger.log.info("Tapping on the Alarm Menu button");
            uiObjects.stopwatch().tap();
        }catch (NoSuchElementException e) {
            throw new AssertionError("Can't tap stopwatch button, element absent or blocked");
        }
    }

    public void tapTimer() {
        try {
            MyLogger.log.info("Tapping on the timer Menu button");
            uiObjects.timer().tap();
        }catch (NoSuchElementException e) {
            throw new AssertionError("Can't tap timer button, element absent or blocked");
        }
    }
}
