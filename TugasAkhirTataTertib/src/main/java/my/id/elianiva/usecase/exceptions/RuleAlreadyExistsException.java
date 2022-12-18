package my.id.elianiva.usecase.exceptions;

public class RuleAlreadyExistsException extends Exception {
    public RuleAlreadyExistsException(String id) {
        super("The rule with an id of " + id + " already exists");
    }
}
