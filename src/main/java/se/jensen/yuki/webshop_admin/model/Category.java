package se.jensen.yuki.webshop_admin.model;

import java.util.List;

/**
 * This is an enum that has all categories' type name, type and List<ProductField>.
 * It must be updated when a new category is added. Then ProductFactory will handle the new category as well.
 */
public enum Category {
    CLOTH("cloth", Cloth.class, List.of(ProductField.TITLE, ProductField.PRICE, ProductField.DESCRIPTION, ProductField.SIZE)),
    APPLIANCE("appliance", Appliance.class, List.of(ProductField.TITLE, ProductField.PRICE, ProductField.DESCRIPTION, ProductField.BRAND)),
    BOOK("book", Book.class, List.of(ProductField.TITLE, ProductField.PRICE, ProductField.DESCRIPTION, ProductField.AUTHOR));

    /** a string value of the type name **/
    private final String string;
    /** Class type **/
    private final Class<?> type;
    /** List of ProductField which the class has **/
    private final List<ProductField> fieldList;

    Category(String string, Class<?> type, List<ProductField> fieldList) {
        this.string = string;
        this.type = type;
        this.fieldList = fieldList;
    }

    /**
     * Getter for type.
     *
     * @return Class type.
     */
    public Class<?> getType() {
        return type;
    }

    /**
     * Getter for fieldList.
     *
     * @return fieldList.
     */
    public List<ProductField> getFieldList() {
        return fieldList;
    }

    /**
     * Getter for string.
     *
     * @return string.
     */
    public String getString() {
        return string;
    }

    /**
     * Makes a prompt text to ask user category.
     *
     * @return a prompt text for UI
     */
    public static String makePromptForCategory() {
        StringBuilder sb = new StringBuilder();
        sb.append("Enter product category:\n");
        Category[] categories = Category.values();
        for (int i = 0; i < categories.length; i++) {
            sb.append(i).append(". ").append(categories[i].getString()).append("\n");
        }
        return sb.toString();
    }
}
