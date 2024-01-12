package dev.rgbmc.tinyonline;


import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class TinyOnline {
    private static Playwright playwright = null;
    private static Browser browser;
    private static BrowserContext context;

    public static Page newPage(int height, int width) {
        if (playwright == null) {
            System.out.println("Setting Environment in first time start the page");
            initEnvironment();
        }
        Page page = context.newPage();
        page.setViewportSize(width, height);
        return page;
    }

    private static void initEnvironment() {
        //Map<String, String> settings = new HashMap<>();
        //settings.put("PLAYWRIGHT_DOWNLOAD_HOST", "https://registry.npmmirror.com/-/binary/playwright/");
        playwright = Playwright.create();
        browser = playwright.chromium().launch();
        context = browser.newContext();
    }

    public static void stopEnvironment() {
        final Playwright playwright1 = playwright;
        playwright = null;
        browser.close();
        playwright1.close();
    }

    public static boolean isBrowserOpen() {
        return playwright != null;
    }
}
