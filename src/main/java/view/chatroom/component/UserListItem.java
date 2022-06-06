package view.chatroom.component;

import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import model.User;

public class UserListItem extends Pane {
    private Rectangle rectangle = new Rectangle(363,50);
    private final User user;
    private CheckBox checkBox = new CheckBox();
    public UserListItem(User user){
        this.setMinWidth(363);
        this.setMinHeight(50);
        this.getStylesheets().add("/CSS/SelectMemberStyle.css");
        this.user = user;
        initRectangle();
        initAvatar();
        initCheckBox();
        initAction();
    }

    private void initRectangle(){
        //rectangle.getStyleClass().add("rectangle");
        this.getChildren().add(rectangle);
    }

    public void initAvatar(){
        Circle avatar = new Circle();
        avatar.setRadius(16);
        avatar.setLayoutX(25);
        avatar.setLayoutY(25);
        avatar.setFill(new ImagePattern(new Image(getClass().getResource("/asset/MainMenuBG.JPEG").toExternalForm())));
        this.getChildren().add(avatar);
    }

    public void initCheckBox(){
        checkBox.setLayoutX(100);
        checkBox.setLayoutY(23);
        this.getChildren().add(checkBox);
    }

    public void initAction(){
        this.setOnMouseClicked(mouseEvent -> {
            checkBox.setSelected(!checkBox.isSelected());
        });
    }
}
