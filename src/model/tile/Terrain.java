package model.tile;
import java.util.Vector;

import model.resource.ResourceType;
import model.resource.*;
public enum Terrain {
	DESERT(0,0,0,0,1,true,false,true, new Vector<>() {{
		add(ResourceType.IRON);
		add(ResourceType.GOLD);
		add(ResourceType.SILVER);
		add(ResourceType.GEMS);
		add(ResourceType.MARBLE);
		add(ResourceType.COTTON);
		add(ResourceType.INCENSE);
		add(ResourceType.SHEEP);
	}}, new Vector<>() {{
		add(TerrainFeature.OASIS);
		add(TerrainFeature.FLOOD_PLAINS);
	}}),
	GRASSLAND(2,0,0,-33,1,true,false,true, new Vector<>() {{
		add(ResourceType.IRON);
		add(ResourceType.HORSES);
		add(ResourceType.COAL);
		add(ResourceType.CATTLE);
		add(ResourceType.GOLD);
		add(ResourceType.GEMS);
		add(ResourceType.MARBLE);
		add(ResourceType.COTTON);
		add(ResourceType.SHEEP);
	}}, new Vector<>() {{
		add(TerrainFeature.FOREST);
		add(TerrainFeature.MARSH);
	}}),
	HILLS(0,2,0,25,2,true,true,true, new Vector<>() {{
		add(ResourceType.IRON);
		add(ResourceType.COAL);
		add(ResourceType.DEER);
		add(ResourceType.GOLD);
		add(ResourceType.SILVER);
		add(ResourceType.GEMS);
		add(ResourceType.MARBLE);
		add(ResourceType.SHEEP);
	}}, new Vector<>() {{
		add(TerrainFeature.FOREST);
		add(TerrainFeature.JUNGLE);
	}}),
	MOUNTAIN(0,0,0,25,9999,false,true,false, new Vector<>(), new Vector<>()),
	OCEAN(1,0,1,0,1,true,false,false, new Vector<>(),new Vector<>(){{
		add(TerrainFeature.ICE);
	}}),
	PLAINS(1,1,0,-33,1,true,false,true, new Vector<>() {{
		add(ResourceType.IRON);
		add(ResourceType.HORSES);
		add(ResourceType.COAL);
		add(ResourceType.WHEAT);
		add(ResourceType.GOLD);
		add(ResourceType.GEMS);
		add(ResourceType.MARBLE);
		add(ResourceType.IVORY);
		add(ResourceType.COTTON);
		add(ResourceType.INCENSE);
		add(ResourceType.SHEEP);
	}},new Vector<>(){{
		add(TerrainFeature.FOREST);
		add(TerrainFeature.JUNGLE);
	}}),
	SNOW(0,0,0,-33,1,true,false,true, new Vector<>() {{
		add(ResourceType.IRON);
	}}, new Vector<>()),
	TUNDRA(1,0,0,-33,1,true,false,true, new Vector<>() {{
		add(ResourceType.IRON);
		add(ResourceType.HORSES);
		add(ResourceType.DEER);
		add(ResourceType.SILVER);
		add(ResourceType.GEMS);
		add(ResourceType.MARBLE);
		add(ResourceType.FURS);
	}}, new Vector<>(){{
		add(TerrainFeature.FOREST);
	}});

	public final int gold;
	public final int combatModifier;
	public final int movementCost;
	public final boolean canContainCity;
	public final boolean passable;
	public final boolean isHighland;
	public final Vector<ResourceType> possibleResources;
	public final int production;
	public final int food;
	public final Vector<TerrainFeature> possibleFeatures;

	Terrain(int food, int gold, int production,  int combatModifier, int movementCost, boolean passable, boolean isHighland,boolean canContainCity, Vector<ResourceType> possibleResources, Vector<TerrainFeature> possibleFeatures) {
		this.gold = gold;
		this.combatModifier = combatModifier;
		this.movementCost = movementCost;
		this.canContainCity = canContainCity;
		this.passable = passable;
		this.isHighland = isHighland;
		this.possibleResources = possibleResources;
		this.production = production;
		this.food = food;
		this.possibleFeatures = possibleFeatures;
	}
}