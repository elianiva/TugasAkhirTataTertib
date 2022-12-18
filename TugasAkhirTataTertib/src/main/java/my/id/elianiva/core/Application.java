package my.id.elianiva.core;

import my.id.elianiva.core.interfaces.IPresenter;

public class Application {
    private final IPresenter ui;

    public Application(IPresenter presenter) {
        this.ui = presenter;
    }

    public void run() {
        while (true) {
            boolean isAuthenticated = ui.showLoginScreen();
            if (!isAuthenticated) continue;
            boolean shouldContinue = ui.showMainMenu();
            if (shouldContinue) continue;
            break;
        }
    }
}
