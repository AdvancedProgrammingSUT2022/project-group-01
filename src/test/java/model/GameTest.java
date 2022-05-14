package model;

import controller.GameInitializer;
import controller.MapController;
import controller.TileController;
import model.civilization.Civilization;
import model.civilization.Civilizations;
import model.civilization.Currency;
import model.civilization.Trade;
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
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Vector;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GameTest {
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
        mc.moveCenterTile(1,"right");
        mc.getConsoleMap(tile);
        TileController.initializeEnums();
        City city = new City(game.getCurrentPlayer().getCivilization().getCivilization().getCityNames()[1],game.getCurrentPlayer().getCivilization(),tile);
        game.getCurrentPlayer().getCivilization().addNewCity(city);
        tile.setOwnerCity(city);
        city.updateBeaker();
        Unit unit = new Armed(UnitType.WARRIOR,city.getCenterTile(),game.getCurrentPlayer().getCivilization());
        city.setGarrisonedUnit(unit);
        city.getGarrisonedUnit();
        city.updateDefencePower(2);
        city.setAttackPower(4);
        game.getCurrentPlayer().getMapCenterTile().setOwnerCity(city);
    }

    @Test
    public void nextTurn(){
        game.nextTurn();
        game.nextTurn();
    }

    @Test
    public void setCurrentPlayer(){
        Player player = game.getCurrentPlayer();
        game.nextTurn();
        game.setCurrentPlayer(player);
    }

    @Test
    public void selectObject(){
        Unit unit = game.getCurrentPlayer().getMapCenterTile().getArmedUnit();
        game.setSelectedObject(unit);
        game.getSelectedObject();
        Assertions.assertEquals(game.getSelectedObject(), unit);
    }

    @Test
    public void civilizationTrade(){
        game.getTradeForCivilization(game.getCurrentPlayer().getCivilization());
        Assertions.assertNull(game.getTradeForCivilization(game.getCurrentPlayer().getCivilization()));
    }

    @Test
    public void getters(){
        game.getMap();
        game.getCurrentPlayer();
        game.getTurn();
        game.getPlayers();
        game.getInformationPanel();
        game.getTrades();
    }

    @Test
    public void increaseTurnTest(){
        int a = game.getTurn();
        game.increaseTurn(3);
        int b = game.getTurn();
        Assertions.assertEquals(a + 3, b);
    }
}
