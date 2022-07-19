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
    private double lastMouseXPress, lastMouseYPress;
    private double lastFixedX, lastFixedY;
    private Pane pane;
    //temporary todo remove it

    public UnitView(Unit unit,Pane pane){
        super(80,80);
        this.pane = pane;
        this.unit = unit;
        initialize();
    }

    public void initialize(){
        String path = unit.getType().getCombatType().name()+"/"+unit.getType().name()+".png";
        this.setFill(GraphicUtils.getImage("/units/"+path));
        this.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                UnitActionsPanel uap = new UnitActionsPanel(UnitView.this);
                //pane.getChildren().add(uap);
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
