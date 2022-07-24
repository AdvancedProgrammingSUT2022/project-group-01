package view.components.preMainInMenus;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import model.User;
import view.components.ImagesAddress;


public class FriendItem {
    private User user;
    private Pane root;


    public FriendItem(User user) {
        this.user = user;
        initialize();
    }

    private void initialize(){
        root = new Pane();
        root.setPrefHeight(116);
        root.setPrefWidth(429);
        root.setStyle("-fx-background-color: transparent");
        initBackImage();
        avatarCircle();
        addName();
    }

    public Pane getRoot(){
        return root;
    }

    private void initBackImage(){
        ImageView backImage = new ImageView(ImagesAddress.FRIEND_ITEM.getImage());
        backImage.setFitHeight(137);
        backImage.setFitWidth(427);
        backImage.setLayoutY(-10);
        backImage.setPickOnBounds(true);
        backImage.setPreserveRatio(true);
        root.getChildren().add(backImage);
    }

    private void avatarCircle(){
        Circle avatarCircle = new Circle();
        avatarCircle.setLayoutX(343);
        avatarCircle.setLayoutY(54);
        avatarCircle.setRadius(31);
        avatarCircle.setFill(new ImagePattern(new Image(user.getAvatarUrl())));
        avatarCircle.setStroke(Color.BLACK);
        avatarCircle.setStrokeType(StrokeType.INSIDE);
        root.getChildren().add(avatarCircle);
    }

    private void addName(){
        Label name = new Label(user.getNickname());
        name.setLayoutX(110);
        name.setLayoutY(23);
        name.setPrefHeight(35);
        name.setPrefWidth(180);
        name.setTextAlignment(TextAlignment.CENTER);
        name.setFont(Font.font("Avenir Book", 21));
        root.getChildren().add(name);
    }

}
