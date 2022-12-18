package my.id.elianiva.usecase.exceptions;

public class UserNotFoundException extends Exception {
    public UserNotFoundException(String username) {
        super("The user with a username of " + username + " couldn't be found");
    }
}
