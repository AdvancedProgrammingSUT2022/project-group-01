package controller;

import model.Database;
import model.User;
import view.LoginMenu;

public class ProgramController {

    private static User loggedInUser = null;
    protected static Database database = new Database();
    private static Menus currentMenu;

    public ProgramController() {
        currentMenu = Menus.LOGIN_MENU;
    }

    public static Menus getCurrentMenu() {
        return currentMenu;
    }

    public static void setCurrentMenu(Menus currentMenu) {
        ProgramController.currentMenu = currentMenu;
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
