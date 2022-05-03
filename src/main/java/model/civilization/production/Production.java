package model.civilization.production;

import model.civilization.Civilization;
import model.civilization.city.City;

import java.util.Vector;

public interface Production {
    Vector<Production> allProductions = new Vector<>();
    void produce(City city);
    boolean isProducible(City city);
    int getCost(City city);
}
