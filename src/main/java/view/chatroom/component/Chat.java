package view.chatroom.component;

import model.User;

import java.util.ArrayList;

public class Chat {
    ArrayList<User> users;
    ArrayList<Message> messages = new ArrayList<>();
    public Chat(ArrayList<User> users, ChatType chatType){
        this.users = (ArrayList<User>) users.clone();
    }

    public void addMessage(){

    }
}
