package resource;
import technology.TechnologyType;
import building.*;
import tile.*;
import improvement.*;
import java.util.Vector;

public class Resource {

	private final KindsOfResource mainKind;
	private final ResourceType type;

	/**
	 * 
	 * @param type
	 */
	public Resource(ResourceType type) {
		// TODO - implement Resource.Resource
		throw new UnsupportedOperationException();
	}

	public int getGold() {
		// TODO - implement Resource.getGold
		throw new UnsupportedOperationException();
	}

	public KindsOfResource getMainKind() {
		return mainKind;
	}


	public ResourceType getType() {
		return type;
	}


	public int getProduction() {
		// TODO - implement Resource.getProduction
		throw new UnsupportedOperationException();
	}

	public int getFood() {
		// TODO - implement Resource.getFood
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param terrain
	 */
	public boolean isPossibleInLand(Terrain terrain) {
		// TODO - implement Resource.isPossibleInLand
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param feature
	 */
	public boolean isPossibleInFeatureLand(TerrainFeature feature) {
		// TODO - implement Resource.isPossibleInFeatureLand
		throw new UnsupportedOperationException();
	}

	public Vector<BuildingType> getNecessaryBuilding() {
		// TODO - implement Resource.getNecessaryBuilding
		throw new UnsupportedOperationException();
	}

	public Vector<Unit> getNecessaryUnits() {
		// TODO - implement Resource.getNecessaryUnits
		throw new UnsupportedOperationException();
	}

	public boolean isTradable() {
		// TODO - implement Resource.isTradable
		throw new UnsupportedOperationException();
	}

	public TechnologyType necessaryTechnology() {
		// TODO - implement Resource.necessaryTechnology
		throw new UnsupportedOperationException();
	}

}