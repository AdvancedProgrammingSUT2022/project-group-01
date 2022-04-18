package model.civilization;

import model.civilization.city.City;
import model.technology.TechTree;
import model.unit.Unit;

import java.util.*;

public class Civilization {

	private Civilizations civilization;//enum
	private Vector<City> cities;
	private City capital;
	private Currency currency;
	private int Happiness;
	double science;
	private Vector<Unit> units;//TODO merge with safar

	private TechTree technology;//TODO merge with safar
	private Vector<Civilization> knownCivilizations;

	public TechTree getResearchTree() {
		// TODO - implement model.civilization.Civilization.getResearchList
		throw new UnsupportedOperationException();
	}

	public void newResearch() {
		// TODO - implement model.civilization.Civilization.newResearch
		throw new UnsupportedOperationException();
	}

	public Currency getCurrency() {
		return this.currency;
	}


	public void addNewCity() {//TODO add arguments
		// TODO - implement model.civilization.Civilization.addNewCity
		throw new UnsupportedOperationException();
	}

	private void updateCurrency() {//TODO for each turn
		// TODO - implement model.civilization.Civilization.updateCurrency
		throw new UnsupportedOperationException();
	}

	public void nextTurn() {
		// TODO - implement model.civilization.Civilization.nextTurn
		throw new UnsupportedOperationException();
	}

	public void increaseHappiness(int amount){
		// TODO - implement model.civilization.Civilization.increaseHappiness
	}

	public void decreaseHappiness(int amount){
		// TODO - implement model.civilization.Civilization.decreaseHappiness
	}

	public int getHappiness(){
		throw new UnsupportedOperationException();
	}

	public double calculateScience(){
		throw new UnsupportedOperationException();
	}

	public Vector<Civilization> getKnownCivilizations() {
		return knownCivilizations;
	}

	public void addKnownCivilization(Civilization civilization){}

	public double getScience() {
		return science;
	}

	public void setScience(double science) {
		this.science = science;
	}

	public Vector<Unit> getUnits() {
		return units;
	}

	public Vector<City> getCities(){
		return cities;
	}
}