package view.components.popup;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.time.Duration;
import java.util.LinkedList;
import java.util.Timer;
import java.util.TimerTask;

public class PopUp extends Application {

    private Stage stage = new Stage();
    private BorderPane pane = new BorderPane();
    private PopUpStates state;
    private String text;
    private static final LinkedList<Stage> stages = new LinkedList<>();

    @Override
    public void start(Stage stage) throws Exception {
        stage.show();
    }

    public void run(PopUpStates state, String message) {
        stages.add(stage);
        this.state = state;
        this.text = message;
        initGraphic();
        initTimer();
        try {
            start(stage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void initTimer() {
        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                try {
                    Platform.runLater(() -> {
                        applyGravity();
                        stage.close();
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        timer.schedule(timerTask, 5000);
    }

    private void initGraphic() {
        initStage();
        initText();
        initIcon();
    }

    public void initStage() {
        Scene scene = new Scene(pane);
        scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setWidth(250);
        stage.setHeight(60);
        pane.setPadding(new Insets(5, 5, 5, 5));
        pane.setStyle("-fx-background-radius: 15,15,15,15;");
        pane.setStyle("-fx-background-color: black");
        //setting position on screen
        Rectangle2D screenBounds = Screen.getPrimary().getBounds();
        stage.setX(screenBounds.getWidth() - (stage.getWidth() + 10));
        stage.setY(screenBounds.getHeight() - 30 - stages.size() * ((stage.getHeight() + 5)));
    }

    public void initText() {
        HBox hbox = new HBox();
        Label label = new Label(text);
        Font font = new Font("Times New Roman", 12);
        label.setFont(font);
        label.setStyle("-fx-text-fill: #ffffff");
        label.setWrapText(true);
        label.setMaxWidth(170);
        hbox.setAlignment(Pos.CENTER);
        hbox.getChildren().add(label);
        pane.setLeft(hbox);
    }

    public void initIcon() {
        Rectangle rectangle = new Rectangle(50, 50);
        rectangle.setFill(state.getImage());
        pane.setRight(rectangle);
    }

    private void applyGravity() {
        stages.pop();
        Rectangle2D screenBounds = Screen.getPrimary().getBounds();
        for (int i = 0; i < stages.size(); i++){
            stages.get(i).setY(screenBounds.getHeight() - 30 - (i+1) * ((stage.getHeight() + 5)));
        }
    }

}
