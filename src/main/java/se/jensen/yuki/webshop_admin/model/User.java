package se.jensen.yuki.webshop_admin.model;

public class User {
    private static long counter = 0;
    /** ID for user */
    private long id;
    /** Username */
    private String username;
    /** Password */
    private String password;

    /**
     * Default constructor for JSON mapper
     */
    public User() {
    }

    /**
     * Normal constructor
     *
     * @param username
     * @param password
     */
    public User(String username, String password) {
        this.id = ++counter;
        this.username = username;
        this.password = password;
    }

    /**
     * Validates user with username and password
     *
     * @param username
     * @param password
     * @return true if the user is validated, false otherwise.
     */
    public boolean isUserMatch(String username, String password) {
        return this.username.equals(username) && this.password.equals(password);
    }

    /**
     * Getter for id
     *
     * @return id
     */
    public long getId() {
        return id;
    }

    /**
     * Setter for id.
     * THIS IS FOR JSON MAPPER. DO NOT USE IT IN THE CODE.
     *
     * @param id
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Getter for username
     *
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Setter for username.
     *
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Getter for password.
     *
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Setter for password.
     *
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    public static void updateCounter(long numberOfUsers) {
        counter = numberOfUsers;
    }
}
