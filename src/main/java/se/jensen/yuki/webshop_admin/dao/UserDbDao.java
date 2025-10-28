package se.jensen.yuki.webshop_admin.dao;

import se.jensen.yuki.webshop_admin.model.User;
import se.jensen.yuki.webshop_admin.util.ConfigurationManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDbDao implements UserDao {
    private static final int USERNAME = 1;
    private static final int PASSWORD = 2;

    public UserDbDao() {
    }


    @Override
    public List<User> loadAll() {
        List<User> userList = new ArrayList<>();
        String sql = "SELECT * FROM user";
        try (Connection con = ConfigurationManager.getInstance().getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                userList.add(new User(rs.getString("username"), rs.getString("password")));
            }

        } catch (SQLException e) {
            System.out.println("Error by reading User database: " + e.getMessage());
        }
        return userList;
    }

    @Override
    public void save(List<User> userList) {
        try (Connection con = ConfigurationManager.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement("INSERT IGNORE INTO user ('username', 'password') VALUES (?, ?)")) {
            for (User user : userList) {
                ps.setString(USERNAME, user.getUsername());
                ps.setString(PASSWORD, user.getPassword());
                ps.addBatch();
            }
            ps.executeBatch();
        } catch (SQLException e) {
            System.out.println("Error by inserting User data to User database: " + e.getMessage());
        }
    }
}
