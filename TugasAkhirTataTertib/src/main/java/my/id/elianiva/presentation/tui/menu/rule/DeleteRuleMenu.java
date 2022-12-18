package my.id.elianiva.presentation.tui.menu.rule;

import my.id.elianiva.presentation.tui.menu.MenuBase;
import my.id.elianiva.presentation.tui.utils.InputScanner;
import my.id.elianiva.presentation.tui.utils.ScreenUtils;
import my.id.elianiva.usecase.RuleService;
import my.id.elianiva.usecase.exceptions.RuleNotFoundException;

public class DeleteRuleMenu extends MenuBase {
    private final InputScanner scanner;
    private final RuleService ruleService;

    public DeleteRuleMenu(InputScanner scanner, RuleService ruleService) {
        super("Delete Rule");
        this.scanner = scanner;
        this.ruleService = ruleService;
    }

    @Override
    public boolean handle() {
        String id = scanner.getNonEmptyString("Insert the id of the rule you want to delete: ", "The id can't be empty!");
        try {
            ruleService.deleteRule(id);
            ScreenUtils.clearScreen();
            System.out.println("The rule with an id of " + id + " has been deleted!");
        } catch (RuleNotFoundException e) {
            ScreenUtils.clearScreen();
            System.out.println(e.getMessage());
        }
        return true;
    }
}
