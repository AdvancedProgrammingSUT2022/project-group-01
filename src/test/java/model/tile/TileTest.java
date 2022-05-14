package model.tile;

import model.ProgressState;
import model.civilization.Civilization;
import model.civilization.Civilizations;
import model.civilization.Person;
import model.civilization.city.City;
import model.improvement.ImprovementType;
import model.resource.ResourceType;
import model.unit.Unit;
import model.unit.UnitType;
import model.unit.action.UnitActions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import java.util.List;
import java.util.Vector;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class TileTest {
    @Mock
    Tile tileUnderTest;

    @Test
    void testAddPerson() throws Exception {
        // Setup
        final Person person = new Person();

        // Run the test
        tileUnderTest.addPerson(person);

        // Verify the results
    }

    @Test
    void testBuildImprovement() throws Exception {
        // Setup
        // Run the test
        tileUnderTest.buildImprovement(ImprovementType.CAMP);

        // Verify the results
    }

    @Test
    void testBuildRailRoad() throws Exception {
        // Setup
        // Run the test
        tileUnderTest.buildRailRoad();

        // Verify the results
    }

    @Test
    void testBuildRoad() throws Exception {
        // Setup
        // Run the test
        tileUnderTest.buildRoad();

        // Verify the results
    }

    @Test
    void testBuildRoute() throws Exception {
        // Setup
        // Run the test
        tileUnderTest.buildRoute();

        // Verify the results
    }

    @Test
    void testDestroy() throws Exception {
        // Setup
        // Run the test
        tileUnderTest.destroy();

        // Verify the results
    }

    @Test
    void testDoesHaveRailRoad() throws Exception {
        assertFalse(tileUnderTest.doesHaveRailRoad());
    }

    @Test
    void testDoesHaveRoad() throws Exception {
        assertFalse(tileUnderTest.doesHaveRoad());
    }

    @Test
    void testGetAdjacentTiles() throws Exception {
        // Setup
        // Run the test
        final Vector<Tile> result = tileUnderTest.getAdjacentTiles();

        // Verify the results
    }

    @Test
    void testGetBoarder() throws Exception {
        // Setup
        // Run the test
        final Boarder result = tileUnderTest.getBoarder(0);

        // Verify the results
    }


    @Test
    void testGetBoarderInfo() throws Exception {
        // Setup
        // Run the test
        final Boarder result = tileUnderTest.getBoarderInfo(0);

        // Verify the results
    }

    @Test
    void testGetBuiltImprovement() throws Exception {
        // Setup
        // Run the test
        final ImprovementType result = tileUnderTest.getBuiltImprovement();

        // Verify the results
        assertNotEquals(ImprovementType.CAMP, result);
    }

    @Test
    void testGetCombatModifierRate() throws Exception {
        assertEquals(0, tileUnderTest.getCombatModifierRate());
    }

    @Test
    void testGetFoodYield() throws Exception {
        assertEquals(0, tileUnderTest.getFoodYield());
    }

    @Test
    void testGetGoldYield() throws Exception {
        assertEquals(0, tileUnderTest.getGoldYield());
    }

    @Test
    void testGetImprovementTurnsLeft() throws Exception {
        // Setup
        // Run the test
        final int result = tileUnderTest.getImprovementTurnsLeft();

        // Verify the results
        assertEquals(0, result);
    }

    @Test
    void testGetMaintenance() throws Exception {
        assertEquals(0, tileUnderTest.getMaintenance());
    }

    @Test
    void testGetMovementCost() throws Exception {
        assertEquals(0, tileUnderTest.getMovementCost());
    }

    @Test
    void testGetProductionYield() throws Exception {
        assertEquals(0, tileUnderTest.getProductionYield());
    }

    @Test
    void testOrderWorkerAction() throws Exception {
        // Setup
        // Run the test
        tileUnderTest.orderWorkerAction(UnitActions.BUILD_ROAD);

        // Verify the results
    }

    @Test
    void testPillageImprovement() throws Exception {
        // Setup
        // Run the test
        tileUnderTest.pillageImprovement();

        // Verify the results
    }

    @Test
    void testRemoveBuiltImprovements() throws Exception {
        // Setup
        // Run the test
        tileUnderTest.removeBuiltImprovements(ImprovementType.CAMP);

        // Verify the results
    }

    @Test
    void testRemoveFeature() throws Exception {
        // Setup
        // Run the test
        tileUnderTest.removeFeature();

        // Verify the results
    }

    @Test
    void testRemoveImprovement() throws Exception {
        // Setup
        // Run the test
        tileUnderTest.removeImprovement();

        // Verify the results
    }

    @Test
    void testRemovePerson() throws Exception {
        // Setup
        final Person person = new Person();

        // Run the test
        tileUnderTest.removePerson(person);

        // Verify the results
    }

    @Test
    void testRemoveResource() throws Exception {
        // Setup
        // Run the test
        tileUnderTest.removeResource();

        // Verify the results
    }

    @Test
    void testRemoveRoads() throws Exception {
        // Setup
        // Run the test
        tileUnderTest.removeRoads();

        // Verify the results
    }

    @Test
    void testRepairImprovement() throws Exception {
        // Setup
        // Run the test
        tileUnderTest.repairImprovement();

        // Verify the results
    }

    @Test
    void testStopImprovementProgress() throws Exception {
        // Setup
        // Run the test
        tileUnderTest.stopImprovementProgress();

        // Verify the results
    }
}
