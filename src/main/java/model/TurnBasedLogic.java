package model;

import model.civilization.Civilization;

import java.util.Vector;

public interface TurnBasedLogic {
    static Vector<TurnBasedLogic> turnBasedObjects = new Vector<>();
    void nextTurn(Civilization civilization);
    static void callNextTurns(Civilization civilization){
        for(TurnBasedLogic object : turnBasedObjects){
            object.nextTurn(civilization);
        }
    }
    default void addToList(){
        turnBasedObjects.add(this);
    }
    default void removeFromList(){
        turnBasedObjects.remove(this);
    }
}
