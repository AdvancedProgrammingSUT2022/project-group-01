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
import utils.Pair;

public class City {

	private Civilization civilization;
	private final Vector<Person> population;
	private String name;

	private Currency currency;
	private ProductionInventory productionInventory;
	private CityState state;
	private BuildingInventory buildingInventory;//TODO: merge with parham
	private Tile center;
	private final Vector<Tile> tiles;
	private int defencePower;
	private int attackPower;
	private int health;
	private Vector<Tile> nextTiles;
	private final int maxNextTiles = 3;
	private final int turnToExpansion = 5;
	private int remainedTurnToExpansion = turnToExpansion;
	private Unit garrisonedUnit;
	public City(String name, Civilization civilization, Tile center) {
		this.civilization =  civilization;
		this.population = new Vector<>(Arrays.asList(new Person(center)));
		this.name = name;
		this.center = center;
		tiles = new Vector<>();
		tiles.add(center);
		tiles.addAll(center.getAdjacentTiles());
		nextTiles = new Vector<>();
		this.currency = new Currency(5,5,5);//TODO: check this values
	}



	public Vector<Tile> getTiles() {
		return tiles;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Currency getCurrency() {
		return currency;
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
		//TODO anything else? + UNIT:
		Currency changes = new Currency(0,0,0);
		//don't forget to update changes for unit and ...
		for(Tile tile : tiles){
			currency.add(tile.getCurrency());
			changes.add(tile.getCurrency());
		}
		//you can show changes like food: +2 and ...
	}

	private void handlePopulationIncrease(){

	}

	public void destroy() {
		//TODO free tiles and others:check with parham
		population.clear();
		civilization.getCities().remove(this);
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

	public Tile getCenterTile() {
		return center;
	}

	private void handleNextTiles(){
		if(nextTiles.size() < maxNextTiles){
			int requiredTileCount = maxNextTiles - nextTiles.size();
			for (int i = 0; i < requiredTileCount; i++) {
				findNextTile();
			}
		}
		if(remainedTurnToExpansion == 0){
			remainedTurnToExpansion = turnToExpansion;
			int i = new Random().nextInt(nextTiles.size());
			addNewTiles(new Vector<>(Arrays.asList(nextTiles.get(i))));
		}
		remainedTurnToExpansion--;
	}

	private void findNextTile(){
		Vector<Tile> adjacentTiles = new Vector<>();
		for (Tile tile : this.tiles) {
			adjacentTiles.addAll(tile.getAdjacentTiles());
		}
		adjacentTiles.removeIf(this.tiles::contains);
		for(Tile tile : adjacentTiles){
			if(!nextTiles.contains(tile)){
				nextTiles.add(tile);
				return;
			}
		}
	}

	public Vector<Pair<Tile, Integer>> getPurchasableTiles(){
		Vector<Tile> adjacentTiles = Tile.getAdjacentForArea(this.tiles, 1);
		Vector<Pair<Tile, Integer>> out = new Vector<>();
		for (Tile adjacentTile : adjacentTiles) {
			out.add(new Pair<>(adjacentTile, 50));
		}
		return out;
	}

	public void addNewTiles(Vector<Tile> tiles){
		for(Tile tile: tiles){
			tile.setCivilization(this.civilization);
			this.tiles.add(tile);
			nextTiles.remove(tile);
		}
	}


}