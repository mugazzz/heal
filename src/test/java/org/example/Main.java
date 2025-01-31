package org.example;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.remote.DesiredCapabilities;


import java.net.URL;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    private AppiumDriver driver;
    private static AppiumDriverLocalService service;

    @Before
    public void setup() {

        // Build the Appium local server service
//        AppiumServiceBuilder builder = new AppiumServiceBuilder();
//        builder.withIPAddress("127.0.0.1");
//        builder.usingPort(4724).withTimeout(Duration.ofSeconds(300));
//        builder.withArgument(GeneralServerFlag.BASEPATH, "/wd/hub/");
//        builder.withArgument(GeneralServerFlag.SESSION_OVERRIDE);
//        builder.withArgument(GeneralServerFlag.LOG_LEVEL, "error");
//        builder.build();
//        service = AppiumDriverLocalService.buildService(builder);
//        service.start();

        try {

            // Set the healenium which use the selenium hud and appium node running at port 4723

            String nodeURL = "http://192.168.1.2:8085";
            MutableCapabilities caps = getDesiredCapabilities();

            // Initialize the driver using local appium
//            driver = new AppiumDriver(service.getUrl(), caps);

            // Initialize the driver using healenium appium
            driver = new AppiumDriver(new URL(nodeURL), caps);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static MutableCapabilities getDesiredCapabilities() {
        MutableCapabilities caps = new MutableCapabilities();
//        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("appium:platformName", "android");
        caps.setCapability("appium:deviceName", "emulator-5554");
        caps.setCapability("appium:platformVersion", "13");
        caps.setCapability("appium:automationName", "UiAutomator2");
        caps.setCapability("appium:fullReset", true);
        caps.setCapability("appium:app", "/Users/mugazp/Downloads/heleniumTest/apks/app_new.apk");
        caps.setCapability("appium:appPackage", "com.example.myquizapp");
        caps.setCapability("appium:appActivity", ".MainActivity");
        caps.setCapability("appium:nativeWebScreenshot",true);
        return caps;
    }

    @Test
    public void sampleTest() throws InterruptedException {
        String welcomeText = driver.findElement(By.id("old_description")).getText();
        System.out.println("Welcome Text: "+ welcomeText);
        driver.findElement(By.id("old_name")).sendKeys("Tester");
        driver.findElement(By.id("btn_start")).click();
        Thread.sleep(2000);
    }

    @After
    public void teardown() {
        if (driver != null) {
            driver.quit();
//            service.stop();
        }
    }

}