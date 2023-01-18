package org.example;

import com.microsoft.playwright.*;
import com.microsoft.playwright.Page.ScreenshotOptions;
import com.microsoft.playwright.options.LoadState;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.nio.file.Paths;

/***
 * This code is a test suite for logging in to the OrangeHRM website
 * using the Playwright tool. The test navigates to the login page,
 * fills in the username and password fields, clicks the login button,
 * and logs out. It also takes screenshots of the username field,
 * the dashboard page, and the entire page, as well as a screenshot
 * of the failed test if the test does not pass. The test also waits
 * for the page to fully load before taking the screenshots.
 * The test also closes the browser, page, and playwright instance
 * after the test is finished.
 */
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


        page.locator("//button[@type=\"submit\"]").click(); // comment out to fail test
        // and get screenshot from tearDown

        page.waitForLoadState(LoadState.NETWORKIDLE); // wait for page to fully load
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
