package my.id.elianiva.presentation.tui.menu.student;

import my.id.elianiva.core.models.Gender;
import my.id.elianiva.core.models.Student;
import my.id.elianiva.presentation.tui.menu.MenuBase;
import my.id.elianiva.presentation.tui.utils.InputScanner;
import my.id.elianiva.presentation.tui.utils.ScreenUtils;
import my.id.elianiva.usecase.StudentService;
import my.id.elianiva.usecase.exceptions.StudentNotFoundException;

public class EditStudentMenu extends MenuBase {
    private final InputScanner scanner;
    private final StudentService studentService;

    public EditStudentMenu(InputScanner scanner, StudentService studentService) {
        super("Edit Student");
        this.scanner = scanner;
        this.studentService = studentService;
    }

    @Override
    public boolean handle() {
        String oldNim;
        Student oldStudent;

        while (true) {
            try {
                oldNim = scanner.getNonEmptyString("Insert the NIM of the student you want to edit: ", "The NIM can't be empty!");
                oldStudent = studentService.getStudentByNim(oldNim);
                break;
            } catch (StudentNotFoundException e) {
                System.out.println(e.getMessage());
            }
        }

        while (true) {
            System.out.println("-- New Student Data --");
            String name = scanner.getNonEmptyStringWithLimit("Name (old: " + oldStudent.getName() + "): ", "The name can't be empty!", 1, Student.MAX_NAME_LENGTH, true);
            String nim = scanner.getNonEmptyStringWithLimit("NIM (old: " + oldStudent.getNim() + "): ", "The NIM can't be empty!", Student.NIM_LENGTH, Student.NIM_LENGTH, true);
            String classPlacement = scanner.getNonEmptyStringWithLimit("Class (old: " + oldStudent.getClassPlacement() + "): ", "Class can't be empty", Student.CLASS_LENGTH, Student.CLASS_LENGTH, true);
            int age = scanner.getPositiveInteger("Age (old: " + oldStudent.getAge() + "): ", Student.MAX_AGE, true);
            Gender gender = scanner.getEnumOption("Gender (old: " + oldStudent.getGender() + ") [M/F]: ", "Invalid gender option!", Gender::fromString, true);

            try {
                studentService.editStudent(oldNim, name, nim, classPlacement, age, gender);
                ScreenUtils.clearScreen();
                System.out.println("The student with a NIM of " + oldNim + " has been successfully edited!");
                break;
            } catch (StudentNotFoundException e) {
                System.out.println(e.getMessage());
            }
        }

        return true;
    }
}