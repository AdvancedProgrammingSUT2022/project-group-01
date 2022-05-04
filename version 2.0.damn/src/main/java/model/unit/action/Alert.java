package model.unit.action;

import model.unit.Unit;

public class Alert implements Action {
	private final Unit unit;
	private int remainedTurns = -1; // 0 finished

	public Alert(Unit unit){
		this.unit = unit;
	}

	@Override
	public int getRequiredTurns() {
		// TODO handle infinite better
		return remainedTurns;
	}

	@Override
	public boolean isPossible() {
		return true;
	}

	@Override
	public void doAction() {
		if(unit.isEnemyNear())
			remainedTurns = 0;
	}
}
