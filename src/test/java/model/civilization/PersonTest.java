package model.civilization;

import model.resource.ResourceType;
import model.tile.Terrain;
import model.tile.TerrainFeature;
import model.tile.Tile;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PersonTest {

    private Person personUnderTest;

    @BeforeEach
    void setUp() {
        personUnderTest = new Person();
    }

    @Test
    void setTileTest(){
        Tile tile = new Tile(Terrain.DESERT, TerrainFeature.ICE,null, ResourceType.IRON,10,10,10);
        personUnderTest.setTile(tile);
        Tile tile2 = new Tile(Terrain.DESERT, TerrainFeature.ICE,null, ResourceType.IRON,10,10,10);
        personUnderTest.setTile(tile2);
        Assertions.assertEquals(personUnderTest.getTile(),tile2);
    }
}
