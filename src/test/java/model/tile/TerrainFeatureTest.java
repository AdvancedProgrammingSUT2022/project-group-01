package model.tile;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TerrainFeatureTest {

    @Test
    void testInitializeVectors() {
        TerrainFeature.FLOOD_PLAINS.initializeVectors();
        TerrainFeature.FOREST.initializeVectors();
        TerrainFeature.ICE.initializeVectors();
        TerrainFeature.JUNGLE.initializeVectors();
        TerrainFeature.MARSH.initializeVectors();
        TerrainFeature.OASIS.initializeVectors();

    }

    @Test
    void testIsRough() {
        assertFalse(TerrainFeature.FLOOD_PLAINS.isRough());
        assertTrue(TerrainFeature.FOREST.isRough());
        assertFalse(TerrainFeature.ICE.isRough());
        assertTrue(TerrainFeature.JUNGLE.isRough());
        assertFalse(TerrainFeature.MARSH.isRough());
        assertFalse(TerrainFeature.OASIS.isRough());
    }

    @Test
    void featureFieldsCallTest(){
        for(TerrainFeature feature : TerrainFeature.values()){
            Assertions.assertNotNull(feature.getCombatModifiers());
            Assertions.assertNotNull(feature.getProduction());
            Assertions.assertNotNull(feature.getMovementCost());
            Assertions.assertNotNull(feature.getGold());
            Assertions.assertNotNull(feature.getFood());
            Assertions.assertNotNull(feature.isPassable());
            Assertions.assertNotNull(feature.getRemoveTime());
            Assertions.assertNotNull(feature.getPossibleResources());
        }
    }
}
