package view.components.mapComponents;

import com.jfoenix.controls.JFXButton;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import view.components.ImagesAddress;


public class GameStopMenu {
    Pane pane;
    Pane backPane;
    GameMapController gameMapController;
    public GameStopMenu(GameMapController gmp, Pane bp){
        this.backPane = bp;
        this.gameMapController = gmp;
        initPane();
    }

    private void initPane(){
        pane = new Pane();
        pane.setPrefHeight(720);
        pane.setPrefWidth(1280);
        pane.setStyle("-fx-background-color: rgb(0,0,0,0.8)");
        initImages();
        initButtons();
        backPane.getChildren().add(pane);
    }


    private void initImages(){
        ImageView resume = new ImageView();
        resume.setLayoutX(542);
        resume.setLayoutY(131);
        resume.setFitWidth(292);
        resume.setFitHeight(64);
        resume.setPickOnBounds(true);
        resume.setPreserveRatio(true);
        resume.setImage(ImagesAddress.GAME_STOP_ITEM.getImage());
        pane.getChildren().add(resume);

        ImageView save = new ImageView();
        save.setLayoutX(542);
        save.setLayoutY(245);
        save.setFitWidth(292);
        save.setFitHeight(64);
        save.setPickOnBounds(true);
        save.setPreserveRatio(true);
        save.setImage(ImagesAddress.GAME_STOP_ITEM.getImage());
        pane.getChildren().add(save);

        ImageView exit = new ImageView();
        exit.setLayoutX(542);
        exit.setLayoutY(359);
        exit.setFitWidth(292);
        exit.setFitHeight(64);
        exit.setPickOnBounds(true);
        exit.setPreserveRatio(true);
        exit.setImage(ImagesAddress.GAME_STOP_ITEM.getImage());
        pane.getChildren().add(exit);
    }
    private void initButtons(){
        JFXButton resumeButton = new JFXButton();
        resumeButton.setLayoutX(575);
        resumeButton.setLayoutY(145);
        resumeButton.setPrefWidth(140);
        resumeButton.setPrefHeight(26);
        resumeButton.setButtonType(JFXButton.ButtonType.RAISED);
        resumeButton.setTextFill(Color.rgb(255,228,228));
        resumeButton.setText("resume");
        pane.getChildren().add(resumeButton);
        resumeButton.setOnMouseClicked(e -> {
            backPane.getChildren().remove(pane);
        });

        JFXButton saveButton = new JFXButton();
        saveButton.setLayoutX(575);
        saveButton.setLayoutY(264);
        saveButton.setPrefWidth(140);
        saveButton.setPrefHeight(26);
        saveButton.setButtonType(JFXButton.ButtonType.RAISED);
        saveButton.setTextFill(Color.rgb(255,228,228));
        saveButton.setText("save");
        pane.getChildren().add(saveButton);
        //TODO : gameMapController.getGameMenuController().save();

        JFXButton exitButton = new JFXButton();
        exitButton.setLayoutX(575);
        exitButton.setLayoutY(380);
        exitButton.setPrefWidth(140);
        exitButton.setPrefHeight(26);
        exitButton.setButtonType(JFXButton.ButtonType.RAISED);
        exitButton.setTextFill(Color.rgb(255,228,228));
        exitButton.setText("exit");
        pane.getChildren().add(exitButton);
        //TODO : exit the game
    }
}
