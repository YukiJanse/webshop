package se.jensen.yuki.webshop_admin.model;

public enum Category {
    CLOTH("Cloth"),
    APPLIANCE("Appliance"),
    BOOK("Book");

    private final String string;

    Category(String string) {
        this.string = string;
    }

    public String getString() {
        return string;
    }
}
