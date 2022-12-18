package my.id.elianiva.presentation.tui.menu.student;

import my.id.elianiva.core.models.Student;
import my.id.elianiva.presentation.tui.menu.MenuBase;
import my.id.elianiva.presentation.tui.utils.InputScanner;
import my.id.elianiva.presentation.tui.utils.ScreenUtils;
import my.id.elianiva.presentation.tui.utils.TableRenderer;
import my.id.elianiva.usecase.StudentService;
import my.id.elianiva.usecase.exceptions.StudentNotFoundException;

public class ShowStudentViolatedRulesMenu extends MenuBase {
    private final InputScanner scanner;
    private final StudentService studentService;

    public ShowStudentViolatedRulesMenu(InputScanner scanner, StudentService studentService) {
        super("Show Student Violated Rules");
        this.scanner = scanner;
        this.studentService = studentService;
    }

    @Override
    public boolean handle() {
        String nim;
        Student student;

        while (true) {
            try {
                nim = scanner.getNonEmptyString("Insert the NIM of the student you want to check: ", "The NIM can't be empty!");
                student = studentService.getStudentByNim(nim);
                ScreenUtils.clearScreen();
                break;
            } catch (StudentNotFoundException e) {
                ScreenUtils.clearScreen();
                System.out.println(e.getMessage());
            }
        }

        TableRenderer.renderRulesTable(student.getViolatedRules());
        scanner.getString("Press any key to go back...");
        ScreenUtils.clearScreen();

        return true;
    }
}