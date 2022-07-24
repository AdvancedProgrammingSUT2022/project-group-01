package view.components.preMainInMenus;

import com.jfoenix.controls.JFXScrollPane;
import controller.GUIController;
import controller.ProgramController;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
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
import model.Database;
import model.User;
import view.Main;
import view.components.ImagesAddress;

import java.util.Comparator;
import java.util.Vector;

public class ScoreBoardMenu {

    public ImageView scoreBoardImage;
    public ImageView image;
    public VBox list;
    public Pane wholePane;
    public ScrollPane scrollPane;


    public void initialize(){
        wholePane.setStyle("-fx-background-image: url(asset/other/scoreBoard/scoreBoardBackground.png)");
        list.setSpacing(20);
        list.setStyle("-fx-background-color: transparent;-fx-background: transparent");
        scoreBoardImage.setImage(ImagesAddress.SCORE_BOARD_TEXT_IMAGE.getImage());
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setStyle("-fx-background: transparent");
        scrollPane.setStyle("-fx-background-color: transparent");
        scrollPane.getStylesheets().add(String.valueOf(Main.class.getResource("/CSS/InvitationMenu.css")));
        addUsers();
        closeButton();
    }

    private void addUsers(){
        Vector<User> users = ProgramController.getDatabase().getUsers();
        users.sort((o1, o2) -> {
            if (o1.getScore() > o2.getScore()) {
                return -1;
            } else if (o1.getScore() < o2.getScore()) {
                return 1;
            } else {
                if (o2.getLastWinDate() == null)
                    return -1;
                if (o1.getLastWinDate() == null)
                    return 1;
                if (o1.getLastWinDate().after(o2.getLastWinDate())) {
                    return -1;
                }else{
                    return 1;
                }
            }
        });
        for(User u : users){
            addToList(u);
        }
    }

    private void addToList(User u){
        Pane p = new Pane();
        p.setPrefHeight(85);
        p.setPrefWidth(596);
        if(u.equals(ProgramController.getLoggedInUser()))
            p.setStyle("-fx-background-color: #f3c5de");
        ImageView userPart = new ImageView(ImagesAddress.SCORE_BOARD_ITEM_USER.getImage());
        userPart.setFitWidth(200);
        userPart.setFitHeight(150);
        userPart.setLayoutX(14);
        userPart.setLayoutY(14);
        userPart.setPickOnBounds(true);
        userPart.setPreserveRatio(true);
        p.getChildren().add(userPart);
        ImageView lastOnlinePart = new ImageView(ImagesAddress.SCORE_BOARD_LAST_VISIT_BOX.getImage());
        lastOnlinePart.setFitWidth(200);
        lastOnlinePart.setFitHeight(150);
        lastOnlinePart.setLayoutX(386);
        lastOnlinePart.setLayoutY(11);
        lastOnlinePart.setPickOnBounds(true);
        lastOnlinePart.setPreserveRatio(true);
        p.getChildren().add(lastOnlinePart);
        ImageView scorePart = new ImageView(ImagesAddress.GRAY_BUTTON.getImage());
        scorePart.setFitWidth(200);
        scorePart.setFitHeight(150);
        scorePart.setLayoutX(200);
        scorePart.setLayoutY(7);
        scorePart.setPickOnBounds(true);
        scorePart.setPreserveRatio(true);
        p.getChildren().add(scorePart);
        addLabels(u,p);
        addUserAvatar(u,p);
        list.getChildren().add(p);
    }

    private void addLabels(User u, Pane p){
        Label userNickName = new Label();
        userNickName.setLayoutX(78);
        userNickName.setLayoutY(25);
        userNickName.setPrefHeight(37);
        userNickName.setPrefWidth(108);
        userNickName.setText(u.getNickname());
        userNickName.setFont(Font.font("AppleGothic Regular",15));
        userNickName.setTextFill(Color.BLACK);
        p.getChildren().add(userNickName);

        Label userScore = new Label();
        userScore.setLayoutX(230);
        userScore.setLayoutY(26);
        userScore.setPrefHeight(37);
        userScore.setPrefWidth(129);
        userScore.setText(String.valueOf(u.getScore()));
        userScore.setFont(Font.font("AppleGothic Regular",24));
        userScore.setAlignment(Pos.CENTER);
        userScore.setTextAlignment(TextAlignment.CENTER);
        userScore.setTextFill(Color.ANTIQUEWHITE);
        p.getChildren().add(userScore);

        Label userLastOnline = new Label();
        userLastOnline.setLayoutX(400);
        userLastOnline.setLayoutY(26);
        userLastOnline.setPrefHeight(29);
        userLastOnline.setPrefWidth(168);
        userLastOnline.setText("undefined");
        userLastOnline.setFont(Font.font("AppleGothic Regular",15));
        userLastOnline.setTextAlignment(TextAlignment.CENTER);
        userLastOnline.setAlignment(Pos.CENTER);
        userLastOnline.setTextFill(Color.ANTIQUEWHITE);
        p.getChildren().add(userLastOnline);
    }

    private void addUserAvatar(User u, Pane p){
        Circle circle = new Circle();
        circle.setLayoutX(43);
        circle.setLayoutY(43);
        circle.setRadius(25);
        circle.setFill(new ImagePattern(new Image(u.getAvatarUrl())));
        circle.setStroke(Color.BLACK);
        circle.setStrokeType(StrokeType.INSIDE);
        p.getChildren().add(circle);
    }

    private void closeButton(){
        ImageView close = new ImageView(ImagesAddress.INFO_CLOSE.getImage());
        close.setLayoutX(10);
        close.setPickOnBounds(true);
        close.setPreserveRatio(true);
        close.setLayoutY(10);
        close.setFitHeight(34);
        close.setFitWidth(34);
        wholePane.getChildren().add(close);
        close.setOnMouseClicked(e -> {
            GUIController.changeMenu("preMainMenu");
        });
    }
}
