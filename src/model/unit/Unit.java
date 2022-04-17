package unit;

import technology.TechnologyType;

import java.util.List;

public class Unit {

	private int health;
	private int movementPoint;
	private List<TechnologyType> requiredTechnologies;
	private int cost;
	private int remainedCost;
	private Cell currentCell;
	private boolean isHealing;

	public void fortifyUntilHeal(){}
	public boolean nextTurn(Civilization civilization, City city){}
}
