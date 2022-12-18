package my.id.elianiva.presentation.tui.menu.user;

import my.id.elianiva.core.models.User;
import my.id.elianiva.presentation.tui.menu.MenuBase;
import my.id.elianiva.presentation.tui.utils.InputScanner;
import my.id.elianiva.presentation.tui.utils.ScreenUtils;
import my.id.elianiva.usecase.UserService;
import my.id.elianiva.usecase.exceptions.UserNotFoundException;

public class EditUserMenu extends MenuBase {
    private final InputScanner scanner;
    private final UserService userService;

    public EditUserMenu(InputScanner scanner, UserService userService) {
        super("Edit User");
        this.scanner = scanner;
        this.userService = userService;
    }

    @Override
    public boolean handle() {
        String oldUsername;
        User oldUser;

        while (true) {
            try {
                oldUsername = scanner.getNonEmptyString("Insert the username of the user you want to edit: ", "The username can't be empty!");
                oldUser = userService.getUserByUsername(oldUsername); // only used to check if the user exists or not
                break;
            } catch (UserNotFoundException e) {
                ScreenUtils.clearScreen();
                System.out.println(e.getMessage());
            }
        }

        while (true) {
            System.out.println("-- New User Data --");
            String newUsername = scanner.getNonEmptyStringWithLimit("Username (" + oldUser.getUsername() + "): ", "The username can't be empty!", 1, User.MAX_USERNAME_LENGTH, true);
            String password = scanner.getNonEmptyStringWithLimit("Password: ", "The password can't be empty!", User.MIN_PASSWORD_LENGTH, Integer.MAX_VALUE, true);
            try {
                userService.editUser(oldUsername, newUsername, password);
                ScreenUtils.clearScreen();
                System.out.println("The user with a username of " + oldUsername + " has been successfully edited!");
                break;
            } catch (UserNotFoundException e) {
                ScreenUtils.clearScreen();
                System.out.println(e.getMessage());
            }
        }

        return true;
    }
}