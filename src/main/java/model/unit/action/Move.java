package model.unit.action;

import model.tile.Tile;
import model.unit.Unit;

public class Move implements Action {
	private final Unit unit;
	private final Tile destination;

	public Move(Unit unit, Tile destination){
		this.unit = unit;
		this.destination = destination;
	}

	@Override
	public int getRequiredTurns() {
		return 1;
	}

	@Override
	public boolean isPossible() {
		return destination.getSameTypeUnit(unit) == null;
	}

	@Override
	public void doAction() {
		unit.moveTo(destination);
	}
}
