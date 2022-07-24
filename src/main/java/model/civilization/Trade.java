package model.civilization;

import lombok.Getter;
import lombok.Setter;

import java.util.*;
@Getter @Setter
public class Trade {
    Civilization first;
    Civilization second;
    HashMap<Object,Integer> firstOffer;
    HashMap<Object,Integer> secondOffer;
    int buildingState = 0;
    @Getter @Setter
    int remainedTurn;

    public Trade(Civilization first, Civilization second, HashMap<Object, Integer> firstOffer, HashMap<Object, Integer> secondOffer, int remainedTurn) {
        this.first = first;
        this.second = second;
        this.firstOffer = firstOffer;
        this.secondOffer = secondOffer;
        this.remainedTurn = remainedTurn;
    }

    public Trade(Civilization first, Civilization second){
        this.first = first;
        this.second = second;
        firstOffer = new HashMap<>();
        secondOffer = new HashMap<>();
    }

    //if returns true means needs to be deleted
    public void nextTurn(){
        remainedTurn--;
    }

    public void applyEffect(){

    }

    public void cancel(){
        first.cancelTrade(this);
        second.cancelTrade(this);
    }


    public void addToSecondOffer(Object o, Integer i){
        secondOffer.put(o,i);
    }

    public void addToFirstOffer(Object o, Integer i){
        firstOffer.put(o,i);
    }

    public void setTime(int time){
        remainedTurn = time;
    }


    public void setBuildingState(int buildingState) {
        this.buildingState = buildingState;
    }

    public int getBuildingState() {
        return buildingState;
    }

    public void addToUsers(){
        first.addToTrades(this);
        second.addToTrades(this);
    }

    public void decline() {
        first.removeRequestTrade(this);
        second.removeRequestTrade(this);
    }
}
