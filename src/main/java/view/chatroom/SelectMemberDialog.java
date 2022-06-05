package view.chatroom;

import controller.ProgramController;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class SelectMemberDialog extends Application {

    public void run(){
        try {
            start(new Stage());
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent windowEvent) {

            }
        });
        stage.setResizable(false);
        Scene scene;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/SelectMemberDialog.fxml"));
        try {
            GridPane pane = loader.load();
            scene = new Scene(pane);
            stage.setScene(scene);
        }catch(Exception e){
            e.printStackTrace();
        }
        stage.show();
    }
}
