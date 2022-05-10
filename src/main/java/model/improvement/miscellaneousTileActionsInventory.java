package model.improvement;

import model.TurnBasedLogic;
import model.civilization.Civilization;
import model.tile.Tile;
import model.unit.action.UnitActions;

public class MiscellaneousTileActionsInventory implements TurnBasedLogic {
    int requiredTime;
    UnitActions action;
    Tile tile;
    public MiscellaneousTileActionsInventory(Tile tile) {
       this.tile = tile;
       this.action = null;
       this.requiredTime = 0;
    }

    public void setAction(UnitActions action){
        if(action.checkIfActionIsDoable(tile)){
            this.requiredTime = action.time;
            this.action = action;
            addToList();
        }
    }

    public void nextTurn(Civilization civilization){
        if(!this.tile.getCivilization().equals(civilization)) return;
        this.requiredTime -= 1;
        if(this.requiredTime == 0){
            action.doAction(tile);
            removeFromList();
        }
    }

    public void stop(){
        removeFromList();
    }

}
