package model.building;
import model.Player;
import model.civilization.Civilization;
import model.civilization.city.City;
import model.civilization.production.Producible;
import model.resource.ResourceType;
import model.technology.TechnologyType;

import java.util.Vector;

public enum BuildingType implements Producible{
	BARRACKS(80,1,TechnologyType.BRONZE_WORKING,new Vector<>()){
		@Override
		public void effect(Player player, City city){
		}
	},
	GRANARY(100,1,TechnologyType.THE_WHEEL, new Vector<>()),
	LIBRARY(80,1,TechnologyType.WRITING, new Vector<>()),
	MONUMENT(60,1,null,new Vector<>()),
	WALLS(100,1,TechnologyType.MASONRY,new Vector<>()),
	WATER_MILL(120,2,TechnologyType.THE_WHEEL,new Vector<>()),
	ARMORY(130,3,TechnologyType.IRON_WORKING,new Vector<>()),
	BURIAL_TOMB(120,0,TechnologyType.PHILOSOPHY,new Vector<>()),
	CIRCUS(150,3,TechnologyType.HORSEBACK_RIDING,new Vector<>(){{
		add(ResourceType.HORSES);
	}}),
	COLOSSEUM(150,3,TechnologyType.HORSEBACK_RIDING, new Vector<>()),
	COURTHOUSE(200,5,TechnologyType.MATHEMATICS,new Vector<>()),
	STABLE(100,1,TechnologyType.HORSEBACK_RIDING,new Vector<>(){{
		add(ResourceType.HORSES);
	}}),
	TEMPLE(120,2,TechnologyType.PHILOSOPHY, new Vector<>()),
	CASTLE(200,3,TechnologyType.CHIVALRY,new Vector<>()),
	FORGE(150,2,TechnologyType.METAL_CASTING,new Vector<>(){{
		add(ResourceType.IRON);
	}}),
	GARDEN(120,2,TechnologyType.THEOLOGY,new Vector<>()),
	MARKET(120,0,TechnologyType.METAL_CASTING, new Vector<>()),
	MINT(120,0,TechnologyType.CURRENCY,new Vector<>()),
	MONASTERY(120,2,TechnologyType.THEOLOGY, new Vector<>()),
	UNIVERSITY(200,3,TechnologyType.EDUCATION, new Vector<>()),
	WORKSHOP(100,2,TechnologyType.METAL_CASTING, new Vector<>()),
	BANK(220,0,TechnologyType.BANKING, new Vector<>()),
	MILITARY_ACADEMY(350,3,TechnologyType.MILITARY_SCIENCE, new Vector<>()),
	MUSEUM(350,3,TechnologyType.ARCHAEOLOGY, new Vector<>()),
	OPERA_HOUSE(220,3,TechnologyType.ACOUSTICS, new Vector<>()),
	PUBLIC_SCHOOL(350,3,TechnologyType.SCIENTIFIC_THEORY, new Vector<>()),
	SATRAPS_COURT(220,0,TechnologyType.BANKING, new Vector<>()),
	THEATRE(300,5,TechnologyType.PRINTING_PRESS, new Vector<>()),
	WINDMILL(180,2,TechnologyType.ECONOMICS, new Vector<>()),
	ARSENAL(350,3,TechnologyType.RAILROAD,new Vector<>()),
	BROADCAST_TOWER(600,3,TechnologyType.RADIO, new Vector<>()),
	FACTORY(300,3,TechnologyType.STEAM_POWER, new Vector<>() {{
		add(ResourceType.COAL);
	}}),
	HOSPITAL(400,2,TechnologyType.BIOLOGY,new Vector<>()),
	MILITARY_BASE(450,4,TechnologyType.TELEGRAPH, new Vector<>()),
	STOCK_EXCHANGE(650,0,TechnologyType.ELECTRICITY, new Vector<>());

	public final int cost;
	public final int maintenance;
	public final TechnologyType requiredTechnology;
	public final Vector<ResourceType> necessaryResources;

	BuildingType(int cost, int maintenance, TechnologyType requiredTechnology, Vector<ResourceType> necessaryResources) {
		this.cost = cost;
		this.maintenance = maintenance;
		this.requiredTechnology = requiredTechnology;
		this.necessaryResources = necessaryResources;
		//Producible.productions.add(this);
	}

	/**
	 * 
	 * @param player
	 * @param city
	 */
	public void effect(Player player, City city) {
		// TODO - implement BuildingType.effect
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param player
	 * @param city
	 */
	public boolean isEligibleToBuild(Player player, City city) {
		// TODO - implement BuildingType.isEligibleToBuild
		throw new UnsupportedOperationException();
	}

	public int getProductionTime(Civilization civilization, City city){
		//TODO override in each enum literal
		return 0;
	}


	@Override
	public void produce(City city) {

	}

	@Override
	public boolean isProducible(City city) {
		return false;
	}

	@Override
	public int getCost(City city) {
		return 0;
	}

	@Override
	public String toString() {
		return this.name();
	}
}