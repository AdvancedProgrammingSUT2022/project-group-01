package model.building;

import model.Player;
import model.civilization.Job;
import model.civilization.Person;
import model.civilization.city.City;
import model.tile.Tile;

import java.util.Vector;

public class Building {

	private BuildingType type;
	private Tile tile;
	private final Vector<Person> specialists;
	private boolean isDamaged;

	public Building(BuildingType type, Tile tile){
		this.type = type;
		specialists = new Vector<Person>();
		isDamaged = false;
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
		// TODO - implement Building.getCost
		throw new UnsupportedOperationException();
	}

	public int getMaintenance() {
		// TODO - implement Building.getMaintenance
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param player
	 * @param city
	 */
	public void doEffect(Player player, City city) {
		type.effect(player, city);
	}

	public void damage(){
		isDamaged = true;
	}
	public boolean getIsDamaged(){
		return isDamaged;
	}


}