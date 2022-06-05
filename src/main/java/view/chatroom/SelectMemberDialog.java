package view.chatroom;

import controller.ProgramController;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.*;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import model.User;

public class SelectMemberDialog extends Application {
    private BorderPane borderPane;
    private Stage stage;
    private Scene scene;
    private HBox container;
    private TableView<User> tableView;
    public String run(){
        try {
            start(new Stage());
        }catch(Exception e){
            e.printStackTrace();
        }
        return "salam";
    }

    @Override
    public void start(Stage stage) throws Exception {
        initialize(stage);
        stage.show();
    }

    @FXML
    public void initialize(Stage stage){
        this.stage = stage;
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setResizable(false);
        initGridPane();
        initButton();
    }

    public void initGridPane(){
        this.borderPane = new BorderPane();
        borderPane.getStylesheets().add(getClass().getResource("/CSS/SelectMemberStyle.css").toExternalForm());
        container = new HBox();
        StackPane center = new StackPane();
        center.getChildren().add(container);
        borderPane.setCenter(center);
        this.scene = new Scene(borderPane);
        stage.setScene(scene);
    }

    public void initButton(){
        Button okBtn = new Button("OK");
        okBtn.setOnAction(actionEvent -> {stage.close();});
        GridPane.setConstraints(okBtn,5,5,5,5);
        container.getChildren().add(okBtn);
    }

    public void initChecklist(){
        TableView<User> tableView = new TableView<>();
        this.tableView = tableView;
    }

}
