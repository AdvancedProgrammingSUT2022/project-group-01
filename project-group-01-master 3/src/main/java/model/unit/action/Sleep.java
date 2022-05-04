package model.unit.action;

import model.unit.Unit;

public class Sleep implements Action {

	public Sleep(){}

	@Override
	public int getRequiredTurns() {
		// TODO : handle infinite better
		return -1;
	}

	@Override
	public boolean isPossible() {
		return true;
	}

	@Override
	public void doAction() {}
}
