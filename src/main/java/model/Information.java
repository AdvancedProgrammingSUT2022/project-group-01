package model;

import model.civilization.Civilization;
import model.civilization.Trade;
import model.civilization.city.City;
import model.civilization.production.Producible;
import model.improvement.ImprovementType;
import model.resource.ResourceType;
import model.technology.TechTree;
import model.tile.Tile;
import model.unit.Unit;
import model.unit.action.Action;
import model.unit.action.Actions;

import java.util.*;

public class Information {
	private Game game;
	private Civilization civilization;


	public Information(Civilization civilization, Game game){
		this.civilization = civilization;
		this.game = game;
	}

	public String cityListPanel(){
		StringBuilder s = new StringBuilder();
		s.append("City List :").append("\n");
		int i = 1;
		for(City city : civilization.getCities()) {
			s.append(i).append(". ");
			s.append("Name : ");
			s.append(city.getName()).append("\n");
			s.append("Population : ").append(city.getPopulation()).append("\n");
			s.append("Defence Power : ").append(city.getDefencePower()).append("\n");
			s.append("Attack Power : ").append(city.getAttackPower()).append("\n");
			i++;
		}
		return s.toString();
	}

	public City getCityByName(String name){
		for(City city : civilization.getCities()){
			if(city.getName().equals(name)){
				return city;
			}
		}
		return null;
	}

	//city panel
	public String cityPanel(City city){
		StringBuilder s = new StringBuilder();
		s.append("City Panel :").append("\n");
		s.append("Name : ").append(city.getName()).append("\n");
		s.append("Population : ").append(city.getPopulation()).append("\n");
		s.append("Defence Power : ").append(city.getDefencePower()).append("\n");
		s.append("Attack Power : ").append(city.getAttackPower()).append("\n");
		s.append("Number Of Tiles : ").append(city.getTiles().size()).append("\n");
		s.append("Beaker : ").append(city.getBeaker()).append("\n");
		s.append("Currency : ").append(city.getCurrency().getGold()).append("G, ");
		s.append(city.getCurrency().getFood()).append("F, ").append(city.getCurrency().getProduct()).append("P").append("\n");
		s.append("Current Production : ");
		if(city.getProductionInventory().getCurrentProduction() != null)
			s.append(city.getProductionInventory().getCurrentProduction());
		s.append("\n");
		return s.toString();
	}


	// barresi kolli eghtesadi in get cities list
	public String EconomicOverview(City city){
		StringBuilder s = new StringBuilder();
		s.append("City name : ").append(city.getName()).append("\n");
		s.append("City population : ").append(city.getPopulation()).append("\n");
		s.append("City attack power : ").append(city.getAttackPower()).append("\n");
		s.append("City defense power : ").append(city.getDefencePower()).append("\n");
		s.append("City beaker : ").append(city.getBeaker()).append("\n");
		s.append("City Currency : ").append("Gold : ").append(city.getCurrency().getGold())
				.append(" Food : ").append(city.getCurrency().getFood()).append(" Production : ")
				.append(city.getCurrency().getProduct()).append("\n");
		s.append("City happiness : ").append(city.getHappiness()).append("\n");
		s.append("City buildings : ").append(" ").append("\n");
		// Improvements
		s.append("City Improvements : ");
		for(Tile tile : city.getTiles()){
			if(tile.getImprovementInAction() != null) {
				s.append("Improvement :").append(tile.getImprovementInAction()).append(" State : ");
				s.append(tile.getImprovementInventoryState());
				if(tile.getImprovementInventoryState().equals(ProgressState.IN_PROGRESS)){
					s.append(" turns left : ").append(tile.getImprovementTurnsLeft());
				}
				s.append("\n");
			}
		}
		s.append("\n");
		//Production
		s.append("City Productions : ").append("\n");
		s.append("Current production : ").append("\n");
		s.append(city.getProductionInventory().getCurrentProduction()).append("\n");
		s.append("Available productions : ").append("\n");
		for(Producible producible : city.getProductionInventory().getAvailableProductions()){
			s.append(producible.toString()).append("\n");
		}
		// Garrison inside
		if(city.getGarrisonedUnit() != null)
			s.append("Garrison unit : ").append(city.getGarrisonedUnit().getType().name()).append("\n");

		return s.toString();
	}

	public String getNotificationsHistory(){
		StringBuilder output = new StringBuilder();
		output.append("Notifications List").append("\n");
		for(Notification notification : civilization.getNotificationInbox().getNotifications()){
			output.append("Text : ").append(notification.getText()).append("\n");
			output.append("Turn : ").append(notification.getAnnouncementTurn()).append("\n");
			output.append("Reading Status : ");
			if(notification.isRead()) output.append("is read");
			else output.append("not read");
			output.append("\n");
		}
		return output.toString();
	}


	//Panel list yegan ha
	public String unitPanelUnitList(){
		StringBuilder s = new StringBuilder();
		s.append("Units list : ").append("\n");
		int i = 1;
		for(Unit unit : civilization.getUnits()){
			s.append(i).append(". ");
			s.append(unit.getType().name()).append(" ");
			if(unit.getActionsQueue().getCurrentAction().getActionType().equals(Actions.SLEEP))
				s.append("sleeping");
			else s.append("active");
			s.append("\n");
			i++;
		}
		return s.toString();
	}

	//active unit in Panel List yegan ha
	public String activeUnit(int i){
		if(civilization.getUnits().size() < i) return "bruh! enter a valid index" + unitPanelUnitList();
		Unit selectedUnit = civilization.getUnits().get(i - 1);
		selectedUnit.wake();
		game.getCurrentPlayer().setMapCenterTile(selectedUnit.getCurrentTile());
		return "The selected unit has waken up. \n The map is now centered on the unit's tile.";
	}

	// barreC koli nezami dar Panel Yegan ha
	public String militaryOverview(){
		StringBuilder s = new StringBuilder();
		s.append("Military Overview :").append("\n");
		for(Unit unit : civilization.getUnits()){
			s.append("Name : ").append(unit.getType().name()).append("\n");
			s.append("Status : ");
			if(unit.getActionsQueue().getCurrentAction() != null) s.append(unit.getActionsQueue().getCurrentAction().getActionType());
			s.append("\n");
			s.append("Turns left of action : ");
			if(unit.getActionsQueue().getCurrentAction() != null)
				s.append(unit.getActionsQueue().getCurrentAction().getRemainedTurns());
			s.append("\n");
			s.append("Health : ").append(unit.getHealth()).append("\n");
			s.append("Combat Strength : ").append(unit.getType().getCombatStrength()).append("\n");
		}
		return s.toString();
	}

	// jamiat shenasi
	public String demographicScreen(){
		StringBuilder s = new StringBuilder();
		// Civilization name
		s.append("Civilization name : ").append("\n");
		s.append(civilization.getCivilization().name()).append("\n");
		// Civilization size
		s.append("Civilization size : ").append("\n");
		// Civilization city size
		s.append(getSizeByCitiesData(civilization));
		// Civilization tile size
		s.append(getNumberOfTilesData(civilization));
		//Units
		s.append(getNumberOfUnits(civilization));
		//Population
		s.append(getPopulationData(civilization));
		//Currency
		s.append(getGoldData(civilization));
		s.append(getFoodData(civilization));
		s.append(getProductionData(civilization));
		//Happiness
		s.append(getHappinessData(civilization));
		//Number Of resources
		s.append(getResourceData(civilization));

		return s.toString();
	}

	private String getPopulationData(Civilization civ){
		ArrayList<Civilization> civilizations = new ArrayList<>();
		int sum = 0;
		for(Player player : game.getPlayers()){
			civilizations.add(player.getCivilization());
			sum += player.getCivilization().getPopulationSize();
		}
		civilizations.sort(Comparator.comparingInt(Civilization::getPopulationSize));
		return "Population : " + "\n" +
				"size : " + civ.getPopulationSize() + "\n" +
				"max : " + civilizations.get(0).getPopulationSize() + "\n" +
				"min : " + civilizations.get(civilizations.size() - 1).getPopulationSize() + "\n" +
				"average : " + sum / civilizations.size() + "\n" +
				"rank : " + civilizations.indexOf(civ) + "\n";
	}

	private String getGoldData(Civilization civ){
		ArrayList<Civilization> civilizations = new ArrayList<>();
		double sum = 0;
		for(Player player : game.getPlayers()){
			civilizations.add(player.getCivilization());
			sum += player.getCivilization().getCurrency().getGold();
		}
		civilizations.sort(new Comparator<>() {
			@Override
			public int compare(Civilization o1, Civilization o2) {
				double difference = o1.getCurrency().getGold() - o2.getCurrency().getGold();
				if (difference > 0) return 1;
				if (difference < 0) return -1;
				return 0;
			}
		});
		return "Gold : " + "\n" +
				"size : " + civ.getCurrency().getGold() + "\n" +
				"max : " + civilizations.get(0).getCurrency().getGold()+ "\n" +
				"min : " + civilizations.get(civilizations.size() - 1).getCurrency().getGold() + "\n" +
				"average : " + sum / civilizations.size() + "\n" +
				"rank : " + civilizations.indexOf(civ) + "\n";
	}

	private String getFoodData(Civilization civ){
		ArrayList<Civilization> civilizations = new ArrayList<>();
		double sum = 0;
		for(Player player : game.getPlayers()){
			civilizations.add(player.getCivilization());
			sum += player.getCivilization().getCurrency().getFood();
		}
		civilizations.sort(new Comparator<>() {
			@Override
			public int compare(Civilization o1, Civilization o2) {
				double difference = o1.getCurrency().getFood() - o2.getCurrency().getFood();
				if (difference > 0) return 1;
				if (difference < 0) return -1;
				return 0;
			}
		});
		return "Food : " + "\n" +
				"size : " + civ.getCurrency().getFood() + "\n" +
				"max : " + civilizations.get(0).getCurrency().getFood()+ "\n" +
				"min : " + civilizations.get(civilizations.size() - 1).getCurrency().getFood() + "\n" +
				"average : " + sum / civilizations.size() + "\n" +
				"rank : " + civilizations.indexOf(civ) + "\n";
	}

	private String getProductionData(Civilization civ){
		ArrayList<Civilization> civilizations = new ArrayList<>();
		double sum = 0;
		for(Player player : game.getPlayers()){
			civilizations.add(player.getCivilization());
			sum += player.getCivilization().getCurrency().getProduct();
		}
		civilizations.sort(new Comparator<>() {
			@Override
			public int compare(Civilization o1, Civilization o2) {
				double difference = o1.getCurrency().getProduct() - o2.getCurrency().getProduct();
				if (difference > 0) return 1;
				if (difference < 0) return -1;
				return 0;
			}
		});
		return "Production : " + "\n" +
				"size : " + civ.getCurrency().getProduct() + "\n" +
				"max : " + civilizations.get(0).getCurrency().getProduct()+ "\n" +
				"min : " + civilizations.get(civilizations.size() - 1).getCurrency().getProduct() + "\n" +
				"average : " + sum / civilizations.size() + "\n" +
				"rank : " + civilizations.indexOf(civ) + "\n";
	}

	private String getHappinessData(Civilization civ){
		ArrayList<Civilization> civilizations = new ArrayList<>();
		double sum = 0;
		for(Player player : game.getPlayers()){
			civilizations.add(player.getCivilization());
			sum += player.getCivilization().getHappiness();
		}
		civilizations.sort(new Comparator<>() {
			@Override
			public int compare(Civilization o1, Civilization o2) {
				double difference = o1.getHappiness() - o2.getHappiness();
				if (difference > 0) return 1;
				if (difference < 0) return -1;
				return 0;
			}
		});
		return "Happiness : " + "\n" +
				"size : " + civ.getHappiness() + "\n" +
				"max : " + civilizations.get(0).getHappiness()+ "\n" +
				"min : " + civilizations.get(civilizations.size() - 1).getHappiness() + "\n" +
				"average : " + sum / civilizations.size() + "\n" +
				"rank : " + civilizations.indexOf(civ) + "\n";
	}

	private String getSizeByCitiesData(Civilization civ){
		ArrayList<Civilization> civilizations = new ArrayList<>();
		double sum = 0;
		for(Player player : game.getPlayers()){
			civilizations.add(player.getCivilization());
			sum += player.getCivilization().getCities().size();
		}
		civilizations.sort(new Comparator<>() {
			@Override
			public int compare(Civilization o1, Civilization o2) {
				double difference = o1.getCities().size() - o2.getCities().size();
				if (difference > 0) return 1;
				if (difference < 0) return -1;
				return 0;
			}
		});
		return "Number of cities: " + "\n" +
				"size : " + civ.getCities().size() + "\n" +
				"max : " + civilizations.get(0).getCities().size()+ "\n" +
				"min : " + civilizations.get(civilizations.size() - 1).getCities().size() + "\n" +
				"average : " + sum / civilizations.size() + "\n" +
				"rank : " + civilizations.indexOf(civ) + "\n";
	}

	private String getNumberOfTilesData(Civilization civ){
		ArrayList<Civilization> civilizations = new ArrayList<>();
		double sum = 0;
		for(Player player : game.getPlayers()){
			civilizations.add(player.getCivilization());
			sum += getNumberOfCivilizationTiles(civilization);
		}
		civilizations.sort(new Comparator<>() {
			@Override
			public int compare(Civilization o1, Civilization o2) {
				double difference = getNumberOfCivilizationTiles(o1) - getNumberOfCivilizationTiles(o2);
				if (difference > 0) return 1;
				if (difference < 0) return -1;
				return 0;
			}
		});
		return "number of tiles : " + "\n" +
				"size : " + getNumberOfCivilizationTiles(civilization) + "\n" +
				"max : " + getNumberOfCivilizationTiles(civilizations.get(0))+ "\n" +
				"min : " + getNumberOfCivilizationTiles(civilizations.get(civilizations.size() - 1)) + "\n" +
				"average : " + sum / civilizations.size() + "\n" +
				"rank : " + civilizations.indexOf(civ) + "\n";
	}

	private int getNumberOfCivilizationTiles(Civilization civ){
		int numberOfTiles = 0;
		for(City city : civ.getCities()){
			numberOfTiles += city.getTiles().size();
		}
		return numberOfTiles;
	}

	private String getNumberOfUnits(Civilization civ){
		ArrayList<Civilization> civilizations = new ArrayList<>();
		double sum = 0;
		for(Player player : game.getPlayers()){
			civilizations.add(player.getCivilization());
			sum += player.getCivilization().getUnits().size();
		}
		civilizations.sort(new Comparator<>() {
			@Override
			public int compare(Civilization o1, Civilization o2) {
				return o1.getUnits().size() - o2.getUnits().size();
			}
		});
		return "number of units : " + "\n" +
				"size : " + civilization.getUnits().size() + "\n" +
				"max : " + civilizations.get(0).getUnits().size()+ "\n" +
				"min : " + civilizations.get(civilizations.size() - 1).getUnits().size() + "\n" +
				"average : " + sum / civilizations.size() + "\n" +
				"rank : " + civilizations.indexOf(civ) + "\n";
	}

	private String getResourceData(Civilization civ){
		ArrayList<Civilization> civilizations = new ArrayList<>();
		double sum = 0;
		for(Player player : game.getPlayers()){
			civilizations.add(player.getCivilization());
			sum += getNumberOfResourceReposit(player.getCivilization());
		}
		civilizations.sort(new Comparator<>() {
			@Override
			public int compare(Civilization o1, Civilization o2) {
				double difference = getNumberOfResourceReposit(o1) - getNumberOfResourceReposit(o2);
				if (difference > 0) return 1;
				if (difference < 0) return -1;
				return 0;
			}
		});
		return "Number of resources in repository : " + "\n" +
				"size : " + getNumberOfResourceReposit(civ) + "\n" +
				"max : " + getNumberOfResourceReposit(civilizations.get(0))+ "\n" +
				"min : " + getNumberOfResourceReposit(civilizations.get(civilizations.size() - 1)) + "\n" +
				"average : " + sum / civilizations.size() + "\n" +
				"rank : " + civilizations.indexOf(civ) + "\n";
	}

	private int getNumberOfResourceReposit(Civilization civ){
		int number = 0;
		for(Integer  i : civ.getResourceRepository().values())
			number += i;
		return number;
	}

	// Panel Kavosh ha
	public String researchInfo(){
		return "Research Info" + "\n" +
				"Current Research :" +
				civilization.getResearchTree().getCurrentResearch() + "\n" +
				"Remaining Science : " + civilization.getResearchTree().getRemainingScience() + "\n";
	}


	// TODO Diplomacy Panel
	public String diplomacyPanel(){return null;}

	// TODO Victory Progress Panel
	public String victoryProgressPanel(){return null;}


	public int getScore(){return 0;}

	//TODO Diplomatic Overview
	public String diplomaticOverview(){return null;}

	//TODO Deal History Screen
	public String dealHistoryScreen(){return null;}




}