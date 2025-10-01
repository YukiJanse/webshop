package se.jensen.yuki.webshop_admin.repository;

import se.jensen.yuki.webshop_admin.model.Product;

import java.util.List;

public class ProductRepository {
    private List<Product> productList;

    /**
     * Constructor
     *
     * @param productList
     */
    public ProductRepository(List<Product> productList) {
        this.productList = productList;
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
}
