package model;

import controller.GameInitializer;
import controller.MapController;
import controller.MapGenerationController;
import controller.TileController;
import model.tile.Tile;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Vector;

class MapTest {



    @Test
    void testGetTiles() {
        Game game;
        MapController mc;
        User user1 = new User("a","b","c");
        User user2 = new User("aa","bb","cc");
        TileController.initializeEnums();
        Vector<User> vec = new Vector<>(List.of(user1,user2));
        GameInitializer gi = new GameInitializer();
        game = gi.startGame(vec,17);
        mc = new MapController(game);
        game.getMap().getMap();
        game.getMap().setReachableTiles(new Vector<>(List.of(game.getCurrentPlayer().getMapCenterTile())));

    }

    @Test
    void testGetTileByNumber() {
        Game game;
        MapController mc;
        User user1 = new User("a","b","c");
        User user2 = new User("aa","bb","cc");
        TileController.initializeEnums();
        Vector<User> vec = new Vector<>(List.of(user1,user2));
        GameInitializer gi = new GameInitializer();
        game = gi.startGame(vec,17);
        mc = new MapController(game);
        Tile tile1 = game.getMap().getTileByNumber(30);
        Tile tile2 = game.getMap().getTileByNumber(9000);
        Assertions.assertNotNull(tile1);
        Assertions.assertNull(tile2);
    }
}
