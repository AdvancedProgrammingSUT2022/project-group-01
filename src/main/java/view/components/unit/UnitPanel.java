package view.components.unit;

import javafx.event.EventHandler;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import model.civilization.Civilization;
import model.unit.UnitType;
import model.unit.armed.Armed;
import utils.Pair;
import view.components.GameVisualUI.WorkingObjectType;
import view.components.ImagesAddress;
import view.components.mapComponents.GameMapController;
import view.components.mapComponents.MapTileComponent;

import java.util.HashMap;

public class UnitPanel extends BorderPane {

    private Civilization civilization;
    private GameMapController gmp;
    private final ScrollPane scrollPane = new ScrollPane();
    private final VBox vbox = new VBox();

    public UnitPanel(Civilization civilization){
        this.civilization = civilization;
        init();
    }
    public UnitPanel(Civilization civilization, GameMapController gmp){
        this.civilization = civilization;
        this.gmp = gmp;
        init();
        closeButton();
    }

    private void init(){
        this.getStylesheets().add(getClass().getResource("/CSS/UnitPanel.css").toExternalForm());
        this.setMinWidth(300);
        initVBox();
        initScrollPane();
        initItems();
    }

    private void initScrollPane(){
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.addEventFilter(ScrollEvent.SCROLL,new EventHandler<ScrollEvent>() {
            @Override
            public void handle(ScrollEvent event) {
                if (event.getDeltaX() != 0) {
                    event.consume();
                }
            }
        });

    }

    private void initVBox(){
        vbox.getStyleClass().add("v-box");
        scrollPane.setContent(vbox);
        this.setCenter(scrollPane);
    }

    private void initItems(){
        for (int i = 0; i < civilization.getUnits().size(); i++) {
            UnitRow unitRow = new UnitRow(civilization.getUnits().get(i));
            initAction(unitRow);
            vbox.getChildren().add(unitRow);

        }
    }

    private void initAction(UnitRow unitRow){
        //todo add focus and select request by parham

        unitRow.setOnMouseClicked(mouseEvent -> {
            WorkingObjectType section = WorkingObjectType.ARMED_UNIT;
            if(unitRow.getUnit().equals(unitRow.getUnit().getCurrentTile().getCivilianUnit())){
                section = WorkingObjectType.CIVILIAN_UNIT;
            }
            MapTileComponent m = gmp.getTileComponentInMap(unitRow.getUnit().getCurrentTile().getPCoordinate(),unitRow.getUnit().getCurrentTile().getQCoordinate());
            gmp.getUiStatesController().addObjectByJob(new Pair<>(m,section));
            gmp.getBackPane().setTranslateX(-m.getPane().getTranslateX() + 200);
            gmp.getBackPane().setTranslateY(-m.getPane().getTranslateY() + 200);
        });
    }

    private void closeButton(){
        ImageView close = new ImageView(ImagesAddress.INFO_CLOSE.getImage());
        close.setLayoutX(270);
        close.setLayoutY(5);
        close.setFitHeight(34);
        close.setFitWidth(34);
        close.setPickOnBounds(true);
        close.setPreserveRatio(true);
        this.getChildren().add(close);
        close.setOnMouseClicked(e -> {
            gmp.getBackground().getChildren().remove(this);
        });
    }
}
