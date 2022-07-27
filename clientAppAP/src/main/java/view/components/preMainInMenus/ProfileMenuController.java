package view.components.preMainInMenus;

import com.jfoenix.controls.JFXComboBox;

import controller.GUIController;
import controller.ProgramController;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Database;
import model.Game;
import view.Main;
import view.components.ImagesAddress;
import view.components.loginRegister.AlertBox;
import view.components.loginRegister.loginPageTexts;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;

public class ProfileMenuController {
    private final FileChooser fileChooser = new FileChooser();
    public ImageView picOne;
    public ImageView picTwo;
    public ImageView picThree;
    public ImageView picFour;
    public Pane root;
    private Stage avatarPopup;

    @FXML
    private TextField newUsername;
    @FXML
    private TextField newPassword;
    @FXML
    private Circle avatarCircle = new Circle();

    public void initialize() {
        ImagePattern pattern = new ImagePattern(ImagesAddress.getImageByNameInEnum(ProgramController.getLoggedInUser().getAvatarUrl()).getImage());
        avatarCircle.setFill(pattern);
        avatarCircle.setStyle("-fx-background-repeat: stretch; -fx-background-size: 59");
        setBackButton();
    }

    public void saveNewPassword() {
        String password = newPassword.getText();
        ProgramController.getLoggedInUser().setPassword(password);
    }

    public void saveNewUsername() {
        String username = newUsername.getText();
         ProgramController.getLoggedInUser().setUsername(username);
    }

    public void returnToMainMenu() {
        GUIController.changeMenu("PreMainMenu");
    }

    public void chooseAvatarFile() {
//        File file = fileChooser.showSaveDialog(new Stage());
//        if (file != null) {
//            String chosenAvatarPath = file.toURI().toString();
//            ImagePattern pattern = new ImagePattern(new Image(chosenAvatarPath),
//                    280, 180, 100, 100, false);
//            avatarCircle.setFill(pattern);
//            ProgramController.getLoggedInUser().setAvatarUrl(chosenAvatarPath);
//        }
    }

    public void openAvatarPanel(MouseEvent event) {
        avatarPopup = new Stage();
        avatarPopup.initModality(Modality.APPLICATION_MODAL);

        avatarPopup.setMinWidth(400);
        avatarPopup.setMinHeight(400);

        Pane layout = new Pane();
        layout.setPrefWidth(400);
        layout.setPrefHeight(400);
        layout.setStyle("-fx-background-color: BLACK");
        showPrefPics();
        layout.getChildren().addAll(picOne, picTwo, picThree, picFour);
        prefPics();
        Scene scene = new Scene(layout);
        avatarPopup.setScene(scene);
        avatarPopup.showAndWait();
        setPicByList();
    }

    public void setPicByList(){
        picOne.setOnMouseClicked(event -> {
            ProgramController.getLoggedInUser().setAvatarUrl(ImagesAddress.AVATAR1.name());
            ImagePattern pattern = new ImagePattern(picOne.getImage());
            avatarCircle.setFill(pattern);
            avatarPopup.close();
        });
        picTwo.setOnMouseClicked(event -> {
            ProgramController.getLoggedInUser().setAvatarUrl(ImagesAddress.AVATAR2.name());
            ImagePattern pattern = new ImagePattern(picTwo.getImage());
            avatarCircle.setFill(pattern);
            avatarPopup.close();
        });
        picThree.setOnMouseClicked(event -> {
            ProgramController.getLoggedInUser().setAvatarUrl(ImagesAddress.AVATAR3.name());
            ImagePattern pattern = new ImagePattern(picThree.getImage());
            avatarCircle.setFill(pattern);
            avatarPopup.close();
        });
        picFour.setOnMouseClicked(event -> {
            ProgramController.getLoggedInUser().setAvatarUrl(ImagesAddress.AVATAR4.name());
            ImagePattern pattern = new ImagePattern(picFour.getImage());
            avatarCircle.setFill(pattern);
            avatarPopup.close();
        });
    }


    private void prefPics(){
        picOne.setFitHeight(200);
        picOne.setFitWidth(200);
        picOne.setLayoutY(0);
        picOne.setLayoutX(0);
        picTwo.setLayoutX(200);
        picTwo.setLayoutY(0);
        picTwo.setFitWidth(200);
        picTwo.setFitWidth(200);

        picThree.setFitHeight(200);
        picThree.setFitWidth(200);
        picThree.setLayoutY(200);
        picThree.setLayoutX(0);
        picFour.setFitWidth(200);
        picFour.setFitHeight(200);
        picFour.setLayoutY(200);
        picFour.setLayoutX(200);
        setupPictures(picOne);
        setupPictures(picTwo);
        setupPictures(picThree);
        setupPictures(picFour);
        picOne.setOnMouseClicked(e -> {
            ProgramController.getLoggedInUser().setAvatarUrl(ImagesAddress.AVATAR1.name());
            ImagePattern pattern = new ImagePattern(ImagesAddress.AVATAR1.getImage());
            avatarCircle.setFill(pattern);
            avatarPopup.close();
        });
        picTwo.setOnMouseClicked(e -> {
            ProgramController.getLoggedInUser().setAvatarUrl(ImagesAddress.AVATAR2.name());
            ImagePattern pattern = new ImagePattern(ImagesAddress.AVATAR2.getImage());
            avatarCircle.setFill(pattern);
            avatarPopup.close();
        });
        picThree.setOnMouseClicked(e -> {
            ProgramController.getLoggedInUser().setAvatarUrl(ImagesAddress.AVATAR3.name());
            ImagePattern pattern = new ImagePattern(ImagesAddress.AVATAR3.getImage());
            avatarCircle.setFill(pattern);
            avatarPopup.close();
        });
        picFour.setOnMouseClicked(e -> {
            ProgramController.getLoggedInUser().setAvatarUrl(ImagesAddress.AVATAR4.name());
            ImagePattern pattern = new ImagePattern(ImagesAddress.AVATAR4.getImage());
            avatarCircle.setFill(pattern);
            avatarPopup.close();
        });
    }


    private void setupPictures(ImageView pic) {
        pic.setPickOnBounds(true);
        pic.setPreserveRatio(true);
    }


    private void showPrefPics(){
        picOne = new ImageView(ImagesAddress.AVATAR1.getImage());
        picTwo = new ImageView(ImagesAddress.AVATAR2.getImage());
        picThree = new ImageView(ImagesAddress.AVATAR3.getImage());
        picFour = new ImageView(ImagesAddress.AVATAR4.getImage());
    }

    public void backToPreMain(MouseEvent event) {
        GUIController.changeMenu("PreMainMenu");
    }

    private void setBackButton(){
        ImageView backButton = new ImageView(ImagesAddress.BACK_BUTTON.getImage());
        backButton.setTranslateX(5);
        backButton.setTranslateY(5);
        backButton.setOnMouseClicked(e -> {
            GUIController.changeMenu("preMainMenu");
        });
        root.getChildren().add(backButton);
    }
}
