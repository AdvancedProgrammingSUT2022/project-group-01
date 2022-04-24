package view;

import controller.LoginMenuController;
import utils.Commands;

import java.util.HashMap;

public class LoginMenu {

    LoginMenuController controller;
    private static HashMap<CommandAction, Commands> commands;

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

		}};
    }

    /**
     * @param controller
     */
    public LoginMenu(LoginMenuController controller) {
        this.controller = controller;
    }

    public static void test() {
        System.out.println("salam");
    }

    public void run() {
        while (true) {
            Menu.handleCommand(commands, Menu.getInput());
        }
    }

}