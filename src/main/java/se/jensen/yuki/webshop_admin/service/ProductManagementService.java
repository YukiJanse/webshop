package se.jensen.yuki.webshop_admin.service;

import se.jensen.yuki.webshop_admin.model.ErrorMessage;
import se.jensen.yuki.webshop_admin.model.Product;
import se.jensen.yuki.webshop_admin.repository.ProductRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductManagementService {
    private final ProductRepository productRepository;

    /**
     * Set the reference of the productList
     *
     * @param productRepository is the repository for Product
     */
    public ProductManagementService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /**
     * @return
     */
    public ProductRepository getProductRepository() {
        return productRepository;
    }

    public void addProduct(Product product) {
        productRepository.addProduct(product);
    }

    public List<String> showAllProduct() {
        List<String> stringList = new ArrayList<>();
        List<Product> productList = productRepository.getProductList();
        for (Product product : productList) {
            stringList.add(product.toString());
        }

        return stringList;
    }

    public String showInformationOfProduct(String articleNumber) {
        Optional<Product> optionalProduct = productRepository.findByArticleNumber(articleNumber);

        return optionalProduct
                .map(Object::toString)
                .orElse(ErrorMessage.NO_SUCH_PRODUCT);

    }
}
