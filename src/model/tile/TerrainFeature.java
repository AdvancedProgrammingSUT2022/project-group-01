package tile;
import java.util.Vector;
import resource.*;
import building.*;
import improvement.*;
public enum TerrainFeature {
	FLOOD_PLAINS(2,0,0,-33,1,true,new Vector<ResourceType>(){{
		add(ResourceType.WHEAT);
		add(ResourceType.SUGAR);
	}}),
	FOREST(1,1,0,25,2,true,new Vector<ResourceType>(){{
		add(ResourceType.DEER);
		add(ResourceType.FURS);
		add(ResourceType.DYES);
		add(ResourceType.SILK);
	}}),
	ICE(0,0,0,0,999,false,new Vector<ResourceType>(){
	}),
	JUNGLE(1,-1,0,25,2,true,new Vector<ResourceType>(){{
		add(ResourceType.BANANAS);
		add(ResourceType.GEMS);
		add(ResourceType.DYES);
	}}),
	MARSH(-1,0,0,-33,2,true,new Vector<ResourceType>(){{
		add(ResourceType.SUGAR);
	}}),
	OASIS(3,0,1,-33,1,true,new Vector<ResourceType>());

	public final int combatModifiers;
	public final boolean passable;
	public final int movementCost;
	public final int food;
	public final int gold;
	public final int production;
	public final Vector<ResourceType> possibleResources;

	/**
	 * 
	 * @param food
	 * @param gold
	 * @param production
	 * @param combatModifier
	 * @param movementCost
	 * @param passable
	 */
//	TerrainFeature(int food, int production, int gold, int combatModifier, int movementCost,boolean passable, Vector<ResourceType> resources) {
//		// TODO - implement FeatureLand.FeatureLand
//		throw new UnsupportedOperationException();
//	}


	TerrainFeature(int food, int production, int gold, int combatModifier, int movementCost,boolean passable, Vector<ResourceType> resources) {

		this.combatModifiers = combatModifier;
		this.passable = passable;
		this.movementCost = movementCost;
		this.food = food;
		this.gold = gold;
		this.production = production;
		this.possibleResources = resources;
	}
}