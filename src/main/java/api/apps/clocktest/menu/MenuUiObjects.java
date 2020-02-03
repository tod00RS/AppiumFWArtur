package api.apps.clocktest.menu;

import core.UiObject;
import core.UiSelector;

public class MenuUiObjects {

    private static UiObject
            alarm,
            worldClock,
            stopwatch,
            timer;

    public UiObject alarm() {
        if(alarm == null) alarm = new UiSelector().text("Alarm").makeUiObject();
        return alarm;
    }

    public UiObject worldClock() {
        if(worldClock == null) worldClock = new UiSelector().text("World clock").makeUiObject();
        return worldClock;
    }

    public UiObject stopwatch() {
        if(stopwatch == null) stopwatch = new UiSelector().text("Stopwatch").makeUiObject();
        return stopwatch;
    }

    public UiObject timer() {
        if(timer == null) timer = new UiSelector().text("Timer").makeUiObject();
        return timer;
    }
}
