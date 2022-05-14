package model.unit.civilian;

import model.Player;
import model.User;
import model.civilization.Civilization;
import model.civilization.Civilizations;
import model.civilization.city.City;
import model.improvement.ImprovementType;
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
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class WorkerTest {

    @Mock
    private Tile mockTile;
    @Mock
    private Civilization mockCivilization;

    private Worker workerUnderTest;

    @BeforeEach
    void setUp() {
        workerUnderTest = new Worker(UnitType.ARCHER, mockTile, mockCivilization);
    }


    @Test
    void testImproveTile() {
        // Setup
        // Run the test
        workerUnderTest.improveTile(ImprovementType.CAMP);

        // Verify the results
    }

    @Test
    void testBuildRoad() {
        // Setup
        // Run the test
        workerUnderTest.buildRoad();

        // Verify the results
    }

    @Test
    void testBuildRail() {
        // Setup
        // Run the test
        workerUnderTest.buildRail();

        // Verify the results
    }

    @Test
    void testPauseImprovement() {
        // Setup
        // Run the test
        workerUnderTest.pauseImprovement();

        // Verify the results
    }

    @Test
    void testRepairImprovement() {
        // Setup
        // Run the test
        workerUnderTest.repairImprovement();

        // Verify the results
    }

    @Test
    void testRemoveImprovement() {
        // Setup
        // Run the test
        workerUnderTest.removeImprovement();

        // Verify the results
    }



    @Test
    void testRemoveRoad() {
        // Setup
        // Run the test
        workerUnderTest.removeRoute();

        // Verify the results
    }



}
