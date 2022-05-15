package model.resource;

import model.civilization.Civilization;
import model.tile.Tile;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;

class ResourceTypeTest2 {

    @Test
    void isVisible() {
    }

    @Mock
    Tile tile;
    @Test
    void initializeVectors() {
        for(ResourceType resourceType : ResourceType.values()) {
            resourceType.initializeVectors();
            resourceType.getCurrency(tile);
        }
    }
}