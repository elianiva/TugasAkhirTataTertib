package my.id.elianiva.presentation.tui.menu.student;

import my.id.elianiva.core.models.Student;
import my.id.elianiva.presentation.tui.menu.BackMenu;
import my.id.elianiva.presentation.tui.menu.ExitMenu;
import my.id.elianiva.presentation.tui.menu.Menu;
import my.id.elianiva.presentation.tui.menu.MenuBase;
import my.id.elianiva.presentation.tui.utils.InputScanner;
import my.id.elianiva.presentation.tui.utils.ScreenUtils;
import my.id.elianiva.presentation.tui.utils.TableRenderer;
import my.id.elianiva.usecase.RuleService;
import my.id.elianiva.usecase.StudentService;

import java.util.List;

public class ShowStudentsMenu extends MenuBase {
    private final InputScanner scanner;
    private final StudentService studentService;
    private final RuleService ruleService;

    public ShowStudentsMenu(InputScanner scanner, StudentService studentService, RuleService ruleService) {
        super("Show Students");
        this.scanner = scanner;
        this.studentService = studentService;
        this.ruleService = ruleService;
    }

    private boolean showStudentsTable(List<Student> students) {
        ScreenUtils.renderTitle("Showing Students Table");
        TableRenderer.renderStudentsTable(students);
        return Menu.showMenu(scanner,"Students Table Menu", new MenuBase[]{
                new ShowStudentViolatedRulesMenu(scanner, studentService),
                new AddStudentViolatedRuleMenu(scanner, studentService, ruleService),
                new AddStudentMenu(scanner, studentService),
                new EditStudentMenu(scanner, studentService),
                new DeleteStudentMenu(scanner, studentService),
                new BackMenu(),
                new ExitMenu(),
        });
    }

    @Override
    public boolean handle() {
        ScreenUtils.clearScreen();
        while (true) {
            List<Student> users = studentService.getAllStudents();
            boolean shouldContinue = showStudentsTable(users);
            if (shouldContinue) continue;
            break;
        }
        return true;
    }
}
