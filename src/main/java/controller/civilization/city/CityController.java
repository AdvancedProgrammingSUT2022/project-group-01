package controller.civilization.city;

import model.civilization.Person;
import model.civilization.city.City;
import model.tile.Tile;

import java.util.Vector;

public class CityController {


    public String changePersonTile(City city, int personId, Tile newTile){
        Vector<Person> population = city.getPopulation();
        if(population.size() <= personId){
            return "Invalid ID";
        }
        population.get(personId).setTile(newTile);
        return "Done";
    }



}
