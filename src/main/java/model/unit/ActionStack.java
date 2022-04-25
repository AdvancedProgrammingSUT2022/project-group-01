package model.unit;

import java.util.Vector;

public class ActionStack {
    Vector<StackData> stack;
    Vector<StackData> inCompleteStack;
    public ActionStack(){
        stack = new Vector<StackData>();
        inCompleteStack = new Vector<StackData>();
    }
    public void nextTurn(){
        //TODO next ture
    }
    public void doThisTurnFunctions(){
        //TODO do functions
    }
    public void addAction(Unit unit,ActionsType actionType){
        //TODO implement function
    }
    public void removeAction(Unit unit){
        //TODO remove stack with unit as Unit
    }
    public boolean checkIfActionIsEligible(Unit unit,ActionsType actionType){
        // TODO check if it's possible to perform the action
        return true;
    }
    public int calculateNecessaryTurns(Unit unit, ActionsType actionsType){
        // TODO implement setting needed time according to the actions' list
        return 0;
    }

    private static class StackData{
        private Unit unit;
        private ActionsType actionType;
        private Integer remainedTime;

        public StackData(Unit unit,ActionsType actionType,Integer remainedTime) {
            this.unit = unit;
            this.actionType = actionType;
            this.remainedTime = remainedTime;
        }

        public Integer getRemainedTime() {
            return remainedTime;
        }

        public void setRemainedTime(Integer remainedTime) {
            this.remainedTime = remainedTime;
        }

        public Unit getUnit() {
            return unit;
        }

        public void setUnit(Unit unit) {
            this.unit = unit;
        }

        public Object getActionType() {
            return actionType;
        }

        public void setActionType(ActionsType actionType) {
            this.actionType = actionType;
        }
    }
}
