package model;

import com.thoughtworks.xstream.annotations.XStreamImplicit;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import controller.UserController;
import lombok.Getter;
import lombok.Setter;
import network.AccessLevel;
import network.ClientHandler;
import network.Update;
import org.bson.Document;
import utils.Converter;

import java.io.DataOutputStream;
import java.util.Date;
import java.util.List;
import java.util.Vector;

@Getter
@Setter
public class User {
	private String username;
	//private undefinedType[] savedGames;//TODO handle here
	private String password;
	private int score;
	private String nickname;
	private String avatarUrl;
	private AccessLevel accessLevel;
	@XStreamOmitField
	private transient Lobby currentLobby = null;
	private Date lastSeen = null;

	private Vector<String> friends = new Vector<>();
	private Vector<String> pendingFriends = new Vector<>();

	public User(String username, String password, String nickname, String avatarUrl, AccessLevel accessLevel) {
		this.nickname = nickname;
		this.username = username;
		this.password = password;
		this.avatarUrl = avatarUrl;
		this.accessLevel = accessLevel;
		this.score = 0;
	}

	public void increaseScore(int score) {
		this.score = score;
	}

	public Document toDocument() {
		return new Document("username", username)
				.append("password", password)
				.append("score", score)
				.append("nickname", nickname)
				.append("avatarUrl", avatarUrl)
				.append("accessLevel", accessLevel.toString())
				.append("lastSeen", lastSeen)
				.append("friends", friends)
				.append("pending", pendingFriends);
	}

	public void updateLastSeen() {
		this.lastSeen = new Date();
		UserController.updateLastSeen(this);
	}
	public void goOnline(){
		this.lastSeen = null;
		UserController.updateLastSeen(this);
	}

	public void sendMessage(Update update) {
		DataOutputStream listener = ClientHandler.listeners.get(this);
		if (listener != null) {
			try {
				listener.writeUTF(Converter.toXML(update));
				listener.flush();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}