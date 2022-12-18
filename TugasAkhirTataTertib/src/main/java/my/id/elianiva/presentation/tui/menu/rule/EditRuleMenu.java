package my.id.elianiva.presentation.tui.menu.rule;

import my.id.elianiva.core.models.Rule;
import my.id.elianiva.presentation.tui.menu.MenuBase;
import my.id.elianiva.presentation.tui.utils.InputScanner;
import my.id.elianiva.presentation.tui.utils.ScreenUtils;
import my.id.elianiva.usecase.RuleService;
import my.id.elianiva.usecase.exceptions.RuleNotFoundException;

public class EditRuleMenu extends MenuBase {
    private final InputScanner scanner;
    private final RuleService ruleService;

    public EditRuleMenu(InputScanner scanner, RuleService ruleService) {
        super("Edit Rule");
        this.scanner = scanner;
        this.ruleService = ruleService;
    }

    @Override
    public boolean handle() {
        String oldId;

        while (true) {
            try {
                oldId = scanner.getNonEmptyString("Insert the id of the rule you want to edit: ", "The id can't be empty!");
                ruleService.getRuleById(oldId); // only used to check if the rule exists or not
                break;
            } catch (RuleNotFoundException e) {
                System.out.println(e.getMessage());
            }
        }


        while (true) {
            System.out.println("-- New Rule Data --");
            String description = scanner.getNonEmptyStringWithLimit("Description: ", "The name can't be empty!", 0, Rule.MAX_DESCRIPTION_LENGTH, true);
            int point = scanner.getPositiveInteger("Point: ", Integer.MAX_VALUE, true);

            try {
                ruleService.editRule(oldId, description, point);
                ScreenUtils.clearScreen();
                System.out.println("The rule with an id of " + oldId + " has been successfully edited!");
                break;
            } catch (RuleNotFoundException e) {
                ScreenUtils.clearScreen();
                System.out.println(e.getMessage());
            }
        }

        return true;
    }
}