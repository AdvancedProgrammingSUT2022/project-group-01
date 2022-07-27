package view.components;

import javafx.scene.control.Label;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Font;
import model.GameInstantiateData;
import model.User;

public class FindUserItem {
    private Pane root = new Pane();
    private User user;
    private Polygon background;
    private ImageView mailButton;
    private GameInstantiateData gameData;
    public FindUserItem(User user,GameInstantiateData gameInstantiateData) {
        this.user = user;
        gameData = gameInstantiateData;
        root.prefHeight(80);
        root.prefWidth(790);
        background = new Polygon(-68.5,-29,-27,1,660,1,703,-29,660,-65,-27,-65);
        background.setStroke(Color.color(1,0.5,0.5,1));
        background.setStrokeWidth(1);
        background.setEffect(new GaussianBlur());
        background.setFill(Color.color(0,0,0,1));
        background.setLayoutX(78);
        background.setLayoutY(72);
        root.getChildren().add(background);
        setAvatarCircle();
        setName();
        invitationButtonSetup();
    }

    private void setAvatarCircle(){
        Circle circle = new Circle();
        circle.setFill(new ImagePattern(new Image(user.getAvatarUrl())));
        circle.setLayoutX(81);
        circle.setLayoutY(40);
        circle.setRadius(25);
        circle.setStroke(Color.BLACK);
        circle.setStrokeType(StrokeType.INSIDE);
        root.getChildren().add(circle);
    }

    private void setName(){
        Label name = new Label();
        name.setLayoutX(127);
        name.setLayoutY(23);
        name.setPrefHeight(34);
        name.setPrefWidth(296);
        name.setText(user.getUsername());
        name.setTextFill(Color.WHITE);
        name.setFont(Font.font("Apple Braille Outline 6 dot",22));
        root.getChildren().add(name);
    }

    private void invitationButtonSetup(){
        mailButton = new ImageView(ImagesAddress.MAIL.getImage());
        if(gameData.userIsInvited(user) || gameData.isUserInGame(user)){
            mailButton.setImage(ImagesAddress.MAILED.getImage());
            background.setStroke(Color.GREEN);
        }
        mailButton.setFitHeight(34);
        mailButton.setFitWidth(50);
        mailButton.setLayoutX(614);
        mailButton.setLayoutY(30);
        //mailButton.setPickOnBounds(true);
        mailButton.setPreserveRatio(true);
        mailButton.setOnMouseClicked(e -> sendInvitationButton());
        root.getChildren().add(mailButton);
    }

    private void sendInvitationButton(){
        if(gameData.userIsInvited(user) || gameData.isUserInGame(user)) return;
        background.setStroke(Color.GREEN);
        gameData.invitePlayer(user);
        mailButton.setImage(ImagesAddress.MAILED.getImage());
    }

    public Pane getRoot(){
        return root;
    }
}
