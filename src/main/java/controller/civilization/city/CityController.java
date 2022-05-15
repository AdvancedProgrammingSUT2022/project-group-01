package controller.civilization.city;

import model.Game;
import model.civilization.Currency;
import model.civilization.Person;
import model.civilization.city.City;
import model.civilization.production.Producible;
import model.tile.Tile;
import model.unit.armed.Armed;
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
            items.append(i+1).append("- ").append(pair.getFirst().getMapNumber()).append(" : ").append(pair.getSecond()).append("$\n");
        }
        return items.toString();
    }

    public String purchaseTile(City city, int tileIndex){
        Vector<Pair<Tile, Integer>> pairs = city.getPurchasableTiles();
        tileIndex--;
        if(tileIndex >= pairs.size()){
            return "invalid index!";
        }
        Currency currency = city.getCivilization().getCurrency();
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
        civilianIndex--;
        if(population.size() <= civilianIndex)
            return "invalid index";
        Vector<Person> peopleInside = newTile.getPeopleInside();
        Person person = city.getPopulation().get(civilianIndex);
        if(newTile.getCivilization() != city.getCivilization())
            return "this isn't your tile!";
        if(!peopleInside.contains(person) && peopleInside.size() != 0)
            return "not empty!";
        if(peopleInside.contains(person))
            return "the worker is already here!";
        person.setTile(newTile);
        return "done!";
    }

    public String deletePopulation(City city, int civilianIndex){
        civilianIndex--;
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
            double turns = Math.ceil(producible.getCost(city)/city.getCurrency().getProduct());
            out.append(i).append("- ").append(producible.toString()).append(" : ");
            if(toProduct) {
                out.append((int)turns);
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
        city.setNewProduction(producible);
        return "done!";
    }

    public String getProductionsListToPurchase(City city){
        Vector<Producible> productions = city.getProductionInventory().getAllProductions();
        return listProductions(productions, city, false);
    }

    public String purchaseProduction(City city, String type){
        Producible producible = stringToProducible(type);
        if(producible == null)
            return "invalid production!";
        int cost = producible.getCost(city);
        Currency currency = city.getCivilization().getCurrency();
        if(cost > currency.getGold())
            return "you don't have enough gold!";
        currency.increase(-1*cost, 0,0);
        producible.produce(city);
        return "done!";
    }

    private Producible stringToProducible(String type){
        Producible out = null;
        for (Producible producible : Producible.productions) {
            if (producible.toString().toLowerCase().equals(type))
                out = producible;
        }
        return out;
    }

    public String increaseResource(City city, String resourceName, int amount){
        switch (resourceName){
            case "gold":{
                city.getCivilization().increaseCurrency(new Currency(amount,0,0));
            }break;
            case "food":{city.increaseCurrency(0,0, amount);}break;
            case "product":{city.increaseCurrency(0,amount,0);}break;
            default:{return "invalid resource!";}
        }

        return "hey cheater, "+resourceName+" increased!";
    }

	public String cityAttack(City city, Armed target, Tile tile) {
        if(target.getOwnerCivilization() == city.getCivilization())
            return "why do you want to attack your units, are you idiot ?";
        if(!city.getCenter().getAttackingArea(2, false).contains(tile))
            return "this unit is not in attack range";
        if(city.isAttackedThisTurn())
            return "you already attacked this turn";
        double damage = city.getAttackPower();
        if(city.getGarrisonedUnit() != null)
            damage += city.getGarrisonedUnit().getType().getCombatStrength();
        target.changeHealth( -(int) (damage * (1 / target.getDefensePower())) );
        city.setAttackedThisTurn(true);
        return "Attacked to unit, say goodbye to that bastard";
	}
}
