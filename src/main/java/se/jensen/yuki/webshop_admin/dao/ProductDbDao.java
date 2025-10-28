package se.jensen.yuki.webshop_admin.dao;

import se.jensen.yuki.webshop_admin.model.Appliance;
import se.jensen.yuki.webshop_admin.model.Book;
import se.jensen.yuki.webshop_admin.model.Cloth;
import se.jensen.yuki.webshop_admin.model.Product;
import se.jensen.yuki.webshop_admin.util.ConfigurationManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The ProductDbDao class is an implementation of the ProductDao interface. It interacts with
 * a relational database to perform operations such as loading all products from the
 * database and saving a list of products to the database.
 * This class supports different types of products, including Cloth, Book,
 * and Appliance, which are derived from the abstract Product class.
 * The type of product is determined dynamically during database operations.
 * The database connection is established using JDBC, with connection details such as
 * URL, username, and password provided during the object construction.
 */
public class ProductDbDao implements ProductDao {
    private static final int TYPE = 1;
    private static final int ARTICLE_NUMBER = 2;
    private static final int TITLE = 3;
    private static final int PRICE = 4;
    private static final int DESCRIPTION = 5;
    private static final int SIZE = 6;
    private static final int AUTHOR = 7;
    private static final int BRAND = 8;

    /**
     * Constructs a ProductDbDao instance for interacting with the product database.
     */
    public ProductDbDao() {

    }

    /**
     * Loads all products from the database and returns them as a list of Product objects.
     * Each product is categorized as a specific subclass (e.g., Cloth, Appliance, or Book)
     * based on the type column in the database.
     * This method establishes a connection to the database, executes a query to fetch all
     * records from the "product" table, and maps the result set to Product instances.
     * If an error occurs during the database interaction, it is logged to the console.
     *
     * @return a list of Product objects representing all products in the database.
     * The list may include objects of subclasses such as Cloth, Appliance, or Book.
     */
    @Override
    public List<Product> loadAll() {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM product";
        try (Connection con = ConfigurationManager.getInstance().getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                String type = rs.getString("type");
                Product p = switch (type) {
                    case "cloth" -> new Cloth(
                            rs.getString("title"),
                            rs.getDouble("price"),
                            rs.getString("description"),
                            rs.getString("size")
                    );
                    case "appliance" -> new Appliance(
                            rs.getString("title"),
                            rs.getDouble("price"),
                            rs.getString("description"),
                            rs.getString("brand")
                    );
                    case "book" -> new Book(
                            rs.getString("title"),
                            rs.getDouble("price"),
                            rs.getString("description"),
                            rs.getString("author")
                    );
                    default -> null;
                };

                products.add(p);
            }

        } catch (SQLException e) {
            System.out.println("Error by reading Product database: " + e.getMessage());
        }
        return products;
    }

    /**
     * Saves a list of products to the database. Each product is inserted into the
     * "product" table based on its type (e.g., Cloth, Book, or Appliance). Products
     * of each type provide specific values for corresponding attributes such as size,
     * author, or brand. Duplicate entries are ignored based on the article number.
     *
     * @param productList the list of products to be saved. Each product should be
     *                    an instance of a subclass of {@code Product}
     *                    (e.g., {@code Cloth}, {@code Book}, or {@code Appliance}).
     */
    @Override
    public void save(List<Product> productList) {

        try (Connection con = ConfigurationManager.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(
                     "INSERT IGNORE INTO product (type, article_number, title, price, description, size, author, brand) VALUES (?, ?, ?, ?, ?, ?, ?, ?)"
             )) {
            for (Product product : productList) {
                ps.setString(TYPE, product.category().getString());
                ps.setString(ARTICLE_NUMBER, product.getArticleNumber());
                ps.setString(TITLE, product.getTitle());
                ps.setDouble(PRICE, product.getPrice());
                ps.setString(DESCRIPTION, product.getDescription());
                if (product instanceof Cloth cloth) {
                    ps.setString(SIZE, cloth.getSize());
                    ps.setNull(AUTHOR, Types.VARCHAR);
                    ps.setNull(BRAND, Types.VARCHAR);
                } else if (product instanceof Book book) {
                    ps.setNull(SIZE, Types.VARCHAR);
                    ps.setString(AUTHOR, book.getAuthor());
                    ps.setNull(BRAND, Types.VARCHAR);
                } else if (product instanceof Appliance appliance) {
                    ps.setNull(SIZE, Types.VARCHAR);
                    ps.setNull(AUTHOR, Types.VARCHAR);
                    ps.setString(BRAND, appliance.getBrand());
                }
                ps.addBatch();
            }
            ps.executeBatch();
        } catch (SQLException e) {
            System.out.println("Error by adding products to Product database: " + e.getMessage());
        }
    }
}
