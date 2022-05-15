package model.improvement;

import model.ProgressState;
import model.civilization.Civilization;
import model.civilization.Civilizations;
import model.civilization.Currency;
import model.civilization.city.City;
import model.resource.ResourceType;
import model.tile.Terrain;
import model.tile.TerrainFeature;
import model.tile.Tile;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ImprovementInventoryTest {

    @Mock
    private Tile mockTile;

    private ImprovementInventory improvementInventoryUnderTest;
    @BeforeEach
    void beforeMethod(){
        improvementInventoryUnderTest = new ImprovementInventory(mockTile);
        improvementInventoryUnderTest.reset(ImprovementType.MANUFACTORY);
    }
    @Test
    public void turnsLeft(){
        improvementInventoryUnderTest.getTurnsLeft();
    }
    @Test
    public void setStateTest(){
        improvementInventoryUnderTest.setState(ProgressState.DAMAGED);
        improvementInventoryUnderTest.setTurnsLeft(10);
        Assertions.assertEquals(improvementInventoryUnderTest.getState(),ProgressState.DAMAGED);
    }

    @Test
    public void setImprovementTest(){
        improvementInventoryUnderTest.setImprovement(ImprovementType.FARM);
        Assertions.assertEquals(improvementInventoryUnderTest.getImprovement(),ImprovementType.FARM);
    }
    @Test
    public void repairImprovement(){
        improvementInventoryUnderTest.setImprovement(ImprovementType.FARM);
        improvementInventoryUnderTest.setState(ProgressState.DAMAGED);
        improvementInventoryUnderTest.setTurnsLeft(3);
        improvementInventoryUnderTest.repair();
    }
}
