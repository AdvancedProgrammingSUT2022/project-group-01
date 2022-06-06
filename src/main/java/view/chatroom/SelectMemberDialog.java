package view.chatroom;

import controller.ProgramController;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.*;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import lombok.Getter;
import model.User;
import view.chatroom.component.UserListItem;

import java.util.ArrayList;

@Getter
public class SelectMemberDialog extends Application {
    private BorderPane borderPane = new BorderPane();
    private Scene scene = new Scene(borderPane);
    private Stage stage;
    private VBox container = new VBox();
    private VBox usersList = new VBox();
    private ArrayList<User> selectedUsers = new ArrayList<>();
    public void run(){
        try {
            start(new Stage());
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void start(Stage stage) throws Exception {
        initialize(stage);
        stage.show();
    }

    public void initialize(Stage stage){
        this.stage = stage;
        initStage();
        initScrollPane();
        initButtons();
    }

    private void initStage(){
        stage.setWidth(400);
        stage.setHeight(500);
        Rectangle2D bounds = Screen.getPrimary().getVisualBounds();
        double x = (bounds.getWidth()-stage.getWidth())/2;
        double y = (bounds.getHeight()-stage.getHeight())/2;
        stage.setX(x);
        stage.setY(y);
        stage.setScene(scene);
        stage.setResizable(false);
        borderPane.setCenter(container);
        container.setPadding(new Insets(10,10,10,10));
        container.setSpacing(5);
        borderPane.getStylesheets().add(getClass().getResource("/CSS/SelectMemberStyle.css").toExternalForm());
        borderPane.getStyleClass().add("select-member-dialog");
    }

    public void initScrollPane(){
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.getStyleClass().add("scroll-bar");
        scrollPane.setStyle("-fx-background-color:transparent;-fx-background:transparent;");
        usersList.setStyle("-fx-background-color:transparent;");
        scrollPane.setContent(usersList);
        scrollPane.setMinHeight(400);
        for(int i=0;i<30;i++)
            usersList.getChildren().add(new UserListItem(ProgramController.getLoggedInUser(), this).getPane());
        container.getChildren().add(scrollPane);
    }

    private void initButtons(){
        Button button = new Button("OK");
        container.getChildren().add(button);
    }




}
