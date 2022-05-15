package controller;

import controller.unit.UnitController;
import controller.unit.WorkerController;
import model.*;
import model.civilization.Civilization;
import model.civilization.city.City;
import model.improvement.ImprovementType;
import model.resource.ResourceType;
import model.tile.Terrain;
import model.tile.Tile;
import model.unit.Unit;
import model.unit.UnitType;
import model.unit.armed.Armed;
import model.unit.armed.RangedUnit;
import model.unit.armed.Siege;
import model.unit.civilian.Civilian;
import model.unit.civilian.Settler;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

@ExtendWith(MockitoExtension.class)
public class UnitControllerTest {
	public class HSS extends HashMap<String, String> {}

	private Game game;
	private MapController mc;
	private UnitController unitController;

	private Tile randomTile;
	private Civilization enemy;
	private City randomCity;
	private Civilization us;
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
	void foundCityTest() {
		Settler settler = new Settler(UnitType.SETTLER, randomTile, enemy);
		settler.setRemainingMP(0);
		assertEquals("unit is out of movement point", unitController.foundCity(settler));
		game.nextTurn();
		game.nextTurn();
		assertEquals("City Founded", unitController.foundCity(settler));
		settler = new Settler(UnitType.SETTLER, randomTile, enemy);
		assertEquals("Cannot Settle new city here", unitController.foundCity(settler));
	}

	@Test
	void deleteTest(){
		Unit unit = new Settler(UnitType.SETTLER, randomTile, enemy);
		assertEquals("ma ro dor nandaz, ma inghadram be dard nakhor nistim",
				unitController.delete(unit));
	}

	@Test
	void sleepTest(){
		Unit unit = new Settler(UnitType.SETTLER, randomTile, enemy);
		unit.sleep();
		assertEquals("this unit is already sleeping",
				unitController.sleep(unit));
	}

	@Test
	void cancelAndWakeTest(){
		Unit unit = new Settler(UnitType.SETTLER, randomTile, enemy);
		unit.sleep();
		assertEquals("actions canceled", unitController.cancel(unit));
		unit.sleep();
		assertEquals("ready for orders", unitController.wake(unit));
		assertEquals("this unit is already awake", unitController.wake(unit));
		assertEquals("this unit is not doing anything\n", unitController.cancel(unit));
	}

	@Test
	void infoTest(){
		Unit unit = new Settler(UnitType.SETTLER, randomTile, enemy);
		unit.sleep();
		assertEquals("Owner is Mongolian\n" +
						"Unit is at 1\n" +
						"Current Health is 10.000000 / 10\n" +
						"Unit Type : Settler\n" +
						"remaining Movement Point is 2.000000\n" +
						"current action is Sleep\n", unitController.info(unit));
	}

	@Test
	void fortifyTest(){
		Unit unit = new Settler(UnitType.SETTLER, randomTile, enemy);
		unit.sleep();
		assertEquals("this is not an armed unit", unitController.fortify(unit));
	}

	@Test
	void fortifyHealTest(){
		Unit unit = new Settler(UnitType.SETTLER, randomTile, enemy);
		unit.sleep();
		assertEquals("this is not an armed unit", unitController.fortifyUntilHeal(unit));
		Armed armed = new Armed(UnitType.WARRIOR, randomTile, enemy);
		armed.changeHealth(-1);
		assertEquals("fortified until heal", unitController.fortifyUntilHeal(armed));
	}
	@Test
	void damageTest(){
		Armed armed = new Armed(UnitType.WARRIOR, randomTile, enemy);
		assertEquals("damage applied successfully", unitController.damage(armed, 1));
	}

	@Test
	void pillageTest(){
		Armed armed = new Armed(UnitType.WARRIOR, randomTile, enemy);
		armed.setRemainingMP(0);
		assertEquals("unit is out of movement point", unitController.pillage(armed));
		armed.setRemainingMP(3);
		assertEquals("there is nothing here to pillage", unitController.pillage(armed));
	}

	@Test
	void teleportTest(){
		Armed armed = new Armed(UnitType.WARRIOR, randomTile, enemy);
		randomTile.setTerrain(Terrain.GRASSLAND);
		Tile dest = game.getMap().getTileByNumber(2);
		dest.setTerrain(Terrain.GRASSLAND);
		assertEquals("teleported", unitController.teleport(armed, dest));
		armed.setRemainingMP(3);
		Armed armed2 = new Armed(UnitType.WARRIOR, dest, enemy);
		assertEquals("you can't have 2 unit of same type in one tile", unitController.teleport(armed, dest));
	}

	@Test
	void alertTest(){
		Settler unit = new Settler(UnitType.SETTLER, randomTile, enemy);
		assertEquals("this is not an armed unit", unitController.alert(unit));
		Armed armed = new Armed(UnitType.WARRIOR, randomTile, enemy);
		assertEquals("alert\n", unitController.alert(armed));
	}

	@Test
	void meleeAttackTest(){
		Armed armed = new Armed(UnitType.WARRIOR, randomTile, enemy);
		Armed archer = new Armed(UnitType.ARCHER, randomTile, enemy);
		Tile dest = game.getMap().getTileByNumber(2);
		Tile farDest = game.getMap().getTileByNumber(3);
		armed.setRemainingMP(0);
		assertEquals("unit is out of movement point", unitController.meleeAttack(armed, dest));
		armed.setRemainingMP(4);
		assertEquals("this unit can't melee attack", unitController.meleeAttack(archer, dest));
		assertEquals("this unit is not nearby", unitController.meleeAttack(armed, farDest));
		assertEquals("no city in destination tile", unitController.meleeAttack(armed, dest));
		Settler settler = new Settler(UnitType.SETTLER, dest, enemy);
		unitController.foundCity(settler);
		City city = dest.getOwnerCity();
		assertEquals("why you want to betray your people ??", unitController.meleeAttack(armed, dest));
		armed.setOwnerCivilization(us);
		assertEquals("knives out", unitController.meleeAttack(armed, dest));
		armed.suicide();
		armed = new Armed(UnitType.TANK, randomTile, us);
		assertEquals("knives out", unitController.meleeAttack(armed, dest));
//		assertEquals("knives out", unitController.meleeAttack(armed, dest));
	}

	@Test
	void rangedAttackTest(){
		RangedUnit armed = new Siege(UnitType.TREBUCHET, randomTile, enemy);
		RangedUnit archer = new RangedUnit(UnitType.ARCHER, randomTile, enemy);
		Tile dest = game.getMap().getTileByNumber(2);
		Tile farDest = game.getMap().getTileByNumber(3);
		armed.setRemainingMP(0);
		assertEquals("unit is out of movement point", unitController.rangedAttack(armed, dest));
		armed.setRemainingMP(4);
		assertEquals("this unit need setup before attack", unitController.rangedAttack(armed, dest));
		((Siege)armed).completeSetup();
		assertEquals("this unit is not in attack range", unitController.rangedAttack(armed, farDest));
		assertEquals("no city in destination tile", unitController.rangedAttack(armed, dest));
		Settler settler = new Settler(UnitType.SETTLER, dest, enemy);
		unitController.foundCity(settler);
		City city = dest.getOwnerCity();
		assertEquals("why you want to betray your people ??", unitController.rangedAttack(armed, dest));
		armed.setOwnerCivilization(us);
		assertEquals("bows out", unitController.rangedAttack(armed, dest));
		armed.suicide();
		armed = new Siege(UnitType.ARTILLERY, randomTile, us);
		((Siege) armed).completeSetup();
		assertEquals("bows out", unitController.rangedAttack(armed, dest));
		armed.suicide();
		armed = new RangedUnit(UnitType.CHARIOT_ARCHER, randomTile, us);
		assertEquals("bows out", unitController.rangedAttack(armed, dest));
	}

	@Test
	void setupTest(){
		Siege armed = new Siege(UnitType.TREBUCHET, randomTile, enemy);
		armed.setRemainingMP(0);
		assertEquals("unit is out of movement point", unitController.setup(armed));
		armed.setRemainingMP(4);
		assertEquals("setup unit done", unitController.setup(armed));
		armed.completeSetup();
		assertEquals("this unit is  already ready to attack", unitController.setup(armed));
	}

}
