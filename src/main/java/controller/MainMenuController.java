package controller;

import model.*;
import java.util.*;
import java.util.Map;

public class MainMenuController {

	Database database;

	public MainMenuController(Database database){
		this.database = database;
	}
	/**
	 * 
	 * @param args
	 */
	public String menuEnter(HashMap<String, String> args) {
		String menuName = args.get("section");
		if(menuName.equals("profile")){
			ProgramController.setCurrentMenu(Menus.PROFILE_MENU);
			return "Done!";
		}else if(menuName.equals("game")){
			return "user play game [players] instead";
		}else if(menuName.equals("login")){
			ProgramController.setCurrentMenu(Menus.LOGIN_MENU);
			return "Done!";
		}
		return "invalid navigation!";
	}

	/**
	 * 
	 * @param args
	 */
	public String menuExit(HashMap<String, String> args) {
		ProgramController.setCurrentMenu(Menus.LOGIN_MENU);
		return "Done!";
	}

	/**
	 *
	 * @param args
	 * @return
	 */
	public String currentMenu(HashMap<String, String> args){
		return "Main Menu";
	}

	public String playGame(HashMap<String, String> args){
		Vector<User> gamePlayers = new Vector<>();
		for(Map.Entry<String, String> set : args.entrySet()){
			if(set.getKey().contains("player")){
				User user = database.findUserByUsername(set.getValue());
				if(user == null)
					return "invalid players!";
				gamePlayers.add(user);
			}
		}
		GameInitializer gameInitializer = new GameInitializer();
		Game game = gameInitializer.startGame(gamePlayers, 17);
		ProgramController.setCurrentMenu(Menus.GAME_MENU);
		ProgramController.setGame(game);
		return "Game Started!";
	}

	public String logOut(HashMap<String, String> args){
		ProgramController.setLoggedInUser(null);
		ProgramController.setCurrentMenu(Menus.LOGIN_MENU);
		return "Done!";
	}



}