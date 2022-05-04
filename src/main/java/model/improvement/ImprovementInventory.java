package model.improvement;

import model.ProgressState;
import model.TurnBasedLogic;
import model.tile.Tile;

public class ImprovementInventory implements TurnBasedLogic {
	private ImprovementType improvement;
	private int turnsLeft;
	private ProgressState state;

	public ImprovementInventory(Tile tile, ImprovementType improvement) {
		this.improvement = improvement;
		this.turnsLeft = improvement.getProductionTime(tile);
		state = ProgressState.IN_PROGRESS;
		addToList();
	}
	public void nextTurn(){
		if(this.state.equals(ProgressState.IN_PROGRESS) | this.state.equals(ProgressState.DAMAGED)) {
			this.turnsLeft -= 1;
			if (this.turnsLeft == 0) {
				this.state = ProgressState.COMPLETE;
				removeFromList();
			}
		}
	}
	public void stop(){
		if(this.state.equals(ProgressState.IN_PROGRESS)) {
			this.state = ProgressState.STOPPED;
			removeFromList();
		}
		if(this.state.equals(ProgressState.DAMAGED)){
			removeFromList();
		}
	}

	public ImprovementType getImprovement() {
		return improvement;
	}

	public void setImprovement(ImprovementType improvement) {
		this.improvement = improvement;
	}

	public int getTurnsLeft() {
		return turnsLeft;
	}

	public void setTurnsLeft(int turnsLeft) {
		this.turnsLeft = turnsLeft;
	}

	public ProgressState getState() {
		return state;
	}

	public void setState(ProgressState state) {
		this.state = state;
	}

	public void progress(){
		if(this.state.equals(ProgressState.STOPPED)){
			this.state = ProgressState.IN_PROGRESS;
			addToList();
		}
	}

	public void repair(){
		if(this.state.equals(ProgressState.DAMAGED)) {
			this.turnsLeft = 3;
			addToList();
		}
	}

	public void damage(){
		this.state = ProgressState.DAMAGED;
	}

}