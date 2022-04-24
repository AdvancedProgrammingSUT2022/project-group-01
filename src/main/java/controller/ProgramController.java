package controller;

import model.Database;
import model.User;
import view.LoginMenu;

enum Menus{
    MAIN_MENU,
    GAME_MENU,
    INFO_MENU,
    LOGIN_MENU
}

public class ProgramController {

    private static User loggedInUser = null;
    protected static Database database = new Database();
    private Menus currentMenu;

    public ProgramController() {
        currentMenu = Menus.LOGIN_MENU;
    }

    public Menus getCurrentMenu() {
        return currentMenu;
    }

    public void setCurrentMenu(Menus currentMenu) {
        this.currentMenu = currentMenu;
    }

    public static User getLoggedInUser() {
        return loggedInUser;
    }

    public static void setLoggedInUser(User loggedInUser) {
        ProgramController.loggedInUser = loggedInUser;
    }

    public void run(){
        LoginMenu lm = new LoginMenu(null);
        lm.run();
    }
}
