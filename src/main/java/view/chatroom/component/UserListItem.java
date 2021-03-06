package view.chatroom.component;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import lombok.Getter;
import model.User;
import view.chatroom.SelectMemberDialog;

@Getter
public class UserListItem {
    private Pane pane;
    private Rectangle rectangle;
    private Circle circle;
    private final User user;
    public UserListItem(User user, SelectMemberDialog selectMemberDialog){
        pane = new Pane();
        rectangle = new Rectangle(360,60);
        pane.setMinWidth(363);
        pane.setMinHeight(60);
        this.user = user;
        initRectangle();
        initAvatar();
        initLabel();
        initCheckBox();
        initAction(selectMemberDialog);
    }

    private void initRectangle(){
        rectangle.setStyle("-fx-background-radius: 20; -fx-border-radius: 20");
        pane.getChildren().add(rectangle);
    }

    private void initLabel(){
        Label nickname = new Label(user.getNickname());
        nickname.setTextFill(Color.WHITE);
        nickname.setFont(Font.font("Apple Braille Outline 6 dot",22));
        nickname.setLayoutX(70);
        nickname.setLayoutY(15);
        Label username = new Label("@"+user.getUsername());
        username.setTextFill(Color.rgb(0,0,255,0.5));
        username.setFont(Font.font("Apple Braille Outline 6 dot",14));
        username.setLayoutX(70);
        username.setLayoutY(40);
        pane.getChildren().addAll(nickname, username);
    }

    public void initAvatar(){
        Circle avatar = new Circle();
        avatar.setRadius(25);
        avatar.setLayoutX(35);
        avatar.setLayoutY(30);
        avatar.setFill(new ImagePattern(new Image(getClass().getResource("/asset/MainMenuBG.JPEG").toExternalForm())));
        pane.getChildren().add(avatar);
    }

    public void initCheckBox(){
        circle = new Circle(55,45,10);
        circle.getStyleClass().add("selected-icon");
        circle.setVisible(false);
        pane.getChildren().add(circle);
    }

    public void initAction(SelectMemberDialog selectMemberDialog){
        pane.setOnMouseClicked(mouseEvent -> {
            circle.setVisible(!circle.isVisible());
            if(circle.isVisible())
                selectMemberDialog.getSelectedUsers().add(this.user);
            else
                selectMemberDialog.getSelectedUsers().remove(this.user);
        });
    }
}
