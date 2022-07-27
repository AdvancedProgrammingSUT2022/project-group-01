package view.components.mapComponents;

import com.jfoenix.controls.JFXButton;
import controller.GameMenuController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import utils.CommandProcessor;
import utils.Commands;
import view.CommandAction;
import view.Main;
import view.components.ImagesAddress;
import view.components.popup.PopUp;
import view.components.popup.PopUpStates;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class CheatPage {
    private GameMenuController controller;
    private GameMapController gameMapController;
    private Pane background = new Pane();
    private JFXButton cheatButton;
    private TextField cheatInput;
    protected HashMap<CommandAction, Commands> commands;

    {
        commands = new HashMap<>() {{
            put(new CommandAction() {
                public String action(HashMap<String, String> args) {
                    return controller.selectUnit(args);
                }
            }, Commands.SELECT_UNIT);
            put(new CommandAction() {
                public String action(HashMap<String, String> args) {
                    return controller.selectCity(args);
                }
            }, Commands.SELECT_CITY);
            put(new CommandAction() {
                public String action(HashMap<String, String> args) {
                    return controller.unitMove(args);
                }
            }, Commands.UNIT_MOVE);
            put(new CommandAction() {
                public String action(HashMap<String, String> args) {
                    return controller.unitSleep(args);
                }
            }, Commands.UNIT_SLEEP);
            put(new CommandAction() {
                public String action(HashMap<String, String> args) {
                    return controller.unitAlert(args);
                }
            }, Commands.UNIT_ALERT);
            put(new CommandAction() {
                public String action(HashMap<String, String> args) {
                    return controller.unitFortify(args);
                }
            }, Commands.UNIT_FORTIFY);
            put(new CommandAction() {
                public String action(HashMap<String, String> args) {
                    return controller.unitFortifyUntilHeal(args);
                }
            }, Commands.UNIT_FORTIFY_UNTIL_HEAL);
            put(new CommandAction() {
                public String action(HashMap<String, String> args) {
                    return controller.unitSetup(args);
                }
            }, Commands.UNIT_SETUP);
            put(new CommandAction() {
                public String action(HashMap<String, String> args) {
                    return controller.unitMeleeAttack(args);
                }
            }, Commands.UNIT_MELEE_ATTACK);
            put(new CommandAction() {
                public String action(HashMap<String, String> args) {
                    return controller.unitRangedAttack(args);
                }
            }, Commands.UNIT_RANGED_ATTACK);
            put(new CommandAction() {
                public String action(HashMap<String, String> args) {
                    return controller.unitFoundCity(args);
                }
            }, Commands.UNIT_FOUND_CITY);
            put(new CommandAction() {
                public String action(HashMap<String, String> args) {
                    return controller.unitCancelMission(args);
                }
            }, Commands.UNIT_CANCEL_MISSION);
            put(new CommandAction() {
                public String action(HashMap<String, String> args) {
                    return controller.unitWake(args);
                }
            }, Commands.UNIT_WAKE);
            put(new CommandAction() {
                public String action(HashMap<String, String> args) {
                    return controller.unitDelete(args);
                }
            }, Commands.UNIT_DELETE);
            put(new CommandAction() {
                public String action(HashMap<String, String> args) {
                    return controller.unitPillage(args);
                }
            }, Commands.UNIT_PILLAGE);
            put(new CommandAction() {
                public String action(HashMap<String, String> args) {
                    return controller.unitRemove(args);
                }
            }, Commands.UNIT_REMOVE);
            put(new CommandAction() {
                public String action(HashMap<String, String> args) {
                    return controller.unitRepair(args);
                }
            }, Commands.UNIT_REPAIR);
            put(new CommandAction() {
                public String action(HashMap<String, String> args) {
                    return controller.selectUnit(args);
                }
            }, Commands.SELECT_UNIT);
            put(new CommandAction() {
                public String action(HashMap<String, String> args) {
                    return controller.menuEnter(args);
                }
            }, Commands.MENU_ENTER);
            put(new CommandAction() {
                public String action(HashMap<String, String> args) {
                    return controller.menuExit(args);
                }
            }, Commands.MENU_EXIT);
            put(new CommandAction() {
                public String action(HashMap<String, String> args) {
                    return controller.currentMenu(args);
                }
            }, Commands.CURRENT_MENU);
            put(new CommandAction() {
                public String action(HashMap<String, String> args) {
                    return controller.increaseResource(args);
                }
            }, Commands.INCREASE_RESOURCE);
            put(new CommandAction() {
                public String action(HashMap<String, String> args) {
                    return controller.spawnUnit(args);
                }
            }, Commands.SPAWN_UNIT);
            put(new CommandAction() {
                public String action(HashMap<String, String> args) {
                    return controller.mapMove(args);
                }
            }, Commands.MAP_MOVE);
            put(new CommandAction() {
                public String action(HashMap<String, String> args) {
                    return controller.mapShow(args);
                }
            }, Commands.MAP_SHOW);
            put(new CommandAction() {
                public String action(HashMap<String, String> args) {
                    return controller.addTechnology(args);
                }
            }, Commands.ADD_TECHNOLOGY);
            put(new CommandAction() {
                public String action(HashMap<String, String> args) {
                    return controller.getResearchableTechnologies(args);
                }
            }, Commands.SHOW_RESEARCHABLE_TECHS);
            put(new CommandAction() {
                public String action(HashMap<String, String> args) {
                    return controller.getCurrentResearch(args);
                }
            }, Commands.GET_CURRENT_RESEARCH);
            put(new CommandAction() {
                public String action(HashMap<String, String> args) {
                    return controller.research(args);
                }
            }, Commands.RESEARCH);
            put(new CommandAction() {
                public String action(HashMap<String, String> args) {
                    return controller.addBeaker(args);
                }
            }, Commands.ADD_BEAKER);
            put(new CommandAction() {
                public String action(HashMap<String, String> args) {
                    return controller.changeResearch(args);
                }
            }, Commands.CHANGE_RESEARCH);
            put(new CommandAction() {
                public String action(HashMap<String, String> args) {
                    return controller.cheatNextTurn(args);
                }
            }, Commands.CHEAT_NEXT_TURN);
            put(new CommandAction() {
                public String action(HashMap<String, String> args) {
                    return controller.showNextTiles(args);
                }
            }, Commands.SHOW_NEXT_TILES);
            put(new CommandAction() {
                public String action(HashMap<String, String> args) {
                    return controller.purchaseProduction(args);
                }
            }, Commands.PURCHASE_PRODUCTION);
            put(new CommandAction() {
                public String action(HashMap<String, String> args) {
                    return controller.unitActionList(args);
                }
            }, Commands.UNIT_ACTION_LIST);
            put(new CommandAction() {
                public String action(HashMap<String, String> args) {
                    return controller.listOfProductions(args);
                }
            }, Commands.LIST_OF_PRODUCTIONS);
            put(new CommandAction() {
                public String action(HashMap<String, String> args) {
                    return controller.listOfAllProductions(args);
                }
            }, Commands.LIST_OF_ALL_OF_PRODUCTIONS);
            put(new CommandAction() {
                public String action(HashMap<String, String> args) {
                    return controller.setProduction(args);
                }
            }, Commands.SET_PRODUCTION);
            put(new CommandAction() {
                public String action(HashMap<String, String> args) {
                    return controller.getPurchasableTiles(args);
                }
            }, Commands.GET_PURCHASABLE_TILES);
            put(new CommandAction() {
                public String action(HashMap<String, String> args) {
                    return controller.purchaseTile(args);
                }
            }, Commands.PURCHASE_TILE);
            put(new CommandAction() {
                public String action(HashMap<String, String> args) {
                    return controller.purchaseTile(args);
                }
            }, Commands.PURCHASE_TILE);
            put(new CommandAction() {
                public String action(HashMap<String, String> args) {
                    return controller.listOfPopulation(args);
                }
            }, Commands.LIST_OF_POPULATION);
            put(new CommandAction() {
                public String action(HashMap<String, String> args) {
                    return controller.setTileForPopulation(args);
                }
            }, Commands.SET_TILE_FOR_POPULATION);
            put(new CommandAction() {
                public String action(HashMap<String, String> args) {
                    return controller.deletePopulation(args);
                }
            }, Commands.DELETE_POPULATION_FROM_TILE);
            put(new CommandAction() {
                public String action(HashMap<String, String> args) {
                    return controller.buildImprovement(args);
                }
            }, Commands.UNIT_BUILD_IMPROVEMENT);
            put(new CommandAction() {
                public String action(HashMap<String, String> args) {
                    return controller.removeFogOfWar(args);
                }
            }, Commands.FOG_OF_WAR);
            put(new CommandAction() {
                public String action(HashMap<String, String> args) {
                    return controller.createFeature(args);
                }
            }, Commands.CREATE_FEATURE);
            put(new CommandAction() {
                public String action(HashMap<String, String> args) {
                    return controller.addHappiness(args);
                }
            }, Commands.ADD_HAPPINESS);
            put(new CommandAction() {
                public String action(HashMap<String, String> args) {
                    return controller.addScore(args);
                }
            }, Commands.ADD_SCORE);
            put(new CommandAction() {
                public String action(HashMap<String, String> args) {
                    return controller.nextTurn(args);
                }
            }, Commands.NEXT_TURN);
            put(new CommandAction() {
                public String action(HashMap<String, String> args) {
                    return controller.multiNextTurn(args);
                }
            }, Commands.MULTI_NEXT_TURN);
            put(new CommandAction() {
                public String action(HashMap<String, String> args) {
                    return controller.buildImprovement(args);
                }
            }, Commands.UNIT_BUILD_IMPROVEMENT);
            put(new CommandAction() {
                public String action(HashMap<String, String> args) {
                    return controller.unitInfo(args);
                }
            }, Commands.UNIT_INFO);
            put(new CommandAction() {
                public String action(HashMap<String, String> args) {
                    return controller.damageUnit(args);
                }
            }, Commands.DAMAGE_UNIT);
            put(new CommandAction() {
                public String action(HashMap<String, String> args) {
                    return controller.showPlayer(args);
                }
            }, Commands.SHOW_PLAYER);
            put(new CommandAction() {
                public String action(HashMap<String, String> args) {
                    return controller.showTileInfo(args);
                }
            }, Commands.SHOW_TILE_INFO);
            put(new CommandAction() {
                public String action(HashMap<String, String> args) {
                    return controller.showTileInfo(args);
                }
            }, Commands.UNIT_REMOVE_FEATURE);
            put(new CommandAction() {
                public String action(HashMap<String, String> args) {
                    return controller.destroyCity(args);
                }
            }, Commands.DESTROY);
            put(new CommandAction() {
                public String action(HashMap<String, String> args) {
                    return controller.buildRoad(args);
                }
            }, Commands.UNIT_BUILD_ROAD);
            put(new CommandAction() {
                public String action(HashMap<String, String> args) {
                    return controller.buildRail(args);
                }
            }, Commands.UNIT_BUILD_RAIL);
            put(new CommandAction() {
                public String action(HashMap<String, String> args) {
                    return controller.removeRoute(args);
                }
            }, Commands.UNIT_REMOVE_ROUTE);
            put(new CommandAction() {
                public String action(HashMap<String, String> args) {
                    return controller.tileInfo(args);
                }
            }, Commands.TILE_INFO);
            put(new CommandAction() {
                public String action(HashMap<String, String> args) {
                    return controller.cityAttack(args);
                }
            }, Commands.CITY_ATTACK);
            put(new CommandAction() {
                public String action(HashMap<String, String> args) {
                    return controller.teleport(args);
                }
            }, Commands.TELEPORT);

        }};
    }

    public CheatPage(GameMapController gameMapController) {
        this.gameMapController = gameMapController;
        this.controller = gameMapController.getGameMenuController();
        Stage cheatStage = new Stage();
        initialize();
        Scene scene = new Scene(background);
        cheatStage.setWidth(400);
        cheatStage.setHeight(300);
        cheatStage.setResizable(false);
        cheatStage.setScene(scene);
        cheatStage.show();
    }

    public void initialize(){
        background.setPrefHeight(300);
        background.setPrefWidth(400);
        background.setStyle("-fx-background-image: url(asset/gamePanels/cheat.JPG)");
        Rectangle backRect = new Rectangle();
        backRect.setFill(Color.rgb(33,35,33));
        backRect.setArcHeight(10);
        backRect.setArcWidth(10);
        backRect.setHeight(300);
        backRect.setLayoutX(0);
        backRect.setStroke(Color.rgb(231,99,99));
        background.getChildren().add(backRect);
        cheatInput = new TextField();
        cheatInput.setLayoutX(16);
        cheatInput.setLayoutY(120);
        cheatInput.setPrefHeight(40);
        cheatInput.setPrefWidth(372);
        cheatInput.getStylesheets().add(Main.class.getResource("/CSS/FirstPage.css").toExternalForm());
        cheatInput.getStyleClass().add("tf_box");
        background.getChildren().add(cheatInput);
        cheatButton = new JFXButton();
        cheatButton.setLayoutX(131);
        cheatButton.setLayoutY(201);
        cheatButton.setButtonType(JFXButton.ButtonType.RAISED);
        cheatButton.setPrefHeight(40);
        cheatButton.setPrefWidth(139);
        cheatButton.setText("Cheat");
        cheatButton.setRipplerFill(Color.rgb(231,99,99));
        cheatButton.setTextAlignment(TextAlignment.CENTER);
        cheatButton.setTextFill(Color.rgb(215,206,206));
        cheatButton.setOnMouseClicked(e -> {
            handleCommand(commands, cheatInput.getText());
        });
        background.getChildren().add(cheatButton);
    }


    public void handleCommand(HashMap<CommandAction, Commands> commands, String input){
        HashMap<String, String> result;
        for(Map.Entry<CommandAction, Commands> set: commands.entrySet()){
            if((result = CommandProcessor.extractCommand(input, set.getValue()))!=null){
                new PopUp().run(PopUpStates.WARNING,set.getKey().action(result));
                System.out.println("cheattts");
                gameMapController.update();
                return;
            }
        }
    }
}
