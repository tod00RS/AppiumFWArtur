package api.apps.clocktest;

import api.android.Android;
import api.apps.clocktest.home.Home;
import api.apps.clocktest.menu.Menu;
import api.interfaces.Application;

public class ClockTest implements Application {

    public Menu menu = new Menu();
    public Home home = new Home();

    public void forceStop() {
        Android.adb.forceStopApp(packageID());
    }

    public void clearData() {
        Android.adb.clearAppsData(packageID());
    }

    public Object open() {
        Android.adb.openAppsActivity(packageID(), activityID());
        return null;
    }

    public String packageID() {
        return "com.sec.android.app.clockpackage";
    }

    public String activityID() {
        return "com.sec.android.app.clockpackage.ClockPackage";
    }
}
