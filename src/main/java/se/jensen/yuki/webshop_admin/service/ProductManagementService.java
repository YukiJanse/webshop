package se.jensen.yuki.webshop_admin.service;

import se.jensen.yuki.webshop_admin.constants.ErrorMessage;
import se.jensen.yuki.webshop_admin.model.Product;
import se.jensen.yuki.webshop_admin.repository.ProductRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * A service class responsible for managing and interacting with products.
 * Provides functionalities to add products, display all products,
 * and retrieve specific product information based on an article number.
 */
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

    public void addProduct(Product product) {
        productRepository.addProduct(product);
    }

    /**
     * Returns a text list of all products.
     *
     * @return a text list.
     */
    public List<String> showAllProduct() {
        List<String> stringList = new ArrayList<>();
        List<Product> productList = productRepository.getProductList();
        for (Product product : productList) {
            stringList.add(product.toString());
        }

        return stringList;
    }

    /**
     * Returns a text of q product.
     *
     * @param articleNumber is to search the product
     * @return the information text of the product or an error message.
     */
    public String showInformationOfProduct(String articleNumber) {
        Optional<Product> optionalProduct = productRepository.findByArticleNumber(articleNumber);

        return optionalProduct
                .map(Object::toString)
                .orElse(ErrorMessage.NO_SUCH_PRODUCT);

    }
}
