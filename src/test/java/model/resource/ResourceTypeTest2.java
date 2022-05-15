package model.resource;

import model.civilization.Civilization;
import model.civilization.Person;
import model.technology.TechTree;
import model.technology.TechnologyType;
import model.tile.Terrain;
import model.tile.Tile;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.Vector;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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
        }
    }

    @Test
    void visibilityTest(){
        Civilization civilization = mock(Civilization.class);
        Tile tile = mock(Tile.class);
        TechTree techTree = mock(TechTree.class);
        when(civilization.getResearchTree()).thenReturn(techTree);
        when(techTree.isResearched(TechnologyType.SCIENTIFIC_THEORY)).thenReturn(true);
        ResourceType.COAL.isVisible(civilization);
    }
    @Test
    void getCurrencyTest(){
        Civilization civilization = mock(Civilization.class);
        Vector<Person> dolls = mock(Vector.class);
        when(dolls.isEmpty()).thenReturn(false);
        Tile tile = mock(Tile.class);
        when(tile.getCivilization()).thenReturn(civilization);
        TechTree techTree = mock(TechTree.class);
        when(civilization.getResearchTree()).thenReturn(techTree);
        when(techTree.isResearched(TechnologyType.SCIENTIFIC_THEORY)).thenReturn(true);
        when(tile.getPeopleInside()).thenReturn(dolls);
        ResourceType.COAL.getCurrency(tile);
    }

}