package model.civilization;

import lombok.Getter;
import lombok.Setter;
import model.Player;
import model.TurnBasedLogic;
import model.civilization.city.City;
import model.information.NotificationInbox;
import model.map.SavedMap;
import model.resource.KindsOfResource;
import model.resource.ResourceType;
import model.technology.TechTree;
import model.tile.Tile;
import model.unit.Unit;
import model.unit.trait.UnitTraits;
import utils.VectorUtils;

import java.util.HashMap;
import java.util.Vector;

@Getter
@Setter
public class Civilization implements TurnBasedLogic {

    Player player;
    private Civilizations civilization;//enum
    private Vector<City> cities;
    private City capital;
    private Currency currency;
    private int happiness = 15;
    private int happinessBase = 0;
    private SavedMap map;
    @Getter
    private HashMap<ResourceType, Integer> resourceRepository;
    private Vector<Unit> units;//TODO merge with safar
    private int beaker;
    @Getter
    private NotificationInbox notificationInbox = new NotificationInbox();

    private TechTree techTree;//TODO merge with safar
    private Vector<Civilization> knownCivilizations;

    public Civilization(Civilizations civilization, City capital, Player player) {
        this.civilization = civilization;
        this.capital = capital;
        units = new Vector<>(); //ADDED BY PRCR
        cities = new Vector<>(); // ADDED BY PRCR
        resourceRepository = new HashMap<>(); //ADDED BY PRCR
        techTree = new TechTree(); // TODO ADDED TEMPORARILY BY PRCR
        addToList();
        this.currency = new Currency(0, 0, 0);
        this.player = player;
        knownCivilizations = new Vector<>();
    }

    public TechTree getResearchTree() {
        return techTree;
    }

    public void addUnit(Unit unit) {
        units.add(unit);
    }

    public void addNewCity(City city) {//TODO add arguments
        cities.add(city);
    }

    private void updateCurrency() {
        for (City city : cities) {
            currency.add(city.getChangesOfCurrency());
        }
    }

    public void nextTurn(Civilization civilization) {
        if (civilization != this)
            return;
        for (City city : cities)
            city.nextTurn();
        updateCurrency();
        updateHappiness();
        updateBeaker();
        for (Unit unit : units)
            unit.nextTurn();
        techTree.addScience(beaker);
    }

    private void updateBeaker() {
        beaker = 0;
        for (City city : cities)
            beaker += city.getBeaker();
    }

    public void updateHappiness() {
        happiness = 20;
        happiness -= cities.size() * 2;
        for (City city : cities)
            happiness -= city.getPopulation().size() / 5;
        doResourceHappiness();
        happiness += happinessBase;
    }

    public int getHappiness() {
        return happiness;
    }

    public Vector<Civilization> getKnownCivilizations() {
        return knownCivilizations;
    }

    public void addKnownCivilization(Civilization civilization) {
        if (!knownCivilizations.contains(civilization))
            knownCivilizations.add(civilization);
    }

    public Vector<Unit> getUnits() {
        return units;
    }

    public Vector<City> getCities() {
        return cities;
    }

    public void increaseCurrency(Currency currency) {
        this.currency.add(currency);
    }

    public Civilizations getCivilization() {
        return civilization;
    }

    public Vector<Tile> visibleTiles() {
        Vector<Tile> ourCells = new Vector<>();
        for (City city : cities) {
            ourCells.addAll(city.getTiles());
        }
        Vector<Tile> out = new Vector<>();
        for (Unit unit : units) {
            out.addAll(unit.getCurrentTile().getSight(
                    unit.getTraitsList().contains(UnitTraits.LIMITED_VISIBILITY) ? 1 : 2
            ));
        }
        for (Tile tile : ourCells) {
            out.add(tile);
            out.addAll(tile.getSight(2));
        }
        out = VectorUtils.unique(out);
        return out;
    }

    public void deleteCity(City city) {
        cities.remove(city);
    }

    public City getCapital() {
        return cities.isEmpty() ? null : cities.get(0);
    }

    // HANDLE ADDING RESOURCE
    public boolean hasResource(ResourceType resource) {
        return this.resourceRepository.containsKey(resource);
    }

    public void addResource(ResourceType resource, int number) {
        if (hasResource(resource))
            this.resourceRepository.replace(resource, this.resourceRepository.get(resource) + number);
        else this.resourceRepository.put(resource, number);
    }

    public void removeResource(ResourceType resource) {
        if (!hasResource(resource))
            return;
        this.resourceRepository.replace(resource, this.resourceRepository.get(resource) - 1);
        if (this.resourceRepository.get(resource) == 0)
            this.resourceRepository.remove(resource);
    }

    public void doResourceHappiness() {
        for (ResourceType resourceType : resourceRepository.keySet()) {
            if (resourceType.resourceKind.equals(KindsOfResource.LUXURY))
                this.happiness += 4;
        }
    }

    public void addTileResources(Tile tile) {
        if ((tile.getAvailableResource() != null) && (!tile.getAvailableResource().resourceKind.equals(KindsOfResource.BONUS)))
            addResource(tile.getAvailableResource(), 1);
    }

    public Currency getResourcesCurrency() {
        Currency returningCurrency = new Currency(0, 0, 0);
        for (ResourceType resource : resourceRepository.keySet()) {
            returningCurrency.increase(resourceRepository.get(resource) * resource.gold, resourceRepository.get(resource) * resource.production, resourceRepository.get(resource) * resource.food);
        }
        return returningCurrency;
    }

    public void removeUnit(Unit unit) {
        units.remove(unit);
    }

    public int getPopulationSize() {
        int population = 0;
        for (City city : this.getCities()) population += city.getPopulation().size();
        return population;
    }


}