package my.id.elianiva.usecase.exceptions;

public class InvalidCredentialException extends Exception {
    public InvalidCredentialException() {
        super("Incorrect password!");
    }
}
