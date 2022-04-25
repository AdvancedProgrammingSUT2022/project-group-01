package model.civilization;

import model.civilization.city.City;
import model.map.SavedMap;
import model.technology.TechTree;
import model.technology.TechnologyType;
import model.tile.Tile;
import model.unit.Unit;

import java.util.*;

public class Civilization {

	private Civilizations civilization;//enum
	private Vector<City> cities;
	private City capital;
	private Currency currency;
	private int happiness;
	private SavedMap map;
	double science;
	private Vector<Unit> units;//TODO merge with safar

	private TechTree techTree;//TODO merge with safar
	private Vector<Civilization> knownCivilizations;

	public TechTree getResearchTree() {
		return techTree;
	}

	public void newResearch(TechnologyType technologyType) {
		//TODO handle on next checkpoint
		techTree.research(technologyType);
	}

	public Currency getCurrency() {
		return this.currency;
	}


	public void addNewCity(City city) {//TODO add arguments
		cities.add(city);
	}

	private void updateCurrency() {//TODO for each turn
		// TODO - implement model.civilization.Civilization.updateCurrency
		throw new UnsupportedOperationException();
	}

	public void nextTurn() {
		// TODO - implement model.civilization.Civilization.nextTurn
		throw new UnsupportedOperationException();
	}

	public void increaseHappiness(int amount){
		happiness += amount;
	}

	public void decreaseHappiness(int amount){
		happiness -= amount;
	}

	public int getHappiness(){
		return happiness;
	}

	public double calculateScience(){
		//TODO handle in next checkpoint
		return 0;
	}

	public Vector<Civilization> getKnownCivilizations() {
		return knownCivilizations;
	}

	public void addKnownCivilization(Civilization civilization){
		if(!knownCivilizations.contains(civilization))
			knownCivilizations.add(civilization);
	}

	public double getScience() {
		return science;
	}

	public void setScience(double science) {
		this.science = science;
	}

	public Vector<Unit> getUnits() {
		return units;
	}

	public Vector<City> getCities(){
		return cities;
	}

	public Civilizations getCivilization() {
		return civilization;
	}

	public Vector<Tile> visibleTiles(){
		Vector<Tile> ourCells = new Vector<>();
		for (City city : cities) {
			ourCells.addAll(city.getTiles());
		}
		for(Unit unit : units) {
			ourCells.add(unit.getCurrentTile());
		}
		Vector<Tile> out = new Vector<>();
		for (Tile tile : ourCells) {
			out.addAll(tile.getSight(2));
		}
		//TODO make out unique
		return out;
	}
}