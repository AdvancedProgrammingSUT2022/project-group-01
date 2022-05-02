package model.unit;

import model.Game;
import model.civilization.Civilization;
import model.tile.Tile;

public class Civilian extends Unit {

	public Civilian(UnitType type, Tile tile, Civilization civilization, Game game){
		super(type, tile, civilization, game);
	}

	public void captureUnit(Civilization enemy){
	}
}