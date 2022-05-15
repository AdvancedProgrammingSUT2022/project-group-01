package controller;

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
import model.unit.armed.RangedUnit;
import model.unit.armed.Siege;
import model.unit.civilian.Civilian;
import model.unit.civilian.Settler;
import model.unit.civilian.Worker;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.annotation.Target;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

@ExtendWith(MockitoExtension.class)
public class WorkerControllerTest {
	private Game game;
	private MapController mc;
	private UnitController unitController;

	private Tile randomTile;
	private Civilization enemy;
	private City randomCity;
	private Civilization us;
	private WorkerController workerController;
	@BeforeEach
	public void init(){
		User user1 = new User("a","b","c");
		User user2 = new User("aa","bb","cc");
		TileController.initializeEnums();
		Vector<User> vec = new Vector<>(List.of(user1,user2));
		GameInitializer gi = new GameInitializer();
		game = gi.startGame(vec,17);
		mc = new MapController(game);
		workerController = new WorkerController(game);
		unitController = new UnitController(game);
		game.getMap().getMap();
		game.setMap(game.getMap());
		enemy = game.getCurrentPlayer().getCivilization();
		game.nextTurn();
		us = game.getCurrentPlayer().getCivilization();
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

		ProgramController.setGame(game);
	}

	@Test
	void checkSelectedUnitTest() {
		Worker worker = new Worker(UnitType.WORKER, randomTile, enemy);
		game.setSelectedObject(worker);
		assertTrue(workerController.checkSelectedObject());
	}

	@Test
	void buildImprovementTest(){
		Worker worker = new Worker(UnitType.WORKER, randomTile, enemy);
		final TechTree techTree = game.getCurrentPlayer().getCivilization().getResearchTree();
		techTree.research(TechnologyType.AGRICULTURE); techTree.addScience(1000);
		techTree.research(TechnologyType.ANIMAL_HUSBANDRY); techTree.addScience(1000);
		randomTile.setFeature(null);
		randomTile.setTerrain(Terrain.GRASSLAND);
		randomTile.setCivilization(game.getCurrentPlayer().getCivilization());
		assertEquals("Improvement is constructing", workerController.buildImprovement(worker, ImprovementType.PASTURE));
		worker.setRemainingMP(0);
		assertEquals("unit is out of movement point", workerController.buildImprovement(worker, ImprovementType.PASTURE));
	}

	@Test
	void removeFeatureTest(){
		Worker worker = new Worker(UnitType.WORKER, randomTile, enemy);
		worker.setRemainingMP(0);
		randomTile.setFeature(TerrainFeature.FOREST);
		assertEquals("unit is out of movement point", workerController.removeFeature(worker));
		worker.setRemainingMP(4);
		assertEquals("operation remove started", workerController.removeFeature(worker));
	}

	@Test
	void removeImprovementTest(){
		Worker worker = new Worker(UnitType.WORKER, randomTile, enemy);
		randomTile.buildImprovement(ImprovementType.MINE);
		randomTile.buildImprovement(ImprovementType.MINE);
		randomTile.buildImprovement(ImprovementType.MINE);
		randomTile.buildImprovement(ImprovementType.MINE);
		randomTile.buildImprovement(ImprovementType.MINE);
		randomTile.buildImprovement(ImprovementType.MINE);
		randomTile.buildImprovement(ImprovementType.MINE);
		worker.setRemainingMP(0);
		assertEquals("unit is out of movement point", workerController.removeImprovement(worker));
		worker.setRemainingMP(4);
		assertEquals("this tile is being remove", workerController.removeImprovement(worker));
	}

	@Test
	void buildRoadTest(){
		Worker worker = new Worker(UnitType.WORKER, randomTile, enemy);
		worker.setRemainingMP(0);
		assertEquals("unit is out of movement point", workerController.buildRoad(worker));
		worker.setRemainingMP(4);
		assertEquals("road is under construction", workerController.buildRoad(worker));
	}

	@Test
	void buildRailTest(){
		Worker worker = new Worker(UnitType.WORKER, randomTile, enemy);
		worker.setRemainingMP(0);
		assertEquals("unit is out of movement point", workerController.buildRail(worker));
		worker.setRemainingMP(4);
		assertEquals("rail road is under construction", workerController.buildRail(worker));
	}

	@Test
	void removeRouteTest(){
		Worker worker = new Worker(UnitType.WORKER, randomTile, enemy);
		randomTile.buildRoad();
		worker.setRemainingMP(0);
		assertEquals("unit is out of movement point", workerController.removeRoute(worker));
		worker.setRemainingMP(4);
		assertEquals("route is being removed now", workerController.removeRoute(worker));
	}
}
