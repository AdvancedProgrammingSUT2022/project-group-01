package controller;

import com.thoughtworks.xstream.XStream;
import lombok.Getter;
import model.*;
import network.Request;
import network.Response;
import utils.Commands;

import java.util.*;
import java.util.Map;
@Getter
public class MainMenuController {

	Database database;

	public MainMenuController(Database database){
		this.database = database;
	}

	@GameCommand(command = Commands.MENU_ENTER)
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

	@GameCommand(command = Commands.MENU_EXIT)
	public String menuExit(HashMap<String, String> args) {
		ProgramController.setCurrentMenu(Menus.LOGIN_MENU);
		return "Done!";
	}

	@GameCommand(command = Commands.CURRENT_MENU)
	public String currentMenu(HashMap<String, String> args){
		return "Main Menu";
	}

	@GameCommand(command = Commands.PLAY_GAME)
	public String playGame(HashMap<String, String> args){
		Request request = new Request("me Game");
		request.setToken(ProgramController.getLoggedInUser().getToken());

		XStream xStream = new XStream();
		xStream.setMode(XStream.ID_REFERENCES);

		Game game = (Game) xStream.fromXML(NetworkController.send(new Request("invoke method")).getMessage());
		// System.out.println("Sex " + game.getTurn());
		ProgramController.setCurrentMenu(Menus.GAME_MENU);
		ProgramController.setGame(game);
		return "Game Started!";
	}

	@GameCommand(command = Commands.CREATE_GAME)
	public String createGame(HashMap<String, String> args){
		Request request = new Request("Method Invoke");
		request.setToken(ProgramController.getLoggedInUser().getToken());
		request.addData("args", new Object[]{Integer.parseInt(args.get("size"))}).addData("method", "/game/create");
		Response response = NetworkController.send(request);
		return response.getMessage() + "\ngame code is " + response.get("game code");
	}

	@GameCommand(command = Commands.START_GAME)
	public String startGame(HashMap<String, String> args){
		Request request = new Request("Method Invoke");
		request.setToken(ProgramController.getLoggedInUser().getToken());
		request.addData("args", new Object[]{}).addData("method", "/game/start");
		Response response = NetworkController.send(request);
		return response.getMessage();
	}


	@GameCommand(command = Commands.JOIN_GAME)
	public String joinGame(HashMap<String, String> args){
		Request request = new Request("Method Invoke");
		request.setToken(ProgramController.getLoggedInUser().getToken());
		request.addData("args", new Object[]{args.get("code")}).addData("method", "/game/join");
		Response response = NetworkController.send(request);
		return response.getMessage();
	}

	@GameCommand(command = Commands.LOGOUT)
	public String logOut(HashMap<String, String> args){
		ProgramController.setLoggedInUser(null);
		ProgramController.setCurrentMenu(Menus.LOGIN_MENU);
		return "Done!";
	}
}