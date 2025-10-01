package se.jensen.yuki.webshop_admin.service;

import se.jensen.yuki.webshop_admin.model.Product;
import se.jensen.yuki.webshop_admin.repository.ProductRepository;

import java.util.List;

public class ProductManagementService {
    private ProductRepository productRepository;

    /**
     * Set the reference of the productList
     *
     * @param productRepository
     */
    public ProductManagementService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ProductRepository getProductRepository() {
        return productRepository;
    }

    public void addProduct(Product product) {
        System.out.println("ProductManagementService.addProduct()");
        //productRepository.add();

    }

    public List<Product> showAllProduct() {
        System.out.println("ProductManagementService.showAllProduct()");
        return productRepository.getProductList();
    }

    public void showInformationOfProduct(String productName) {
        System.out.println("ProductManagementService.showInformationOfProduct()");

    }
}
