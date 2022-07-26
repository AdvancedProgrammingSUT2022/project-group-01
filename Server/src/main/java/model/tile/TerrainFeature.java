package model.tile;

import lombok.Getter;
import model.resource.ResourceType;

import java.util.List;
import java.util.Vector;

@Getter
public enum TerrainFeature {
	FLOOD_PLAINS(2, 0, 0, -33, 1, true, -1) {
		@Override
		public void initializeVectors() {
			this.possibleResources = new Vector<>(List.of(ResourceType.WHEAT, ResourceType.SUGAR));
		}
	},
	FOREST(1, 1, 0, 25, 2, true, 3) {
		@Override
		public void initializeVectors() {
			this.possibleResources = new Vector<>(List.of(ResourceType.DEER, ResourceType.FURS, ResourceType.DYES, ResourceType.SILK));
		}
	},
	ICE(0, 0, 0, 0, 999, false, -1),
	JUNGLE(1, -1, 0, 25, 2, true, 6) {
		@Override
		public void initializeVectors() {
			this.possibleResources = new Vector<>(List.of(ResourceType.BANANAS, ResourceType.GEMS, ResourceType.DYES));
		}
	},
	MARSH(-1, 0, 0, -33, 2, true, 5) {
		@Override
		public void initializeVectors() {
			this.possibleResources = new Vector<>(List.of(ResourceType.SUGAR));
		}
	},
	OASIS(3, 0, 1, -33, 1, true, -1);

	public final int combatModifiers;
	public final boolean passable;
	public final int movementCost;
	public final int food;
	public final int gold;
	public final int production;
	public Vector<ResourceType> possibleResources;
	public final int removeTime;

	TerrainFeature(int food, int production, int gold, int combatModifier, int movementCost, boolean passable, int removeTime) {

		this.combatModifiers = combatModifier;
		this.passable = passable;
		this.movementCost = movementCost;
		this.food = food;
		this.gold = gold;
		this.production = production;
		this.removeTime = removeTime;
	}

	public void initializeVectors() {
		this.possibleResources = new Vector<>();
	}

	public boolean isRough() {
		return this.equals(JUNGLE) || this.equals(FOREST);
	}
}