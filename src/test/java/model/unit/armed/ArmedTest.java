package model.unit.armed;

import model.Player;
import model.User;
import model.civilization.Civilization;
import model.civilization.Civilizations;
import model.civilization.city.City;
import model.resource.ResourceType;
import model.technology.TechnologyList;
import model.technology.TechnologyType;
import model.tile.Terrain;
import model.tile.TerrainFeature;
import model.tile.Tile;
import model.unit.Unit;
import model.unit.UnitType;
import model.unit.action.Actions;
import model.unit.action.ActionsQueue;
import model.unit.trait.TraitsList;
import model.unit.trait.UnitTraits;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ArmedTest {

    @Mock
    private Tile mockTile;
    @Mock
    private Civilization mockCivilization;

    private Armed armedUnderTest;

    @BeforeEach
    void setUp() {
        armedUnderTest = new Armed(UnitType.ARCHER, mockTile, mockCivilization);
    }


    @Test
    void testAddXP() {
        // Setup
        // Run the test
        armedUnderTest.addXP(0);

        // Verify the results
    }


    @Test
    void testGetAttackPower() {
        assertEquals(0, armedUnderTest.getAttackPower());
    }

    @Test
    void testUpgradeUnit() {
        // Setup
        // Run the test
        armedUnderTest.upgradeUnit();

        // Verify the results
    }

    @Test
    void spawnSpecial(){
        Armed.spawnArmed(UnitType.TREBUCHET,mockTile,mockCivilization);
    }
    @Test
    void attackTileTest(){
        armedUnderTest.attackTile(mockTile);
        armedUnderTest.isInAttackRange(mockTile);
    }

    @Test
    void getDefencePower(){
        when(mockTile.getTerrain()).thenReturn(Terrain.DESERT);
        armedUnderTest.getDefensePower();
    }

}
