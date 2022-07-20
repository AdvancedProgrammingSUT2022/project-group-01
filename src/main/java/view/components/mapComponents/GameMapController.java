package view.components.mapComponents;

import controller.GUIController;
import controller.GameMenuController;
import controller.ProgramController;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;
import model.Map;
import model.map.SavedMap;
import model.technology.TechnologyType;
import model.tile.Tile;
import view.Main;
import view.components.GameVisualUI.UIStatesController;
import view.components.StatusBar;
import view.components.gamePanelComponents.TilePopUp;

import java.io.IOException;
import java.net.URL;
import java.util.HashSet;
import java.util.Objects;
import java.util.Vector;

public class GameMapController {
    private GameMenuController gameMenuController;
    private MapTileComponent[][] mapTiles;
    private Pane backPane = new Pane();
    private Pane background = new Pane();
    private Pane cheatPane = new Pane();
    private HashSet<KeyCode> pressedKeys = new HashSet<>();
    double xComponent;
    double yComponent;
    double paneLastX;
    double paneLastY;
    double paneLastScaleX;
    private StatusBar statusBar;
    double paneLastScaleY;
    private Vector<Pane> panels = new Vector<>();
    private Vector<Pane> mapTempPanes = new Vector<>();
    private UIStatesController uiStatesController;

    public GameMapController(GameMenuController gameMenuController) {
        this.gameMenuController = gameMenuController;
        mapTiles = new MapTileComponent[gameMenuController.getGame().getMap().getMapSize()][gameMenuController.getGame().getMap().getMapSize()];
        this.uiStatesController = new UIStatesController(this,gameMenuController);
        initializeBackPane();
    }

    private void initializeBackPane(){
        GUIController.setCursor();
        movementKeyOperation();
        gameMenuController.getMapController().updateCurrentPlayersMap();
        background.setStyle("-fx-background-image: url(asset/background.jpeg)");
        background.prefHeight(1280);
        background.prefWidth(720);
        background.getChildren().add(backPane);
        backPane.setStyle("-fx-background : transparent");
        setMapMovementActions();
        showTiles();
        ProgramController.getGame().getCurrentPlayer().getCivilization().getResearchTree().research(TechnologyType.AGRICULTURE);
        addPanels();
        backPane.requestFocus();
    }

    public Pane getBackground() {
        return background;
    }

    private void showTiles(){
        SavedMap savedMap = gameMenuController.getGame().getCurrentPlayer().getSavedMap();
        Map map = gameMenuController.getGame().getMap();
        Tile centerTile = gameMenuController.getGame().getCurrentPlayer().getMapCenterTile();
        int mapSize = gameMenuController.getGame().getMap().getMapSize();
        centerTile = gameMenuController.getGame().getMap().getTile((mapSize - 1)/2, (mapSize - 1)/2);
        MapTileComponent mapTileComponent = new MapTileComponent(centerTile,this);
        Pane pane = mapTileComponent.initialize();
        backPane.getChildren().add(pane);
        pane.setTranslateX(640);
        pane.setTranslateY(340);
        int differ = (mapSize - 1) / 2;
        for(int p = -differ; p <= differ; p++){
            for(int q = - differ; q <= differ; q++){
                Tile adjacent;
                if(gameMenuController.getGame().getMap().getTile(centerTile.getPCoordinate() + p,centerTile.getQCoordinate() + q) == null)
                    continue;
                else
                    adjacent = map.getTile(centerTile.getPCoordinate() + p, centerTile.getQCoordinate() + q);

                System.out.println((centerTile.getPCoordinate() + p) + "and" + (centerTile.getQCoordinate() + q));
                MapTileComponent tileComponent = new MapTileComponent(adjacent,this);
                mapTiles[centerTile.getPCoordinate() + p][centerTile.getQCoordinate() + q] = tileComponent;
                Pane adjacentPane = tileComponent.initialize();
                backPane.getChildren().add(adjacentPane);
                int differX = -(p + q) * 330;
                int differY = (q - p) * 190;
                adjacentPane.setTranslateX(640 + differX);
                adjacentPane.setTranslateY(340 + differY);
            }
        }
    }

    public void update(){
        backPane.getChildren().clear();
        backPane.requestFocus();
        showTiles();
        backPane.getChildren().addAll(mapTempPanes);
    }

    private void movementKeyOperation(){
        GUIController.getScene().setOnKeyPressed(event -> {
            pressedKeys.add(event.getCode());
            System.out.println(event.getCode().getName());
            cheatActivate();
        });
        GUIController.getScene().setOnKeyReleased(event -> {
            pressedKeys.remove(event.getCode());
        });
    }
    private void cheatActivate(){
        if(pressedKeys.contains(KeyCode.SHIFT) && pressedKeys.contains(KeyCode.F)){
            System.out.println("FUCK");
            try {
                URL address = new URL(Objects.requireNonNull(
                        Main.class.getResource("/FXML/CheatBox.fxml")).toString());
                cheatPane = FXMLLoader.load(address);
                cheatPane.setTranslateX(600);
                cheatPane.setTranslateY(200);
                background.getChildren().add(cheatPane);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void setMapMovementActions(){
        backPane.setOnMousePressed(event -> {
            if(event.isSecondaryButtonDown()) {
                xComponent = backPane.getTranslateX() - event.getScreenX();
                yComponent = backPane.getTranslateY() - event.getScreenY();
                paneLastX = backPane.getTranslateX();
                paneLastY = backPane.getTranslateY();
            }
        });
        backPane.setOnMouseDragged(event -> {
            double x = event.getScreenX() + xComponent;
            double y = event.getScreenY() + yComponent;
            backPane.setTranslateX(x);
            backPane.setTranslateY(y);
            paneLastX = backPane.getTranslateX();
            paneLastY = backPane.getTranslateY();
        });
        backPane.setPrefHeight(Screen.getPrimary().getBounds().getHeight());
        backPane.setPrefWidth(Screen.getPrimary().getBounds().getWidth());
        backPane.setOnScroll(scrollEvent -> {
            double ratio = 1.15;
            double respectingRate = backPane.getScaleY();
            if(scrollEvent.getDeltaY() > 0) respectingRate *= ratio;
            else respectingRate /= ratio;
            if ((respectingRate > 2) || (respectingRate < 0.2))
                return;
            backPane.setScaleX(respectingRate);
            backPane.setScaleY(respectingRate);
            double movingRate;
            if(scrollEvent.getDeltaY() > 0) movingRate = ratio;
            else movingRate = 1 / ratio;
            backPane.setTranslateX(backPane.getTranslateX() * movingRate);
            backPane.setTranslateY(backPane.getTranslateY() * movingRate);
            paneLastX = backPane.getTranslateX();
            paneLastY = backPane.getTranslateY();
            paneLastScaleX = backPane.getScaleX();
            paneLastY = backPane.getScaleY();
        });
    }


    private void addPanels(){
        //TechnologyPopUp technologyPopUp = new TechnologyPopUp(background,this);
        //panels.add(technologyPopUp.getPane());
        //background.getChildren().add(technologyPopUp.getPane());
        addStatusBar();
    }

    public GameMenuController getGameMenuController(){
        return this.gameMenuController;
    }

    public UIStatesController getUiStatesController(){
        return this.uiStatesController;
    }

    private void addStatusBar(){
        statusBar = new StatusBar(gameMenuController.getGame().getCurrentPlayer().getCivilization());
        background.getChildren().add(statusBar);
    }

    public void destroyTemporaryPanels(){
        for(Pane p : panels){
            background.getChildren().remove(p);
        }
        for(Pane p : mapTempPanes){
            backPane.getChildren().remove(p);
        }
    }

    public void addPaneToPanels(Pane pane){
        panels.add(pane);
        background.getChildren().add(pane);
    }

    public void addPanelToBackPane(Pane pane){
        mapTempPanes.add(pane);
        backPane.getChildren().add(pane);
    }

    public MapTileComponent getTileComponentInMap(int p, int q){
        return mapTiles[p][q];
    }
}
