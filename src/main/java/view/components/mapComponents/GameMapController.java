package view.components.mapComponents;

import controller.GUIController;
import controller.GameMenuController;
import controller.ProgramController;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;
import javafx.util.Duration;
import model.Map;
import model.map.SavedMap;
import model.technology.TechnologyType;
import model.tile.Tile;
import view.Main;
import view.components.GameVisualUI.UIStatesController;
import view.components.ImagesAddress;
import view.components.StatusBar;
import view.components.gamePanelComponents.TilePopUp;
import view.components.infoPanels.CityListPanel;
import view.components.infoPanels.GameEndPage;
import view.components.infoPanels.SideLog;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
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
    private boolean nextTurnSituation;
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
        addNextTurnButton();
        addSideMenu();
    }

    public Pane getBackground() {
        return background;
    }

    private void showTiles(){
        gameMenuController.getMapController().updateCurrentPlayersMap();
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
        statusBar.update();
    }

    private void movementKeyOperation(){
        GUIController.getScene().setOnKeyPressed(event -> {
            if(event.getCode().equals(KeyCode.F) && event.isShiftDown())
                new CheatPage(this);
        });

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
        panels.clear();
        for(Pane p : mapTempPanes){
            backPane.getChildren().remove(p);
        }
        mapTempPanes.clear();
    }

    public void addPaneToPanels(Pane pane){
        if(panels.contains(pane)) return;
        panels.add(pane);
        if(background.getChildren().contains(pane)) return;
        background.getChildren().add(pane);
    }

    public void removePaneFromPanels(Pane pane){
        panels.remove(pane);
        background.getChildren().remove(pane);
    }

    public void addPanelToBackPane(Pane pane){
        mapTempPanes.add(pane);
        backPane.getChildren().add(pane);
    }

    public void clearBackPanePanels(){
        for(Pane p : mapTempPanes){
            backPane.getChildren().remove(p);
        }
        mapTempPanes.clear();
    }

    public MapTileComponent getTileComponentInMap(int p, int q){
        return mapTiles[p][q];
    }

    private void addNextTurnButton(){
        ImageView around = new ImageView();
        around.setFitHeight(148);
        around.setFitWidth(148);
        around.setLayoutX(1107);
        around.setLayoutY(560);
        around.setPickOnBounds(true);
        around.setPreserveRatio(true);
        around.setImage(ImagesAddress.ROUND_NEXT_TURN.getImage());
        ImageView nextTurn = new ImageView();
        nextTurn.setImage(ImagesAddress.NEXT_TURN.getImage());
        nextTurn.setFitHeight(64);
        nextTurn.setFitWidth(57);
        nextTurn.setLayoutX(1155);
        nextTurn.setLayoutY(600);
        nextTurn.setPickOnBounds(true);
        nextTurn.setPreserveRatio(true);
        nextTurn.setOnMouseClicked(event -> {
            if(event.getButton() == MouseButton.PRIMARY) {
                gameMenuController.nextTurn(new HashMap<>());
                update();
                if(getGameMenuController().getGame().isGameEnded()){
                    endTheGame();
                }
            }
        });
        background.getChildren().add(around);
        background.getChildren().add(nextTurn);
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(200), event -> {
            if(nextTurnSituation) {
                around.setImage(ImagesAddress.ROUND_NEXT_TURN_SHINE.getImage());
                nextTurnSituation = false;
            }
            else {
                around.setImage(ImagesAddress.ROUND_NEXT_TURN.getImage());
                nextTurnSituation = true;
            }
        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    private void endTheGame() {
        GUIController.changeMenuManually(new GameEndPage(gameMenuController.getGameController()).getRoot());
    }

    private void addSideMenu(){
        new SideLog(background,this);
    }
}
