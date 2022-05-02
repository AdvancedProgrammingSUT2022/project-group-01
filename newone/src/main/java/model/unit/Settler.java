package model.unit;

import model.Game;
import model.Map;
import model.civilization.Civilization;
import model.tile.Tile;


public class Settler extends Civilian {

	public Settler(UnitType type, Tile tile, Civilization civilization, Game game){
		super(type, tile, civilization, game);
	}
	public boolean canSettle(Map map){
		return false;
	}
	public void settle(){}
}
