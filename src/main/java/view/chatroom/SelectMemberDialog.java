package view.chatroom;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lombok.Getter;
import model.User;

import java.net.Socket;
import java.util.Vector;

@Getter
public class SelectMemberDialog extends Application {

    private User user;
    private Vector<User> users = new Vector<>();
    private Socket socket;
    private Socket listener;
    public void run(User user, Vector<User> users, Socket socket, Socket listener) throws Exception{
        Platform.startup(()-> {
            try {
                SelectMemberDialog.this.socket = socket;
                SelectMemberDialog.this.listener = listener;
                SelectMemberDialog.this.user = user;
                SelectMemberDialog.this.users.addAll(users);
                start(new Stage());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setWidth(320);
        ChatPanel cp = new ChatPanel(user, users, socket, listener);
        cp.setListener(listener);
        cp.setSocket(socket);
        Scene scene = new Scene(cp);
        stage.setScene(scene);
        stage.show();
    }

    private void initStage(){

    }

    public void initScrollPane(){

    }

    private void initButtons(){

    }




}
