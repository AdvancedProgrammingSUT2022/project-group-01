package view.components.infoPanels;

import controller.GUIController;
import controller.GameController;
import controller.ProgramController;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import model.Player;
import model.civilization.Person;
import view.Main;
import view.components.ImagesAddress;

import java.util.Vector;


public class GameEndPage {
    private Pane root;
    private GameController gameController;
    private VBox list;

    public GameEndPage(GameController gameController) {
        this.gameController = gameController;
        initialize();
        addPlayers();
        closeButton();
    }

    public Pane getRoot() {
        return root;
    }
    private void initialize(){
        root = new Pane();
        root.setPrefHeight(720);
        root.setPrefWidth(1280);
        root.setStyle("-fx-background-image: url(asset/other/endGame/endGameBackground.jpg)");
        Label title = new Label("Game Over");
        title.setFont(Font.font("Verdana", 18));
        title.setTextAlignment(TextAlignment.CENTER);
        title.setAlignment(Pos.CENTER);
        title.setTextFill(Color.WHITE);
        title.setLayoutX(438);
        title.setLayoutY(44);
        title.setPrefHeight(46);
        title.setPrefWidth(405);

        root.getChildren().add(title);

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setLayoutX(328);
        scrollPane.setLayoutY(111);
        scrollPane.prefHeight(580);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.prefWidth(644);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setStyle("-fx-background: transparent");
        scrollPane.setStyle("-fx-background-color: transparent");
        scrollPane.getStylesheets().add(String.valueOf(Main.class.getResource("/CSS/InvitationMenu.css")));
        list = new VBox();
        list.setSpacing(15);
        list.setPrefHeight(577);
        list.setPrefWidth(639);
        list.setStyle("-fx-background-color: transparent; -fx-background: transparent");
        scrollPane.setContent(list);
        root.getChildren().add(scrollPane);

    }

    private Pane getEndGameItem(Player player, int rank){
        Pane back = new Pane();
        back.setPrefWidth(553);
        back.setPrefHeight(99);
        back.setStyle("-fx-background-color: transparent");

        ImageView itemBackImage = new ImageView(ImagesAddress.END_GAME_ITEM.getImage());
        itemBackImage.setFitHeight(83);
        itemBackImage.setFitWidth(390);
        itemBackImage.setLayoutY(17);
        itemBackImage.setPickOnBounds(true);
        itemBackImage.setPreserveRatio(true);
        back.getChildren().add(itemBackImage);
        ImageView scoreImage = new ImageView();
        if(rank == 1)
            scoreImage.setImage(ImagesAddress.GOLD_SIDE_HEX.getImage());
        else
            scoreImage.setImage(ImagesAddress.BLUE_SIDE_HEX.getImage());
        scoreImage.setFitHeight(106);
        scoreImage.setFitWidth(117);
        scoreImage.setLayoutX(413);
        scoreImage.setLayoutY(-4);
        scoreImage.setPickOnBounds(true);
        scoreImage.setPreserveRatio(true);
        back.getChildren().add(scoreImage);
        Label playerName = new Label();
        playerName.setLayoutX(72);
        playerName.setLayoutY(31);
        playerName.setPrefHeight(37);
        playerName.setPrefWidth(214);
        playerName.setText(player.getUser().getNickname());
        playerName.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
        playerName.setTextFill(Color.rgb(216,218,238));
        playerName.setFont(Font.font("AppleGothic Regular",23));
        back.getChildren().add(playerName);
        Label score = new Label();
        score.setLayoutX(446);
        score.setLayoutY(34);
        int scoreNumber = gameController.getScores().get(player);
        score.setText(String.valueOf(scoreNumber));
        score.setAlignment(Pos.CENTER);
        score.setTextFill(Color.BLACK);
        score.setFont(Font.font("AppleGothic Regular",23));
        score.setTextAlignment(TextAlignment.CENTER);
        back.getChildren().add(score);

        return back;
    }

    private void addPlayers(){
        Vector<Player> players = new Vector<>(gameController.getScores().keySet());
        players.sort((p1, p2) -> gameController.getScores().get(p2) - gameController.getScores().get(p1));
        for(int i = 0; i < players.size(); i++){
            System.out.println("player "+ i);
            list.getChildren().add(getEndGameItem(players.get(i), i+1));
        }
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
            ProgramController.setGame(null);
            GUIController.changeMenu("preMainMenu");
        });
    }

}
