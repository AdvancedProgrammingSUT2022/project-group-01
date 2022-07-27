package model.unit.armed;

import model.civilization.Civilization;
import model.tile.Tile;
import model.unit.CombatType;
import model.unit.UnitType;

public class RangedUnit extends Armed {

	public RangedUnit(UnitType type, Tile tile, Civilization civilization) {
		super(type, tile, civilization);
	}

	public static RangedUnit spawnRanged(UnitType type, Tile tile, Civilization civilization) {
		if (type.getCombatType() == CombatType.SIEGE)
			return Siege.spawnSiege(type, tile, civilization);
		return new RangedUnit(type, tile, civilization);
	}

	void rangedAttack(Tile targetTile) {
	}
}
