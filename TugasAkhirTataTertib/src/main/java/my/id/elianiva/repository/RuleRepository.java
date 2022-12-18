package my.id.elianiva.repository;

import my.id.elianiva.core.models.Rule;
import my.id.elianiva.usecase.exceptions.RuleAlreadyExistsException;
import my.id.elianiva.usecase.exceptions.RuleNotFoundException;
import my.id.elianiva.usecase.interfaces.IRuleRepository;

import java.util.ArrayList;
import java.util.List;

public class RuleRepository implements IRuleRepository {
    private final List<Rule> rules = new ArrayList<Rule>();
    // used to emulate autoincrement
    private Integer idCounter = 0;

    public RuleRepository() {
        // initial data
        rules.add(new Rule("R001", "Student can't be late more than 15 minutes", 10));
        rules.add(new Rule("R002", "Student can't be absent for 7 days straight", 100));
        rules.add(new Rule("R003", "It is illegal to smoke or vape in campus", 40));
        rules.add(new Rule("R004", "Going to the toilet without asking for permission", 4));
        rules.add(new Rule("R005", "Not doing the homework that has been given by the lecturer", 20));
        idCounter = 6;
    }

    @Override
    public Rule getRuleById(String id) throws RuleNotFoundException {
        Rule rule = rules.stream().filter(r -> r.getId().equals(id)).findFirst().orElse(null);
        if (rule == null) {
            throw new RuleNotFoundException(id);
        }
        return rule;
    }

    @Override
    public List<Rule> getAllRules() {
        return rules.stream().sorted((r1, r2) -> {
            Integer r1Number = Integer.parseInt(r1.getId().substring(1), 10);
            Integer r2Number = Integer.parseInt(r2.getId().substring(1), 10);
            return r1Number.compareTo(r2Number);
        }).toList();
    }

    private String generateId() {
        return String.format("R%3s", idCounter++).replace(' ', '0');
    }

    @Override
    public void addRule(Rule rule) throws RuleAlreadyExistsException {
        rule.setId(generateId());

        String newId = rule.getId();
        Rule existingRule = rules.stream().filter(r -> r.getId().equals(newId)).findFirst().orElse(null);
        if (existingRule != null) {
            throw new RuleAlreadyExistsException(newId);
        }
        rules.add(rule);
    }

    @Override
    public void editRule(String id, Rule newRule) throws RuleNotFoundException {
        Rule existingRule = rules.stream().filter(r -> r.getId().equals(id)).findFirst().orElse(null);
        if (existingRule == null) {
            throw new RuleNotFoundException(id);
        }
        rules.add(newRule);
    }

    @Override
    public void deleteRule(String id) throws RuleNotFoundException {
        Rule existingRule = rules.stream().filter(r -> r.getId().equals(id)).findFirst().orElse(null);
        if (existingRule == null) {
            throw new RuleNotFoundException(id);
        }
        rules.remove(existingRule);
    }
}
