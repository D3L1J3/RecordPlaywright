package org.example;

import com.microsoft.playwright.*;

import java.io.File;
import java.nio.file.Paths;
import java.util.Arrays;

public class DownloadFile {

    public static void main(String[] args) {

        try(Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
            Page page = browser.newPage();
            page.navigate("https://www.selenium.dev/downloads/");

            Download download = page.waitForDownload(() -> {
                page.locator("//p[contains(text(), 'Latest stable version')]/a").click();
            });

            System.out.print(download.path());

            String path = System.getProperty("user.dir");
            download.saveAs(Paths.get(path + File.separator + "Selenium.jar"));
        }
    }
}
