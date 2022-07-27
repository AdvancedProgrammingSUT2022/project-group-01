package model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

@Getter @Setter
public class Message {

    private String text;
    private final User sender;
    private final int chatId;
    private boolean seen = false;

    private LocalTime time = LocalTime.now();

    public Message(String text, User sender, int chatId){
        this.text = text;
        this.sender = sender;
        this.chatId = chatId;
    }

    public void seen(){
        this.seen = true;
    }

}
