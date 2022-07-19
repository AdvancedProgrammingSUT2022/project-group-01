package view.components.loginRegister;

import com.jfoenix.controls.JFXComboBox;

import controller.GUIController;
import controller.LoginMenuController;
import controller.ProgramController;
import controller.TileController;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.Database;
import model.Game;
import model.User;
import view.Main;
import view.components.popup.PopUp;
import view.components.popup.PopUpStates;


import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Objects;
import java.util.Random;

public class FirstPageController {
    private final FileChooser fileChooser = new FileChooser();
    public TextField nickname;
    @FXML
    private VBox vBox;
    @FXML
    private Pane parent;
    @FXML
    private TextField password;
    @FXML
    private TextField username;
    @FXML
    private Circle avatarCircle;
    private String chosenAvatarPath;
    private LoginMenuController loginMenuController = new LoginMenuController(ProgramController.getDatabase());

    public static void choosePictureOfList(JFXComboBox avatarList, Circle avatarCircle) {
        String newAvatarPath;
        if (avatarList == null || avatarList.getValue() == null) return;
        if (avatarList.getValue().equals("Miles Prower"))
            newAvatarPath = getProfilePictureAddress(1);
        else if (avatarList.getValue().equals("CupHead"))
            newAvatarPath = getProfilePictureAddress(2);
        else if (avatarList.getValue().equals("Mugman"))
            newAvatarPath = getProfilePictureAddress(3);
        else return;
        if (!newAvatarPath.equals("")) {
            ImagePattern pattern = new ImagePattern(new Image(newAvatarPath),
                    280, 180, 100, 100, false);
            avatarCircle.setFill(pattern);
            if (ProgramController.getLoggedInUser() != null) ProgramController.getLoggedInUser().setAvatarUrl(newAvatarPath);
        }
    }

    private static String getProfilePictureAddress(int i){
        String newAvatarPath = "";
        try {
            newAvatarPath = String.valueOf(
                    new URL(Objects.requireNonNull(
                            Main.class.getResource(
                                    "/pictures/defaultProfilePictures/" + i + ".jpeg")).toString()));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return  newAvatarPath;
    }

    public void initialize() {
        gameInitializations();
        openSignUp();
        fileChooser.setInitialDirectory(new File(
                "/Users/cyberrose/personal/Sharif%/Term2/" +
                        "AP/HW3/src/main/resources/asset/defaultProfilePictures"));
        if (avatarCircle != null) randomInitializeAvatar();
    }

    public void openSignUp() { // change the vbox to sign up page
        TranslateTransition transition = new TranslateTransition(Duration.millis(1000), vBox);
        transition.setToX(0);
        transition.play();
        transition.setOnFinished((e -> {
            try {
                parent = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("/FXML/SignUp.fxml")));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            vBox.getChildren().removeAll();
            vBox.getChildren().setAll(parent);
        }));
    }

    public void openSignIn() { // change th vbox to sign in
        TranslateTransition transition = new TranslateTransition(Duration.millis(1000), vBox);
        transition.setToX(vBox.getLayoutX() * 20);
        transition.play();
        transition.setOnFinished((e -> {
            try {
                parent = FXMLLoader.load(Objects.requireNonNull(view.Main.class.getResource("/FXML/loginpage.fxml")));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            vBox.getChildren().removeAll();
            vBox.getChildren().setAll(parent);
        }));
    }

    public void register() {
        HashMap<String,String> inputs = new HashMap<>();
        inputs.put("username",username.getText());
        inputs.put("password",password.getText());
        inputs.put("nickname",nickname.getText());
        inputs.put("avatarURL",chosenAvatarPath);
        String output = loginMenuController.register(inputs);
        if(output.startsWith("user with username"))
            AlertBox.display(loginPageTexts.USERNAME_EXISTS);
        if(output.startsWith("user with nickname"))
            AlertBox.display(loginPageTexts.NICKNAME_EXISTS);
        else AlertBox.display(loginPageTexts.WELCOME); // show the welcome pop up
    }

    public void signInAttempt() {
        HashMap<String,String> inputs = new HashMap<>();
        inputs.put("username",username.getText());
        inputs.put("password",password.getText());
        String output = loginMenuController.login(inputs);
        if (output.startsWith("Username and password"))
            //AlertBox.display(loginPageTexts.USER_NOT_EXISTS);
            new PopUp().run(PopUpStates.OK,"username bird");
        else
            GUIController.changeMenu("PreMainMenu");
    }

    public void closeApp() {
        GUIController.closeApp();
    }

    public void chooseImage() {
        File file = fileChooser.showSaveDialog(new Stage());
        if (file != null) {
            chosenAvatarPath = file.toURI().toString();
            ImagePattern pattern = new ImagePattern(
                    new Image(chosenAvatarPath), 280, 180, 100, 100, false);
            avatarCircle.setFill(pattern);

        }
    }

    private void randomInitializeAvatar() {
        Random random = new Random(); // random number
        int index = Math.abs(random.nextInt()) % 3 + 1; // based in 1 - 3
        URL address = null;
        try {
            address = new URL(Objects.requireNonNull(
                    Main.class.getResource("/asset/defaultProfilePictures/" + index + ".jpg")).toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        assert address != null;
        chosenAvatarPath = address.toString();
        ImagePattern pattern = new ImagePattern(new Image(chosenAvatarPath));
        avatarCircle.setFill(pattern);
    }


    private void gameInitializations(){
        ProgramController.getDatabase().load();
        TileController.initializeEnums();
    }
}
