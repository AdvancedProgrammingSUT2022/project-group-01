package view;

import controller.*;
import utils.Commands;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
public class LoginMenu {

    LoginMenuController controller;
    private final HashMap<CommandAction, Commands> commands;
    {
        commands = new HashMap<>();
        for (Method method : LoginMenuController.class.getMethods()) {
            GameCommand annotation = method.getAnnotation(GameCommand.class);
            if(annotation != null){
                commands.put(
                        args -> (String) method.invoke(controller, args),
                        annotation.command()
                );
            }
        }
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