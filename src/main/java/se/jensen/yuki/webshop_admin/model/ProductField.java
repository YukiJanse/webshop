package se.jensen.yuki.webshop_admin.model;

import java.util.function.Predicate;

import static se.jensen.yuki.webshop_admin.model.ErrorMessage.*;

public enum ProductField {
    TITLE(String.class, "title", value -> !((String) value).isBlank() && ((String) value).length() <= 30, INVALID_TITLE),
    PRICE(Integer.class, "price", value -> ((int) value) > 0, INVALID_PRICE),
    DESCRIPTION(String.class, "description", value -> !((String) value).isBlank() && ((String) value).length() <= 500, INVALID_DESCRIPTION),
    AUTHOR(String.class, "author", value -> !((String) value).isBlank() && ((String) value).length() <= 30, INVALID_AUTHOR),
    SIZE(String.class, "size",
            value -> !((String) value).isBlank()
                    && (((String) value).equalsIgnoreCase("S"))
                    || (((String) value).equalsIgnoreCase("M"))
                    || (((String) value).equalsIgnoreCase("L")), INVALID_SIZE),
    BRAND(String.class, "brand", value -> !((String) value).isBlank() && ((String) value).length() <= 30, INVALID_BLAND);

    private final Class<?> type;
    private final String fieldName;
    private final Predicate<Object> validator;
    private final String validateErrorMessage;

    ProductField(Class<?> type, String fieldName, Predicate<Object> validator, String validateErrorMessage) {
        this.type = type;
        this.fieldName = fieldName;
        this.validator = validator;
        this.validateErrorMessage = validateErrorMessage;
    }


    public <T> T createInstance() {
        try {
            return (T) type.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String getFieldName() {
        return fieldName;
    }

    public String getValidateErrorMessage() {
        return validateErrorMessage;
    }

    public Object parseValue(String input) {
        try {
            if (type == Integer.class) {
                return Integer.parseInt(input);
            } else if (type == Double.class) {
                return Double.parseDouble(input);
            } else if (type == Boolean.class) {
                return Boolean.parseBoolean(input);
            }
            return input;
        } catch (Exception e) {
            throw new IllegalArgumentException("Wrong format input");
        }
    }

    public boolean validate(Object value) {
        return validator.test(value);
    }
}
