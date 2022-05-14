package model.improvement;

import model.civilization.Civilization;
import model.civilization.Civilizations;
import model.civilization.city.City;
import model.tile.Terrain;
import model.tile.TerrainFeature;
import model.tile.Tile;
import model.unit.action.UnitActions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MiscellaneousTileActionsInventoryTest {
    @Mock
    Tile tile;
    @Mock Civilization civ1;

    @Test
    public void nextTurnTest(){
       // Civilization civilization = new Civilization(Civilizations.ASSYRIAN,new City("hi",null,tile));

    }
}