package controller;

import lombok.SneakyThrows;
import model.Chat;
import model.Message;
import model.User;
import network.*;

import java.util.ArrayList;
import java.util.Vector;

public class ChatController {
	public static void receiveMessage(int chatId, String message, String sender){}
	public static void newChat(Chat chat, Vector<User> _users){}

	@SneakyThrows
	@ClientAPI(ACCESS_LEVEL = AccessLevel.USER, path = "/chat/send")
	public static Response sendMessage(String message, int chatId) {
		Chat chat = Chat.getChat(chatId);
		User sender = ClientHandler.currentUser.get();
		chat.sendMessage(message, sender);
		Vector<String> usernames = new Vector<>();
		for(User receiver : chat.getUsers()){
			if(receiver != sender){
				Update update = new Update("Method Invoke");
				update.addData("method", ChatController.class.getMethod("receiveMessage", int.class, String.class, String.class));
				update.addData("args", new Object[]{chatId, message, sender.getUsername()});
				try {
					receiver.sendMessage(update);
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
		}
		return new Response(200, "Message sent");
	}

	private static User findUserByUsername(String username){
		User found = null;
		for (User user : TOF.users) {
			if(user.getUsername().equals("username"))
				found = user;
		}
		return found;
	}

	@SneakyThrows
	@ClientAPI(ACCESS_LEVEL = AccessLevel.USER, path = "chat/new")
	public static Response newChat(ArrayList<String> usernames, String chatName){
		System.out.println("Here ?");
		Vector<User> users = new Vector<>();
		for(String username : usernames){
			User user = findUserByUsername(username);
			if(user == null) {
				System.out.println("NOOOOOOO " + username);
//				return new Response(400, "Invalid User");
			} else {
				System.out.println("YESSSSSSSS ");
				users.add(user);
			}
		}
		Chat chat = new Chat(users);
		if(users.size() != 2)
			chat.setChatName(chatName);
		Update update = new Update("Method Invoke");
		update.addData("method", ChatController.class.getMethod("newChat", Chat.class, Vector.class));
		update.addData("args", new Object[]{chat, users});
//		sendOutput(query.toJson());
		for(User user : users){
			try {
				System.out.println(user.getUsername());
				user.sendMessage(update);
//				pairs.get(user).sendFromSpeaker(query.toJson());
			}catch(Exception e){
			}
		}
		return new Response(200,"just sth");
	}




}
