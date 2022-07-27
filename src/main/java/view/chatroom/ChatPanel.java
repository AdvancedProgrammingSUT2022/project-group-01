package view.chatroom;

import com.jfoenix.controls.JFXTabPane;
import controller.SocketHandler;
import controller.SocketThread;
import javafx.scene.control.Tab;
import lombok.Getter;
import lombok.Setter;
import model.User;

import java.net.Socket;
import java.util.HashMap;
import java.util.Vector;

@Getter @Setter
public class ChatPanel extends JFXTabPane {
    public static ChatPanel lastInstance;

    private final Vector<User> users;
    private final User user;
    private final HashMap<ChatPage, Tab> pairs = new HashMap<>();
    private Socket socket;
    private Socket listener;
    private SocketHandler socketHandler;
    private SocketThread socketThread;
    public ChatPanel(User user, Vector<User> users, Socket socket, Socket listener) {
        this.user = user;
        this.users = users;
        this.socket = socket;
        this.listener = listener;
        init();
        lastInstance = this;
    }

    private void init() {
        initTabPane();
        initTabs();
        initNetwork();
    }

    public void initNetwork(){
        this.socketHandler = new SocketHandler(socket,user);
        this.socketThread = new SocketThread(listener, this);
        socketThread.start();
    }

    private void initTabPane() {
        this.setRotateGraphic(true);
    }

    private void initTabs() {
//        for (User user : users) {
//            if (user == this.user)
//                continue;
//            Tab tab = new Tab();
//            tab.setText(user.getNickname());
//            ChatPage chatPage = new ChatPage(this.user, new Vector<>() {
//                {
//                    add(ChatPanel.this.user);
//                    add(user);
//                }
//            });
//            tab.setContent(chatPage);
//            this.getTabs().add(tab);
//            this.getPairs().put(chatPage, tab);
//        }
        //this.getTabs().addAll(tabs);
        initFindChatTab();
    }

    private void initFindChatTab(){
        Tab tab = new Tab();
        tab.setText("Find Chat");
        ChatFinder chatFinder = new ChatFinder(users,this, socket);
        tab.setContent(chatFinder);
        this.getTabs().add(tab);
    }

}
