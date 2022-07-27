import controller.*;
import model.Database;
import model.User;
import view.chatroom.SelectMemberDialog;

import java.net.Socket;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;
import java.util.Vector;


public class Main {
    static final int SERVER_PORT = 9090;

    public static void main(String[] args) throws Exception{
        testChat();
    }

    public static void testChat() throws Exception {
        if(!NetworkController.connect("localhost", SERVER_PORT)){
            System.out.println("Error in Connection ...");
            return;
        }
//        Socket socket = new Socket("localhost", SERVER_PORT);
//        Socket listenerSocket = new Socket("localhost", SERVER_PORT);
        SelectMemberDialog smd = new SelectMemberDialog();
        User u1 = Database.instance.findUserByUsername("akbar");
        Vector<User> users = new Vector<>();
        User u2 = Database.instance.findUserByUsername("ravani");
        User u3 = Database.instance.findUserByUsername("u3");
        User u4 = Database.instance.findUserByUsername("u4");
        users.addAll(Arrays.asList(u1,u2,u3,u4));
        LoginMenuController loginMenuController = new LoginMenuController(null);
        HashMap<String, String> args = new HashMap<>();
        Vector<String> _users = new Vector<>();
        _users.add("akbar");
        _users.add("ravani");
        int idx = (new Random()).nextInt(2);
        System.out.println(idx);
        args.put("username", _users.get(idx));
        args.put("password", "1234");
        loginMenuController.login(args);
        smd.run(users.get(idx), users, null, null);
    }
}
