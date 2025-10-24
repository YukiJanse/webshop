package se.jensen.yuki.webshop_admin.repository;

import se.jensen.yuki.webshop_admin.dao.UserDao;
import se.jensen.yuki.webshop_admin.model.User;

import java.util.List;
import java.util.Optional;

/**
 * Repository class for managing and interacting with User data.
 * Provides methods to initialize, retrieve, search, and save user data.
 */
public class UserRepository {
    private final UserDao dao;
    private List<User> userList;

    /**
     * Constructor
     */
    public UserRepository(UserDao dao) {
        this.dao = dao;
        try {
            this.userList = dao.loadAll();
        } catch (RuntimeException e) {
            System.out.println("Failed initializing UserFileRepository: " + e.getMessage());
        }
    }

    /**
     * Getter for userList.
     *
     * @return
     */
    public List<User> getUserList() {
        return userList;
    }

    /**
     * Setter for userList.
     *
     * @param userList
     */
    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public void saveRepositoryToFile() {
        dao.save(userList);
    }

    /**
     * Find User instance from userList by username.
     * If the list has null, it throws NullPointException
     *
     * @param username
     * @return Optional<User>
     */
    public Optional<User> findByUsername(String username) {
        return userList.stream()
                .filter(user -> user.getUsername().equals(username))
                .findFirst();
    }
}
