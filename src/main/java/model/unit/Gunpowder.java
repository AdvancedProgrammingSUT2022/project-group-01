package model.unit;

import model.Game;
import model.civilization.Civilization;
import model.tile.Tile;

public class Gunpowder extends Armed {

	public Gunpowder(UnitType type, Tile tile, Civilization civilization, Game game){
		super(type, tile, civilization, game);
	}
}
