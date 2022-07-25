package view;

import controller.*;
import lombok.SneakyThrows;
import utils.Commands;

import java.lang.reflect.Method;
import java.util.HashMap;

public class InfoMenu {
	private InfoMenuController controller;
	protected HashMap<CommandAction, Commands> commands;

	public InfoMenu(InfoMenuController infoMenuController){
		this.controller = infoMenuController;
	}

	{
		commands = new HashMap<>();
		for (Method method : InfoMenuController.class.getMethods()) {
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
		while (ProgramController.getCurrentMenu() == Menus.INFO_MENU) {
			Menu.handleCommand(commands, Menu.getInput());
		}
	}
}
