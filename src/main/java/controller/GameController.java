package controller;

import model.Game;
import model.Player;
import model.civilization.Civilization;
import model.civilization.city.City;
import model.tile.Tile;
import model.unit.armed.Armed;
import model.unit.civilian.Civilian;

import java.util.Vector;

public class GameController {

    private final Game game;
    private final MapController mapController;
    public GameController(Game game,MapController mapController){
        this.game = game;
        this.mapController = mapController;
    }

    public String selectUnit(String type, Integer position){
        Tile tile = game.getMap().getTileByNumber(position);
        if(tile == null)
            return "Invalid tile!";
        Tile.VisibilityState visibilityState = game.getCurrentPlayer().getSavedMap().getVisibilityState(tile);
        if(visibilityState == Tile.VisibilityState.FOG_OF_WAR || visibilityState == Tile.VisibilityState.DISCOVERED)
            return "you can't select any unit here!";
        if(type.equals("civilian")){
            Civilian civilian = tile.getCivilianUnit();
            if(civilian == null)
                return "there is no civilian here!";
            game.setSelectedObject(civilian);
        }else if(type.equals("armed")){
            Armed armed = tile.getArmedUnit();
            if(armed == null)
                return "there is no armed here!";
            game.setSelectedObject(armed);
        }else{
            return "invalid unit type, types are [armed, civilian]";
        }
        return "unit selected";
    }

    private City findCityByName(String name){
        Vector<Player> players = game.getPlayers();
        for(Player player : players){
            Civilization civilization = player.getCivilization();
            for(City city : civilization.getCities()){
                if(city.getName().equals(name)){
                    return city;
                }
            }
        }
        return null;
    }

    public String selectCity(String selectingType, String value){
        if(selectingType.equals("position")){
            Tile tile = game.getMap().getTileByNumber(Integer.parseInt(value));
            if(tile == null)
                return "Invalid position";
            if(tile.getOwnerCity() == null)
                return "There is no city here";
            game.setSelectedObject(tile.getOwnerCity());
            return "city selected";
        }else if(selectingType.equals("name")){
            City city = findCityByName(value);
            if(city == null)
                return "there is no city with this name";
            game.setSelectedObject(city);
        }
        return "invalid command!";
    }

    public String mapShow(String selectingType, String value){
        if(selectingType.equals("position")){
            int position = Integer.parseInt(value);
            mapController.setPosition(position);
            return "done";
        }else if(selectingType.equals("cityname")){
            City city = findCityByName(value);
            if(city == null)
                return "there is no city with this name";
            mapController.setPosition(city.getCenterTile().getMapNumber());
            return "done";
        }else{
            return "invalid command!";
        }
    }
}
