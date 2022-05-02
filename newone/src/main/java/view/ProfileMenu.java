package view;

import controller.Menus;
import controller.ProfileMenuController;
import controller.ProgramController;
import utils.Commands;

import java.util.HashMap;

public class ProfileMenu {
    ProfileMenuController controller;

    public ProfileMenu(ProfileMenuController controller){
        this.controller = controller;
    }

    HashMap<CommandAction, Commands> commands;
    {
        commands = new HashMap<>() {{
            put(new CommandAction(){
                public String action(HashMap<String, String> args){
                    return controller.profileChange(args);
                }
                }, Commands.PROFILE_CHANGE);
            put(new CommandAction() {
                    public String action(HashMap<String, String> args) {
                        return controller.menuEnter(args);
                    }
                }, Commands.MENU_ENTER);
            put(new CommandAction() {
                public String action(HashMap<String, String> args) {
                    return controller.currentMenu(args);
                }
            }, Commands.CURRENT_MENU);
            put(new CommandAction() {
                public String action(HashMap<String, String> args){
                    return controller.menuExit(args);
                }
            }, Commands.MENU_EXIT);
        }};
    }

    public void run(){
        while (ProgramController.getCurrentMenu() == Menus.PROFILE_MENU) {
            Menu.handleCommand(commands, Menu.getInput());
        }
    }
}
