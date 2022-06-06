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

@Getter
public class UserListItem {
    Pane pane;
    private Rectangle rectangle;
    private final User user;
    private CheckBox checkBox = new CheckBox();
    public UserListItem(User user){
        pane = new Pane();
        rectangle = new Rectangle(360,60);
        pane.setMinWidth(363);
        pane.setMinHeight(60);
        this.user = user;
        initRectangle();
        initAvatar();
        initLabel();
        initCheckBox();
        initAction();
    }

    private void initRectangle(){
        rectangle.setStyle("-fx-background-radius: 20; -fx-border-radius: 20");
        //rectangle.getStyleClass().add("rectangle");
        pane.getChildren().add(rectangle);
    }

    private void initLabel(){
        Label label = new Label(user.getNickname());
        label.setTextFill(Color.WHITE);
        label.setFont(Font.font("Apple Braille Outline 6 dot",22));
        label.setLayoutX(70);
        label.setLayoutY(20);
        pane.getChildren().add(label);

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
        checkBox.setLayoutX(100);
        checkBox.setLayoutY(23);
        pane.getChildren().add(checkBox);
    }

    public void initAction(){
        pane.setOnMouseClicked(mouseEvent -> {
            checkBox.setSelected(!checkBox.isSelected());
        });
    }
}
