package my.id.elianiva.presentation.tui.menu.rule;

import my.id.elianiva.core.models.Rule;
import my.id.elianiva.presentation.tui.menu.BackMenu;
import my.id.elianiva.presentation.tui.menu.ExitMenu;
import my.id.elianiva.presentation.tui.menu.Menu;
import my.id.elianiva.presentation.tui.menu.MenuBase;
import my.id.elianiva.presentation.tui.utils.InputScanner;
import my.id.elianiva.presentation.tui.utils.ScreenUtils;
import my.id.elianiva.presentation.tui.utils.TableRenderer;
import my.id.elianiva.usecase.RuleService;

import java.util.List;

public class ShowRulesMenu extends MenuBase {
    private final InputScanner scanner;
    private final RuleService ruleService;

    public ShowRulesMenu(InputScanner scanner, RuleService ruleService) {
        super("Show Rules");
        this.scanner = scanner;
        this.ruleService = ruleService;
    }

    private boolean showRulesTable(List<Rule> rules) {
        ScreenUtils.renderTitle("Showing Rules Table");
        TableRenderer.renderRulesTable(rules);
        return Menu.showMenu(scanner, "Rules Table Menu", new MenuBase[]{
                new AddRuleMenu(scanner, ruleService),
                new EditRuleMenu(scanner, ruleService),
                new DeleteRuleMenu(scanner, ruleService),
                new BackMenu(),
                new ExitMenu(),
        });
    }

    @Override
    public boolean handle() {
        ScreenUtils.clearScreen();
        while (true) {
            List<Rule> rules = ruleService.getAllRules();
            boolean shouldContinue = showRulesTable(rules);
            if (shouldContinue) continue;
            break;
        }
        return true;
    }
}
