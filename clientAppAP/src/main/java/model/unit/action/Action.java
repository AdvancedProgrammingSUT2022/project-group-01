package model.unit.action;

import lombok.Getter;
import lombok.Setter;
import model.improvement.ImprovementType;
import model.tile.TerrainFeature;
import model.tile.Tile;
import model.unit.Unit;
import model.unit.civilian.Worker;

import java.util.function.Consumer;
import java.util.function.Function;

@Getter @Setter
public class Action {
	private final Unit unit;
	private Integer remainedTurns;

	private Tile tile; // Move
	private ImprovementType improvementType; // Worker
	private TerrainFeature feature;
	@Getter
	private Actions actionType;

	Function<Action, Boolean> isPossibleFunc;
	Consumer<Action> doActionFunc;

	public Action(Unit unit, Actions actionType){
		this.unit = unit;
		this.remainedTurns = actionType.getRequiredTurns();
		this.isPossibleFunc	= actionType.getIsPossibleFunc();
		this.doActionFunc = actionType.getDoActionFunc();
		this.actionType = actionType;
	}
	public Action(Unit unit, Actions actionType, Tile tile){
		this(unit, actionType);
		this.tile = tile;
		this.actionType = actionType;
	}

	public Action(Worker unit, Actions actionType, ImprovementType improvementType){
		this(unit, actionType);
		// major additional one turn bug TODO
		this.remainedTurns = improvementType.getProductionTime(unit.getCurrentTile());
		this.improvementType = improvementType;
	}

	public Action(Worker worker, Actions actionType, TerrainFeature feature){
		this(worker, actionType);
		this.remainedTurns = feature.getRemoveTime();
		this.feature = feature;
		this.tile = worker.getCurrentTile();
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

	public void decreaseTurn() {
		remainedTurns --;
	}
	public boolean isLastTurn(){ return remainedTurns == 1; }
	public void completeAction() {
		remainedTurns = 0;
	}
}
