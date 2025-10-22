package se.jensen.yuki.webshop_admin.dao;

import se.jensen.yuki.webshop_admin.model.User;

import java.util.List;

public interface UserDao {
    List<User> loadAll();

    void save(List<User> userList);
}
