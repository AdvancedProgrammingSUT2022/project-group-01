package controller;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import view.chatroom.SelectMemberDialog;

public class GUIController extends Application {

    @Override
    public void start(Stage stage) {
        stage.setResizable(false);
        Scene scene;
        Button button = new Button("salam");
        button.setOnAction(actionEvent -> {
            new SelectMemberDialog().run();
        });
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/Chatroom.fxml"));
        try {
            BorderPane pane = loader.load();
            ((TabPane)pane.getCenter()).getTabs().get(0).setContent(button);
            scene = new Scene(pane);
            stage.setScene(scene);
        }catch(Exception e){
            e.printStackTrace();
        }

        stage.show();
    }

    public void run(){
        launch();
    }
}
