package controller;

import model.GameInstantiateData;
import model.Lobby;
import model.User;

public class LobbyController {
    private Lobby lobby;

    public LobbyController(Lobby lobby){
        this.lobby = lobby;
    }

    public void addGame(GameInstantiateData gameInstantiateData){
        lobby.addGame(gameInstantiateData);
    }

    public void removeGame(GameInstantiateData gameInstantiateData){
        lobby.removeGame(gameInstantiateData);
    }

    public String createGame(User creator, int playersNumber, String id){
        if(playersNumber < 1) return "You can't start a game with no one";
        for(GameInstantiateData g : lobby.getRooms()){
            if(g.getId().equals(id)) return "This id is already used";
        }
        GameInstantiateData gameInstantiateData = new GameInstantiateData(creator);
        gameInstantiateData.setId(id);
        gameInstantiateData.setPreSetNumberOfUsers(playersNumber);
        lobby.addGame(gameInstantiateData);
        return "Game created";
    }
    

}
