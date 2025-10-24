package se.jensen.yuki.webshop_admin.controller;

import se.jensen.yuki.webshop_admin.constants.ErrorMessage;
import se.jensen.yuki.webshop_admin.constants.PromptText;
import se.jensen.yuki.webshop_admin.factory.ProductFactory;
import se.jensen.yuki.webshop_admin.model.Category;
import se.jensen.yuki.webshop_admin.model.Product;
import se.jensen.yuki.webshop_admin.model.ProductField;
import se.jensen.yuki.webshop_admin.service.AuthService;
import se.jensen.yuki.webshop_admin.service.ProductManagementService;
import se.jensen.yuki.webshop_admin.ui.ConsoleUI;
import se.jensen.yuki.webshop_admin.ui.DialogUi;
import se.jensen.yuki.webshop_admin.ui.Ui;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class WebshopAdminController {
    private static final String START_MENU_LOGIN = "1";
    private static final String START_MENU_QUIT = "2";
    private static final String MAIN_MENU_ADD_PRODUCT = "1";
    private static final String MAIN_MENU_SHOW_ALL_PRODUCT = "2";
    private static final String MAIN_MENU_SHOW_PRODUCT_INFO = "3";
    private static final String MAIN_MENU_QUIT = "4";
    private static final String QUIT_MENU_YES = "1";
    private final AuthService authService;
    private final ProductManagementService productManagementService;

    public WebshopAdminController(AuthService authService, ProductManagementService productManagementService) {
        this.authService = authService;
        this.productManagementService = productManagementService;
    }


    /**
     * Run all logic for the web shop admin app
     */
    public void run(String uiOption) {
        boolean isRunnning = true;
        Scanner scanner = new Scanner(System.in);
        Ui ui = switch (uiOption) {
            case "dialog" -> new DialogUi();
            default -> new ConsoleUI(scanner);
        };
        while (isRunnning) {
            String choice = ui.startMenu();
            if (choice == null) {
                choice = ui.prompt(PromptText.QUIT_MENU);
                if (choice != null && choice.equals(QUIT_MENU_YES)) {
                    isRunnning = false;
                }
            } else if (choice.equals(START_MENU_LOGIN)) {
                // Login
                login(ui);
                // Start the main menu
                mainMenu(ui);
                // Logout
                logout();
            } else if (choice.equals(START_MENU_QUIT)) {
                isRunnning = false;
            } else {
                ui.info(ErrorMessage.START_MENU_CHOICE);
            }
        }
        scanner.close();
    }

    /**
     * Asks the user product category to add.
     *
     * @param ui is to ask user and showing information.
     * @return Category
     */
    private Category askCategory(Ui ui) {
        String input = "";
        Category category = null;
        boolean isAsking = true;
        while (isAsking) {
            input = ui.prompt(PromptText.PRODUCT_CATEGORY_PROMPT);
            try {
                // If the user input a bigger number than Category.values() or not a number
                // it sends an exception
                category = Category.values()[Integer.parseInt(input)];
                isAsking = false;
            } catch (Exception e) {
                ui.info(ErrorMessage.PRODUCT_CATEGORY);
            }
        }
        return category;
    }

    /**
     * Asks user product info and returns a Map with ProductField and values.
     *
     * @param productFieldList is to create a Product-instans dynamically.
     * @param ui               is to ask user product info.
     * @return a Map with ProductField and values.
     */
    private Map<ProductField, String> inputProductInfo(List<ProductField> productFieldList, Ui ui) {
        Map<ProductField, String> input = new HashMap<>();
        for (ProductField field : productFieldList) {
            boolean isNotValid = true;
            String userInput = "";
            while (isNotValid) {
                try {
                    userInput = ui.prompt(PromptText.ADD_PRODUCT_PROMPT + field.getFieldName());
                    // parseValue checks whether the value is convertable to the correct type
                    // validate() checks mostly the range of the value
                    isNotValid = !field.validate(field.parseValue(userInput));
                    if (isNotValid) {
                        ui.info(ErrorMessage.INVALID_PRODUCT_VALUE + field.getValidateErrorMessage());
                    }
                } catch (IllegalArgumentException e) {
                    ui.info(ErrorMessage.INVALID_PRODUCT_VALUE + field.getValidateErrorMessage());
                }
            }
            input.put(field, userInput);
        }
        return input;
    }

    /**
     * Asks login info and Authenticates that.
     *
     * @param ui is to ask user login info.
     */
    private void login(Ui ui) {
        boolean isLoggedIn = false;
        // Login logic
        while (!authService.isLoggedIn()) {
            try {
                String username = ui.prompt(PromptText.LOGIN_USERNAME_PROMPT);
                String password = ui.prompt(PromptText.LOGIN_PASSWORD_PROMPT);
                // Authenticate user
                if (authService.login(username, password)) {
                    ui.info(PromptText.LOGIN_SUCCESS);
                } else {
                    ui.info(ErrorMessage.LOGIN_FAIL);
                }
            } catch (NullPointerException e) {
                ui.info(ErrorMessage.NO_USER_LIST);
            }
        }
    }

    /**
     * Logouts current user.
     */
    private void logout() {
        authService.logout();
    }

    /**
     * Shows mainMenu and do what user chose
     *
     * @param ui is to ask user
     */
    private void mainMenu(Ui ui) {
        boolean isUserOnMenu = true;
        while (isUserOnMenu) {
            String choice = ui.menu();
            switch (choice) {
                case MAIN_MENU_ADD_PRODUCT -> {
                    try {
                        Category category = askCategory(ui);
                        Product targetProduct = (Product) ProductFactory.createProduct(category, inputProductInfo(category.getFieldList(), ui));
                        productManagementService.addProduct(targetProduct);
                        ui.info(PromptText.SUCCESSEd_ADD_PRODUCT);
                    } catch (RuntimeException e) {
                        ui.info(ErrorMessage.FAILED_ADD_PRODUCT + e.getMessage());
                    }
                } // Add a new product
                case MAIN_MENU_SHOW_ALL_PRODUCT ->
                        ui.info(productManagementService.showAllProduct()); // Show all products
                case MAIN_MENU_SHOW_PRODUCT_INFO ->
                        ui.info(productManagementService.showInformationOfProduct(ui.prompt(PromptText.PRODUCT_INFORMATION_PROMPT))); // Show the information of a product
                case MAIN_MENU_QUIT -> isUserOnMenu = false; // Quit Menu
                case null -> {
                    choice = ui.prompt(PromptText.QUIT_MENU);
                    if (choice.equals(QUIT_MENU_YES)) {
                        isUserOnMenu = false;
                    }
                }
                default -> ui.info(ErrorMessage.MENU_CHOICE);
            }
        }
    }
}
