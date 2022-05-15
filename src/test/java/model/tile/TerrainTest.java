package model.tile;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TerrainTest {

    @Test
    void testInitializeVectors() {
        // Run the test
        Terrain.DESERT.initializeVectors();
        Terrain.GRASSLAND.initializeVectors();
        Terrain.HILLS.initializeVectors();
        Terrain.MOUNTAIN.initializeVectors();
        Terrain.OCEAN.initializeVectors();
        Terrain.PLAINS.initializeVectors();
        Terrain.SNOW.initializeVectors();
        Terrain.TUNDRA.initializeVectors();

        // Verify the results
    }

    @Test
    void testIsRough() {
        assertFalse(Terrain.DESERT.isRough());
        assertFalse(Terrain.GRASSLAND.isRough());
        assertTrue(Terrain.HILLS.isRough());
        assertFalse(Terrain.MOUNTAIN.isRough());
        assertFalse(Terrain.OCEAN.isRough());
        assertFalse(Terrain.PLAINS.isRough());
        assertFalse(Terrain.SNOW.isRough());
        assertFalse(Terrain.TUNDRA.isRough());
    }
    @Test
    void publicFieldsCall(){
        for(Terrain terrain : Terrain.values()){
            Assertions.assertNotNull(terrain.getGold());
            Assertions.assertNotNull(terrain.getCombatModifier());
            Assertions.assertNotNull(terrain.getMovementCost());
            Assertions.assertNotNull(terrain.isPassable());
            Assertions.assertNotNull(terrain.isHighland());
            Assertions.assertNotNull(terrain.isCanContainCity());
            Assertions.assertNotNull(terrain.getFood());
            Assertions.assertNotNull(terrain.getProduction());
            Assertions.assertNotNull(terrain.getPossibleResources());
            Assertions.assertNotNull(terrain.getPossibleFeatures());
        }
    }
}
