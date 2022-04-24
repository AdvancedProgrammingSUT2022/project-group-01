package model;

import java.util.Vector;

public class Database {

    private Vector<User> users;

    public Database() {
        // TODO - implement model.Database.model.Database
        throw new UnsupportedOperationException();
    }

    public void addUser(String username, String nickname, String password){
        users.add(new User(username,password, nickname));
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