package view.components.infoPanels.democracy;

import com.jfoenix.controls.JFXButton;
import controller.GUIController;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import model.Game;
import model.Player;
import model.civilization.Civilization;
import view.components.ImagesAddress;
import view.components.gamePanelComponents.deplomacy.TradeMenu;
import view.components.mapComponents.GameMapController;

import java.util.HashMap;

public class DemocracyMainPanel {
    private Pane pane;
    private VBox civList;
    private GameMapController gmp;
    private Pane mainPane;
    private Civilization chosenCivilization;
    private HashMap<JFXButton,Civilization> civButtonCorrespondence = new HashMap<>();
    private void initBack(){
        pane = new Pane();
        pane.setPrefHeight(720);
        pane.setPrefWidth(1280);
        pane.setStyle("-fx-background-image: url(asset/other/friendsBackground.jpg)");
    }

    public DemocracyMainPanel(GameMapController gmp) {
        this.gmp = gmp;
        init();
    }

    public Pane getPane(){
        return pane;
    }

    private void init(){
        initBack();
        sidePane();
        addCivilizations();
        mainPane();
        closeButton();
    }
    private void sidePane(){
        Pane sidePane = new Pane();
        sidePane.setPrefHeight(720);
        sidePane.setPrefWidth(279);
        pane.getChildren().add(sidePane);

        ScrollPane civScroll = new ScrollPane();
        civScroll.setLayoutX(14);
        civScroll.setLayoutY(91);
        civScroll.setPrefHeight(609);
        civScroll.setPrefWidth(253);
        civScroll.setStyle("-fx-background-color: transparent; -fx-background: transparent");
        civScroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        civScroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        sidePane.getChildren().add(civScroll);

        civList = new VBox();
        civList.setPrefWidth(250);
        civList.setStyle("-fx-background-color: transparent; -fx-background: transparent");
        civScroll.setContent(civList);
    }

    private void addCivilizations(){
        Game g = gmp.getGameMenuController().getGame();
        for(Player p : g.getPlayers()){
            if(p.equals(gmp.getGameMenuController().getGame().getCurrentPlayer())) continue;
            JFXButton j = new JFXButton(p.getCivilization().getCivilization().getName());
            j.setTextFill(Color.WHITESMOKE);
            j.setFont(Font.font("Avenir Book",17));
            civButtonCorrespondence.put(j,p.getCivilization());
            civList.getChildren().add(j);
            j.setOnMouseClicked(e -> {
                addChosenCivMenu(civButtonCorrespondence.get(j));
                chosenCivilization = civButtonCorrespondence.get(j);
            });
        }
    }

    private void mainPane(){
        mainPane = new Pane();
        mainPane.setLayoutX(319);
        mainPane.setLayoutY(14);
        mainPane.setPrefHeight(687);
        mainPane.setPrefWidth(933);
        pane.getChildren().add(mainPane);
    }

    private void addChosenCivMenu(Civilization c){
        mainPane.getChildren().clear();
        JFXButton war = new JFXButton("WAR");
        war.setButtonType(JFXButton.ButtonType.RAISED);
        war.setLayoutX(375);
        war.setLayoutY(120);
        war.setPrefHeight(26);
        war.setPrefWidth(172);
        war.setFont(Font.font("Avenir Book",17));
        war.setTextFill(Color.WHITESMOKE);
        mainPane.getChildren().add(war);
        war.setOnMouseClicked(e -> {
            //TODO with safar
        });
        JFXButton peace = new JFXButton("PEACE");
        peace.setButtonType(JFXButton.ButtonType.RAISED);
        peace.setLayoutX(375);
        peace.setLayoutY(240);
        peace.setPrefHeight(26);
        peace.setPrefWidth(172);
        peace.setFont(Font.font("Avenir Book",17));
        peace.setTextFill(Color.WHITESMOKE);
        peace.setOnMouseClicked(e -> {
            //TODO with safar
        });
        mainPane.getChildren().add(peace);
        JFXButton trade = new JFXButton("TRADE/DEMAND");
        trade.setButtonType(JFXButton.ButtonType.RAISED);
        trade.setLayoutX(375);
        trade.setLayoutY(360);
        trade.setPrefHeight(26);
        trade.setPrefWidth(172);
        trade.setFont(Font.font("Avenir Book",17));
        trade.setTextFill(Color.WHITESMOKE);
        trade.setOnMouseClicked(e -> {
            GUIController.changeMenuManually(new TradeMenu(chosenCivilization,gmp.getGameMenuController(),gmp).getBackPane());
        });
        mainPane.getChildren().add(trade);
        JFXButton discuss = new JFXButton("DISCUSS");
        discuss.setButtonType(JFXButton.ButtonType.RAISED);
        discuss.setLayoutX(375);
        discuss.setLayoutY(480);
        discuss.setPrefHeight(26);
        discuss.setPrefWidth(172);
        discuss.setFont(Font.font("Avenir Book",17));
        discuss.setTextFill(Color.WHITESMOKE);
        discuss.setOnMouseClicked(e -> {
            //TODO merge with amnam
        });
        mainPane.getChildren().add(discuss);
    }

    private void closeButton(){
        ImageView close = new ImageView(ImagesAddress.INFO_CLOSE.getImage());
        close.setFitHeight(34);
        close.setFitWidth(34);
        close.setLayoutX(20);
        close.setLayoutY(20);
        pane.getChildren().add(close);
        close.setOnMouseClicked(e -> {
            gmp.getBackground().getChildren().remove(pane);
        });
    }
}
