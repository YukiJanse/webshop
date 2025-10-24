package se.jensen.yuki.webshop_admin.factory;

import se.jensen.yuki.webshop_admin.model.Category;
import se.jensen.yuki.webshop_admin.model.Product;
import se.jensen.yuki.webshop_admin.model.ProductField;

import java.lang.reflect.Field;
import java.util.Map;

public class ProductFactory {
    /**
     * Factory method to make a product object from user input.
     * It depends on what enum Category has in the productFieldList.
     * No update is required when adding a new category.
     *
     * @param category  is the category the user chose.
     * @param userInput is the input from the user.
     * @return Product object
     */
    public static Object createProduct(Category category, Map<ProductField, String> userInput) {
        try {
            Object instance = category.getType().getDeclaredConstructor().newInstance();
            // Set values in the instance
            for (ProductField field : category.getFieldList()) {
                String value = userInput.get(field);

                Field classField = findField(category.getType(), field.getFieldName());
                classField.setAccessible(true);

                classField.set(instance, field.parseValue(value));
            }
            // Set articleNumber and update the counter of Product class
            ((Product) instance).initArticleNumberIfNull();
            return instance;
        } catch (Exception e) {
            // Check the validation and the fieldList of the enum class
            throw new RuntimeException("Failed creating a Product instance: " + e.getMessage());
        }
    }

    /**
     * Returns a field object.
     *
     * @param classType is the class that has the field.
     * @param fieldName is the field name.
     * @return Field object
     * @throws NoSuchFieldException
     */
    private static Field findField(Class<?> classType, String fieldName) throws NoSuchFieldException {
        while (classType != null) {
            try {
                return classType.getDeclaredField(fieldName);
            } catch (NoSuchFieldException e) {
                classType = classType.getSuperclass();
            }
        }
        throw new NoSuchFieldException(fieldName);
    }
}
