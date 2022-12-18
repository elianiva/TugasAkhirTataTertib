package my.id.elianiva.presentation.tui.menu.student;

import my.id.elianiva.core.models.Gender;
import my.id.elianiva.core.models.Student;
import my.id.elianiva.presentation.tui.menu.MenuBase;
import my.id.elianiva.presentation.tui.utils.InputScanner;
import my.id.elianiva.presentation.tui.utils.ScreenUtils;
import my.id.elianiva.usecase.StudentService;
import my.id.elianiva.usecase.exceptions.StudentAlreadyExistsException;

public class AddStudentMenu extends MenuBase {
    private final InputScanner scanner;
    private final StudentService studentService;

    public AddStudentMenu(InputScanner scanner, StudentService userService) {
        super("Add Student");
        this.scanner = scanner;
        this.studentService = userService;
    }

    @Override
    public boolean handle() {
        System.out.println("-- New Student Data --");
        String name = scanner.getNonEmptyStringWithLimit("Name: ", "The name can't be empty!", 1, Student.MAX_NAME_LENGTH);
        String nim = scanner.getNonEmptyStringWithLimit("NIM: ", "The NIM can't be empty!", Student.NIM_LENGTH, Student.NIM_LENGTH);
        String classPlacement = scanner.getNonEmptyString("Class: ", "Class can't be empty");
        int age = scanner.getPositiveInteger("Age: ", Student.MAX_AGE);
        Gender gender = scanner.getEnumOption("Gender (M/F): ", "Invalid gender option!", Gender::fromString);

        try {
            studentService.addStudent(name, nim, classPlacement, age, gender);
            ScreenUtils.clearScreen();
            System.out.println("New student has been successfully added!");
        } catch (StudentAlreadyExistsException e) {
            ScreenUtils.clearScreen();
            System.out.println(e.getMessage());
        }
        return true;
    }
}
