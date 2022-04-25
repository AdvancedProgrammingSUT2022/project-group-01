package model.building;
import model.Player;
import model.civilization.Civilization;
import model.civilization.Job;
import model.civilization.city.City;
import model.resource.ResourceType;
import model.technology.TechnologyType;

import java.util.Vector;

public enum BuildingType {
	BARRACKS(80,1,TechnologyType.BRONZE_WORKING,null,0, new Vector<>()){
		@Override
		public void effect(Player player, City city){
		}
	},
	GRANARY(100,1,TechnologyType.THE_WHEEL,null,0, new Vector<>()),
	LIBRARY(80,1,TechnologyType.WRITING, Job.SCIENTIST,2, new Vector<>()),
	MONUMENT(60,1,null,null,0, new Vector<>()),
	WALLS(100,1,TechnologyType.MASONRY,null,0, new Vector<>()),
	WATER_MILL(120,2,TechnologyType.THE_WHEEL,null,0, new Vector<>()),
	ARMORY(130,3,TechnologyType.IRON_WORKING,null,0, new Vector<>()),
	BURIAL_TOMB(120,0,TechnologyType.PHILOSOPHY,null,0, new Vector<>()),
	CIRCUS(150,3,TechnologyType.HORSEBACK_RIDING,null,0, new Vector<>(){{
		add(ResourceType.HORSES);
	}}),
	COLOSSEUM(150,3,TechnologyType.HORSEBACK_RIDING,null,0, new Vector<>()),
	COURTHOUSE(200,5,TechnologyType.MATHEMATICS,null,0, new Vector<>()),
	STABLE(100,1,TechnologyType.HORSEBACK_RIDING,null,0, new Vector<>(){{
		add(ResourceType.HORSES);
	}}),
	TEMPLE(120,2,TechnologyType.PHILOSOPHY,Job.ARTIST,1, new Vector<>()),
	CASTLE(200,3,TechnologyType.CHIVALRY,null,0, new Vector<>()),
	FORGE(150,2,TechnologyType.METAL_CASTING,null,0, new Vector<>(){{
		add(ResourceType.IRON);
	}}),
	GARDEN(120,2,TechnologyType.THEOLOGY,Job.ARTIST,1, new Vector<>()),
	MARKET(120,0,TechnologyType.METAL_CASTING,Job.ENGINEER,1, new Vector<>()),
	MINT(120,0,TechnologyType.CURRENCY,null,0, new Vector<>()),
	MONASTERY(120,2,TechnologyType.THEOLOGY,null,0, new Vector<>()),
	UNIVERSITY(200,3,TechnologyType.EDUCATION,Job.SCIENTIST,1, new Vector<>()),
	WORKSHOP(100,2,TechnologyType.METAL_CASTING,Job.ENGINEER,1, new Vector<>()),
	BANK(220,0,TechnologyType.BANKING,Job.MERCHANT,1, new Vector<>()),
	MILITARY_ACADEMY(350,3,TechnologyType.MILITARY_SCIENCE,null,0, new Vector<>()),
	MUSEUM(350,3,TechnologyType.ARCHAEOLOGY,Job.ARTIST,1, new Vector<>()),
	OPERA_HOUSE(220,3,TechnologyType.ACOUSTICS,null,0, new Vector<>()),
	PUBLIC_SCHOOL(350,3,TechnologyType.SCIENTIFIC_THEORY,Job.SCIENTIST,1, new Vector<>()),
	SATRAPS_COURT(220,0,TechnologyType.BANKING,Job.MERCHANT,1, new Vector<>()),
	THEATRE(300,5,TechnologyType.PRINTING_PRESS,null,0, new Vector<>()),
	WINDMILL(180,2,TechnologyType.ECONOMICS, Job.ENGINEER,1, new Vector<>()),
	ARSENAL(350,3,TechnologyType.RAILROAD,null,0, new Vector<>()),
	BROADCAST_TOWER(600,3,TechnologyType.RADIO,null,0, new Vector<>()),
	FACTORY(300,3,TechnologyType.STEAM_POWER, Job.ENGINEER,1, new Vector<>() {{
		add(ResourceType.COAL);
	}}),
	HOSPITAL(400,2,TechnologyType.BIOLOGY,null,0,new Vector<>()),
	MILITARY_BASE(450,4,TechnologyType.TELEGRAPH,null,0, new Vector<>()),
	STOCK_EXCHANGE(650,0,TechnologyType.ELECTRICITY,Job.MERCHANT,1, new Vector<>());

	public final int cost;
	public final int maintenance;
	public final TechnologyType requiredTechnology;
	public final Job specialist;
	public final int specialistSlots;
	public final Vector<ResourceType> necessaryResources;

	BuildingType(int cost, int maintenance, TechnologyType requiredTechnology, Job specialist, int specialistSlots, Vector<ResourceType> necessaryResources) {
		this.cost = cost;
		this.maintenance = maintenance;
		this.requiredTechnology = requiredTechnology;
		this.specialist = specialist;
		this.specialistSlots = specialistSlots;
		this.necessaryResources = necessaryResources;
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

}