package model.information;

import controller.GameInitializer;
import controller.MapController;
import controller.TileController;
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
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Vector;

import static org.mockito.Mockito.spy;

class NotificationBuilderTest {

    @Test
    public void buildNotificationTest(){
        User user1 = new User("a","b","c");
        User user2 = new User("aa","bb","cc");
        TileController.initializeEnums();
        Vector<User> vec = new Vector<>(List.of(user1,user2));
        GameInitializer gi = new GameInitializer();
        Game game = gi.startGame(vec,17);
        MapController mc = new MapController(game);

        Tile tile = game.getCurrentPlayer().getMapCenterTile();
        tile.setCivilization(game.getCurrentPlayer().getCivilization());
        Armed armed = new Armed(UnitType.WARRIOR,tile,game.getCurrentPlayer().getCivilization());
        Civilian civilian = new Civilian(UnitType.WORKER,tile,game.getCurrentPlayer().getCivilization());
        tile.setArmedUnit(armed);
        tile.setCivilianUnit(civilian);
        Tile tile2 = tile.getBoarder(2).getOtherTile(tile);
        tile2.setCivilization(game.getPlayers().get(1).getCivilization());
        Armed armed2 = new Armed(UnitType.TANK,tile2,tile2.getCivilization());
        tile2.setArmedUnit(armed2);
        NotificationBuilder.buildNotifications(game.getCurrentPlayer().getCivilization());
        game.increaseTurn(666);
        System.out.println(game.getTurn());
        NotificationBuilder.buildNotifications(game.getCurrentPlayer().getCivilization());
    }
}
