package view.components.mapComponents.UserMapBuilder;

import com.jfoenix.controls.JFXButton;
import controller.GUIController;
import controller.GameMenuController;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;
import view.components.mapComponents.GameMapController;
import view.components.mapComponents.UserMapBuilder.MapUserBuilderController;
import view.components.mapComponents.UserMapBuilder.TileBuilderData;

import java.util.HashSet;

public class MapUserBuilder {
    private GameMenuController gameMenuController;
    private Pane backPane = new Pane();
    private Pane background = new Pane();
    private JFXButton proceedButton = new JFXButton("proceed to game");
    private Pane controller = new Pane();
    private HashSet<KeyCode> pressedKeys = new HashSet<>();
    private PreBuiltMap preBuiltMap;
    double xComponent;
    double yComponent;

    public MapUserBuilder(GameMenuController gameMenuController) {
        this.gameMenuController = gameMenuController;
        preBuiltMap = new PreBuiltMap(gameMenuController.getGame().getMap().getMapSize());
        initializeBackPane();
        initProceedToGame();
        saveButton();
    }

    private void initializeBackPane() {
        GUIController.setCursor();
        movementKeyOperation();
        background.setStyle("-fx-background-image: url(asset/background.jpeg)");
        background.prefHeight(1280);
        background.prefWidth(720);
        background.getChildren().add(backPane);
        backPane.setStyle("-fx-background : transparent");
        setMapMovementActions();
        showTiles();
    }

    private void initProceedToGame(){
        proceedButton.setTranslateX(10);
        proceedButton.setTranslateY(10);
        proceedButton.setStyle("-fx-background-color: RED");
        proceedButton.setOnMouseClicked(event ->
        {
            proceedToGame();
        });
        background.getChildren().add(proceedButton);
    }
    private void saveButton(){
        JFXButton save = new JFXButton("Save");
        save.setTranslateX(10);
        save.setTranslateY(40);
        save.setStyle("-fx-background-color: GREEN");
        save.setOnMouseClicked(event ->
        {
            saveMap();
        });
        background.getChildren().add(save);
    }

    private void proceedToGame() {
        setMapToNewOne();
        GameMapController gameMapController = new GameMapController(gameMenuController);
        GUIController.changeMenuManually(gameMapController.getBackPane());
    }

    private void setMapToNewOne() {
        for(int i = 0; i < preBuiltMap.getTiles().length; i++){
            for(int j = 0; j < preBuiltMap.getTiles()[i].length; j++){
                if(preBuiltMap.getTiles()[i][j] == null || preBuiltMap.getTiles()[i][j].getTerrain() == null)
                    continue;
                gameMenuController.getGame().getMap().getTile(i,j).setTerrain(preBuiltMap.getTiles()[i][j].getTerrain());
                gameMenuController.getGame().getMap().getTile(i,j).setFeature(preBuiltMap.getTiles()[i][j].getFeature());
                gameMenuController.getGame().getMap().getTile(i,j).setAvailableResource(preBuiltMap.getTiles()[i][j].getResourceType());
            }
        }
    }

    private void showTiles() {
        int mapSize = gameMenuController.getGame().getMap().getMapSize();
        int differ = (mapSize - 1)/2;
        for(int p = -differ; p <= differ; p++){
            for(int q = -differ; q <= differ; q++){
                if(gameMenuController.getGame().getMap().getTile(differ + p, differ + q) == null)
                    continue;
                TileBuilderData tileBuilderData = new TileBuilderData();
                preBuiltMap.setTile(differ + p, differ + q, tileBuilderData.getTileReservedData());
                Pane tilePane = tileBuilderData.initialize();
                tilePane.setOnMouseClicked(event ->
                {
                    background.getChildren().remove(controller);
                    controller = new MapUserBuilderController(background,tileBuilderData).getRoot();
                });
                backPane.getChildren().add(tilePane);
                int differX = -(p + q) * 165;
                int differY = (q - p) * 95;
                tilePane.setTranslateX(640 + differX);
                tilePane.setTranslateY(340 + differY);
            }
        }
    }

    private void setMapMovementActions() {
        backPane.setOnMousePressed(event -> {
            xComponent = backPane.getTranslateX() - event.getScreenX();
            yComponent = backPane.getTranslateY() - event.getScreenY();
        });
        backPane.setOnMouseDragged(event -> {
            double x = event.getScreenX() + xComponent;
            double y = event.getScreenY() + yComponent;
            backPane.setTranslateX(x);
            backPane.setTranslateY(y);
        });
        backPane.setPrefHeight(Screen.getPrimary().getBounds().getHeight());
        backPane.setPrefWidth(Screen.getPrimary().getBounds().getWidth());
        backPane.setOnScroll(scrollEvent -> {
            double ratio = 1.15;
            double respectingRate = backPane.getScaleY();
            if(scrollEvent.getDeltaY() > 0) respectingRate *= ratio;
            else respectingRate /= ratio;
            if ((respectingRate > 2.5) || (respectingRate < 0.5))
                return;
            backPane.setScaleX(respectingRate);
            backPane.setScaleY(respectingRate);
            double movingRate;
            if(scrollEvent.getDeltaY() > 0) movingRate = ratio;
            else movingRate = 1 / ratio;
            backPane.setTranslateX(backPane.getTranslateX() * movingRate);
            backPane.setTranslateY(backPane.getTranslateY() * movingRate);
        });
    }

    private void movementKeyOperation() {
        GUIController.getScene().setOnKeyPressed(event -> {
            pressedKeys.add(event.getCode());
            System.out.println(event.getCode().getName());
        });
        GUIController.getScene().setOnKeyReleased(event -> {
            pressedKeys.remove(event.getCode());
        });
    }

    public Pane getBackPane() {
        return background;
    }


    private void saveMap() {
        preBuiltMap.toJson();
    }

}
