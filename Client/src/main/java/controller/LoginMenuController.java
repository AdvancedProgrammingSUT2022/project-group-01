package controller;

import lombok.Getter;
import model.Database;
import model.User;
import network.Request;
import network.Response;
import utils.Commands;

import javax.xml.crypto.Data;
import java.util.*;

@Getter
public class LoginMenuController {
	@GameCommand(command = Commands.LOGIN) // Server-Side, Checked
	public String login(HashMap<String, String> args) {
		Request request = new Request("Method Invoke");
		request.setToken("");
		request.addData("method", "/login");
		request.addData("args", new Object[]{args.get("username"), args.get("password")});
		Response response = NetworkController.send(request);
		if(response.getStatus() == 200) {
			ProgramController.setLoggedInUser((User) response.get("user"));
			ProgramController.getLoggedInUser().setToken((String) response.get("token"));
			ProgramController.setCurrentMenu(Menus.MAIN_MENU);
		}
		return response.getMessage();
	}

	@GameCommand(command = Commands.REGISTER) // Server-Side, Checked
	public String register(HashMap<String, String> args) {
		Request request = new Request("Method Invoke");
		request.setToken("");
		request.addData("method", "/register");
		request.addData("args", new Object[]{args.get("username"), args.get("password"), args.get("nickname"), "/images/default_avatar.png"});
		Response response = NetworkController.send(request);
		return response.getMessage();
	}

	@GameCommand(command = Commands.MENU_ENTER) // Client-side
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

	@GameCommand(command = Commands.CURRENT_MENU) // Client-side
	public String currentMenu(HashMap<String, String> args){
		return "Login Menu";
	}

	@GameCommand(command = Commands.MENU_EXIT) // Client-side
	public String menuExit(HashMap<String, String> args){
		ProgramController.setCurrentMenu(Menus.EXIT);
		return "goodbye :)";
	}
}