package controller;

import model.Game;
import model.Player;
import model.civilization.Civilization;
import model.civilization.city.City;
import model.technology.TechnologyType;
import model.tile.TerrainFeature;
import model.tile.Tile;
import model.unit.armed.Armed;
import model.unit.civilian.Civilian;

import java.util.HashMap;
import java.util.Vector;

public class GameController {

    private final Game game;
    private final MapController mapController;
    private HashMap<Player,Integer> scores;
    private Vector<Player> losers;

    public GameController(Game game, MapController mapController) {
        this.game = game;
        this.mapController = mapController;
        scores = new HashMap<>();
        losers = new Vector<>();
        initPlayers();
    }


    private City findCityByName(String name) {
        Vector<Player> players = game.getPlayers();
        for (Player player : players) {
            Civilization civilization = player.getCivilization();
            for (City city : civilization.getCities()) {
                if (city.getName().toLowerCase().equals(name)) {
                    return city;
                }
            }
        }
        return null;
    }

    public String selectCity(String selectingType, String value) {
        if (selectingType.equals("position")) {
            Tile tile = game.getMap().getTileByNumber(Integer.parseInt(value));
            if (tile == null)
                return "Invalid position";
            if (tile.getOwnerCity() == null)
                return "There is no city here";
            game.setSelectedObject(tile.getOwnerCity());
            return "city selected";
        } else if (selectingType.equals("name")) {
            City city = findCityByName(value);
            if (city == null)
                return "there is no city with this name";
            game.setSelectedObject(city);
            return "city selected";
        }
        return "invalid command!";
    }

    public String mapShow(String selectingType, String value) {
        mapController.updateCurrentPlayersMap();

        if (selectingType.equals("position")) {
            int position = Integer.parseInt(value);
            mapController.setPosition(position);
            return "map is set";
        }else if(selectingType.equals("cityname")){
            City city = findCityByName(value);
            if (city == null)
                return "there is no city with this name";
            mapController.setPosition(city.getCenter().getMapNumber());
            return "map is set";
        } else {
            return "invalid command!";
        }
    }

    public String mapShow() {
        mapController.updateCurrentPlayersMap();
        return mapController.getConsoleMap(game.getCurrentPlayer().getMapCenterTile());
    }

    public String showTileInfo(Tile tile) {
        StringBuilder s = new StringBuilder().append("Tile Number : ").append(tile.getMapNumber()).append("\n");
        s.append("Terrain : ").append(tile.getTerrain().name()).append("\n");
        s.append("Feature : ");
        if (tile.getFeature() != null) s.append(tile.getFeature().name());
        s.append("\n");
        s.append("Gold : ").append(tile.getCurrency().getGold()).append("\n");
        s.append("Food : ").append(tile.getCurrency().getFood()).append("\n");
        s.append("Production : ").append(tile.getCurrency().getProduct()).append("\n");
        s.append("Owner Civilization : ");
        if (tile.getCivilization() != null) s.append(tile.getCivilization().getCivilization().name());
        s.append("\n");
        s.append("Armed Unit Inside : ");
        if (tile.getArmedUnit() != null) s.append(tile.getArmedUnit().getType().name());
        s.append("\n");
        s.append("Civilian Unit Inside : ");
        if (tile.getCivilianUnit() != null) s.append(tile.getCivilianUnit().getType().name());
        s.append("\n");
        s.append("Resources : ");
        if (tile.getAvailableResource() != null) s.append(tile.getAvailableResource().name());
        s.append("\n");
        s.append("Improvement : ");
        if (tile.getImprovementInAction() != null) {
            s.append(tile.getImprovementInAction().name()).append(" ");
            s.append(tile.getImprovementInventoryState());
        }
        s.append("\n");
        s.append("Owner City : ");
        if (tile.getOwnerCity() != null) s.append(tile.getOwnerCity().getName());
        s.append("\n");
        return s.toString();
    }

    public String cheatSetFeature(Tile tile, TerrainFeature feature) {
        if (tile.getImprovementInAction() != null) return "you can't use this cheat on a tile containing improvement";
        if (tile.getAvailableResource() != null) return "you can't use cheat on a tile containing a resource";
        if (!tile.getTerrain().possibleFeatures.contains(feature))
            return "you can't use this feature on this tile's terrain";
        tile.setFeature(feature);
        return "God's grace has brought you the feature you desired.";
    }

    public String getPlayerInfo() {
        return "current player: " + game.getCurrentPlayer().getUser().getNickname();
    }

    public String cheatRemoveFogOfWar (Tile tile){
        game.getCurrentPlayer().getSavedMap().updateData(tile, Tile.VisibilityState.VISIBLE, tile.getTerrain(), tile.getFeature(), tile.getAvailableResource(), tile.getOwnerCity());
        return "see more... like it's gonna help you not be a newbie.";
    }




    public void yearCheck(){
        calculateScores();
        findLooser();
        if (game.getTurn() >= 4) {
            game.end();
        }else{
            for(Player p : game.getPlayers()){
                if(losers.contains(p)) continue;
                if(checkWinByOnlyHavingFirstCapital(p)){
                    game.end();
                    break;
                }
            }
        }
    }


    private int scoreCounter(Player player){
        int score = 0;
        // number of tiles
        int numberOfTiles = 0;
        for(City c : player.getCivilization().getCities())
            numberOfTiles += c.getTiles().size();
        score += numberOfTiles;
        // number of cities
        score += player.getCivilization().getCities().size() * 2;
        // population
        int population = 0;
        for(City c : player.getCivilization().getCities())
            population += c.getPopulation().size();
        score += population;
        // technologies
        for(TechnologyType t : TechnologyType.values()){
            if(player.getCivilization().getTechTree().isResearched(t))
                score += 2;
        }
        // map size
        score += game.getMap().getMapSize() * 2;
        return score;
    }

    private boolean hasFirstCapital(Player player){
        for(City c : player.getCivilization().getCities()){
            if(c.getName().equals(player.getCivilization().getCivilization().getCityNames()[0]))
                return true;
        }
        return false;
    }

    private boolean checkWinByOnlyHavingFirstCapital(Player player){
        if(!hasFirstCapital(player))
            return false;
        for(Player p : game.getPlayers()) {
            if(p.equals(player))
                continue;
            if(losers.contains(p)) continue;
            if(!firstCapitalBuiltYet(p))
                return false;
            if(hasFirstCapital(p))
                return false;
        }
        return true;
    }

    private boolean firstCapitalBuiltYet(Player g) {
        for (Player p : game.getPlayers()) {
            for (City c : p.getCivilization().getCities()) {
                if (c.getName().equals(g.getCivilization().getCivilization().getCityNames()[0]))
                    return true;
            }
        }
        return false;
    }

    private void initPlayers(){
        for(Player p : game.getPlayers()){
            scores.put(p, 0);
        }
    }

    private void calculateScores(){
        for(Player player : game.getPlayers()){
            if(losers.contains(player)) scores.replace(player,0);
            scores.replace(player,scoreCounter(player));
        }
    }


    public HashMap<Player, Integer> getScores() {
        return scores;
    }

    private void findLooser(){
        for(Player p : game.getPlayers()){
            if(losers.contains(p)) continue;
            else if(p.getCivilization().getCities().size() == 0 && p.getCivilization().getUnits().size() == 0){
                losers.add(p);
            }
        }
    }
}

