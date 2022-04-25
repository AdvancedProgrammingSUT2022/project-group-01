package model.civilization.city;
//TODO : added Getter setter for center
import java.util.*;

import model.building.BuildingInventory;
import model.civilization.Civilization;
import model.civilization.Currency;
import model.civilization.Person;
import model.civilization.production.ProductionInventory;
import model.tile.Tile;
import model.unit.Unit;

public class City {

	private Civilization civilization;
	private Vector<Person> population;


	private Currency currency;
	private ProductionInventory productionInventory;
	private CityState state;
	private BuildingInventory buildingInventory;//TODO: merge with parham
	private Tile center;//todo merge with parham
	private Vector<Tile> tiles;//todo merge with parham
	private int defencePower;
	private int attackPower;


	private int health;

	private Unit garrisonedUnit;
	public City() {
		// TODO - implement model.civilization.city.City.model.civilization.city.City
		throw new UnsupportedOperationException();
	}

	public Civilization getCivilization(){
		throw new UnsupportedOperationException();
	}

	public ProductionInventory getProductionsList() {
		// TODO - implement model.civilization.city.City.getProductionsList
		throw new UnsupportedOperationException();
	}

	public void setNewProduction() {
		// TODO - implement model.civilization.city.City.setNewProduction
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param civilization
	 * @param state
	 */
	public void setNewState(Civilization civilization, CityState state) {
		// TODO - implement model.civilization.city.City.setNewState
		throw new UnsupportedOperationException();
	}

	private void updateCurrency() {
		// TODO - implement model.civilization.city.City.updateCurrency
		throw new UnsupportedOperationException();
	}

	public void destroy() {
		// TODO - implement model.civilization.city.City.destroy
		throw new UnsupportedOperationException();
	}

	public void nextTurn() {
		// TODO - implement model.civilization.city.City.nextTurn
		throw new UnsupportedOperationException();
	}

	public double calculateScience(){
		// TODO
		return 0;
	}

	public Unit getGarrisonedUnit() {
		return garrisonedUnit;
	}

	public void setGarrisonedUnit(Unit garrisonedUnit) {
		this.garrisonedUnit = garrisonedUnit;
	}

	public int getDefencePower() {
		return defencePower;
	}

	public void updateDefencePower(int defencePower) {

	}

	public int getAttackPower() {
		return attackPower;
	}

	public void setAttackPower(int attackPower) {
		this.attackPower = attackPower;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public void attackToUnit(Unit unit){}


	public Vector<Person> getPopulation() {
		return population;
	}

	public Tile getCenter() {
		return center;
	}

	public void setCenter(Tile center) {
		this.center = center;
	}
}