package model.tile;

import controller.*;
import model.*;

import model.civilization.Person;
import model.improvement.ImprovementType;
import model.resource.ResourceType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Vector;

import static org.mockito.Mockito.spy;

@ExtendWith(MockitoExtension.class)
public class TileTest3 {
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
        tile.setBoarder(boarder,1);
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


   @Test
    public void mapGenerator1(){
        TileController.initializeEnums();
        User user1 = new User("a","b","c");
        User user2 = new User("aa","bb","cc");
        Player p1 = new Player(user1);
        Player p2 = new Player(user2);
        Vector<Player> pvec = new Vector<>(List.of(p1,p2));
        Game game = new Game(pvec, 21);
        MapGenerationController mgc = new MapGenerationController(game);


        mgc.generateMap(21);
        Assertions.assertNotNull(game.getMap().getTile(14,14).getTerrain());
    }



//    @Test
//    public void setPositionTest(){
//        TileController.initializeEnums();
//        MapController mc = new MapController(game);
//        Map map = new Map(4);
//        when(game.getMap()).thenReturn(map);
//        Assertions.assertEquals(mc.setPosition(-2), "invalid position");
//        Assertions.assertEquals(mc.setPosition(1000),"invalid position");
//    }


    @Test
    public void moveCenterTileTest(){
        User user1 = new User("a","b","c");
        User user2 = new User("aa","bb","cc");
        TileController.initializeEnums();
        Vector<User> vec = new Vector<>(List.of(user1,user2));
        GameInitializer gi = new GameInitializer();
        Game game = gi.startGame(vec,17);
        Game spyGame = spy(game);
        MapController mc = new MapController(spyGame);

        Tile tile = spyGame.getCurrentPlayer().getMapCenterTile();
        mc.moveCenterTile(1,"right");

        Assertions.assertSame(spyGame.getCurrentPlayer().getMapCenterTile(),tile.getBoarder(1).getOtherTile(tile));
        Assertions.assertEquals(mc.moveMap("left",3),"changed map position");
    }

    @Mock
    Game game2;
    MapController mc2;
    @Test
    public void showTileInfoTest(){
        Tile tile = new Tile(Terrain.DESERT,TerrainFeature.FOREST,null,ResourceType.GEMS,10,10,4);
        GameController gc = new GameController(game2 , mc2);
        GameController gcSpy = spy(gc);
        Tile tileSpy = spy(tile);
        Mockito.doReturn(Terrain.OCEAN).when(tileSpy).getTerrain();
        Assertions.assertTrue(gc.showTileInfo(tileSpy).contains("Tile Number :"));

    }

@Test
    public void mapGeneration2Test(){
        User user1 = new User("a","b","c");
        User user2 = new User("aa","bb","cc");
        TileController.initializeEnums();
        Vector<User> vec = new Vector<>(List.of(user1,user2));
        GameInitializer gi = new GameInitializer();
        Game game = gi.startGame(vec,17);

    }

    @Test
    public void removeAndDestroyTest(){
        Tile tile = new Tile(Terrain.DESERT,TerrainFeature.FOREST,null,ResourceType.IRON,10,101,0);
        tile.removeResource();
        tile.getCombatModifierRate();
        tile.getMovementCost();
        Assertions.assertTrue(tile.isPassable());
        tile.buildRoad();
        Assertions.assertTrue(tile.doesHaveRoad());
        tile.doesHaveRoad();
        tile.buildRailRoad();
        tile.removeBuiltImprovements(ImprovementType.CAMP);
        Assertions.assertFalse(tile.isDestroyed());
        tile.destroy();
        tile.removeImprovement();
        Assertions.assertNull(tile.getBuiltImprovement());
    }

    @Test
    public void peopleInsideTileTest(){
        Person person = new Person();
        Tile tile = new Tile(Terrain.DESERT,TerrainFeature.FOREST,null,ResourceType.IRON,10,101,0);
        tile.addPerson(person);
        tile.removePerson(person);
        Assertions.assertTrue(tile.getPeopleInside().isEmpty());
    }




}
