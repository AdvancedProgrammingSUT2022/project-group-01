package view.chatroom;

import java.net.Socket;

public class ChatroomListener extends Thread{
    private Socket socket;
    private ChatPanel chatPanel;
    public ChatroomListener(Socket socket, ChatPanel chatPanel){
        this.socket = socket;
        this.chatPanel = chatPanel;
    }
}
