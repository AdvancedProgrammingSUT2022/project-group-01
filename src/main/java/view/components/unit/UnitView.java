package view.components.unit;

import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import lombok.Getter;
import model.unit.CombatType;
import model.unit.Unit;
import model.unit.UnitType;
import utils.GraphicUtils;
import utils.Pair;
import view.components.GameVisualUI.WorkingObjectType;
import view.components.mapComponents.GameMapController;
import view.components.mapComponents.MapTileComponent;

@Getter
public class UnitView extends Rectangle {

    private Unit unit;
    private double lastMouseXPress, lastMouseYPress;
    private double lastFixedX, lastFixedY;
    private Pane pane;
    private MapTileComponent mapTileComponent;
    //temporary todo remove it

    public UnitView(Unit unit,Pane pane, MapTileComponent mapTileComponent) {
        super(80,80);
        this.pane = pane;
        this.unit = unit;
        this.mapTileComponent = mapTileComponent;
        initialize();
    }

    public void initialize(){
        String path = unit.getType().getCombatType().name()+"/"+unit.getType().name()+".png";
        this.setFill(GraphicUtils.getImage("/units/"+path));
        this.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
                    WorkingObjectType type = WorkingObjectType.ARMED_UNIT;
                    if (unit.getType().getCombatType().equals(CombatType.CIVILIAN)) {
                        type = WorkingObjectType.CIVILIAN_UNIT;
                    }
                    mapTileComponent.getGameMapController().getUiStatesController().addObjectByJob(new Pair<>(mapTileComponent, type));
                }
            }
        });
        initDragAndDrop();
    }

    public void initDragAndDrop(){
//        this.setOnMousePressed(mouseEvent -> {
//            lastMouseXPress = mouseEvent.getX();
//            lastMouseYPress = mouseEvent.getY();
//            lastFixedX = UnitView.this.getX();
//            lastFixedY = UnitView.this.getY();
//        });
//        this.setOnMouseDragged(mouseEvent -> {
//            UnitView.this.setX(lastFixedX + (mouseEvent.getX() - lastMouseXPress));
//            UnitView.this.setY(lastFixedY + (mouseEvent.getY() - lastMouseYPress));
//        });
    }

    public void move(double x, double y){
        new UnitMoveTransition(this, x,y);
    }

    public void move(MapTileComponent tileComponent){
        move(tileComponent.getPane().getTranslateX(), tileComponent.getPane().getTranslateY());
    }

    public void attack(UnitView enemyView){
        new UnitAttackTransition(this, enemyView, pane);
    }

}
