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
        WorkerController workerController = new WorkerController(game);
        gameMenuController = new GameMenuController(game,new GameController(game,mc),new CityController(game),new UnitController(game), workerController);
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
    void testAddBeaker() throws Exception {
        // Setup
        final HashMap<String, String> args = new HashMap<>(Map.ofEntries(Map.entry("value", "value")));

        // Run the test
        final String result = gameMenuController.addBeaker(args);

        // Verify the results
        assertEquals("invalid amount", result);
    }


    @Test
    void testBuildImprovement() throws Exception {
        // Setup
        final HashMap<String, String> args = new HashMap<>(Map.ofEntries(Map.entry("value", "value")));

        // Run the test
        final String result = gameMenuController.buildImprovement(args);

        // Verify the results
        assertEquals("you haven't select any unit", result);
    }


    @Test
    void testCheatNextTurn() throws Exception {
        // Setup
        final HashMap<String, String> args = new HashMap<>(Map.ofEntries(Map.entry("value", "value")));

        // Run the test
        final String result = gameMenuController.cheatNextTurn(args);

        // Verify the results
        assertEquals("time fast forwarded !", result);
    }



    @Test
    void testCurrentMenu() throws Exception {
        assertEquals("Game Menu",
                gameMenuController.currentMenu(new HashMap<>(Map.ofEntries(Map.entry("value", "value")))));
    }

    @Test
    void testDamageUnit() throws Exception {
        // Setup
        final HashMap<String, String> args = new HashMap<>(Map.ofEntries(Map.entry("value", "value")));

        // Run the test
        final String result = gameMenuController.damageUnit(args);

        // Verify the results
        assertEquals("invalid amount", result);
    }

    @Test
    void testDeletePopulation() throws Exception {
        // Setup
        final HashMap<String, String> args = new HashMap<>(Map.ofEntries(Map.entry("value", "value")));

        // Run the test
        final String result = gameMenuController.deletePopulation(args);

        // Verify the results
        assertEquals("select city first!", result);
    }



    @Test
    void testGetCurrentResearch() throws Exception {
        // Setup
        final HashMap<String, String> args = new HashMap<>(Map.ofEntries(Map.entry("value", "value")));

        // Run the test
        final String result = gameMenuController.getCurrentResearch(args);

        // Verify the results
        assertEquals("no research at this time", result);
    }

    @Test
    void testGetPurchasableTiles() throws Exception {
        // Setup
        final HashMap<String, String> args = new HashMap<>(Map.ofEntries(Map.entry("value", "value")));

        // Run the test
        final String result = gameMenuController.getPurchasableTiles(args);

        // Verify the results
        assertEquals("select city first!", result);
    }



    @Test
    void testListOfPopulation() throws Exception {
        // Setup
        final HashMap<String, String> args = new HashMap<>(Map.ofEntries(Map.entry("value", "value")));

        // Run the test
        final String result = gameMenuController.listOfPopulation(args);

        // Verify the results
        assertEquals("select city first!", result);
    }

    @Test
    void testListOfProductions() throws Exception {
        // Setup
        final HashMap<String, String> args = new HashMap<>(Map.ofEntries(Map.entry("value", "value")));

        // Run the test
        final String result = gameMenuController.listOfProductions(args);

        // Verify the results
        assertEquals("select city first!", result);
    }


    @Test
    void testMapShow() throws Exception {
        // Setup
        final HashMap<String, String> args = new HashMap<>(Map.ofEntries(Map.entry("value", "value")));

        // Run the test
        final String result = gameMenuController.mapShow(args);

        // Verify the results
        assertEquals("invalid command", result);
    }


    @Test
    void testMenuExit() throws Exception {
        assertEquals("Done!", gameMenuController.menuExit(new HashMap<>(Map.ofEntries(Map.entry("value", "value")))));
    }

    @Test
    void testPurchaseProduction() throws Exception {
        // Setup
        final HashMap<String, String> args = new HashMap<>(Map.ofEntries(Map.entry("value", "value")));

        // Run the test
        final String result = gameMenuController.purchaseProduction(args);

        // Verify the results
        assertEquals("select city first", result);
    }

    @Test
    void testPurchaseTile() throws Exception {
        // Setup
        final HashMap<String, String> args = new HashMap<>(Map.ofEntries(Map.entry("value", "value")));

        // Run the test
        final String result = gameMenuController.purchaseTile(args);

        // Verify the results
        assertEquals("select city first!", result);
    }


    @Test
    void testSelectCity() throws Exception {
        // Setup
        final HashMap<String, String> args = new HashMap<>(Map.ofEntries(Map.entry("value", "value")));

        // Run the test
        final String result = gameMenuController.selectCity(args);

        // Verify the results
        assertEquals("invalid command!", result);
    }


    @Test
    void testSetProduction() throws Exception {

        final HashMap<String, String> args = new HashMap<>(Map.ofEntries(Map.entry("value", "value")));

        final String result = gameMenuController.setProduction(args);

        assertEquals("select city first!", result);
    }



    @Test
    void testShowNextTiles() throws Exception {
        // Setup
        final HashMap<String, String> args = new HashMap<>(Map.ofEntries(Map.entry("value", "value")));

        // Run the test
        final String result = gameMenuController.showNextTiles(args);

        // Verify the results
        assertEquals("select city first!", result);
    }


    @Test
    void moveUnitTest(){
        ProgramController.setGame(game);
        game.setSelectedObject(game.getCurrentPlayer().getCivilization().getUnits().get(0));
        HashMap<String,String> hashMap = new HashMap<>();
        hashMap.put("position",String.valueOf(game.getCurrentPlayer().getMapCenterTile().getMapNumber()));
        gameMenuController.unitMove(hashMap);
    }
    @Test
    void unitSleepTest(){
        game.setSelectedObject(game.getCurrentPlayer().getCivilization().getUnits().get(0));
        gameMenuController.unitSleep(null);
        game.setSelectedObject(null);
        gameMenuController.unitSleep(null);
    }

    @Test
    void unitAlert(){
        //TODO COMPLETE HERE AFTER MERGE WITH AMUSFR
        gameMenuController.unitAlert(null);
    }

    @Test
    void unitFortify(){
        game.setSelectedObject(game.getCurrentPlayer().getCivilization().getUnits().get(0));
        gameMenuController.unitFortify(null);
        game.setSelectedObject(null);
        gameMenuController.unitFortify(null);
    }

    @Test
    void unitFortifyUntilHeal(){
        game.setSelectedObject(game.getCurrentPlayer().getCivilization().getUnits().get(0));
        gameMenuController.unitFortifyUntilHeal(null);
        game.setSelectedObject(null);
        gameMenuController.unitFortifyUntilHeal(null);
        Assertions.assertTrue(gameMenuController.unitFortifyUntilHeal(null).contains("haven't select"));
    }

//    @Test
//    void unitPillageTest(){
//        game.setSelectedObject(game.getCurrentPlayer().getCivilization().getUnits().get(0));
//        gameMenuController.unitPillage(null);
//        game.setSelectedObject(null);
//        gameMenuController.unitPillage(null);
//        game.nextTurn();
//        game.setSelectedObject(game.getPlayers().get(0).getCivilization().getUnits().get(0));
//        gameMenuController.unitPillage(null);
//        game.nextTurn();
//    }

}