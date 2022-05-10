package model.unit.armed;

import model.Game;
import model.civilization.Civilization;
import model.tile.Tile;
import model.unit.UnitType;
import model.unit.action.Action;
import model.unit.action.Actions;
import model.unit.armed.RangedUnit;

public class Siege extends RangedUnit {
	private boolean canAttack = false;

	public Siege(UnitType type, Tile tile, Civilization civilization, Game game) {
		super(type, tile, civilization, game);
	}

	public void setup() {
		actionsQueue.resetQueue();
		actionsQueue.addAction(new Action(this, Actions.SETUP));
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

	public void completeSetup() {
	}
}
