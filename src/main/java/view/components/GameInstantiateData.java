package view.components;

import model.User;

import java.util.Vector;

public class GameInstantiateData {
    private Vector<User> players = new Vector<>();
    private User creator;
    private Vector<User> invitedPlayers = new Vector<>();
    private int mapSize;

    public GameInstantiateData(User creator) {
        this.creator = creator;
    }

    public void addPlayer(User user){
        players.add(user);
    }

    public void invitePlayer(User user){
        invitedPlayers.add(user);
    }

    public void removePlayer(User user){
        players.remove(user);
    }

    public void removeInvitationToPlayer(User user){
        invitedPlayers.remove(user);
    }

    public int getMapSize() {
        return mapSize;
    }

    public void setMapSize(int mapSize) {
        this.mapSize = mapSize;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public boolean userIsInvited(User user){
        return invitedPlayers.contains(user);
    }

    public boolean isUserInGame(User user){
        return players.contains(user);
    }
}
