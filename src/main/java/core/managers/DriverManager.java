package core.managers;

import api.android.Android;
import core.ADB;
import core.MyLogger;
import core.constants.Arg;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.service.DriverService;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

public class DriverManager {

    private static String nodeJS = "C:/nodejs/node.exe";
    private static String appiumJS = System.getenv("APPIUM_HOME");
    private static DriverService service;
    private static String deviceID;

    private static HashMap<String, URL> hosts;
    private static String unlockPackage = "io.appium.unlock";

    private static DesiredCapabilities getCaps(String deviceID) {
        MyLogger.log.info("Creating driver caps for device: " + deviceID);
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("deviceName", deviceID);
        caps.setCapability("platformName", "Android");
//        caps.setCapability("app", "");
        return caps;
    }

    private static URL host(String deviceID) throws MalformedURLException {
        if (hosts == null) {
            hosts = new HashMap<String, URL>();
            hosts.put("46f4ecc1", new URL("http://0.0.0.0:4723/wd/hub"));
        }
        return hosts.get(deviceID);
    }

    private static ArrayList<String> getAvailableDevices() {
        MyLogger.log.info("Checking for available devices");
        ArrayList<String> availableDevices = new ArrayList<String>();
        ArrayList connectedDevices = ADB.getConnectedDevices();
        for (Object connectedDevice : connectedDevices) {
            String device = connectedDevice.toString();
            ArrayList apps = new ADB(device).getInstalledPackages();
            if (!apps.contains(unlockPackage)) availableDevices.add(device);
            else
                MyLogger.log.info("Device: " + device + " has " + unlockPackage + " installed, assuming it is under testing");
        }
        if (availableDevices.size() == 0)
            throw new RuntimeException("Not a single device is available for testing at this time");
        return availableDevices;
    }

    private static DriverService createService() throws MalformedURLException {
        service = new AppiumServiceBuilder()
                .usingDriverExecutable(new File(nodeJS))
                .withAppiumJS(new File(appiumJS))
                .withIPAddress(host(deviceID).toString().split(":")[1].replace("//", ""))
                .usingPort(Integer.parseInt(host(deviceID).toString().split(":")[2].replace("/wd/hub", "")))
                .withArgument(Arg.TIMEOUT, "120")
                .withArgument(Arg.LOG_LEVEL, "warn")
                .build();
        return service;
    }

    public static void createDriver() throws MalformedURLException {
        ArrayList<String> devices = getAvailableDevices();
        for (String device : devices) {
            try {
                deviceID = device;
                MyLogger.log.info("Trying to create a new Driver for device: " + device);
                createService().start();
                Android.driver = new AndroidDriver(host(device), getCaps(device));
                Android.adb = new ADB(device);
                break;
            } catch (Exception e) {
                e.printStackTrace();
//                Ignore and try next device
            }
        }
    }

    public static void killDriver() {
        if (Android.driver != null) {
            MyLogger.log.info("Killing Android Driver");
            Android.driver.quit();
            Android.adb.uninstallApp(unlockPackage);
            service.stop();
        } else MyLogger.log.info("Android Driver is not initialized, nothing to kill");
    }
}
