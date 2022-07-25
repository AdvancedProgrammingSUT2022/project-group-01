package controller;

import lombok.Getter;
import model.Database;
import network.Request;
import network.Response;
import utils.Commands;

import java.util.HashMap;

public class ProfileMenuController {

    @GameCommand(command = Commands.PROFILE_CHANGE) // Server-Side, Unimplemented
    public String profileChange(HashMap<String, String> args){
        String section = args.get("section");
        if(section.equals("password"))
            return changePassword(args);
        else if(section.equals("nickname"))
            return changeNickname(args);
        else if(section.equals("username"))
            return changeUsername(args);
        else
            return "invalid changing!";
    }

    // Server-Side, Unchecked
    private String changeNickname(HashMap<String, String> args){
        Request request = new Request("Method Invoke");
        request.setToken("");
        request.addData("method", "/changeNickname");
        request.addData("args", new Object[]{args.get("nickname")});
        Response response = NetworkController.send(request);
        if(response.getStatus() == 200)
            ProgramController.getLoggedInUser().setNickname(args.get("nickname"));
        return response.getMessage();
    }

    // Server-Side, Unchecked
    private String changePassword(HashMap<String, String> args){
        Request request = new Request("Method Invoke");
        request.setToken("");
        request.addData("method", "/changePassword");
        request.addData("args", new Object[]{args.get("current"), args.get("new")});
        Response response = NetworkController.send(request);
        if(response.getStatus() == 200)
            ProgramController.getLoggedInUser().setPassword(args.get("new"));
        return response.getMessage();
    }

    // Client-Side
    private String changeUsername(HashMap<String, String> args){
        return "you can't change your username!";
    }

    @GameCommand(command = Commands.MENU_ENTER) // Client-side
    public String menuEnter(HashMap<String, String> args){
        return "menu navigation is not possible!";
    }

    @GameCommand(command = Commands.CURRENT_MENU) // Client-side
    public String currentMenu(HashMap<String, String> args){
        return "profile menu";
    }

    @GameCommand(command = Commands.MENU_EXIT) // Client-side
    public String menuExit(HashMap<String, String> args){
        ProgramController.setCurrentMenu(Menus.MAIN_MENU);
        return "done!";
    }
}

