package controller;

import javafx.application.Platform;
import javafx.scene.control.Tab;
import lombok.SneakyThrows;
import model.Database;
import model.User;
import model.chatroom.Query;
import view.chatroom.Chat;
import view.chatroom.ChatPage;
import view.chatroom.ChatPanel;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class SocketThread extends Thread {
    private final Socket socket;
    private DataInputStream inputStream;
    private DataOutputStream outputStream;
    private final ChatPanel chatPanel;

    @SneakyThrows
    public SocketThread(Socket socket, ChatPanel chatPanel) {
        this.socket = socket;
//        this.inputStream = new DataInputStream(socket.getInputStream());
//        this.outputStream = new DataOutputStream(socket.getOutputStream());
        this.chatPanel = chatPanel;
    }

    @SneakyThrows
    @Override
    public void run() {
        while (true) {
//            Query query = Query.fromJson(inputStream.readUTF());
//            handle(query);
        }
    }

    public void handle(Query query){
        switch(query.getHeader()){
            case "new_message":{sendMessage(query);}break;
            case "edit_message":{editMessage(query);}break;
            case "delete_message":{deleteMessage(query);}break;
            case "new_chat":{newChat(query);}break;
        }
    }

    public void sendMessage(Query query){
        Platform.runLater(() -> {
            ChatPage chatPage = getChatPage((Integer) query.get("chatId"));
            String text = (String) query.get("message");
            User user = Database.instance.findUserByUsername((String) query.get("sender"));
            chatPage.addMessage(user, text, false);
        });
    }

    public void editMessage(Query query){
        Platform.runLater(() -> {
            ChatPage chatPage = getChatPage((Integer) query.get("chatId"));
            String text = (String) query.get("new_message");
            int messageId = (int) query.get("message_id");
            chatPage.editMessage(text, messageId);
        });
    }

    public void deleteMessage(Query query){
        Platform.runLater(() -> {
            ChatPage chatPage = getChatPage((Integer) query.get("chatId"));
            int messageId = (int) query.get("message_id");
            chatPage.deleteMessage(messageId);
        });
    }

    public void newChat(Query query){
        Platform.runLater(() -> {
            Chat chat = (Chat) query.get("chat");
            ArrayList<String> usernames = (ArrayList<String>) query.get("usernames");
            ArrayList<User> users = new ArrayList<>();
            for (String username : usernames)
                users.add(Database.instance.findUserByUsername(username));
            chat.setUsers(users);
            ChatPage chatPage = new ChatPage(chatPanel.getUser(), users, chat, chatPanel.getSocketHandler());
            Tab tab = new Tab();
            if (usernames.size() == 2) {
                int index = 1 - chat.getUsers().indexOf(chatPanel.getUser());
                tab.setText(chat.getUsers().get(index).getNickname());
            } else {
                tab.setText(chat.getChatName());
            }
            tab.setContent(chatPage);
            chatPanel.getTabs().add(chatPanel.getTabs().size() - 1, tab);
        });
    }

    public ChatPage getChatPage(int id){
        for(Tab tab : chatPanel.getTabs()){
            if(tab.getContent() instanceof ChatPage){
                ChatPage chatPage = (ChatPage) tab.getContent();
                if(chatPage.getChat().getId() == id)
                    return chatPage;
            }
        }
        return null;
    }
}
