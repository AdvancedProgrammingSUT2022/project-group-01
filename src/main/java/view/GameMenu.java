package view;

import controller.GameMenuController;
import controller.Menus;
import controller.ProgramController;
import utils.Commands;

import java.util.HashMap;

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
					return controller.unitFoundCity(args);
				}
			}, Commands.UNIT_FOUND_CITY);
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
					return controller.unitPillage(args);
				}
			}, Commands.UNIT_PILLAGE);
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
					return controller.spawnUnit(args);
				}
			}, Commands.SPAWN_UNIT);
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
			put(new CommandAction() {
				public String action(HashMap<String, String> args) {
					return controller.addTechnology(args);
				}
			}, Commands.ADD_TECHNOLOGY);
			put(new CommandAction() {
				public String action(HashMap<String, String> args) {
					return controller.getResearchableTechnologies(args);
				}
			}, Commands.SHOW_RESEARCHABLE_TECHS);
			put(new CommandAction() {
				public String action(HashMap<String, String> args) {
					return controller.getCurrentResearch(args);
				}
			}, Commands.GET_CURRENT_RESEARCH);
			put(new CommandAction() {
				public String action(HashMap<String, String> args) {
					return controller.research(args);
				}
			}, Commands.RESEARCH);
			put(new CommandAction() {
				public String action(HashMap<String, String> args) {
					return controller.addBeaker(args);
				}
			}, Commands.ADD_BEAKER);
			put(new CommandAction() {
				public String action(HashMap<String, String> args) {
					return controller.changeResearch(args);
				}
			}, Commands.CHANGE_RESEARCH);
			put(new CommandAction() {
				public String action(HashMap<String, String> args) {
					return controller.cheatNextTurn(args);
				}
			}, Commands.CHEAT_NEXT_TURN);
			put(new CommandAction() {
				public String action(HashMap<String, String> args) {
					return controller.showNextTiles(args);
				}
			}, Commands.SHOW_NEXT_TILES);
			put(new CommandAction() {
				public String action(HashMap<String, String> args) {
					return controller.purchaseProduction(args);
				}
			}, Commands.PURCHASE_PRODUCTION);
			put(new CommandAction() {
				public String action(HashMap<String, String> args) {
					return controller.unitActionList(args);
				}
			}, Commands.UNIT_ACTION_LIST);
			put(new CommandAction() {
				public String action(HashMap<String, String> args) {
					return controller.listOfProductions(args);
				}
			}, Commands.LIST_OF_PRODUCTIONS);
			put(new CommandAction() {
				public String action(HashMap<String, String> args) {
					return controller.listOfAllProductions(args);
				}
			}, Commands.LIST_OF_ALL_OF_PRODUCTIONS);
			put(new CommandAction() {
				public String action(HashMap<String, String> args) {
					return controller.setProduction(args);
				}
			}, Commands.SET_PRODUCTION);
			put(new CommandAction() {
				public String action(HashMap<String, String> args) {
					return controller.getPurchasableTiles(args);
				}
			}, Commands.GET_PURCHASABLE_TILES);
			put(new CommandAction() {
				public String action(HashMap<String, String> args) {
					return controller.purchaseTile(args);
				}
			}, Commands.PURCHASE_TILE);
			put(new CommandAction() {
				public String action(HashMap<String, String> args) {
					return controller.purchaseTile(args);
				}
			}, Commands.PURCHASE_TILE);
			put(new CommandAction() {
				public String action(HashMap<String, String> args) {
					return controller.listOfPopulation(args);
				}
			}, Commands.LIST_OF_POPULATION);
			put(new CommandAction() {
				public String action(HashMap<String, String> args) {
					return controller.setTileForPopulation(args);
				}
			}, Commands.SET_TILE_FOR_POPULATION);
			put(new CommandAction() {
				public String action(HashMap<String, String> args) {
					return controller.deletePopulation(args);
				}
			}, Commands.DELETE_POPULATION_FROM_TILE);
			put(new CommandAction() {
				public String action(HashMap<String, String> args) {
					return controller.buildImprovement(args);
				}
			}, Commands.UNIT_BUILD_IMPROVEMENT);
			put(new CommandAction() {
				public String action(HashMap<String, String> args) {
					return controller.makeTileVisible(args);
				}
			}, Commands.MAKE_TILE_VISIBLE);
			put(new CommandAction() {
				public String action(HashMap<String, String> args) {
					return controller.createFeature(args);
				}
			}, Commands.CREATE_FEATURE);
			put(new CommandAction() {
				public String action(HashMap<String, String> args) {
					return controller.addHappiness(args);
				}
			}, Commands.ADD_HAPPINESS);
			put(new CommandAction() {
				public String action(HashMap<String, String> args) {
					return controller.addScore(args);
				}
			}, Commands.ADD_SCORE);
			put(new CommandAction() {
				public String action(HashMap<String, String> args) {
					return controller.nextTurn(args);
				}
			}, Commands.NEXT_TURN);
			put(new CommandAction() {
				public String action(HashMap<String, String> args) {
					return controller.multiNextTurn(args);
				}
			}, Commands.MULTI_NEXT_TURN);
			put(new CommandAction() {
				public String action(HashMap<String, String> args) {
					return controller.buildImprovement(args);
				}
			}, Commands.UNIT_BUILD_IMPROVEMENT);
			put(new CommandAction() {
				public String action(HashMap<String, String> args) {
					return controller.unitInfo(args);
				}
			}, Commands.UNIT_INFO);
			put(new CommandAction() {
				public String action(HashMap<String, String> args) {
					return controller.damageUnit(args);
				}
			}, Commands.DAMAGE_UNIT);
			put(new CommandAction() {
				public String action(HashMap<String, String> args) {
					return controller.showPlayer(args);
				}
			}, Commands.SHOW_PLAYER);
			put(new CommandAction() {
				public String action(HashMap<String, String> args) {
					return controller.removeFeature(args);
				}
			}, Commands.UNIT_REMOVE_FEATURE);
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