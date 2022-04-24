package model.improvement;
import model.civilization.Civilization;
import model.civilization.city.City;
import model.resource.ResourceType;
import model.technology.TechnologyType;
import model.tile.Terrain;
import model.tile.TerrainFeature;
import model.tile.Tile;

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
	}},new Vector<>()){
		@Override
		public void improvementSpecialAction(Tile tile){
			if(tile.getFeature().equals(TerrainFeature.JUNGLE)
			|| tile.getFeature().equals(TerrainFeature.FOREST)
			|| tile.getFeature().equals(TerrainFeature.MARSH))
				tile.removeFeature();
		}
		@Override
		public int getProductionTime(Tile tile){
			if(tile.getFeature().equals(TerrainFeature.FOREST))
				return 10;
			if(tile.getFeature().equals(TerrainFeature.JUNGLE))
				return 13;
			if(tile.getFeature().equals(TerrainFeature.MARSH))
				return 12;
			return 6;
		}
	},
	LUMBER_MILL(0,0,1,TechnologyType.ENGINEERING,new Vector<ResourceType>(), new Vector<Terrain>(), new Vector<TerrainFeature>(){{
		add(TerrainFeature.FOREST);
	}}){
		@Override
		public int getProductionTime(Tile tile){
			return 6;
		}
	},
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
	}}){
		@Override
		public void improvementSpecialAction(Tile tile){
			if(tile.getFeature().equals(TerrainFeature.JUNGLE)
					|| tile.getFeature().equals(TerrainFeature.FOREST)
					|| tile.getFeature().equals(TerrainFeature.MARSH))
				tile.removeFeature();
		}
		@Override
		public int getProductionTime(Tile tile){
			if(tile.getFeature().equals(TerrainFeature.FOREST))
				return 10;
			if(tile.getFeature().equals(TerrainFeature.JUNGLE))
				return 13;
			if(tile.getFeature().equals(TerrainFeature.MARSH))
				return 12;
			return 6;
		}
	},
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
	}}, new Vector<TerrainFeature>()){
		@Override
		public int getProductionTime(Tile tile) {
			return 7;
		}
	},
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
	}}){
		@Override
		public int getProductionTime(Tile tile) {
			return 5;
		}
	},
	QUARRY(0,0,0,TechnologyType.MASONRY,new Vector<ResourceType>(){{
		add(ResourceType.MARBLE);
	}}, new Vector<Terrain>(){{
		add(Terrain.GRASSLAND);
		add(Terrain.PLAINS);
		add(Terrain.DESERT);
		add(Terrain.TUNDRA);
		add(Terrain.HILLS);
	}}, new Vector<TerrainFeature>()){
		@Override
		public int getProductionTime(Tile tile) {
			return 7;
		}
	},
	TRADING_POST(0,2,0,TechnologyType.TRAPPING,new Vector<ResourceType>(), new Vector<Terrain>(){{
		add(Terrain.GRASSLAND);
		add(Terrain.PLAINS);
		add(Terrain.DESERT);
		add(Terrain.TUNDRA);
	}}, new Vector<>()){
		@Override
		public int getProductionTime(Tile tile) {
			return 8;
		}
	},
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

	public int getProductionTime(Tile tile){
		return 0;
	}
	public boolean isEligibleToBuild(Civilization civilization, Tile tile){
		if(!(this.canBuiltOnTerrains.contains(tile.getTerrain())
				|| this.canBuiltOnTerrainFeatures.contains(tile.getFeature())))
			return false;
		if(!civilization.getResearchTree().isResearched(this.preRequisiteTech))
			return false;
		return true;
	}
	public void improvementSpecialAction(Tile tile){
	}
}