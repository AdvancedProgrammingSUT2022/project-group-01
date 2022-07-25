package view.components.unit;

import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import lombok.Getter;
import model.unit.Unit;
import utils.GraphicUtils;

@Getter
public class UnitView extends Rectangle {

    private Unit unit;
    private Pane pane;
    private double lastMouseXPress, lastMouseYPress;
    private double lastFixedX, lastFixedY;
    //temporary todo remove it
    UnitView enemy;
    public UnitView(int temp, Unit unit, Pane pane, int x, int y){
        super(x,y, 80,80);
        this.unit = unit;
        this.pane = pane;
        initialize();
        if(temp == 1)
            enemy = new UnitView(0,unit, pane, x-100, y-100);
    }

    public void initialize(){
        String path = unit.getType().getCombatType().name()+"/"+unit.getType().name()+".png";
        this.setFill(GraphicUtils.getImage("/units/"+path));
        pane.getChildren().add(this);
        this.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(mouseEvent.getClickCount() == 3)
                    move(400,400);
                if(mouseEvent.getClickCount() == 2)
                    attack(enemy);
            }
        });
        initDragAndDrop();
    }

    public void initDragAndDrop(){
        this.setOnMousePressed(mouseEvent -> {
            lastMouseXPress = mouseEvent.getX();
            lastMouseYPress = mouseEvent.getY();
            lastFixedX = UnitView.this.getX();
            lastFixedY = UnitView.this.getY();
        });
        this.setOnMouseDragged(mouseEvent -> {
            UnitView.this.setX(lastFixedX + (mouseEvent.getX() - lastMouseXPress));
            UnitView.this.setY(lastFixedY + (mouseEvent.getY() - lastMouseYPress));
        });
    }

    public void move(int x, int y){
        new UnitMoveTransition(this, x,y);
    }

    public void attack(UnitView enemyView){
        new UnitAttackTransition(this, enemyView, pane);
    }

}
