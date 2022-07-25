package model;

import lombok.Getter;

import java.util.Vector;

@Getter
public class Lobby {
	public static final Vector<Lobby> lobbies = new Vector<>();

	private final Vector<User> users = new Vector<>();
	private final String code;
	private final int mapSize;
	private final User owner;

	public static String generateCode(){
		StringBuilder code = new StringBuilder();
		for(int i = 0; i < 6; i++){
			code.append((char) ((int) (Math.random() * 26) + 97));
		}
		return code.toString();
	}

	public Lobby(int mapSize, User owner) {
		this.code = generateCode();
		this.mapSize = mapSize;
		this.owner = owner;
		lobbies.add(this);
		join(owner);
	}

	public void join(User user){
		users.add(user);
		user.setCurrentLobby(this);
	}

	public void leave(User user){
		users.remove(user);
		user.setCurrentLobby(null);
	}

	public void cancel(){
		lobbies.remove(this);
	}
}
