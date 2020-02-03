package tests;

import api.android.Android;
import api.apps.clocktest.ClockTest;
import core.MyLogger;
import core.managers.TestManager;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.junit.*;


public class Functionality extends TestManager{

    private static ClockTest clockTest = Android.app.clocktest;

    @BeforeClass
    public static void beforeClass() {
        clockTest.open();
    }

    @Before
    public void beforeEach() {
        testInfo.suite("Functionality");
    }

    @Test
    public void test4() {
        testInfo.id("test4").name("Verify that alarm can be deleted");
        clockTest.home.tapAddButton();
        clockTest.home.setHoursField("10");
        clockTest.home.setMinutesField("55");
        clockTest.home.tapSaveButton();
        clockTest.home.tapDeleteAlarmButton();
        Assert.assertFalse(clockTest.home.uiObject.alarmClockLogo().exists());
    }

    @AfterClass
    public static void afterClass() {
       Android.driver.pressKey(new KeyEvent(AndroidKey.HOME));
    }
}
