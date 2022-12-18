package my.id.elianiva.usecase;

import my.id.elianiva.core.models.Rule;
import my.id.elianiva.usecase.exceptions.RuleAlreadyExistsException;
import my.id.elianiva.usecase.exceptions.RuleNotFoundException;
import my.id.elianiva.usecase.interfaces.IRuleRepository;

import java.util.List;

public class RuleService {
    private final IRuleRepository ruleRepository;

    public RuleService(IRuleRepository ruleRepository) {
        this.ruleRepository = ruleRepository;
    }

    public Rule getRuleById(String id) throws RuleNotFoundException {
        return ruleRepository.getRuleById(id);
    }

    public List<Rule> getAllRules() {
        return ruleRepository.getAllRules();
    }

    public void addRule(String description, int point) throws RuleAlreadyExistsException {
        ruleRepository.addRule(new Rule(null, description, point));
    }

    public void deleteRule(String id) throws RuleNotFoundException {
        ruleRepository.deleteRule(id);
    }

    public void editRule(String id, String newDescription, int newPoint) throws RuleNotFoundException {
        Rule rule = ruleRepository.getRuleById(id);
        rule.update(newDescription, newPoint);
        ruleRepository.editRule(id, rule);
    }
}
