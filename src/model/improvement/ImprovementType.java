package improvement;
import technology.TechnologyType;
import resource.*;
import tile.*;
import java.util.Vector;

public enum ImprovementType {
	CAMP(0,0,0,TechnologyType.TRAPPING,new Vector<ResourceType>(){{
		add(ResourceType.IVORY);
		add(ResourceType.FURS);
		add(ResourceType.DEER);
	}},new Vector<Terrain>(){{
		add(Terrain.TUNDRA);
		add(Terrain.PLAINS);
		add(Terrain.HILLS);
	}}, new Vector<TerrainFeature>(){{
		add(TerrainFeature.FOREST);
	}}),
	FARM(1,0,0,TechnologyType.AGRICULTURE,new Vector<ResourceType>(){{
		add(ResourceType.WHEAT);
	}}, new Vector<Terrain>(){{
		add(Terrain.GRASSLAND);
		add(Terrain.PLAINS);
		add(Terrain.DESERT);
	}},new Vector<>()),
	LUMBER_MILL(0,0,1,TechnologyType.ENGINEERING,new Vector<ResourceType>(), new Vector<Terrain>(), new Vector<TerrainFeature>(){{
		add(TerrainFeature.FOREST);
	}}),
	MINE(0,0,1,TechnologyType.MINING,new Vector<ResourceType>(){{
		add(ResourceType.IRON);
		add(ResourceType.COAL);
		add(ResourceType.GEMS);
		add(ResourceType.GOLD);
		add(ResourceType.SILVER);
	}}, new Vector<Terrain>(){{
		add(Terrain.GRASSLAND);
		add(Terrain.PLAINS);
		add(Terrain.DESERT);
		add(Terrain.TUNDRA);
		add(Terrain.SNOW);
		add(Terrain.HILLS);
	}}, new Vector<TerrainFeature>(){{
		add(TerrainFeature.JUNGLE);
	}}),
	PASTURE(0,0,0,TechnologyType.ANIMAL_HUSBANDRY,new Vector<ResourceType>(){{
		add(ResourceType.HORSES);
		add(ResourceType.CATTLE);
		add(ResourceType.SHEEP);
	}}, new Vector<Terrain>(){{
		add(Terrain.GRASSLAND);
		add(Terrain.PLAINS);
		add(Terrain.DESERT);
		add(Terrain.TUNDRA);
		add(Terrain.HILLS);
	}}, new Vector<TerrainFeature>()),
	PLANTATION(0,0,0,TechnologyType.CALENDAR,new Vector<ResourceType>(){{
		add(ResourceType.BANANAS);
		add(ResourceType.DYES);
		add(ResourceType.SILK);
		add(ResourceType.SUGAR);
		add(ResourceType.COTTON);
		add(ResourceType.INCENSE);
	}}, new Vector<Terrain>(){{
		add(Terrain.GRASSLAND);
		add(Terrain.PLAINS);
		add(Terrain.DESERT);
	}}, new Vector<TerrainFeature>(){{
		add(TerrainFeature.FOREST);
		add(TerrainFeature.MARSH);
		add(TerrainFeature.FLOOD_PLAINS);
		add(TerrainFeature.JUNGLE);
	}}),
	QUARRY(0,0,0,TechnologyType.MASONRY,new Vector<ResourceType>(){{
		add(ResourceType.MARBLE);
	}}, new Vector<Terrain>(){{
		add(Terrain.GRASSLAND);
		add(Terrain.PLAINS);
		add(Terrain.DESERT);
		add(Terrain.TUNDRA);
		add(Terrain.HILLS);
	}}, new Vector<TerrainFeature>()),
	TRADING_POST(0,2,0,TechnologyType.TRAPPING,new Vector<ResourceType>(), new Vector<Terrain>(){{
		add(Terrain.GRASSLAND);
		add(Terrain.PLAINS);
		add(Terrain.DESERT);
		add(Terrain.TUNDRA);
	}}, new Vector<>()),
	MANUFACTORY(0,0,3,null,new Vector<ResourceType>(), new Vector<Terrain>(){{
		add(Terrain.GRASSLAND);
		add(Terrain.PLAINS);
		add(Terrain.DESERT);
		add(Terrain.TUNDRA);
		add(Terrain.SNOW);
	}}, new Vector<>());

	public final int foodYield;
	public final int goldYield;
	public final int productionYield;
	public final TechnologyType preRequisiteTech;
	public final Vector<ResourceType> affectingResources;
	public final Vector<Terrain> canBuiltOnTerrains;
	public final Vector<TerrainFeature> canBuiltOnTerrainFeatures;

	ImprovementType(int foodYield, int goldYield, int productionYield, TechnologyType preRequisiteTech, Vector<ResourceType> affectingResources, Vector<Terrain> canBuiltOnTerrains, Vector<TerrainFeature> canBuiltOnTerrainFeatures) {
		this.foodYield = foodYield;
		this.goldYield = goldYield;
		this.productionYield = productionYield;
		this.preRequisiteTech = preRequisiteTech;
		this.affectingResources = affectingResources;
		this.canBuiltOnTerrains = canBuiltOnTerrains;
		this.canBuiltOnTerrainFeatures = canBuiltOnTerrainFeatures;
	}
}