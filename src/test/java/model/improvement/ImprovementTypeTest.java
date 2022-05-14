package model.improvement;

import controller.GameInitializer;
import controller.MapController;
import controller.TileController;
import model.Game;
import model.ProgressState;
import model.TurnBasedLogic;
import model.User;
import model.civilization.Civilization;
import model.civilization.Civilizations;
import model.civilization.city.City;
import model.resource.ResourceType;
import model.technology.TechTree;
import model.technology.TechnologyType;
import model.tile.Terrain;
import model.tile.TerrainFeature;
import model.tile.Tile;
import model.unit.UnitType;
import model.unit.armed.Armed;
import model.unit.civilian.Civilian;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.beans.FeatureDescriptor;
import java.util.List;
import java.util.Vector;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

class ImprovementTypeTest {


    @Mock
    Tile tile;


    @Test
    void testInitializeVectors() {
        // Run the test

        ImprovementType.CAMP.initializeVectors();
        ImprovementType.FARM.initializeVectors();
        ImprovementType.LUMBER_MILL.initializeVectors();
        ImprovementType.MINE.initializeVectors();
        ImprovementType.PASTURE.initializeVectors();
        ImprovementType.PLANTATION.initializeVectors();
        ImprovementType.QUARRY.initializeVectors();
        ImprovementType.TRADING_POST.initializeVectors();
        ImprovementType.MANUFACTORY.initializeVectors();

        // Verify the results
    }
    @Mock
    TechTree techTree;
    @Mock
    Civilization civilization;
    @Test
    public void isEligibleTest(){
        User user1 = new User("a","b","c");
        User user2 = new User("aa","bb","cc");
        TileController.initializeEnums();
        Vector<User> vec = new Vector<>(List.of(user1,user2));
        GameInitializer gi = new GameInitializer();
        Game game = gi.startGame(vec,17);
        Game spyGame = spy(game);
        MapController mc = new MapController(spyGame);

        Tile tile = spyGame.getCurrentPlayer().getMapCenterTile();
        tile.setCivilization(game.getCurrentPlayer().getCivilization());
        Armed armed = new Armed(UnitType.WARRIOR,tile,game.getCurrentPlayer().getCivilization());
        Civilian civilian = new Civilian(UnitType.WORKER,tile,game.getCurrentPlayer().getCivilization());
        tile.setArmedUnit(armed);
        tile.setCivilianUnit(civilian);
        tile.setAvailableResource(ResourceType.IRON);
        for(ImprovementType f : ImprovementType.values()){
            f.isEligibleToBuild(game.getCurrentPlayer().getCivilization(),tile);
            f.improvementSpecialAction(tile);
        }
    }
}
