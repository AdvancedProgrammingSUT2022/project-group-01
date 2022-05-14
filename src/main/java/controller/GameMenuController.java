package controller;

import controller.civilization.city.CityController;
import controller.unit.UnitController;
import controller.unit.WorkerController;
import model.Game;
import model.Player;
import model.ProgressState;
import model.TurnBasedLogic;
import model.civilization.Civilization;
import model.civilization.city.City;
import model.improvement.ImprovementType;
import model.technology.TechTree;
import model.technology.TechnologyType;
import model.tile.TerrainFeature;
import model.tile.Tile;
import model.unit.Unit;
import model.unit.UnitType;
import model.unit.armed.Armed;
import model.unit.armed.RangedUnit;
import model.unit.armed.Siege;
import model.unit.civilian.Civilian;
import model.unit.civilian.Settler;
import model.unit.civilian.Worker;
import utils.Commands;
import utils.StringUtils;


import java.sql.ParameterMetaData;

import java.util.HashMap;

public class GameMenuController {

	private final Game game;
	private final MapController mapController;
	private final GameController gameController;
	private final UnitController unitController;
	private final WorkerController workerController;
	private CityController cityController;
	private final UnitController unitController;

	/**
	 * @param
	 */
<
	public GameMenuController(Game game, GameController gameController, CityController cityController, UnitController unitController, WorkerController workerController) {
>
		this.game = game;
		mapController = new MapController(game);
		this.gameController = gameController;
		this.cityController = cityController;
		this.unitController = unitController;

		this.workerController = workerController;

	}

	//SELECT:
	public String selectUnit(HashMap<String, String> args) {
		if(!isInteger(args.get("position")))
			return "invalid position";
		String selectingType = args.get("section");//armed or civilian or garbage
		Tile destTile = game.getMap().getTileByNumber(Integer.parseInt(args.get("position")));
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
		else if (args.containsKey("name")) return gameController.selectCity("name", args.get("name"));
		else return "invalid command!";
	}

	//UNIT:
	public String unitMove(HashMap<String, String> args) {
		if(!isInteger(args.get("position")))
			return "invalid number";
		int position = Integer.parseInt(args.get("position"));
		Tile tile = game.getMap().getTileByNumber(position);
		if (tile == null) return "invalid position";
		if (!(game.getSelectedObject() instanceof Unit))
			return "Selected \"Object\" is not Unit";
		if (isAnotherPlayerUnit())
			return "you don't own this unit";
		Unit unit = (Unit) game.getSelectedObject();
		if (!unit.canGoTo(tile))
			return "Can't reach there\n";
		if(unit.outOfMP())
			return "selected unit is out of movement point";
		if(game.getCurrentPlayer().getSavedMap().getVisibilityState(tile).equals(Tile.VisibilityState.FOG_OF_WAR))
			return "you don't see there";

		unit.goTo(tile);
		return "Done!";
	}

	@GameCommand(command = Commands.UNIT_ACTION_LIST)
	public String unitActionList(HashMap<String, String> args) {
		System.err.println("We are Here !");
		if (game.getSelectedObject() == null || !(game.getSelectedObject() instanceof Worker))
			return "worker is not selected !";
		if (isAnotherPlayerUnit())
			return "you don't own this unit";
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

	@GameCommand(command = Commands.UNIT_SLEEP)
	public String unitSleep(HashMap<String, String> args) {
		Unit unit = getSelectedUnit();

		if (unit == null)
			return "you haven't select any unit";
		if (isAnotherPlayerUnit())
			return "you don't own this unit";
		return unitController.sleep(unit);

	}

	@GameCommand(command = Commands.DAMAGE_UNIT)
	public String damageUnit(HashMap<String, String> args) {
		if (!isInteger(args.get("amount"))) return "invalid amount";
		int amount = Integer.parseInt(args.get("amount"));
		Unit unit = getSelectedUnit();
		if (unit == null)
			return "you haven't select any unit";
		return unitController.damage(unit, amount);
	}

	@GameCommand(command = Commands.UNIT_ALERT)
	public String unitAlert(HashMap<String, String> args) {
		Unit unit = getSelectedUnit();
		if (unit == null)
			return "you haven't select any unit";
		if (isAnotherPlayerUnit())
			return "you don't own this unit";
		return unitController.alert(unit);
	}

	@GameCommand(command = Commands.UNIT_FORTIFY)
	public String unitFortify(HashMap<String, String> args) {
		Unit unit = getSelectedUnit();

		if (unit == null)
			return "you haven't select any unit";
		if (isAnotherPlayerUnit())
			return "you don't own this unit";

		return unitController.fortify(unit);
	}

	@GameCommand(command = Commands.UNIT_FORTIFY_UNTIL_HEAL)
	public String unitFortifyUntilHeal(HashMap<String, String> args) {
		Unit unit = getSelectedUnit();

		if (unit == null)
			return "you haven't select any unit";
		if (isAnotherPlayerUnit())
			return "you don't own this unit";

		return unitController.fortifyUntilHeal(unit);
	}

	public String unitGarrison(HashMap<String, String> args) {
		//todo safar : garisson game.selectedObject
		return null;//todo return ro dorost kon
	}

	@GameCommand(command = Commands.UNIT_SETUP)
	public String unitSetup(HashMap<String, String> args) {
		if (getSelectedUnit() == null)
			return "you haven't select any unit";
		if (isAnotherPlayerUnit())
			return "you don't own this unit";
		if (!(getSelectedUnit() instanceof Siege))
			return "selected unit is not Siege";
		return unitController.setup((Siege) getSelectedUnit());
	}

	@GameCommand(command = Commands.UNIT_MELEE_ATTACK)
	public String unitMeleeAttack(HashMap<String, String> args) {
		int position = Integer.parseInt(args.get("position"));
		Tile tile = game.getMap().getTileByNumber(position);
		if (tile == null) return "invalid position!";
		if (getSelectedUnit() == null || !(getSelectedUnit() instanceof Armed))
			return "you haven't select any armed unit";
		return unitController.meleeAttack((Armed) getSelectedUnit(), tile);
	}

	@GameCommand(command = Commands.UNIT_RANGED_ATTACK)
	public String unitRangedAttack(HashMap<String, String> args) {
		int position = Integer.parseInt(args.get("position"));
		Tile tile = game.getMap().getTileByNumber(position);
		if (tile == null) return "invalid position!";
		if (getSelectedUnit() == null || !(getSelectedUnit() instanceof RangedUnit))
			return "you haven't select any ranged unit";
		return unitController.rangedAttack((RangedUnit) getSelectedUnit(), tile);
	}

	@GameCommand(command = Commands.UNIT_INFO)
	public String unitInfo(HashMap<String, String> args) {
		Unit unit = getSelectedUnit();
		if (unit == null)
			return "you haven't select any unit";
		return unitController.info(unit);
	}

	@GameCommand(command = Commands.UNIT_INFO)
	public String unitInfo(HashMap<String, String> args){
		Unit unit = getSelectedUnit();
		if(unit == null)
			return "you haven't select any unit";
		return unitController.info(unit);
	}

	@GameCommand(command = Commands.UNIT_FOUND_CITY)
	public String unitFoundCity(HashMap<String, String> args) {
		if (getSelectedUnit() == null)
			return "you haven't select any unit";
		if (isAnotherPlayerUnit())
			return "you don't own this unit";
		if (!(getSelectedUnit() instanceof Settler))
			return "selected unit is not Settler";
		Settler settler = (Settler) game.getSelectedObject();
		UnitController unitController = new UnitController(game);
		return unitController.foundCity(settler);
	}

	@GameCommand(command = Commands.UNIT_CANCEL_MISSION)
	public String unitCancelMission(HashMap<String, String> args) {
		Unit unit = getSelectedUnit();

		if (unit == null)
			return "you haven't select any unit";
		if (isAnotherPlayerUnit())
			return "you don't own this unit";

		return unitController.cancel(unit);
	}

	@GameCommand(command = Commands.UNIT_WAKE)
	public String unitWake(HashMap<String, String> args) {
		Unit unit = getSelectedUnit();

		if (unit == null)
			return "you haven't select any unit";
		if (isAnotherPlayerUnit())
			return "you don't own this unit";

		return unitController.wake(unit);
	}

	@GameCommand(command = Commands.UNIT_DELETE)
	public String unitDelete(HashMap<String, String> args) {
		Unit unit = getSelectedUnit();

		if (unit == null)
			return "you haven't select any unit";
		if (isAnotherPlayerUnit())
			return "you don't own this unit";

		return unitController.delete(unit);
	}

	@GameCommand(command = Commands.UNIT_PILLAGE)
	public String unitPillage(HashMap<String, String> args) {
		Unit unit = getSelectedUnit();
		if (unit == null)
			return "you haven't select any unit";
		if (isAnotherPlayerUnit())
			return "you don't own this unit";
		return unitController.pillage(unit);
	}


	//MAP:

	public String mapShow(HashMap<String, String> args) {
		if (args.containsKey("position")) {
			return gameController.mapShow("position", args.get("position"));
		} else if (args.containsKey("cityname")) {
			return gameController.mapShow("cityname", args.get("cityname"));
		}
		return "invalid command";
	}

	public String mapMove(HashMap<String, String> args) {
		String section = args.get("section");
		int count = Integer.parseInt(args.get("count"));
		return mapController.moveMap(section, count);
	}

	//GLOBAL:

	public String menuEnter(HashMap<String, String> args) {
		String menuName = args.get("section");

		if (menuName.equals("info")) {
			ProgramController.setCurrentMenu(Menus.INFO_MENU);
			return "done";
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
		String resourceName = args.get("section");
		int amount = Integer.parseInt(args.get("amount"));
		if (!(game.getSelectedObject() instanceof City))
			return "select city first!";
		City city = (City) game.getSelectedObject();
		return cityController.increaseResource(city, resourceName, amount);
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

//		game.getCurrentPlayer().getCivilization().addUnit(unit);
		if (unit instanceof Civilian) tile.setCivilianUnit((Civilian) unit);
		if (unit instanceof Armed) tile.setArmedUnit((Armed) unit);
		mapController.updateCurrentPlayersMap();
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
		if (!(game.getSelectedObject() instanceof City))
			return "select city first";
		String type = args.get("type");
		City city = (City) game.getSelectedObject();
		return cityController.purchaseProduction(city, type);
	}

	public String setProduction(HashMap<String, String> args) {
		String type = args.get("type");
		if (!(game.getSelectedObject() instanceof City))
			return "select city first!";
		City city = (City) game.getSelectedObject();
		return cityController.setProductionToProduce(city, type);
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
		if((dest.getOwnerCity() == null) || (!dest.getOwnerCity().equals(city))) return "destination is outside of your city";
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

	@GameCommand(command = Commands.CHEAT_NEXT_TURN)
	public String cheatNextTurn(HashMap<String, String> args) {
		for (Unit unit : game.getCurrentPlayer().getCivilization().getUnits()) {

//			unit.nextTurn();
//			System.out.println("unit type " + unit.toString());

		}
		game.nextTurn();
		return "time fast forwarded !";
	}

	@GameCommand(command = Commands.UNIT_BUILD_IMPROVEMENT)
	public String buildImprovement(HashMap<String, String> args) {

		if (getSelectedUnit() == null)
			return "you haven't select any unit";
		if (!(getSelectedUnit() instanceof Worker))
			return "worker is not selected !";
		if (isAnotherPlayerUnit())
			return "you don't own this unit";
		return workerController.buildImprovement((Worker) getSelectedUnit(), getImprovementType(args.get("name")));
	}

	@GameCommand(command = Commands.UNIT_REMOVE_FEATURE)
	public String removeFeature(HashMap<String, String> args) {
		if (getSelectedUnit() == null)
			return "you haven't select any unit";
		if (!(getSelectedUnit() instanceof Worker))
			return "worker is not selected !";
		if (isAnotherPlayerUnit())
			return "you don't own this unit";
		return workerController.removeFeature((Worker) getSelectedUnit());
	}

	@GameCommand(command = Commands.UNIT_REPAIR)
	public String unitRepair(HashMap<String, String> args) {
		if (getSelectedUnit() == null)
			return "you haven't select any unit";
		if (!(getSelectedUnit() instanceof Worker))
			return "worker is not selected !";
		if (isAnotherPlayerUnit())
			return "you don't own this unit";
		return workerController.repair((Worker) getSelectedUnit());
	}

	@GameCommand(command = Commands.UNIT_REMOVE)
	public String unitRemove(HashMap<String, String> args) {
		if (getSelectedUnit() == null)
			return "you haven't select any unit";
		if (!(getSelectedUnit() instanceof Worker))
			return "worker is not selected !";
		if (isAnotherPlayerUnit())
			return "you don't own this unit";
		return workerController.removeImprovement((Worker) getSelectedUnit());
	}

	@GameCommand(command = Commands.UNIT_BUILD_ROAD)
	public String buildRoad(HashMap<String, String> args) {
		if (getSelectedUnit() == null)
			return "you haven't select any unit";
		if (!(getSelectedUnit() instanceof Worker))
			return "worker is not selected !";
		if (isAnotherPlayerUnit())
			return "you don't own this unit";
		return workerController.buildRoad((Worker) getSelectedUnit());
	}

	@GameCommand(command = Commands.UNIT_BUILD_RAIL)
	public String buildRail(HashMap<String, String> args) {
		if (getSelectedUnit() == null)
			return "you haven't select any unit";
		if (!(getSelectedUnit() instanceof Worker))
			return "worker is not selected !";
		if (isAnotherPlayerUnit())
			return "you don't own this unit";
		return workerController.buildRail((Worker) getSelectedUnit());
	}

	@GameCommand(command = Commands.UNIT_BUILD_RAIL)
	public String removeRoute(HashMap<String, String> args) {
		if (getSelectedUnit() == null)
			return "you haven't select any unit";
		if (!(getSelectedUnit() instanceof Worker))
			return "worker is not selected !";
		if (isAnotherPlayerUnit())
			return "you don't own this unit";
		return workerController.removeRoute((Worker) getSelectedUnit());
	}

	private boolean isInteger(String input) {
		try {
			Integer.parseInt(input);
		} catch (NumberFormatException nfe) {
			return false;
		}
		return true;
	}

	public String teleport(HashMap<String, String> args) {
		int position = Integer.parseInt(args.get("position"));
		Tile tile = game.getMap().getTileByNumber(position);
		if (tile == null)
			return "invalid position";
		return unitController.teleport(getSelectedUnit(), tile);
	}

	public String makeTileVisible(HashMap<String, String> args) {
		int position = Integer.parseInt(args.get("position"));
		Tile tile = game.getMap().getTileByNumber(position);
		if (tile == null)
			return "invalid position";
		//todo implement here
		return "teleported";
	}


	public String removeFogOfWar(HashMap<String, String> args){
		int position = Integer.parseInt(args.get("position"));
		Tile tile = game.getMap().getTileByNumber(position);
		if(tile == null)
			return "invalid position";
		//todo implement here
		return gameController.cheatRemoveFogOfWar(tile);
	}

	public String addHappiness(HashMap<String, String> args){

		int amount = Integer.parseInt(args.get("amount"));
		Civilization civilization = game.getCurrentPlayer().getCivilization();
		civilization.setHappinessBase(civilization.getHappinessBase() + amount);
		return "done!";
	}

	public String createFeature(HashMap<String, String> args){
		String type = args.get("type").toLowerCase();
		Tile tile = game.getMap().getTileByNumber(Integer.parseInt(args.get("position")));
		if(tile == null)
			return "invalid position";
		for(TerrainFeature terrainFeature : TerrainFeature.values()){
			if(terrainFeature.name().toLowerCase().equals(type))
				return gameController.cheatSetFeature(tile,terrainFeature);
		}
		return "invalid feature";

	}

	public String addScore(HashMap<String, String> args) {
		int amount = Integer.parseInt(args.get("amount"));
		game.getCurrentPlayer().getUser().increaseScore(amount);
		return "done!";
	}

	public String nextTurn(HashMap<String, String> args){
		game.nextTurn();

		return "time flies...\n"+game.getCurrentPlayer().getUser().getNickname()+"'s turn:";
	}

	public String multiNextTurn(HashMap<String, String> args) {
		int count = Integer.parseInt(args.get("count"));

		for(int i=0;i<count*game.getPlayers().size();i++){

			nextTurn(null);
		}
		return "can you travel to past too?\nDone!";
	}

	private boolean isAnotherPlayerUnit() {
		if (getSelectedUnit() == null) return true;
		return getSelectedUnit().getOwnerCivilization().getPlayer() != game.getCurrentPlayer();
	}

	private ImprovementType getImprovementType(String name) {
		for (ImprovementType improvement : ImprovementType.values()) {
			if (improvement.name().toLowerCase().equals(name))
				return improvement;
		}
		return null;
	}

	public String showPlayer(HashMap<String, String> args) {
		return gameController.getPlayerInfo();
  }
	private Unit getSelectedUnit() {
		if (game.getSelectedObject() == null || !(game.getSelectedObject() instanceof Unit))
			return null;
		return (Unit) game.getSelectedObject();
	}

	public String showCurrentMap(){
		String s = gameController.mapShow() + "\n";
		s += "current player : " + game.getCurrentPlayer().getUser().getNickname() + " ";
		s += "(" + game.getCurrentPlayer().getCivilization().getCivilization().name() + ")";
		s += "\n" + "turn : " + game.getTurn() + "\n";
		return s;
	}

	public String showTileInfo(HashMap<String,String> args){
		int position = Integer.parseInt(args.get("index"));
		if(game.getMap().getTileByNumber(position) == null) return "invalid position";
		return gameController.showTileInfo(game.getMap().getTileByNumber(position));
	}

	public String destroyCity(HashMap<String, String> args){
		if(!(game.getSelectedObject() instanceof City))
			return "select city first";
		City city = (City) game.getSelectedObject();
		city.destroy();
		return "boom!";
	}

	@GameCommand(command = Commands.TILE_INFO)
	public String tileInfo(HashMap<String, String> args) {
		if (!isInteger(args.get("position")))
			return "position is not integer";

		int position = Integer.parseInt(args.get("position"));
		Tile tile = game.getMap().getTileByNumber(position);
		if (tile == null)
			return "invalid position";
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(String.format("tile id : %d\n", tile.getMapNumber()));
		if (tile.getImprovementInventoryState().equals(ProgressState.DAMAGED))
			stringBuilder.append("this tile is pillaged\n");
		if (tile.getCivilianUnit() != null)
			stringBuilder.append(String.format("civilian unit in this tile is %s\n",
					tile.getCivilianUnit().getType().toString()
			));
		else stringBuilder.append("there isn't any civilian unit here\n");

		if (tile.getArmedUnit() != null)
			stringBuilder.append(String.format("armed unit in this tile is %s\n",
					tile.getArmedUnit().getType().toString()
			));
		else stringBuilder.append("there isn't any armed unit here\n");

		if (tile.getBuiltImprovement() != null)
			stringBuilder.append(String.format("Improvement in this tile is %s\n", tile.getBuiltImprovement().toString()));
		else
			stringBuilder.append("there isn't any improvement here\n");
		if (tile.doesHaveRailRoad())
			stringBuilder.append("this tile has rail road\n");
		if (tile.doesHaveRoad())
			stringBuilder.append("this tile has road\n");
		return stringBuilder.toString();
	}

	@GameCommand(command = Commands.CITY_ATTACK)
	public String cityAttack(HashMap<String, String> args) {
		if(!isInteger(args.get("target")))
			return "invalid Tile";
		if(!(game.getSelectedObject() instanceof City))
			return "you haven't Selected any city";
		City city = (City) game.getSelectedObject();
		if(game.getCurrentPlayer().getCivilization() != city.getCivilization())
			return "you don't own this city";
		Tile tile = game.getMap().getTileByNumber(Integer.parseInt(args.get("target")));
		if(tile == null)
			return "invalid tile";
		if(tile.getArmedUnit() == null)
			return "there isn't any armed unit there";
		Armed target = tile.getArmedUnit();
		if(target.getOwnerCivilization() == city.getCivilization())
			return "why do you want to attack your units, are you idiot ?";
		if(!city.getCenter().getAttackingArea(2, false).contains(tile))
			return "this unit is not in attack range";
		if(city.isAttackedThisTurn())
			return "you already attacked this turn";
		double damage = city.getAttackPower();
		target.changeHealth( -(int) (damage * (1 / target.getDefensePower())) );
		city.setAttackedThisTurn(true);
		return "Attacked to unit, say goodbye to that bastard";
	}
}