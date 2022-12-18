package my.id.elianiva.presentation.tui.menu.user;

import my.id.elianiva.core.models.User;
import my.id.elianiva.presentation.tui.menu.MenuBase;
import my.id.elianiva.presentation.tui.utils.InputScanner;
import my.id.elianiva.presentation.tui.utils.ScreenUtils;
import my.id.elianiva.usecase.UserService;
import my.id.elianiva.usecase.exceptions.UserAlreadyExistsException;

public class AddUserMenu extends MenuBase {
    private final InputScanner scanner;
    private final UserService userService;

    public AddUserMenu(InputScanner scanner, UserService userService) {
        super("Add User");
        this.scanner = scanner;
        this.userService = userService;
    }

    @Override
    public boolean handle() {
        System.out.println("-- New User Data --");
        String username = scanner.getNonEmptyStringWithLimit("Username: ", "The username can't be empty!", 1, User.MAX_USERNAME_LENGTH);
        String password = scanner.getNonEmptyStringWithLimit("Password: ", "The password can't be empty!", 0, User.MIN_PASSWORD_LENGTH);
        try {
            userService.addUser(username, password);
            ScreenUtils.clearScreen();
            System.out.println("New user has been successfully added!");
        } catch (UserAlreadyExistsException e) {
            ScreenUtils.clearScreen();
            System.out.println(e.getMessage());
        }
        return true;
    }
}
