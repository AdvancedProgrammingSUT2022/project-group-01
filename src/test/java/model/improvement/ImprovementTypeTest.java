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
import static org.mockito.Mockito.*;

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

    @Test
    void specialFarmActionsTest(){
        ImprovementType.FARM.initializeVectors();
        Civilization civilization = mock(Civilization.class);
        Tile tile = mock(Tile.class);
        when(tile.getTerrain()).thenReturn(Terrain.GRASSLAND);
        TechTree techTree = mock(TechTree.class);
        when(civilization.getResearchTree()).thenReturn(techTree);
        when(techTree.isResearched(TechnologyType.AGRICULTURE)).thenReturn(true);
        when(techTree.isResearched(TechnologyType.MINING)).thenReturn(true);
        when(techTree.isResearched(TechnologyType.BRONZE_WORKING)).thenReturn(true);
        ImprovementType.FARM.isEligibleToBuild(civilization,tile);
        when(tile.getFeature()).thenReturn(TerrainFeature.JUNGLE);
        ImprovementType.FARM.isEligibleToBuild(civilization,tile);
        when(tile.getFeature()).thenReturn(TerrainFeature.MARSH);
        ImprovementType.FARM.isEligibleToBuild(civilization,tile);
        ImprovementType.FARM.improvementSpecialAction(tile);
    }
    @Test
    void specialMineActionsTest(){
        ImprovementType.MINE.initializeVectors();
        Civilization civilization = mock(Civilization.class);
        Tile tile = mock(Tile.class);
        when(tile.getTerrain()).thenReturn(Terrain.GRASSLAND);
        TechTree techTree = mock(TechTree.class);
        when(civilization.getResearchTree()).thenReturn(techTree);
        when(techTree.isResearched(TechnologyType.AGRICULTURE)).thenReturn(true);
        when(techTree.isResearched(TechnologyType.MINING)).thenReturn(true);
        when(techTree.isResearched(TechnologyType.BRONZE_WORKING)).thenReturn(true);
        ImprovementType.MINE.isEligibleToBuild(civilization,tile);
        ImprovementType.MINE.getProductionTime(tile);
        when(tile.getFeature()).thenReturn(TerrainFeature.JUNGLE);
        ImprovementType.MINE.isEligibleToBuild(civilization,tile);
        when(tile.getFeature()).thenReturn(TerrainFeature.MARSH);
        ImprovementType.MINE.isEligibleToBuild(civilization,tile);
        ImprovementType.MINE.improvementSpecialAction(tile);
    }
    @Test
    void defaultEligibilityTest(){
        ImprovementType.MANUFACTORY.initializeVectors();
        Civilization civilization = mock(Civilization.class);
        Civilization civilization1 = mock(Civilization.class);
        Tile tile = mock(Tile.class);
        when(tile.getTerrain()).thenReturn(Terrain.GRASSLAND);
        TechTree techTree = mock(TechTree.class);
        when(civilization.getResearchTree()).thenReturn(techTree);
        ImprovementType.MANUFACTORY.isEligibleToBuild(civilization1,tile);
    }
}
