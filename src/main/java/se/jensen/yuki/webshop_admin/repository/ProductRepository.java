package se.jensen.yuki.webshop_admin.repository;

import se.jensen.yuki.webshop_admin.dao.ProductDao;
import se.jensen.yuki.webshop_admin.model.Product;

import java.util.List;
import java.util.Optional;

/**
 * Repository class responsible for managing and interacting with Product data.
 * It provides methods for initializing, retrieving, searching, and saving product data.
 */
public class ProductRepository {
    /** ProductDao **/
    private ProductDao dao;
    /** List of products **/
    private List<Product> productList;

    /**
     * Constructor
     */
    public ProductRepository(ProductDao dao) {
        try {
            this.dao = dao;
            this.productList = dao.loadAll();
            Product.updateCounter(productList.size());
        } catch (RuntimeException e) {
            System.out.println("Failed initializing ProductRepository: " + e.getMessage());
        }
    }

    /**
     * Getter for productList
     *
     * @return productList
     */
    public List<Product> getProductList() {
        return productList;
    }

    /**
     * Setter for productList
     *
     * @param productList is a list of Product objects.
     */
    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    /**
     * Searches a product from the productList with articleNumber.
     *
     * @param articleNumber to search a product.
     * @return a Product object or null
     */
    public Optional<Product> findByArticleNumber(String articleNumber) {
        return productList.stream()
                .filter(product -> product.getArticleNumber().equals(articleNumber))
                .findFirst();
    }

    /**
     * Searches a product by title.
     *
     * @param title is to search a product.
     * @return result of the search.
     */
    public Optional<Product> findByName(String title) {
        return productList.stream()
                .filter(product -> product.getTitle().equals(title))
                .findFirst();
    }

    /**
     * Adds a product to the productList and save them to Data
     *
     * @param product is a Product object to add.
     */
    public void addProduct(Product product) {
        productList.add(product);
        dao.save(productList);
    }
}
