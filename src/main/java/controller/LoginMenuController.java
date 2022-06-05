package controller;

import lombok.Getter;
import model.Database;
import model.User;

import javax.xml.crypto.Data;
import java.util.*;

@Getter
public class LoginMenuController {

	private final Database database;

	public LoginMenuController(Database database) {
		this.database = database;
	}

	/**
	 * 
	 * @param args
	 */
	public String login(HashMap<String, String> args) {
		String username = args.get("username");
		String password = args.get("password");
		User user = database.findUserByUsername(username);
		if(user == null || !user.getPassword().equals(password))
			return "Username and password didn’t match!";
		ProgramController.setLoggedInUser(user);
		ProgramController.setCurrentMenu(Menus.MAIN_MENU);
		return "user logged in successfully!";
	}

	/**
	 *
	 * @param args
	 */
	public String register(HashMap<String, String> args) {
		String username = args.get("username");
		String nickname = args.get("nickname");
		String password = args.get("password");
		String avatarUrl = args.get("avatarURL");
		if(database.findUserByUsername(username) != null)
			return String.format("user with username %s already exist!", username);
		if(database.findUserByNickname(nickname) != null)
			return String.format("user with nickname %s already exist!", nickname);
		database.addUser(username, nickname, password, avatarUrl);
		return "user created successfully!";
	}

	public String menuEnter(HashMap<String, String> args){
		if(ProgramController.getLoggedInUser() == null)
			return "login first!";
		String menuName = args.get("section");
		if(menuName.equals("main")){
			ProgramController.setCurrentMenu(Menus.MAIN_MENU);
			return "Done!";
		}
		return "invalid navigation!";
	}

	public String currentMenu(HashMap<String, String> args){
		return "Login Menu";
	}

	public String menuExit(HashMap<String, String> args){
		ProgramController.setCurrentMenu(Menus.EXIT);
		return "goodbye :)";
	}

}