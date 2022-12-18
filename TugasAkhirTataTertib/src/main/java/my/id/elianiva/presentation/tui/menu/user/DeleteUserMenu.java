package my.id.elianiva.presentation.tui.menu.user;

import my.id.elianiva.presentation.tui.menu.MenuBase;
import my.id.elianiva.presentation.tui.utils.InputScanner;
import my.id.elianiva.presentation.tui.utils.ScreenUtils;
import my.id.elianiva.usecase.UserService;
import my.id.elianiva.usecase.exceptions.UserNotFoundException;

public class DeleteUserMenu extends MenuBase {
    private final InputScanner scanner;
    private final UserService userService;

    public DeleteUserMenu(InputScanner scanner, UserService userService) {
        super("Delete User");
        this.scanner = scanner;
        this.userService = userService;
    }

    @Override
    public boolean handle() {
        String username = scanner.getNonEmptyString("Insert the username of the user you want to delete: ", "The username can't be empty!");
        try {
            userService.deleteUser(username);
            ScreenUtils.clearScreen();
            System.out.println("The user with a username of " + username + " has been deleted!");
        } catch (UserNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return true;
    }
}
