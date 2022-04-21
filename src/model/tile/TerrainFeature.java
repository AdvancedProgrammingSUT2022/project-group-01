package model.tile;
import java.util.Vector;

import model.resource.ResourceType;
import model.resource.*;
import model.building.*;
import model.improvement.*;
public enum TerrainFeature {
	FLOOD_PLAINS(2,0,0,-33,1,true, new Vector<>() {{
		add(ResourceType.WHEAT);
		add(ResourceType.SUGAR);
	}}),
	FOREST(1,1,0,25,2,true, new Vector<>() {{
		add(ResourceType.DEER);
		add(ResourceType.FURS);
		add(ResourceType.DYES);
		add(ResourceType.SILK);
	}}),
	ICE(0,0,0,0,999,false, new Vector<>() {
	}),
	JUNGLE(1,-1,0,25,2,true, new Vector<>() {{
		add(ResourceType.BANANAS);
		add(ResourceType.GEMS);
		add(ResourceType.DYES);
	}}),
	MARSH(-1,0,0,-33,2,true, new Vector<>() {{
		add(ResourceType.SUGAR);
	}}),
	OASIS(3,0,1,-33,1,true, new Vector<>()),
	RIVER(0,0,1,0,Integer.MAX_VALUE,true,new Vector<>());

	public final int combatModifiers;
	public final boolean passable;
	public final int movementCost;
	public final int food;
	public final int gold;
	public final int production;
	public final Vector<ResourceType> possibleResources;


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