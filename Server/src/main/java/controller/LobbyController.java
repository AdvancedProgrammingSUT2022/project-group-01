package controller;

import model.Game;
import model.Lobby;
import model.User;
import network.AccessLevel;
import network.ClientAPI;
import network.ClientHandler;
import network.Response;

import java.util.List;
import java.util.Vector;
import java.util.concurrent.atomic.AtomicBoolean;

public class LobbyController {

	@ClientAPI(ACCESS_LEVEL = AccessLevel.USER, path = "/game/start")
	public static Response startGame() {
		User user = ClientHandler.currentUser.get();
		if(user.getCurrentLobby() == null)
			return new Response(400, "You are not in a lobby");
//		if (user.getCurrentLobby().getUsers().size() < 2)
//			return new Response(400, "Not enough players");
		if(user.getCurrentLobby().getOwner() != user)
			return new Response(400, "You are not the owner of this lobby");
		GameInitializer gameInitializer = new GameInitializer();
		Game game =  gameInitializer.startGame(
				user.getCurrentLobby().getUsers(),
				user.getCurrentLobby().getMapSize()
		);
		game.notifyUsers();
		return new Response(200, "Game started");
	}

	@ClientAPI(ACCESS_LEVEL = AccessLevel.USER, path = "/game/create")
	public static Response createGame(int mapSize) {
		Response response = new Response(200, "Game created");
		Lobby lobby = new Lobby(mapSize, ClientHandler.currentUser.get());
		response.addData("game code", lobby.getCode());
		return response;
	}

	@ClientAPI(ACCESS_LEVEL = AccessLevel.USER, path = "/game/join")
	public static Response joinGame(String code) {
		AtomicBoolean found = new AtomicBoolean(false);
		Lobby.lobbies.stream().filter(lobby -> lobby.getCode().equals(code)).findFirst().ifPresent(lobby -> {
			lobby.join(ClientHandler.currentUser.get());
			found.set(true);
		});
		if(!found.get()) {
			return new Response(400, "Game not found");
		}
		return new Response(200, "Game joined");
	}
}
