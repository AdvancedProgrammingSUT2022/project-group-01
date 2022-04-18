package model.tile;
import java.util.Vector;

import model.Player;
import model.civilization.Civilization;
import model.civilization.Person;
import model.civilization.city.City;
import model.improvement.Improvement;
import model.improvement.ImprovementType;
import model.resource.Resource;
import model.unit.Armed;
import model.unit.Civilian;
import model.unit.Unit;

public class Tile {

	private Terrain terrain;
	private TerrainFeature feature;
	private VisibilityState state;
	private Civilization civilization;
	private Player player;
	private City innerCity;
	private Armed armedUnit;
	private Civilian civilianUnit;
	private Vector<ImprovementType> builtImprovements;
	private Vector<Resource> availableResources;
	private boolean hasRoad;
	private boolean hasRailRoad;
	private Boarder[] nearbyBoarders;
	private boolean isDestroyed;
	private Vector<Person> peopleInside;


	public Vector<Person> getPeopleInside() {
		return peopleInside;
	}

	public Tile(Terrain terrain, TerrainFeature feature, Civilization civilization, Vector<Resource> availableResources) {
		this.terrain = terrain;
		this.feature = feature;
		nearbyBoarders = new Boarder[6];
		this.civilization = civilization;
		this.availableResources = availableResources;
		this.isDestroyed = false;
	}

	public void addPerson(Person person) {
		peopleInside.add(person);
	}
	public void removePerson(Person person){
		peopleInside.remove(person);
	}
	public void buildImprovement(Improvement improvement){
		builtImprovements.add(improvement.getType());
	}
	public void removeImprovement(Improvement improvement){
		builtImprovements.remove(improvement.getType());
	}
	public void removeResource(Resource resource){
		availableResources.remove(resource);
	}
	public boolean doesHaveRoad() {
		return hasRoad;
	}
	public void addRiver(Tile tile){}
	public void buildRoad() {
		this.hasRoad = true;
	}
	public void setBoarder(Boarder boarder, int i){}
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

	public Tile() {
		// TODO - implement Cell.Cell
		throw new UnsupportedOperationException();
	}

	public int getFoodYield() {
		// TODO - implement Cell.getFoodYield
		throw new UnsupportedOperationException();
	}

	public int getGoldYield() {
		// TODO - implement Cell.getGoldYield
		throw new UnsupportedOperationException();
	}

	public int getProductionYield() {
		// TODO - implement Cell.getProductionYield
		throw new UnsupportedOperationException();
	}

	public int getCombatModifierRate() {
		// TODO - implement Cell.getCombatModifierRate
		throw new UnsupportedOperationException();
	}

	public int getMovementCost() {
		// TODO - implement Cell.getMovementCost
		// If river & move => = moving point hash
		throw new UnsupportedOperationException();
	}

	public boolean isPassable() {
		// TODO - implement Cell.isPassable
		throw new UnsupportedOperationException();
	}

	public void buildRoute() {
		// TODO - implement Cell.buildRoute
		throw new UnsupportedOperationException();
	}

	public boolean getHasRoute() {
		return this.hasRoad;
	}

	public void removeFeature() {
		// TODO - implement Cell.removeFeature
		throw new UnsupportedOperationException();
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
		// TODO - implement Cell.destroy
		throw new UnsupportedOperationException();
	}

	public VisibilityState getState() {
		return state;
	}

	public void setState(VisibilityState state) {
		this.state = state;
	}

	enum VisibilityState{
		VISIBLE,
		DISCOVERED,
		FOG_OF_WAR
	}

}