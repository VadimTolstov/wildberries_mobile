package helpers;

import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.nio.charset.StandardCharsets;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class Attachments {
    @Attachment(value = "{attachName}", type = "image/png")
    public static byte[] screenshotAs(String attachName) {
        try {
            byte[] result = ((TakesScreenshot) getWebDriver()).getScreenshotAs(OutputType.BYTES);
            return result;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return new byte[0];
    }

    @Attachment(value = "Page source", type = "text/plain")
    public static byte[] pageSource() {
        return getWebDriver().getPageSource().getBytes(StandardCharsets.UTF_8);
    }


    @Attachment(value = "Video", type = "text/html", fileExtension = ".html")
    public static String videoBrowserstack(String sessionId) {
        return "<html><body><video width='100%' height='100%' controls autoplay><source src='"
               + Browserstack.videoUrl(sessionId)
               + "' type='video/mp4'></video></body></html>";
    }

    @Attachment(value = "Browserstack full info link", type = "text/html", fileExtension = ".html")
    public static String browserstackFullInfoLink(String sessionId) {
        return "<html><body><a href='"
               + Browserstack.fullInfoPublicUrl(sessionId)
               + "'>Full info link</a></body></html>";
    }

    public static String getSessionId() {
        return ((RemoteWebDriver) getWebDriver()).getSessionId().toString();
    }
}
