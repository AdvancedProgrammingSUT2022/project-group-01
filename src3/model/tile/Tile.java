package model.tile;
import java.util.Vector;

import model.Player;
import model.civilization.Civilization;
import model.civilization.Person;
import model.civilization.city.City;
import model.improvement.Improvement;
import model.improvement.ImprovementType;
import model.resource.Resource;
import model.resource.ResourceType;
import model.unit.Armed;
import model.unit.Civilian;
import model.unit.Unit;
// TODO added get available resource
public class Tile {
	private int pCoordinate;
	private int qCoordinate;
	private int mapNumber;
	private Terrain terrain;
	private TerrainFeature feature;
	private Civilization civilization;
	private Player player;
	private City innerCity;
	private Armed armedUnit;
	private Civilian civilianUnit;
	private Improvement builtImprovement;
	private Resource availableResource;
	private boolean hasRoad;
	private boolean hasRailRoad;
	private Boarder[] nearbyBoarders;
	private boolean isDestroyed;
	private Vector<Person> peopleInside;

	public Civilization getCivilization() {
		return civilization;
	}

	public int getMapNumber() {
		return mapNumber;
	}

	public Armed getArmedUnit() {
		return armedUnit;
	}

	public void setArmedUnit(Armed armedUnit) {
		this.armedUnit = armedUnit;
	}

	public Civilian getCivilianUnit() {
		return civilianUnit;
	}

	public void setCivilianUnit(Civilian civilianUnit) {
		this.civilianUnit = civilianUnit;
	}

	public Resource getAvailableResource() {
		return availableResource;
	}

	public void setCivilization(Civilization civilization) {
		this.civilization = civilization;
	}

	public int getPCoordinate() {
		return pCoordinate;
	}

	public Terrain getTerrain() {
		return terrain;
	}

	public void setTerrain(Terrain terrain) {
		this.terrain = terrain;
	}

	public TerrainFeature getFeature() {
		return this.feature;
	}

	public void setFeature(TerrainFeature feature) {
		this.feature = feature;
	}

	public void setpCoordinate(int pCoordinate) {
		this.pCoordinate = pCoordinate;
	}

	public void setBoarder(Boarder boarder, int i){
		this.nearbyBoarders[i] = boarder;
	}
	public Boarder getBoarder(int i){
		return nearbyBoarders[i];
	}
	public int getBoarderDirection(Boarder boarder){
		for(int i = 0; i < 6; i++){
			if(this.nearbyBoarders[i].equals(boarder))
				return i;
		}
		return -1;
	}

	public int getqCoordinate() {
		return qCoordinate;
	}

	public void setqCoordinate(int qCoordinate) {
		this.qCoordinate = qCoordinate;
	}

	public Vector<Person> getPeopleInside() {
		return peopleInside;
	}

	public Tile(Terrain terrain, TerrainFeature feature, Civilization civilization, Resource availableResources, int p, int q) {
		this.terrain = terrain;
		this.feature = feature;
		nearbyBoarders = new Boarder[6];
		this.civilization = civilization;
		this.availableResource = availableResources;
		this.isDestroyed = false;
		pCoordinate = p;
		qCoordinate = q;
		// TODO set map Numeber
	}

	public void addPerson(Person person) {
		peopleInside.add(person);
	}
	public void removePerson(Person person){
		peopleInside.remove(person);
	}
	public void buildImprovement(Improvement improvement){
		builtImprovement = improvement;;
	}
	public void removeImprovement(Improvement improvement){
		builtImprovement = null;
	}
	public void removeResource(){
		availableResource = null;
	}
	public boolean doesHaveRoad() {
		return hasRoad;
	}
	public void addRiver(Tile tile){}
	public void buildRoad() {
		this.hasRoad = true;
	}
	public Boarder getBoarderInfo(int i){return nearbyBoarders[i];}
	public boolean isHasRailRoad() {
		return hasRailRoad;
	}

	public void buildRailRoad(boolean hasRailRoad) {
		this.hasRailRoad = hasRailRoad;
	}

	public boolean hasRiverNearby() {
		return false;
	}
	public void removeRiver(Tile tile){}
	public boolean checkRiverByTile(Tile tile){return false;}
	public boolean isDestroyed() {
		return isDestroyed;
	}

	public void setDestroyed(boolean destroyed) {
		isDestroyed = destroyed;
	}

	public Tile(int p, int q) {
		pCoordinate = p;
		qCoordinate = q;
		nearbyBoarders = new Boarder[6];
		//TODO set map number
	}

	public int getFoodYield() {
		int yield = 0;
		yield += terrain.food;
		if(feature != null)
			yield += feature.food;
		return yield;
	}

	public int getGoldYield() {
		int yield = 0;
		yield += terrain.gold;
		if(feature != null)
			yield += feature.gold;
		return yield;
	}

	public int getProductionYield() {
		int yield = 0;
		yield += terrain.production;
		if(feature != null)
			yield += feature.production;
		return yield;
	}

	public int getCombatModifierRate() {
		int cMRate = 0;
		cMRate += terrain.combatModifier;
		if(feature != null)
			cMRate += feature.combatModifiers;
		return cMRate;
	}

	public int getMovementCost() {
		// TODO - implement Cell.getMovementCost
		// If river & move => = moving point hash
		throw new UnsupportedOperationException();
	}

	public boolean isPassable() {
		return this.terrain.passable;
	}

	public void buildRoute() {
		this.hasRoad = true;
	}

	public boolean getHasRoute() {
		return this.hasRoad;
	}

	public void removeFeature() {
		this.feature = null;
	}

	/**
	 * 
	 * @param improvement
	 */
	public void removeBuiltImprovements(ImprovementType improvement) {
		// TODO - implement Cell.removeBuiltImprovements
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param unit
	 */
	public void removeUnit(Unit unit) {
		// TODO - implement Cell.removeUnit
		throw new UnsupportedOperationException();
	}

	public boolean getIsDestroyed() {
		// TODO - implement Cell.getIsDestroyed
		throw new UnsupportedOperationException();
	}

	public void destroy() {
		this.isDestroyed = true;
	}

	public Improvement getBuiltImprovement() {
		return builtImprovement;
	}

	public void setBuiltImprovement(Improvement builtImprovement) {
		this.builtImprovement = builtImprovement;
	}

	public void setAvailableResource(ResourceType resourceType){
		this.availableResource = new Resource(resourceType);
	}
	public enum VisibilityState{
		VISIBLE,
		DISCOVERED,
		FOG_OF_WAR
	}

}