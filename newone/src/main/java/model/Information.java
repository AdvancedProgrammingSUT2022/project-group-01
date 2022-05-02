package model;

import model.civilization.Civilization;
import model.civilization.Trade;
import model.civilization.city.City;
import model.resource.ResourceType;
import model.technology.TechTree;
import model.unit.Unit;

import java.util.HashMap;
import java.util.Vector;

public class Information {
	private Civilization civilization;
	private int civilizationSize;
	private HashMap<ResourceType, Integer> resources;

	public Information(Civilization civilization){
		this.civilization = civilization;
	}

	public void updateCivilization(Civilization civilization){

	}
	public void updateInformation(){}
	public HashMap<ResourceType, Integer> getResources(){return null; };
	public TechTree getTechTree(){
		//TODO: Implement here
		return null;
	}
	public Vector<City> getCities(){ return null; }
	public int getScore(){return 0;}

	public Vector<Civilization> knownCivilizations(){ return null; }

	public void demographics(){}

	public Vector<Notification> getNotifications(){
		return null;
		//TODO: implement here
	}

	public Vector<Unit> getUnits(){
		return null;
	}

	public void economicsStatus(){}

	public int getHappiness() {
		//return happiness;TODO
		return 0;
	}

	public int getPopulation() {
		//return population;TODO
		return 0;
	}

	public Vector<Trade> getTrades(){
		return null;
		//TODO: implemnet here
	}

}