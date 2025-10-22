package se.jensen.yuki.webshop_admin;

import se.jensen.yuki.webshop_admin.controller.WebshopAdminController;
import se.jensen.yuki.webshop_admin.dao.ProductDbDao;
import se.jensen.yuki.webshop_admin.dao.ProductFileDao;
import se.jensen.yuki.webshop_admin.dao.UserDbDao;
import se.jensen.yuki.webshop_admin.dao.UserFileDao;
import se.jensen.yuki.webshop_admin.repository.ProductRepository;
import se.jensen.yuki.webshop_admin.repository.UserRepository;
import se.jensen.yuki.webshop_admin.service.AuthService;
import se.jensen.yuki.webshop_admin.service.ProductManagementService;

public class Main {
    private static final String PRODUCT_DB_URL = "jdbc:mysql://localhost:3306/product_db";
    private static final String PRODUCT_DB_USER = "admin";
    private static final String PRODUCT_DB_PASS = "pass";
    private static final String USER_DB_URL = "jdbc:mysql://localhost:3306/product_db";
    private static final String USER_DB_USER = "admin";
    private static final String USER_DB_PASS = "pass";

    public static void main(String[] args) {
        String uiOption = "console";
        String daoOption = "file";
        if (args.length > 0) {
            uiOption = args[0].toLowerCase();
            daoOption = args[1].toLowerCase();
        }
        System.out.println("UI option: " + uiOption + "\nDAO option: " + daoOption);
        WebshopAdminController controller = new WebshopAdminController(
                new AuthService(new UserRepository(daoOption.equals("file") ? new UserFileDao() : new UserDbDao(USER_DB_URL, USER_DB_USER, USER_DB_PASS))),
                new ProductManagementService(new ProductRepository(
                        daoOption.equals("file") ? new ProductFileDao() : new ProductDbDao(PRODUCT_DB_URL, PRODUCT_DB_USER, PRODUCT_DB_PASS)
                ))
        );
        controller.run(uiOption);

        System.out.println("Application is closing...");
    }
}
