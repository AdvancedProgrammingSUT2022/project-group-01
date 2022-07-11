package view.chatroom.component;

import model.User;

import java.util.Date;

public class Message {
    String text;
    User sender;
    Date date;

    public Message(String text, User sender, Date date){
        this.text = text;
        this.sender = sender;
        this.date = date;
    }

}
