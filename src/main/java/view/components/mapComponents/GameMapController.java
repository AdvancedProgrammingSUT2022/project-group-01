package view.components.mapComponents;

import controller.GUIController;
import controller.GameMenuController;
import controller.ProgramController;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.input.TouchEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;
import model.Map;
import model.civilization.city.City;
import model.map.SavedMap;
import model.technology.TechnologyType;
import model.tile.Terrain;
import model.tile.Tile;
import view.Main;
import view.components.city.CityOverview;
import view.components.gamePanelComponents.technologyPanel.TechnologyPopUp;

import java.io.IOException;
import java.net.URL;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.Vector;

public class GameMapController {
    private GameMenuController gameMenuController;
    private Pane backPane = new Pane();
    private Pane background = new Pane();
    private Pane cheatPane = new Pane();
    private HashSet<KeyCode> pressedKeys = new HashSet<>();
    double xComponent;
    double yComponent;
    private Vector<Pane> panels = new Vector<>();
    private Object selectedObject;
    public GameMapController(GameMenuController gameMenuController) {
        this.gameMenuController = gameMenuController;
        initializeBackPane();
    }

    private void initializeBackPane(){
        GUIController.setCursor();
        movementKeyOperation();
        backPane.requestFocus();
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
    }

    public Pane getBackPane() {
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
                Pane adjacentPane = tileComponent.initialize();
                backPane.getChildren().add(adjacentPane);
                int differX = -(p + q) * 165;
                int differY = (q - p) * 95;
                adjacentPane.setTranslateX(640 + differX);
                adjacentPane.setTranslateY(340 + differY);
            }
        }
    }

    public void update(){
        background.getChildren().clear();
        backPane = new Pane();
        cheatPane = new Pane();
        initializeBackPane();
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
                cheatPane.setTranslateY(400);
                background.getChildren().add(cheatPane);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void setMapMovementActions(){
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


    private void addPanels(){
        //TechnologyPopUp technologyPopUp = new TechnologyPopUp(background,this);
        //panels.add(technologyPopUp.getPane());
        //background.getChildren().add(technologyPopUp.getPane());
    }

    public GameMenuController getGameMenuController(){
        return this.gameMenuController;
    }

    public void setSelectedObject(Object o){
        this.selectedObject = o;
        //TODO update selected object actions
        if(o instanceof City){
            CityOverview overview = new CityOverview((City)o);
            System.out.println("city got fucked?");
            background.getChildren().add(overview);
        }
    }




}
