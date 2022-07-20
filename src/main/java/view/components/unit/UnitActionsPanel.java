package view.components.unit;

import javafx.geometry.Pos;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import lombok.Getter;
import model.improvement.ImprovementType;
import model.unit.CombatType;
import model.unit.Unit;
import model.unit.UnitType;
import utils.StringUtils;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Vector;
@Getter
public class UnitActionsPanel extends VBox {
    private Unit unit;
    private UnitView unitView;
    private Vector<UnitAction> actions = new Vector<>();
    private HashMap<UnitAction, Vector<UnitType>> actionCorrespondingUnitTypes = new HashMap<>();
    private void initActionCorrespondingUnitTypes() {
        actionCorrespondingUnitTypes.put(UnitAction.ALERT, new Vector<>());
        actionCorrespondingUnitTypes.put(UnitAction.FORTIFY, new Vector<>());
        actionCorrespondingUnitTypes.put(UnitAction.PILLAGE, new Vector<>());
        actionCorrespondingUnitTypes.put(UnitAction.HEAL, new Vector<>());
        actionCorrespondingUnitTypes.put(UnitAction.RANGE_ATTACK, new Vector<>());
        actionCorrespondingUnitTypes.put(UnitAction.ATTACK, new Vector<>());
        actionCorrespondingUnitTypes.put(UnitAction.CITY_ATTACK, new Vector<>());
        for(UnitType a : UnitType.values()){
            if(!a.getCombatType().equals(CombatType.CIVILIAN)) {
                actionCorrespondingUnitTypes.get(UnitAction.ALERT).add(a);
                actionCorrespondingUnitTypes.get(UnitAction.FORTIFY).add(a);
                actionCorrespondingUnitTypes.get(UnitAction.PILLAGE).add(a);
                actionCorrespondingUnitTypes.get(UnitAction.HEAL).add(a);
                actionCorrespondingUnitTypes.get(UnitAction.RANGE_ATTACK).add(a);
                actionCorrespondingUnitTypes.get(UnitAction.ATTACK).add(a);
                actionCorrespondingUnitTypes.get(UnitAction.CITY_ATTACK).add(a);
            }
        }
        actionCorrespondingUnitTypes.put(UnitAction.BUILD_ROUTE, new Vector<>());
        //TODO: wtf is route
        actionCorrespondingUnitTypes.put(UnitAction.FOUND_CITY, new Vector<>());
        actionCorrespondingUnitTypes.get(UnitAction.FOUND_CITY).add(UnitType.SETTLER);


        actionCorrespondingUnitTypes.put(UnitAction.BUILD_RAIL_ROAD, new Vector<>());
        actionCorrespondingUnitTypes.get(UnitAction.BUILD_RAIL_ROAD).add(UnitType.WORKER);
        actionCorrespondingUnitTypes.put(UnitAction.BUILD_ROAD, new Vector<>());
        actionCorrespondingUnitTypes.get(UnitAction.BUILD_ROAD).add(UnitType.WORKER);
        actionCorrespondingUnitTypes.put(UnitAction.REPAIR, new Vector<>());
        actionCorrespondingUnitTypes.get(UnitAction.REPAIR).add(UnitType.WORKER);
        actionCorrespondingUnitTypes.put(UnitAction.REMOVE, new Vector<>());
        actionCorrespondingUnitTypes.get(UnitAction.REMOVE).add(UnitType.WORKER);
    }
    public UnitActionsPanel(UnitView unitView){
        this.unitView = unitView;
        this.unit = unitView.getUnit();
        initActionCorrespondingUnitTypes();
        init();
    }

    public void init(){
        initItems();
        initGraphic();
    }

    public void initItems(){
        //todo add special items
        for(UnitAction ua : actionCorrespondingUnitTypes.keySet()){
            if(actionCorrespondingUnitTypes.get(ua).contains(unit.getType())){
                actions.add(ua);
            }
        }
        initImprovementBuilding();
        initGeneralItems();
    }

    private void initImprovementBuilding() {
        if(!unit.getType().equals(UnitType.WORKER)) return;
        if(ImprovementType.CAMP.isEligibleToBuild(unitView.getMapTileComponent().getGameMapController().getGameMenuController().getGame().getCurrentPlayer().getCivilization(),unitView.getMapTileComponent().getTile()))
            actions.add(UnitAction.CAMP);
        if(ImprovementType.FARM.isEligibleToBuild(unitView.getMapTileComponent().getGameMapController().getGameMenuController().getGame().getCurrentPlayer().getCivilization(),unitView.getMapTileComponent().getTile()))
            actions.add(UnitAction.FARM);
        if(ImprovementType.MINE.isEligibleToBuild(unitView.getMapTileComponent().getGameMapController().getGameMenuController().getGame().getCurrentPlayer().getCivilization(),unitView.getMapTileComponent().getTile()))
            actions.add(UnitAction.MINE);
        if(ImprovementType.TRADING_POST.isEligibleToBuild(unitView.getMapTileComponent().getGameMapController().getGameMenuController().getGame().getCurrentPlayer().getCivilization(),unitView.getMapTileComponent().getTile()))
            actions.add(UnitAction.TRADING_POST);
        if(ImprovementType.LUMBER_MILL.isEligibleToBuild(unitView.getMapTileComponent().getGameMapController().getGameMenuController().getGame().getCurrentPlayer().getCivilization(),unitView.getMapTileComponent().getTile()))
            actions.add(UnitAction.LUMBER_MILL);
        if(ImprovementType.QUARRY.isEligibleToBuild(unitView.getMapTileComponent().getGameMapController().getGameMenuController().getGame().getCurrentPlayer().getCivilization(),unitView.getMapTileComponent().getTile()))
            actions.add(UnitAction.QUARRY);
        if(ImprovementType.PASTURE.isEligibleToBuild(unitView.getMapTileComponent().getGameMapController().getGameMenuController().getGame().getCurrentPlayer().getCivilization(),unitView.getMapTileComponent().getTile()))
            actions.add(UnitAction.PASTURE);
        if(ImprovementType.PLANTATION.isEligibleToBuild(unitView.getMapTileComponent().getGameMapController().getGameMenuController().getGame().getCurrentPlayer().getCivilization(),unitView.getMapTileComponent().getTile()))
            actions.add(UnitAction.PLANTATION);
        if(ImprovementType.MANUFACTORY.isEligibleToBuild(unitView.getMapTileComponent().getGameMapController().getGameMenuController().getGame().getCurrentPlayer().getCivilization(),unitView.getMapTileComponent().getTile()))
            actions.add(UnitAction.MANUFACTORY);

    }

    public void initGeneralItems(){
        actions.add(UnitAction.MOVE_TO);
        if(unit.isSleeping())
            actions.add(UnitAction.WAKE_UP);
        else
            actions.add(UnitAction.SLEEP);
        actions.add(UnitAction.CANCEL);
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
        icon.setOnMouseClicked(mouseEvent -> {action.effect(unitView,unitView.getMapTileComponent().getGameMapController());});
        icon.getStyleClass().add("item");
        icon.setX(0);
        icon.setY(0);
        Tooltip tooltip = new Tooltip(StringUtils.convertToPascalCase(action.name()).replaceAll("_"," "));
        Tooltip.install(icon, tooltip);
        pane.getChildren().add(icon);
        return pane;
    }

}
