package com.solvd.testautomation.web.components;

public enum ToyCategories {

    PLUSH ("//a[contains(@class, 'mega-menu__linklist-link') and normalize-space()='%s']"),

    MASCOTS ("//a[contains(@class, 'mega-menu__linklist-link') and normalize-space()='%s']"),

    CUSHIONS ("//a[contains(@class, 'mega-menu__linklist-link') and normalize-space()='%s']"),

    COLLECTIBLES ("//a[contains(@class, 'mega-menu__linklist-link') and normalize-space()='%s']"),

    GAMES ("//a[contains(@class, 'mega-menu__linklist-link') and normalize-space()='%s']"),

    TOYS ("//a[contains(@class, 'mega-menu__linklist-link') and normalize-space()='%s']");

    private final String xpathPattern;

    ToyCategories (String xpathPattern) {
        this.xpathPattern = xpathPattern;
    }

    public String getXpath(String value) {
        return String.format(xpathPattern, value);
    }

    public String getDisplayName() {
        return name().toLowerCase();
    }
}
