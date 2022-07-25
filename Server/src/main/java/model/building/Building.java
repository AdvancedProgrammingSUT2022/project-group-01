package model.building;

import model.Player;
import model.civilization.city.City;
import model.tile.Tile;

public class Building {

	private BuildingType type;
	private Tile tile;


	public Building(BuildingType type, Tile tile) {
		this.type = type;

		this.tile = tile;
	}

	public BuildingType getType() {
		return this.type;
	}

	public void setType(BuildingType type) {
		this.type = type;
	}

	public int getCost() {
		return this.type.cost;
	}

	public int getMaintenance() {
		return this.type.maintenance;
	}

	/**
	 * @param player
	 * @param city
	 */
	public void doEffect(Player player, City city) {
		type.effect(player, city);
	}


}