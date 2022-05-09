package controller;

import lombok.Getter;
import lombok.Setter;
import model.Database;
import model.Game;
import model.User;
import view.GameMenu;
import view.LoginMenu;
import view.MainMenu;
import view.ProfileMenu;

import java.io.IOException;

public class ProgramController {

    private static User loggedInUser = null;
    @Getter
    protected static Database database = new Database();
    private static Menus currentMenu;
    @Getter @Setter
    private static Game game;
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

    public void run() {
        database.load();
        TileController.initializeEnums();
        while(currentMenu != Menus.EXIT){
            switch (currentMenu){
                case LOGIN_MENU:{
                    LoginMenuController loginMenuController = new LoginMenuController(database);
                    LoginMenu loginMenu = new LoginMenu(loginMenuController);
                    loginMenu.run();
                }break;
                case PROFILE_MENU:{
                    ProfileMenuController profileMenuController = new ProfileMenuController(database);
                    ProfileMenu profileMenu = new ProfileMenu(profileMenuController);
                    profileMenu.run();
                }break;
                case INFO_MENU:{
                    //TODO talk to safari
                }break;
                case GAME_MENU:{
                    MapController mapController = new MapController(game);
                    GameController gameController = new GameController(game, mapController);
                    GameMenuController gameMenuController = new GameMenuController(game,gameController);
                    GameMenu gameMenu = new GameMenu(gameMenuController);
                    gameMenu.run();
                }break;
                case MAIN_MENU:{
                    MainMenuController mainMenuController = new MainMenuController(database);
                    MainMenu mainMenu = new MainMenu(mainMenuController);
                    mainMenu.run();
                }break;
            }
        }
        database.save();
    }
}
