package model.civilization.production;

import model.Notification;
import model.civilization.Civilization;
import model.civilization.Currency;
import model.civilization.city.City;

import java.util.*;

public class ProductionInventory {

	City city;
	HashMap<Producible, Double> productions = new HashMap<>();
	Producible currentProduction;
	public ProductionInventory(City city) {
		this.city = city;
		currentProduction = null;
		initList();
	}

	public void initList(){
		productions = new HashMap<>();
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
			Notification notification = new Notification(this.city, Notification.NotificationTexts.PRODUCTION_BUILT);
			this.city.getCivilization().getNotificationInbox().addNotification(notification);
		}
	}

	public Producible getCurrentProduction() {
		return currentProduction;
	}

	public void setCurrentProduction(Producible producible){
		initList();
		this.currentProduction = producible;
	}

	public Vector<Producible> getAvailableProductions() {
		Vector<Producible> out = new Vector<>();
		for(Map.Entry<Producible, Double> set : productions.entrySet())
			if(set.getKey().isProducible(city))
				out.add(set.getKey());
		return out;
	}

	public Vector<Producible> getAllProductions(){
		return Producible.productions;
	}

	public void cancelCurrentProduction(){
		currentProduction = null;
	}

	public int getRemainedTurns(){
		if(currentProduction == null)
			return 0;
		Currency currency = city.getCurrency();
		if(currency.getProduct() == 0)
			return 999999;
		else
			return (int)Math.ceil(productions.get(currentProduction)/currency.getProduct());
	}


}