package my.id.elianiva.presentation.tui;

import my.id.elianiva.core.Application;
import my.id.elianiva.core.interfaces.IPresenter;
import my.id.elianiva.core.models.User;
import my.id.elianiva.presentation.tui.menu.ExitMenu;
import my.id.elianiva.presentation.tui.menu.LogoutMenu;
import my.id.elianiva.presentation.tui.menu.Menu;
import my.id.elianiva.presentation.tui.menu.MenuBase;
import my.id.elianiva.presentation.tui.menu.rule.ShowRulesMenu;
import my.id.elianiva.presentation.tui.menu.student.ShowStudentsMenu;
import my.id.elianiva.presentation.tui.menu.user.ShowUsersMenu;
import my.id.elianiva.presentation.tui.utils.InputScanner;
import my.id.elianiva.presentation.tui.utils.ScreenUtils;
import my.id.elianiva.usecase.RuleService;
import my.id.elianiva.usecase.StudentService;
import my.id.elianiva.usecase.UserService;
import my.id.elianiva.usecase.exceptions.InvalidCredentialException;
import my.id.elianiva.usecase.exceptions.UserNotFoundException;

public class TuiPresenter implements IPresenter {
    private final UserService userService;
    private final StudentService studentService;
    private final RuleService ruleService;
    private final InputScanner scanner;

    public TuiPresenter(InputScanner scanner, UserService userService, StudentService studentService, RuleService ruleService) {
        this.scanner = scanner;
        this.userService = userService;
        this.studentService = studentService;
        this.ruleService = ruleService;
    }

    @Override
    public boolean showLoginScreen() {
        ScreenUtils.renderTitle("Welcome to the Point of Order System!");
        System.out.println("Please login before using the system!");

        String username = scanner.getNonEmptyString("Username: ", "Username can't be empty!");
        String password = scanner.getNonEmptyString("Password: ", "Password can't be empty!");

        try {
            // this actually returns the User instance but let's ignore that for now
            User user = userService.login(username, password);
            ScreenUtils.clearScreen();
            ScreenUtils.renderTitle("Logged in successfully! Welcome back, " + user.getUsername() + "!");
            return true;
        } catch (UserNotFoundException | InvalidCredentialException exc) {
            ScreenUtils.clearScreen();
            System.out.println(exc.getMessage());
            return false;
        }
    }

    @Override
    public boolean showMainMenu()  {
        while (true) {
            boolean shouldContinue = Menu.showMenu(scanner, "Point of Order System Main Menu", new MenuBase[]{
                    new ShowUsersMenu(scanner, userService),
                    new ShowStudentsMenu(scanner, studentService, ruleService),
                    new ShowRulesMenu(scanner, ruleService),
                    new LogoutMenu(),
                    new ExitMenu(),
            });
            if (shouldContinue) continue;
            return true;
        }
    }
}
