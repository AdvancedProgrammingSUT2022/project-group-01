package view;

import controller.LoginMenuController;
import controller.Menus;
import controller.ProgramController;
import utils.Commands;
import java.util.HashMap;
public class LoginMenu {

    LoginMenuController controller;
    private final HashMap<CommandAction, Commands> commands;

    {
        commands = new HashMap<CommandAction, Commands>() {{
            put(new CommandAction() {
                public String action(HashMap<String, String> args) {
                    return controller.currentMenu(args);
                }
            }, Commands.CURRENT_MENU);
            put(new CommandAction() {
                public String action(HashMap<String, String> args) {
                    return controller.login(args);
                }
            }, Commands.LOGIN);
            put(new CommandAction() {
                public String action(HashMap<String, String> args) {
                    return controller.register(args);
                }
            }, Commands.REGISTER);
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
                public String action(HashMap<String, String> args) {
                    return controller.menuExit(args);
                }
            }, Commands.MENU_EXIT);
        }};
    }

    /**
     * @param controller
     */
    public LoginMenu(LoginMenuController controller) {
        this.controller = controller;
    }

    public void run() {
        while (ProgramController.getCurrentMenu() == Menus.LOGIN_MENU) {
            Menu.handleCommand(commands, Menu.getInput());
        }
    }

}