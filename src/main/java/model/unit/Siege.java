package model.unit;

import model.Game;
import model.civilization.Civilization;
import model.tile.Tile;
import model.unit.action.PreAttack;

public class Siege extends RangedUnit {
	private boolean canAttack = false;

	public Siege(UnitType type, Tile tile, Civilization civilization, Game game) {
		super(type, tile, civilization, game);
	}

	public void preAttack() {
		actionsQueue.resetQueue();
		actionsQueue.addAction(new PreAttack(this));
	}

	public boolean readyToAttack(){
		return canAttack;
	}

	@Override
	public void moveTo(Tile tile) {
		currentTile.removeUnit(this);
		canAttack = false;

		this.currentTile = tile;
		tile.setArmedUnit(this);
	}
}
