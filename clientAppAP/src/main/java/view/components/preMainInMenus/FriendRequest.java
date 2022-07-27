package view.components.preMainInMenus;

import controller.ProgramController;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.geometry.Pos;
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
import javafx.util.Duration;
import model.User;
import view.components.ImagesAddress;


public class FriendRequest {
    private User sender;
    private Pane root;
    private User receiver;

    public FriendRequest(User receiver) {
        this.receiver = receiver;
        initialize();
    }

    private void initialize(){
        initRoot();
        if(receiver.getPendingFriends().size() > 0){
            sender = ProgramController.getDatabase().findUserByUsername(receiver.getPendingFriends().get(0));
            update();
        }
    }

    public Pane getRoot(){
        return root;
    }

    private void initRoot(){
        root = new Pane();
        root.setPrefHeight(454);
        root.setPrefWidth(347);
        root.setStyle("-fx-background-color: transparent");
    }

    private void initBackImage(){
        ImageView backImage = new ImageView(ImagesAddress.FRIEND_REQUEST.getImage());
        backImage.setFitHeight(446);
        backImage.setFitWidth(347);
        backImage.setLayoutX(3);
        backImage.setLayoutY(4);
        backImage.setPickOnBounds(true);
        backImage.setPreserveRatio(true);
        root.getChildren().add(backImage);
    }

    private void update(){
        initBackImage();
        initUserImageRing();
        initUserImage();
        addName();
        addAccept();
        addDecline();
    }

    private void initUserImageRing(){
        ImageView userImageRing = new ImageView(ImagesAddress.CIRCLE_RING.getImage());
        userImageRing.setFitHeight(96);
        userImageRing.setFitWidth(105);
        userImageRing.setLayoutX(126);
        userImageRing.setLayoutY(65);
        userImageRing.setPickOnBounds(true);
        userImageRing.setPreserveRatio(true);
        root.getChildren().add(userImageRing);
    }
    private void initUserImage(){
        Circle userImage = new Circle();
        userImage.setLayoutX(173);
        userImage.setLayoutY(113);
        userImage.setRadius(37);
        userImage.setStroke(Color.BLACK);
        userImage.setStrokeType(StrokeType.INSIDE);
        userImage.setFill(new ImagePattern(ImagesAddress.getImageByNameInEnum(sender.getAvatarUrl()).getImage()));
        root.getChildren().add(userImage);
    }

    private void addName(){
        Label name = new Label(sender.getNickname());
        name.setAlignment(Pos.CENTER);
        name.setTextAlignment(TextAlignment.CENTER);
        name.setLayoutX(111);
        name.setLayoutY(185);
        name.setPrefHeight(35);
        name.setPrefWidth(125);
        name.setTextFill(Color.rgb(207,210,229));
        name.setFont(Font.font("Avenir Book",28));
        root.getChildren().add(name);
    }

    private void addAccept(){
        Circle circle = new Circle();
        circle.setLayoutX(82);
        circle.setLayoutY(273);
        circle.setRadius(29);
        circle.setFill(Color.rgb(241,215,47));
        circle.setOpacity(0);
        circle.setOnMouseClicked(e ->{
            Timeline timeline = new Timeline(new KeyFrame(Duration.millis(300),e2->{
                circle.setOpacity(0.6);
            }));
            timeline.setCycleCount(1);
            timeline.setOnFinished(e3 -> {acceptRequest();});;
            timeline.play();
        });
        root.getChildren().add(circle);
    }

    private void addDecline(){
        Circle circle = new Circle();
        circle.setLayoutX(263);
        circle.setLayoutY(273);
        circle.setRadius(29);
        circle.setFill(Color.rgb(236,51,79));
        circle.setOpacity(0);
        circle.setOnMouseClicked(e ->{
            Timeline timeline = new Timeline(new KeyFrame(Duration.millis(300),e2->{
                circle.setOpacity(0.6);
            }));
            timeline.setCycleCount(1);
            timeline.setOnFinished(e3 -> {declineRequest();});;
            timeline.play();
        });
        root.getChildren().add(circle);
    }

    private void acceptRequest(){
        receiver.addFriend(sender);
        sender.addFriend(receiver);
        goToNext();
    }

    private void declineRequest(){
        receiver.removeRequest(sender);
        goToNext();
    }

    private void goToNext(){
        TranslateTransition transition = new TranslateTransition(Duration.millis(500),root);
        transition.setToY(900);
        transition.setOnFinished(e -> {
            if(receiver.getPendingFriends().size() > 0){
                sender = ProgramController.getDatabase().findUserByUsername(receiver.getPendingFriends().get(0));
                root.getChildren().clear();
                update();
                TranslateTransition translateTransition = new TranslateTransition(Duration.millis(500),root);
                translateTransition.setToY(0);
                translateTransition.play();
            }
            else {
                root.getChildren().clear();
            }
        });
        transition.play();
    }

}
