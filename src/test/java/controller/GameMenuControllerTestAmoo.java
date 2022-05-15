package controller;

import controller.*;
import controller.civilization.city.CityController;
import controller.unit.UnitController;
import controller.unit.WorkerController;
import model.*;
import model.civilization.Civilization;
import model.civilization.city.City;
import model.improvement.ImprovementType;
import model.resource.ResourceType;
import model.technology.TechTree;
import model.technology.TechnologyType;
import model.tile.Terrain;
import model.tile.TerrainFeature;
import model.tile.Tile;
import model.unit.Unit;
import model.unit.UnitType;
import model.unit.armed.Armed;
import model.unit.civilian.Civilian;
import model.unit.civilian.Worker;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;
import org.w3c.dom.html.HTMLOptGroupElement;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class GameMenuControllerTestAmoo {
	public class HSS extends HashMap<String, String> {}

	private Game game;
	private MapController mc;
	private GameMenuController gameMenuController;

	private Tile randomTile;
	private Civilization enemy;
	private City randomCity;
	@BeforeEach
	public void init(){
		User user1 = new User("a","b","c");
		User user2 = new User("aa","bb","cc");
		TileController.initializeEnums();
		Vector<User> vec = new Vector<>(List.of(user1,user2));
		GameInitializer gi = new GameInitializer();
		game = gi.startGame(vec,17);
		mc = new MapController(game);
		WorkerController workerController = new WorkerController(game);
		gameMenuController = new GameMenuController(game,new GameController(game,mc),new CityController(game),new UnitController(game), workerController);
		game.getMap().getMap();
		game.setMap(game.getMap());
		enemy = game.getCurrentPlayer().getCivilization();
		game.nextTurn();
		Tile tile = game.getCurrentPlayer().getMapCenterTile();
		tile.setCivilization(game.getCurrentPlayer().getCivilization());
		Armed armed = new Armed(UnitType.WARRIOR,tile,game.getCurrentPlayer().getCivilization());
		Civilian civilian = new Civilian(UnitType.WORKER,tile,game.getCurrentPlayer().getCivilization());
		tile.setArmedUnit(armed);
		tile.setCivilianUnit(civilian);
		tile.setAvailableResource(ResourceType.IRON);
		tile.buildRoad();
		tile.buildImprovement(ImprovementType.LUMBER_MILL);
		tile.buildImprovement(ImprovementType.LUMBER_MILL);
		tile.buildImprovement(ImprovementType.LUMBER_MILL);
		tile.buildImprovement(ImprovementType.LUMBER_MILL);
		tile.buildImprovement(ImprovementType.LUMBER_MILL);
		tile.buildImprovement(ImprovementType.LUMBER_MILL);
		tile.buildImprovement(ImprovementType.LUMBER_MILL);
		Assertions.assertEquals(tile.getImprovementInventoryState(), ProgressState.COMPLETE);
		mc.updateSavedMap(game.getCurrentPlayer(),new Vector<>(List.of(tile)),game.getMap());
		mc.moveCenterTile(1,"right");
		mc.getConsoleMap(tile);
		TileController.initializeEnums();
		City city = new City(game.getCurrentPlayer().getCivilization().getCivilization().getCityNames()[1],game.getCurrentPlayer().getCivilization(),tile);
		game.getCurrentPlayer().getCivilization().addNewCity(city);
		tile.setOwnerCity(city);
		city.updateBeaker();
		Unit unit = new Armed(UnitType.WARRIOR,city.getCenter(),game.getCurrentPlayer().getCivilization());
		city.setGarrisonedUnit(unit);
		city.getGarrisonedUnit();
		city.updateDefencePower(2);
		city.setAttackPower(4);
		game.getCurrentPlayer().getMapCenterTile().setOwnerCity(city);
		randomTile = game.getMap().getTileByNumber(1);
		randomCity = city;
	}


	@Test
	void addBeakerTest(){
		HSS hss = new HSS();
		hss.put("amount", "-2");
		gameMenuController.addBeaker(hss);
		assertEquals("positive amount is required", gameMenuController.addBeaker(hss));
		hss.put("amount", "2");
		assertEquals("no research at this time", gameMenuController.addBeaker(hss));
		game.getCurrentPlayer().getCivilization().getResearchTree().research(TechnologyType.AGRICULTURE);
		assertEquals("progress made", gameMenuController.addBeaker(hss));
	}

	@Test
	void cheatNextTurn(){
		HSS hss = new HSS();
		gameMenuController.cheatNextTurn(hss);
	}

	@Test
	void buildImprovementTest(){
		HSS hss = new HSS();
		hss.put("name", "ali");
		Unit unit = new Unit(UnitType.WARRIOR, randomTile, enemy);
		game.setSelectedObject(unit);
		assertEquals("worker is not selected !", gameMenuController.buildImprovement(hss));
		unit = new Worker(UnitType.WORKER, randomTile, enemy);
		game.setSelectedObject(unit);
		assertEquals("you don't own this unit", gameMenuController.buildImprovement(hss));
		unit.setOwnerCivilization(game.getCurrentPlayer().getCivilization());
		assertEquals("invalid Improvement !", gameMenuController.buildImprovement(hss));
		hss.put("name", "mine");
		assertEquals("not Eligible to Build this improvement here", gameMenuController.buildImprovement(hss));
	}

	@Test
	void removeFeatureTest(){
		HSS hss = new HSS();
		hss.put("name", "ali");
		game.setSelectedObject(null);
		assertEquals("you haven't select any unit", gameMenuController.removeFeature(hss));
		Unit unit = new Unit(UnitType.WARRIOR, randomTile, enemy);
		game.setSelectedObject(unit);
		assertEquals("worker is not selected !", gameMenuController.removeFeature(hss));
		unit = new Worker(UnitType.WORKER, randomTile, enemy);
		game.setSelectedObject(unit);
		assertEquals("you don't own this unit", gameMenuController.removeFeature(hss));
		unit.setOwnerCivilization(game.getCurrentPlayer().getCivilization());
		randomTile.removeFeature();
		assertEquals("this tile doesn't have any feature", gameMenuController.removeFeature(hss));
	}

	@Test
	void unitRepairTest(){
		HSS hss = new HSS();
		hss.put("name", "ali");
		game.setSelectedObject(null);
		assertEquals("you haven't select any unit", gameMenuController.unitRepair(hss));
		Unit unit = new Unit(UnitType.WARRIOR, randomTile, enemy);
		game.setSelectedObject(unit);
		assertEquals("worker is not selected !", gameMenuController.unitRepair(hss));
		unit = new Worker(UnitType.WORKER, randomTile, enemy);
		game.setSelectedObject(unit);
		assertEquals("you don't own this unit", gameMenuController.unitRepair(hss));
		unit.setOwnerCivilization(game.getCurrentPlayer().getCivilization());
		assertEquals("this tile is not pillaged", gameMenuController.unitRepair(hss));
	}


	@Test
	void unitRemoveTest(){
		HSS hss = new HSS();
		hss.put("name", "ali");
		game.setSelectedObject(null);
		assertEquals("you haven't select any unit", gameMenuController.unitRemove(hss));
		Unit unit = new Unit(UnitType.WARRIOR, randomTile, enemy);
		game.setSelectedObject(unit);
		assertEquals("worker is not selected !", gameMenuController.unitRemove(hss));
		unit = new Worker(UnitType.WORKER, randomTile, enemy);
		game.setSelectedObject(unit);
		assertEquals("you don't own this unit", gameMenuController.unitRemove(hss));
		unit.setOwnerCivilization(game.getCurrentPlayer().getCivilization());
		assertEquals("this tile don't have any improvement", gameMenuController.unitRemove(hss));
	}

	@Test
	void buildRoadTest(){
		HSS hss = new HSS();
		hss.put("name", "ali");
		game.setSelectedObject(null);
		assertEquals("you haven't select any unit", gameMenuController.buildRoad(hss));
		Unit unit = new Unit(UnitType.WARRIOR, randomTile, enemy);
		game.setSelectedObject(unit);
		assertEquals("worker is not selected !", gameMenuController.buildRoad(hss));
		unit = new Worker(UnitType.WORKER, randomTile, enemy);
		game.setSelectedObject(unit);
		assertEquals("you don't own this unit", gameMenuController.buildRoad(hss));
		unit.setOwnerCivilization(game.getCurrentPlayer().getCivilization());
		randomTile.buildRoad();
		assertEquals("you can't build road here", gameMenuController.buildRoad(hss));
	}

	@Test
	void buildRailTest(){
		HSS hss = new HSS();
		hss.put("name", "ali");
		game.setSelectedObject(null);
		assertEquals("you haven't select any unit", gameMenuController.buildRail(hss));
		Unit unit = new Unit(UnitType.WARRIOR, randomTile, enemy);
		game.setSelectedObject(unit);
		assertEquals("worker is not selected !", gameMenuController.buildRail(hss));
		unit = new Worker(UnitType.WORKER, randomTile, enemy);
		game.setSelectedObject(unit);
		assertEquals("you don't own this unit", gameMenuController.buildRail(hss));
		unit.setOwnerCivilization(game.getCurrentPlayer().getCivilization());
		randomTile.buildRailRoad();
		assertEquals("you can't build rail road here", gameMenuController.buildRail(hss));
	}

	@Test
	void removeRouteTest(){
		HSS hss = new HSS();
		hss.put("name", "ali");
		game.setSelectedObject(null);
		assertEquals("you haven't select any unit", gameMenuController.removeRoute(hss));
		Unit unit = new Unit(UnitType.WARRIOR, randomTile, enemy);
		game.setSelectedObject(unit);
		assertEquals("worker is not selected !", gameMenuController.removeRoute(hss));
		unit = new Worker(UnitType.WORKER, randomTile, enemy);
		game.setSelectedObject(unit);
		assertEquals("you don't own this unit", gameMenuController.removeRoute(hss));
		unit.setOwnerCivilization(game.getCurrentPlayer().getCivilization());
		assertEquals("this tile doesn't have any route", gameMenuController.removeRoute(hss));
	}

	@Test
	void teleportTest(){
		HSS hss = new HSS();
		hss.put("position", "ali");
		game.setSelectedObject(new Unit(UnitType.WORKER, randomTile, enemy));
		assertEquals("position is not integer", gameMenuController.teleport(hss));
		hss.put("position", "-1");
		assertEquals("invalid position", gameMenuController.teleport(hss));
		hss.put("position", "1");
		assertEquals("this tile is not passable", gameMenuController.teleport(hss));
	}

	@Test
	void removeFogOfWarTest(){
		HSS hss = new HSS();
		hss.put("position", "ali");
		assertEquals("position is not integer", gameMenuController.removeFogOfWar(hss));
	}

	@Test
	void createFeatureTest(){
		HSS hss = new HSS();
		hss.put("type", "type");
		hss.put("position", "-1");
		assertEquals("invalid position", gameMenuController.createFeature(hss));
		hss.put("position", "1");
		assertEquals("invalid feature", gameMenuController.createFeature(hss));
		hss.put("type", "forest");
		assertEquals("you can't use this feature on this tile's terrain",
				gameMenuController.createFeature(hss));
	}

	@Test
	void addScoreTest(){
		HSS hss = new HSS();
		hss.put("amount", "5");
		assertEquals("done!", gameMenuController.addScore(hss));
	}

	@Test
	void tileInfoTest(){
		HSS hss = new HSS();
		hss.put("position", "a");
		assertEquals("position is not integer", gameMenuController.tileInfo(hss));
		hss.put("position", "-1");
		assertEquals("invalid position", gameMenuController.tileInfo(hss));
		hss.put("position", "1");

		randomTile.buildRoad();
		randomTile.buildRailRoad();
		randomTile.buildImprovement(ImprovementType.LUMBER_MILL);
		randomTile.buildImprovement(ImprovementType.LUMBER_MILL);
		randomTile.buildImprovement(ImprovementType.LUMBER_MILL);
		randomTile.buildImprovement(ImprovementType.LUMBER_MILL);
		randomTile.buildImprovement(ImprovementType.LUMBER_MILL);
		randomTile.buildImprovement(ImprovementType.LUMBER_MILL);
		randomTile.buildImprovement(ImprovementType.LUMBER_MILL);
		randomTile.buildImprovement(ImprovementType.LUMBER_MILL);
		String expected = "tile id : 1\n" +
				"there isn't any civilian unit here\n" +
				"there isn't any armed unit here\n" +
				"Improvement in this tile is Lumber Mill\n" +
				"this tile has rail road\n" +
				"this tile has road\n";
		assertEquals(expected, gameMenuController.tileInfo(hss));
		randomTile.setCivilianUnit(new Civilian(UnitType.WORKER, randomTile, enemy));
		randomTile.setArmedUnit(new Armed(UnitType.WARRIOR, randomTile, enemy));
		randomTile.pillageImprovement();
		expected = "tile id : 1\n" +
				"this tile is pillaged\n" +
				"civilian unit in this tile is Worker\n" +
				"armed unit in this tile is Warrior\n" +
				"there isn't any improvement here\n" +
				"this tile has rail road\n" +
				"this tile has road\n";
		assertEquals(expected, gameMenuController.tileInfo(hss));
	}

	@Test
	void cityAttackTest(){
		HSS hss = new HSS();
		hss.put("target", "a");
		assertEquals("invalid Tile", gameMenuController.cityAttack(hss));
		hss.put("target", "-1");
		game.setSelectedObject(null);
		assertEquals("you haven't Selected any city", gameMenuController.cityAttack(hss));
		game.setSelectedObject(randomCity);
		game.nextTurn();
		assertEquals("you don't own this city", gameMenuController.cityAttack(hss));
		game.nextTurn();
		assertEquals("invalid tile", gameMenuController.cityAttack(hss));
		hss.put("target", "1");
		assertEquals("there isn't any armed unit there",
				gameMenuController.cityAttack(hss));
		randomTile.setArmedUnit(new Armed(UnitType.WARRIOR, randomTile, enemy));
		assertEquals("this unit is not in attack range", gameMenuController.cityAttack(hss));
	}

	@Test
	void unitActionListTest(){
		HSS hss = new HSS();
		game.setSelectedObject(null);
		assertEquals("worker is not selected !", gameMenuController.unitActionList(hss));
		game.setSelectedObject(new Worker(UnitType.WORKER, randomTile, enemy));
		assertEquals("you don't own this unit", gameMenuController.unitActionList(hss));
		game.nextTurn();
		randomTile.buildRoad();
		final TechTree techTree = game.getCurrentPlayer().getCivilization().getResearchTree();
		randomTile.setFeature(TerrainFeature.FOREST);
		techTree.research(TechnologyType.AGRICULTURE);
		techTree.addScience(1000);
		techTree.research(TechnologyType.ANIMAL_HUSBANDRY);
		techTree.addScience(1000);
		randomTile.setCivilization(enemy);
		randomTile.setTerrain(Terrain.GRASSLAND);
		assertEquals("-- Remove Forest\n" +
				"-- Remove Road\n" +
				"-- Build Improvement Pasture\n", gameMenuController.unitActionList(hss));
	}
}
