package model.civilization.production;

import model.civilization.city.City;

import java.util.Vector;

public interface Producible {
    Vector<Producible> productions = new Vector<>();
    void produce(City city);
    boolean isProducible(City city);
    int getCost(City city);
    String toString();
    default void addToProductions(){
        productions.add(this);
    }
}
