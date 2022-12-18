package my.id.elianiva.usecase.exceptions;

public class UserAlreadyExistsException extends Exception {
    public UserAlreadyExistsException(String username) {
        super("The user with a username of " + username + " already exists");
    }
}
