package model.improvement;

import model.ProgressState;
import model.TurnBasedLogic;
import model.civilization.Civilization;
import model.tile.Tile;
import model.unit.action.UnitActions;

public class MiscellaneousTileActionsInventory {
    int requiredTime;
    UnitActions action;
    ProgressState state;
    Tile tile;
    ProgressState roadState;
    ProgressState railRoadState;
    public MiscellaneousTileActionsInventory(Tile tile) {
       this.tile = tile;
       this.action = null;
       this.requiredTime = 0;
       this.state = ProgressState.COMPLETE;
       roadState = ProgressState.NOT_BUILT;
       railRoadState = ProgressState.NOT_BUILT;
    }

    public void doAction(UnitActions action){
        if(action.checkIfActionIsDoable(tile)){
            if(this.state.equals(ProgressState.IN_PROGRESS)){
                if((this.action != null) && (this.action.equals(action))){
                    progress();
                }
            }
            else {
                this.requiredTime = action.time;
                this.action = action;
                this.state = ProgressState.IN_PROGRESS;
            }
            nextTurn();
        }
    }

    public void nextTurn(){
        this.requiredTime -= 1;
        if(this.requiredTime <= 0){
            action.doAction(tile);
            this.state = ProgressState.COMPLETE;
            this.requiredTime = 0;
        }
    }

    public void pillage(){
        if(this.roadState.equals(ProgressState.COMPLETE))
            this.roadState = ProgressState.DAMAGED;
        if(this.railRoadState.equals(ProgressState.COMPLETE))
            this.railRoadState = ProgressState.DAMAGED;
    }

    public void progress(){
            nextTurn();
    }

    public boolean hasRoad(){
        return this.roadState.equals(ProgressState.COMPLETE);
    }

    public void forceBuildRoad(){
        roadState = ProgressState.COMPLETE;
    }

    public boolean hasRailRoad(){
        return this.railRoadState.equals(ProgressState.COMPLETE);
    }

    public void forceBuildRailRoad(){
        this.railRoadState = ProgressState.COMPLETE;
    }

    public void forceRemoveRoad(){
        this.roadState = ProgressState.NOT_BUILT;
    }

    public void forceRemoveRailRoad(){
        this.railRoadState = ProgressState.NOT_BUILT;
    }

    public boolean isRoadPillaged(){
        return roadState.equals(ProgressState.DAMAGED);
    }

    public boolean isRailRoadPillaged(){
        return railRoadState.equals(ProgressState.DAMAGED);
    }
}
