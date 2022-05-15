package controller;

import model.*;
import model.civilization.Civilization;
import model.civilization.Civilizations;
import model.civilization.city.City;
import model.improvement.ImprovementType;
import model.resource.ResourceType;
import model.tile.Terrain;
import model.tile.TerrainFeature;
import model.tile.Tile;
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

import java.util.List;
import java.util.Vector;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MapControllerTest {

    @Mock
    private Game mockGame;

    private MapController mapControllerUnderTest;

    @BeforeEach
    void setUp() {
        mapControllerUnderTest = new MapController(mockGame);
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
    @Test
    void testMoveCenterTile() {
        // Setup
        // Configure Game.getCurrentPlayer(...).
        final Player player = new Player(new User("username", "password", "nickname"));
        when(mockGame.getCurrentPlayer()).thenReturn(player);

        // Run the test
        mapControllerUnderTest.moveCenterTile(0, "direction");

        // Verify the results
    }
    @Test
    public void moveMapCenter(){
        MapController mapController = new MapController(game);
        mapController.moveCenterTile(1, "down");
        mapController.moveCenterTile(1, "up");
    }

    @Test
    public void discoveredMap(){
        MapController mapController = new MapController(game);
        GameController gameController = new GameController(game,mapController);
        gameController.cheatRemoveFogOfWar(game.getMap().getTileByNumber(50));
        mapController.updateCurrentPlayersMap();
        game.nextTurn();
        game.nextTurn();
        mapController.updateCurrentPlayersMap();
        game.getCurrentPlayer().setMapCenterTile(game.getMap().getTileByNumber(50));
        mapController.getConsoleMap(game.getCurrentPlayer().getMapCenterTile());
    }
}
