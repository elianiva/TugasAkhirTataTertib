package my.id.elianiva.core.models;

public class User {
    public static final int MAX_USERNAME_LENGTH = 10;
    public static final int MIN_PASSWORD_LENGTH = 8;
    private String username;
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void update(String newUsername, String newPassword) {
        if (!newUsername.trim().isEmpty()) {
            this.username = newUsername;
        }
        if (!newPassword.trim().isEmpty()) {
            this.password = newPassword;
        }
    }
}
