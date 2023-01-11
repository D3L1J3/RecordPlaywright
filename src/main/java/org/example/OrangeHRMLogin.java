package org.example;

import com.microsoft.playwright.*;
import com.microsoft.playwright.Page.ScreenshotOptions;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.nio.file.Paths;

// Run all
public class OrangeHRMLogin {

    Browser browser;
    Page page;
    Playwright playwright;

    @BeforeMethod
    public void setup() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        page = browser.newPage();
    }

    @Test
    public void testLogin () throws InterruptedException {
        page.navigate("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");

        page.locator("//input[@name='username']").fill("admin");
        // Take screenshot of the field
        page.locator("//input[@name='username']").screenshot(new Locator.ScreenshotOptions().setPath(Paths.get("UserInput.png")));

        page.locator("//input[@name='password']").fill("admin123");


        // page.locator("//button[@type=\"submit\"]").click(); // comment out to fail test
        // and get screenshot from tearDown

        // Take screenshot of entire page
        page.screenshot(new ScreenshotOptions().setPath(Paths.get("Dashboard.png")));
        page.screenshot(new ScreenshotOptions().setPath(Paths.get("FullDashboard.png")).setFullPage(true));
        page.locator("//span[@class='oxd-userdropdown-tab']").click();
        page.locator("//a[text()='Logout']").click();
    }

    @AfterMethod
    public void tearDown (ITestResult result) {
        if (result.getStatus() != 1)
            page.screenshot(new ScreenshotOptions().setPath(Paths.get("FailedTest.png")).setFullPage(true));

        // if (result.getStatus() == Result)
        browser.close();
        page.close();
        playwright.close();
    }

}
