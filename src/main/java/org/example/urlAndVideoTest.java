package org.example;

import com.microsoft.playwright.*;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;


/***
 * a test that navigates to the Playwright website,
 * clicks on different links on the page,
 * and asserts that the correct URLs are loaded.
 * There is also a setup and tear down method for
 * creating a new browser and page instances,
 * and for closing them when the test is finished.
 * The tear down method also creates a folder named "videos" and
 * records a video of the test and saves it in the folder with
 * the test name as the file name.
 */
public class urlAndVideoTest {

    private Browser browser;
    private Page page;
    private BrowserContext context;
    @BeforeMethod
    public void setup() {
        Playwright playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));

        context = browser.newContext(new Browser.NewContextOptions().setRecordVideoDir(Paths.get("videos/")));
        page = context.newPage();
    }

    /***
    * Verify that page-URL is correct
    *
    */
    @Test
    public void testExample() {
        // go to playwright.dev
        page.navigate("https://playwright.dev/");

        // click text = get started
        page.locator("text=Get started").click();
        assertThat(page).hasURL("https://playwright.dev/docs/intro");

        // Click aside >> text= writing tests
        page.locator("aside >> text=Writing Tests").click();
        assertThat(page).hasURL("https://playwright.dev/docs/writing-tests");

        // Click aside >> text=Running Tests
        page.locator("aside >> text=Running Tests").click();
        assertThat(page).hasURL("https://playwright.dev/docs/running-tests");
    }

    
     /***
     * Creates video folder and adds new recordings into the folder
     */

    @AfterMethod
    public void tearDown(ITestResult result) {
        String pathProject = System.getProperty("user.dir");
        String testName = result.getMethod().getMethodName();
        Path videoName = page.video().path().getFileName();
        System.out.println(videoName);
        browser.close();
        context.close();
        page.close();

        File file1 = new File(pathProject + File.separator + "videos" + File.separator + videoName);
        File file2 = new File(pathProject + File.separator + "videos" + File.separator + testName + ".webm");
        boolean status = file1.renameTo(file2);
        System.out.println(status);
    }
}
