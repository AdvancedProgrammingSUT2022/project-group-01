package view;

import controller.*;
import utils.Commands;

import java.lang.reflect.Method;
import java.util.HashMap;

public class ProfileMenu {
    ProfileMenuController controller;

    public ProfileMenu(ProfileMenuController controller){
        this.controller = controller;
    }

    HashMap<CommandAction, Commands> commands;
    {
        commands = new HashMap<>();
        for (Method method : ProfileMenuController.class.getMethods()) {
            GameCommand annotation = method.getAnnotation(GameCommand.class);
            if(annotation != null){
                commands.put(
                        args -> (String) method.invoke(controller, args),
                        annotation.command()
                );
            }
        }
    }

    public void run(){
        while (ProgramController.getCurrentMenu() == Menus.PROFILE_MENU) {
            Menu.handleCommand(commands, Menu.getInput());
        }
    }
}
