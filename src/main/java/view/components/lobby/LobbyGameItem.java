package view.components.lobby;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import model.Lobby;
import model.Player;
import model.User;
import view.components.ImagesAddress;


public class LobbyGameItem {
    private Pane root;
    private ImageView backImage;
    private VBox listOfUsers;
    private Lobby lobby;


    public LobbyGameItem(Lobby lobby) {
        this.lobby = lobby;
        init();
    }

    public Pane getRoot(){
        return root;
    }

    private void init(){
        initPane();
        initLabels();
        addPlayersPane();
        addPlayersData();
    }
    private void initPane(){
        root = new Pane();
        root.setPrefHeight(333);
        root.setPrefWidth(600);
        root.setStyle("-fx-background-color: transparent");

        backImage = new ImageView();
        backImage.setFitHeight(333);
        backImage.setFitWidth(600);
        backImage.setImage(ImagesAddress.LOBBY_ITEM_BACKGROUND.getImage());
        backImage.setPickOnBounds(true);
        backImage.setPreserveRatio(true);
        root.getChildren().add(backImage);

    }

    private void initLabels(){
        Label id = new Label();
        id.setText(lobby.getCode());
        id.setTextAlignment(TextAlignment.CENTER);
        id.setAlignment(Pos.CENTER);
        id.setPrefHeight(35);
        id.setPrefWidth(303);
        id.setLayoutX(149);
        id.setLayoutY(38);
        id.setTextFill(Color.WHITESMOKE);
        id.setFont(Font.font("Avenit Book",23));
        root.getChildren().add(id);

        Label preferredNumberOfPlayers = new Label();
        preferredNumberOfPlayers.setText(String.valueOf(lobby.getMapSize()));
        preferredNumberOfPlayers.setLayoutX(511);
        preferredNumberOfPlayers.setLayoutY(145);
        preferredNumberOfPlayers.setTextFill(Color.rgb(168,9,9));
        preferredNumberOfPlayers.setFont(Font.font("Avenit Book",15));
        preferredNumberOfPlayers.setPrefHeight(43);
        preferredNumberOfPlayers.setPrefWidth(75);
        root.getChildren().add(preferredNumberOfPlayers);
    }

    private void addPlayersPane(){
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setLayoutX(183);
        scrollPane.setLayoutY(243);
        scrollPane.setPrefHeight(76);
        scrollPane.setPrefWidth(267);
        scrollPane.setStyle("-fx-background: transparent; -fx-background-color: transparent;");
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        listOfUsers = new VBox();
        listOfUsers.setSpacing(15);
        listOfUsers.setAlignment(Pos.CENTER);
        listOfUsers.setPrefWidth(264);
        scrollPane.setContent(listOfUsers);
        root.getChildren().add(scrollPane);
    }

    private void addPlayersData(){
        for(User u : lobby.getUsers()){
            Label l = new Label(u.getNickname());
            l.setTextFill(Color.WHITESMOKE);
            l.setFont(Font.font("Avenit Book",17));
            l.setTextAlignment(TextAlignment.CENTER);
            l.setAlignment(Pos.CENTER);
            l.setPrefWidth(262);
            listOfUsers.getChildren().add(l);
        }
    }

}
