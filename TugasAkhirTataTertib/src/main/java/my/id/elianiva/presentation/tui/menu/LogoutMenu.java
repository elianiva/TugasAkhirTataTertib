package my.id.elianiva.presentation.tui.menu;

import my.id.elianiva.presentation.tui.utils.ScreenUtils;

public class LogoutMenu extends MenuBase {
    public LogoutMenu() {
        super("Logout");
    }

    @Override
    public boolean handle() {
        ScreenUtils.clearScreen();
        return false;
    }
}
