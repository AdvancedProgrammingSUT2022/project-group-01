package model;

import model.civilization.Civilization;
import model.resource.ResourceType;
import model.technology.TechTree;

import java.util.HashMap;

public class Information {
	private Civilization civilization;
	private int civilizationSize;
	private HashMap<ResourceType, Integer> resources;
	private int population;
	private int happiness;
	private TechTree techTree;

	public Information(Civilization civilization){
		this.civilization = civilization;
	}

	public void updateInformation(){}
	public HashMap<ResourceType, Integer> getResources(){return null; };
	public TechTree getTechTree() {
		return techTree;
	}
}
