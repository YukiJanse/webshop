package se.jensen.yuki.webshop_admin;

import se.jensen.yuki.webshop_admin.controller.WebshopAdminController;

public class Main {
    public static void main(String[] args) {
        WebshopAdminController controller = new WebshopAdminController();

        controller.run();

        System.out.println("Application is closing...");
    }
}
