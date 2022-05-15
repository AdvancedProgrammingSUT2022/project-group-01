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
import model.unit.armed.RangedUnit;
import model.unit.armed.Siege;
import model.unit.civilian.Civilian;
import model.unit.civilian.Settler;
import model.unit.civilian.Worker;
import model.unit.trait.TraitsList;
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

import java.util.*;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GameMenuControllerTest {
    private  Game game;
    private  MapController mc;
    private  GameMenuController gameMenuController;
    @BeforeEach
    public void beforechert(){
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

    @Test
    void unitPillageTest(){
        game.setSelectedObject(game.getCurrentPlayer().getCivilization().getUnits().get(0));
        gameMenuController.unitPillage(null);
        game.setSelectedObject(null);
        gameMenuController.unitPillage(null);
        game.nextTurn();
        game.setSelectedObject(game.getPlayers().get(0).getCivilization().getUnits().get(0));
        gameMenuController.unitPillage(null);
        game.nextTurn();
    }

    @Test
    void increaseResourceTest(){
        HashMap<String,String> hashMap = new HashMap<>();
        hashMap.put("section","IRON");
        hashMap.put("amount","2");
        game.setSelectedObject(game.getCurrentPlayer().getCivilization().getCities().get(0));
        gameMenuController.increaseResource(hashMap);
        game.setSelectedObject(null);
        gameMenuController.increaseResource(hashMap);

    }
    @Test
    void showTileInfoTest(){
        HashMap<String,String> hashMap = new HashMap<>();
        hashMap.put("index","1");
        gameMenuController.showTileInfo(hashMap);
        hashMap.replace("index","90");
        gameMenuController.showTileInfo(hashMap);
    }
    @Test
    void destroyCityTest(){
        game.setSelectedObject(game.getCurrentPlayer().getCivilization().getCities().get(0));
        gameMenuController.destroyCity(null);
        game.setSelectedObject(null);
        gameMenuController.destroyCity(null);
    }
    @Test
    void showCurrentMapTest(){
        gameMenuController.showCurrentMap();
        gameMenuController.showPlayer(null);
    }

    @Test
    void nextTurnTest(){
        gameMenuController.nextTurn(null);
        HashMap<String,String> hashMap = new HashMap<>();
        hashMap.put("count","3");
        gameMenuController.multiNextTurn(hashMap);
    }

    @Test
    void testAddBeaker1() throws Exception {
        // Setup
        final HashMap<String, String> args = new HashMap<>(Map.ofEntries(Map.entry("value", "value")));

        // Run the test
        final String result = gameMenuController.addBeaker(args);

        // Verify the results
        assertEquals("invalid amount", result);
    }

    @Test
    void testCheatNextTurn1() throws Exception {
        // Setup
        final HashMap<String, String> args = new HashMap<>(Map.ofEntries(Map.entry("value", "value")));

        // Run the test
        final String result = gameMenuController.cheatNextTurn(args);

        // Verify the results
        assertEquals("time fast forwarded !", result);
    }


    @Test
    void testCurrentMenu1() throws Exception {
        assertEquals("Game Menu",
                gameMenuController.currentMenu(new HashMap<>(Map.ofEntries(Map.entry("value", "value")))));
    }

    @Test
    void testDamageUnit1() throws Exception {
        // Setup
        final HashMap<String, String> args = new HashMap<>(Map.ofEntries(Map.entry("value", "value")));

        // Run the test
        final String result = gameMenuController.damageUnit(args);

        // Verify the results
        assertEquals("invalid amount", result);
    }

    @Test
    void testDeletePopulation1() throws Exception {
        // Setup
        final HashMap<String, String> args = new HashMap<>(Map.ofEntries(Map.entry("value", "value")));

        // Run the test
        final String result = gameMenuController.deletePopulation(args);

        // Verify the results
        assertEquals("select city first!", result);
    }



    @Test
    void testGetCurrentResearch1() throws Exception {
        // Setup
        final HashMap<String, String> args = new HashMap<>(Map.ofEntries(Map.entry("value", "value")));

        // Run the test
        final String result = gameMenuController.getCurrentResearch(args);

        // Verify the results
        assertEquals("no research at this time", result);
    }

    @Test
    void testGetPurchasableTiles1() throws Exception {
        // Setup
        final HashMap<String, String> args = new HashMap<>(Map.ofEntries(Map.entry("value", "value")));

        // Run the test
        final String result = gameMenuController.getPurchasableTiles(args);

        // Verify the results
        assertEquals("select city first!", result);
    }




    @Test
    void testListOfPopulation1() throws Exception {
        // Setup
        final HashMap<String, String> args = new HashMap<>(Map.ofEntries(Map.entry("value", "value")));

        // Run the test
        final String result = gameMenuController.listOfPopulation(args);

        // Verify the results
        assertEquals("select city first!", result);
    }

    @Test
    void testListOfProductions1() throws Exception {
        // Setup
        final HashMap<String, String> args = new HashMap<>(Map.ofEntries(Map.entry("value", "value")));

        // Run the test
        final String result = gameMenuController.listOfProductions(args);

        // Verify the results
        assertEquals("select city first!", result);
    }


    @Test
    void testMapShow1() throws Exception {
        // Setup
        final HashMap<String, String> args = new HashMap<>(Map.ofEntries(Map.entry("value", "value")));

        // Run the test
        final String result = gameMenuController.mapShow(args);

        // Verify the results
        assertEquals("invalid command", result);
    }

    @Test
    void testMenuExit1() throws Exception {
        assertEquals("Done!", gameMenuController.menuExit(new HashMap<>(Map.ofEntries(Map.entry("value", "value")))));
    }

    @Test
    void testPurchaseProduction1() throws Exception {
        // Setup
        final HashMap<String, String> args = new HashMap<>(Map.ofEntries(Map.entry("value", "value")));

        // Run the test
        final String result = gameMenuController.purchaseProduction(args);

        // Verify the results
        assertEquals("select city first", result);
    }

    @Test
    void testPurchaseTile1() throws Exception {
        // Setup
        final HashMap<String, String> args = new HashMap<>(Map.ofEntries(Map.entry("value", "value")));

        // Run the test
        final String result = gameMenuController.purchaseTile(args);

        // Verify the results
        assertEquals("select city first!", result);
    }


    @Test
    void testSelectCity1() throws Exception {
        // Setup
        final HashMap<String, String> args = new HashMap<>(Map.ofEntries(Map.entry("value", "value")));

        // Run the test
        final String result = gameMenuController.selectCity(args);

        // Verify the results
        assertEquals("invalid command!", result);
    }

    @Test
    void testSelectUnit() throws Exception {
        // Setup
        final HashMap<String, String> args = new HashMap<>(Map.ofEntries(Map.entry("value", "value")));

        // Run the test
        final String result = gameMenuController.selectUnit(args);

        // Verify the results
        assertEquals("invalid position", result);
    }

    @Test
    void testSetProduction1() throws Exception {
        // Setup
        final HashMap<String, String> args = new HashMap<>(Map.ofEntries(Map.entry("value", "value")));

        // Run the test
        final String result = gameMenuController.setProduction(args);

        // Verify the results
        assertEquals("select city first!", result);
    }


    @Test
    void testShowNextTiles1() throws Exception {
        // Setup
        final HashMap<String, String> args = new HashMap<>(Map.ofEntries(Map.entry("value", "value")));

        // Run the test
        final String result = gameMenuController.showNextTiles(args);

        // Verify the results
        assertEquals("select city first!", result);
    }




    @Test
    void testUnitActionList() throws Exception {

        final HashMap<String, String> args = new HashMap<>(Map.ofEntries(Map.entry("value", "value")));


        final String result = gameMenuController.unitActionList(args);

        assertEquals("worker is not selected !", result);
    }



    @Test
    void testUnitInfo() throws Exception {

        final HashMap<String, String> args = new HashMap<>(Map.ofEntries(Map.entry("value", "value")));

        final String result = gameMenuController.unitInfo(args);

        assertEquals("you haven't select any unit", result);
    }


    @Test
    void removeFogOfWar(){
        HashMap<String,String> hashMap = new HashMap<>();
        hashMap.put("position","150");
        gameMenuController.removeFogOfWar(hashMap);
        hashMap.replace("position","-3");
        gameMenuController.removeFogOfWar(hashMap);
    }

    @Test
    void addHappinessTest(){
        HashMap<String,String> hashMap = new HashMap<>();
        hashMap.put("amount","150");
        gameMenuController.addHappiness(hashMap);
    }

    @Mock
    Worker worker;
    @Test
    void buildRailTest(){
        game.setSelectedObject(null);
        gameMenuController.buildRail(null);
    }


    @Test
    void unitFoundCity(){
        Settler settler = mock(Settler.class);
        game.getCurrentPlayer().getMapCenterTile().setCivilianUnit(settler);
        when(settler.getOwnerCivilization()).thenReturn(game.getCurrentPlayer().getCivilization());
        game.setSelectedObject(game.getCurrentPlayer().getMapCenterTile().getArmedUnit());
        gameMenuController.unitFoundCity(null);
        game.setSelectedObject(game.getCurrentPlayer().getMapCenterTile().getCivilianUnit());
        gameMenuController.unitFoundCity(null);
        game.setSelectedObject(game.getCurrentPlayer().getCivilization().getUnits().get(0));
        gameMenuController.unitFoundCity(null);
        when(settler.getOwnerCivilization()).thenReturn(game.getPlayers().get(1).getCivilization());
        game.setSelectedObject(settler);
        gameMenuController.unitFoundCity(null);

    }
    @Test
    void unitCanselMission(){
        Settler settler = mock(Settler.class);
        game.getCurrentPlayer().getMapCenterTile().setCivilianUnit(settler);
        when(settler.getOwnerCivilization()).thenReturn(game.getCurrentPlayer().getCivilization());
        game.setSelectedObject(game.getCurrentPlayer().getMapCenterTile().getCivilianUnit());
        gameMenuController.unitCancelMission(null);
        when(settler.getOwnerCivilization()).thenReturn(game.getPlayers().get(1).getCivilization());
        gameMenuController.unitCancelMission(null);
        game.setSelectedObject(null);
        gameMenuController.unitCancelMission(null);
    }

    @Test
    void unitWake(){
        Settler settler = mock(Settler.class);
        game.getCurrentPlayer().getMapCenterTile().setCivilianUnit(settler);
        when(settler.getOwnerCivilization()).thenReturn(game.getCurrentPlayer().getCivilization());
        game.setSelectedObject(game.getCurrentPlayer().getMapCenterTile().getCivilianUnit());
        gameMenuController.unitWake(null);
        when(settler.getOwnerCivilization()).thenReturn(game.getPlayers().get(1).getCivilization());
        gameMenuController.unitWake(null);
        game.setSelectedObject(null);
        gameMenuController.unitWake(null);
    }

    @Test
    void unitDeleteTest(){
        Settler settler = mock(Settler.class);
        when(settler.getType()).thenReturn(UnitType.SETTLER);
        game.setSelectedObject(null);
        gameMenuController.unitDelete(null);
        game.setSelectedObject(settler);
        when(settler.getOwnerCivilization()).thenReturn(game.getPlayers().get(1).getCivilization());
        gameMenuController.unitDelete(null);
        game.getCurrentPlayer().getMapCenterTile().setCivilianUnit(settler);
        when(settler.getOwnerCivilization()).thenReturn(game.getCurrentPlayer().getCivilization());
        game.setSelectedObject(game.getCurrentPlayer().getMapCenterTile().getCivilianUnit());
        gameMenuController.unitDelete(null);
    }

    @Test
    void mapshowTest3(){
      HashMap<String,String> hashMap = new HashMap<>();
      hashMap.put("position","100");
      gameMenuController.mapShow(hashMap);
      hashMap.remove("position");
      hashMap.put("cityname",game.getCurrentPlayer().getCivilization().getCities().get(0).getName());
      gameMenuController.mapShow(hashMap);
    }
    @Test
    void unitSetupTest(){
        TraitsList traitsList = mock(TraitsList.class);
        Settler settler = mock(Settler.class);
        game.getCurrentPlayer().getMapCenterTile().setCivilianUnit(settler);
        when(settler.getOwnerCivilization()).thenReturn(game.getCurrentPlayer().getCivilization());
        game.setSelectedObject(game.getCurrentPlayer().getMapCenterTile().getCivilianUnit());
        gameMenuController.unitSetup(null);
        when(settler.getOwnerCivilization()).thenReturn(game.getPlayers().get(1).getCivilization());
        gameMenuController.unitSetup(null);
        game.setSelectedObject(null);
        gameMenuController.unitSetup(null);
        Siege siege = mock(Siege.class);
        game.getCurrentPlayer().getMapCenterTile().setArmedUnit(siege);
        when(siege.getOwnerCivilization()).thenReturn(game.getCurrentPlayer().getCivilization());
        game.setSelectedObject(siege);
        when(siege.getTraitsList()).thenReturn(traitsList);
        gameMenuController.unitSetup(null);

    }
    @Test
    void unitMeleeAttackTest(){
        HashMap<String,String> hashMap = new HashMap<>();
        hashMap.put("position","100");
        gameMenuController.unitMeleeAttack(hashMap);
        hashMap.replace("position",String.valueOf(game.getCurrentPlayer().getCivilization().getUnits().get(0).getCurrentTile().getMapNumber()));
        game.setSelectedObject(game.getCurrentPlayer().getCivilization().getUnits().get(0));
        gameMenuController.unitMeleeAttack(hashMap);
    }

    @Test
    void unitRangedAttack(){
        HashMap<String,String> hashMap = new HashMap<>();
        hashMap.put("position","100");
        gameMenuController.unitRangedAttack(hashMap);
        RangedUnit rangedUnit = new RangedUnit(UnitType.LONG_SWORDSMAN,game.getMap().getTileByNumber(100),game.getCurrentPlayer().getCivilization() );
        game.getMap().getTileByNumber(100).setArmedUnit(rangedUnit);
        gameMenuController.unitRangedAttack(hashMap);
        game.setSelectedObject(rangedUnit);
        gameMenuController.unitRangedAttack(hashMap);
    }
    @Test
    void unitInfoTest(){
        game.setSelectedObject(game.getCurrentPlayer().getCivilization().getUnits().get(0));
        gameMenuController.unitInfo(null);
    }

    @Test
    void getterSetterCalls(){
        gameMenuController.getGame();
        gameMenuController.getMapController();
        gameMenuController.getUnitController();
        gameMenuController.getWorkerController();
        gameMenuController.getCityController();
        gameMenuController.getGameController();
        gameMenuController.setCityController(gameMenuController.getCityController());
    }
}