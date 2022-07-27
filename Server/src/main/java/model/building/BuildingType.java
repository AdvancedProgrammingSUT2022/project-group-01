package model.building;
import lombok.Getter;
import model.Player;
import model.civilization.city.City;
import model.civilization.production.Producible;
import model.resource.ResourceList;
import model.resource.ResourceType;
import model.technology.TechnologyType;
import model.tile.Terrain;
import model.tile.Tile;

import java.util.Vector;

@Getter
public enum BuildingType implements Producible{
	BARRACKS(80,1,TechnologyType.BRONZE_WORKING,new Vector<>()){
		@Override
		public void effect(Player player, City city){

		}
	},
	GRANARY(100,1,TechnologyType.THE_WHEEL, new Vector<>()){
		@Override
		public void effect(Player player, City city){
			city.getFixedCurrencyRate().increase(0,0,2);
		}
	},
	LIBRARY(80,1,TechnologyType.WRITING, new Vector<>()){
		@Override
		public void effect(Player player, City city){
			city.setFixedBeakerRate(city.getFixedBeakerRate() + city.getPopulation().size()/2);
		}
	},
	MONUMENT(60,1,null,new Vector<>()),
	WALLS(100,1,TechnologyType.MASONRY,new Vector<>()){
		@Override
		public void effect(Player player, City city){
			city.increaseDefencePower(5);
		}
	},
	WATER_MILL(120,2,TechnologyType.THE_WHEEL,new Vector<>()){
		@Override
		public boolean isProducible(City city) {
			boolean out = false;
			Vector<Tile> tiles = city.getTiles();
			for(Tile tile : tiles){
				for(int i = 0; i < 6; i++){
					if(tile.getBoarder(i).isRiver())
						out = true;
				}
			}
			return out;
		}
		@Override
		public void effect(Player player, City city){
			city.getFixedCurrencyRate().increase(0,0,2);
		}
	},
	ARMORY(130,3,TechnologyType.IRON_WORKING,new Vector<>()){
		@Override
		public boolean isProducible(City city) {
			return (city.getBuildingInventory().getOwnedBuildings().contains(BARRACKS));
		}
	},
	BURIAL_TOMB(120,0,TechnologyType.PHILOSOPHY,new Vector<>()){
		@Override
		public void effect(Player player, City city){
			city.getCivilization().setHappinessBase(city.getCivilization().getHappinessBase() + 2);
		}
	},
	CIRCUS(150,3,TechnologyType.HORSEBACK_RIDING,new Vector<>(){{
		add(ResourceType.HORSES);
	}}){
		@Override
		public void effect(Player player, City city){
			city.getCivilization().setHappinessBase(city.getCivilization().getHappinessBase() + 3);
		}
		@Override
		public boolean isProducible(City city) {
			return false;//todo implement here
		}
	},
	COLOSSEUM(150,3,TechnologyType.HORSEBACK_RIDING, new Vector<>()){
		@Override
		public void effect(Player player, City city){
			city.getCivilization().setHappinessBase(city.getCivilization().getHappinessBase() + 4);
		}
	},
	COURTHOUSE(200,5,TechnologyType.MATHEMATICS,new Vector<>()){
		@Override
		public void effect(Player player, City city){
			//todo here
		}
	},
	STABLE(100,1,TechnologyType.HORSEBACK_RIDING,new Vector<>(){{
		add(ResourceType.HORSES);
	}}){
		@Override
		public void effect(Player player, City city){
			//todo
		}
		@Override
		public boolean isProducible(City city) {
			return false;//todo implement here
		}
	},
	TEMPLE(120,2,TechnologyType.PHILOSOPHY, new Vector<>()){
		@Override
		public boolean isProducible(City city) {
			return city.getBuildingInventory().getOwnedBuildings().contains(MONUMENT);
		}
	},
	CASTLE(200,3,TechnologyType.CHIVALRY,new Vector<>()){
		@Override
		public void effect(Player player, City city){
			city.increaseDefencePower(7.5);
		}
		@Override
		public boolean isProducible(City city) {
			return city.getBuildingInventory().getOwnedBuildings().contains(WALLS);
		}
	},
	FORGE(150,2,TechnologyType.METAL_CASTING,new Vector<>(){{
		add(ResourceType.IRON);
	}}){
		@Override
		public void effect(Player player, City city){
			city.setProductCurrencyFactor(city.getProductCurrencyFactor() + 0.15);
		}
		@Override
		public boolean isProducible(City city) {
			return false;//todo implement here
		}
	},
	GARDEN(120,2,TechnologyType.THEOLOGY,new Vector<>()){
		@Override
		public void effect(Player player, City city){
			city.setTurnToExpansion((int)(city.getTurnToExpansion()*0.8));
		}
		@Override
		public boolean isProducible(City city) {
			boolean out = false;
			Vector<Tile> tiles = city.getTiles();
			for(Tile tile : tiles){
				for(int i = 0; i < 6; i++){
					if(tile.getBoarder(i).isRiver())
						out = true;
				}
			}
			return out;
		}
	},
	MARKET(120,0,TechnologyType.METAL_CASTING, new Vector<>()){
		@Override
		public void effect(Player player, City city){
			city.setGoldCurrencyFactor(city.getGoldCurrencyFactor() + 0.25);
		}
	},
	MINT(120,0,TechnologyType.CURRENCY,new Vector<>()),
	MONASTERY(120,2,TechnologyType.THEOLOGY, new Vector<>()),
	UNIVERSITY(200,3,TechnologyType.EDUCATION, new Vector<>()){
		@Override
		public boolean isProducible(City city){
			return city.getBuildingInventory().getOwnedBuildings().contains(LIBRARY);
		}
		@Override
		public void effect(Player player, City city){
			city.setBeakerFactor(city.getBeakerFactor() + 0.5);
		}
	},
	WORKSHOP(100,2,TechnologyType.METAL_CASTING, new Vector<>()){
		@Override
		public void effect(Player player, City city){
			city.setProductCurrencyFactor(city.getProductCurrencyFactor() + 0.2);
		}
	},
	BANK(220,0,TechnologyType.BANKING, new Vector<>()){
		@Override
		public boolean isProducible(City city){
			return city.getBuildingInventory().getOwnedBuildings().contains(MARKET);
		}
		@Override
		public void effect(Player player, City city){
			city.setProductCurrencyFactor(city.getProductCurrencyFactor() + 0.25);
		}
	},
	MILITARY_ACADEMY(350,3,TechnologyType.MILITARY_SCIENCE, new Vector<>()){
		@Override
		public boolean isProducible(City city){
			return city.getBuildingInventory().getOwnedBuildings().contains(BARRACKS);
		}
	},
	MUSEUM(350,3,TechnologyType.ARCHAEOLOGY, new Vector<>()){
		@Override
		public boolean isProducible(City city){
			return city.getBuildingInventory().getOwnedBuildings().contains(OPERA_HOUSE);
		}
	},
	OPERA_HOUSE(220,3,TechnologyType.ACOUSTICS, new Vector<>()){
		@Override
		public boolean isProducible(City city){
			return city.getBuildingInventory().getOwnedBuildings().contains(TEMPLE) &&
					city.getBuildingInventory().getOwnedBuildings().contains(BURIAL_TOMB);
		}
	},
	PUBLIC_SCHOOL(350,3,TechnologyType.SCIENTIFIC_THEORY, new Vector<>()){
		@Override
		public boolean isProducible(City city){
			return city.getBuildingInventory().getOwnedBuildings().contains(UNIVERSITY);
		}
		@Override
		public void effect(Player player, City city){
			city.setBeakerFactor(city.getBeakerFactor() + 0.5);
		}
	},
	SATRAPS_COURT(220,0,TechnologyType.BANKING, new Vector<>()){
		@Override
		public boolean isProducible(City city){
			return city.getBuildingInventory().getOwnedBuildings().contains(MARKET);
		}
		@Override
		public void effect(Player player, City city){
			city.setGoldCurrencyFactor(city.getGoldCurrencyFactor() + 0.25);
			city.getCivilization().setHappinessBase(city.getCivilization().getHappinessBase() + 2);
		}
	},
	THEATRE(300,5,TechnologyType.PRINTING_PRESS, new Vector<>()){
		@Override
		public boolean isProducible(City city){
			return city.getBuildingInventory().getOwnedBuildings().contains(COLOSSEUM);
		}
		@Override
		public void effect(Player player, City city){
			city.getCivilization().setHappinessBase(city.getCivilization().getHappinessBase() + 4);
		}
	},
	WINDMILL(180,2,TechnologyType.ECONOMICS, new Vector<>()){
		@Override
		public boolean isProducible(City city){
			return city.getCenterTile().getTerrain() != Terrain.HILLS;
		}
		@Override
		public void effect(Player player, City city){
			city.setProductCurrencyFactor(city.getProductCurrencyFactor() + 0.15);
		}
	},
	ARSENAL(350,3,TechnologyType.RAILROAD,new Vector<>()){
		@Override
		public boolean isProducible(City city){
			return city.getBuildingInventory().getOwnedBuildings().contains(MILITARY_ACADEMY);
		}
		@Override
		public void effect(Player player, City city){
			city.setProductCurrencyFactor(city.getProductCurrencyFactor() + 0.2);
		}
	},
	BROADCAST_TOWER(600,3,TechnologyType.RADIO, new Vector<>()){
		@Override
		public boolean isProducible(City city){
			return city.getBuildingInventory().getOwnedBuildings().contains(MUSEUM);
		}
	},
	FACTORY(300,3,TechnologyType.STEAM_POWER, new Vector<>() {{
		add(ResourceType.COAL);
	}}){
		@Override
		public void effect(Player player, City city){
			city.setProductCurrencyFactor(city.getProductCurrencyFactor() + 0.5);
		}
	},
	HOSPITAL(400,2,TechnologyType.BIOLOGY,new Vector<>()){
		@Override
		public void effect(Player player, City city){
			city.setFoodCurrencyFactor(city.getFoodCurrencyFactor() - 0.5);
		}
	},
	MILITARY_BASE(450,4,TechnologyType.TELEGRAPH, new Vector<>()){
		@Override
		public boolean isProducible(City city){
			return city.getBuildingInventory().getOwnedBuildings().contains(CASTLE);
		}
		@Override
		public void effect(Player player, City city){
			city.increaseDefencePower(12);
		}
	},
	STOCK_EXCHANGE(650,0,TechnologyType.ELECTRICITY, new Vector<>()){
		@Override
		public boolean isProducible(City city){
			return city.getBuildingInventory().getOwnedBuildings().contains(BANK) ||
					city.getBuildingInventory().getOwnedBuildings().contains(SATRAPS_COURT);
		}
		@Override
		public void effect(Player player, City city){
			city.setGoldCurrencyFactor(city.getGoldCurrencyFactor() + 0.33);
		}
	};

	private final int cost;
	private final int maintenance;
	private final TechnologyType requiredTechnology;
	private final Vector<ResourceType> necessaryResources;
	private final ResourceList resourceList;
	BuildingType(int cost, int maintenance, TechnologyType requiredTechnology, Vector<ResourceType> necessaryResources) {
		this.cost = cost;
		this.maintenance = maintenance;
		this.requiredTechnology = requiredTechnology;
		this.necessaryResources = necessaryResources;
		this.resourceList = new ResourceList(necessaryResources);
		addToProductions();
	}

	public void effect(Player player, City city) {
		// implementing by overriding
	}


	@Override
	public void produce(City city) {
		city.getBuildingInventory().ownBuilding(this);
	}

	@Override
	public boolean isProducible(City city) {
		return resourceList.isAvailable(city.getCivilization()) && city.getCivilization().getResearchTree().isResearched(requiredTechnology);
	}

	@Override
	public int getCost(City city) {
		return this.cost;
	}

	@Override
	public String toString() {
		return this.name();
	}

	public void initializeEnum() {

	}
}