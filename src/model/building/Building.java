package model.building;
import model.Player;
import model.civilization.Job;
import model.civilization.Person;
import model.civilization.city.City;
import model.civilization.production.Production;
import model.tile.Tile;

import java.util.Vector;

public class Building extends Production {

	private BuildingType type;
	private Tile tile;
	private final Vector<Person> specialists;

	public Building(BuildingType type, Tile tile){
		this.type = type;
		specialists = new Vector<Person>();

		this.tile = tile;
	}
	public BuildingType getType() {
		return this.type;
	}

	public Job getSpecialistTitle(){
		return type.specialist;
	}
	public boolean hasEmptySlot(){
		if(type.specialistSlots == 0)
			return false;
		return specialists.size() != type.specialistSlots;
	}
	public void assignSpecialist(Person person){
		specialists.add(person);
	}
	public Vector<Person> getSpecialists(){
		return specialists;
	}
	public void removeSpecialist(Person person){
		specialists.remove(person);
	}
	public boolean isPersonASpecialist(Person person){
		return specialists.contains(person);
	}
	/**
	 * 
	 * @param type
	 */
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
	 * 
	 * @param player
	 * @param city
	 */
	public void doEffect(Player player, City city) {
		type.effect(player, city);
	}



}