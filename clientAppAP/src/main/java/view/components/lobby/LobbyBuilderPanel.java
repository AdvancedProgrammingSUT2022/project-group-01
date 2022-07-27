package view.components.lobby;

import com.jfoenix.controls.JFXButton;
import controller.GUIController;
import controller.NetworkController;
import controller.ProgramController;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Font;
import model.Lobby;
import network.Request;
import network.Response;
import view.components.ImagesAddress;


public class LobbyBuilderPanel {
    private Pane root;
    private TextField mapSize;
    private TextField players;
    private JFXButton buildLobby;
    private Pane backPane;

    public LobbyBuilderPanel(Pane backPane) {
        this.backPane = backPane;
        backInit();
        initTextFields();
        addLabels();
        startNewLobbyButton();
    }

    public Pane getRoot(){
        return root;
    }

    private void backInit(){
        root = new Pane();
        root.setPrefHeight(258);
        root.setPrefWidth(490);
        Rectangle rectangle = new Rectangle();
        rectangle.setArcHeight(10);
        rectangle.setArcWidth(10);
        rectangle.setFill(Color.rgb(52,79,104));
        rectangle.setHeight(258);
        rectangle.setWidth(498);
        rectangle.setLayoutX(-4);
        rectangle.setStroke(Color.BLACK);
        rectangle.setStrokeType(StrokeType.INSIDE);
        root.getChildren().add(rectangle);
    }

    private void initTextFields(){
        mapSize = new TextField();
        mapSize.setLayoutX(41);
        mapSize.setLayoutY(117);

        players = new TextField();
        players.setLayoutX(284);
        players.setLayoutY(117);
        players.setPrefHeight(26);
        players.setPrefWidth(159);

        root.getChildren().add(mapSize);
        root.getChildren().add(players);
    }

    private void addLabels(){
        Label mapSizeLabel = new Label("Map Size");
        mapSizeLabel.setLayoutX(85);
        mapSizeLabel.setLayoutY(71);
        mapSizeLabel.setTextFill(Color.rgb(255,243,243));
        mapSizeLabel.setFont(Font.font("AppleGothic Regular",16));

        Label numPlayers = new Label("Number Of PLayers");
        numPlayers.setLayoutX(299);
        numPlayers.setLayoutY(71);
        numPlayers.setTextFill(Color.rgb(255,243,243));
        numPlayers.setFont(Font.font("AppleGothic Regular",16));
        root.getChildren().add(mapSizeLabel);
        root.getChildren().add(numPlayers);
    }

    private void startNewLobbyButton(){
        buildLobby = new JFXButton();
        buildLobby.setButtonType(JFXButton.ButtonType.RAISED);;
        buildLobby.setLayoutX(146);
        buildLobby.setLayoutY(182);
        buildLobby.setPrefHeight(51);
        buildLobby.setPrefWidth(198);
        buildLobby.setText("New Lobby");
        buildLobby.setTextFill(Color.rgb(255,243,243));
        buildLobby.setOnMouseClicked(e -> {
            backPane.getChildren().remove(root);
            createLobby();
        });
        root.getChildren().add(buildLobby);
    }

    private void createLobby() {
        int mapSizeNumber = Integer.parseInt(mapSize.getText());
        int playerSize = Integer.parseInt(players.getText());
        Request request = new Request("Method Invoke");
        request.setToken("");
        request.addData("method", "/game/create");
        request.addData("args", new Object[]{mapSizeNumber,playerSize});
        Response response = NetworkController.send(request);
        GUIController.changeMenuManually(
                (new LobbyVisual((Lobby) response.get("lobby"), ProgramController.getLoggedInUser())).getRoot()
        );
    }
}
