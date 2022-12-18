package my.id.elianiva.presentation.tui.menu.student;

import my.id.elianiva.presentation.tui.menu.MenuBase;
import my.id.elianiva.presentation.tui.utils.InputScanner;
import my.id.elianiva.presentation.tui.utils.ScreenUtils;
import my.id.elianiva.usecase.StudentService;
import my.id.elianiva.usecase.exceptions.StudentNotFoundException;

public class DeleteStudentMenu extends MenuBase {
    private final InputScanner scanner;
    private final StudentService studentService;

    public DeleteStudentMenu(InputScanner scanner, StudentService studentService) {
        super("Delete Student");
        this.scanner = scanner;
        this.studentService = studentService;
    }

    @Override
    public boolean handle() {
        String nim = scanner.getNonEmptyString("Insert the NIM of the student you want to delete: ", "The NIM can't be empty!");
        try {
            studentService.deleteStudent(nim);
            ScreenUtils.clearScreen();
            System.out.println("The student with a NIM of " + nim + " has been deleted!");
        } catch (StudentNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return true;
    }
}
