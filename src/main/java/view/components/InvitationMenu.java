package view.components;

import controller.ProgramController;
import javafx.scene.control.DateCell;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import lombok.Getter;
import model.Database;
import model.User;
import view.Main;

import java.util.Locale;

public class InvitationMenu {
    @Getter
    private Pane root = new Pane();
    private TextField textField = new TextField();
    private ScrollPane scrollPane = new ScrollPane();
    private GameInstantiateData gameInstantiateData;
    private ImageView searchIcon;
    private VBox usersList = new VBox();
    public InvitationMenu(GameInstantiateData gameInstantiateData) {
        this.gameInstantiateData = gameInstantiateData;
        root.setPrefHeight(450);
        root.setPrefWidth(830);
       // root.setStyle("-fx-background-color: rgba(0,0,0,0.66)");
        root.setStyle("-fx-background-color: transparent");
        usersList.setStyle("-fx-background-color: transparent");
        //scrollPane.setStyle("-fx-background-color: rgba(0,0,0,0.66)");
        scrollPane.setStyle("-fx-opacity: 0");
        usersList.setPrefWidth(830);
        usersList.setSpacing(10);
        setTextField();
        setSearchIcon();
        initScrollPane();
    }

    private void setTextField(){
        textField.setLayoutX(31);
        textField.setLayoutY(14);
        textField.setPrefHeight(34);
        textField.setPrefWidth(709);
        textField.getStylesheets().add(String.valueOf(Main.class.getResource("/CSS/FirstPage.css")));
        textField.getStyleClass().add("tf_box");
        textField.setPromptText("Enter the username you are searching for...");
        root.getChildren().add(textField);
    }

    private void setSearchIcon(){
        searchIcon = new ImageView(ImagesAddress.SEARCH.getImage());
        searchIcon.setFitHeight(34);
        searchIcon.setFitWidth(38);
        searchIcon.setLayoutX(761);
        searchIcon.setLayoutY(14);
        searchIcon.setOnMouseClicked(e -> updateScrollPaneData());
        root.getChildren().add(searchIcon);
    }

    private void updateScrollPaneData(){
        usersList.getChildren().clear();
        String name = textField.getText();
        name = name.trim();
        if(name.equals("")) return;
        for(User user : ProgramController.getDatabase().getUsers()){
            if(user.getUsername().startsWith(name)){
                usersList.getChildren().add(new FindUserItem(user,gameInstantiateData).getRoot());
            }
        }
    }

    private void initScrollPane(){
        scrollPane.setLayoutX(0);
        scrollPane.setLayoutY(62);
        scrollPane.setPrefHeight(388);
        scrollPane.setPrefWidth(830);
        scrollPane.getStylesheets().add(String.valueOf(Main.class.getResource("/CSS/InvitationMenu.css")));
        scrollPane.setStyle("-fx-background: transparent");
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        // set Vbox size and stuff
        scrollPane.setContent(usersList);
        root.getChildren().add(scrollPane);
    }
}
