package view.components.unit;

import javafx.scene.paint.ImagePattern;
import lombok.Getter;
import utils.GraphicUtils;

@Getter
public enum UnitAction { //todo check list
    ALERT(),
    BUILD_ROUTE(),
    CANCEL(),
    DELETE(),
    FORTIFY(),
    FOUND_CITY(),
    HEAL(),
    MOVE_TO(),
    PROMOTE(),
    RANGE_ATTACK(),
    SKIP_TURN(),
    SLEEP(),
    UPGRADE(),
    WAKE_UP(){
        @Override
        public void effect(UnitView unitView){
            System.out.println("waking up");
        }
    };


    private ImagePattern icon;

    UnitAction() {
        this.icon = GraphicUtils.getImage("/units/actions/"+name().toLowerCase()+".png");
    }

    public void effect(UnitView unitView) {
        System.out.println("I'm doing st");
    }
}
