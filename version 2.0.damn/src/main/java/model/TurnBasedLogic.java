package model;

import java.util.Vector;

public interface TurnBasedLogic {
    static Vector<TurnBasedLogic> turnBasedObjects = new Vector<>();
    void nextTurn();
    static void callNextTurns(){
        for(TurnBasedLogic object : turnBasedObjects){
            object.nextTurn();
        }
    }
    default void addToList(){
        turnBasedObjects.add(this);
    }
    default void removeFromList(){
        turnBasedObjects.remove(this);
    }
}
