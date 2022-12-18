package my.id.elianiva.usecase.interfaces;

import my.id.elianiva.core.models.Rule;
import my.id.elianiva.usecase.exceptions.RuleAlreadyExistsException;
import my.id.elianiva.usecase.exceptions.RuleNotFoundException;

import java.util.List;

public interface IRuleRepository {
    Rule getRuleById(String id) throws RuleNotFoundException;

    List<Rule> getAllRules();

    void addRule(Rule rule) throws RuleAlreadyExistsException;

    void editRule(String username, Rule newStudent) throws RuleNotFoundException;

    void deleteRule(String username) throws RuleNotFoundException;
}
