package model;

import com.thoughtworks.xstream.annotations.XStreamImplicit;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import lombok.Getter;
import lombok.Setter;
import network.AccessLevel;
import network.ClientHandler;
import org.bson.Document;

import java.io.DataOutputStream;

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
	private Lobby currentLobby = null;

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
				.append("accessLevel", accessLevel.toString());
	}

	public void sendMessage(String message) {
		DataOutputStream listener = ClientHandler.listeners.get(this);
		if (listener != null) {
			System.out.println(password);
			try {
				listener.writeUTF(message);
				listener.flush();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}