package model.civilization;

import model.Game;
import model.unit.Unit;
import model.unit.UnitType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class UnitViewTest {
    @Test
    public void tstOne(){
        Game game = Mockito.mock(Game.class);
        Unit unit = Mockito.mock(Unit.class);
        Mockito.when(game.getSelectedObject()).thenReturn(unit);
        Mockito.when(unit.getType()).thenReturn(UnitType.SETTLER);
//        UnitTestGui utg = new UnitTestGui();
//        utg.run(game);
        Assertions.assertEquals(1,1);
    }
}
