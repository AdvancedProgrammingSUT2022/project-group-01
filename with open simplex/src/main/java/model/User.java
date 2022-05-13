package model;

import lombok.Getter;
import lombok.Setter;
import org.junit.jupiter.api.Test;

@Getter @Setter
public class User {

    private String username;
    private String password;
    private int score;
    private String nickname;

    /**
     * @param username user's username
     * @param password user's password
     * @param nickname user's nickname
     */
    public User(String username, String password, String nickname) {
        this.nickname = nickname;
        this.username = username;
        this.password = password;
        this.score = 0;
    }

}