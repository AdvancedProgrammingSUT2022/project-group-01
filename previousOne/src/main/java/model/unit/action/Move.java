package model.unit.action;

import model.tile.Tile;
import model.unit.Unit;

public class Move implements Action {
	private final Unit unit;
	private final Tile destination;
	private int remainedTurns = 1;

	public Move(Unit unit, Tile destination){
		this.unit = unit;
		this.destination = destination;
	}

	@Override
	public int getRequiredTurns() {
		return remainedTurns;
	}

	@Override
	public boolean isPossible() {
		return destination.getSameTypeUnit(unit) == null;
	}

	@Override
	public void doAction() {
		unit.moveTo(destination);
		remainedTurns --;
	}
}
