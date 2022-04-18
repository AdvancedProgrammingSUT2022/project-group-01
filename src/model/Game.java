package model;

import model.civilization.*;

import java.util.*;

public class Game {
    Vector<Player> players;
    Vector<Trade> trades;
    Player currentPlayer;
    int turn;

    public Game(Vector<Player> players){

    }
    public void nextTurn(){}
    public void applyCommandForPlayer(Command command, HashMap<String, String> args, Player player){}
    public Trade getTradeForCivilization(Civilization civilization){
        //TODO: implement here
        return null;
    }

}
