package se.jensen.yuki.webshop_admin.repository;

import se.jensen.yuki.webshop_admin.model.User;

import java.util.List;
import java.util.Optional;

public class UserRepository {
    private List<User> userList;

    /**
     * Constructor
     *
     * @param userList
     */
    public UserRepository(List<User> userList) {
        this.userList = userList;
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
