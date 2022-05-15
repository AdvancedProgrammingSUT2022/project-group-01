package model.civilization;

import model.Player;
import model.civilization.city.City;
import model.map.SavedMap;
import model.resource.ResourceType;
import model.tile.Tile;
import model.unit.Unit;
import model.unit.trait.TraitsList;
import model.unit.trait.UnitTraits;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Vector;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CivilizationTest {
    Civilization civilization;
    City city;
    Player player;
    @BeforeEach
    void init(){
        city = mock(City.class);
        player = mock(Player.class);
        civilization = new Civilization(Civilizations.ASSYRIAN,city, player);
    }

    @Test
    void constructorTest(){
        Assertions.assertNotNull(civilization.getUnits());
        Assertions.assertNotNull(civilization.getCities());
        Assertions.assertNotNull(civilization.getResourceRepository());
        Assertions.assertNotNull(civilization.getTechTree());
        Assertions.assertNotNull(civilization.getCurrency());
        Assertions.assertNotNull(civilization.getPlayer());

    }

    @Test
    void getterTest(){
        civilization.getPlayer();
        civilization.getCivilization();
        civilization.getCities();
        civilization.getCapital();
        civilization.getCurrency();
        civilization.getHappiness();
        civilization.getHappinessBase();
        civilization.getMap();
        civilization.getResourceRepository();
        civilization.getUnits();
        civilization.getBeaker();
        civilization.getNotificationInbox();
        civilization.getTechTree();
        civilization.getKnownCivilizations();
        Assertions.assertNotNull(civilization.getResearchTree());
        Player player1 = mock(Player.class);
        civilization.setPlayer(player1);
        civilization.setCivilization(Civilizations.EGYPTIAN);
        civilization.setCities(new Vector<City>());
        City city = mock(City.class);
        civilization.setCapital(city);
        civilization.setCurrency(new Currency(5,5,5));
        civilization.setHappiness(10);
        civilization.setHappinessBase(5);
        SavedMap savedMap = mock(SavedMap.class);
        civilization.setMap(savedMap);
        civilization.setResourceRepository(null);
        civilization.setUnits(new Vector<Unit>());
        civilization.setBeaker(15);
        civilization.setNotificationInbox(null);
        civilization.setTechTree(null);
        civilization.setKnownCivilizations(new Vector<>());
    }

    @Test
    void addUnitTest(){
        Unit unit = mock(Unit.class);
        civilization.addUnit(unit);
        Assertions.assertEquals(civilization.getUnits().size(),1);
    }

    @Test
    void addNewCityTest(){
        City city = mock(City.class);
        civilization.addNewCity(city);
        Assertions.assertEquals(civilization.getCities().size(),1);
    }

    @Test
    void nextTurnTest(){
        when(city.getChangesOfCurrency()).thenReturn(new Currency(5,5,5));
        civilization.addNewCity(city);
        when(city.getBeaker()).thenReturn(5);
        Vector population = mock(Vector.class);
        when(population.size()).thenReturn(1);
        Vector<Unit> units = new Vector<>();
        Unit unit = mock(Unit.class);
        units.add(unit);
        civilization.setUnits(units);
        when(city.getPopulation()).thenReturn(population);
        HashMap<ResourceType,Integer> h = new HashMap<>();
        h.put(ResourceType.GOLD,1);
        civilization.setResourceRepository(h);
        civilization.nextTurn(civilization);
        Assertions.assertEquals(civilization.getCurrency().getGold(),5);
        Assertions.assertEquals(civilization.getCurrency().getFood(),5);
        Assertions.assertEquals(civilization.getCurrency().getProduct(),5);
        Assertions.assertEquals(civilization.getHappiness(),22);//todo this is wrong
        Assertions.assertEquals(civilization.getBeaker(),5);
    }
    @Test
    void invalidNextTurnTest(){
        Civilization newCiv = mock(Civilization.class);
        civilization.nextTurn(newCiv);
    }


    @Test
    void getHappinessTest(){
        civilization.setHappiness(10);
        Assertions.assertEquals(civilization.getHappiness(),10);
    }

    @Test
    void getKnownCivilizationsTest(){
        Vector<Civilization> known = civilization.getKnownCivilizations();
        Assertions.assertEquals(known.size(), 0);
    }

    @Test
    void adKnownCivilizationTest(){
        Civilization civ1 = mock(Civilization.class);
        Civilization civ2 = mock(Civilization.class);
        civilization.addKnownCivilization(civ1);
        civilization.addKnownCivilization(civ2);
        civilization.addKnownCivilization(civ1);
    }

    @Test
    void getCitiesTest(){
        City city = mock(City.class);
        civilization.addNewCity(city);
        Vector<City> cities = civilization.getCities();
        Assertions.assertTrue(cities.contains(city));
    }

    @Test
    void increaseCurrencyTest(){
        Currency currency = new Currency(5,5,5);
        Currency curr = civilization.getCurrency();
        double currGold = curr.getGold();
        civilization.increaseCurrency(currency);
        Assertions.assertEquals(curr.getGold(), currGold+5);
    }

    @Test
    void getCivilizationTest(){
        Assertions.assertEquals(civilization.getCivilization(), Civilizations.ASSYRIAN);
    }

    @Test
    void visibleTilesTest(){
        Unit unit = mock(Unit.class);
        Tile tile2 = mock(Tile.class);
        when(unit.getCurrentTile()).thenReturn(tile2);
        TraitsList traitsList = mock(TraitsList.class);
        when(unit.getTraitsList()).thenReturn(traitsList);
        when(traitsList.contains(UnitTraits.LIMITED_VISIBILITY)).thenReturn(true);
        when(tile2.getSight(1)).thenReturn(new Vector<>());
        civilization.addUnit(unit);
        Vector<Tile> tiles = new Vector<>();
        Tile tile = mock(Tile.class);
        tiles.add(tile);
        when(city.getTiles()).thenReturn(tiles);
        when(tile.getSight(2)).thenReturn(new Vector<Tile>());
        civilization.addNewCity(city);
        Vector<Tile> result = civilization.visibleTiles();
        Assertions.assertEquals(result.size(),1);
    }

    @Test
    void visibleTilesTestTwo(){
        Unit unit = mock(Unit.class);
        Tile tile2 = mock(Tile.class);
        when(unit.getCurrentTile()).thenReturn(tile2);
        TraitsList traitsList = mock(TraitsList.class);
        when(unit.getTraitsList()).thenReturn(traitsList);
        when(traitsList.contains(UnitTraits.LIMITED_VISIBILITY)).thenReturn(false);
        when(tile2.getSight(2)).thenReturn(new Vector<>());
        civilization.addUnit(unit);
        Vector<Tile> tiles = new Vector<>();
        Tile tile = mock(Tile.class);
        tiles.add(tile);
        when(city.getTiles()).thenReturn(tiles);
        when(tile.getSight(2)).thenReturn(new Vector<Tile>());
        civilization.addNewCity(city);
        Vector<Tile> result = civilization.visibleTiles();
        Assertions.assertEquals(result.size(),1);
    }
    @Test
    void deleteCityTest(){
        civilization.deleteCity(city);
        Assertions.assertEquals(civilization.getCities().size() ,0);
    }

    @Test
    void getCapitalTest(){
        civilization.addNewCity(this.city);
        City city = civilization.getCapital();
        Assertions.assertSame(city,this.city);
    }

    @Test
    void hasResourceTest(){
        HashMap<ResourceType, Integer> h = new HashMap<>();
        h.put(ResourceType.COAL,1);
        civilization.setResourceRepository(h);
        Assertions.assertEquals(civilization.hasResource(ResourceType.COAL),true);
    }

    @Test
    void hasResourceTestTwo(){
        HashMap<ResourceType, Integer> h = new HashMap<>();
        h.put(ResourceType.COAL,1);
        civilization.setResourceRepository(h);
        Assertions.assertEquals(civilization.hasResource(ResourceType.GOLD),false);
    }

    @Test
    void addResourceTestOne(){
        HashMap<ResourceType, Integer> h = new HashMap<>();
        h.put(ResourceType.COAL,1);
        civilization.setResourceRepository(h);
        civilization.addResource(ResourceType.COAL,2);
        civilization.addResource(ResourceType.GOLD,2);
        Assertions.assertEquals(civilization.getResourceRepository().get(ResourceType.COAL),3);
        Assertions.assertEquals(civilization.getResourceRepository().get(ResourceType.GOLD),2);
    }

    @Test
    void removeResourceTestOne(){
        HashMap<ResourceType, Integer> h = new HashMap<>();
        h.put(ResourceType.COAL,3);
        h.put(ResourceType.IRON,1);
        civilization.setResourceRepository(h);
        civilization.removeResource(ResourceType.GOLD);
        civilization.removeResource(ResourceType.IRON);
        civilization.removeResource(ResourceType.COAL);
        Assertions.assertFalse(civilization.hasResource(ResourceType.IRON));
        Assertions.assertTrue(civilization.hasResource(ResourceType.COAL));
    }

    @Test
    void removeUnitTest(){
        Unit unit = mock(Unit.class);
        civilization.addUnit(unit);
        Assertions.assertEquals(civilization.getUnits().size(),1);
        civilization.removeUnit(unit);
        Assertions.assertEquals(civilization.getUnits().size(),0);
    }

    @Test
    void getPopulationSizeTest(){
        Vector population = mock(Vector.class);
        when(population.size()).thenReturn(2);
        when(city.getPopulation()).thenReturn(population);
        civilization.addNewCity(city);
        Assertions.assertEquals(civilization.getPopulationSize(),2);
    }

    @Test
    void addTileResourceTest(){
        Tile tile = mock(Tile.class);
        when(tile.getAvailableResource()).thenReturn(ResourceType.GOLD);
        civilization.addTileResources(tile);
        Assertions.assertTrue(civilization.hasResource(ResourceType.GOLD));
    }

    @Test
    void getResourcesCurrency(){
        HashMap<ResourceType, Integer> repo = new HashMap<>();
        repo.put(ResourceType.GOLD,1);
        civilization.setResourceRepository(repo);
        Currency currency = civilization.getResourcesCurrency();
        Assertions.assertEquals(currency.getGold(), 2);
    }

}
