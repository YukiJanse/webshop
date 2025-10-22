package se.jensen.yuki.webshop_admin.dao;

import se.jensen.yuki.webshop_admin.model.Appliance;
import se.jensen.yuki.webshop_admin.model.Book;
import se.jensen.yuki.webshop_admin.model.Cloth;
import se.jensen.yuki.webshop_admin.model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDbDao implements ProductDao {
    private final String url;
    private final String user;
    private final String password;
    private static final int TYPE = 1;
    private static final int ARTICLE_NUMBER = 2;
    private static final int TITLE = 3;
    private static final int PRICE = 4;
    private static final int DESCRIPTION = 5;
    private static final int SIZE = 6;
    private static final int AUTHOR = 7;
    private static final int BRAND = 8;

    public ProductDbDao(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    private Connection connect() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    @Override
    public List<Product> loadAll() {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM product";
        try (Connection con = connect();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                String type = rs.getString("type");
                Product p = switch (type) {
                    case "cloth" -> new Cloth(
                            rs.getString("title"),
                            rs.getInt("price"),
                            rs.getString("description"),
                            rs.getString("size")
                    );
                    case "appliance" -> new Appliance(
                            rs.getString("title"),
                            rs.getInt("price"),
                            rs.getString("description"),
                            rs.getString("brand")
                    );
                    case "book" -> new Book(
                            rs.getString("title"),
                            rs.getInt("price"),
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

    @Override
    public void save(List<Product> productList) {

        try (Connection con = connect();
             PreparedStatement ps = con.prepareStatement(
                     "INSERT IGNORE INTO product (type, article_number, title, price, description, size, author, brand) VALUES (?, ?, ?, ?, ?, ?, ?, ?)"
             )) {
            for (Product product : productList) {
                ps.setString(TYPE, product.category().getString());
                ps.setString(ARTICLE_NUMBER, product.getArticleNumber());
                ps.setString(TITLE, product.getTitle());
                ps.setInt(PRICE, product.getPrice());
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
