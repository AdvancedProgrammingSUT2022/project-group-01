package model.unit;
// TODO : ADDED GET TILE
// TODO : ADDED Get, Unit Type
// TODO : ADDED getOwnerCivilization
import model.civilization.Civilization;
import model.civilization.city.City;
import model.civilization.production.Production;
import model.technology.TechnologyType;
import model.tile.Tile;

import java.util.List;

public class Unit extends Production {
	private Civilization ownerCivilization;
	private int health;
	private int movementPoint;
	private List<TechnologyType> requiredTechnologies;
	private int cost;
	private int remainedCost;
	private Tile currentTile;
	private boolean isHealing;
	private UnitType type;

	public Unit(UnitType type){}

	public void fortifyUntilHeal(){}
	public boolean nextTurn(Civilization civilization, City city){ return false; }
	public void defendAgainstMelee(Unit enemy){}
	public void defendAgainstRanged(Unit enemy){}

	public Tile getCurrentTile() {
		return currentTile;
	}

	public void execute(ActionsType actionType){}

	public UnitType getType() {
		return type;
	}

	public Civilization getOwnerCivilization() {
		return ownerCivilization;
	}
}
