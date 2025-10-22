package se.jensen.yuki.webshop_admin.dao;

import se.jensen.yuki.webshop_admin.model.User;
import se.jensen.yuki.webshop_admin.util.JsonFileReader;
import se.jensen.yuki.webshop_admin.util.JsonFileWriter;

import java.util.ArrayList;
import java.util.List;

import static se.jensen.yuki.webshop_admin.model.FilePath.USER_JSON_FILE_PATH;

public class UserFileDao implements UserDao {
    private final JsonFileWriter fileWriter = new JsonFileWriter();
    private final JsonFileReader fileReader = new JsonFileReader();

    @Override
    public List<User> loadAll() {
        List<User> userList = new ArrayList<>();
        try {
            userList = fileReader.readJson(USER_JSON_FILE_PATH, User.class);
            if (userList != null && !userList.isEmpty()) {
                User.updateCounter(userList.size());
            }
        } catch (RuntimeException e) {
            System.out.println("Failed initializing UserRepository: " + e.getMessage());
        }
        return userList;
    }

    @Override
    public void save(List<User> userList) {
        fileWriter.writeJsonFile(USER_JSON_FILE_PATH, userList);
    }
}
