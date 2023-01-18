package org.example;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertEquals;

/***
 * This code is a test suite written in Java using the Playwright tool.
 * It tests a form on the website "https://selectorshub.com/xpath-practice-page/".
 * The test opens a browser, navigates to the website,
 * fills out the form with specific data, clicks the submit button,
 * and asserts that the validation message displayed on the page is correct.
 * The test also includes a method for filling out the form and a setup and
 * tear down method for launching and closing the browser.
 */
public class FormTest {
    Playwright playwright;
    Browser browser;
    Page page;

    @BeforeTest
    public void setup() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(100));
        page = browser.newPage();

    }

    @Test
    public void validateFormMessage() {
        page.navigate("https://selectorshub.com/xpath-practice-page/");
        // call customerTest1
        fillFormData("dragan.test.se", "123456test", "testing ab", 123456789L);
        // Click text=Submit
        page.click("text=Submit");

        String validationMessage = (String) page.evaluate("document.querySelector('#userId').validationMessage");
        assertEquals("Please include an '@' in the email address. 'dragan.test.se' is missing an '@'.", validationMessage);

    }

    public void fillFormData(String email, String password, String company, long mobileNumber){
        // Click [placeholder="Enter email"]
        page.click("[placeholder=\"Enter email\"]");
        // Fill [placeholder="Enter email"]
        page.fill("[placeholder=\"Enter email\"]", email);
        // Click [placeholder="Enter Password"]
        page.click("[placeholder=\"Enter Password\"]");
        // Fill [placeholder="Enter Password"]
        page.fill("[placeholder=\"Enter Password\"]", password);
        // Click [placeholder="Enter your company"]
        page.click("[placeholder=\"Enter your company\"]");
        // Fill [placeholder="Enter your company"]
        page.fill("[placeholder=\"Enter your company\"]", company);
        // Click [placeholder="Enter your mobile number"]
        page.click("[placeholder=\"Enter your mobile number\"]");
        // Fill [placeholder="Enter your mobile number"]
        page.fill("[placeholder=\"Enter your mobile number\"]", String.valueOf(mobileNumber));
    }


    @AfterTest
    public void tearDown(){
        page.close();
    }
}
