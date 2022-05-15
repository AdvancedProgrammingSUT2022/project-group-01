package controller;
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
import model.unit.civilian.Worker;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GameMenuControllerTestAmnam {
    GameMenuController gameMenuController;
    UnitController unitController;
    Game game;
    @BeforeEach
    void init(){
        game = mock(Game.class);
        GameController gameController = mock(GameController.class);
        CityController cityController = mock(CityController.class);
        unitController = mock(UnitController.class);
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
        when(game.getMap()).thenReturn(map);
        when(game.getSelectedObject()).thenReturn(obj);
        when(obj.getOwnerCivilization()).thenReturn(civ1);

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
        String ans = "Can't reach there\n";
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
    void unitActionListTestOne(){
        when(game.getSelectedObject()).thenReturn(null);
        String ans = "worker is not selected !";
        String result = gameMenuController.unitActionList(null);
        Assertions.assertEquals(ans, result);
    }

    @Test
    void unitActionListTestTwo(){
        Worker worker = mock(Worker.class);
        when(game.getSelectedObject()).thenReturn(worker);
        Civilization civ1 = mock(Civilization.class);
        Civilization civ2 = mock(Civilization.class);
        when(worker.getOwnerCivilization()).thenReturn(civ1);
        Player player = mock(Player.class);
        when(player.getCivilization()).thenReturn(civ2);
        when(game.getCurrentPlayer()).thenReturn(player);
        String ans = "you don't own this unit";
        String result = gameMenuController.unitActionList(null);
        Assertions.assertEquals(ans, result);
    }
    @Test
    void unitActionListTestThree(){//todo implement here
        Worker worker = mock(Worker.class);
        when(game.getSelectedObject()).thenReturn(worker);
        Civilization civ1 = mock(Civilization.class);
        when(worker.getOwnerCivilization()).thenReturn(civ1);
        Player player = mock(Player.class);
        when(player.getCivilization()).thenReturn(civ1);
        when(game.getCurrentPlayer()).thenReturn(player);
        when(worker.canRemoveFeature()).thenReturn(true);

        String ans = "you don't own this unit";
        String result = gameMenuController.unitActionList(null);
        Assertions.assertEquals(ans, result);
    }

    @Test
    void unitSleepTestOne(){
        when(game.getSelectedObject()).thenReturn(null);
        String ans = "you haven't select any unit";
        String result = gameMenuController.unitSleep(null);
        Assertions.assertEquals(ans,result);
    }

    @Test
    void unitSleepTestTwo(){
        Civilization c1 = mock(Civilization.class);
        Player p1 = mock(Player.class);
        Player p2 = mock(Player.class);
        Unit unit = mock(Unit.class);
        when(unit.getOwnerCivilization()).thenReturn(c1);
        when(c1.getPlayer()).thenReturn(p1);
        when(game.getCurrentPlayer()).thenReturn(p2);
        when(game.getSelectedObject()).thenReturn(unit);
        String ans = "you don't own this unit";
        String result = gameMenuController.unitSleep(null);
        Assertions.assertEquals(ans,result);
    }

    @Test
    void unitSleepTestThree(){
        Civilization c1 = mock(Civilization.class);
        Player p1 = mock(Player.class);
        Unit unit = mock(Unit.class);
        when(unit.getOwnerCivilization()).thenReturn(c1);
        when(c1.getPlayer()).thenReturn(p1);
        when(game.getCurrentPlayer()).thenReturn(p1);
        when(game.getSelectedObject()).thenReturn(unit);
        String ans = "done";
        when(unitController.sleep(unit)).thenReturn("done");
        String result = gameMenuController.unitSleep(null);
        Assertions.assertEquals(ans,result);
    }

    @Test
    void damageUnitTestOne(){
        HashMap<String, String> args = new HashMap<>();
        args.put("amount", "string");
        Assertions.assertEquals("invalid amount", gameMenuController.damageUnit(args));
    }

    @Test
    void damageUnitTestTwo(){
        HashMap<String, String> args = new HashMap<>();
        args.put("amount", "5");
        when(game.getSelectedObject()).thenReturn(null);
        String ans = "you haven't select any unit";
        Assertions.assertEquals(ans, gameMenuController.damageUnit(args));
    }

    @Test
    void damageUnitTestThree(){
        HashMap<String, String> args = new HashMap<>();
        args.put("amount", "5");
        Unit unit = mock(Unit.class);
        when(game.getSelectedObject()).thenReturn(unit);
        when(unitController.damage(unit,5)).thenReturn("done");
        Assertions.assertEquals("done", gameMenuController.damageUnit(args));
    }

    @Test
    void unitAlertTestOne(){
        when(game.getSelectedObject()).thenReturn(null);
        String ans = "you haven't select any unit";
        String result = gameMenuController.unitAlert(null);
        Assertions.assertEquals(ans,result);
    }

    @Test
    void unitAlertTestTwo(){
        Civilization c1 = mock(Civilization.class);
        Player p1 = mock(Player.class);
        Player p2 = mock(Player.class);
        Unit unit = mock(Unit.class);
        when(unit.getOwnerCivilization()).thenReturn(c1);
        when(c1.getPlayer()).thenReturn(p1);
        when(game.getCurrentPlayer()).thenReturn(p2);
        when(game.getSelectedObject()).thenReturn(unit);
        String ans = "you don't own this unit";
        String result = gameMenuController.unitAlert(null);
        Assertions.assertEquals(ans,result);
    }

    @Test
    void unitAlertTestThree(){
        Civilization c1 = mock(Civilization.class);
        Player p1 = mock(Player.class);
        Unit unit = mock(Unit.class);
        when(unit.getOwnerCivilization()).thenReturn(c1);
        when(c1.getPlayer()).thenReturn(p1);
        when(game.getSelectedObject()).thenReturn(unit);
        when(game.getCurrentPlayer()).thenReturn(p1);
        when(unitController.alert(unit)).thenReturn("done");
        String ans = "done";
        String result = gameMenuController.unitAlert(null);
        Assertions.assertEquals(ans,result);
    }

    @Test
    void unitFortifyTestOne(){
        when(game.getSelectedObject()).thenReturn(null);
        String ans = "you haven't select any unit";
        String result = gameMenuController.unitFortify(null);
        Assertions.assertEquals(ans,result);
    }

    @Test
    void unitFortifyTestTwo(){
        Civilization c1 = mock(Civilization.class);
        Player p1 = mock(Player.class);
        Player p2 = mock(Player.class);
        Unit unit = mock(Unit.class);
        when(unit.getOwnerCivilization()).thenReturn(c1);
        when(c1.getPlayer()).thenReturn(p1);
        when(game.getCurrentPlayer()).thenReturn(p2);
        when(game.getSelectedObject()).thenReturn(unit);
        String ans = "you don't own this unit";
        String result = gameMenuController.unitFortify(null);
        Assertions.assertEquals(ans,result);
    }

    @Test
    void unitFortifyTestThree(){
        Civilization c1 = mock(Civilization.class);
        Player p1 = mock(Player.class);
        Unit unit = mock(Unit.class);
        when(unit.getOwnerCivilization()).thenReturn(c1);
        when(c1.getPlayer()).thenReturn(p1);
        when(game.getSelectedObject()).thenReturn(unit);
        when(game.getCurrentPlayer()).thenReturn(p1);
        when(unitController.fortify(unit)).thenReturn("done");
        String ans = "done";
        String result = gameMenuController.unitFortify(null);
        Assertions.assertEquals(ans,result);
    }

    @Test
    void unitFortifyUntilHealTestOne(){
        when(game.getSelectedObject()).thenReturn(null);
        String ans = "you haven't select any unit";
        String result = gameMenuController.unitFortifyUntilHeal(null);
        Assertions.assertEquals(ans,result);
    }

    @Test
    void unitFortifyUntilHealTestTwo(){
        Civilization c1 = mock(Civilization.class);
        Player p1 = mock(Player.class);
        Player p2 = mock(Player.class);
        Unit unit = mock(Unit.class);
        when(unit.getOwnerCivilization()).thenReturn(c1);
        when(c1.getPlayer()).thenReturn(p1);
        when(game.getCurrentPlayer()).thenReturn(p2);
        when(game.getSelectedObject()).thenReturn(unit);
        String ans = "you don't own this unit";
        String result = gameMenuController.unitFortifyUntilHeal(null);
        Assertions.assertEquals(ans,result);
    }

    @Test
    void unitFortifyUntilHealTestThree(){
        Civilization c1 = mock(Civilization.class);
        Player p1 = mock(Player.class);
        Unit unit = mock(Unit.class);
        when(unit.getOwnerCivilization()).thenReturn(c1);
        when(c1.getPlayer()).thenReturn(p1);
        when(game.getSelectedObject()).thenReturn(unit);
        when(game.getCurrentPlayer()).thenReturn(p1);
        when(unitController.fortifyUntilHeal(unit)).thenReturn("done");
        String ans = "done";
        String result = gameMenuController.unitFortifyUntilHeal(null);
        Assertions.assertEquals(ans,result);
    }

    @Test
    void unitSetupTestOne(){
        when(game.getSelectedObject()).thenReturn(null);

    }

}
