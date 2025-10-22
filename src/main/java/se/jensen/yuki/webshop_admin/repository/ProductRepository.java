package se.jensen.yuki.webshop_admin.repository;

import se.jensen.yuki.webshop_admin.dao.ProductDao;
import se.jensen.yuki.webshop_admin.model.Product;

import java.util.List;
import java.util.Optional;

public class ProductRepository {
    private ProductDao dao;
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
     * @param productList
     */
    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    public Optional<Product> findByArticleNumber(String articleNumber) {
        return productList.stream()
                .filter(product -> product.getArticleNumber().equals(articleNumber))
                .findFirst();
    }

    public Optional<Product> findByName(String title) {
        return productList.stream()
                .filter(product -> product.getTitle().equals(title))
                .findFirst();
    }

    public void addProduct(Product product) {
        productList.add(product);
        dao.save(productList);
    }
}
