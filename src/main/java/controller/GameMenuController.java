package controller;

import controller.civilization.city.CityController;
import model.Game;
import model.Player;
import model.civilization.city.City;
import model.improvement.ImprovementType;
import model.technology.TechTree;
import model.technology.TechnologyType;
import model.tile.Tile;
import model.unit.Unit;
import model.unit.UnitType;
import model.unit.armed.Armed;
import model.unit.civilian.Civilian;
import model.unit.civilian.Worker;
import utils.Commands;
import utils.StringUtils;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.HashMap;

public class GameMenuController {

	private final Game game;
	private final MapController mapController;
	private final GameController gameController;
	private CityController cityController;

	/**
	 * @param
	 */
	public GameMenuController(Game game, GameController gameController, CityController cityController) {
		this.game = game;
		mapController = new MapController(game);
		this.gameController = gameController;
		this.cityController = cityController;
	}

	//SELECT:
	public String selectUnit(HashMap<String, String> args) {//todo: safar implement here
		String selectingType = args.get("section");//armed or civilian or garbage
		Tile destTile = game.getMap().getTileByNumber(Integer.parseInt(args.get("position")));
		//man inja ye chizayi neveshtam vali bebar too controllere khodet inja khalvat she
		if (destTile == null) return "invalid position";
		if (selectingType.equals("armed")) {
			Armed armed = destTile.getArmedUnit();
			if (armed == null) return "there is no armed unit here";
			game.setSelectedObject(armed);
		} else if (selectingType.equals("civilian")) {
			Civilian civilian = destTile.getCivilianUnit();
			if (civilian == null) return "there is no civilian unit here";
			game.setSelectedObject(civilian);
		} else {
			return "invalid unit type, types are [armed, civilian]";
		}
		return "unit selected";
	}

	public String selectCity(HashMap<String, String> args) {
		if (args.containsKey("position")) return gameController.selectCity("position", args.get("position"));
		else if (args.containsKey("cityname")) return gameController.selectCity("cityname", args.get("cityname"));
		else return "invalid command!";
	}

	//UNIT:
	public String unitMove(HashMap<String, String> args) {
		//TODO handle for armed unit in next checkpoint
		int position = Integer.parseInt(args.get("position"));
		Tile tile = game.getMap().getTileByNumber(position);
		if (tile == null) return "invalid position";
		//todo safar: move game.selectedObject -> tile
		if (!(game.getSelectedObject() instanceof Unit)) return "Selected \"Object\" is not Unit";
		Unit unit = (Unit) game.getSelectedObject();
		unit.goTo(tile);
		return "Done!";
	}

	@GameCommand(command = Commands.UNIT_ACTION_LIST)
	public String unitActionList(HashMap<String, String> args) {
		System.err.println("We are Here !");
		if (game.getSelectedObject() == null || !(game.getSelectedObject() instanceof Worker))
			return "worker is not selected !";
		Worker worker = (Worker) game.getSelectedObject();

		StringBuilder actions = new StringBuilder();

		if (worker.canRemoveFeature())
			actions.append(String.format("-- Remove %s\n", StringUtils.convertToPascalCase(worker.getCurrentTile().getFeature().name())));

		if (worker.canRemoveRoad()) actions.append("-- Remove Road\n");
		Tile tile = worker.getCurrentTile();
		for (ImprovementType improvement : ImprovementType.values()) {
			if (improvement.isEligibleToBuild(game.getCurrentPlayer().getCivilization(), tile))
				actions.append(String.format("-- Build Improvement %s\n", StringUtils.convertToPascalCase(improvement.name())));
		}
		return actions.toString();
	}

	public String unitSleep(HashMap<String, String> args) {
		//todo safar : sleep game.selectedObject
		return null;//todo return ro dorost kon
	}

	public String unitAlert(HashMap<String, String> args) {
		//todo safar : alert game.selectedObject
		return null;//todo return ro dorost kon
	}

	public String unitFortify(HashMap<String, String> args) {
		//todo safar : fortify game.selectedObject
		return null;//todo return ro dorost kon
	}

	public String unitFortifyUntilHeal(HashMap<String, String> args) {
		//todo safar : fortify until heal game.selectedObject
		return null;//todo return ro dorost kon
	}

	public String unitGarrison(HashMap<String, String> args) {
		//todo safar : garisson game.selectedObject
		return null;//todo return ro dorost kon
	}

	public String unitSetup(HashMap<String, String> args) {
		//todo safar : setup game.selectedObject
		return null;//todo return ro dorost kon
	}

	public String unitAttack(HashMap<String, String> args) {
		int position = Integer.parseInt(args.get("position"));
		Tile tile = game.getMap().getTileByNumber(position);
		if (tile == null) return "invalid position!";
		//todo safar : call your function here
		return null;// return ro ham dorost kon =)
	}

	public String unitFoundCity(HashMap<String, String> args) {
		//todo safar : call your function here
		return null;// return ro ham dorost kon =)
	}

	public String unitCancelMission(HashMap<String, String> args) {
		//todo safar : call your function here
		return null;// return ro ham dorost kon =)
	}

	public String unitWake(HashMap<String, String> args) {
		//todo safar : call your function here
		return null;// return ro ham dorost kon =)
	}

	public String unitDelete(HashMap<String, String> args) {
		//todo safar : call your function here
		return null;// return ro ham dorost kon =)
	}

	public String unitBuild(HashMap<String, String> args) {
		String buildingItem = args.get("section");//road || rail road ...
		//todo safar : call your function here
		return null;// return ro ham dorost kon =)
	}

	public String unitRemove(HashMap<String, String> args) {
		String removingItem = args.get("section");//jungle || route
		//todo safar : call your function here
		return null;// return ro ham dorost kon =)
	}

	public String unitRepair(HashMap<String, String> args) {
		//todo safar : call your function here
		return null;// return ro ham dorost kon =)
	}

	//MAP:
	public String mapShow(HashMap<String, String> args) {
//		System.err.println("???");
//		System.err.println(game.getMap().getTileByNumber(70).getArmedUnit());
		if (args.containsKey("position")) {
			return gameController.mapShow("position", args.get("position"));
		} else if (args.containsKey("cityname")) {
			return gameController.mapShow("cityname", args.get("cityname"));
		}
		return "invalid command!";
	}

	public String mapMove(HashMap<String, String> args) {
		String section = args.get("section");
		int count = Integer.parseInt(args.get("count"));
		return mapController.moveMap(section, count);
	}

	//GLOBAL:
	public String menuEnter(HashMap<String, String> args) {
		String menuName = args.get("section");
		if(menuName.equals("info")){
			ProgramController.setCurrentMenu(Menus.INFO_MENU);
		}
		return "invalid navigation!";
	}

	public String menuExit(HashMap<String, String> args) {
		ProgramController.setCurrentMenu(Menus.MAIN_MENU);
		return "Done!";
	}

	public String currentMenu(HashMap<String, String> args) {
		return "Game Menu";
	}

	public String increaseResource(HashMap<String, String> args) {
        /*String section = args.get("section");
        int amount = Integer.parseInt(args.get("amount"));
        Currency currency = new Currency(0,0,0);
        if(section.equals("gold")){
            currency.increase(amount,0,0);
        }else if(section.equals("product")){
            currency.increase(0,amount,0);
        }else if(section.equals("food")){
            currency.increase(0,0,amount);
        }else{
            return "invalid resource";
        }
        game.getSelectedCity().getCurrency().add(currency);
        return "Done!";*/
		return null;
	}

	@GameCommand(command = Commands.SPAWN_UNIT)
	public String spawnUnit(HashMap<String, String> args) {
		if (!isInteger(args.get("position")))
			return "position is not integer";

		int position = Integer.parseInt(args.get("position"));
		Tile tile = game.getMap().getTileByNumber(position);
		if (tile == null) return "invalid position";
		UnitType unitType = null;
		for (UnitType value : UnitType.values())
			if (StringUtils.convertToPascalCase(value.name()).toLowerCase().equals(args.get("name")))
				unitType = value;

		if (unitType == null)
			return "invalid unit type";

		Unit unit = Unit.spawnUnit(unitType, tile, game.getCurrentPlayer().getCivilization());
		if (unit instanceof Armed && tile.getArmedUnit() != null)
			return "there is already armed unit here";
		if (unit instanceof Civilian && tile.getCivilianUnit() != null)
			return "there is already civilian unit here";

		game.getCurrentPlayer().getCivilization().addUnit(unit);
		if (unit instanceof Civilian) tile.setCivilianUnit((Civilian) unit);
		if (unit instanceof Armed) tile.setArmedUnit((Armed) unit);
		return "god sent you some units !";
	}

	public String listOfProductions(HashMap<String, String> args) {
		if (!(game.getSelectedObject() instanceof City)) {
			return "select city first!";
		}
		City city = (City) game.getSelectedObject();
		return cityController.getProductionsListToProduce(city);
	}

	public String listOfAllProductions(HashMap<String, String> args) {
		String section = args.get("section");
		if (!section.equals("-a") && !section.equals("--all")) {
			return "invalid flag!";
		}
		if (!(game.getSelectedObject() instanceof City)) {
			return "select city first!";
		}
		City city = (City) game.getSelectedObject();
		return cityController.getProductionsListToPurchase(city);
	}

	public String purchaseProduction(HashMap<String, String> args) {
		return null;
		//todo implement here
	}

	public String setProduction(HashMap<String, String> args) {
		return null;
		//todo implement here
	}

	public String changeProduction(HashMap<String, String> args) {
		return null;
		//todo implement here
	}

	@GameCommand(command = Commands.SHOW_NEXT_TILES)
	public String showNextTiles(HashMap<String, String> args) {
		if (!(game.getSelectedObject() instanceof City)) return "select city first!";
		City city = (City) game.getSelectedObject();
		return cityController.getNextTiles(city);
	}

	public String getPurchasableTiles(HashMap<String, String> args) {
		if (!(game.getSelectedObject() instanceof City)) {
			return "select city first!";
		}
		City city = (City) game.getSelectedObject();
		return cityController.getPurchasableTile(city);
	}

	public String purchaseTile(HashMap<String, String> args) {
		if (!(game.getSelectedObject() instanceof City)) {
			return "select city first!";
		}
		City city = (City) game.getSelectedObject();
		return cityController.purchaseTile(city, Integer.parseInt(args.get("index")));
	}

	public String listOfPopulation(HashMap<String, String> args) {
		if (!(game.getSelectedObject() instanceof City)) {
			return "select city first!";
		}
		City city = (City) game.getSelectedObject();
		return cityController.getPopulation(city);
	}

	public String setTileForPopulation(HashMap<String, String> args) {
		if (!(game.getSelectedObject() instanceof City)) {
			return "select city first!";
		}
		City city = (City) game.getSelectedObject();
		int civilianIndex = Integer.parseInt(args.get("index"));
		Tile dest = game.getMap().getTileByNumber(Integer.parseInt(args.get("position")));
		if (dest == null) return "invalid destination!";
		return cityController.setPopulation(city, civilianIndex, dest);
	}

	public String deletePopulation(HashMap<String, String> args) {
		if (!(game.getSelectedObject() instanceof City)) {
			return "select city first!";
		}
		City city = (City) game.getSelectedObject();
		return cityController.deletePopulation(city, Integer.parseInt(args.get("index")));
	}

	@GameCommand(command = Commands.ADD_TECHNOLOGY)
	public String addTechnology(HashMap<String, String> args) {
		final TechTree techTree = game.getCurrentPlayer().getCivilization().getResearchTree();
		TechnologyType technologyType = null;
		for (TechnologyType value : TechnologyType.values()) {
			if (StringUtils.convertToPascalCase(value.name()).toLowerCase().equals(args.get("name"))) {
				technologyType = value;
			}
		}
		if (technologyType == null) return "invalid name";
		if (techTree.isResearched(technologyType)) return "already researched";
		techTree.completeResearch(technologyType);
		return "you bastard, cheat complete";
	}

	@GameCommand(command = Commands.SHOW_RESEARCHABLE_TECHS)
	public String getResearchableTechnologies(HashMap<String, String> args) {
		final Player currentPlayer = game.getCurrentPlayer();
		final TechTree researchTree = currentPlayer.getCivilization().getResearchTree();

		if (researchTree.getResearchableTechs().isEmpty()) return "All Technologies are researched\n";

		StringBuilder stringBuilder = new StringBuilder();
		int index = 1;
		for (TechnologyType researchableTech : researchTree.getResearchableTechs()) {
			stringBuilder.append(String.format("%d- %s\n", index, StringUtils.convertToPascalCase(researchableTech.name())));
			index++;
		}
		stringBuilder.deleteCharAt(stringBuilder.length() - 1);
		return stringBuilder.toString();
	}

	@GameCommand(command = Commands.GET_CURRENT_RESEARCH)
	public String getCurrentResearch(HashMap<String, String> args) {
		TechTree techTree = game.getCurrentPlayer().getCivilization().getResearchTree();
		if (techTree.getCurrentResearch() == null) return "no research at this time";
		return String.format("\"%s\" is being researched now, %d more science is required to complete research", StringUtils.convertToPascalCase(techTree.getCurrentResearch().name()), techTree.getRemainingScience());
	}

	@GameCommand(command = Commands.RESEARCH)
	public String research(HashMap<String, String> args) {
		final TechTree techTree = game.getCurrentPlayer().getCivilization().getResearchTree();
		if (techTree.isResearching()) return "another research is in progress";

		TechnologyType technologyType = null;
		for (TechnologyType value : TechnologyType.values()) {
			if (StringUtils.convertToPascalCase(value.name()).toLowerCase().equals(args.get("name"))) {
				technologyType = value;
			}
		}

		if (technologyType == null) return "invalid name";
		if (techTree.isResearched(technologyType)) return "already researched";
		if (!techTree.isResearchable(technologyType)) return "not researchable at this time";
		techTree.research(technologyType);
		return "scientist started this research";
	}

	@GameCommand(command = Commands.CHANGE_RESEARCH)
	public String changeResearch(HashMap<String, String> args) {
		final TechTree techTree = game.getCurrentPlayer().getCivilization().getResearchTree();
		if (!techTree.isResearching()) return "no research is in progress now";

		TechnologyType technologyType = null;
		for (TechnologyType value : TechnologyType.values()) {
			if (StringUtils.convertToPascalCase(value.name()).toLowerCase().equals(args.get("name"))) {
				technologyType = value;
			}
		}

		if (technologyType == null) return "invalid name";
		if (techTree.isResearched(technologyType)) return "already researched";
		if (!techTree.isResearchable(technologyType)) return "not researchable at this time";
		techTree.changeResearch(technologyType);
		return "scientist started this research";
	}

	@GameCommand(command = Commands.ADD_BEAKER)
	public String addBeaker(HashMap<String, String> args) {
		if (!isInteger(args.get("amount"))) return "invalid amount";
		int amount = Integer.parseInt(args.get("amount"));
		if (amount <= 0) return "positive amount is required";

		TechTree techTree = game.getCurrentPlayer().getCivilization().getResearchTree();
		if (!techTree.isResearching()) return "no research at this time";
		techTree.addScience(amount);
		return "progress made";
	}


	private boolean isInteger(String input) {
		try {
			Integer.parseInt(input);
		} catch (NumberFormatException nfe) {
			return false;
		}
		return true;
	}

	@GameCommand(command = Commands.CHEAT_NEXT_TURN)
	public String cheatNextTurn(HashMap<String, String> args) {
		for (Unit unit : game.getCurrentPlayer().getCivilization().getUnits()) {
			unit.nextTurn();
		}
		return "time fast forwarded !";
	}
}