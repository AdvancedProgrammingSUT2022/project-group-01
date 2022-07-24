package view.components.preMainInMenus;

import controller.GUIController;
import controller.ProgramController;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import model.Database;
import model.User;
import view.Main;
import view.components.ImagesAddress;

public class FriendShipController {
    private Pane root;
    private User user;
    private VBox friendsBox;
    private VBox searchBox;
    private TextField searchField;
    private Pane requestPane;


    public FriendShipController(User user) {
        this.user = user;
        baseInitialize();
    }

    private void baseInitialize(){
        root = new Pane();
        root.setPrefWidth(1280);
        root.setPrefHeight(720);
        root = (Pane) GUIController.loadFXML("FriendsPage");
        root.setStyle("-fx-background-image: url(asset/other/friendsBackground.jpg)");
        initSearchPane();
        initSearchField();
        searchButton();
        initFriendsPane();
        addFriendsToList();
        getRequestPane();
        closeButton();
    }

    public Pane getRoot(){
        return root;
    }


    private void searchButton(){
        ImageView search = new ImageView(ImagesAddress.FRIENDS_SEARCH_BUTTON.getImage());
        search.setLayoutX(539);
        search.setLayoutY(494);
        search.setFitHeight(26);
        search.setFitWidth(26);
        search.setPickOnBounds(true);
        search.setPreserveRatio(true);
        root.getChildren().add(search);
        search.setOnMouseClicked(event -> {
            addUsersList(searchField.getText());
        });
    }

    private void initSearchField(){
        searchField = new TextField();
        searchField.setLayoutX(53);
        searchField.setLayoutY(494);
        searchField.setPrefHeight(26);
        searchField.setPrefWidth(437);
        searchField.setPromptText("Search");
        searchField.setFont(Font.font("Avenir Book",14));
        searchField.getStylesheets().add(String.valueOf(Main.class.getResource("/CSS/FirstPage.css")));
        searchField.getStyleClass().add("tf_box");
        root.getChildren().add(searchField);
    }

    private void initFriendsPane(){
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setLayoutX(53);
        scrollPane.setLayoutY(105);
        scrollPane.setPrefHeight(268);
        scrollPane.setPrefWidth(513);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setStyle("-fx-background: transparent");
        scrollPane.setStyle("-fx-background-color: transparent");
        scrollPane.getStylesheets().add(String.valueOf(Main.class.getResource("/CSS/InvitationMenu.css")));
        friendsBox = new VBox();
        friendsBox.setPrefWidth(501);
        friendsBox.setStyle("-fx-background-color: transparent; -fx-background: transparent");
        friendsBox.setSpacing(20);
        scrollPane.setContent(friendsBox);
        root.getChildren().add(scrollPane);
    }

    private void addFriendsToList(){
        for(String username : user.getFriends()){
            Pane p = new FriendItem(ProgramController.getDatabase().findUserByUsername(username)).getRoot();
            friendsBox.getChildren().add(p);
        }
    }

    private void initSearchPane(){
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setLayoutX(52);
        scrollPane.setLayoutY(549);
        scrollPane.setPrefHeight(150);
        scrollPane.setStyle("-fx-background: transparent");
        scrollPane.setStyle("-fx-background-color: transparent");
        scrollPane.setPrefWidth(513);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.getStylesheets().add(String.valueOf(Main.class.getResource("/CSS/InvitationMenu.css")));
        searchBox = new VBox();
        searchBox.setPrefWidth(499);
        searchBox.setPrefHeight(139);
        searchBox.setStyle("-fx-background-color: transparent; -fx-background: transparent");
        searchBox.setSpacing(20);
        scrollPane.setContent(searchBox);
        root.getChildren().add(scrollPane);
    }

    private void getRequestPane(){
        requestPane = new FriendRequest(user).getRoot();
        requestPane.setLayoutX(762);
        requestPane.setLayoutY(161);
        requestPane.setPrefHeight(454);
        requestPane.setPrefWidth(347);
        root.getChildren().add(requestPane);
    }

    private void addUsersList(String name){
        searchBox.getChildren().clear();
        for(User userChooser : ProgramController.getDatabase().getUsers()){
            if(!user.equals(userChooser)){
                if(!user.hasUserAsFriend(userChooser)){
                    if(userChooser.getNickname().startsWith(name)){
                        addUserToFriendsList(userChooser);
                    }
                }
            }
        }
    }

    private void addUserToFriendsList(User userChooser) {
        Pane p = new FriendItem(userChooser).getRoot();
        p.setOnMouseClicked(event -> {
            userChooser.addRequest(user);
            searchBox.getChildren().remove(p);
        });
        searchBox.getChildren().add(p);
    }

    private void update(){
        friendsBox.getChildren().clear();
        addFriendsToList();
        searchBox.getChildren().clear();
        root.getChildren().remove(requestPane);
        getRequestPane();
    }

    private void closeButton(){
        ImageView close = new ImageView(ImagesAddress.INFO_CLOSE.getImage());
        close.setLayoutX(10);
        close.setLayoutY(10);
        close.setFitHeight(34);
        close.setFitWidth(34);
        close.setPickOnBounds(true);
        close.setPreserveRatio(true);
        root.getChildren().add(close);
        close.setOnMouseClicked(e -> {
            GUIController.changeMenu("preMainMenu");
        });
    }
}
