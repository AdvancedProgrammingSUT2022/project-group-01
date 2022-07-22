package model;

import controller.ProgramController;

import java.util.Map;
import java.util.Vector;

public class User {

    private String username;
    //private undefinedType[] savedGames;//TODO handle here
    private String password;
    private int score;
    private String nickname;
    private String avatarUrl;
    private Vector<String> friends = new Vector<>();
    private Vector<String> friendshipRequests = new Vector<>();
    /**
     * @param username user's username
     * @param password user's password
     * @param nickname user's nickname
     */
    public User(String username, String password, String nickname, String avatarUrl) {
        this.nickname = nickname;
        this.username = username;
        this.password = password;
        this.avatarUrl = avatarUrl;
        this.score = 0;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String newUsername) {
        this.username = newUsername;
        ProgramController.getDatabase().save();
    }

    public String getPassword() {
        return this.password;
    }

    /**
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
        ProgramController.getDatabase().save();
    }

    public int getScore() {
        return this.score;
    }

    /**
     * @param score
     */
    public void increaseScore(int score) {
        this.score = score;
        ProgramController.getDatabase().save();
    }

    public String getNickname() {
        return this.nickname;
    }

    /**
     * @param nickname
     */
    public void setNickname(String nickname) {
        this.nickname = nickname;
        ProgramController.getDatabase().save();
    }


    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
        ProgramController.getDatabase().save();
    }

    public void addFriend(User user){
        this.friendshipRequests.remove(user.username);
        if(!friends.contains(user.username))
            this.friends.add(user.username);
        ProgramController.getDatabase().save();
    }

    public void addRequest(User user){
        for(String request : this.friendshipRequests){
            if(request.equals(user.username))
                return;
        }
        friendshipRequests.add(user.username);
        ProgramController.getDatabase().save();
    }

    public void removeRequest(User user){
        friendshipRequests.remove(user.username);
        ProgramController.getDatabase().save();
    }

    public Vector<String> getFriendshipRequests() {
        return friendshipRequests;
    }

    public boolean hasUserAsFriend(User user){
        return friends.contains(user.username);
    }

    public Vector<String> getFriends() {
        return friends;
    }
}