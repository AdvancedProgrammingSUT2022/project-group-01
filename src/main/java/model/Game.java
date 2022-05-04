package model;
// TODO added MAP map and getter setter
// TODO added get current Player
import model.civilization.*;
import model.civilization.Currency;
import model.civilization.city.City;
import model.tile.Tile;
import model.unit.Unit;

import java.util.*;

public class Game {
    Vector<Player> players;
    Vector<Trade> trades;
    Player currentPlayer;
    private Object selectedObject;
    private Map map;
    int turn;

    public Game(Vector<Player> players, int mapSize){
        //TODO : ADDED MAP FIRST INITIALIZE AND map size
        this.players = players;
        map = new Map(mapSize);
    }
    public void nextTurn(){}
    public Trade getTradeForCivilization(Civilization civilization){
        //TODO: implement here
        return null;
    }

    //cheats
    public void increaseTurn(int amount){}
    public void increaseCurrency(Currency currency, Civilization civilization){}
    public void increaseScience(double amount, Civilization civilization){}
    public void increaseHappiness(int amount, Civilization civilization){}

    public Map getMap() {
        return map;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }


    public void addPlayer(Player player){
        this.players.add(player);
    }

    public void setMap(Map map) {
        this.map = map;
    }


    public Vector<Player> getPlayers() {
        return players;
    }
  
    // TODO: method ziro paak kon
    public void setCurrentPlayer(Player player){
        this.currentPlayer = player;
    }

    public Object getSelectedObject() {
        return selectedObject;
    }

    public void setSelectedObject(Object selectedObject) {
        this.selectedObject = selectedObject;
    }
}
