package view.components.unit;

import javafx.animation.Transition;
import javafx.util.Duration;

public class UnitMoveTransition extends Transition {
    private UnitView unitView;
    private double baseX, baseY, destX, destY;
    public UnitMoveTransition(UnitView unitView, int destX, int destY){
        this.unitView = unitView;
        setCycleCount(1);
        setCycleDuration(Duration.millis(500));
        initCoordinates(destX, destY);
        this.play();
    }

    public void initCoordinates(double destX, double destY){
        this.baseX = unitView.getX();
        this.baseY = unitView.getY();
        this.destX = destX;
        this.destY = destY;
    }

    @Override
    protected void interpolate(double v) {
        unitView.setX(baseX + v * (destX - baseX));
        unitView.setY(baseY + v * (destY - baseY));
    }
}
