package api.apps.clocktest.home;

import api.android.Android;
import core.UiObject;
import core.UiSelector;

public class HomeUiObject {

    private static UiObject
            addButton,
            hoursField,
            minutesField,
            saveButton,
            cancelButton,
            alarmClockLogo,
            alarmClockDelete;

    public UiObject addButton() {
        if (addButton == null)
            addButton = new UiSelector().resourceId(Android.app.clocktest.packageID() + ":id/custom_create_button_alarm").makeUiObject();
        return addButton;
    }

    public UiObject hoursField() {
        if (hoursField == null)
            hoursField = new UiSelector().resourceId("touchwiz:id/numberpicker_input").makeUiObject();
        return hoursField;
    }

    public UiObject minutesField() {
        if (minutesField == null)
            minutesField = new UiSelector().resourceId("touchwiz:id/numberpicker_input").textContains("Minute").makeUiObject();
        return minutesField;
    }

    public UiObject saveButton() {
        if (saveButton == null)
            saveButton = new UiSelector().resourceId(Android.app.clocktest.packageID() + ":id/Menu_Done").makeUiObject();
        return saveButton;
    }

    public UiObject cancelButton() {
        if (cancelButton == null)
            cancelButton = new UiSelector().resourceId(Android.app.clocktest.packageID() + ":id/Menu_Cancel").makeUiObject();
        return cancelButton;
    }

    public UiObject alarmClockLogo() {
        if (alarmClockLogo == null)
            alarmClockLogo = new UiSelector().resourceId(Android.app.clocktest.packageID() + ":id/alarm_list").makeUiObject();
        return alarmClockLogo;
    }

    public UiObject alarmClockDelete() {
        if (alarmClockDelete == null)
            alarmClockDelete = new UiSelector().resourceId(Android.app.clocktest.packageID() + ":id/alarmlist_delete_icon").makeUiObject();
        return alarmClockDelete;
    }

}
