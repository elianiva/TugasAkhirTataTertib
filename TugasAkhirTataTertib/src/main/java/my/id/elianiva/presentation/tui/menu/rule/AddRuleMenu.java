package my.id.elianiva.presentation.tui.menu.rule;

import my.id.elianiva.core.models.Rule;
import my.id.elianiva.presentation.tui.menu.MenuBase;
import my.id.elianiva.presentation.tui.utils.InputScanner;
import my.id.elianiva.presentation.tui.utils.ScreenUtils;
import my.id.elianiva.usecase.RuleService;
import my.id.elianiva.usecase.exceptions.RuleAlreadyExistsException;

public class AddRuleMenu extends MenuBase {
    private final InputScanner scanner;
    private final RuleService ruleService;

    public AddRuleMenu(InputScanner scanner, RuleService userService) {
        super("Add Rule");
        this.scanner = scanner;
        this.ruleService = userService;
    }

    @Override
    public boolean handle() {
        System.out.println("-- New Rule Data --");
        String description = scanner.getNonEmptyStringWithLimit("Description: ", "The description can't be empty!", 10, Rule.MAX_DESCRIPTION_LENGTH);
        int point = scanner.getPositiveInteger("Point: ", Integer.MAX_VALUE);

        try {
            ruleService.addRule(description, point);
            ScreenUtils.clearScreen();
            System.out.println("New rule has been successfully added!");
        } catch (RuleAlreadyExistsException e) {
            ScreenUtils.clearScreen();
            System.out.println(e.getMessage());
        }
        return true;
    }
}
