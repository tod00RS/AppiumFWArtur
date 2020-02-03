package tests;

import api.android.Android;
import api.apps.clocktest.ClockTest;
import core.managers.TestManager;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.junit.*;

public class Navigation extends TestManager {

    private static ClockTest clockTest = Android.app.clocktest;

    @BeforeClass
    public static void beforeClass() {
        clockTest.open();
    }

    @Before
    public void before() {
        testInfo.suite("Navigation");
    }

    @Test
    public void test1() {
        testInfo.id("test1").name("Verify that Home activity has all the elements");
        Assert.assertTrue(clockTest.menu.uiObjects.alarm().exists());
        Assert.assertTrue(clockTest.menu.uiObjects.stopwatch().exists());
        Assert.assertTrue(clockTest.menu.uiObjects.timer().exists());
        Assert.assertTrue(clockTest.menu.uiObjects.worldClock().exists());
    }

    @Test
    public void test2() {
        testInfo.id("test2").name("Verify that home has hours and minute fields");
        clockTest.home.tapAddButton();
        Assert.assertTrue(clockTest.home.uiObject.hoursField().exists());
        Assert.assertTrue(clockTest.home.uiObject.minutesField().exists());
        clockTest.home.tapCancelButton();
    }

    @Test
        public void test3() {
        testInfo.id("test3").name("Verify that alarm has been set");
        clockTest.home.tapAddButton();
        clockTest.home.setHoursField("12");
        clockTest.home.setMinutesField("55");
        clockTest.home.tapSaveButton();
        Assert.assertTrue(clockTest.home.uiObject.alarmClockLogo().exists());
    }

    @AfterClass
    public static void afterClass() {
        clockTest.home.tapDeleteAlarmButton();
        Android.driver.pressKey(new KeyEvent(AndroidKey.HOME));
    }

}
