package model;

import controller.GameInitializer;
import controller.MapController;
import controller.TileController;
import model.civilization.Civilization;
import model.civilization.Civilizations;
import model.civilization.city.City;
import model.improvement.ImprovementType;
import model.resource.ResourceType;
import model.tile.Tile;
import model.unit.Unit;
import model.unit.UnitType;
import model.unit.armed.Armed;
import model.unit.civilian.Civilian;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.List;
import java.util.Vector;

import static org.mockito.Mockito.when;

class NotificationTest {

    private Notification notificationUnderTest;
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
        tile.buildImprovement(ImprovementType.LUMBER_MILL);
        tile.buildImprovement(ImprovementType.LUMBER_MILL);
        tile.buildImprovement(ImprovementType.LUMBER_MILL);
        tile.buildImprovement(ImprovementType.LUMBER_MILL);
        tile.buildImprovement(ImprovementType.LUMBER_MILL);
        tile.buildImprovement(ImprovementType.LUMBER_MILL);
        Assertions.assertEquals(tile.getImprovementInventoryState(), ProgressState.COMPLETE);
        mc.updateSavedMap(game.getCurrentPlayer(),new Vector<>(List.of(tile)),game.getMap());
        mc.moveCenterTile(1,"right");
        mc.getConsoleMap(tile);
        TileController.initializeEnums();
        City city = new City(game.getCurrentPlayer().getCivilization().getCivilization().getCityNames()[1],game.getCurrentPlayer().getCivilization(),tile);
        game.getCurrentPlayer().getCivilization().addNewCity(city);
        city.updateBeaker();
        Unit unit = new Armed(UnitType.WARRIOR,city.getCenter(),game.getCurrentPlayer().getCivilization());
        city.setGarrisonedUnit(unit);
        unit.moveTo(game.getMap().getTileByNumber(100));
        city.getGarrisonedUnit();
        city.updateDefencePower(2);
        city.setAttackPower(4);
        game.getCurrentPlayer().getMapCenterTile().setOwnerCity(city);
    }
    @BeforeEach
    void setUp() {
        notificationUnderTest = new Notification("text", 0);
    }

    @Test
    void testReadNotification() {
        // Setup
        // Run the test
        notificationUnderTest.readNotification();

        // Verify the results
    }

   @Test
    void testCityProduction(){
        Notification notification = new Notification(game.getCurrentPlayer().getMapCenterTile().getOwnerCity(), Notification.NotificationTexts.PRODUCTION_BUILT);
        notification.setAnnouncementTurn(2);
        for(Notification.NotificationTexts text : Notification.NotificationTexts.values())
            Assertions.assertNotNull(text.getText());

    }
}
