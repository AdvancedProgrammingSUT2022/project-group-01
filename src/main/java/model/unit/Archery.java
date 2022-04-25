package model.unit;

import model.Game;
import model.civilization.Civilization;
import model.tile.Tile;

public class Archery extends RangedUnit {
	public Archery(UnitType type, Tile tile, Civilization civilization, Game game){
		super(type, tile, civilization, game);
	}
}
