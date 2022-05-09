package model.civilization.production;

import model.civilization.Civilization;
import model.civilization.city.City;

import java.util.*;

public class ProductionInventory {

	City city;
	HashMap<Producible, Double> productions;
	Producible currentProduction;
	public ProductionInventory(City city) {
		this.city = city;
		currentProduction = null;
		initList();
	}

	private void initList(){
		for(Producible producible : Producible.productions){
			productions.put(producible, (double) producible.getCost(city));
		}
	}

	public void payProduction(double product) {
		if(currentProduction == null)
			return;
		productions.put(currentProduction, productions.get(currentProduction) - product);
		if(productions.get(currentProduction) <= 0) {
			currentProduction.produce(city);
			productions.put(currentProduction,(double) currentProduction.getCost(city));
			currentProduction = null;
		}
	}

	public Producible getCurrentProduction() {
		return currentProduction;
	}

	public void setCurrentProduction(Producible producible){
		this.currentProduction = producible;
	}

	public Vector<Producible> getAvailableProductions() {
		Vector<Producible> out = new Vector<>();
		for(Map.Entry<Producible, Double> set : productions.entrySet())
			if(set.getKey().isProducible(city))
				out.add(set.getKey());
		return out;
	}

	public void cancelCurrentProduction(){
		currentProduction = null;
	}

	public void purchaseProduction(Producible producible){
		city.payCurrency(producible.getCost(city),0,0);
		producible.produce(city);
	}

}