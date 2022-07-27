package view.components.unit;

import javafx.animation.FadeTransition;
import javafx.animation.Transition;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

public class UnitAttackTransition extends Transition {

    Pane pane;
    UnitView enemy;
    Pane unitPane;
    Pane enemyPane;
    UnitView unit;
    Circle bullet;
    double baseX, baseY,destX, destY;
    Boolean hasBullet = false;
    public UnitAttackTransition(UnitView unit, UnitView enemy, Pane pane,Pane unitPane, Pane enemyPane){
        if(unit.getUnit().getType().getRange() == -1){
            setCycleCount(2);
            setAutoReverse(true);
            setCycleDuration(Duration.millis(500));
        }else {
            hasBullet = true;
            setCycleCount(1);
            setCycleDuration(Duration.millis(1000));
        }
        this.unitPane = unitPane;
        this.enemyPane = enemyPane;
        this.pane = pane;
        this.unit = unit;
        this.enemy = enemy;
        initBullet();
        blink();
        this.play();
    }

    public void initBullet(){
        bullet = new Circle(unit.getTranslateX() + unitPane.getTranslateX() +30,unit.getTranslateY() + unitPane.getTranslateY() + 30,2);
        baseX = unit.getTranslateX() + unitPane.getTranslateX();
        baseY = unit.getTranslateY() + unitPane.getTranslateY();
        destX = enemy.getTranslateX() + enemyPane.getTranslateX();
        destY = enemy.getTranslateY() + enemyPane.getTranslateY();
        bullet.setStyle("-fx-background-color: black");//todo check it
        if(hasBullet) {
            pane.getChildren().add(bullet);
            this.setOnFinished(actionEvent -> {pane.getChildren().remove(bullet);});
        }
    }



    public void handleBullet(double v){
        if(!hasBullet)
            return;
        bullet.setLayoutX(v * (destX - baseX));
        bullet.setLayoutY(v * (destY - baseY));
    }

//    public void handleMove(double v){
//        if(hasBullet)
//            return;
//        unit.setX(baseX + v * (destX - baseX));
//        unit.setY(baseY + v * (destY - baseY));
//    }

    public void blink(){
        FadeTransition fadeTransition = new FadeTransition(Duration.millis(250), enemy);
        fadeTransition.setFromValue(1.0);
        fadeTransition.setToValue(0.1);
        fadeTransition.setCycleCount(6);
        fadeTransition.setAutoReverse(true);
        fadeTransition.play();
    }

    @Override
    protected void interpolate(double v) {
        handleBullet(v);
        //handleMove(v);
    }
}
