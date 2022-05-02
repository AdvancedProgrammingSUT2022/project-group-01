package model.tile;
import java.util.List;
import java.util.Vector;

import model.resource.ResourceType;
import model.resource.*;
public enum Terrain {
	DESERT(0,0,0,0,1,true,false,true){
		@Override
		public void initializeVectors(){
			this.possibleFeatures = new Vector<TerrainFeature>(List.of(TerrainFeature.OASIS, TerrainFeature.FLOOD_PLAINS));
			this.possibleResources = new Vector<>(List.of(ResourceType.IRON,ResourceType.GOLD,ResourceType.SILVER,ResourceType.GEMS,ResourceType.MARBLE,ResourceType.COTTON,ResourceType.INCENSE,ResourceType.SHEEP));
		}
	},
	GRASSLAND(2,0,0,-33,1,true,false,true){
		@Override
		public void initializeVectors(){
			this.possibleFeatures = new Vector<>(List.of(TerrainFeature.FOREST, TerrainFeature.MARSH));
			this.possibleResources = new Vector<>(List.of(ResourceType.IRON,ResourceType.HORSES,ResourceType.COAL,ResourceType.CATTLE,ResourceType.GOLD,ResourceType.GEMS,ResourceType.MARBLE,ResourceType.COTTON,ResourceType.SHEEP));
		}
	},
	HILLS(0,2,0,25,2,true,true,true){
		@Override
		public void initializeVectors(){
			this.possibleFeatures = new Vector<>(List.of(TerrainFeature.FOREST, TerrainFeature.JUNGLE));
			this.possibleResources = new Vector<>(List.of(ResourceType.IRON,ResourceType.COAL,ResourceType.DEER,ResourceType.GOLD,ResourceType.SILVER,ResourceType.GEMS,ResourceType.MARBLE,ResourceType.SHEEP));
		}
	},
	MOUNTAIN(0,0,0,25,9999,false,true,false),
	OCEAN(1,0,1,0,1,true,false,false){
		@Override
		public void initializeVectors(){
			this.possibleFeatures = new Vector<>(List.of(TerrainFeature.ICE));
			this.possibleResources = new Vector<>();
		}
	},
	PLAINS(1,1,0,-33,1,true,false,true){
		@Override
		public void initializeVectors(){
			this.possibleFeatures = new Vector<>(List.of(TerrainFeature.FOREST, TerrainFeature.JUNGLE));
			this.possibleResources = new Vector<>(List.of(ResourceType.IRON,ResourceType.HORSES,ResourceType.COAL,ResourceType.WHEAT,ResourceType.GOLD,ResourceType.GEMS,ResourceType.MARBLE,ResourceType.IVORY,ResourceType.COTTON,ResourceType.INCENSE,ResourceType.SHEEP));
		}
	},
	SNOW(0,0,0,-33,1,true,false,true){
		@Override
		public void initializeVectors(){
			this.possibleResources = new Vector<>(List.of(ResourceType.IRON));
			this.possibleFeatures = new Vector<>();
		}
	},
	TUNDRA(1,0,0,-33,1,true,false,true){
		@Override
		public void initializeVectors(){
			this.possibleFeatures = new Vector<>(List.of(TerrainFeature.FOREST));
			this.possibleResources = new Vector<>(List.of(ResourceType.IRON,ResourceType.HORSES,ResourceType.DEER,ResourceType.SILVER,ResourceType.GEMS,ResourceType.MARBLE,ResourceType.FURS));
		}
	};
	//TODO make public -> private
	public final int gold;
	public final int combatModifier;
	public final int movementCost;
	public final boolean canContainCity;
	public final boolean passable;
	public final boolean isHighland;
	public Vector<ResourceType> possibleResources;
	public final int production;
	public final int food;
	public Vector<TerrainFeature> possibleFeatures;

	Terrain(int food, int gold, int production,  int combatModifier, int movementCost, boolean passable, boolean isHighland,boolean canContainCity) {
		this.gold = gold;
		this.combatModifier = combatModifier;
		this.movementCost = movementCost;
		this.canContainCity = canContainCity;
		this.passable = passable;
		this.isHighland = isHighland;
		this.production = production;
		this.food = food;
	}
	public void initializeVectors(){
		this.possibleResources = new Vector<>();
		this.possibleFeatures = new Vector<>();
	}
}