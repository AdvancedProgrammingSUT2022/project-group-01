//package view.components.lobby;
//
//import com.jfoenix.controls.JFXButton;
//import javafx.scene.control.Label;
//import javafx.scene.control.TextField;
//import javafx.scene.layout.Pane;
//import javafx.scene.paint.Color;
//import javafx.scene.shape.Rectangle;
//import javafx.scene.shape.StrokeType;
//import javafx.scene.text.Font;
//
//
//public class LobbyBuilderPanel {
//    private Pane root;
//    private TextField mapSize;
//    private TextField players;
//    private JFXButton buildLobby;
//
//    private void backInit(){
//        root = new Pane();
//        root.setPrefHeight(258);
//        root.setPrefWidth(490);
//        Rectangle rectangle = new Rectangle();
//        rectangle.setArcHeight(10);
//        rectangle.setArcWidth(10);
//        rectangle.setFill(Color.rgb(52,79,104));
//        rectangle.setHeight(258);
//        rectangle.setWidth(498);
//        rectangle.setLayoutX(-4);
//        rectangle.setStroke(Color.BLACK);
//        rectangle.setStrokeType(StrokeType.INSIDE);
//        root.getChildren().add(rectangle);
//    }
//
//    private void initTextFields(){
//        mapSize = new TextField();
//        mapSize.setLayoutX(41);
//        mapSize.setLayoutY(117);
//
//        players = new TextField();
//        players.setLayoutX(284);
//        players.setLayoutY(71);
//        players.setPrefHeight(26);
//        players.setPrefWidth(159);
//
//        root.getChildren().add(mapSize);
//        root.getChildren().add(players);
//    }
//
//    private void addLabels(){
//        Label mapSizeLabel = new Label("Map Size");
//        mapSizeLabel.setLayoutX(85);
//        mapSizeLabel.setLayoutY(71);
//        mapSizeLabel.setTextFill(Color.rgb(255,243,243));
//        mapSizeLabel.setFont(Font.font("AppleGothic Regular",16));
//
//        Label numPlayers = new Label("Number Of PLayers");
//        numPlayers.setLayoutX(299);
//        numPlayers.setLayoutY(71);
//        numPlayers.setTextFill(Color.rgb(255,243,243));
//        numPlayers.setFont(Font.font("AppleGothic Regular",16));
//        root.getChildren().add(mapSizeLabel);
//        root.getChildren().add(numPlayers);
//    }
//
//    private void startNewLobbyButton(){
//        buildLobby = new JFXButton();
//        buildLobby.setButtonType(JFXButton.ButtonType.RAISED);;
//        buildLobby.setLayoutX(146);
//        buildLobby.setLayoutY(182);
//        buildLobby.setPrefHeight(51);
//        buildLobby.setPrefWidth(198);
//        buildLobby.setText("New Lobby");
//        buildLobby.setTextFill(Color.rgb(255,243,243));
//        root.getChildren().add(buildLobby);
//    }
//}
