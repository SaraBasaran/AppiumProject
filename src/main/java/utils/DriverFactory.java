package utils;

import io.appium.java_client.AppiumDriver;
import io.cucumber.core.internal.com.fasterxml.jackson.databind.annotation.JsonAppend;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Time;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class DriverFactory {
    /*when open the emulator on android app to transport the data to appium and to receive the data from the appium we need
    4 capabilities;
      1.appActivity
      2.appPackage
      3.udid
      4.platformName

      */
    static AppiumDriver driver;
    static Properties properties;
    static DesiredCapabilities capabilities;

    public static AppiumDriver initializeDriver(String browser){

        properties=ConfigReader.getProperties();
        capabilities=new DesiredCapabilities();
        if (browser.equals("Android")){
            capabilities.setCapability("platformName", "Android");
            capabilities.setCapability("udid", "");
            capabilities.setCapability("appPackage", "");
            capabilities.setCapability("appActivity", "");
        } else if (browser.equals("IOS")) {
            capabilities.setCapability("platformName", "IOS");
            capabilities.setCapability("udid", "");
            capabilities.setCapability("appPackage", "");
            capabilities.setCapability("appActivity", "");

        }
        try {
            driver=new AppiumDriver(new URL("https://0.0.0.0:4723/wd/hub"), capabilities);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        int impWait= Integer.parseInt(properties.getProperty("implicitlyWait"));
        driver.manage().timeouts().implicitlyWait(impWait, TimeUnit.SECONDS);
        return getDriver();


    }

    public static AppiumDriver getDriver(){
        return driver;
    }




}
