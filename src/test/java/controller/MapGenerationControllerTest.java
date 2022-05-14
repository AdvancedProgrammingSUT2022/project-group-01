package controller;

import model.Game;
import model.Map;
import model.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Vector;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MapGenerationControllerTest {

    @Mock
    private Game mockGame;
    private static Game game;
    private static MapController mc;
    private MapGenerationController mapGenerationControllerUnderTest;

    @BeforeAll
    public static void beforechert() {
        User user1 = new User("a", "b", "c");
        User user2 = new User("aa", "bb", "cc");
        TileController.initializeEnums();
        Vector<User> vec = new Vector<>(List.of(user1, user2));
        GameInitializer gi = new GameInitializer();
        game = gi.startGame(vec, 51);
        mc = new MapController(game);
    }

    @Test
    public void mapTest1(){
        MapGenerationController mapGenerationController = new MapGenerationController(game);
        mapGenerationController.setElevationSeed(10);
        mapGenerationController.setHumiditySeed(100);
        mapGenerationController.generateMap(51);
        mapGenerationController.setElevationSeed(6);
        mapGenerationController.setHumiditySeed(1456);
        mapGenerationController.generateMap(51);
    }
}
