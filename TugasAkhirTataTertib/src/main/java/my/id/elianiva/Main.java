package my.id.elianiva;

import my.id.elianiva.core.Application;
import my.id.elianiva.core.interfaces.IPresenter;
import my.id.elianiva.presentation.tui.TuiPresenter;
import my.id.elianiva.presentation.tui.utils.InputScanner;
import my.id.elianiva.repository.RuleRepository;
import my.id.elianiva.repository.StudentRepository;
import my.id.elianiva.repository.UserRepository;
import my.id.elianiva.usecase.RuleService;
import my.id.elianiva.usecase.StudentService;
import my.id.elianiva.usecase.UserService;
import my.id.elianiva.usecase.interfaces.IRuleRepository;
import my.id.elianiva.usecase.interfaces.IStudentRepository;
import my.id.elianiva.usecase.interfaces.IUserRepository;

/**
 * Nama: Dicha Zelianivan Arkana
 * NIM: 22417200002
 * Department: Information Technology
 * Study Program: D4 Informatics Engineering
 */
public class Main {
    public static void main(String[] args) {
        IUserRepository userRepository = new UserRepository();
        IStudentRepository studentRepository = new StudentRepository();
        IRuleRepository ruleRepository = new RuleRepository();

        UserService userService = new UserService(userRepository);
        StudentService studentService = new StudentService(studentRepository, ruleRepository);
        RuleService ruleService = new RuleService(ruleRepository);

        InputScanner scanner = new InputScanner(System.in);

        IPresenter presenter = new TuiPresenter(scanner, userService, studentService, ruleService);

            Application app = new Application(presenter);
            app.run();
    }
}