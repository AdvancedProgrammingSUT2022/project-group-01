package view.components.unit;

import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import lombok.Getter;
import model.unit.CombatType;
import model.unit.Unit;
import model.unit.UnitType;
import model.unit.action.Actions;
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
    }


    public void move(double x, double y){
        new UnitMoveTransition(this, x,y);
    }

    public void move(MapTileComponent tileComponent){
        move(tileComponent.getPane().getTranslateX(), tileComponent.getPane().getTranslateY());
    }

    public void attack(Pane enemyPane, UnitView enemyView){
        new UnitAttackTransition(this,enemyView,pane,mapTileComponent.getPane(),enemyPane);
    }


    private ImagePattern getActionImage(){
        if(unit.getJob() == null) return null;
        if(unit.getJob().equals(Actions.FORTIFY))
            return GraphicUtils.getImage("/units/actions/fortify.png");
        else if(unit.getJob().equals(Actions.FORTIFY_UNTIL_HEAL))
            return GraphicUtils.getImage("/units/actions/heal.png");
        else if(unit.getJob().equals(Actions.SLEEP))
            return GraphicUtils.getImage("/units/actions/sleep.png");
        else if(unit.getJob().equals(Actions.SETUP))
            return GraphicUtils.getImage("/units/actions/setup.png");
        else if(unit.getJob().equals(Actions.ALERT))
            return GraphicUtils.getImage("/units/actions/alert.png");
        else if(unit.getJob().equals(Actions.MOVE))
            return GraphicUtils.getImage("/units/actions/move_to.png");
        else if(unit.getJob().equals(Actions.SETTLE))
            return GraphicUtils.getImage("/units/actions/found_city.png");
        else if(unit.getJob().equals(Actions.BUILD_IMPROVEMENT))
            return GraphicUtils.getImage("/units/actions/farm.png");
        else if(unit.getJob().equals(Actions.PILLAGE_IMPROVEMENT))
            return GraphicUtils.getImage("/units/actions/repair.png");
        else if(unit.getJob().equals(Actions.REMOVE_IMPROVEMENT))
            return GraphicUtils.getImage("/units/actions/remove.png");
        else if(unit.getJob().equals(Actions.REPAIR_IMPROVEMENT))
            return GraphicUtils.getImage("/units/actions/repair.png");
        else if(unit.getJob().equals(Actions.PAUSE_IMPROVEMENT))
            return GraphicUtils.getImage("/units/actions/cancel.png");
        else if(unit.getJob().equals(Actions.BUILD_ROAD))
            return GraphicUtils.getImage("/units/actions/build_road.png");
        else if(unit.getJob().equals(Actions.BUILD_RAIL))
            return GraphicUtils.getImage("/units/actions/build_rail_road.png");
        else if(unit.getJob().equals(Actions.REMOVE_ROUTE))
            return GraphicUtils.getImage("/units/actions/remove.png");
        else if(unit.getJob().equals(Actions.REMOVE_FEATURE))
            return GraphicUtils.getImage("/units/actions/remove.png");
        else return null;
    }

    public void addJobCircle(){
        Circle jobCircle = new Circle();
        jobCircle.setRadius(7);
        ImagePattern backImage;
        if((backImage = getActionImage()) == null) return;
        jobCircle.setFill(backImage);
        jobCircle.setTranslateX(this.getTranslateX() + this.getWidth()/2);
        jobCircle.setTranslateY(this.getTranslateY());
        pane.getChildren().add(jobCircle);
    }
}
