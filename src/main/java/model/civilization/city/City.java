package model.civilization.city;
//TODO : added Getter setter for center
import java.util.*;

import lombok.Getter;
import lombok.Setter;
import model.Notification;
import model.TurnBasedLogic;
import model.building.BuildingInventory;
import model.civilization.Civilization;
import model.civilization.Currency;
import model.civilization.Person;
import model.civilization.production.Producible;
import model.civilization.production.ProductionInventory;
import model.tile.Terrain;
import model.tile.Tile;
import model.unit.Unit;
import model.unit.UnitType;
import utils.Pair;

@Getter @Setter
public class City {
	private final int cityMaxHealth = 10;

	private Civilization civilization;
	private final Vector<Person> population;
	private String name;
	private Currency currency;
	private Currency changesOfCurrency;
	private Currency fixedCurrencyRate = new Currency(0,0,0);
	private double goldCurrencyFactor = 0;
	private double foodCurrencyFactor = 0;
	private double productCurrencyFactor = 0;
	private ProductionInventory productionInventory;
	private CityState state;
	private BuildingInventory buildingInventory;//TODO: merge with parham
	private Tile center;
	private final Vector<Tile> tiles;
	private double defencePower;
	private int attackPower;
	private double health;
	private Vector<Tile> nextTiles;
	private final int maxNextTiles = 3;
	private int turnToExpansion = 5;
	private int remainedTurnToExpansion = turnToExpansion;
	private Unit garrisonedUnit;
	private double beaker = 5;//todo check correct value
	private double fixedBeakerRate = 0;
	private double beakerFactor = 0;
	private int remainedTurnToGrowth = 8;
	@Setter @Getter
	private boolean attackedThisTurn = false;

	public City(String name, Civilization civilization, Tile center) {
		this.civilization =  civilization;
		this.population = new Vector<>(Arrays.asList(new Person()));
		this.name = name;
		this.center = center;
		tiles = new Vector<>();
		this.productionInventory = new ProductionInventory(this);
		tiles.add(center);
		tiles.addAll(center.getAdjacentTiles());
		nextTiles = new Vector<>();
		this.currency = new Currency(0,5,5);
		this.changesOfCurrency = new Currency(5,5,5);
		if(center.getTerrain().equals(Terrain.HILLS))
			defencePower += 5;
		for(Tile tile : tiles) {
			tile.setCivilization(civilization);
			tile.setOwnerCity(this);
		}
		this.productionInventory = new ProductionInventory(this);
		this.state = CityState.NORMAL;

		this.health = cityMaxHealth;

	}

	public void captureCity(Civilization newCivilization){
		civilization.getCities().remove(this);
		productionInventory.initList();
		this.health = cityMaxHealth;
		remainedTurnToExpansion = turnToExpansion;
		remainedTurnToGrowth = 8;
		newCivilization.addNewCity(this);
		this.civilization = newCivilization;
		for(Tile tile : tiles){
			tile.setCivilization(newCivilization);
			if(tile.getCivilianUnit() != null)
				tile.getCivilianUnit().setOwnerCivilization(newCivilization);
		}
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
		return this.civilization;
	}


	public ProductionInventory getProductionInventory() {
		return this.productionInventory;
	}

	public void setNewProduction(Producible producible) {
		productionInventory.setCurrentProduction(producible);
	}

	private void updateCurrency() {
		changesOfCurrency.setValue(0,0,0);
		changesOfCurrency.add(fixedCurrencyRate);
		for(Tile tile : tiles){
			currency.add(tile.getCurrency());
			changesOfCurrency.add(tile.getCurrency());
		}
		currency.increase(changesOfCurrency.getGold()*goldCurrencyFactor,changesOfCurrency.getProduct()*productCurrencyFactor,changesOfCurrency.getFood()*foodCurrencyFactor);
		currency.increase(-currency.getGold(),0,0);
		if((productionInventory.getCurrentProduction() != null) &&(productionInventory.getCurrentProduction().equals(UnitType.SETTLER))){
			if(currency.getFood() > 0)
				currency.increase(0,0,-currency.getFood());
			if(changesOfCurrency.getFood() > 0)
				changesOfCurrency.increase(0,0,-changesOfCurrency.getFood());
		}
	}

	public void changeHealth(double deltaHealth) {
		health += deltaHealth;
		health = Math.min(cityMaxHealth, health);
		if (health <= 0) {
			// Annex the city todo
			this.state = CityState.ANNEXED;
			health = 0.5f;
		}
	}

	public void resetChangesOfCurrency(){
		changesOfCurrency.setValue(0,0,0);
	}


	private void handlePopulationIncrease(){
		if(remainedTurnToExpansion == 0 && currency.getFood() > 15){
			population.add(new Person());
			remainedTurnToGrowth = 8;
		}
		if(remainedTurnToExpansion > 0)
			remainedTurnToGrowth--;
	}

	public void destroy() {
		civilization.getCities().remove(this);
		for(Tile tile : tiles){
			tile.setOwnerCity(null);
			tile.setCivilization(null);
			tile.removeImprovement();
			tile.getPeopleInside().clear();
		}
	}

	public void nextTurn() {
		updateCurrency();
		if(productionInventory.getCurrentProduction() != null) {
			productionInventory.payProduction(currency.getProduct());
			currency.increase(0,-currency.getProduct(),0);
		}

		handleNextTiles();
		handlePopulationIncrease();
		updateBeaker();
		attackedThisTurn = false;
		if(state == CityState.NORMAL)
			changeHealth(+1);
		//todo create notification here
	}

	public Unit getGarrisonedUnit() {
		return garrisonedUnit;
	}

	public void setGarrisonedUnit(Unit garrisonedUnit) {
		this.garrisonedUnit = garrisonedUnit;
	}

	public double getDefencePower() {
		return defencePower;
	}

	public void updateDefencePower(int defencePower) {

	}

	public double getAttackPower() {
		return ((getPopulation().size() + 40) / 10f) * (getCenter().getTerrain().equals(Terrain.HILLS) ? 1.3 : 1);
	}

	public void setAttackPower(int attackPower) {
		this.attackPower = attackPower;
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
			int i = new Random(58).nextInt(nextTiles.size());
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
			tile.setOwnerCity(this);
			this.tiles.add(tile);
			civilization.addTileResources(tile);
			nextTiles.remove(tile);
		}
	}

	/**
	 * it should be called after updating currency
	 */
	public void updateBeaker(){
		//todo implement here(update currency if is necessary and update with buildings break)
		this.beaker = 0;
		this.beaker += fixedBeakerRate;
		if(this.civilization.getCapital() == this)
			this.beaker = 3;
		this.beaker += population.size();
		this.beaker += this.beaker*beakerFactor;
	}

	public void increaseDefencePower(double amount){
		this.defencePower += amount;
	}

	public void increaseCurrency(double gold, double production, double food){
		this.currency.increase(gold, production, food);
	}

	public HashMap<String, String> getScreen(){
		return new HashMap<>(){{
			put("name", name);
			put("defencePower", String.valueOf(defencePower));
			put("health", String.valueOf(health));
			put("state", state.name().toLowerCase());
			put("gold", String.valueOf(currency.getGold()));
			put("food", String.valueOf(currency.getFood()));
			put("production", String.valueOf(currency.getProduct()));
			put("beaker", String.valueOf(beaker));
			put("population",String.valueOf(population.size()));
		}};
	}


}