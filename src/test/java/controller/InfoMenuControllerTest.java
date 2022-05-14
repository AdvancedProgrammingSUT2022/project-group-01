package controller;

import model.*;
import model.civilization.Civilization;
import model.civilization.city.City;
import model.improvement.ImprovementType;
import model.resource.ResourceType;
import model.technology.TechnologyType;
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
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class InfoMenuControllerTest {

    private static Game game;
    private static MapController mc;
    @BeforeAll
    public static void beforechert() {
        User user1 = new User("a", "b", "c");
        User user2 = new User("aa", "bb", "cc");
        TileController.initializeEnums();
        Vector<User> vec = new Vector<>(List.of(user1, user2));
        GameInitializer gi = new GameInitializer();
        game = gi.startGame(vec, 17);
        mc = new MapController(game);
        game.getMap().getMap();
        Tile tile = game.getCurrentPlayer().getMapCenterTile();
        tile.setCivilization(game.getCurrentPlayer().getCivilization());
        Armed armed = new Armed(UnitType.WARRIOR, tile, game.getCurrentPlayer().getCivilization());
        Civilian civilian = new Civilian(UnitType.WORKER, tile, game.getCurrentPlayer().getCivilization());
        tile.setArmedUnit(armed);
        tile.setCivilianUnit(civilian);
        tile.setAvailableResource(ResourceType.IRON);
        tile.buildRoad();
        City city = new City("alo",game.getCurrentPlayer().getCivilization(),game.getCurrentPlayer().getMapCenterTile());
        game.getCurrentPlayer().getCivilization().addNewCity(city);
        tile.buildImprovement(ImprovementType.LUMBER_MILL);
        tile.buildImprovement(ImprovementType.LUMBER_MILL);
        tile.buildImprovement(ImprovementType.LUMBER_MILL);
        tile.buildImprovement(ImprovementType.LUMBER_MILL);
        tile.buildImprovement(ImprovementType.LUMBER_MILL);
        tile.buildImprovement(ImprovementType.LUMBER_MILL);
        tile.buildImprovement(ImprovementType.LUMBER_MILL);
        Assertions.assertEquals(tile.getImprovementInventoryState(), ProgressState.COMPLETE);
        mc.updateSavedMap(game.getCurrentPlayer(), new Vector<>(List.of(tile)), game.getMap());
        mc.moveCenterTile(1, "right");
        mc.getConsoleMap(tile);
        TileController.initializeEnums();
    }

    private InfoMenuController infoMenuControllerUnderTest;

    @BeforeEach
    void setUp() {
        infoMenuControllerUnderTest = new InfoMenuController(game);
    }

    @Test
    void testGetResearchRemainingTurn() {
        assertEquals(0, infoMenuControllerUnderTest.getResearchRemainingTurn());
    }

    @Test
    void testGetAllUnits() {
        // Setup
        // Run the test
        final Vector<Unit> result = infoMenuControllerUnderTest.getAllUnits();

        // Verify the results
    }

    @Test
    void testGetAllCities() {
        // Setup
        // Run the test
        final Vector<Unit> result = infoMenuControllerUnderTest.getAllCities();

        // Verify the results
    }

    @Test
    void testGetGameScore() {
        assertEquals(0, infoMenuControllerUnderTest.getGameScore());
    }

    @Test
    void testGetOtherCivilizations() {
        // Setup
        // Run the test
        final Vector<Civilization> result = infoMenuControllerUnderTest.getOtherCivilizations();

        // Verify the results
    }

    @Test
    void testGetNotifications() {
        // Setup
        // Run the test
        final Vector<Notification> result = infoMenuControllerUnderTest.getNotifications();

        // Verify the results
    }

    @Test
    void testGetInfo() {
        // Setup
        // Run the test
        final Information result = infoMenuControllerUnderTest.getInfo();

        // Verify the results
    }



    @Test
    void testMenuExit() {
        assertEquals("done!", infoMenuControllerUnderTest.menuExit(new HashMap<>(
                Map.ofEntries(Map.entry("value", "value")))));
    }

    @Test
    void testCurrentMenu() {
        // Setup
        final HashMap<String, String> args = new HashMap<>(Map.ofEntries(Map.entry("value", "value")));

        // Run the test
        final String result = infoMenuControllerUnderTest.currentMenu(args);

        // Verify the results
        assertEquals("information menu", result);
    }

    @Test
    void menuEnterTest(){
        HashMap<String,String> hashMap = new HashMap<>();
        hashMap.put("section","research");
        infoMenuControllerUnderTest.menuEnter(hashMap);
        hashMap.replace("section","unit");
        infoMenuControllerUnderTest.cityScreen(hashMap);
        infoMenuControllerUnderTest.economicOverview(hashMap);
        infoMenuControllerUnderTest.menuEnter(hashMap);
        hashMap.replace("section","1");
        infoMenuControllerUnderTest.activeUnit(hashMap);
        infoMenuControllerUnderTest.militaryOverview(hashMap);
        hashMap.replace("section","city");
        hashMap.put("name",game.getCurrentPlayer().getCivilization().getCities().get(0).getName());
        infoMenuControllerUnderTest.menuEnter(hashMap);
        infoMenuControllerUnderTest.cityScreen(hashMap);
        infoMenuControllerUnderTest.economicOverview(hashMap);
        hashMap.replace("section","notification");
        infoMenuControllerUnderTest.menuEnter(hashMap);
        hashMap.replace("section","demographic");
        infoMenuControllerUnderTest.menuEnter(hashMap);
        hashMap.replace("section","asdf");
        infoMenuControllerUnderTest.menuEnter(hashMap);
        infoMenuControllerUnderTest.activeUnit(hashMap);
        infoMenuControllerUnderTest.militaryOverview(hashMap);
        Assertions.assertEquals(infoMenuControllerUnderTest.militaryOverview(hashMap),"invalid command!");
        infoMenuControllerUnderTest.currentMenu(hashMap);
        infoMenuControllerUnderTest.menuExit(hashMap);
    }


}
