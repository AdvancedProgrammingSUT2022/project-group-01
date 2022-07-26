package model;

import controller.GameInitializer;
import lombok.Getter;
import lombok.Setter;
import network.ClientHandler;

import javax.swing.event.CaretListener;
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

	public static String generateCode(){
		StringBuilder code = new StringBuilder();
		for(int i = 0; i < 6; i++){
			code.append((char) ((int) (Math.random() * 26) + 97));
		}
		return code.toString();
	}

	public Lobby(int mapSize, int capacity, User owner) {
		this.code = generateCode();
		this.mapSize = mapSize;
		this.owner = owner;
		this.capacity = capacity;
		lobbies.add(this);
		join(owner);
	}

	public void start(){
		GameInitializer gameInitializer = new GameInitializer();
		Game game = gameInitializer.startGame(
				users,
				mapSize
		);
		game.sendToPlayers();
	}

	public void join(User user){
		users.add(user);
		user.setCurrentLobby(this);
		if(users.size() == capacity)
			start();
	}

	public void leave(User user){
		users.remove(user);
		user.setCurrentLobby(null);
		if(users.isEmpty())
			cancel();
		if(owner == user)
			owner = users.firstElement();
	}

	public void cancel(){
		lobbies.remove(this);
	}

}
