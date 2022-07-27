package controller;

import lombok.Getter;
import lombok.Setter;
import model.Game;
import model.User;

import java.io.DataInputStream;

public class ProgramController {

	private static User loggedInUser = null;
	@Getter
	@Setter
	private final DataInputStream inputStream;

	@Getter
	@Setter
	private static Game game;

	public ProgramController(DataInputStream inputStream) {
		this.inputStream = inputStream;
	}

	public static User getLoggedInUser() {
		return loggedInUser;
	}

	public static void setLoggedInUser(User loggedInUser) {
		ProgramController.loggedInUser = loggedInUser;
	}

	public void run() {
//        database.load();
//        TileController.initializeEnums();
        /*
        while(currentMenu != Menus.EXIT){
            switch (currentMenu){
                case LOGIN_MENU:{
                    LoginMenuController loginMenuController = new LoginMenuController(database);
                    LoginMenu loginMenu = new LoginMenu(inputStream, loginMenuController);
                    loginMenu.run();
                }break;
                case PROFILE_MENU:{
                    ProfileMenuController profileMenuController = new ProfileMenuController(database);
                    ProfileMenu profileMenu = new ProfileMenu(inputStream, profileMenuController);
                    profileMenu.run();
                }break;
                case INFO_MENU:{
                    InfoMenuController infoMenuController = new InfoMenuController(game);
                    InfoMenu infoMenu = new InfoMenu(inputStream, infoMenuController);
                    infoMenu.run();
                }break;
                case GAME_MENU:{
                    MapController mapController = new MapController(game);
                    GameController gameController = new GameController(game, mapController);
                    CityController cityController = new CityController(game);
                    UnitController unitController = new UnitController(game);
                    WorkerController workerController = new WorkerController(game);
                    GameMenuController gameMenuController = new GameMenuController(game,gameController,cityController, unitController, workerController);
                    GameMenu gameMenu = new GameMenu(inputStream, gameMenuController);
                    gameMenu.run();
                }break;
                case MAIN_MENU:{
                    MainMenuController mainMenuController = new MainMenuController(database);
                    MainMenu mainMenu = new MainMenu(inputStream, mainMenuController);
                    mainMenu.run();
                }break;
            }
        }
        */

//        database.save();
	}
}
