package view.components.unit;

import javafx.scene.layout.VBox;
import model.unit.Unit;

public class UnitStatePanel extends VBox {

    UnitView unitView;
    Unit unit;

    public UnitStatePanel(UnitView unitView){
        this.unitView = unitView;
        this.unit = unitView.getUnit();
    }

    public void init(){

    }

    public void initItems(){
        //unit.isSleeping()
    }
}
