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

@ExtendWith(MockitoExtension.class)
class RangedUnitTest {

    @Mock
    private Tile mockTile;
    @Mock
    private Civilization mockCivilization;

    private RangedUnit rangedUnitUnderTest;

    @BeforeEach
    void setUp() {
        rangedUnitUnderTest = new RangedUnit(UnitType.ARCHER, mockTile, mockCivilization);
    }

    @Test
    void testRangedAttack() {
        RangedUnit rangedUnit = new RangedUnit(UnitType.CANON,mockTile,mockCivilization);
        rangedUnit.rangedAttack(mockTile);
    }

    @Test
    void testSpawnRanged() {
        RangedUnit.spawnRanged(UnitType.CROSSBOW_MAN,mockTile,mockCivilization);
        RangedUnit.spawnRanged(UnitType.CANON,mockTile,mockCivilization);
    }
}
