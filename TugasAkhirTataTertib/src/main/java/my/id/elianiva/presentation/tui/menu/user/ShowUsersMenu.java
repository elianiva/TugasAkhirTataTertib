package my.id.elianiva.presentation.tui.menu.user;

import my.id.elianiva.core.models.User;
import my.id.elianiva.presentation.tui.menu.BackMenu;
import my.id.elianiva.presentation.tui.menu.ExitMenu;
import my.id.elianiva.presentation.tui.menu.Menu;
import my.id.elianiva.presentation.tui.menu.MenuBase;
import my.id.elianiva.presentation.tui.utils.InputScanner;
import my.id.elianiva.presentation.tui.utils.ScreenUtils;
import my.id.elianiva.presentation.tui.utils.TableRenderer;
import my.id.elianiva.usecase.UserService;

import java.util.List;

public class ShowUsersMenu extends MenuBase {
    private final InputScanner scanner;
    private final UserService userService;

    public ShowUsersMenu(InputScanner scanner, UserService userService) {
        super("Show Users");
        this.scanner = scanner;
        this.userService = userService;
    }

    private boolean showUsersTable(List<User> users) {
        ScreenUtils.renderTitle("Showing Users Table");
        TableRenderer.renderUsersTable(users);
        return Menu.showMenu(scanner, "Users Table Menu", new MenuBase[]{
                new AddUserMenu(scanner, userService),
                new EditUserMenu(scanner, userService),
                new DeleteUserMenu(scanner, userService),
                new BackMenu(),
                new ExitMenu(),
        });
    }

    @Override
    public boolean handle() {
        ScreenUtils.clearScreen();
        while (true) {
            List<User> users = userService.getAllUsers();
            boolean shouldContinue = showUsersTable(users);
            if (shouldContinue) continue;
            break;
        }
        return true;
    }
}
