package model.unit.action;

import model.unit.Unit;

public class Fortify implements Action {
	private final Unit unit;
	private int remainedTurns;

	public Fortify(Unit unit, int turns){
		this.unit = unit;
		this.remainedTurns = turns;
	}

	@Override
	public int getRequiredTurns() {
		return remainedTurns;
	}

	@Override
	public boolean isPossible() {
		return unit.getHealth() < Unit.maxHealth;
	}

	@Override
	public void doAction() {
		unit.changeHealth(+1);
		remainedTurns --;
	}
}
