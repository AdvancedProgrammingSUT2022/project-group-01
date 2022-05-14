package controller;

import model.Game;
import model.ProgressState;
import model.TurnBasedLogic;
import model.User;
import model.civilization.city.City;
import model.improvement.ImprovementType;
import model.resource.ResourceType;
import model.tile.TerrainFeature;
import model.tile.Tile;
import model.unit.Unit;
import model.unit.UnitType;
import model.unit.armed.Armed;
import model.unit.civilian.Civilian;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Vector;

import static org.mockito.Mockito.spy;

public class GameControllerTest {

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
        game.getMap().getMap();
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
    }
    @Test
    public void mapShowTest(){

        GameController gameController = new GameController(game,mc);
        gameController.mapShow("pos","2");
        gameController.mapShow("position","40");
        gameController.mapShow("position","10000");
        gameController.mapShow("cityname","uzarsif");
        gameController.mapShow("cityname",game.getCurrentPlayer().getCivilization().getCities().get(0).getName());
    }

    @Test
    public void showTileInfoTest(){
        GameController gc = new GameController(game,mc);
        gc.showTileInfo(game.getCurrentPlayer().getMapCenterTile());
        game.getCurrentPlayer().getMapCenterTile().setCivilization(game.getCurrentPlayer().getCivilization());
        game.getCurrentPlayer().getMapCenterTile().buildImprovement(ImprovementType.LUMBER_MILL);
        game.nextTurn();
        game.nextTurn();
        game.nextTurn();
        game.nextTurn();
        game.nextTurn();
        gc.showTileInfo(game.getCurrentPlayer().getMapCenterTile());
    }

    @Test
    public void selectCityTest(){
        game.getCurrentPlayer().getMapCenterTile().setCivilization(game.getCurrentPlayer().getCivilization());
        game.getCurrentPlayer().getMapCenterTile().buildImprovement(ImprovementType.LUMBER_MILL);
        game.nextTurn();
        game.nextTurn();
        game.nextTurn();
        game.nextTurn();
        GameController gc = new GameController(game,mc);
        gc.selectCity("position","51");
        gc.selectCity("pos","100");
        gc.selectCity("position","-4");
        gc.selectCity("position",String.valueOf(game.getCurrentPlayer().getMapCenterTile().getMapNumber()));
        gc.selectCity("name","hallooha");
        gc.selectCity("name",game.getCurrentPlayer().getMapCenterTile().getOwnerCity().getName());
        gc.cheatSetFeature(game.getCurrentPlayer().getMapCenterTile(), TerrainFeature.FOREST);
        gc.cheatSetFeature(game.getMap().getTileByNumber(30),TerrainFeature.FOREST );
        gc.cheatSetFeature(game.getMap().getTileByNumber(30),TerrainFeature.ICE );
        gc.cheatSetFeature(game.getMap().getTileByNumber(40),TerrainFeature.OASIS );
    }
}