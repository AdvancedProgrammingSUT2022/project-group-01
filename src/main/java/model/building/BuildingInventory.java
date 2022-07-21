package model.building;
import lombok.Getter;
import lombok.Setter;
import model.civilization.city.City;

import java.util.Vector;


@Getter @Setter
public class BuildingInventory {

	private Vector<BuildingType> ownedBuildings;
	private City city;
	public BuildingInventory(City city) {
		this.ownedBuildings = new Vector<>();
		this.city = city;
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

}