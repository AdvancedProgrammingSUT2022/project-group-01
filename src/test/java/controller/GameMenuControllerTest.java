package controller;

import controller.civilization.city.CityController;
import controller.unit.UnitController;
import controller.unit.WorkerController;
import model.*;
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
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GameMenuControllerTest {
    private static Game game;
    private static MapController mc;
    private static GameMenuController gameMenuController;
    @BeforeAll
    public static void beforechert(){
        User user1 = new User("a","b","c");
        User user2 = new User("aa","bb","cc");
        TileController.initializeEnums();
        Vector<User> vec = new Vector<>(List.of(user1,user2));
        GameInitializer gi = new GameInitializer();
        game = gi.startGame(vec,17);
        mc = new MapController(game);
        gameMenuController = new GameMenuController(game,new GameController(game,mc),new CityController(game),new UnitController(game), new WorkerController(game));
        game.getMap().getMap();
        game.setMap(game.getMap());
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
    }

    @Mock
    Armed armed;
    @Mock
    Civilian civilian;
    @Test
    void selectUnitTest(){
        HashMap<String,String> hashMap1 = new HashMap<>();
        hashMap1.put("section","armed");
        int a = game.getCurrentPlayer().getMapCenterTile().getMapNumber();
        String number = Integer.toString(a);
        hashMap1.put("position",number);
       gameMenuController.selectUnit(hashMap1);
       game.getCurrentPlayer().getMapCenterTile().setArmedUnit(armed);
        gameMenuController.selectUnit(hashMap1);
        HashMap<String,String> hashMap2 = new HashMap<>();
        hashMap2.put("section","civilian");
        hashMap2.put("position",number);
        gameMenuController.selectUnit(hashMap2);
        game.getCurrentPlayer().getMapCenterTile().setCivilianUnit(civilian);
        gameMenuController.selectUnit(hashMap2);
        hashMap2.replace("section","chert");
        gameMenuController.selectUnit(hashMap2);
    }

    @Test
    void moveUnitTest(){

    }
}