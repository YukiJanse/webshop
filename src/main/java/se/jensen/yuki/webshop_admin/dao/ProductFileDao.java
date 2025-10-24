package se.jensen.yuki.webshop_admin.dao;

import se.jensen.yuki.webshop_admin.model.Product;
import se.jensen.yuki.webshop_admin.util.JsonFileReader;
import se.jensen.yuki.webshop_admin.util.JsonFileWriter;

import java.util.ArrayList;
import java.util.List;

import static se.jensen.yuki.webshop_admin.constants.FilePath.PRODUCT_JSON_FILE_PATH;

public class ProductFileDao implements ProductDao {
    private final JsonFileReader fileReader = new JsonFileReader();
    private final JsonFileWriter fileWriter = new JsonFileWriter();

    @Override
    public List<Product> loadAll() {
        List<Product> productList = new ArrayList<>();
        try {
            productList = fileReader.readJson(PRODUCT_JSON_FILE_PATH, Product.class);
            if (productList != null && !productList.isEmpty()) {
                Product.updateCounter(productList.size());
            }
        } catch (RuntimeException e) {
            System.out.println("Failed initializing ProductRepository: " + e.getMessage());
        }
        return productList;
    }

    @Override
    public void save(List<Product> productList) {
        fileWriter.writeJsonFile(PRODUCT_JSON_FILE_PATH, productList, Product.class);
    }
}
