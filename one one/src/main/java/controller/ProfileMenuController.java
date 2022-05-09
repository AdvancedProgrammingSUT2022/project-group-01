package controller;

import model.Database;

import java.util.HashMap;

public class ProfileMenuController {
    Database database;

    public ProfileMenuController(Database database){
        this.database = database;
    }

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

    private String changeNickname(HashMap<String, String> args){
        String nickname = args.get("new");
        if(database.findUserByNickname(nickname)!=null)
            return String.format("user with nickname %s already exist!", nickname);
        ProgramController.getLoggedInUser().setNickname(nickname);
        return "nickname changed successfully!";
    }

    private String changePassword(HashMap<String, String> args){
        String currentPassword = args.get("current");
        String newPassword = args.get("new");
        if(!ProgramController.getLoggedInUser().getPassword().equals(currentPassword))
            return "current password is invalid";
        if(currentPassword.equals(newPassword))
            return "please enter a new password";
        ProgramController.getLoggedInUser().setPassword(newPassword);
        return "password changed successfully!";
    }

    private String changeUsername(HashMap<String, String> args){
        String newUsername = args.get("new");
        if(database.findUserByUsername(newUsername) != null)
            return String.format("user with username %s already exist!", newUsername);
        ProgramController.getLoggedInUser().setUsername(newUsername);
        return "username changed succesfully!";
    }

    public String menuEnter(HashMap<String, String> args){
        return "menu navigation is not possible";
    }

    public String currentMenu(HashMap<String, String> args){
        return "Profile Menu";
    }

    public String menuExit(HashMap<String, String> args){
        ProgramController.setCurrentMenu(Menus.MAIN_MENU);
        return "Done!";
    }
}
