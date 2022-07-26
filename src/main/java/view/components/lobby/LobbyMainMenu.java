package view.components.lobby;

import controller.GUIController;
import controller.NetworkController;
import controller.ProgramController;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import lombok.Getter;
import model.Lobby;
import network.Request;
import network.Response;
import view.Main;
import view.components.ImagesAddress;
import view.components.popup.PopUp;
import view.components.popup.PopUpStates;

import java.util.Vector;


public class LobbyMainMenu {
	@Getter
    private Pane backPane;
    private VBox gamesBox;
    private ImageView newGameButton;
    private ImageView refreshButton;
    private TextField searchField;
    private ImageView searchButton;

    public LobbyMainMenu(){
        initBackPane();
        initScrollPane();
        newGameButton();
		initSearchField();
        refreshButton();initSearchButton();
    }
    private void initBackPane(){
        backPane = new Pane();
        backPane.setPrefWidth(1280);
        backPane.setPrefHeight(720);
        backPane.setStyle("-fx-background-image: url(asset/lobby/background.jpg);");
    }

    private void initScrollPane(){
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setLayoutX(90);
        scrollPane.setLayoutY(110);
        scrollPane.setPrefWidth(685);
        scrollPane.setPrefHeight(570);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setStyle("-fx-background: transparent");
        scrollPane.setStyle("-fx-background-color: transparent");
		scrollPane.getStylesheets().add(String.valueOf(Main.class.getResource("/CSS/InvitationMenu.css")));
		scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
		scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        backPane.getChildren().add(scrollPane);
        gamesBox = new VBox();
        gamesBox.setPrefWidth(685);
        gamesBox.setStyle("-fx-background-color: transparent");
        scrollPane.setContent(gamesBox);
    }

    private void newGameButton(){
        newGameButton = new ImageView();
        newGameButton.setLayoutX(680);
        newGameButton.setLayoutX(39);
        newGameButton.setFitHeight(65);
        newGameButton.setFitWidth(65);
        newGameButton.setPickOnBounds(true);
        newGameButton.setPreserveRatio(true);
        newGameButton.setImage(ImagesAddress.LOBBY_NEW_GAME_BUTTON.getImage());
        newGameButton.setOnMouseClicked(e -> {
            System.out.println("shit");
//            (new LobbyVisual())
        });
        backPane.getChildren().add(newGameButton);
    }

    private void refreshButton(){
        refreshButton = new ImageView();
        refreshButton.setLayoutX(105);
        refreshButton.setLayoutY(28);
        refreshButton.setFitHeight(65);
        refreshButton.setFitWidth(65);
        refreshButton.setPickOnBounds(true);
        refreshButton.setPreserveRatio(true);
        refreshButton.setImage(ImagesAddress.LOBBY_REFRESH_BUTTON.getImage());
        refreshButton.setOnMouseClicked(e -> {
            gamesBox.getChildren().clear();
            addRandomLobbiesToList();
        });
        backPane.getChildren().add(refreshButton);
    }

    private void initSearchField(){
        searchField = new TextField();
        searchField.setLayoutX(848);
        searchField.setLayoutY(51);
        searchField.setPrefWidth(325);
        searchField.setPrefHeight(26);
        // add search field style sheet //TODO
        backPane.getChildren().add(searchField);
    }

    private void initSearchButton(){
        searchButton = new ImageView(ImagesAddress.SEARCH.getImage());
        searchButton.setLayoutX(1197);
        searchButton.setLayoutY(40);
        searchButton.setFitHeight(48);
        searchButton.setFitWidth(48);
        searchButton.setPickOnBounds(true);
        searchButton.setPreserveRatio(true);
        searchButton.setOnMouseClicked(e -> {
            searchLobby();
        });
        backPane.getChildren().add(searchButton);
    }

    private void addRandomLobbiesToList(){
        gamesBox.getChildren().clear();
        Vector<Lobby> allLobbies = Lobby.getLobbies();
        int size = allLobbies.size();
        Vector<Lobby> chosenLobbies = new Vector<>();
        int j = 10;
        for(int i =0; i < size && j > 0; i++ ){
            if(allLobbies.get(i).isPublic()){
                chosenLobbies.add(allLobbies.get(i));
                j--;
            }
        }
        for(Lobby l : chosenLobbies){
            LobbyGameItem lgi = new LobbyGameItem(l);
            gamesBox.getChildren().add(lgi.getRoot());
        }
    }

    private void searchLobby(){
//        for(Lobby l : Lobby.lobbies){
//            if(l.getCode().equals(searchField.getText())){
//                enterLobby(l);
//
//				break;
//            }
//        }
		Request request = new Request("Method Invoke");
		request.setToken("");
		request.addData("method", "/game/join").addData("args", new Object[]{searchField.getText()});
		Response response = NetworkController.send(request);
		if(response.getStatus() == 400)
			new PopUp().run(PopUpStates.ERROR, response.getMessage());
    	else if(response.getStatus() == 200)
			GUIController.changeMenuManually(
					(new LobbyVisual((Lobby) response.get("lobby"), ProgramController.getLoggedInUser())).getRoot()
			);
	}
}
