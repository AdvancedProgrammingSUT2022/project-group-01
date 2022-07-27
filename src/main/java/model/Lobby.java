package model;

import java.util.Vector;

public class Lobby {
    private Vector<GameInstantiateData> rooms;


    public Lobby() {
        rooms = new Vector<>();
    }

    public void addGame(GameInstantiateData gameInstantiateData){
        rooms.add(gameInstantiateData);
    }

    public void removeGame(GameInstantiateData gameInstantiateData) {
        rooms.remove(gameInstantiateData);
    }

    public Vector<GameInstantiateData> getRooms() {
        return rooms;
    }
}
