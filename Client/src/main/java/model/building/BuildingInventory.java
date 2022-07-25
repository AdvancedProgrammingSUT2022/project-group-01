package model.building;
import lombok.Getter;
import lombok.Setter;

import java.util.Vector;


@Getter @Setter
public class BuildingInventory {

	private Vector<BuildingType> accessedBuilding;
	private Vector<BuildingType> ownedBuildings;

	public BuildingInventory() {
		// TODO - implement BuildingInventory.BuildingInventory
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param building
	 */
	public void ownBuilding(BuildingType building) {
		ownedBuildings.add(building);
	}

	/**
	 * 
	 * @param building
	 */
	public boolean hasBuilding(BuildingType building) {
		return ownedBuildings.contains(building);
	}

	/**
	 * 
	 * @param building
	 */
	public void accessBuilding(BuildingType building) {
		accessedBuilding.add(building);
	}

	/**
	 * 
	 * @param building
	 */
	public boolean hasAccessToBuilding(BuildingType building) {
		return accessedBuilding.contains(building);
	}

	public int getBeaker(){
		return 0;
	}

}