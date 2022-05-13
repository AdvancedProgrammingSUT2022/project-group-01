package model.unit.civilian;

import model.Game;
import model.civilization.Civilization;
import model.tile.Tile;
import model.unit.Unit;
import model.unit.UnitType;

public class Civilian extends Unit {

	public Civilian(UnitType type, Tile tile, Civilization civilization, Game game){
		super(type, tile, civilization, game);
	}

    public static Unit spawnCivilian(UnitType type, Tile tile, Civilization ownerCivilization) {
		return null;
    }

    public void captureUnit(Unit enemy){
		ownerCivilization = enemy.getOwnerCivilization();
	}

	public void moveTo(Tile tile) {
		currentTile.removeUnit(this);
		tile.setCivilianUnit((Civilian) this);
		this.currentTile = tile;
		tile.setCivilianUnit(this);
	}
}
