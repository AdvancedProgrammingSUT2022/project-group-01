package model;

import model.civilization.*;
import model.civilization.Currency;

import java.util.*;

public class Game {
    Vector<Player> players;
    Vector<Trade> trades;
    Player currentPlayer;
    int turn;

    public Game(Vector<Player> players){

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
}
