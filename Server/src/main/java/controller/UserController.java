package controller;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import lombok.SneakyThrows;
import model.User;
import network.*;
import org.bson.Document;
import utils.Converter;

public class UserController {
	private static final MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://localhost:27017"));
	private static final MongoDatabase database = mongoClient.getDatabase("Civilization");
	private static final MongoCollection<Document> users = database.getCollection("Players");

	@SneakyThrows
	public static void init() {
	}

	@ClientAPI(ACCESS_LEVEL = AccessLevel.GUEST, path = "/login")
	public static Response login(String username, String password) {
		Document document = users.find(new Document("username", username)).first();
		if (document == null) {
			return new Response(400, "User not found");
		}
		String passwordInDB = document.getString("password");
		if (!passwordInDB.equals(password)) {
			return new Response(400, "Wrong password");
		}
		User user = new User(
				username,
				password,
				document.getString("nickname"),
				document.getString("avatarUrl"),
				AccessLevel.valueOf(document.getString("accessLevel")));
		user.setScore(document.getInteger("score"));
		String token = TokenHandler.generateToken(user);
		ClientHandler.currentUser.set(user);
		ClientHandler.listeners.put(user, ClientHandler.listenerOutputStream.get());

		return new Response(200, "Login successful").addData("user", user).addData("token", token);
	}

	@ClientAPI(ACCESS_LEVEL = AccessLevel.GUEST, path = "/register")
	public static Response register(String username, String password, String nickname, String avatarUrl) {
		Document document = users.find(new Document("username", username)).first();
		if (document != null) {
			return new Response(200, "User already exists");
		}
		User user = new User(username, password, nickname, avatarUrl, AccessLevel.USER);
		users.insertOne(user.toDocument());
		return new Response(400, "User created");
	}

	@ClientAPI(ACCESS_LEVEL = AccessLevel.USER, path = "/changeNickname")
	public static Response changeNickname(String username, String nickname) {
		Document document = users.find(new Document("username", username)).first();
		if (document == null) {
			return new Response(400, "User not found");
		}
		if(users.find(new Document("nickname", nickname)).first() != null) {
			return new Response(400, "Nickname already exists");
		}
		document.put("nickname", nickname);
		users.replaceOne(new Document("username", username), document);
		return new Response(200, "Nickname changed");
	}

	@ClientAPI(ACCESS_LEVEL = AccessLevel.USER, path = "/changePassword")
	public static Response changePassword(String username, String oldPassword, String newPassword) {
		Document document = users.find(new Document("username", username)).first();
		if (document == null) {
			return new Response(400, "User not found");
		}
		String passwordInDB = document.getString("password");
		if (!passwordInDB.equals(oldPassword)) {
			return new Response(400, "Wrong password");
		}
		document.put("password", newPassword);
		users.replaceOne(new Document("username", username), document);
		return new Response(200, "Password changed");
	}
}
