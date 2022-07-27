package view.chatroom;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;
import lombok.Getter;
import model.User;

@Getter
public class MemberRow extends HBox {

    private User user;
    private boolean checked = false;
    public MemberRow(User user){
        this.user = user;
        init();
    }

    private void init(){
        initHBox();
        initProfile();
        initName();
        initAction();
    }

    private void initHBox(){
        this.setAlignment(Pos.CENTER_LEFT);
        this.getStylesheets().add(getClass().getResource("/CSS/GroupChat.css").toExternalForm());
        this.getStyleClass().add("member-row");
    }

    private void initProfile(){
        Circle circle = new Circle(15);//todo handle profile picture
        this.getChildren().add(circle);
    }

    private void initName(){
        Label label = new Label(user.getNickname());
        label.getStyleClass().add("member-row-label");
        this.getChildren().add(label);
    }

    private void initAction(){
        this.setOnMouseClicked(action -> {
            check();
        });
    }

    private void check(){
        if(!checked) {
            this.setStyle("-fx-border-width: 3;");
            checked = true;
            return;
        }
        this.setStyle("-fx-border-width: 0;");
        checked = false;
    }
}


