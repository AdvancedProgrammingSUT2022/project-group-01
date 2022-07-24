package view.components.popup;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import utils.Pair;

public class NotificationHistory extends Pane {

    public NotificationHistory(){
        init();
    }

    private void init(){
        this.getStylesheets().add(getClass().getResource("/CSS/Notification.css").toExternalForm());
        initTop();
        initVBox();
    }

    private void initTop(){
        HBox hbox = new HBox();
        Label label = new Label("Notification History");
        hbox.getChildren().add(label);
        hbox.setAlignment(Pos.CENTER);
        hbox.setPadding(new Insets(5,5,5,5));
        hbox.setMinWidth(300);
        hbox.setMaxWidth(300);
        hbox.setMinHeight(30);
        hbox.setMaxHeight(30);
        hbox.setTranslateX(10);
        hbox.setTranslateY(10);
        this.getChildren().add(hbox);
    }

    private void initVBox(){
        ScrollPane scrollPane = new ScrollPane();
        VBox vbox = new VBox();
        vbox.getStyleClass().add("bottom");
        scrollPane.setContent(vbox);
        scrollPane.getStyleClass().add("bottom");
        for(Pair<String, PopUpStates> pair : PopUp.getHistory()){
            vbox.getChildren().add(row(pair.getFirst(),pair.getSecond()));
        }
        scrollPane.setTranslateX(10);
        scrollPane.setTranslateY(60);
        this.getChildren().add(scrollPane);
    }

    private BorderPane row(String text, PopUpStates state){
        BorderPane out = new BorderPane();
        out.getStyleClass().add("box");
        out.setPadding(new Insets(5,5,5,5));

        HBox hbox = new HBox();
        Label label = new Label(text);
        Font font = new Font("Times New Roman", 12);
        label.setFont(font);
        label.getStyleClass().add("label");
        label.setWrapText(true);
        label.setMaxWidth(170);
        hbox.getChildren().add(label);
        hbox.setAlignment(Pos.CENTER_LEFT);

        Rectangle rectangle = new Rectangle(50,50);
        rectangle.setFill(state.getImage());

        out.setLeft(hbox);
        out.setRight(rectangle);
        return out;
    }
}
