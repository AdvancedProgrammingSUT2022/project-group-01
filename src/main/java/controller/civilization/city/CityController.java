package controller.civilization.city;

import model.Game;
import model.civilization.Currency;
import model.civilization.Person;
import model.civilization.city.City;
import model.civilization.production.Producible;
import model.tile.Tile;
import utils.Pair;

import java.util.Arrays;
import java.util.Vector;

public class CityController {

    private final Game game;

    public CityController(Game game){
        this.game = game;
    }

    public String getNextTiles(City city){
        Vector<Tile> tiles = city.getNextTiles();
        StringBuilder stringBuilder = new StringBuilder();
        for(int i=0; i<tiles.size();i++){
            stringBuilder.append(i+1).append("- ").append(tiles.get(i).getMapNumber());
            if(i != tiles.size()-1)
                stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

    public String getPurchasableTile(City city){
        StringBuilder items = new StringBuilder();
        Vector<Pair<Tile, Integer>> pairs = city.getPurchasableTiles();
        for (int i=0;i<pairs.size();i++) {
            Pair<Tile, Integer> pair = pairs.get(i);
            items.append(pair.getFirst().getMapNumber()).append(" : ").append(pair.getSecond()).append("$\n");
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
            return "you don't have enough gold!";
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

    /**
     *
     * @param productions list of productions want to be printed
     * @param city city
     * @param toProduct true if you want turns and false if you want cost as gold
     * @return a string that contains (producible : turns) or (producible : gold) form for each producible
     */
    private String listProductions(Vector<Producible> productions, City city,boolean toProduct){
        StringBuilder out = new StringBuilder();
        int i = 1;
        for(Producible producible : productions){
            Double turns = Math.ceil(producible.getCost(city)/city.getCurrency().getProduct());
            out.append(i).append("- ").append(producible.toString()).append(" : ");
            if(toProduct) {
                out.append(turns);
                if (turns > 1)
                    out.append(" turns\n");
                else
                    out.append(" turn\n");
            }else{
                out.append(producible.getCost(city)).append(" gold\n");
            }
            i++;
        }
        return out.toString();
    }

    public String getProductionsListToProduce(City city){
        Vector<Producible> productions = city.getProductionInventory().getAvailableProductions();
        return listProductions(productions, city, true);
    }

    public String setProductionToProduce(City city, String type){
        Producible producible = stringToProducible(type);
        if(producible == null)
            return "invalid production!";
        if(!producible.isProducible(city))
            return "you don't have necessary technology!";
        city.getProductionInventory().setCurrentProduction(producible);
        return "done!";
    }

    public String getProductionsListToPurchase(City city){
        System.out.println("salam");
        Vector<Producible> productions = Producible.productions;
        return listProductions(productions, city, false);
    }

    public String purchaseProduction(City city, String type){
        Producible purchasing = stringToProducible(type);
        if(purchasing == null)
            return "invalid production!";
        int cost = purchasing.getCost(city);
        if(cost > city.getCurrency().getGold())
            return "you don't have enough gold!";
        city.payCurrency(cost, 0,0);
        purchasing.produce(city);
        System.out.printf("%s purchased\n", purchasing.toString());//todo remove here
        return "done";
    }

    private Producible stringToProducible(String type){
        Producible out = null;
        for (Producible producible : Producible.productions)
            if(producible.toString().toLowerCase().equals(type))
                out = producible;
        return out;
    }

    public String increaseResource(City city, String resourceName, int amount){
        Currency currency = new Currency(0,0,0);
        switch (resourceName){
            case "gold":{currency.increase(amount,0,0);}break;
            case "food":{currency.increase(0,0,amount);}break;
            case "product":{currency.increase(0,amount,0);}break;
            default:{return "invalid resource";}
        }
        city.getCurrency().add(currency);
        city.getChangesOfCurrency().add(currency);
        return "hey cheater, "+resourceName+" increased!";
    }
}
