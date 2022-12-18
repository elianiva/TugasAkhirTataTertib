package my.id.elianiva.presentation.tui.menu;

public abstract class MenuBase {
    public String name;

    public MenuBase(String name) {
        this.name = name;
    }

    /**
     * Handler for the menu.
     * Return true if you want to continue on the current section of the menu.
     * Return false if you want to go back to the previous menu.
     */
    public boolean handle() {
        System.out.println("No handler for menu: " + name);
        return false;
    }
}
