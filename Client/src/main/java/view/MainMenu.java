package view;

import controller.*;
import utils.Commands;

import java.lang.reflect.Method;
import java.util.HashMap;

public class MainMenu {

    private MainMenuController controller;
    protected HashMap<CommandAction, Commands> commands;
    {
        commands = new HashMap<>();
        for (Method method : MainMenuController.class.getMethods()) {
            GameCommand annotation = method.getAnnotation(GameCommand.class);
            if(annotation != null){
                commands.put(
                        args -> (String) method.invoke(controller, args),
                        annotation.command()
                );
            }
        }
    }

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
