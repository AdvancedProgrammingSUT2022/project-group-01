package controller;

import javafx.application.Platform;
import javafx.scene.control.Tab;
import model.Database;
import model.User;
import model.chatroom.Query;
import view.chatroom.Chat;
import view.chatroom.ChatPage;
import view.chatroom.ChatPanel;

import java.util.ArrayList;
import java.util.Vector;

public class ChatController {
	public static void receiveMessage(int chatId, String message, String sender){
		Platform.runLater(() -> {
			ChatPage chatPage = getChatPage(chatId);
			User user = Database.instance.findUserByUsername(sender);
			chatPage.addMessage(user, message, false);
		});
	}

	public static void newChat(Chat chat, Vector<User> _users){
		Platform.runLater(() -> {
//			ArrayList<String> usernames = (ArrayList<String>) query.get("usernames");
			ArrayList<User> users = new ArrayList<>(_users);
			chat.setUsers(users);
			ChatPage chatPage = new ChatPage(ChatPanel.lastInstance.getUser(), users, chat, ChatPanel.lastInstance.getSocketHandler());
			Tab tab = new Tab();
			if (users.size() == 2) {
				int index = 1 - chat.getUsers().indexOf(ChatPanel.lastInstance.getUser());
				tab.setText(chat.getUsers().get(index).getNickname());
			} else {
				tab.setText(chat.getChatName());
			}
			tab.setContent(chatPage);
			ChatPanel.lastInstance.getTabs().add(ChatPanel.lastInstance.getTabs().size() - 1, tab);
		});
	}




	public static ChatPage getChatPage(int id){
		for(Tab tab : ChatPanel.lastInstance.getTabs()){
			if(tab.getContent() instanceof ChatPage){
				ChatPage chatPage = (ChatPage) tab.getContent();
				if(chatPage.getChat().getId() == id)
					return chatPage;
			}
		}
		return null;
	}
}
