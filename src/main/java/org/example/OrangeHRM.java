package org.example;

// run test in webkit - Login Test

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class OrangeHRM {

    Browser browser;
    Page page;


    @BeforeMethod
    public void setup() {
        Playwright playwright = Playwright.create();
        browser = playwright.webkit().launch(new BrowserType.LaunchOptions().setHeadless(false));
        page = browser.newPage();
        }

        @Test
        public void testLogin () {
            page.navigate("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
            page.locator("//input[@name='username']").fill("admin");
            page.locator("//input[@name='password']").fill("admin123");
            page.locator("//button[@type=\"submit\"]").click();
            page.locator("//span[@class='oxd-userdropdown-tab']").click();
            page.locator("//a[text()='Logout']").click();
        }

        @AfterMethod
        public void tearDown () {
            browser.close();
            page.close();

        }

    }
