package controller;

import model.*;
import model.civilization.city.City;
import model.improvement.ImprovementType;
import model.resource.ResourceType;
import model.tile.Tile;
import model.unit.UnitType;
import model.unit.armed.Armed;
import model.unit.civilian.Civilian;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Vector;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ProgramControllerTest {

    private ProgramController programControllerUnderTest;

    @BeforeEach
    void setUp() {
        programControllerUnderTest = new ProgramController();
    }
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

//    @Test
//    void testLoginRun() {
//        InputStream sysInBackup = System.in; // backup System.in to restore it later
//        ByteArrayInputStream in = new ByteArrayInputStream("menu exit\nmenu exit\nmenu exit".getBytes());
//        System.setIn(in);
//        ProgramController.setCurrentMenu(Menus.LOGIN_MENU);
//        programControllerUnderTest.run();
//        System.setIn(sysInBackup);
//    }
    @Test
    void testProfileRun() {
        InputStream sysInBackup = System.in; // backup System.in to restore it later
        ByteArrayInputStream in = new ByteArrayInputStream("menu exit\nmenu exit\nmenu exit".getBytes());
        System.setIn(in);
        ProgramController.setCurrentMenu(Menus.PROFILE_MENU);
        programControllerUnderTest.run();
        System.setIn(sysInBackup);
    }
//    @Test
//    void testINFORun() {
//        ProgramController.setGame(game);
//        InputStream sysInBackup = System.in; // backup System.in to restore it later
//        ByteArrayInputStream in = new ByteArrayInputStream("menu exit\nmenu exit\nmenu exit\nmenu exit".getBytes());
//        System.setIn(in);
//        ProgramController.setCurrentMenu(Menus.INFO_MENU);
//        programControllerUnderTest.run();
//        System.setIn(sysInBackup);
//    }


    @Test
    void testSetCurrentMenu() {
        // Setup
        // Run the test
        ProgramController.setCurrentMenu(Menus.MAIN_MENU);

        // Verify the results
    }

    @Test
    void testGetLoggedInUser() {
        // Setup
        // Run the test
        final User result = ProgramController.getLoggedInUser();

        // Verify the results
    }

    @Test
    void testSetLoggedInUser() {
        // Setup
        final User loggedInUser = new User("username", "password", "nickname");

        // Run the test
        ProgramController.setLoggedInUser(loggedInUser);

        // Verify the results
    }

    @Test
    void testGetDatabase() {
        // Setup
        // Run the test
        final Database result = ProgramController.getDatabase();

        // Verify the results
    }

    @Test
    void testGetGame() {
        // Setup
        // Run the test
        final Game result = ProgramController.getGame();

        // Verify the results
    }

    @Test
    void testSetGame() {
        // Setup
        final Game game = new Game(new Vector(List.of(new Player(new User("username", "password", "nickname")))), 0);

        // Run the test
        ProgramController.setGame(game);

        // Verify the results
    }

    @Test
    void getDatabase(){
        ProgramController.getDatabase();
        Assertions.assertNotNull(ProgramController.getDatabase());
    }
}
