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
    private City selectedCity;
    private Tile selectedTile;
    private Unit selectedUnit;
    private Map map;
    int turn;

    public Game(Vector<Player> players, int mapSize){
        //TODO : ADDED MAP FIRST INITIALIZE AND map size
        map = new Map(mapSize);
    }
    public void nextTurn(){}
    public void applyCommandForPlayer(Command command, HashMap<String, String> args, Player player){}
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

    // TODO: method ziro paak kon
    public void setCurrentPlayer(Player player){
        this.currentPlayer = player;
    }

    public City getSelectedCity() {
        return selectedCity;
    }

    public void setSelectedCity(City selectedCity) {
        this.selectedCity = selectedCity;
    }

    public Tile getSelectedTile() {
        return selectedTile;
    }

    public void setSelectedTile(Tile selectedTile) {
        this.selectedTile = selectedTile;
    }

    public Unit getSelectedUnit() {
        return selectedUnit;
    }

    public void setSelectedUnit(Unit selectedUnit) {
        this.selectedUnit = selectedUnit;
    }
}
