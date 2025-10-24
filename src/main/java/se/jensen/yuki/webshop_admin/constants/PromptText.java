package se.jensen.yuki.webshop_admin.constants;

import se.jensen.yuki.webshop_admin.model.Category;

public class PromptText {
    public static final String START_MENU = """
            ***** Welcome to Webshop Admin *****
            1. Login
            2. Quit
            
            Enter from 1 - 2
            """;
    public static final String MAIN_MENU = """
            ***** Web Shop Admin Menu *****
            1. Add a product
            2. Show all products
            3. Show the information of a product
            4. Quit Menu
            
            Enter from 1 - 4
            """;
    public static final String LOGIN_USERNAME_PROMPT = "Enter username";
    public static final String LOGIN_PASSWORD_PROMPT = "Enter password";
    public static final String LOGIN_SUCCESS = "Logged in successfully";
    public static final String PRODUCT_CATEGORY_PROMPT = Category.makePromptForCategory();
    public static final String ADD_PRODUCT_PROMPT = "Enter product ";
    public static final String PRODUCT_INFORMATION_PROMPT = "Enter product's article number";
    public static final String QUIT_MENU = """
            Do you want to quit application?
            1. Yes
            2. No
            
            Enter 1 or 2
            """;
    public static String SUCCESSEd_ADD_PRODUCT = "Added a product successfully.";
}
