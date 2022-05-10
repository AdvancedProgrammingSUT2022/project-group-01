package model.unit.armed;

import model.Game;
import model.civilization.Civilization;
import model.tile.Tile;
import model.unit.UnitType;
import model.unit.armed.Armed;

public class RangedUnit extends Armed {

	public RangedUnit(UnitType type, Tile tile, Civilization civilization, Game game){
		super(type, tile, civilization, game);
	}
	void rangedAttack(Tile targetTile){}
}
