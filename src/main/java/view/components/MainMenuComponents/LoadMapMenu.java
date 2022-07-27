package view.components.MainMenuComponents;

import com.jfoenix.controls.JFXButton;
import controller.GameMenuController;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import model.User;
import view.Main;

import java.io.File;


public class LoadMapMenu {
    private Pane root;
    private VBox files;


    public LoadMapMenu() {
        init();
    }

    private void init(){
        initRoot();
        addScrollPane();
        addFiles();
    }

    public Pane getRoot(){
        return root;
    }
    private void initRoot(){
        root = new Pane();
        root.setPrefHeight(450);
        root.setPrefWidth(830);
        root.setStyle("-fx-background-color: transparent");
    }

    private void addFiles(){
        String[] fileNames=new File("src/main/resources/savedGames").list();
        for (String fileName : fileNames) {
            System.out.println(fileName);
            //String name= fileName.replaceAll(".json","");
            String name = fileName;
            JFXButton save = new JFXButton(name);
            save.setTextFill(Color.WHITESMOKE);
            save.setFont(Font.font("AppleGothic Regular",15));
            save.setAlignment(Pos.CENTER);
            save.setTextAlignment(TextAlignment.CENTER);
            files.getChildren().add(save);
            save.setOnMouseClicked(mouseEvent -> {
                GameMenuController.readFromFile(name);
            });
        }
    }

    private void addScrollPane(){
        ScrollPane s = new ScrollPane();
        s.setLayoutX(0);
        s.setLayoutY(60);
        s.setPrefHeight(388);
        s.setPrefWidth(830);
        s.getStylesheets().add(String.valueOf(Main.class.getResource("/CSS/InvitationMenu.css")));
        s.setStyle("-fx-background: transparent");
        s.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        s.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        files = new VBox();
        files.setStyle("-fx-background-color: transparent");
        files.setPrefWidth(830);
        s.setContent(files);
        root.getChildren().add(s);
    }


}
