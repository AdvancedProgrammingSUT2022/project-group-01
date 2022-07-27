package model;

import controller.NetworkController;
import lombok.Getter;
import lombok.Setter;
import network.Request;
import network.Response;

import java.util.Vector;

@Getter @Setter
public class Lobby {
	public static final Vector<Lobby> lobbies = new Vector<>();

	private final Vector<User> users = new Vector<>();
	private final String code;
	private final int mapSize;
	private final int capacity;
	private User owner;
	private boolean isPublic = false;

	public Lobby(int mapSize, int capacity, User owner) {
		this.code = null;
		this.mapSize = mapSize;
		this.owner = owner;
		this.capacity = capacity;
		lobbies.add(this);
	}

	public static Vector<Lobby> getLobbies(){
		Request request = new Request("Method Invoke");
		request.setToken("");
		request.addData("method", "/game/get").addData("args", new Object[]{});
		Response response = NetworkController.send(request);
		return (Vector<Lobby>) response.get("lobbies");
	}

	// TODO send setPublic to server

	public void removeUser(User user) {
		users.remove(user);
	}
}
