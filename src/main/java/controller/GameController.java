package controller;

import model.Game;
import model.Player;
import model.civilization.Civilization;
import model.civilization.city.City;
import model.tile.TerrainFeature;
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

    private City findCityByName(String name){Vector<Player> players = game.getPlayers();
        for(Player player : players){
            Civilization civilization = player.getCivilization();
            for(City city : civilization.getCities()){
                if(city.getName().toLowerCase().equals(name)){
                    return city;
                }}
        }
        return null;}

    public String selectCity(String selectingType, String value){if(selectingType.equals("position")){
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
            return "city selected";
        }
        return "invalid command!";
    }

    public String mapShow(String selectingType, String value){
        mapController.updateCurrentPlayersMap();
        if(selectingType.equals("position")){
            int position = Integer.parseInt(value);
            mapController.setPosition(position);
            return mapController.getConsoleMap(game.getCurrentPlayer().getMapCenterTile());
        }
        else if(selectingType.equals("cityname")){
            City city = findCityByName(value);
            if(city == null)
                return "there is no city with this name";

            mapController.setPosition(city.getCenter().getMapNumber());
            return mapController.getConsoleMap(game.getCurrentPlayer().getMapCenterTile());
        }else{
            return "invalid command!";
        }
    }

    public String showTileInfo(Tile tile){StringBuilder s = new StringBuilder().append("Tile Number : ").append(tile.getMapNumber()).append("\n");
        s.append("Terrain : ").append(tile.getTerrain().name()).append("\n");
        s.append("Feature : ");
        if(tile.getFeature() != null) s.append(tile.getFeature().name());
        s.append("\n");
        s.append("Gold : ").append(tile.getCurrency().getGold()).append("\n");
        s.append("Food : ").append(tile.getCurrency().getFood()).append("\n");
        s.append("Production : ").append(tile.getCurrency().getProduct()).append("\n");
        s.append("Owner Civilization");
        if(tile.getCivilization() != null) s.append(tile.getCivilization().getCivilization().name());
        s.append("\n");
        s.append("Armed Unit Inside : ");
        if(tile.getArmedUnit() != null) s.append(tile.getArmedUnit().getType().name());
        s.append("\n");
        s.append("Civilian Unit Inside : ");
        if(tile.getCivilianUnit() != null) s.append(tile.getCivilianUnit().getType().name());
        s.append("\n");
        s.append("Resources : ");
        if(tile.getAvailableResource() != null) s.append(tile.getAvailableResource().name());
        s.append("\n");
        s.append("Improvement : ");
        if(tile.getImprovementInAction() != null){
            s.append(tile.getImprovementInAction().name()).append(" ");
            s.append(tile.getImprovementInventoryState());
        }
        s.append("\n");
        s.append("Owner City : ");
        if(tile.getOwnerCity() != null) s.append(tile.getOwnerCity().getName());
        s.append("\n");
        return s.toString();
    }

    public String cheatSetFeature(Tile tile, TerrainFeature feature){
        if(tile.getImprovementInAction() != null) return "you can't use cheat on this tile";
        if(tile.getAvailableResource() != null) return "you can't use cheat on this tile";
        if(!tile.getTerrain().possibleFeatures.contains(feature)) return "you can't use cheat on this tile";
        tile.setFeature(feature);
        return "God's grace has brought you the feature you desired.";
    }

    public String getPlayerInfo(){
        return "current player: "+game.getCurrentPlayer().getUser().getNickname();
    }
}
