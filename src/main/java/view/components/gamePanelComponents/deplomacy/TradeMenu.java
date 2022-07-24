package view.components.gamePanelComponents.deplomacy;

import com.jfoenix.controls.JFXButton;
import controller.GUIController;
import controller.GameMenuController;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import lombok.Getter;
import model.civilization.Civilization;
import model.civilization.Trade;
import model.civilization.TradeList;
import model.resource.KindsOfResource;
import model.resource.ResourceType;
import view.Main;
import view.components.ImagesAddress;
import view.components.mapComponents.GameMapController;
import view.components.popup.PopUp;
import view.components.popup.PopUpStates;

import java.util.HashMap;
import java.util.Vector;

public class TradeMenu {
    private GameMenuController gameMenuController;
    @Getter
    private Civilization user;
    @Getter
    private Civilization opponent;
    private Pane backPane;
    private Pane sidePane;
    private ScrollPane offerScrollPane;
    private VBox offerVBox;
    private ScrollPane demandScrollPane;
    private VBox demandVBox;
    private JFXButton sendButton;
    private Trade selectedTrade;
    private Vector<TradeOffer> trades = new Vector<>();
    private HashMap<Object,Spinner<Integer>> correspondingSpinners;
    private boolean makingOffer = true;
    private GameMapController gmp;

    public TradeMenu(Civilization opponent, GameMenuController gameMenuController, GameMapController gmp) {
        user = gameMenuController.getGame().getCurrentPlayer().getCivilization(); //TODO change in server mode to logged in user ...........
        this.gmp = gmp;
        this.gameMenuController = gameMenuController;
        this.opponent = opponent;
        initialize();
    }

    private void initialize(){
        backPane = new Pane();
        backPane.setPrefWidth(1280);
        backPane.setPrefHeight(720);
        backPane.setStyle("-fx-background-image: url(asset/other/friendsBackground.jpg)");
        sidePane = new Pane();
        sidePane.setPrefWidth(372);
        sidePane.setPrefHeight(720);
        sidePane.setStyle("-fx-background-color: transparent");
        backPane.getChildren().add(sidePane);
        sidePaneStartingInitialize();
        makingOfferList();
        makingDemandPane();
        fillDemandVBox();
        closeButton();
    }

    public Pane getBackPane(){
        return backPane;
    }

    private void sidePaneStartingInitialize(){
        offerScrollPane = new ScrollPane();
        offerScrollPane.setLayoutX(33);
        offerScrollPane.setLayoutY(189);
        offerScrollPane.setPrefWidth(312);
        offerScrollPane.setPrefHeight(465);
        offerScrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        offerScrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        offerScrollPane.setStyle("-fx-background: transparent");
        offerScrollPane.setStyle("-fx-background-color: transparent");
        offerScrollPane.getStylesheets().add(String.valueOf(Main.class.getResource("/CSS/InvitationMenu.css")));
        backPane.getChildren().add(offerScrollPane);
        offerVBox = new VBox();
        offerVBox.setStyle("-fx-background: transparent");
        offerVBox.setStyle("-fx-background-color: transparent");
        offerVBox.setPrefWidth(310);
        offerScrollPane.setContent(offerVBox);
        //avatar circle
        Circle avatarCircle = new Circle();
        avatarCircle.setLayoutX(186);
        avatarCircle.setLayoutY(68);
        avatarCircle.setRadius(45);
        avatarCircle.setStroke(Color.BLACK);
        avatarCircle.setStrokeType(StrokeType.INSIDE);
        avatarCircle.setFill(new ImagePattern(new Image(user.getPlayer().getUser().getAvatarUrl())));
        sidePane.getChildren().add(avatarCircle);
        //name label
        Label name = new Label();
        name.setLayoutX(33);
        name.setLayoutY(140);
        name.setPrefHeight(26);
        name.setPrefWidth(312);
        name.setTextFill(Color.GHOSTWHITE);
        name.setFont(Font.font("Verdana", 20));
        name.setTextAlignment(TextAlignment.CENTER);
        name.setText(user.getCivilization().getName());
        sidePane.getChildren().add(name);

        // offer button
        sendButton = new JFXButton();
        sendButton.setLayoutX(33);
        sendButton.setLayoutY(674);
        sendButton.setPrefWidth(312);
        sendButton.setPrefHeight(26);
        sendButton.setTextFill(Color.GHOSTWHITE);
        sendButton.setFont(Font.font("Verdana", 20));
        sendButton.setTextAlignment(TextAlignment.CENTER);
        sendButton.setText("Send");
        sidePane.getChildren().add(sendButton);
        sendButton.setOnMouseClicked(e ->{
            doAction();
        });
    }

    private void makingOfferList(){
        correspondingSpinners = new HashMap<>();
        for(TradeList t : TradeList.values()){
            offerVBox.getChildren().add(opponentSourceItem(t));
        }
        for(ResourceType r : ResourceType.values()){
            if(r.resourceKind.equals(KindsOfResource.BONUS)) continue;
            offerVBox.getChildren().add(opponentSourceItem(r));
        }
        offerVBox.getChildren().add(opponentSourceItem(new StringBuilder("turns")));
    }

    private Pane opponentSourceItem(Object o){
        Pane pane = new Pane();
        pane.setPrefHeight(60);
        pane.setPrefWidth(310);
        pane.setStyle("-fx-background: transparent");
        pane.setStyle("-fx-background-color: transparent");

        Label name = new Label();
        name.setLayoutX(14);
        name.setLayoutY(4);
        name.setPrefHeight(53);
        name.setPrefWidth(181);
        name.setFont(Font.font("AppleGothic Regular",18));
        name.setTextFill(Color.GHOSTWHITE);
        name.setText(String.valueOf(o));
        Spinner<Integer> spinner = new Spinner<Integer>();
        SpinnerValueFactory<Integer> svf = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,100);
        svf.setValue(0);
        spinner.setValueFactory(svf);
        spinner.setLayoutX(211);
        spinner.setLayoutY(18);
        spinner.setPrefHeight(26);
        spinner.setPrefWidth(85);
        pane.getChildren().add(name);
        pane.getChildren().add(spinner);
        correspondingSpinners.put(o,spinner);
        return pane;
    }


    private void doAction(){
        for(Object o : correspondingSpinners.keySet()){
            if(Integer.parseInt(correspondingSpinners.get(o).getValue().toString()) < 0){
                new PopUp().run(PopUpStates.WARNING,"You must offer at least 0 of each resource");
                return;
            }
        }
        if(!checkIfCanPay(opponent)){
            new PopUp().run(PopUpStates.WARNING,"Opponent can't afford this trade");
            return;
        }

        if(makingOffer){
            Trade trade = new Trade(user,opponent);
            for(Object o : correspondingSpinners.keySet()){
                if(o instanceof ResourceType){
                    ResourceType r = (ResourceType) o;
                    int amount =  correspondingSpinners.get(o).getValue();
                    if(amount > 0) trade.addToSecondOffer(o,amount);
                }else if(o instanceof TradeList){
                    TradeList t = (TradeList) o;
                    int amount = correspondingSpinners.get(o).getValue();
                    if(amount > 0) trade.addToSecondOffer(t,amount);
                }else if(o instanceof Integer){
                    int amount = (Integer) o;
                    if(amount > 0) trade.setTime(amount);
                }
            }
            trade.setBuildingState(1);
            opponent.addToReceivedTradeRequests(trade);
        }else{
            Trade trade = selectedTrade;
            if(trade.getBuildingState() == 1 && trade.getSecond().equals(user)) {
                for (Object o : correspondingSpinners.keySet()) {
                    if (o instanceof ResourceType) {
                        ResourceType r = (ResourceType) o;
                        int amount = correspondingSpinners.get(o).getValue();
                        if (amount > 0) trade.addToFirstOffer(o, amount);
                    } else if (o instanceof TradeList) {
                        TradeList t = (TradeList) o;
                        int amount = correspondingSpinners.get(o).getValue();
                        if (amount > 0) trade.addToFirstOffer(t, amount);
                    } else if (o instanceof Integer) {
                        int amount = (Integer) o;
                        if (amount > 0) trade.setTime(amount);
                    }
                }
                trade.setBuildingState(2);
                opponent.addToReceivedTradeRequests(trade);
            }
            makingOffer = true;
        }
    }

    private boolean checkIfCanPay(Civilization a){
        for(Object o : correspondingSpinners.keySet()){
            System.out.println(o);
           if(o instanceof TradeList){
                TradeList t = (TradeList) o;
                if(t.equals(TradeList.GOLD)){
                     if(correspondingSpinners.get(o).getValue() > a.getCurrency().getGold()) return false;
                }else if(t.equals(TradeList.FOOD)){
                    if(correspondingSpinners.get(o).getValue() > a.getCurrency().getFood()) return false;
                }else if(t.equals(TradeList.PRODUCTION)){
                    if(correspondingSpinners.get(o).getValue() > a.getCurrency().getProduct()) return false;
                }
           }else if(o instanceof ResourceType){
               ResourceType r = (ResourceType) o;
               if(r.resourceKind.equals(KindsOfResource.BONUS)) continue;
               if(correspondingSpinners.get(o).getValue() == 0) continue;
               if(a.getResourceRepository().get(r) == null) return false;
               if(correspondingSpinners.get(o).getValue() > a.getResourceRepository().get(r)) return false;
           }
        }
        return true;
    }


    private void makingDemandPane(){
        demandScrollPane = new ScrollPane();
        demandScrollPane.setLayoutX(400);
        demandScrollPane.setLayoutY(52);
        demandScrollPane.setPrefHeight(640);
        demandScrollPane.setPrefWidth(707);
        demandScrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        demandScrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        demandScrollPane.setStyle("-fx-background: transparent");
        demandScrollPane.setStyle("-fx-background-color: transparent");
        demandScrollPane.getStylesheets().add(String.valueOf(Main.class.getResource("/CSS/InvitationMenu.css")));
        demandVBox = new VBox();
        demandVBox.setStyle("-fx-background-color: transparent");
        demandVBox.setStyle("-fx-background: transparent");
        demandVBox.setPrefWidth(703);
        demandScrollPane.setContent(demandVBox);
    }

    private void fillDemandVBox(){
        for(Trade t : user.getTrades()){
            addTradesFromVector(t, t.getSecond(), t.getFirst());
        }
        for(Trade t : user.getReceivedTradeRequests()){
            addTradesFromVector(t, t.getFirst(), t.getSecond());
        }
        for(Trade t : opponent.getReceivedTradeRequests()){
            if(t.getSecond().equals(user) || t.getFirst().equals(user)){
                TradeOffer a = new TradeOffer(t,demandVBox,this);
                trades.add(a);
                demandVBox.getChildren().add(a.getPane());
                a.getBackBox().setOnMouseClicked(e ->{
                    mouseClickAction(a);
                });
            }
        }
    }

    private void addTradesFromVector(Trade t, Civilization second, Civilization first) {
        if(second.equals(opponent) || first.equals(opponent)){
            TradeOffer a = new TradeOffer(t,demandVBox,this);
            trades.add(a);
            demandVBox.getChildren().add(a.getPane());
            a.getBackBox().setOnMouseClicked(e ->{
                mouseClickAction(a);
            });
        }
    }

    private void mouseClickAction(TradeOffer s){
        if(s.getTrade().getBuildingState() == 1){
            if(s.getTrade().getFirst().equals(opponent)){
                makingOffer = false;
                selectedTrade = s.getTrade();
            }
        }
    }

    private void closeButton(){
        ImageView close = new ImageView(ImagesAddress.INFO_CLOSE.getImage());
        close.setFitHeight(34);
        close.setFitWidth(34);
        close.setLayoutX(20);
        close.setLayoutY(20);
        backPane.getChildren().add(close);
        close.setOnMouseClicked(e -> {
            GUIController.changeMenuManually(gmp.getBackground());
        });
    }

    private void update(){
        demandVBox.getChildren().clear();
        selectedTrade = null;
        makingOffer = true;
        fillDemandVBox();
    }



}
