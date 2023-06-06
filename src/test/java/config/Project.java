package config;

import org.aeonbits.owner.ConfigFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.assertj.core.api.Assertions.assertThat;

public class Project {

    public static ProjectConfig config = ConfigFactory.create(ProjectConfig.class, System.getProperties()); //todo

    private static final Logger logger = LoggerFactory.getLogger(Project.class);

    static {
        validateEnvDependentProperties();
        logger.info(config.toString());
    }

    private static void validateEnvDependentProperties() {
        validateProperty(config.platform(), "platform");
        switch (config.platform()) {
            case "android_browserstack":
                validateProperty(config.browserstackUser(), "browserstack.user");
                validateProperty(config.browserstackKey(), "browserstack.key");
                break;
            case "android_emulator":
                validateProperty(config.remoteDriverUrl(), "remoteDriver");
                validateProperty(config.deviceName(), "deviceName");
                validateProperty(config.platformVersion(), "platformVersion");
                validateProperty(config.appActivity(), "appActivity");
                validateProperty(config.appPackage(), "appPackage");
                break;
            default:
                throw new IllegalStateException("Неправильное значение в 'platform' " + config);
        }
    }

    public static void validateProperty(String propertyValue, String propertyName) {
        assertThat(propertyValue)
                .withFailMessage("'%s' значение равно null или пусто!", propertyName)
                .isNotEmpty();
    }
}
