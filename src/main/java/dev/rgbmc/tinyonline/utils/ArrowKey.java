package dev.rgbmc.tinyonline.utils;

public enum ArrowKey {
    UP("ArrowUp"),
    LEFT("ArrowLeft"),
    RIGHT("ArrowRight"),
    DOWN("ArrowDown");


    private final String key;

    ArrowKey(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }
}
