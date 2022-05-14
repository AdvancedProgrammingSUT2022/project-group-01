package model.tile;
import java.util.Vector;

import lombok.Getter;
import lombok.Setter;
import model.Player;
import model.ProgressState;
import model.civilization.Civilization;
import model.civilization.Currency;
import model.civilization.Person;
import model.civilization.city.City;
import model.improvement.ImprovementInventory;
import model.improvement.ImprovementType;

import model.improvement.MiscellaneousTileActionsInventory;
import model.resource.ResourceType;
import model.unit.Unit;
import model.unit.action.UnitActions;
import model.unit.armed.Armed;
import model.unit.civilian.Civilian;
import utils.VectorUtils;

import javax.swing.plaf.ColorUIResource;

// TODO added get available resource
@Getter @Setter
public class Tile {
	private int pCoordinate;
	private int qCoordinate;
	private int mapNumber;
	private Terrain terrain;
	private TerrainFeature feature;
	private Civilization civilization;
	private Player player;
	private City ownerCity;
	private Armed armedUnit;
	private Civilian civilianUnit;
	private ImprovementInventory improvementInventory = new ImprovementInventory(this);
	private MiscellaneousTileActionsInventory miscellaneousTileActionsInventory;
	private ResourceType availableResource;
	private boolean hasRoad;
	private boolean hasRailRoad;
	private Boarder[] nearbyBoarders;
	private boolean isDestroyed;
	private Vector<Person> peopleInside;
	private Currency currency;
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

	public ResourceType getAvailableResource() {
		return availableResource;
	}

	public void setCivilization(Civilization civilization) {
		this.civilization = civilization;
	}
	public Currency getCurrency(){
		 Currency output = new Currency(getGoldYield() - getMaintenance(), getProductionYield(), getFoodYield());
		 // Adding Improvement Currency
		output.add(this.improvementInventory.getCurrency());
		// Adding Resource Currency
		if(availableResource != null)
			output.add(availableResource.getCurrency(this));
		return output;
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

	public int getQCoordinate() {
		return qCoordinate;
	}

	public Vector<Person> getPeopleInside() {
		return peopleInside;
	}

	public Tile(Terrain terrain, TerrainFeature feature, Civilization civilization, ResourceType availableResources, int p, int q, int number) {
		this.terrain = terrain;
		this.feature = feature;
		nearbyBoarders = new Boarder[6];
		this.civilization = civilization;
		this.availableResource = availableResources;
		this.isDestroyed = false;
		this.peopleInside = new Vector<>();
		pCoordinate = p;
		qCoordinate = q;
		this.mapNumber = number;
		this.miscellaneousTileActionsInventory = new MiscellaneousTileActionsInventory(this);
	}

	public void addPerson(Person person) {
		peopleInside.add(person);
	}
	public void removePerson(Person person){
		peopleInside.remove(person);
	}

	public void buildImprovement(ImprovementType improvement){
		if(this.improvementInventory != null){
			if(this.improvementInventory.getImprovement() != null) {
				if (this.improvementInventory.getImprovement().equals(improvement)) {
					if (this.improvementInventory.getState().equals(ProgressState.STOPPED))
						this.improvementInventory.progress();
				}

			}
			else this.improvementInventory.reset(improvement);
		}
	}
	public void removeImprovement(){
		if(this.improvementInventory == null) return;
		this.improvementInventory.remove();

	}
	public void removeResource(){
		availableResource = null;
	}
	public boolean doesHaveRoad() {
		return hasRoad;
	}

	public void buildRoad() {
		this.hasRoad = true;
	}

	public Boarder getBoarderInfo(int i){
		return nearbyBoarders[i];
	}
	public boolean doesHaveRailRoad() {
		return hasRailRoad;
	}

	public void buildRailRoad() {
		this.hasRailRoad = true;
	}

	public boolean hasRiverNearby() {
		for(Boarder boarder : this.nearbyBoarders) {
			if(boarder != null)
				if (boarder.isRiver()) return true;
		}
		return false;
	}
	public void removeRoads(){
		this.hasRoad = false;
		this.hasRailRoad = false;
	}

	public boolean isDestroyed() {
		return isDestroyed;
	}

	public void setDestroyed(boolean destroyed) {
		isDestroyed = destroyed;
	}

	public Tile(int p, int q, int number) {
		pCoordinate = p;
		qCoordinate = q;
		nearbyBoarders = new Boarder[6];
        this.mapNumber = number;
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
		int MP = 0;
		MP += this.terrain.movementCost;
		if(this.feature != null) MP += this.feature.movementCost;
		if(hasRoad || hasRoad) MP /= 4;
		return MP;
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
		if(improvementInventory.getImprovement() == null) return;
		if (this.improvementInventory.getImprovement().equals(improvement)){
			if(this.improvementInventory.getState().equals(ProgressState.COMPLETE))
				this.improvementInventory.remove();
			}
	}

	public void stopImprovementProgress(){
		if(this.improvementInventory != null){
			if(this.improvementInventory.getState().equals(ProgressState.IN_PROGRESS))
				this.improvementInventory.stop();
		}
	}

	/**
	 * 
	 * @param unit
	 */
	public void removeUnit(Unit unit) {
		if(this.civilianUnit != null && this.civilianUnit.equals(unit))
			this.civilianUnit = null;
		else if(this.armedUnit != null && this.armedUnit.equals(unit))

			this.armedUnit = null;
	}


	public void destroy() {
		this.isDestroyed = true;
	}

	public ImprovementType getBuiltImprovement() {
		if(this.improvementInventory != null){
			if(this.improvementInventory.getState().equals(ProgressState.COMPLETE))
				return this.improvementInventory.getImprovement();
		}
		return null;
	}




	public void setAvailableResource(ResourceType resourceType){
		this.availableResource = resourceType;
	}
	public Vector<Tile> getAdjacentTiles(){
		Vector<Tile> adjacentTiles = new Vector<>();
		Tile tempTile;
		for(int i = 0; i < 6; i++){
			if((tempTile = this.nearbyBoarders[i].getOtherTile(this)) != null)
				adjacentTiles.add(tempTile);
		}
		return adjacentTiles;
	}

	public Vector<Tile> getSight(int depth){
		if(depth == 1)
			return getAdjacentTiles();
		Vector<Tile> out = new Vector<>(getAdjacentTiles());
		for (Tile adjacentTile : getAdjacentTiles()) {
			if(!adjacentTile.terrain.isHighland || this.terrain.isHighland)
				out.addAll(adjacentTile.getAdjacentTiles());
		}
		return out;
	}

	public Unit getSameTypeUnit(Unit unit) {
		if(unit instanceof Civilian) return getCivilianUnit();
		return getArmedUnit();
	}

	public enum VisibilityState{
		VISIBLE,
		DISCOVERED,
		FOG_OF_WAR
	}

	public static Vector<Tile> getAdjacentForArea(Vector<Tile> area, int depth){
		Vector<Tile> out = new Vector<>();
		for(int i=0;i<depth;i++){
			if(i == 0)
				for(Tile tile : area){
					out.addAll(tile.getAdjacentTiles());
				}
			else
				for(Tile tile : out){
					out.addAll(tile.getAdjacentTiles());
				}
			out.removeIf(area::contains);
		}
		out = VectorUtils.unique(out);
		return out;
	}

	public City getOwnerCity() {
		return ownerCity;
	}

	public void setOwnerCity(City ownerCity) {
		this.ownerCity = ownerCity;
	}

	public void pillageImprovement(){
		if(this.improvementInventory != null)
			this.improvementInventory.damage();
	}

	public ProgressState getImprovementInventoryState(){
		return this.improvementInventory.getState();
	}

	public ImprovementType getImprovementInAction(){
		return this.improvementInventory.getImprovement();
	}

	public void repairImprovement(){
		this.improvementInventory.repair();
	}

	public int getImprovementTurnsLeft(){
		return this.improvementInventory.getTurnsLeft();
	}

	public void orderWorkerAction(UnitActions action){
		this.miscellaneousTileActionsInventory.setAction(action);
	}

	public int getMaintenance(){
		int cost = 0;
		if(hasRoad) cost += 3;
		if(hasRailRoad) cost += 3;
		return cost;
	}
}