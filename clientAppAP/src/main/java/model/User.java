package model;

import com.thoughtworks.xstream.annotations.XStreamOmitField;
import controller.NetworkController;
import controller.ProgramController;
import lombok.Getter;
import lombok.Setter;
import network.AccessLevel;
import network.Request;
import network.Response;
import view.components.ImagesAddress;
import view.components.popup.PopUp;
import view.components.popup.PopUpStates;

import java.util.Date;
import java.util.Vector;

@Getter
@Setter
public class User {

    private String username;
    //private undefinedType[] savedGames;//TODO handle here
    private String password;
    private int score;
    @XStreamOmitField
    private transient Date lastWinDate;
    private String nickname;
    private String avatarUrl;
    private Vector<String> friends = new Vector<>();
    private Vector<String> pendingFriends = new Vector<>();

    private AccessLevel accessLevel;
    private String token = "";
    private Date lastSeen = null;


//    private Vector<String> pendingFriends = new Vector<>();


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
        Request request = new Request("Method Invoke");
        request.setToken("");
        request.addData("method","/setUsername");
        request.addData("args", new Object[]{username,newUsername});
        Response response = NetworkController.send(request);
        new PopUp().run(PopUpStates.OK,response.getMessage());
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
        Request request = new Request("Method Invoke");
        request.setToken("");
        request.addData("method","/setPassword");
        request.addData("args", new Object[]{username,password});
        Response response = NetworkController.send(request);
        new PopUp().run(PopUpStates.OK,response.getMessage());
    }

    public int getScore() {
        return this.score;
    }

    /**
     * @param score
     */
    public void increaseScore(int score) {
        this.score += score;
        ProgramController.getDatabase().save();
        Request request = new Request("Method Invoke");
        request.setToken("");
        request.addData("method","/increaseScore");
        request.addData("args", new Object[]{username,this.score});
        Response response = NetworkController.send(request);
        new PopUp().run(PopUpStates.OK,response.getMessage());
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
        Request request = new Request("Method Invoke");
        request.setToken("");
        request.addData("method","/setNickname");
        request.addData("args", new Object[]{username,nickname});
        Response response = NetworkController.send(request);
        new PopUp().run(PopUpStates.OK,response.getMessage());
    }


    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
        ProgramController.getDatabase().save();
        Request request = new Request("Method Invoke");
        request.setToken("");
        request.addData("method","/setAvatarUrl");
        request.addData("args", new Object[]{username,avatarUrl});
        Response response = NetworkController.send(request);
        new PopUp().run(PopUpStates.OK,response.getMessage());
    }

    public void addFriend(User user){
        this.pendingFriends.remove(user.username);
        if(!friends.contains(user.username))
            this.friends.add(user.username);
        ProgramController.getDatabase().save();
    }

    public void addRequest(User user){
        for(String request : this.pendingFriends){
            if(request.equals(user.username))
                return;
        }
        pendingFriends.add(user.username);
        ProgramController.getDatabase().save();
    }

    public void removeRequest(User user){
        pendingFriends.remove(user.username);
        ProgramController.getDatabase().save();
    }

    public Vector<String> getPendingFriends() {
        return pendingFriends;
    }

    public boolean hasUserAsFriend(User user){
        return friends.contains(user.username);
    }

    public Vector<String> getFriends() {
        return friends;
    }

    public void setLastWinDate(Date date){
        this.lastWinDate = date;
        ProgramController.getDatabase().save();
    }

    public Date getLastWinDate(){
        return this.lastWinDate;
    }

}