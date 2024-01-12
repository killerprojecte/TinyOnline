package dev.rgbmc.tinyonline.games;

import com.microsoft.playwright.Page;
import dev.rgbmc.tinyonline.TinyOnline;
import dev.rgbmc.tinyonline.utils.ArrowKey;
import dev.rgbmc.tinyonline.utils.ResizeUtils;

import java.awt.image.BufferedImage;

public abstract class Game {
    private final String url;
    private final int height;
    private final int width;
    private final int resizeHeight;
    private final int resizeWidth;
    private Page page;

    public Game(String url, int height, int width, int resizeHeight, int resizeWidth) {
        this.url = url;
        this.height = height;
        this.width = width;
        this.resizeHeight = resizeHeight;
        this.resizeWidth = resizeWidth;
    }

    public void startGame() {
        page = TinyOnline.newPage(height, width);
        page.navigate(url);
    }

    public BufferedImage getCurrentScreen() {
        try {
            return ResizeUtils.resize(page.screenshot(), resizeWidth, resizeHeight);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void stopGame() {
        onGameStop(page);
        page.close();
    }

    public abstract void onGameStop(Page page);

    public void pressKey(ArrowKey arrow) {
        pressKey(arrow.getKey());
    }

    public void pressKey(String keyName) {
        page.keyboard().press(keyName);
    }

    public void downKey(ArrowKey arrow) {
        downKey(arrow.getKey());
    }

    public void downKey(String keyName) {
        page.keyboard().down(keyName);
    }

    public void upKey(ArrowKey arrow) {
        upKey(arrow.getKey());
    }

    public void upKey(String keyName) {
        page.keyboard().up(keyName);
    }
}
