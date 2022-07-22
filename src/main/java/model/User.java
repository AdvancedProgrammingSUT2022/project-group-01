package model;

import controller.ProgramController;

import java.util.Vector;

public class User {

    private String username;
    //private undefinedType[] savedGames;//TODO handle here
    private String password;
    private int score;
    private String nickname;
    private String avatarUrl;

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
}