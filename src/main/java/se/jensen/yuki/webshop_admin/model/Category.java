package se.jensen.yuki.webshop_admin.model;

import java.util.List;

public enum Category {
    CLOTH("Cloth", Cloth.class, List.of(ProductField.TITLE, ProductField.PRICE, ProductField.DESCRIPTION, ProductField.SIZE)),
    APPLIANCE("Appliance", Appliance.class, List.of(ProductField.TITLE, ProductField.PRICE, ProductField.DESCRIPTION, ProductField.BRAND)),
    BOOK("Book", Book.class, List.of(ProductField.TITLE, ProductField.PRICE, ProductField.DESCRIPTION, ProductField.AUTHOR));

    private final String string;
    private final Class<?> type;
    private final List<ProductField> fieldList;

    Category(String string, Class<?> type, List<ProductField> fieldList) {
        this.string = string;
        this.type = type;
        this.fieldList = fieldList;
    }

    public Class<?> getType() {
        return type;
    }

    public List<ProductField> getFieldList() {
        return fieldList;
    }

    public String getString() {
        return string;
    }

    public static String makePromptForCategory() {
        StringBuilder sb = new StringBuilder();
        sb.append("Enter product category:\n");
        Category[] categories = Category.values();
        for (int i = 0; i < categories.length; i++) {
            sb.append(i + ". " + categories[i].getString() + "\n");
        }
        return sb.toString();
    }
}
