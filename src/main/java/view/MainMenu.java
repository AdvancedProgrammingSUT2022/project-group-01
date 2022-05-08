package view;

import controller.MainMenuController;
import controller.Menus;
import controller.ProgramController;
import utils.Commands;

import java.util.HashMap;

public class MainMenu extends Menu{

    private MainMenuController controller;
    protected HashMap<CommandAction, Commands> commands;
    {
        commands = new HashMap<>(){{
            put(new CommandAction() {
                public String action(HashMap<String, String> args) {
                    return controller.menuEnter(args);
                }
            }, Commands.MENU_ENTER);
            put(new CommandAction() {
                public String action(HashMap<String, String> args) {
                    return controller.menuExit(args);
                }
            }, Commands.MENU_EXIT);
            put(new CommandAction() {
                public String action(HashMap<String, String> args) {
                    return controller.currentMenu(args);
                }
            }, Commands.CURRENT_MENU);
            put(new CommandAction() {
                public String action(HashMap<String, String> args) {
                    return controller.playGame(args);
                }
            }, Commands.PLAY_GAME);
            put(new CommandAction() {
                public String action(HashMap<String, String> args) {
                    return controller.logOut(args);
                }
            }, Commands.LOGOUT);
        }};
    }
    /**
     * @param controller
     */
    public MainMenu(MainMenuController controller) {
        this.controller = controller;
    }

    public void run() {
        while (ProgramController.getCurrentMenu() == Menus.MAIN_MENU) {
            Menu.handleCommand(commands, Menu.getInput());
        }
    }

    public void initCommands(){

    }

}
