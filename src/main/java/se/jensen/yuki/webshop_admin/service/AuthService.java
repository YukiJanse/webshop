package se.jensen.yuki.webshop_admin.service;

import se.jensen.yuki.webshop_admin.model.User;
import se.jensen.yuki.webshop_admin.repository.UserRepository;

import java.util.Optional;

public class AuthService {
    private UserRepository userRepository;
    private User loggedInUser;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserRepository getUserRepository() {
        return userRepository;
    }

    /**
     * Authenticate the username and password
     *
     * @throws RuntimeException will be thrown when failed login
     */
    public boolean login(String username, String password) {
        Optional<User> targetOptUser = userRepository.findByUsername(username);

        if (targetOptUser.isPresent() && targetOptUser.get().getPassword().equals(password)) {
            loggedInUser = targetOptUser.get();
            return true;
        }

        return false;
    }

    /**
     * Log out the account.
     */
    public void logout() {
        loggedInUser = null;
    }

    /**
     * Returns the current user.
     *
     * @return current user
     */
    public User getLoggedInUser() {
        return loggedInUser;
    }

    /**
     * Returns login status
     *
     * @return true if a user logged in.
     */
    public boolean isLoggedIn() {
        return loggedInUser != null;
    }
}
