package se.jensen.yuki.webshop_admin.controller;

import se.jensen.yuki.webshop_admin.model.*;
import se.jensen.yuki.webshop_admin.repository.ProductRepository;
import se.jensen.yuki.webshop_admin.repository.UserRepository;
import se.jensen.yuki.webshop_admin.service.AuthService;
import se.jensen.yuki.webshop_admin.service.ProductManagementService;
import se.jensen.yuki.webshop_admin.ui.ConsoleUI;
import se.jensen.yuki.webshop_admin.ui.Ui;
import se.jensen.yuki.webshop_admin.util.JsonFileReader;
import se.jensen.yuki.webshop_admin.util.JsonFileWriter;

import java.util.ArrayList;
import java.util.Scanner;

import static se.jensen.yuki.webshop_admin.model.FilePath.PRODUCT_JSON_FILE_PATH;
import static se.jensen.yuki.webshop_admin.model.FilePath.USER_JSON_FILE_PATH;

public class WebshopAdminController {
    private final AuthService authService;
    private final ProductManagementService productManagementService;

    public WebshopAdminController() {
        AuthService tempAuthService;
        ProductManagementService tempProductManagerService;
        try {
            tempAuthService = new AuthService(loadUserRepository());
            tempProductManagerService = new ProductManagementService(loadProductRepository());
        } catch (RuntimeException e) {
            e.printStackTrace();
            tempAuthService = new AuthService(new UserRepository(new ArrayList<User>()));
            tempProductManagerService = new ProductManagementService(new ProductRepository(new ArrayList<Product>()));
        }
        authService = tempAuthService;
        productManagementService = tempProductManagerService;
    }


    /**
     * Run all logic for the web shop admin app
     */
    public void run() {
        boolean isRunnning = true;
        Scanner scanner = new Scanner(System.in);
        Ui ui = new ConsoleUI(scanner);
        System.out.println(productManagementService.getProductRepository().getProductList());
        saveRepository();
        while (isRunnning) {
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
                    /** DO SOMETHING TO QUIT THE APP **/
                }
            }
            boolean isUserOnMenu = true;
            while (isUserOnMenu) {
                String choice = ui.menu();
                switch (choice) {
                    case "1" -> productManagementService.addProduct(new Book()); // Add a new product
                    case "2" -> productManagementService.showAllProduct(); // Show all products
                    case "3" ->
                            productManagementService.showInformationOfProduct("new Book()"); // Show the information of a product
                    case "4" -> isUserOnMenu = false; // Quit Menu
                    default -> ui.info(ErrorMessage.MENU_CHOICE);
                }
            }
            authService.logout();
        }
        scanner.close();
    }

    /**
     * Fills up User data in UserRepository
     */
    private UserRepository loadUserRepository() {
/*
        return new UserRepository(
                List.of(
                        new User("Yuki", "pass"),
                        new User("Håkan", "pass"),
                        new User("Emil", "pass")
                )
        );*/
        return new UserRepository(JsonFileReader.readJson(FilePath.USER_JSON_FILE_PATH, User.class));
    }

    private ProductRepository loadProductRepository() {
        /*return new ProductRepository(
                List.of(
                        new Cloth("Hat", 1000, 10),
                        new Appliance("Fridge", 2000, 5),
                        new Book("Dictionary", 500, 20)
                )
        );*/
        return new ProductRepository(JsonFileReader.readJson(FilePath.PRODUCT_JSON_FILE_PATH, Product.class));
    }

    private void saveRepository() {
        JsonFileWriter.writeJsonFile(USER_JSON_FILE_PATH, authService.getUserRepository().getUserList());
        JsonFileWriter.writeJsonFile(PRODUCT_JSON_FILE_PATH, productManagementService.getProductRepository().getProductList(), Product.class);
    }
}
