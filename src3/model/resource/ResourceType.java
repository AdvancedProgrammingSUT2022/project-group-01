package model.resource;
import model.technology.TechnologyType;
import model.building.*;
import model.tile.*;
import model.improvement.*;
import java.util.Vector;

public enum ResourceType {
	//Bonus Resources
	BANANAS(1,0,0,new Vector<Terrain>(), new Vector<TerrainFeature>(){{
		add(TerrainFeature.JUNGLE);
	}},ImprovementType.PLANTATION,null,false,KindsOfResource.BONUS),
	CATTLE(1,0,0,new Vector<Terrain>(),new Vector<TerrainFeature>(){{
		add(TerrainFeature.JUNGLE);
	}}, ImprovementType.PASTURE,null,false,KindsOfResource.BONUS),
	DEER(1,0,0,new Vector<Terrain>(){{
		add(Terrain.TUNDRA);
		add(Terrain.HILLS);
	}},new Vector<TerrainFeature>(){{
		add(TerrainFeature.FOREST);
	}}, ImprovementType.CAMP,null,false,KindsOfResource.BONUS),
	SHEEP(1,0,0,new Vector<Terrain>(){{
		add(Terrain.GRASSLAND);
		add(Terrain.PLAINS);
		add(Terrain.DESERT);
		add(Terrain.HILLS);
	}},new Vector<TerrainFeature>(), ImprovementType.PASTURE,null,false,KindsOfResource.BONUS),
	WHEAT(1,0,0,new Vector<Terrain>(){{
		add(Terrain.PLAINS);
	}},new Vector<TerrainFeature>(){{
		add(TerrainFeature.FLOOD_PLAINS);
	}}, ImprovementType.FARM,null,false,KindsOfResource.BONUS),
	COAL(0,1,0,new Vector<Terrain>(){{
		add(Terrain.GRASSLAND);
		add(Terrain.PLAINS);
		add(Terrain.HILLS);
	}},new Vector<TerrainFeature>(), ImprovementType.MINE,TechnologyType.SCIENTIFIC_THEORY,true,KindsOfResource.STRATEGIC),
	HORSES(0,1,0,new Vector<Terrain>(){{
		add(Terrain.GRASSLAND);
		add(Terrain.PLAINS);
		add(Terrain.TUNDRA);
	}},new Vector<TerrainFeature>(), ImprovementType.PASTURE,TechnologyType.ANIMAL_HUSBANDRY,true,KindsOfResource.STRATEGIC),
	IRON(0,1,0,new Vector<Terrain>(){{
		add(Terrain.GRASSLAND);
		add(Terrain.PLAINS);
		add(Terrain.DESERT);
		add(Terrain.TUNDRA);
		add(Terrain.SNOW);
		add(Terrain.HILLS);
	}},new Vector<TerrainFeature>(), ImprovementType.MINE,TechnologyType.IRON_WORKING,true,KindsOfResource.STRATEGIC),
	COTTON(0,0,2,new Vector<Terrain>(){{
		add(Terrain.GRASSLAND);
		add(Terrain.PLAINS);
		add(Terrain.DESERT);
	}},new Vector<TerrainFeature>(), ImprovementType.PLANTATION,null,false,KindsOfResource.LUXURY),
	DYES(0,0,2,new Vector<Terrain>(),new Vector<TerrainFeature>(){{
		add(TerrainFeature.JUNGLE);
		add(TerrainFeature.FOREST);
	}}, ImprovementType.PLANTATION,null,false,KindsOfResource.LUXURY),
	FURS(0,0,2,new Vector<Terrain>(){{
		add(Terrain.TUNDRA);
	}},new Vector<TerrainFeature>(){{
		add(TerrainFeature.FOREST);
	}}, ImprovementType.CAMP,null,false,KindsOfResource.LUXURY),
	GEMS(0,0,3,new Vector<Terrain>(){{
		add(Terrain.GRASSLAND);
		add(Terrain.PLAINS);
		add(Terrain.DESERT);
		add(Terrain.TUNDRA);
		add(Terrain.HILLS);
	}},new Vector<TerrainFeature>(){{
		add(TerrainFeature.JUNGLE);
	}}, ImprovementType.MINE,null,false,KindsOfResource.LUXURY),
	GOLD(0,0,2,new Vector<Terrain>(){{
		add(Terrain.GRASSLAND);
		add(Terrain.PLAINS);
		add(Terrain.DESERT);
		add(Terrain.HILLS);
	}},new Vector<TerrainFeature>(), ImprovementType.MINE,null,false,KindsOfResource.LUXURY),
	INCENSE(0,0,2,new Vector<Terrain>(){{
		add(Terrain.PLAINS);
		add(Terrain.DESERT);
	}},new Vector<TerrainFeature>(), ImprovementType.PLANTATION,null,false,KindsOfResource.LUXURY),
	IVORY(0,0,2,new Vector<Terrain>(){{
		add(Terrain.PLAINS);
	}},new Vector<TerrainFeature>(), ImprovementType.CAMP,null,false,KindsOfResource.LUXURY),
	MARBLE(0,0,2,new Vector<Terrain>(){{
		add(Terrain.GRASSLAND);
		add(Terrain.PLAINS);
		add(Terrain.DESERT);
		add(Terrain.TUNDRA);
		add(Terrain.HILLS);
	}},new Vector<TerrainFeature>(), ImprovementType.QUARRY,null,false,KindsOfResource.LUXURY),
	SILK(0,0,2,new Vector<Terrain>(),new Vector<TerrainFeature>(){{
		add(TerrainFeature.FOREST);
	}}, ImprovementType.PLANTATION,null,false,KindsOfResource.LUXURY),
	SILVER(0,0,2,new Vector<Terrain>(){{
		add(Terrain.DESERT);
		add(Terrain.TUNDRA);
		add(Terrain.HILLS);
	}},new Vector<TerrainFeature>(), ImprovementType.MINE,null,false,KindsOfResource.LUXURY),
	SUGAR(0,0,2,new Vector<Terrain>(),new Vector<TerrainFeature>(){{
		add(TerrainFeature.FLOOD_PLAINS);
		add(TerrainFeature.MARSH);
	}}, ImprovementType.PLANTATION,null,false,KindsOfResource.LUXURY);

	public final int food;
	public final int production;
	public final int gold;
	public final Vector<Terrain> possibleTerrains;
	public final Vector<TerrainFeature> possibleLandFeatures;
	public final ImprovementType necessaryImprovement;
	public final TechnologyType visibilityTechnology;
	public final boolean tradable;
	public final KindsOfResource resourceKind;

	ResourceType(int food, int production, int gold, Vector<Terrain> possibleTerrains, Vector<TerrainFeature> possibleLandFeatures, ImprovementType necessaryImprovement, TechnologyType visibilityTechnology, boolean tradable, KindsOfResource resourceKind) {
		this.food = food;
		this.production = production;
		this.gold = gold;
		this.possibleTerrains = possibleTerrains;
		this.possibleLandFeatures = possibleLandFeatures;
		this.necessaryImprovement = necessaryImprovement;
		this.visibilityTechnology = visibilityTechnology;
		this.tradable = tradable;
		this.resourceKind = resourceKind;
	}
}