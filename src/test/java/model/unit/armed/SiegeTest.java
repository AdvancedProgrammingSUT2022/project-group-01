package model.unit.armed;

import controller.ProgramController;
import model.Game;
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
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SiegeTest {

    @Mock
    private Tile mockTile;
    @Mock
    private Civilization mockCivilization;

    private Siege siegeUnderTest;

    @BeforeEach
    void setUp() {
        siegeUnderTest = new Siege(UnitType.ARCHER, mockTile, mockCivilization);
    }

    @Test
    void testSetup() {
        siegeUnderTest.setup();
    }


    @Test
    void testCompleteSetup() {
        siegeUnderTest.completeSetup();
    }

}
