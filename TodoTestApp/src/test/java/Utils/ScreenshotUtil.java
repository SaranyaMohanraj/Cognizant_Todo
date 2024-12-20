package Utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
public class ScreenshotUtil {

    public static String takeScreenshot(WebDriver driver, String fileName) {
        // Directory to save screenshots
        File screenshotsDir = new File("screenshots");
        if (!screenshotsDir.exists()) {
            screenshotsDir.mkdir();
        }

        // Take a screenshot
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        // Define the file path
        String filePath = "screenshots/" + fileName + ".png";
        System.out.println("***********************Screenshots folder: " + screenshotsDir.getAbsolutePath());

        // Save the screenshot
        try {
            FileUtils.copyFile(screenshot, new File(screenshotsDir.getAbsolutePath()+ fileName + ".png"));
        } catch (IOException e) {
            System.out.println("Failed to save screenshot: " + e.getMessage());
        }

        return filePath;
    }
}