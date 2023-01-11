package org.example;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.regex.Pattern;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class FormTest {
    Playwright playwright;
    Browser browser;
    Page page;

    @BeforeTest
    public void setup() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(100));
        page = browser.newPage();;

    }

    @Test
    public void CodeGenDemo() {

        page.navigate("https://selectorshub.com/xpath-practice-page/");
        // Click [placeholder="Enter email"]
        page.click("[placeholder=\"Enter email\"]");
        // Fill [placeholder="Enter email"]
        page.fill("[placeholder=\"Enter email\"]", "dragan@test.se");
        // Click [placeholder="Enter Password"]
        page.click("[placeholder=\"Enter Password\"]");
        // Fill [placeholder="Enter Password"]
        page.fill("[placeholder=\"Enter Password\"]", "123456");
        // Click [placeholder="Enter your company"]
        page.click("[placeholder=\"Enter your company\"]");
        // Fill [placeholder="Enter your company"]
        page.fill("[placeholder=\"Enter your company\"]", "test ab");
        // Click text=Submit
        page.waitForNavigation(() -> {
            page.click("text=Submit");
        });
        assertThat(page).hasURL(Pattern.compile(".*https://selectorshub.com/xpath-practice-page/"));

    }

    @AfterTest
    public void tearDown(){
        page.close();
    }
}
