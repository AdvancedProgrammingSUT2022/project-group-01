package tile;
import java.util.Vector;
import
public class Tile {

	private Terrain terrain;
	private TerrainFeature feature;
	private Civilization civilization;
	private Player player;
	private City innerCity;
	private Armed armedUnit;
	private Civilian civilianUnit;
	private Vector<ImprovementType> builtImprovements;
	private Vector<Resource> availableResources;
	private boolean hasRoad;
	private boolean hasRailRoad;
	private boolean hasRiver;
	private boolean isDistroyed;
	private Vector<Person> peopleInside;

	public Vector<Person> getPeopleInside() {
		return peopleInside;
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

	public void buildRoad() {
		this.hasRoad = true;
	}

	public boolean isHasRailRoad() {
		return hasRailRoad;
	}

	public void buildRailRoad(boolean hasRailRoad) {
		this.hasRailRoad = hasRailRoad;
	}

	public boolean doesHaveRiver() {
		return hasRiver;
	}

	public boolean isDistroyed() {
		return isDistroyed;
	}

	public void setDistroyed(boolean distroyed) {
		isDistroyed = distroyed;
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

	public boolean getHasRiver() {
		return this.hasRiver;
	}

	/**
	 * 
	 * @param hasRiver
	 */
	public void setHasRiver(boolean hasRiver) {
		this.hasRiver = hasRiver;
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

}