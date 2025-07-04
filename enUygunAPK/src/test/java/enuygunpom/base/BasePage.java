package enuygunpom.base;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.screenrecording.CanRecordScreen;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.Base64;

public class BasePage {

    AppiumDriver driver;
    WebDriverWait wait;
    TouchAction swipe;

    public BasePage(AppiumDriver driver){
        this.driver = driver;
        wait = new WebDriverWait(driver,60);
    }
    public void waitElement(By by){
        wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }
    public WebElement findElement(By by){
        waitElement(by);
        return driver.findElement(by);
    }
    public void clickElement(By by){
        findElement(by).click();
    }
    public void clearElement(By by){
        findElement(by).clear();
    }
    public void sendKeys(By by, String text){
        findElement(by).sendKeys(text);
    }
    public void getText(By by){
        System.out.println(findElement(by).getText());
    }
    public void swipeScreen(int px,int py,int mx,int my){
        swipe = new TouchAction<>(driver)
                .press(PointOption.point(px,py))
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(800)))
                .moveTo(PointOption.point(mx,my))
                .release()
                .perform();
    }
    public void startRecorScreen(){
        //start screen
        ((CanRecordScreen)driver).startRecordingScreen();
    }
    public void stopRecordScreen(){
        String video;

        //stop screen
        video = ((CanRecordScreen)driver).stopRecordingScreen();
        byte[] decodeVideo = Base64.getMimeDecoder().decode(video);

        try{
            Path testVideoDir = Paths.get(System.getProperty("user.dir")+"/videos");
            Files.createDirectories(testVideoDir);
            Path testVideoFile =
                    Paths.get(testVideoDir.toString(),String.format("%s-%d.%s","test",System.currentTimeMillis(),"mp4"));
            Files.write(testVideoFile,decodeVideo);
        }catch (Exception e){
            e.printStackTrace();
        }

    }
    public void sendKeysWıthKeyboard(String txt){
        Actions kk = new Actions(driver);
        kk.sendKeys(txt).perform();
    }
}
