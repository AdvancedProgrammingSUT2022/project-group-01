import controller.GameController;
import controller.GameMenuController;
import controller.civilization.city.CityController;
import controller.unit.UnitController;
import controller.unit.WorkerController;
import model.*;
import model.civilization.Civilization;
import model.map.SavedMap;
import model.tile.Tile;
import model.unit.Unit;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GameMenuControllerTestAmnam2 {
    GameMenuController gameMenuController;
    Game game;
    @BeforeEach
    void init(){
        game = mock(Game.class);
        GameController gameController = mock(GameController.class);
        CityController cityController = mock(CityController.class);
        UnitController unitController = mock(UnitController.class);
        WorkerController workerController = mock(WorkerController.class);
        gameMenuController = new GameMenuController(game,gameController,cityController,unitController,workerController);
    }

    @Test
    void unitMoveTestOne(){
        String ans = "invalid number";
        HashMap<String, String> arg = new HashMap<>();
        arg.put("position","String");
        Assertions.assertEquals(ans, gameMenuController.unitMove(arg));
    }

    @Test
    void unitMoveTestTwo(){
        String ans = "invalid position";
        HashMap<String, String> arg = new HashMap<>();
        Map map = mock(Map.class);
        when(game.getMap()).thenReturn(map);
        when(map.getTileByNumber(5)).thenReturn(null);
        arg.put("position","5");
        Assertions.assertEquals(ans, gameMenuController.unitMove(arg));
    }

    @Test
    void unitMoveTestThree(){
        String ans = "Selected \"Object\" is not Unit";
        HashMap<String, String> arg = new HashMap<>();
        Map map = mock(Map.class);
        when(game.getMap()).thenReturn(map);
        when(game.getSelectedObject()).thenReturn(map);
        Tile tile = mock(Tile.class);
        when(map.getTileByNumber(5)).thenReturn(tile);
        arg.put("position","5");
        Assertions.assertEquals(ans, gameMenuController.unitMove(arg));
    }

    @Test
    void unitMoveTestFour(){
        Player player1 = mock(Player.class);
        Player player2 = mock(Player.class);
        Civilization civ1 = mock(Civilization.class);
        when(civ1.getPlayer()).thenReturn(player1);
        Civilization civ2 = mock(Civilization.class);
        when(civ2.getPlayer()).thenReturn(player2);
        String ans = "you don't own this unit";
        HashMap<String, String> arg = new HashMap<>();
        Map map = mock(Map.class);
        Unit obj = mock(Unit.class);
        when(game.getSelectedObject()).thenReturn(obj);
        when(obj.getOwnerCivilization()).thenReturn(civ1);
        when(game.getMap()).thenReturn(map);
        Tile tile = mock(Tile.class);
        when(map.getTileByNumber(5)).thenReturn(tile);
        arg.put("position","5");
        Assertions.assertEquals(ans, gameMenuController.unitMove(arg));
    }

    @Test
    void unitMoveTestFive() {
        Player player1 = mock(Player.class);
        Civilization civ1 = mock(Civilization.class);
        when(civ1.getPlayer()).thenReturn(player1);
        String ans = "Can't reach there";
        HashMap<String, String> arg = new HashMap<>();
        Map map = mock(Map.class);
        when(game.getMap()).thenReturn(map);
        Unit obj = mock(Unit.class);
        when(obj.getOwnerCivilization()).thenReturn(civ1);
        when(civ1.getPlayer()).thenReturn(null);
        when(game.getCurrentPlayer()).thenReturn(null);
        when(game.getSelectedObject()).thenReturn(obj);
        Tile tile = mock(Tile.class);
        when(obj.canGoTo(tile)).thenReturn(false);
        when(map.getTileByNumber(5)).thenReturn(tile);
        arg.put("position","5");
        Assertions.assertEquals(ans, gameMenuController.unitMove(arg));
    }

    @Test
    void unitMoveTestSix() {
        Player player1 = mock(Player.class);
        Civilization civ1 = mock(Civilization.class);
        when(civ1.getPlayer()).thenReturn(player1);
        String ans = "selected unit is out of movement point";
        HashMap<String, String> arg = new HashMap<>();
        Map map = mock(Map.class);
        when(game.getMap()).thenReturn(map);
        Unit obj = mock(Unit.class);
        when(obj.getOwnerCivilization()).thenReturn(civ1);
        when(civ1.getPlayer()).thenReturn(null);
        when(game.getCurrentPlayer()).thenReturn(null);
        when(game.getSelectedObject()).thenReturn(obj);
        Tile tile = mock(Tile.class);
        when(obj.canGoTo(tile)).thenReturn(true);
        when(obj.outOfMP()).thenReturn(true);
        when(map.getTileByNumber(5)).thenReturn(tile);
        arg.put("position","5");
        Assertions.assertEquals(ans, gameMenuController.unitMove(arg));
    }

    @Test
    void unitMoveTestSeven() {//todo implement here
        Player player1 = mock(Player.class);
        Civilization civ1 = mock(Civilization.class);
        when(civ1.getPlayer()).thenReturn(player1);
        String ans = "you don't see there";
        HashMap<String, String> arg = new HashMap<>();
        Map map = mock(Map.class);
        when(game.getMap()).thenReturn(map);
        Unit obj = mock(Unit.class);
        when(obj.getOwnerCivilization()).thenReturn(civ1);
        when(game.getCurrentPlayer()).thenReturn(player1);
        SavedMap savedMap = mock(SavedMap.class);
        when(player1.getSavedMap()).thenReturn(savedMap);
        when(game.getSelectedObject()).thenReturn(obj);
        Tile tile = mock(Tile.class);
        when(obj.canGoTo(tile)).thenReturn(true);
        when(obj.outOfMP()).thenReturn(false);
        when(map.getTileByNumber(5)).thenReturn(tile);
        when(savedMap.getVisibilityState(tile)).thenReturn(Tile.VisibilityState.FOG_OF_WAR);
        arg.put("position","5");
        Assertions.assertEquals(ans, gameMenuController.unitMove(arg));
    }

    @Test
    void unitMoveTestEight(){
        Player player1 = mock(Player.class);
        Civilization civ1 = mock(Civilization.class);
        when(civ1.getPlayer()).thenReturn(player1);
        String ans = "Done!";
        HashMap<String, String> arg = new HashMap<>();
        Map map = mock(Map.class);
        when(game.getMap()).thenReturn(map);
        Unit obj = mock(Unit.class);
        when(obj.getOwnerCivilization()).thenReturn(civ1);
        when(game.getCurrentPlayer()).thenReturn(player1);
        SavedMap savedMap = mock(SavedMap.class);
        when(player1.getSavedMap()).thenReturn(savedMap);
        when(game.getSelectedObject()).thenReturn(obj);
        Tile tile = mock(Tile.class);
        when(obj.canGoTo(tile)).thenReturn(true);
        when(obj.outOfMP()).thenReturn(false);
        when(map.getTileByNumber(5)).thenReturn(tile);
        when(savedMap.getVisibilityState(tile)).thenReturn(Tile.VisibilityState.VISIBLE);
        arg.put("position","5");
        Assertions.assertEquals(ans, gameMenuController.unitMove(arg));
    }

    @Test
    void unitActionListTestOne(){
        when(game.getSelectedObject()).thenReturn(null);
        String ans = "worker is not selected !";
        String result = gameMenuController.unitActionList(null);
        Assertions.assertEquals(ans, result);
    }


}
