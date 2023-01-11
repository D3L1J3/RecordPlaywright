package org.example;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertEquals;

public class Test1 {

    @Test
    public void verifyFirstProgram() {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        Page page = browser.newPage();
        page.navigate("https://playwright.dev/");
        assertEquals("Fast and reliable end-to-end testing for modern web apps | Playwright", page.title());

    }
}
