package view;

import controller.*;
import utils.Commands;

import java.lang.reflect.Method;
import java.util.HashMap;

public class GameMenu{

	private GameMenuController controller;
	protected HashMap<CommandAction, Commands> commands;

	{
		commands = new HashMap<>();
		for (Method method : GameMenuController.class.getMethods()) {
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
	 *
	 * @param controller
	 */
	public GameMenu(GameMenuController controller) {
		this.controller = controller;
	}

	public void run() {
		while (ProgramController.getCurrentMenu() == Menus.GAME_MENU) {
			Menu.handleCommand(commands, Menu.getInput());
			System.out.print(controller.showCurrentMap());
		}
	}
}