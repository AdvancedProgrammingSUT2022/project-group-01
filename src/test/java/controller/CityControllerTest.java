package controller;

import controller.civilization.city.CityController;
import model.civilization.Civilization;
import model.civilization.Currency;
import model.civilization.Person;
import model.civilization.city.City;
import model.civilization.production.Producible;
import model.civilization.production.ProductionInventory;
import model.technology.TechTree;
import model.tile.Tile;
import model.unit.UnitType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.Pair;

import java.beans.Transient;
import java.util.Vector;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CityControllerTest {

    CityController cityController;
    @BeforeEach
    public void init(){
        cityController = new CityController(null);
    }

    @Test
    public void getNextTilesTest(){
        City city = mock(City.class);
        Tile tile = mock(Tile.class);
        when(tile.getMapNumber()).thenReturn(2);
        Vector<Tile> tiles = new Vector<Tile>();
        tiles.add(tile);
        when(city.getNextTiles()).thenReturn(tiles);
        String answer = "1- 2";
        Assertions.assertEquals(answer, cityController.getNextTiles(city));
    }

    @Test
    public void getPurchasableTilesTest(){
        City city = mock(City.class);
        Tile tile = mock(Tile.class);
        when(tile.getMapNumber()).thenReturn(2);
        Vector<Pair<Tile,Integer>> tiles = new Vector<>();
        tiles.add(new Pair<>(tile, 50));
        when(city.getPurchasableTiles()).thenReturn(tiles);
        String answer = "1- 2 : 50$\n";
        Assertions.assertEquals(answer, cityController.getPurchasableTile(city));
    }

    @Test
    public void purchaseTileTestInvalidIndex(){
        City city = mock(City.class);
        Tile tile = mock(Tile.class);
        Vector<Pair<Tile,Integer>> tiles = new Vector<>();
        tiles.add(new Pair<>(tile, 50));
        when(city.getPurchasableTiles()).thenReturn(tiles);
        String answer = "invalid index!";
        Assertions.assertEquals(answer, cityController.purchaseTile(city, 2));
    }

    @Test
    public void purchaseTileTestNotEnoughGold(){
        City city = mock(City.class);
        Tile tile = mock(Tile.class);
        Civilization civilization = mock(Civilization.class);
        Vector<Pair<Tile,Integer>> tiles = new Vector<>();
        tiles.add(new Pair<>(tile, 50));
        when(city.getPurchasableTiles()).thenReturn(tiles);
        when(city.getCivilization()).thenReturn(civilization);
        when(civilization.getCurrency()).thenReturn(new Currency(49,0,0));

        String answer = "you don't have enough gold!";
        Assertions.assertEquals(answer, cityController.purchaseTile(city, 1));
    }

    @Test
    public void purchaseTileTestEnoughGold(){
        City city = mock(City.class);
        Tile tile = mock(Tile.class);
        Civilization civilization = mock(Civilization.class);
        Vector<Pair<Tile,Integer>> tiles = new Vector<>();
        tiles.add(new Pair<>(tile, 50));
        when(city.getPurchasableTiles()).thenReturn(tiles);
        when(city.getCivilization()).thenReturn(civilization);
        when(civilization.getCurrency()).thenReturn(new Currency(100,0,0));
        String answer = "tile purchased!";
        Assertions.assertEquals(answer, cityController.purchaseTile(city, 1));
    }

    @Test
    public void getPopulationTest(){
        Vector<Person> population = new Vector<Person>();
        City city = mock(City.class);
        Tile tile = mock(Tile.class);
        Person person1 = new Person();
        Person person2 = new Person();
        person1.setTile(tile);
        when(tile.getMapNumber()).thenReturn(25);
        population.add(person1);
        population.add(person2);
        when(city.getPopulation()).thenReturn(population);
        String result = "number of civilians: 2\n1 : 25\n2 : unemployed\n";
        Assertions.assertEquals(result, cityController.getPopulation(city));
    }

    @Test
    public void setPopulationTestOne(){
        City city = mock(City.class);
        Vector population = mock(Vector.class);
        when(city.getPopulation()).thenReturn(population);
        when(population.size()).thenReturn(2);
        String answer = "invalid index";
        Assertions.assertEquals(answer, cityController.setPopulation(city,3,null));
    }

    @Test
    public void setPopulationTestTwo(){
        Civilization civ1 = mock(Civilization.class);
        Civilization civ2 = mock(Civilization.class);
        City city = mock(City.class);
        Tile tile = mock(Tile.class);
        Vector<Person> population = new Vector<Person>();
        Person person1 = new Person();
        population.add(person1);
        when(city.getPopulation()).thenReturn(population);
        when(tile.getCivilization()).thenReturn(civ1);
        when(city.getCivilization()).thenReturn(civ2);
        String answer = "this isn't your tile!";
        Assertions.assertEquals(answer, cityController.setPopulation(city,1,tile));
    }

    @Test
    public void setPopulationTestThree(){
        Civilization civ1 = mock(Civilization.class);
        City city = mock(City.class);
        Tile tile = mock(Tile.class);
        Vector<Person> population = new Vector<Person>();
        Vector peopleInside = mock(Vector.class);
        Person person1 = new Person();
        when(peopleInside.contains(person1)).thenReturn(false);
        when(peopleInside.size()).thenReturn(1);
        when(tile.getPeopleInside()).thenReturn(peopleInside);
        population.add(person1);
        when(city.getPopulation()).thenReturn(population);
        when(tile.getCivilization()).thenReturn(civ1);
        when(city.getCivilization()).thenReturn(civ1);
        String answer = "not empty!";
        Assertions.assertEquals(answer, cityController.setPopulation(city,1,tile));
    }

    @Test
    public void setPopulationTestFour(){
        Civilization civ1 = mock(Civilization.class);
        City city = mock(City.class);
        Tile tile = mock(Tile.class);
        Vector<Person> population = new Vector<Person>();
        Vector peopleInside = mock(Vector.class);
        Person person1 = new Person();
        when(peopleInside.contains(person1)).thenReturn(true);
        when(peopleInside.size()).thenReturn(1);
        when(tile.getPeopleInside()).thenReturn(peopleInside);
        population.add(person1);
        when(city.getPopulation()).thenReturn(population);
        when(tile.getCivilization()).thenReturn(civ1);
        when(city.getCivilization()).thenReturn(civ1);
        String answer = "the worker is already here!";
        Assertions.assertEquals(answer, cityController.setPopulation(city,1,tile));
    }

    @Test
    public void setPopulationTestFive(){
        Civilization civ1 = mock(Civilization.class);
        City city = mock(City.class);
        Tile tile = mock(Tile.class);
        Vector<Person> population = new Vector<Person>();
        Vector peopleInside = mock(Vector.class);
        Person person1 = new Person();
        when(peopleInside.contains(person1)).thenReturn(false);
        when(peopleInside.size()).thenReturn(0);
        when(tile.getPeopleInside()).thenReturn(peopleInside);
        population.add(person1);
        when(city.getPopulation()).thenReturn(population);
        when(tile.getCivilization()).thenReturn(civ1);
        when(city.getCivilization()).thenReturn(civ1);
        String answer = "done!";
        Assertions.assertEquals(answer, cityController.setPopulation(city,1,tile));
    }

    @Test
    public void deletePopulationTestOne(){
        City city = mock(City.class);
        Vector population = mock(Vector.class);
        when(city.getPopulation()).thenReturn(population);
        when(population.size()).thenReturn(1);
        String answer = "invalid index";
        Assertions.assertEquals(answer, cityController.deletePopulation(city,2));
    }

    @Test
    public void deletePopulationTestTwo(){
        City city = mock(City.class);
        Vector<Person> population = new Vector();
        Person person = mock(Person.class);
        when(city.getPopulation()).thenReturn(population);
        population.add(person);
        String answer = "this civilian doesn't have job yet!";
        Assertions.assertEquals(answer, cityController.deletePopulation(city,1));
    }

    @Test
    public void deletePopulationTestThree(){
        Tile tile = mock(Tile.class);
        City city = mock(City.class);
        Vector<Person> population = new Vector();
        Person person = mock(Person.class);
        when(city.getPopulation()).thenReturn(population);
        population.add(person);
        when(person.getTile()).thenReturn(tile);
        String answer = "done!";
        Assertions.assertEquals(answer, cityController.deletePopulation(city,1));
    }

    @Test 
    public void listProductionsTestOne(){
        City city = mock(City.class);
        ProductionInventory productionInventory = mock(ProductionInventory.class);
        Vector<Producible> list = new Vector<>();
        Currency currency = new Currency(0,40,0);
        when(city.getCurrency()).thenReturn(currency);
        list.add(UnitType.WARRIOR);
        list.add(UnitType.WORKER);
        when(productionInventory.getAvailableProductions()).thenReturn(list);
        when(city.getProductionInventory()).thenReturn(productionInventory);
        String answer = "1- WARRIOR : 1 turn\n2- WORKER : 2 turns\n";
        Assertions.assertEquals(answer, cityController.getProductionsListToProduce(city));
    }

    @Test
    public void listProductionsTestTwo(){
        City city = mock(City.class);
        ProductionInventory productionInventory = mock(ProductionInventory.class);
        Vector<Producible> list = new Vector<>();
        Currency currency = new Currency(0,40,0);
        when(city.getCurrency()).thenReturn(currency);
        list.add(UnitType.WARRIOR);
        list.add(UnitType.WORKER);
        when(productionInventory.getAllProductions()).thenReturn(list);
        when(city.getProductionInventory()).thenReturn(productionInventory);
        String answer = "1- WARRIOR : 40 gold\n2- WORKER : 70 gold\n";
        Assertions.assertEquals(answer, cityController.getProductionsListToPurchase(city));
    }

    @Test
    public void setProductionTestOne(){
        City city = mock(City.class);
        String answer = "invalid production!";
        Assertions.assertEquals(answer, cityController.setProductionToProduce(city, "random"));
    }

    @Test
    public void setProductionTestTwo(){
        City city = mock(City.class);
        TechTree techTree = mock(TechTree.class);
        Civilization civ1 = mock(Civilization.class);
        when(city.getCivilization()).thenReturn(civ1);
        when(civ1.getResearchTree()).thenReturn(techTree);
        when(techTree.isResearched(UnitType.WARRIOR.getRequiredTechs())).thenReturn(false);
        String ans = "you don't have necessary technology!";
        Assertions.assertEquals(ans, cityController.setProductionToProduce(city, "warrior"));
    }

    @Test
    public void setProductionTestThree(){
        City city = mock(City.class);
        TechTree techTree = mock(TechTree.class);
        Civilization civ1 = mock(Civilization.class);
        when(city.getCivilization()).thenReturn(civ1);
        when(civ1.getResearchTree()).thenReturn(techTree);
        when(techTree.isResearched(UnitType.WARRIOR.getRequiredTechs())).thenReturn(true);
        String ans = "done!";
        Assertions.assertEquals(ans, cityController.setProductionToProduce(city, "warrior"));
    }

    @Test
    public void purchaseProductionTestOne(){
        City city = mock(City.class);
        String answer = "invalid production!";
        Assertions.assertEquals(answer, cityController.purchaseProduction(city, "random"));
    }

    @Test
    public void purchaseProductionTestTwo(){
        City city = mock(City.class);
        TechTree techTree = mock(TechTree.class);
        Civilization civ1 = mock(Civilization.class);
        Currency currency = new Currency(39,0,0);
        when(city.getCivilization()).thenReturn(civ1);
        when(civ1.getResearchTree()).thenReturn(techTree);
        when(civ1.getCurrency()).thenReturn(currency);
        when(techTree.isResearched(UnitType.WARRIOR.getRequiredTechs())).thenReturn(true);
        String answer = "you don't have enough gold!";
        Assertions.assertEquals(answer, cityController.purchaseProduction(city, "warrior"));
    }

    @Test
    public void purchaseProductionTestThree(){
        City city = mock(City.class);
        Tile tile = mock(Tile.class);
        when(city.getCenter()).thenReturn(tile);
        TechTree techTree = mock(TechTree.class);
        Civilization civ1 = mock(Civilization.class);
        Currency currency = new Currency(40,0,0);
        when(city.getCivilization()).thenReturn(civ1);
        when(civ1.getResearchTree()).thenReturn(techTree);
        when(civ1.getCurrency()).thenReturn(currency);
        when(techTree.isResearched(UnitType.WARRIOR.getRequiredTechs())).thenReturn(true);
        String answer = "done!";
        Assertions.assertEquals(answer, cityController.purchaseProduction(city, "warrior"));
    }

    @Test
    public void increaseResourceTestOne(){
        City city = mock(City.class);
        Currency currency = new Currency(0,0,0);
        when(city.getCurrency()).thenReturn(currency);
        Civilization civ = mock(Civilization.class);
        when(city.getCivilization()).thenReturn(civ);
        String answer = "hey cheater, gold increased!";
        Assertions.assertEquals(cityController.increaseResource(city, "gold", 5), answer);
    }
    @Test
    public void increaseResourceTestTwo(){
        City city = mock(City.class);
        Currency currency = new Currency(0,0,0);
        when(city.getCurrency()).thenReturn(currency);
        Civilization civ = mock(Civilization.class);
        when(city.getCivilization()).thenReturn(civ);
        String answer = "hey cheater, product increased!";
        Assertions.assertEquals(cityController.increaseResource(city, "product", 5), answer);
    }
    @Test
    public void increaseResourceTestThree(){
        City city = mock(City.class);
        Currency currency = new Currency(0,0,0);
        when(city.getCurrency()).thenReturn(currency);
        Civilization civ = mock(Civilization.class);
        when(city.getCivilization()).thenReturn(civ);
        String answer = "hey cheater, food increased!";
        Assertions.assertEquals(cityController.increaseResource(city, "food", 5), answer);
    }
    @Test
    public void increaseResourceTestFour(){
        City city = mock(City.class);
        Currency currency = new Currency(0,0,0);
        when(city.getCurrency()).thenReturn(currency);
        Civilization civ = mock(Civilization.class);
        when(city.getCivilization()).thenReturn(civ);
        String answer = "invalid resource!";
        Assertions.assertEquals(cityController.increaseResource(city, "random", 5), answer);
    }
}
