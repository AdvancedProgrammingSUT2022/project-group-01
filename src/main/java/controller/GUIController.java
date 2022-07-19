package controller;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.ImageCursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import view.chatroom.SelectMemberDialog;
import view.components.ImagesAddress;

public class GUIController extends Application {
    public static Parent root;
    private static Scene scene;
    private static Stage stage;

    public static Scene getScene() {
        return scene;
    }

    public static Stage getStage() {
        return stage;
    }


    public static void changeMenu(String name) {
        Parent root = loadFXML(name);
        if (!Objects.equals(name, "FirstPage")) {
            if(name.equals("PreMainMenu")) {
                Stage newStage = new Stage();
                newStage.setScene(scene);
                newStage.setResizable(false); // fixing the size
                stage.close();
                stage = newStage;
                stage.show();
            }
        } else {
            Stage newStage = new Stage();
            newStage.setScene(scene);
            newStage.setResizable(false);
            newStage.setHeight(720);
            newStage.setWidth(1280);
            newStage.initStyle(StageStyle.TRANSPARENT); // special background of first page
            scene.setFill(Color.TRANSPARENT); // filling color
            stage.close();
            stage = newStage;
            stage.show();
        }
        GUIController.scene.setRoot(root);
    }

    private static Parent loadFXML(String name) {
        try {
            BorderPane pane = loader.load();
            ((TabPane)pane.getCenter()).getTabs().get(0).setContent(button);
            scene = new Scene(pane);
            stage.setScene(scene);
        }catch(Exception e){
            e.printStackTrace();
        }

    public static void closeApp() {
        stage.close();
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.<AnchorPane>load(Objects.requireNonNull(getClass().getResource("/fxml/FirstPage.fxml")));
        stage = primaryStage;
        GUIController.root = root;
        Scene scene = new Scene(root);
        GUIController.scene = scene;
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        scene.setFill(Color.TRANSPARENT);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public void run(){
        launch();
    }

    public static void changeMenuManually(Pane pane){
        root = pane;
        //Stage newStage = new Stage();
        //newStage.setScene(scene);
        //newStage.setResizable(false); // fixing the size
        //stage.close();
        //stage = newStage;
        //stage.show();
        GUIController.scene.setRoot(root);
    }

    public static void setCursor(){
        GUIController.getScene().setCursor(new ImageCursor(ImagesAddress.LIGHTSABER.getImage()));
    }
}
