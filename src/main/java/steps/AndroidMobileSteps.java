package steps;


import io.appium.java_client.android.AndroidDriver;
import net.thucydides.core.annotations.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;

public class AndroidMobileSteps {

    public AndroidDriver driver;
    public WebDriverWait wait;

    @Step
    public void setup(String deviceName, String udId, String platformVersion) throws MalformedURLException {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("deviceName", deviceName);
        caps.setCapability("udid", udId); //DeviceId from "adb devices" command
        caps.setCapability("platformName", "Android");
        caps.setCapability("platformVersion", platformVersion);
        caps.setCapability("skipUnlock", "true");
        caps.setCapability("appPackage", "com.wt.apkinfo");
        caps.setCapability("appActivity", "com.wt.apkinfo.activity.StartActivity");
        caps.setCapability("noReset", "false");
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), caps);
        wait = new WebDriverWait(driver, 10);

    }

    @Step
    public void basicTest() {

        wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.id("com.wt.apkinfo:id/icon1"))).click();


        wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.xpath("//android.widget.ImageView[@content-desc=\"More options\"]"))).click();


        wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.id("com.wt.apkinfo:id/title")));

        wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.xpath("//android.widget.TextView[@text=\"Search in Play Store\"]"))).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.id("android:id/text1")));

    }

    @Step
    public void teardown() {
        driver.quit();
    }

}
