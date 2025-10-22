package se.jensen.yuki.webshop_admin.factory;

import se.jensen.yuki.webshop_admin.model.Category;
import se.jensen.yuki.webshop_admin.model.Product;
import se.jensen.yuki.webshop_admin.model.ProductField;

import java.lang.reflect.Field;
import java.util.Map;

public class ProductFactory {
    public static Object createProduct(Category category, Map<ProductField, String> userInput) {
        try {
            Object instance = category.getType().getDeclaredConstructor().newInstance();

            for (ProductField field : category.getFieldList()) {
                String value = userInput.get(field);

                Field classField = findField(category.getType(), field.getFieldName());
                classField.setAccessible(true);
//                System.out.printf("Setting field %s (type=%s) with value %s (class=%s)%n",
//                        classField.getName(),
//                        classField.getType().getSimpleName(),
//                        value,
//                        value.getClass().getSimpleName());

                classField.set(instance, field.parseValue(value));
            }
            ((Product) instance).initArticleNumberIfNull();
            return instance;
        } catch (Exception e) {
            // Check the validation and the fieldList of the enum class
            throw new RuntimeException("Failed creating a Product instance: " + e.getMessage());
        }
    }

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
