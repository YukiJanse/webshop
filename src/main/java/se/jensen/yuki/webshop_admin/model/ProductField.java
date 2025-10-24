package se.jensen.yuki.webshop_admin.model;

import java.util.function.Predicate;

import static se.jensen.yuki.webshop_admin.constants.ErrorMessage.*;

/**
 * This enum has each field's type of the value, name, validation and error message for validation.
 * When a new category is added, it must be updated.
 */
public enum ProductField {
    TITLE(String.class, "title", value -> !((String) value).isBlank() && ((String) value).length() <= 100, INVALID_TITLE),
    PRICE(Double.class, "price", value -> ((double) value) > 0, INVALID_PRICE),
    DESCRIPTION(String.class, "description", value -> !((String) value).isBlank() && ((String) value).length() <= 500, INVALID_DESCRIPTION),
    AUTHOR(String.class, "author", value -> !((String) value).isBlank() && ((String) value).length() <= 100, INVALID_AUTHOR),
    SIZE(String.class, "size",
            value -> !((String) value).isBlank()
                    && (((String) value).equalsIgnoreCase("S"))
                    || (((String) value).equalsIgnoreCase("M"))
                    || (((String) value).equalsIgnoreCase("L")), INVALID_SIZE),
    BRAND(String.class, "brand", value -> !((String) value).isBlank() && ((String) value).length() <= 100, INVALID_BLAND);

    /** type of the field **/
    private final Class<?> type;
    /** name of the field **/
    private final String fieldName;
    /** validation for the value **/
    private final Predicate<Object> validator;
    /** error message for the validation **/
    private final String validateErrorMessage;

    ProductField(Class<?> type, String fieldName, Predicate<Object> validator, String validateErrorMessage) {
        this.type = type;
        this.fieldName = fieldName;
        this.validator = validator;
        this.validateErrorMessage = validateErrorMessage;
    }

    /**
     * Getter for fieldName.
     *
     * @return fieldName
     */
    public String getFieldName() {
        return fieldName;
    }

    /**
     * Getter for validateErrorMessage for validation.
     *
     * @return validateErrorMessage.
     */
    public String getValidateErrorMessage() {
        return validateErrorMessage;
    }

    /**
     * Pareser for the value.
     *
     * @param input is user input from ui.
     * @return parsed value.
     */
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

    /**
     * Validates the value.
     *
     * @param value is a parsed value.
     * @return the result as a boolean.
     */
    public boolean validate(Object value) {
        return validator.test(value);
    }
}
