package view;

import controller.InfoMenuController;
import controller.LoginMenuController;
import controller.Menus;
import controller.ProgramController;
import utils.Commands;

import java.util.HashMap;

public class InfoMenu {
	private InfoMenuController controller;
	protected HashMap<CommandAction, Commands> commands;

	public InfoMenu(InfoMenuController infoMenuController){
		this.controller = infoMenuController;
	}

	{
		commands = new HashMap<>(){{
			put(new CommandAction() {
				public String action(HashMap<String, String> args) {
					return controller.menuEnter(args);
				}
			}, Commands.INFO);
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
					return controller.activeUnit(args);
				}
			}, Commands.ACTIVE_UNIT);
			put(new CommandAction() {
				public String action(HashMap<String, String> args) {
					return controller.militaryOverview(args);
				}
			}, Commands.SHOW_MILITARY_OVERVIEW);
			put(new CommandAction() {
				public String action(HashMap<String, String> args) {
					return controller.cityScreen(args);
				}
			}, Commands.SHOW_CITY_SCREEN);
			put(new CommandAction() {
				public String action(HashMap<String, String> args) {
					return controller.economicOverview(args);
				}
			}, Commands.SHOW_ECONOMIC_OVERVIEW);
		}};

	}

	public void run(){
		while (ProgramController.getCurrentMenu() == Menus.INFO_MENU) {
			Menu.handleCommand(commands, Menu.getInput());
		}
	}
}
