package model.tile;

import controller.GameInitializer;
import controller.MapController;
import controller.TileController;
import model.Game;
import model.ProgressState;
import model.TurnBasedLogic;
import model.User;
import model.civilization.city.City;
import model.improvement.ImprovementType;
import model.resource.ResourceType;
import model.unit.Unit;
import model.unit.UnitType;
import model.unit.armed.Armed;
import model.unit.civilian.Civilian;
import model.unit.civilian.Worker;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Vector;

public class TestForTerrains {
    private static Game game;
    private static MapController mc;
    @BeforeAll
    public static void beforechert(){
        User user1 = new User("a","b","c");
        User user2 = new User("aa","bb","cc");
        TileController.initializeEnums();
        Vector<User> vec = new Vector<>(List.of(user1,user2));
        GameInitializer gi = new GameInitializer();
        game = gi.startGame(vec,17);
        mc = new MapController(game);
        Tile tile = game.getCurrentPlayer().getMapCenterTile();
        tile.setCivilization(game.getCurrentPlayer().getCivilization());
        Armed armed = new Armed(UnitType.WARRIOR,tile,game.getCurrentPlayer().getCivilization());
        Civilian civilian = new Civilian(UnitType.WORKER,tile,game.getCurrentPlayer().getCivilization());
        tile.setArmedUnit(armed);
        tile.setCivilianUnit(civilian);
        tile.setAvailableResource(ResourceType.IRON);
        tile.buildRoad();
        tile.buildImprovement(ImprovementType.LUMBER_MILL);
        TurnBasedLogic.callNextTurns(game.getCurrentPlayer().getCivilization());
        TurnBasedLogic.callNextTurns(game.getCurrentPlayer().getCivilization());
        TurnBasedLogic.callNextTurns(game.getCurrentPlayer().getCivilization());
        TurnBasedLogic.callNextTurns(game.getCurrentPlayer().getCivilization());
        TurnBasedLogic.callNextTurns(game.getCurrentPlayer().getCivilization());
        TurnBasedLogic.callNextTurns(game.getCurrentPlayer().getCivilization());
        TurnBasedLogic.callNextTurns(game.getCurrentPlayer().getCivilization());
        Assertions.assertEquals(tile.getImprovementInventoryState(), ProgressState.COMPLETE);
        mc.updateSavedMap(game.getCurrentPlayer(),new Vector<>(List.of(tile)),game.getMap());
        mc.getConsoleMap(tile);
        TileController.initializeEnums();
        City city = new City(game.getCurrentPlayer().getCivilization().getCivilization().getCityNames()[1],game.getCurrentPlayer().getCivilization(),tile);
        game.getCurrentPlayer().getCivilization().addNewCity(city);
        tile.setOwnerCity(city);
        tile.setCivilization(city.getCivilization());
        city.updateBeaker();
        Unit unit = new Armed(UnitType.WARRIOR,city.getCenter(),game.getCurrentPlayer().getCivilization());
        city.setGarrisonedUnit(unit);
        city.getGarrisonedUnit();
        city.updateDefencePower(2);
        city.setAttackPower(4);
        game.getCurrentPlayer().getMapCenterTile().setOwnerCity(city);
    }

    @Test
    public void yields(){
        int a = game.getMap().getTileByNumber(30).getGoldYield();
        game.getMap().getTileByNumber(30).getFoodYield();
        game.getMap().getTileByNumber(30).getProductionYield();
        game.getMap().getTileByNumber(30).setFeature(TerrainFeature.ICE);
        int b = game.getMap().getTileByNumber(30).getGoldYield();
        game.getMap().getTileByNumber(30).getFoodYield();
        game.getMap().getTileByNumber(30).getProductionYield();
        Assertions.assertTrue(b >= a);
    }

//    @Test
//    public void roadAndPassing(){
//        game.getMap().getTileByNumber(30).isPassable();
//        game.getMap().getTileByNumber(30).buildRoute();
//        game.getMap().getTileByNumber(30).removeFeature();
//
//    }

//    @Test
//    public void improvementActions(){
//        Tile tile = game.getCurrentPlayer().getMapCenterTile();
//        tile.buildImprovement(ImprovementType.LUMBER_MILL);
//        TurnBasedLogic.callNextTurns(game.getCurrentPlayer().getCivilization());
//        tile.stopImprovementProgress();
//        tile.buildImprovement(ImprovementType.LUMBER_MILL);
//        TurnBasedLogic.callNextTurns(game.getCurrentPlayer().getCivilization());
//        TurnBasedLogic.callNextTurns(game.getCurrentPlayer().getCivilization());
//        TurnBasedLogic.callNextTurns(game.getCurrentPlayer().getCivilization());
//        TurnBasedLogic.callNextTurns(game.getCurrentPlayer().getCivilization());
//        Assertions.assertEquals(tile.getImprovementInventoryState(),ProgressState.COMPLETE);
//        TurnBasedLogic.callNextTurns(game.getCurrentPlayer().getCivilization());
//        Assertions.assertEquals(tile.getImprovementInAction(),ImprovementType.LUMBER_MILL);
//        tile.pillageImprovement();
//        Assertions.assertEquals(tile.getImprovementInventoryState(), ProgressState.DAMAGED);
//        tile.repairImprovement();
//        TurnBasedLogic.callNextTurns(game.getCurrentPlayer().getCivilization());
//        TurnBasedLogic.callNextTurns(game.getCurrentPlayer().getCivilization());
//        TurnBasedLogic.callNextTurns(game.getCurrentPlayer().getCivilization());
//        Assertions.assertEquals(tile.getImprovementTurnsLeft(),0);
//        tile.removeBuiltImprovements(ImprovementType.LUMBER_MILL);
//        Assertions.assertNull(tile.getImprovementInventory().getImprovement());
//    }

    @Test
    public void removeUnit(){
        Tile tile = game.getCurrentPlayer().getMapCenterTile();
        tile.removeUnit(tile.getArmedUnit());
        tile.removeUnit(tile.getCivilianUnit());
    }
    @Test
    public void getMaintenance(){
        Tile tile = game.getCurrentPlayer().getMapCenterTile();
        tile.removeRoads();
        int a = tile.getMaintenance();
        tile.buildRoad();
        TurnBasedLogic.callNextTurns(game.getCurrentPlayer().getCivilization());
        TurnBasedLogic.callNextTurns(game.getCurrentPlayer().getCivilization());
        TurnBasedLogic.callNextTurns(game.getCurrentPlayer().getCivilization());
        TurnBasedLogic.callNextTurns(game.getCurrentPlayer().getCivilization());
        TurnBasedLogic.callNextTurns(game.getCurrentPlayer().getCivilization());
        int b = tile.getMaintenance();
        Assertions.assertEquals(b, a + 3);
    }

    @Test
    public void checkUnitType(){
        Armed armed = new Armed(UnitType.WARRIOR,game.getCurrentPlayer().getMapCenterTile(),game.getCurrentPlayer().getCivilization());
        Civilian civilian = new Civilian(UnitType.SETTLER,game.getCurrentPlayer().getMapCenterTile(),game.getCurrentPlayer().getCivilization());
        Tile tile = game.getCurrentPlayer().getMapCenterTile();
        tile.setArmedUnit(armed);
        tile.setCivilianUnit(civilian);
        Assertions.assertEquals(armed,tile.getSameTypeUnit(armed));
        Assertions.assertEquals(civilian,tile.getSameTypeUnit(civilian));
    }

    @Test
    public void sightTest(){
        Tile tile = game.getCurrentPlayer().getMapCenterTile();
        Assertions.assertTrue(tile.getSight(2).contains(tile.getBoarder(2).getOtherTile(tile)));
        Assertions.assertTrue(tile.getSight(1).contains(tile.getBoarder(2).getOtherTile(tile)));
    }

}
