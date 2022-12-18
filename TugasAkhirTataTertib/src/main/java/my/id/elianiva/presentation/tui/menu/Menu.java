package my.id.elianiva.presentation.tui.menu;

import my.id.elianiva.presentation.tui.utils.InputScanner;

public class Menu {
    public static boolean showMenu(InputScanner scanner, String title, MenuBase[] menus) {
        System.out.println(title);
        for (int i = 0; i < menus.length; i++) {
            System.out.printf("%d. %s\n", i + 1, menus[i].name);
        }
        int selectedMenu = scanner.getPositiveInteger("Select menu: ", menus.length);
        return menus[selectedMenu - 1].handle();
    }
}
