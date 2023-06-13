package drivers;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverProvider;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;

import javax.annotation.CheckReturnValue;
import javax.annotation.Nonnull;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import static config.Project.config;
import static io.appium.java_client.remote.AutomationName.ANDROID_UIAUTOMATOR2;
import static io.appium.java_client.remote.MobilePlatform.ANDROID;

public class LocalAndroidDriver implements WebDriverProvider {

    public static URL getAppiumServerUrl() {
        try {
            return new URL(config.remoteDriverUrl());
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    @CheckReturnValue
    @Nonnull
    public WebDriver createDriver(@Nonnull Capabilities capabilities) {
        Configuration.browserSize = null;
        UiAutomator2Options options = new UiAutomator2Options();
        options.merge(capabilities);
        options.setCapability("uiautomator2ServerInstallTimeout", 90000);
        options.setAutomationName(ANDROID_UIAUTOMATOR2)
                .setPlatformName(ANDROID)
                .setDeviceName(config.deviceName())
                .setPlatformVersion(config.platformVersion())
                .setApp(getAppPath().getAbsolutePath())
                .setLocale("ru")
                .setLanguage("ru")
                .setAppPackage(config.appPackage())
                .setAppActivity(config.appActivity());

        return new AndroidDriver(getAppiumServerUrl(), options);
    }

    private File getAppPath() {
        return new File("src/test/resources/apk/Wildberries_5.1.3000_Apkpure.apk");


    }
}
