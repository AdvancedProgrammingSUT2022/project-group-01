package model.tile;
import java.util.List;
import java.util.Vector;

import model.resource.ResourceType;
import model.resource.*;
import model.building.*;
import model.improvement.*;
public enum TerrainFeature {
	FLOOD_PLAINS(2,0,0,-33,1,true){
		@Override
		public void initializeVectors(){
			this.possibleResources = new Vector<>(List.of(ResourceType.WHEAT, ResourceType.SUGAR));
		}
	},
	FOREST(1,1,0,25,2,true){
		@Override
		public void initializeVectors(){
			this.possibleResources = new Vector<>(List.of(ResourceType.DEER,ResourceType.FURS,ResourceType.DYES,ResourceType.SILK));
		}
	},
	ICE(0,0,0,0,999,false),
	JUNGLE(1,-1,0,25,2,true){
		@Override
		public void initializeVectors(){
			this.possibleResources = new Vector<>(List.of(ResourceType.BANANAS,ResourceType.GEMS,ResourceType.DYES));
		}
	},
	MARSH(-1,0,0,-33,2,true){
		@Override
		public void initializeVectors(){
			this.possibleResources = new Vector<>(List.of(ResourceType.SUGAR));
		}
	},
	OASIS(3,0,1,-33,1,true);

	public final int combatModifiers;
	public final boolean passable;
	public final int movementCost;
	public final int food;
	public final int gold;
	public final int production;
	public Vector<ResourceType> possibleResources;


	TerrainFeature(int food, int production, int gold, int combatModifier, int movementCost,boolean passable) {

		this.combatModifiers = combatModifier;
		this.passable = passable;
		this.movementCost = movementCost;
		this.food = food;
		this.gold = gold;
		this.production = production;
	}

	public void initializeVectors(){
		this.possibleResources = new Vector<>();
	}
}