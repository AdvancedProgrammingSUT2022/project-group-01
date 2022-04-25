package model.unit;

import model.Game;
import model.civilization.Civilization;
import model.tile.Tile;

public class Melee extends Armed {
	public Melee(UnitType type, Tile tile, Civilization civilization, Game game){
		super(type, tile, civilization, game);
	}
	public void meleeAttack(Tile targetTile){}
}
