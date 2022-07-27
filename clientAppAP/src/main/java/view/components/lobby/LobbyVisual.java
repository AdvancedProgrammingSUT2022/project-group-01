package view.components.lobby;

import controller.GUIController;
import controller.NetworkController;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import model.Lobby;
import model.User;
import network.Request;
import network.Response;
import view.Main;
import view.components.ImagesAddress;
//import view.components.InvitationMenu;

public class LobbyVisual {
    private Lobby lobby;
    private User user;
    private Pane root;
    private VBox usersListBox;
    private ImageView chatButton;
    private ImageView exitLobbyButton;
    private Label privacy;
    private Pane invitationPane;
    private ImageView startGame;
    private ImageView invitationSetter;
    private ImageView privacySet;

    public LobbyVisual(Lobby lobby, User user) {
        this.lobby = lobby;
        this.user = user;
        init();
    }

    private void init(){
        initRoot();
        usersList();

        insideChatButton();
        exitLobby();
        setPrivacyLabel();
        update();
        listenerForUpdate();
    }

    public Pane getRoot(){
        return root;
    }

    private void update(){
        clear();
        setPrivacyLabel();
        addUsers();
        if(user.getUsername().equals(lobby.getOwner().getUsername())){
            privacySetter();
            invitationSetter();
            startGameButton();
        }
    }

    private void clear(){
        root.getChildren().remove(privacy);
        usersListBox.getChildren().clear();
        root.getChildren().remove(privacySet);
        root.getChildren().remove(invitationSetter);
        root.getChildren().remove(startGame);

    }
    private void initRoot(){
        root = new Pane();
        root.setPrefHeight(720);
        root.setPrefWidth(1280);
//        root.setStyle("-fx-background-image: url(asset/lobby/lobyVisualBackground.png)");
        ImageView backImage = new ImageView(ImagesAddress.LOBBY_BACK.getImage());
        backImage.setFitHeight(720);
        backImage.setFitWidth(1280);
        backImage.setPickOnBounds(true);
        backImage.setPreserveRatio(true);
        root.getChildren().add(backImage);
    }

    private void usersList(){
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setLayoutX(473);
        scrollPane.setLayoutY(226);
        scrollPane.setPrefHeight(304);
        scrollPane.setPrefWidth(316);
        scrollPane.setStyle("-fx-background-color: transparent");
        scrollPane.setStyle("-fx-background : transparent");
        scrollPane.getStylesheets().add(String.valueOf(Main.class.getResource("/CSS/InvitationMenu.css")));
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        usersListBox = new VBox();
        usersListBox.setPrefWidth(315);
        scrollPane.setContent(usersListBox);
        root.getChildren().add(scrollPane);
    }

    private void addUsers(){
        for(User u : lobby.getUsers()){
            Label m = new Label();
            m.setText(u.getNickname());
            m.setTextFill(Color.WHITE);
            if(u.equals(lobby.getOwner()))
                m.setTextFill(Color.RED);
            usersListBox.getChildren().add(m);
        }
    }

    private void insideChatButton(){
        chatButton = new ImageView();
        chatButton.setFitHeight(58);
        chatButton.setFitWidth(58);
        chatButton.setLayoutX(987);
        chatButton.setLayoutY(14);
        chatButton.setPickOnBounds(true);
        chatButton.setPreserveRatio(true);
        root.getChildren().add(chatButton);
        chatButton.setOnMouseClicked(e -> {
            // TODO ... amnaaaaaaaammmmmmmm kooooojjaaaaaaiiiii
        });
    }

    private void exitLobby(){
        exitLobbyButton = new ImageView();
        exitLobbyButton.setFitWidth(58);
        exitLobbyButton.setFitHeight(58);
        exitLobbyButton.setLayoutX(1070);
        exitLobbyButton.setLayoutY(14);
        exitLobbyButton.setPickOnBounds(true);
        exitLobbyButton.setPreserveRatio(true);
        root.getChildren().add(exitLobbyButton);
        exitLobbyButton.setOnMouseClicked(e -> {
            //TODO ...
            lobby.removeUser(user);
            GUIController.changeMenuManually(new LobbyMainMenu().getBackPane());
        });
    }

    private void setPrivacyLabel(){
        privacy = new Label();
        privacy.setLayoutX(1203);
        privacy.setLayoutY(35);
        privacy.setTextFill(Color.BLACK);
        if(lobby.isPublic())
            privacy.setText("public");
        else privacy.setText("private");
        root.getChildren().add(privacy);
    }

    //admin options
    private void privacySetter(){
        privacySet = new ImageView(ImagesAddress.PRIVACY_ICON.getImage());
        privacySet.setFitHeight(74);
        privacySet.setFitWidth(74);
        privacySet.setLayoutX(34);
        privacySet.setLayoutY(110);
        privacySet.setPickOnBounds(true);
        privacySet.setPreserveRatio(true);
        privacySet.setOnMouseClicked(e -> {
            if(lobby.isPublic()){
                lobby.setPublic(false);
            }else
                lobby.setPublic(true);
            update();
        });
        root.getChildren().add(privacySet);
    }

    private void invitationSetter(){
        invitationSetter = new ImageView(ImagesAddress.INVITATION_ICON.getImage());
        invitationSetter.setFitHeight(74);
        invitationSetter.setFitWidth(74);
        invitationSetter.setLayoutX(34);
        invitationSetter.setLayoutY(250);
        invitationSetter.setPickOnBounds(true);
        invitationSetter.setPreserveRatio(true);
//        invitationSetter.setOnMouseClicked(e -> {
//            showInvitationMenu();
//        });
        root.getChildren().add(invitationSetter);
    }

//    private void showInvitationMenu(){
//        if(invitationPane != null) {
//            root.getChildren().remove(invitationPane);
//            invitationPane = null;
//        }
//        invitationPane = new InvitationMenu(lobby).getRoot();
//        root.getChildren().add(invitationPane);
//        invitationPane.setLayoutX(200);
//        invitationPane.setLayoutY(200);
//    }

    private void startGameButton(){
        startGame = new ImageView(ImagesAddress.START_GAME_LOBBY_ICON.getImage());
        startGame.setFitWidth(74);
        startGame.setFitHeight(74);
        startGame.setLayoutX(34);
        startGame.setLayoutY(390);
        root.getChildren().add(startGame);
        startGame.setOnMouseClicked(e -> {
            startGame();
        });
    }

    private void listenerForUpdate(){
        Timeline t = new Timeline(new KeyFrame(Duration.millis(6000),e -> {update();}));
        t.setCycleCount(Animation.INDEFINITE);
        t.play();
    }

    private void startGame(){
        Request request = new Request("Method Invoke");
        request.setToken("");
        request.addData("method", "/game/start");
        request.addData("args", new Object[]{});
        Response response = NetworkController.send(request);
    }

}
