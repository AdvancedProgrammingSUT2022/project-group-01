package model.civilization;

import model.civilization.city.City;
import model.map.SavedMap;
import model.technology.TechTree;
import model.technology.TechnologyType;
import model.tile.Tile;
import model.unit.Unit;
import utils.VectorUtils;

import java.util.*;

public class Civilization {

	private Civilizations civilization;//enum
	private Vector<City> cities;
	private City capital;
	private Currency currency;
	private Currency citiesCurrency;
	private int happiness;
	private SavedMap map;
	private Vector<Unit> units;//TODO merge with safar

	private TechTree techTree;//TODO merge with safar
	private Vector<Civilization> knownCivilizations;

	public Civilization(Civilizations civilization, City capital) {
		this.civilization = civilization;
		this.capital = capital;
		units = new Vector<>(); // ADDED BY PRCR
		cities = new Vector<>(); // ADDED BY PRCR
	}

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

	public void addUnit(Unit unit){
		units.add(unit);
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

	public void updateHappiness(){
		happiness = 0;
		for(City city : cities)
			happiness += city.getHappiness();
		//todo implement for civilization based happiness bonus
	}

	public int getHappiness(){
		return happiness;
	}

	public Vector<Civilization> getKnownCivilizations() {
		return knownCivilizations;
	}

	public void addKnownCivilization(Civilization civilization){
		if(!knownCivilizations.contains(civilization))
			knownCivilizations.add(civilization);
	}


	public Vector<Unit> getUnits() {
		return units;
	}

	public Vector<City> getCities(){
		return cities;
	}

	public void increaseCurrency(Currency currency){
		this.currency.add(currency);
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
		out = VectorUtils.unique(out);
		return out;
	}

	public void deleteCity(City city){
		cities.remove(city);
	}

	private void handleCurrency(){
		citiesCurrency = new Currency(0,0,0);
		for(City city : cities){
			citiesCurrency.add(city.getCurrency());
		}
		//todo update unit and ... for currency
	}

	public City getCapital() {
		return capital;
	}
}