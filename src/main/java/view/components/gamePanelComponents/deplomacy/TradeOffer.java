package view.components.gamePanelComponents.deplomacy;

import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import lombok.Getter;
import model.civilization.Trade;
import view.Main;
import view.components.ImagesAddress;
@Getter
public class TradeOffer {
    private Pane pane = new Pane();
    private Trade trade;
    private ImageView backImage;
    private VBox firstOffer;
    private VBox secondOffer;
    private VBox backBox;
    private TradeMenu tradeMenu;

    public TradeOffer(Trade trade, VBox backBox,TradeMenu m) {
        this.tradeMenu = m;
        this.trade = trade;
        this.backBox = backBox;
        preInit();
        initScrollPanes();
        initForLabel();
        showOffer();
        buttons();
    }

    public Pane getPane() {
        return pane;
    }

    private void preInit(){
        pane = new Pane();
        pane.setPrefWidth(400);
        pane.setPrefHeight(274);
        backImage = new ImageView(ImagesAddress.TRADE_OFFER_BOX.getImage());
        backImage.setFitHeight(277);
        backImage.setFitWidth(401);
        backImage.setPickOnBounds(true);
        backImage.setPreserveRatio(true);
        pane.getChildren().add(backImage);
    }
    private void initScrollPanes(){
        ScrollPane firstScrollPane = new ScrollPane();
        firstScrollPane.setLayoutX(14);
        firstScrollPane.setLayoutY(12);
        firstScrollPane.setPrefWidth(164);
        firstScrollPane.setPrefHeight(180);
        firstScrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        firstScrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        firstScrollPane.setStyle("-fx-background: transparent");
        firstScrollPane.setStyle("-fx-background-color: transparent");
        firstScrollPane.getStylesheets().add(String.valueOf(Main.class.getResource("/CSS/InvitationMenu.css")));
        pane.getChildren().add(firstScrollPane);
        ScrollPane secondScrollPane = new ScrollPane();
        secondScrollPane.setLayoutX(222);
        secondScrollPane.setLayoutY(12);
        secondScrollPane.setPrefWidth(164);
        secondScrollPane.setPrefHeight(180);
        secondScrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        secondScrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        secondScrollPane.setStyle("-fx-background: transparent");
        secondScrollPane.setStyle("-fx-background-color: transparent");
        secondScrollPane.getStylesheets().add(String.valueOf(Main.class.getResource("/CSS/InvitationMenu.css")));
        pane.getChildren().add(secondScrollPane);
        firstOffer = new VBox();
        firstOffer.setPrefWidth(139);
        firstOffer.setStyle("-fx-background: transparent");
        firstOffer.setStyle("-fx-background-color: transparent");
        firstScrollPane.setContent(firstOffer);
        secondOffer = new VBox();
        secondOffer.setPrefWidth(156);
        secondOffer.setStyle("-fx-background: transparent");
        secondOffer.setStyle("-fx-background-color: transparent");
        secondScrollPane.setContent(secondOffer);
    }

    private void initForLabel(){
        Label label = new Label();
        label.setText("for");
        label.setLayoutX(184);
        label.setLayoutY(84);
        label.setPrefHeight(35);
        label.setPrefWidth(33);
        label.setFont(new Font("AppleGothic Regular", 22));
        label.setTextFill(Color.WHITE);
        pane.getChildren().add(label);
    }

    private void showOffer(){
        firstOffer.getChildren().clear();
        secondOffer.getChildren().clear();
        System.err.println("we are showing the offers but you may be blind");
        if(trade.getBuildingState() >= 1) {
            for (Object o : trade.getSecondOffer().keySet()) {
                Label l = new Label();
                l.setFont(Font.font("AppleGothic Regular", 14));
                l.setTextFill(Color.WHITESMOKE);
                l.setText(o + ": " + trade.getSecondOffer().get(o));
                firstOffer.getChildren().add(l);
            }
        }
        if(trade.getBuildingState() >= 2){
            for (Object o : trade.getFirstOffer().keySet()) {
                Label l = new Label();
                l.setFont(Font.font("AppleGothic Regular", 14));
                l.setTextFill(Color.WHITESMOKE);
                l.setText(o + ": " + trade.getFirstOffer().get(o));
                secondOffer.getChildren().add(l);
            }
        }
    }

    private void buttons(){
        if(trade.getBuildingState() == 1 && trade.getSecond().equals(tradeMenu.getUser())){
            addDiscardButton();
        }
        else if(trade.getBuildingState() == 2 && trade.getFirst().equals(tradeMenu.getUser()) && !trade.isDemand()){
            addDiscardButton();
            addAcceptButton();
        }else if(trade.getBuildingState() == 2 && trade.isDemand() && trade.getSecond().equals(tradeMenu.getUser())){
            addDiscardButton();
            addAcceptButton();
        }
    }

    private void addDiscardButton(){
        ImageView discardButton = new ImageView();
        discardButton.setFitHeight(80);
        discardButton.setFitWidth(80);
        discardButton.setLayoutX(304);
        discardButton.setLayoutY(203);
        discardButton.setPreserveRatio(true);
        discardButton.setPickOnBounds(true);
        discardButton.setImage(ImagesAddress.DECLINE_TRADE.getImage());
        discardButton.setOnMouseClicked(e -> {
            trade.decline();
            close();
        });
        pane.getChildren().add(discardButton);
    }

    private void addAcceptButton(){
        ImageView acceptButton = new ImageView();
        acceptButton.setFitHeight(80);
        acceptButton.setFitWidth(80);
        acceptButton.setLayoutX(41);
        acceptButton.setLayoutY(203);
        acceptButton.setPreserveRatio(true);
        acceptButton.setPickOnBounds(true);
        acceptButton.setImage(ImagesAddress.ACCEPT_TRADE.getImage());
        acceptButton.setOnMouseClicked(event -> {
            trade.setBuildingState(3);
            trade.addToUsers();
            close();
        });
        pane.getChildren().add(acceptButton);
    }

    private void close(){
        backBox.getChildren().remove(this.pane);
    }


}
