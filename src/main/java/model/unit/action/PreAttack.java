package model.unit.action;

import model.unit.Siege;
import model.unit.Unit;

public class PreAttack implements Action {
	private Siege siegeUnit;
	private int remainedTurns = 1;

	public PreAttack(Siege siegeUnit){
		this.siegeUnit = siegeUnit;
	}

	@Override
	public int getRequiredTurns() {
		return remainedTurns;
	}

	@Override
	public boolean isPossible() {
		return true;
	}

	@Override
	public void doAction() {
		siegeUnit.preAttack();
		remainedTurns --;
	}
}
