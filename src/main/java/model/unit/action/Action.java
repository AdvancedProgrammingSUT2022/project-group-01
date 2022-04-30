package model.unit.action;

import model.tile.Tile;
import model.unit.Unit;

import java.util.PriorityQueue;
import java.util.function.Consumer;
import java.util.function.Function;

public class Action {
	private final Unit unit;
	private int remainedTurns;
	private Tile tile;

	Function<Action, Boolean> isPossibleFunc;
	Consumer<Action> doActionFunc;

	public Action(Unit unit, Actions actionType){
		this.unit = unit;
		this.remainedTurns = actionType.getRequiredTurns();
		this.isPossibleFunc	= actionType.getIsPossibleFunc();
		this.doActionFunc = actionType.getDoActionFunc();
	}
	public Action(Unit unit, Actions actionType, Tile tile){
		this(unit, actionType);
		this.tile = tile;
	}

	public Unit getUnit() {
		return unit;
	}

	public Tile getTile() {
		return tile;
	}

	public int getRemainedTurns() {
		return remainedTurns;
	}
	public void doAction(){
		doActionFunc.accept(this);
	}
	public boolean isPossible(){
		return isPossibleFunc.apply(this);
	}

	protected void decreaseTurn() {
		remainedTurns --;
	}

	public void completeAction() {
		remainedTurns = 0;
	}
}
