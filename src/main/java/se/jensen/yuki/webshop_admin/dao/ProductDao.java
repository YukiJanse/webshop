package se.jensen.yuki.webshop_admin.dao;

import se.jensen.yuki.webshop_admin.model.Product;

import java.util.List;

public interface ProductDao {
    List<Product> loadAll();

    void save(List<Product> productList);
}
