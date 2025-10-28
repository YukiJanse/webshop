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

    public static void main(String[] args) {
        String uiOption = "console";
        String daoOption = "file";
        if (args.length == 2) {
            uiOption = args[0].toLowerCase();
            daoOption = args[1].toLowerCase();
        } else {
            System.out.println("Application runs as default mode. If you want set options, it must be [UiOption DaoOption]");
        }
        try {
            WebshopAdminController controller = new WebshopAdminController(
                    new AuthService(new UserRepository(daoOption.equals("file") ? new UserFileDao() : new UserDbDao())),
                    new ProductManagementService(new ProductRepository(
                            daoOption.equals("file") ? new ProductFileDao() : new ProductDbDao()
                    ))
            );
            controller.run(uiOption);
        } catch (RuntimeException e) {
            System.out.println("Application startup failed: " + e.getMessage());
        }

        System.out.println("Application is closing...");
    }
}
