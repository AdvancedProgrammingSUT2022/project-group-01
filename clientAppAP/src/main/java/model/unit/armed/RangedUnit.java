package model.unit.armed;

import model.Game;
import model.civilization.Civilization;
import model.tile.Tile;
import model.unit.CombatType;
import model.unit.UnitType;
import model.unit.armed.Armed;

public class RangedUnit extends Armed {

	public RangedUnit(UnitType type, Tile tile, Civilization civilization){
		super(type, tile, civilization);
	}

	public static RangedUnit spawnRanged(UnitType type, Tile tile, Civilization civilization){
		if(type.getCombatType() == CombatType.SIEGE)
			return Siege.spawnSiege(type, tile, civilization);
		return new RangedUnit(type, tile, civilization);
	}

	void rangedAttack(Tile targetTile){}
}
