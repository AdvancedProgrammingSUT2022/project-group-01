package view.components.unit;

import javafx.geometry.Pos;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import model.unit.Unit;
import utils.StringUtils;

import java.util.Vector;

public class UnitActionsPanel extends VBox {
    private Unit unit;
    private UnitView unitView;
    private Vector<UnitAction> actions = new Vector<>();
    public UnitActionsPanel(UnitView unitView){
        this.unitView = unitView;
        this.unit = unitView.getUnit();
        init();
    }

    public void init(){
        initItems();
        initGraphic();
    }

    public void initItems(){
        //todo add special items
        initGeneralItems();
    }

    public void initGeneralItems(){
        actions.add(UnitAction.SKIP_TURN);
        if(unit.isSleeping())
            actions.add(UnitAction.WAKE_UP);
        else
            actions.add(UnitAction.SLEEP);
        actions.add(UnitAction.DELETE);
    }

    public void initGraphic(){
        this.setAlignment(Pos.CENTER);
        this.getStylesheets().add(getClass().getResource("/CSS/UnitActions.css").toExternalForm());
        this.getStyleClass().add("box");
        this.setSpacing(5);
        for(UnitAction action: actions){
            this.getChildren().add(initSingleItem(action));
        }
    }

    public Pane initSingleItem(UnitAction action){
        Pane pane = new Pane();
        pane.setMinWidth(30);
        pane.setMaxWidth(30);
        pane.setMinHeight(30);
        pane.setMaxHeight(30);
        Rectangle background = new Rectangle(30,30);
        background.setArcHeight(10);
        background.setArcWidth(10);
        background.setFill(new Color(0.8f,0.8f,0.8f,1f));
        background.setX(0);
        background.setY(0);
        pane.getChildren().add(background);
        Rectangle icon = new Rectangle(30,30);
        icon.setFill(action.getIcon());
        icon.setOnMouseClicked(mouseEvent -> {action.effect(unitView);});
        icon.getStyleClass().add("item");
        icon.setX(0);
        icon.setY(0);
        Tooltip tooltip = new Tooltip(StringUtils.convertToPascalCase(action.name()).replaceAll("_"," "));
        Tooltip.install(icon, tooltip);
        pane.getChildren().add(icon);
        return pane;
    }

}
