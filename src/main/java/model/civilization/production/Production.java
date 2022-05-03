package model.civilization.production;

import model.civilization.Civilization;
import model.civilization.city.City;

public abstract class Production {

    int cost;
    int remainedCost;

    public abstract void create(City city);

    public void createNextPart(int production, City city){
        remainedCost -= production;
        if(remainedCost <= 0)
            create(city);
    }

}
