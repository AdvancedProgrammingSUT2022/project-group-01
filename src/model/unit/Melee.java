package model.unit;

import model.tile.Tile;

public class Melee extends Armed {
	public Melee(UnitType type){
		super(type);
	}
	public void meleeAttack(Tile targetTile){}
}
