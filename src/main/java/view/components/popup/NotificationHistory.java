package view.components.popup;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import utils.Pair;
import view.components.ImagesAddress;

public class NotificationHistory extends Pane {
    private Pane background;
    public NotificationHistory(){
        init();
    }
    public NotificationHistory(Pane backGround){
        this.background = backGround;
        init();
    }
    private void init(){
        this.getStylesheets().add(getClass().getResource("/CSS/Notification.css").toExternalForm());
        this.getStyleClass().add("pane");
        initTop();
        initVBox();
        closeButton();
    }

    private void initTop(){
        HBox hbox = new HBox();
        Label label = new Label("Notification History");
        Font font  = new Font("Times New Roman", 20);
        label.setFont(font);
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
        vbox.setSpacing(5);
        vbox.getStyleClass().add("bottom");
        vbox.setAlignment(Pos.CENTER);
        scrollPane.setContent(vbox);
        scrollPane.getStyleClass().add("bottom");
        scrollPane.setTranslateX(10);
        scrollPane.setTranslateY(60);
        scrollPane.setMinHeight(300);
        scrollPane.setMaxHeight(300);
        for(Pair<String, PopUpStates> pair : PopUp.getHistory()){
            vbox.getChildren().add(row(pair.getFirst(),pair.getSecond()));
        }
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.addEventFilter(ScrollEvent.SCROLL,new EventHandler<ScrollEvent>() {
            @Override
            public void handle(ScrollEvent event) {
                if (event.getDeltaX() != 0) {
                    event.consume();
                }
            }
        });
        this.getChildren().add(scrollPane);
    }

    private BorderPane row(String text, PopUpStates state){
        BorderPane out = new BorderPane();
        out.getStyleClass().add("box");
        out.setPadding(new Insets(5,5,5,5));

        HBox hbox = new HBox();
        Label label = new Label(text.split("/")[0]);
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

        Tooltip tooltip = new Tooltip(text.split("/")[1]);
        Tooltip.install(out, tooltip);
        return out;
    }

    private void closeButton(){
        ImageView close = new ImageView(ImagesAddress.INFO_CLOSE.getImage());
        close.setLayoutX(10);
        close.setLayoutY(10);
        close.setFitHeight(34);
        close.setFitWidth(34);
        close.setPickOnBounds(true);
        close.setPreserveRatio(true);
        this.getChildren().add(close);
        close.setOnMouseClicked(e -> {
            background.getChildren().remove(this);
        });
    }
}
