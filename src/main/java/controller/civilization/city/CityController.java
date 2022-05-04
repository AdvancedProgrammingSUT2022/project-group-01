package controller.civilization.city;

import model.Game;
import model.civilization.Currency;
import model.civilization.Person;
import model.civilization.city.City;
import model.tile.Tile;
import utils.Pair;

import java.util.Arrays;
import java.util.Vector;

public class CityController {

    private Game game;

    public CityController(Game game){
        this.game = game;
    }

    public String changePersonTile(City city, int personId, Tile newTile){
        Vector<Person> population = city.getPopulation();
        if(population.size() <= personId){
            return "invalid id!";
        }
        population.get(personId).setTile(newTile);
        return "done!";
    }

    public String getPurchasableTile(City city){
        StringBuilder items = new StringBuilder();
        Vector<Pair<Tile, Integer>> pairs = city.getPurchasableTiles();
        for (int i=0;i<pairs.size();i++) {
            Pair<Tile, Integer> pair = pairs.get(i);
            items.append(pair.getFirst().getMapNumber() + " : " + pair.getSecond() + "$\n");
        }
        return items.toString();
    }

    public String purchaseTile(City city, int tileIndex){
        Vector<Pair<Tile, Integer>> pairs = city.getPurchasableTiles();
        if(tileIndex >= pairs.size()){
            return "invalid index!";
        }
        Currency currency = city.getCurrency();
        Pair<Tile, Integer> pair = pairs.get(tileIndex);
        if(currency.getGold() < pair.getSecond())
            return "insufficient gold!";
        currency.add(new Currency(-pair.getSecond(), 0,0));
        city.addNewTiles(new Vector<>(Arrays.asList(pair.getFirst())));
        return "tile purchased!";
    }

    public String getPopulation(City city){
        Vector<Person> population = city.getPopulation();
        StringBuilder out = new StringBuilder();
        out.append("number of civilians: "+population.size()+"\n");
        for(int i=0;i<population.size();i++){
            out.append(String.format("%d : ", i+1));
            if(population.get(i).getTile() != null)
                out.append(population.get(i).getTile().getMapNumber());
            else
                out.append("unemployed");
            out.append("\n");
        }
        return out.toString();
    }

    public String setPopulation(City city, int civilianIndex, Tile newTile){
        Vector<Person> population = city.getPopulation();
        if(population.size() <= civilianIndex)
            return "invalid index";
        Vector<Person> peopleInside = newTile.getPeopleInside();
        Person person = city.getPopulation().get(civilianIndex);
        if(!peopleInside.contains(person) && peopleInside.size() != 0)
            return "not empty!";
        if(peopleInside.contains(person))
            return "the worker is already here!";
        person.setTile(newTile);
        return "done!";
    }

    public String deletePopulation(City city, int civilianIndex){
        Vector<Person> population = city.getPopulation();
        if(population.size() <= civilianIndex)
            return "invalid index";
        Person person = city.getPopulation().get(civilianIndex);
        if(person.getTile() == null)
            return "this civilian doesn't have job yet!";
        person.setTile(null);
        return "done!";
    }

}
