package controller;

import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import model.User;
import model.chatroom.Query;
import network.Request;
import network.Response;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

@Getter @Setter
public class SocketHandler {

    private Socket socket;
    private DataInputStream inputStream;
    private DataOutputStream outputStream;
    private User user;

    @SneakyThrows
    public SocketHandler(Socket socket, User user){
        this.socket = socket;
        this.user = user;
//        this.inputStream = new DataInputStream(socket.getInputStream());
//        this.outputStream = new DataOutputStream(socket.getOutputStream());
        register();
    }

    public void register(){
        Query query = new Query("register");
        query.put("username", user.getUsername());
        sendQuery(query);
    }

    public void sendMessage(String text, int chatId){
        Request request = new Request("Method Invoke");
        request.addData("method", "/chat/send");
        request.addData("args", new Object[]{text, chatId});
        Response response = NetworkController.send(request);
    }

    public void editMessage(String newText, int messageId, int chatId){
        Query query = new Query("edit_message");
        query.put("chat_id", chatId);
        query.put("message_id", messageId);
        query.put("new_message", newText);
        sendQuery(query);
    }

    public void deleteMessage(int messageId, int chatId){
        Query query = new Query("delete_message");
        query.put("message_id", messageId);
        query.put("chat_id", chatId);
        sendQuery(query);
    }

    @SneakyThrows
    public void sendQuery(Query query){
        if(query.getHeader().equals("register"))
            return ;
        System.out.println(query.toJson());
        outputStream.writeUTF(query.toJson());
        outputStream.flush();
    }



}
