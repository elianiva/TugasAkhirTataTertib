package my.id.elianiva.presentation.tui.menu;

import my.id.elianiva.presentation.tui.utils.ScreenUtils;

public class BackMenu extends MenuBase {
    public BackMenu() {
        super("Back");
    }

    @Override
    public boolean handle() {
        ScreenUtils.clearScreen();
        return false;
    }
}
