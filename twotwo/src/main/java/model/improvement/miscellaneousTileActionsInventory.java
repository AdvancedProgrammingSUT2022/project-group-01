package model.improvement;

import model.TurnBasedLogic;
import model.tile.Tile;
import model.unit.action.UnitActions;

public class miscellaneousTileActionsInventory implements TurnBasedLogic {
    int requiredTime;
    UnitActions action;
    Tile tile;
    public miscellaneousTileActionsInventory(UnitActions action, Tile tile) {
        if(action.checkIfActionIsDoable(tile)) {
            this.requiredTime = action.time;
            this.tile = tile;
            if (action.mainKind instanceof ImprovementType) {
                requiredTime = ((ImprovementType) action.mainKind).getProductionTime(tile);
            }
            this.action = action;
            addToList();
        }
    }

    public void nextTurn(){
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
