package model;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.Vector;

public class Database {

    private Vector<User> users;

    public Database() {
        users = new Vector<>();
    }

    public void save() {
        try {
            Gson gson = new Gson();
            FileWriter fileWriter = new FileWriter("./database/users.json");
            gson.toJson(users, fileWriter);
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void load(){
        File file = new File("./database/users.json");
        if(!file.exists())
            return;
        Gson gson = new Gson();
        try (Reader reader = new FileReader("./database/users.json")) {
            // Convert JSON File to Java Object
            Type listType = new TypeToken<Vector<User>>(){}.getType();
            users = gson.fromJson(reader, listType);
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void addUser(String username, String nickname, String password){
        users.add(new User(username,password, nickname));
        save();
    }

    /**
     * @param user
     * @param newPassword
     */
    public void changePassword(User user, String newPassword) {
        // TODO - implement model.Database.changePassword
        throw new UnsupportedOperationException();
    }

    /**
     * @param user
     * @param newNickname
     */
    public void changeNickname(User user, String newNickname) {
        // TODO - implement model.Database.changeNickname
        throw new UnsupportedOperationException();
    }

    public User findUserByUsername(String username) {
        for (User user : users)
            if (user.getUsername().equals(username))
                return user;
        return null;
    }

    public User findUserByNickname(String nickname){
        for(User user : users)
            if(user.getNickname().equals(nickname))
                return user;
        return null;
    }

}