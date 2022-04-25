package model.unit;

import model.Game;
import model.civilization.Civilization;
import model.tile.Tile;

public class Armored extends Armed {
	public Armored(UnitType type, Tile tile, Civilization civilization, Game game){
		super(type, tile, civilization, game);
	}
}
