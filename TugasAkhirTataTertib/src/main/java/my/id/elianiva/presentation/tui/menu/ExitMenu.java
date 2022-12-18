package my.id.elianiva.presentation.tui.menu;

import my.id.elianiva.presentation.tui.utils.ScreenUtils;

public class ExitMenu extends MenuBase {
    public ExitMenu() {
        super("Exit");
    }

    @Override
    public boolean handle() {
        ScreenUtils.clearScreen();
        System.out.println("Have a good day! :)");
        System.exit(0);
        return false;
    }
}
