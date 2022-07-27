package controller;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import lombok.SneakyThrows;
import model.User;
import network.*;
import org.bson.Document;
import view.component.ImagesAddress;

import javax.print.Doc;
import java.util.Iterator;
import java.util.Vector;

public class UserController {
	private static final MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://localhost:27017"));
	private static final MongoDatabase database = mongoClient.getDatabase("Civilization");
	private static final MongoCollection<Document> users = database.getCollection("Players");

	@SneakyThrows
	public static void init() {
	}

	@ClientAPI(ACCESS_LEVEL = AccessLevel.GUEST, path = "/login") // Merged
	public static Response login(String username, String password) {
		Document document = users.find(new Document("username", username)).first();
		if (document == null) {
			return new Response(400, "Username and password didn't match!");
		}
		String passwordInDB = document.getString("password");
		if (!passwordInDB.equals(password)) {
			return new Response(400, "Username and password didn't match!");
		}
		User user = new User(
				username,
				password,
				document.getString("nickname"),
				document.getString("avatarUrl"),
				AccessLevel.valueOf(document.getString("accessLevel")));
		user.setScore(document.getInteger("score"));
		user.goOnline();
		String token = TokenHandler.generateToken(user);
		ClientHandler.currentUser.set(user);
		ClientHandler.listeners.put(user, ClientHandler.listenerOutputStream.get());
		TOF.users.add(user);
		return new Response(200, "user logged in successfully!").addData("user", user).addData("token", token);
	}

	@ClientAPI(ACCESS_LEVEL = AccessLevel.USER, path = "/logout") // Merged
	public static Response logout() {
		User user = ClientHandler.currentUser.get();
		ClientHandler.currentUser.set(null);
		user.updateLastSeen();
		return new Response(200, "Done!");
	}

	@ClientAPI(ACCESS_LEVEL = AccessLevel.GUEST, path = "/register") // Merged
	public static Response register(String username, String password, String nickname, String avatarUrl) {
		System.out.println("avalesh");
		Document document = users.find(new Document("username", username)).first();
		if (document != null) {
			return new Response(200, String.format("user with username %s already exist!", username));
		}
		document = users.find(new Document("nickname", nickname)).first();
		if (document != null) {
			return new Response(200, String.format("user with nickname %s already exist!", nickname));
		}
		User user = new User(username, password, nickname, avatarUrl, AccessLevel.USER);
		users.insertOne(user.toDocument());
		return new Response(400, "user created successfully!");
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

	@ClientAPI(ACCESS_LEVEL = AccessLevel.USER, path = "/setUsername")
	public static Response setUsername(String username, String newUsername){
		Document document = users.find(new Document("username", username)).first();
		if(document == null)
			return new Response(400,"User not found");
		Document document1 = users.find(new Document("username",newUsername)).first();
		if(document1 != null)
			return new Response(400,"Username exists");
		document.put("username",newUsername);
		users.replaceOne(new Document("username",username),document);
		return new Response(200,"Username changed");
	}

	@ClientAPI(ACCESS_LEVEL = AccessLevel.USER, path = "/setPassword")
	public static Response setPassword(String username,String password){
		System.out.println("helll");
		Document document = users.find(new Document("username", username)).first();
		if(document == null)
			return new Response(400,"User not found");
		document.put("password",password);
		users.replaceOne(new Document("username",username), document);
		return new Response(200,"Password Changed");
	}

	@ClientAPI(ACCESS_LEVEL = AccessLevel.USER, path = "/increaseScore")
	public static Response increaseScore(String username, int score){
		Document document = users.find(new Document("username", username)).first();
		if(document == null)
			return new Response(400,"User not found");
		document.put("score",score);
		users.replaceOne(new Document("username",username), document);
		return new Response(200,"Score Changed");
	}

	@ClientAPI(ACCESS_LEVEL =  AccessLevel.USER, path = "/setAvatarUrl")
	public static Response changeAvatarUrl(String username, String avatarUrl){
		Document document = users.find(new Document("username", username)).first();
		if(document == null)
			return new Response(400,"User not found");
		document.put("avatarUrl",avatarUrl);
		users.replaceOne(new Document("username",username), document);
		return new Response(200,"Avatar Changed");
	}

	public static void updateLastSeen(User user) {
		Document document = users.find(new Document("username", user.getUsername())).first();
		if (document == null) {
			return;
		}
		document.put("lastSeen", user.getLastSeen());
		users.replaceOne(new Document("username", user.getUsername()), document);
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

	@ClientAPI(ACCESS_LEVEL = AccessLevel.USER, path = "/friend/send")
	public static Response sendFriendRequest(String username) {
		User user = ClientHandler.currentUser.get();
		Document friend = users.find(new Document("username", username)).first();
		if (friend == null) {
			return new Response(400, "User not found");
		}
		if (user.getFriends().contains(username)) {
			return new Response(400, "User already your friend");
		}
//		friend.get("pending").add(user.getUsername(), user.getNickname());

		return new Response(200, "Friend request sent");
	}

	@ClientAPI(ACCESS_LEVEL = AccessLevel.USER, path = "/getAllUsers")
	public static Response getAllUsers(){
		FindIterable<Document> documents = users.find();
		Vector<User> users = new Vector<>();
		Iterator<Document> it = documents.iterator();
		while(it.hasNext()){
			Document document = it.next();
			User user = new User(
					document.getString("username"),
					document.getString("password"),
					document.getString("nickname"),
					document.getString("avatarUrl"),
					AccessLevel.valueOf(document.getString("accessLevel")));
			user.setScore(document.getInteger("score"));
			user.setLastSeen(document.getDate("lastSeen"));
			users.add(user);
		}
		for(User u : users) System.out.println(u.getLastSeen());
		return (new Response(200,"done")).addData("users",users);
	}


}
