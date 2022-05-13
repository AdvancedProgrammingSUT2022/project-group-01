package view;

import controller.GameCommand;
import controller.GameMenuController;
import controller.Menus;
import controller.ProgramController;
import utils.Commands;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.function.BiFunction;

public class GameMenu{

	private GameMenuController controller;
	protected HashMap<CommandAction, Commands> commands;

	{
		commands = new HashMap<CommandAction, Commands>() {{
			put(new CommandAction() {
				public String action(HashMap<String, String> args) {
					return controller.selectUnit(args);
				}
			}, Commands.SELECT_UNIT);
			put(new CommandAction() {
				public String action(HashMap<String, String> args) {
					return controller.selectCity(args);
				}
			}, Commands.SELECT_CITY);
			put(new CommandAction() {
				public String action(HashMap<String, String> args) {
					return controller.unitMove(args);
				}
			}, Commands.UNIT_MOVE);
			put(new CommandAction() {
				public String action(HashMap<String, String> args) {
					return controller.unitSleep(args);
				}
			}, Commands.UNIT_SLEEP);
			put(new CommandAction() {
				public String action(HashMap<String, String> args) {
					return controller.unitAlert(args);
				}
			}, Commands.UNIT_ALERT);
			put(new CommandAction() {
				public String action(HashMap<String, String> args) {
					return controller.unitFortify(args);
				}
			}, Commands.UNIT_FORTIFY);
			put(new CommandAction() {
				public String action(HashMap<String, String> args) {
					return controller.unitFortifyUntilHeal(args);
				}
			}, Commands.UNIT_FORTIFY_UNTIL_HEAL);
			put(new CommandAction() {
				public String action(HashMap<String, String> args) {
					return controller.unitGarrison(args);
				}
			}, Commands.UNIT_GARRISON);
			put(new CommandAction() {
				public String action(HashMap<String, String> args) {
					return controller.unitSetup(args);
				}
			}, Commands.UNIT_SETUP);
			put(new CommandAction() {
				public String action(HashMap<String, String> args) {
					return controller.unitAttack(args);
				}
			}, Commands.UNIT_ATTACK);
			put(new CommandAction() {
				public String action(HashMap<String, String> args) {
					return controller.unitCancelMission(args);
				}
			}, Commands.UNIT_CANCEL_MISSION);
			put(new CommandAction() {
				public String action(HashMap<String, String> args) {
					return controller.unitWake(args);
				}
			}, Commands.UNIT_WAKE);
			put(new CommandAction() {
				public String action(HashMap<String, String> args) {
					return controller.unitDelete(args);
				}
			}, Commands.UNIT_DELETE);
			put(new CommandAction() {
				public String action(HashMap<String, String> args) {
					return controller.unitBuild(args);
				}
			}, Commands.UNIT_BUILD);
			put(new CommandAction() {
				public String action(HashMap<String, String> args) {
					return controller.unitRemove(args);
				}
			}, Commands.UNIT_REMOVE);
			put(new CommandAction() {
				public String action(HashMap<String, String> args) {
					return controller.unitRepair(args);
				}
			}, Commands.UNIT_REPAIR);
			put(new CommandAction() {
				public String action(HashMap<String, String> args) {
					return controller.selectUnit(args);
				}
			}, Commands.SELECT_UNIT);
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
					return controller.increaseResource(args);
				}
			}, Commands.INCREASE_RESOURCE);
			put(new CommandAction() {
				public String action(HashMap<String, String> args) {
					return controller.mapMove(args);
				}
			}, Commands.MAP_MOVE);
			put(new CommandAction() {
				public String action(HashMap<String, String> args) {
					return controller.mapShow(args);
				}
			}, Commands.MAP_SHOW);




			for (Method method : GameMenuController.class.getDeclaredMethods()) {
				final GameCommand annotation = method.getAnnotation(GameCommand.class);
				if(annotation == null) continue;
				put(new CommandAction() {
					@Override
					public String action(HashMap<String, String> args) {
						try {
							return (String) method.invoke(controller, args);
						} catch (IllegalAccessException | InvocationTargetException e){
							throw new RuntimeException(e);
						}
					}
				}, annotation.command());
			}
		}};

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
		}
	}
}