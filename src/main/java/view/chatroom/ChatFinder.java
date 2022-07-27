package view.chatroom;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import controller.NetworkController;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import lombok.SneakyThrows;
import model.User;
import model.chatroom.Query;
import network.Request;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Map;
import java.util.Vector;

public class ChatFinder extends BorderPane {
    private final Vector<User> users = new Vector<>();
    private final User self;
    private final ScrollPane scrollPane = new ScrollPane();
    private final VBox usersList = new VBox();
    private final ChatPanel chatPanel;
    private final JFXTextField txtField = new JFXTextField();
//    private Socket socket;
//    private DataInputStream inputStream;
//    private DataOutputStream outputStream;

    @SneakyThrows
    public ChatFinder(Vector<User> users, ChatPanel chatPanel, Socket socket){
        this.users.addAll(users);
        this.chatPanel = chatPanel;
        this.self = chatPanel.getUser();
//        this.socket = socket;
//        this.inputStream = new DataInputStream(socket.getInputStream());
//        this.outputStream = new DataOutputStream(socket.getOutputStream());
        init();
    }

    private void init(){
        this.setMinWidth(300);
        this.setMaxWidth(300);
        this.setPadding(new Insets(5,5,5,5));
        initTop();
        initButton();
        initScrollPane();
        initUsersList();
    }

    @SneakyThrows
    private void initButton(){
        JFXButton btn = new JFXButton("(Go to)/(create) chat");
        btn.setStyle("-fx-background-color: #6d6d6d;-fx-min-width: 290;");
        btn.setAlignment(Pos.CENTER);
        btn.setOnAction(actionEvent -> {
            ArrayList<User> newChatUsers = new ArrayList<>();
            ArrayList<String> newChatUsernames = new ArrayList<>();
            newChatUsers.add(self);
            for(Node node : usersList.getChildren()){
                if(((MemberRow)node).isChecked()) {
                    newChatUsers.add(((MemberRow) node).getUser());
                    newChatUsernames.add(((MemberRow) node).getUser().getUsername());
                }
            }
            Chat chat = Chat.getChat(newChatUsers);
            for(Map.Entry<ChatPage, Tab> set : chatPanel.getPairs().entrySet()){
                if(set.getKey().getChat() == chat){
                    chatPanel.getSelectionModel().select(set.getValue());
                    return;
                }
            }
            if(newChatUsers.size() > 1 && txtField.getText().length()<2){
                System.out.println("invalid group name");//todo add pop up here
                return;
            }
            newChatUsernames.add(chatPanel.getUser().getUsername());
            Request request = new Request("Method Invoke");
            request.addData("method", "chat/new");
            request.addData("args", new Object[]{newChatUsernames, newChatUsers.size() > 1 ? txtField.getText() : ""});

            NetworkController.send(request);
//            Query query = new Query("get_chat");
//            query.put("usernames", newChatUsernames);
//            if(newChatUsers.size() > 1)
//                query.put("chat_name",txtField.getText());
//            try {
//                outputStream.writeUTF(query.toJson());
//                outputStream.flush();
//            }catch (Exception e){
//                e.printStackTrace();
//            }
        });
        this.setBottom(btn);
    }

    private void initTop(){
        txtField.setStyle("-fx-min-width: 290");
        txtField.setPromptText("Enter group chat's name");
        txtField.setPadding(new Insets(5,5,5,5));
        this.setTop(txtField);
    }

    private void initScrollPane(){
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.addEventFilter(ScrollEvent.SCROLL,new EventHandler<ScrollEvent>() {
            @Override
            public void handle(ScrollEvent event) {
                if (event.getDeltaX() != 0) {
                    event.consume();
                }
            }
        });
    }

    private void initUsersList(){
        scrollPane.setContent(usersList);
        usersList.setSpacing(5);
        for(User user : users){
            if(user == self)
                continue;
            usersList.getChildren().add(new MemberRow(user));
        }
        this.setCenter(scrollPane);
        //usersList.setAlignment(Pos.BASELINE_CENTER);
        usersList.setPadding(new Insets(5,5,5,5));
    }


}
