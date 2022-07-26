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
		user.getCurrentLobby().start();
		return new Response(200, "Game started");
	}

	@ClientAPI(ACCESS_LEVEL = AccessLevel.USER, path = "/game/create")
	public static Response createGame(int mapSize, int capacity) {
		Response response = new Response(200, "Game created");
		Lobby lobby = new Lobby(mapSize, capacity, ClientHandler.currentUser.get());
		response.addData("game code", lobby.getCode());
		return response;
	}

	@ClientAPI(ACCESS_LEVEL = AccessLevel.USER, path = "/game/join")
	public static Response joinGame(String code) {
		Lobby found = null;
		for (Lobby lobby : Lobby.lobbies) {
			if (lobby.getCode().equals(code))
				found = lobby;
		}

		if(found == null) {
			return new Response(400, "Game not found");
		}
		found.join(ClientHandler.currentUser.get());
		return new Response(200, "Game joined").addData("lobby", found);
	}

	// leave
	@ClientAPI(ACCESS_LEVEL = AccessLevel.USER, path = "/game/leave")
	public static Response leaveGame() {
		User user = ClientHandler.currentUser.get();
		if(user.getCurrentLobby() == null)
			return new Response(400, "You are not in a lobby");
		user.getCurrentLobby().leave(user);
		return new Response(200, "Game left");
	}

	// private public
	@ClientAPI(ACCESS_LEVEL = AccessLevel.USER, path = "/game/makePublic")
	public static Response makeGamePublic() {
		User user = ClientHandler.currentUser.get();
		if(user.getCurrentLobby() == null)
			return new Response(400, "You are not in a lobby");
		if(user.getCurrentLobby().getOwner() != user)
			return new Response(400, "You are not the owner of this lobby");
		user.getCurrentLobby().setPublic(true);
		return new Response(200, "Game is public now");
	}

	@ClientAPI(ACCESS_LEVEL = AccessLevel.USER, path = "/game/makePrivate")
	public static Response makeGamePrivate() {
		User user = ClientHandler.currentUser.get();
		if(user.getCurrentLobby() == null)
			return new Response(400, "You are not in a lobby");
		if(user.getCurrentLobby().getOwner() != user)
			return new Response(400, "You are not the owner of this lobby");
		user.getCurrentLobby().setPublic(false);
		return new Response(200, "Game is public now");
	}

	// getLobbies
	@ClientAPI(ACCESS_LEVEL = AccessLevel.USER, path = "/game/get")
	public static Response getLobbies(){
		User user = ClientHandler.currentUser.get();
		Vector<Lobby> lobbies = new Vector<>();
		for (Lobby lobby : Lobby.lobbies) {
			if(lobby.isPublic() || user == lobby.getOwner())
				lobbies.add(lobby);
		}
		Response response = new Response(200, "Here List of available Lobbies");
		response.addData("lobbies", lobbies);
		return response;
	}

	//
//	@ClientAPI(ACCESS_LEVEL = AccessLevel.ADMIN, path = "/game/cancel")
//	public static Response cancelGame(){
//		User user = ClientHandler.currentUser.get();
//		if(user.getCurrentLobby() == null)
//			return new Response(400, "You are not in a lobby");
//		if(user.getCurrentLobby().getOwner() != user)
//			return new Response(400, "You are not the owner of this lobby");
//		user.getCurrentLobby().cancel();
//		return new Response(200, "Game is deleted");
//	}


}
