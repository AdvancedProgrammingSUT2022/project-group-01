package model.resource;
import model.technology.TechnologyType;
import model.building.*;
import model.tile.*;
import model.unit.Unit;

import java.util.Vector;

public class Resource {

	private final KindsOfResource mainKind;
	private final ResourceType type;

	/**
	 * 
	 * @param type
	 */
	public Resource(ResourceType type) {
		this.type = type;
		this.mainKind = type.resourceKind;
	}

	public int getGold() {
		return this.type.gold;
	}

	public KindsOfResource getMainKind() {
		return mainKind;
	}


	public ResourceType getType() {
		return type;
	}


	public int getProduction() {
		return this.type.production;
	}

	public int getFood() {
		return this.type.food;
	}

	/**
	 * 
	 * @param terrain
	 */
	public boolean isPossibleInTerrain(Terrain terrain) {
		return terrain.possibleResources.contains(this.type);
	}

	/**
	 * 
	 * @param feature
	 */
	public boolean isPossibleInTerrainFeature(TerrainFeature feature) {
		return feature.possibleResources.contains(this.type);
	}

	public Vector<BuildingType> getAffectingBuildings() {
		Vector<BuildingType> affectingBuildings = new Vector<>();
		for(BuildingType building : BuildingType.values()){
			if(building.necessaryResources.contains(this.type))
				affectingBuildings.add(building);
		}
		return affectingBuildings;
	}

	public boolean isTradable() {
		return this.mainKind.equals(KindsOfResource.STRATEGIC);
	}

	public TechnologyType necessaryTechnology() {
		return this.type.visibilityTechnology;
	}

}