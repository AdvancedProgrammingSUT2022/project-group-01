package model.unit;

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

	public Unit(UnitType type){}

	public void fortifyUntilHeal(){}
	public boolean nextTurn(Civilization civilization, City city){ return false; }
	public void defendAgainstMelee(Unit enemy){}
	public void defendAgainstRanged(Unit enemy){}

	public void execute(ActionsType actionType){}
}
