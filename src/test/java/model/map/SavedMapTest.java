package model.map;

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
import model.tile.Terrain;
import model.tile.TerrainFeature;
import model.tile.Tile;
import model.unit.Unit;
import model.unit.UnitType;
import model.unit.armed.Armed;
import model.unit.civilian.Civilian;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Vector;

import static org.junit.jupiter.api.Assertions.*;

class SavedMapTest {
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
    }

    @Test
    public void update(){
        game.getCurrentPlayer().getMapCenterTile().getArmedUnit().moveTo(game.getMap().getTileByNumber(50));
        game.nextTurn();
        game.nextTurn();
        mc.updateSavedMap(game.getCurrentPlayer(),game.getCurrentPlayer().getCivilization().visibleTiles(),game.getMap());
        Terrain terrain = game.getCurrentPlayer().getSavedMap().getTerrain(game.getCurrentPlayer().getMapCenterTile());
        Assertions.assertEquals(terrain,game.getCurrentPlayer().getMapCenterTile().getTerrain());
        TerrainFeature feature = game.getCurrentPlayer().getSavedMap().getFeature(game.getCurrentPlayer().getMapCenterTile());
        Assertions.assertEquals(feature,game.getCurrentPlayer().getMapCenterTile().getFeature());
        ResourceType resourceType = game.getCurrentPlayer().getSavedMap().getResourceTypes(game.getCurrentPlayer().getMapCenterTile());
        Assertions.assertEquals(resourceType,game.getCurrentPlayer().getMapCenterTile().getAvailableResource());
        game.getCurrentPlayer().getMapCenterTile().removeResource();
        mc.updateSavedMap(game.getCurrentPlayer(),game.getCurrentPlayer().getCivilization().visibleTiles(),game.getMap());
        Assertions.assertEquals(game.getCurrentPlayer().getSavedMap().getResourceTypes(game.getCurrentPlayer().getMapCenterTile()),game.getCurrentPlayer().getMapCenterTile().getAvailableResource());
    }
}