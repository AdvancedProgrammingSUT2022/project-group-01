package view.chatroom;

import lombok.Getter;
import lombok.Setter;
import model.User;

import java.util.ArrayList;
import java.util.Vector;

@Getter @Setter
public class Chat {

    private static ArrayList<Chat> chats = new ArrayList<>();

    private ArrayList<User> users = new ArrayList<>();
    private final ArrayList<Message> messages = new ArrayList<>();
    private final int id;
    private String chatName;
    public Chat(Vector<User> users) {
        this.users.addAll(users);
        this.id = chats.size();
        chats.add(this);
    }

    public Message sendMessage(String text, User sender) {
        Message message;
        message = new Message(text, sender, id);
        messages.add(message);
        return message;
    }

    public static Chat getChat(ArrayList<User> users) {
        for (Chat chat : chats) {
            boolean result = true;
            for(User user : users){
                if (!chat.getUsers().contains(user)) {
                    result = false;
                    break;
                }
            }
            if(result)
                return chat;
        }
        return null;
    }


    public static Chat getChat(int code){
        return chats.get(code);
    }

}
