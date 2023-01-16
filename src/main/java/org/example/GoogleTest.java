package org.example;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.LoadState;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.regex.Pattern;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.testng.AssertJUnit.*;

/***
 * This code is a test suite for the Google website using the Playwright tool in Java language
 * The first test, test1_navigateToGoogle, asserts that the title of the page is "Google".
 * -------------------------------------
 * The second test, test2_searchForPlaywright, enters "Playwright" in the search bar,
 * and asserts that the title of the search results page is "Playwright - Google Search".
 * -------------------------------------
 * The third test, test3_clickFirstResult, enters "Playwright" in the search bar,
 * clicks on the first result, and asserts that the title of the resulting page
 * is "Fast and reliable end-to-end testing for modern web apps | Playwright".
 * -------------------------------------
 * The fourth test, test4ClickGoogleImages, clicks on the "Images" tab,
 * and asserts that the URL of the page contains "imghp".
 * -------------------------------------
 * The fifth test, test5ClickGoogleApps, clicks on the "Apps" button,
 * and asserts that the iFrame element that is initially hidden
 * becomes visible after the specified element is clicked.
 */

public class GoogleTest {

    private Playwright playwright;
    private Browser browser;
    private Page page;

    @BeforeTest
    public void setup() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        page = browser.newPage();
        page.waitForLoadState(LoadState.LOAD);
    }

    @Test
    public void test1_navigateToGoogle() {
        page.navigate("https://www.google.com/");
        assertThat(page).hasTitle("Google");
    }

    @Test
    public void test2_searchForPlaywright() {
        page.navigate("https://www.google.com/");
        page.waitForSelector(".QS5gu.sy4vM");
        page.click(".QS5gu.sy4vM");
        page.fill("input[name='q']", "Playwright");
        page.press("input[name='q']", "Enter");
        assertThat(page).hasTitle("Playwright - Google Search");
    }

    @Test
    public void test3_clickFirstResult() {
        page.navigate("https://www.google.com/");
        page.fill("input[name='q']", "Playwright");
        page.press("input[name='q']", "Enter");
        page.click("h3.LC20lb.MBeuO.DKV0Md");
        assertThat(page).hasTitle("Fast and reliable end-to-end testing for modern web apps | Playwright");
    }


    @Test
    public void test4ClickGoogleImages() {
        page.navigate("https://www.google.com");
        page.click("a.gb_m[data-pid='2']");
        assertThat(page).hasURL(Pattern.compile(".*imghp.*"));
    }

    @Test
    public void test5ClickGoogleApps() {
        page.navigate("https://www.google.com");
        page.click(".gb_Ze");
        page.waitForLoadState(LoadState.NETWORKIDLE);
        String visibility = page.getAttribute("iframe[name='app']", "style");
        assertFalse(visibility.contains("visibility: hidden;"));
    }
}
