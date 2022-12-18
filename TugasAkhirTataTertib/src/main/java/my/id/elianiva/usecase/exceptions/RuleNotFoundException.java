package my.id.elianiva.usecase.exceptions;

public class RuleNotFoundException extends Exception {
    public RuleNotFoundException(String id) {
        super("The rule with an id of " + id + " couldn't be found");
    }
}
