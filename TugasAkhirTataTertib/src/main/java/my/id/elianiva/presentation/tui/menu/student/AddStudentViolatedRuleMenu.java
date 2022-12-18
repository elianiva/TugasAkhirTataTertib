package my.id.elianiva.presentation.tui.menu.student;

import my.id.elianiva.core.models.Rule;
import my.id.elianiva.presentation.tui.menu.MenuBase;
import my.id.elianiva.presentation.tui.utils.InputScanner;
import my.id.elianiva.presentation.tui.utils.ScreenUtils;
import my.id.elianiva.presentation.tui.utils.TableRenderer;
import my.id.elianiva.usecase.RuleService;
import my.id.elianiva.usecase.StudentService;
import my.id.elianiva.usecase.exceptions.RuleNotFoundException;
import my.id.elianiva.usecase.exceptions.StudentNotFoundException;

import java.util.List;

public class AddStudentViolatedRuleMenu extends MenuBase {
    private final InputScanner scanner;
    private final StudentService studentService;
    private final RuleService ruleService;


    public AddStudentViolatedRuleMenu(InputScanner scanner, StudentService studentService, RuleService ruleService) {
        super("Add Point to Student");
        this.scanner = scanner;
        this.studentService = studentService;
        this.ruleService = ruleService;
    }

    @Override
    public boolean handle() {
        String nim;

        while (true) {
            try {
                nim = scanner.getNonEmptyString("Insert the NIM of the student who violated a rule: ", "The NIM can't be empty!");
                studentService.getStudentByNim(nim); // only checks if the student exists or not
                break;
            } catch (StudentNotFoundException e) {
                System.out.println(e.getMessage());
            }
        }

        ScreenUtils.clearScreen();
        List<Rule> rules = ruleService.getAllRules();

        while (true) {
            TableRenderer.renderRulesTable(rules);
            String ruleId = scanner.getNonEmptyStringWithLimit("Insert the id of the rule that has been violated: ", "The id can't be empty!", Rule.ID_LENGTH, Rule.ID_LENGTH);

            try {
                studentService.addViolatedRule(nim, ruleId);
                ScreenUtils.clearScreen();
                System.out.println("The rule with an id of " + ruleId + " has been successfully added to the student with a NIM of " + nim);
                break;
            } catch (StudentNotFoundException | RuleNotFoundException e) {
                System.out.println(e.getMessage());
            }
        }

        return true;
    }
}
