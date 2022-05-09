package model.tile;

import controller.GameController;
import controller.GameInitializer;
import controller.MapController;
import controller.TileController;
import model.Game;
import model.Player;
import model.User;
import model.civilization.Civilization;
import model.civilization.Civilizations;
import model.civilization.city.City;
import model.resource.ResourceType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Vector;


public class TileTest {
    @Test
    public void testFeature(){
        Tile tile = new Tile(Terrain.PLAINS, TerrainFeature.FOREST,null, ResourceType.IRON, 10,10,10);
        Assertions.assertEquals(tile.getFeature(), TerrainFeature.FOREST);
    }
    @Test
    public void repairTest(){
        Tile tile = new Tile(Terrain.PLAINS,TerrainFeature.FOREST,null,ResourceType.IRON,10,10,10);
        tile.buildRoad();
        Assertions.assertTrue(tile.getHasRoute());
    }
    @Test
    public void boarderTest(){
        Tile tile = new Tile(Terrain.DESERT,TerrainFeature.JUNGLE, null, ResourceType.GEMS,15,50,50);
        Boarder boarder = new Boarder(tile,null,true,18);
        boarder.setRiver();
        Assertions.assertTrue(tile.hasRiverNearby());
    }
    @Test
    public void mapShowTest(){
        User user1 = new User("a","b","c");
        User user2 = new User("aa","bb","cc");
        TileController.initializeEnums();
        Vector<User> vec = new Vector<>(List.of(user1,user2));
        GameInitializer gi = new GameInitializer();
        Game game = gi.startGame(vec,17);
        MapController mc = new MapController(game);
        String s = mc.getConsoleMap(game.getCurrentPlayer().getMapCenterTile()).substring(0,2);
        Assertions.assertTrue(s.startsWith(" "));
        Assertions.assertEquals(17, game.getMap().getMapSize());
    }
}
