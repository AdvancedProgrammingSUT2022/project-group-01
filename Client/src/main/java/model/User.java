package model;

import lombok.Getter;
import lombok.Setter;
import network.AccessLevel;
@Getter
@Setter
public class User {
    private String username;
    //private undefinedType[] savedGames;//TODO handle here
    private String password;
    private int score;
    private String nickname;
    private String avatarUrl;
    private AccessLevel accessLevel;
    private String token = "";

    public User(String username, String password, String nickname, String avatarUrl, AccessLevel accessLevel) {
        this.nickname = nickname;
        this.username = username;
        this.password = password;
        this.avatarUrl = avatarUrl;
        this.accessLevel = accessLevel;
        this.score = 0;
    }

    public void increaseScore(int score) {
        this.score = score;
    }
}