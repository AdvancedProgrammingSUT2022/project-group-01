package model.unit.armed;

import model.civilization.Civilization;
import model.civilization.city.City;
import model.tile.Tile;
import model.unit.Unit;
import model.unit.UnitType;
import model.unit.trait.UnitTraits;

public class Armed extends Unit {
	private int XP = 0;

	public Armed(UnitType type, Tile tile, Civilization civilization) {
		super(type, tile, civilization);
	}

	public static Armed spawnArmed(UnitType unitType, Tile tile, Civilization ownerCivilization) {
		if (unitType.getRange() != -1)
			return RangedUnit.spawnRanged(unitType, tile, ownerCivilization);
		return new Armed(unitType, tile, ownerCivilization);
	}

	@Override
	public void moveTo(Tile tile) {
		updateMapAfterMove();
		currentTile.removeUnit(this);
		tile.setArmedUnit(this);

		this.currentTile = tile;
		tile.setArmedUnit(this);
	}

	public void attackTile(Tile destinationTile) {
		if (this instanceof Siege) {
			if (!((Siege) this).readyToAttack())
				return;
		}
		// TODO handle attack
	}

	public boolean isInAttackRange(Tile targetTile) {
		return false;
	}

	public void addXP(int XP) {
		this.XP += XP;
	}

	public double getDefensePower() {
		double power = 1 + (getCurrentTile().getTerrain().combatModifier / 100f);
		if (getTraitsList().contains(UnitTraits.ROUGH_TERRAIN_PENALTY)
				&& (getCurrentTile().getTerrain().isRough()
				|| (getCurrentTile().getFeature() != null && getCurrentTile().getFeature().isRough())))
			power *= 0.5f;
		return power;
	}

	public int getAttackPower() {
		return 0;
	}

	public void upgradeUnit() {
	}

	public void garrisonCity(City city) {
	}

	public void attackToCity(City city) {
	}
}
