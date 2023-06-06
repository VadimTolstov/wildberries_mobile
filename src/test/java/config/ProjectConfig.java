package config;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "system:properties",
        "classpath:config/${platform}.properties"
})
public interface ProjectConfig extends Config {

    @Key("remoteDriverUrl")
    String remoteDriverUrl();

    String platform();

    @Key("browserstack.user")
    String browserstackUser();

    @Key("browserstack.key")
    String browserstackKey();

    @Key("deviceName")
    String getDeviceName();

    @Key("platformVersion")
    String getPlatformVersion();

    @Key("app")
    String app();

    @Key("deviceName")
    String deviceName();

    @Key("platformVersion")
    String platformVersion();

    @Key("appPackage")
    String appPackage();

    @Key("appActivity")
    String appActivity();

    @Key("base_url")
    @DefaultValue("https://api.browserstack.com/app-automate/sessions/%s.json") //todo
    String getSessionsUrl();
}
