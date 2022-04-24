package controller;

import model.Game;
import model.Map;
import model.Player;
import model.civilization.city.City;
import model.map.ConsoleMap;
import model.map.SavedMap;
import model.tile.Tile;
import model.unit.Unit;

import java.util.Vector;

public class MapController  extends Controller {
    Game game;
    public MapController(Game game) {
        this.game = game;
    }

    public String checkPosition(int x){
        int mapSize = game.getMap().getMapSize();
        if((x < 0) | (x >= (3 * mapSize * mapSize - 1) / 2))
            return "invalid position";
        return "valid position";
    }
    public void setMapOnCity(City city){
        // TODO : implement after adding city to map
    }
    public String moveMap() {
        //  TODO : IMPLEMENT BY VOROODI AMNAM
        String direction = "up"; // = ??
        int c = 2; // ==??
        if(!(direction.equals("up") || direction.equals("down")
        || direction.equals("left") || direction.equals("right")))
            return "invalid direction";
        moveCenterTile(c, direction);
        return "changed map position";
    }
    public void moveCenterTile(int c, String direction){
        Tile endPointTile = game.getCurrentPlayer().getMapCenterTile();
        int directionNumber = switch (direction) {
            case "left" -> 4;
            case "right" -> 1;
            case "up" -> 0;
            case "down" -> 3;
            default -> 2;
        };
        for(int i = 0 ; i < c; i++){
            if(endPointTile.getBoarderInfo(directionNumber).getOtherTile(endPointTile) != null)
                endPointTile = endPointTile.getBoarderInfo(directionNumber).getOtherTile(endPointTile);
            else
                break;

        }
        game.getCurrentPlayer().setMapCenterTile(endPointTile);
    }

//    public Vector<Vector<String>> getConsoleMapByNumber(int x){
//        // TODO : implement number to tile
//        Tile tile;
//        return getConsoleMap(tile);
//    }
    public Vector<Vector<String>> getConsoleMap(Tile currentTile){
        ConsoleMap newConsole = new ConsoleMap(game.getMap().getMapSize());
        setMap(currentTile, newConsole, game.getCurrentPlayer(), game.getMap());
        return newConsole.getScreenMap();
    }

    public void updateSavedMap(Player player, Vector<Tile> visibleTiles, Map map){
        SavedMap savedMap = player.getSavedMap();
        for(Tile tile : map.getTiles()){
            if(savedMap.getVisibilityState(tile).equals(Tile.VisibilityState.VISIBLE))
                savedMap.setVisibilityState(tile, Tile.VisibilityState.DISCOVERED);
        }
        for(Tile tile : visibleTiles){
            savedMap.updateData(tile, Tile.VisibilityState.VISIBLE, tile.getTerrain(), tile.getFeature(), tile.getAvailableResource());
        }
    }

    public void setMap(Tile centerTile, ConsoleMap showingMap, Player player, Map map){
        Vector<Tile> showingTiles = new Vector<>();
        showingTiles.add(centerTile);
        int maxCoordinate = map.getMapSize() - 1;
        int minSum = (map.getMapSize() - 1) / 2;
        int maxSum = 3 * minSum;
        for(int q = centerTile.getqCoordinate() - 2; q < centerTile.getqCoordinate() + 3; q++){
            for(int p = centerTile.getPCoordinate() - 2; p < centerTile.getPCoordinate() + 3; p++){
                if((q < 0) | (p < 0) | (q > maxCoordinate) | (p > maxCoordinate))
                    continue;
                if(p + q > maxSum)
                    continue;
                if(p + q < minSum)
                    continue;
                showingTiles.add(map.getTile(p, q));
            }
        }
        for(Tile tile : showingTiles){
            if(player.getSavedMap().getVisibilityState(tile).equals(Tile.VisibilityState.FOG_OF_WAR))
                addTileToShowingMap(showingMap, tile, player, centerTile);
        }
        for(Tile tile : showingTiles){
            if(!player.getSavedMap().getVisibilityState(tile).equals(Tile.VisibilityState.FOG_OF_WAR))
                addTileToShowingMap(showingMap, tile, player, centerTile);
        }
    }

    public void addTileToShowingMap(ConsoleMap showingMap, Tile tile, Player player, Tile centerTile){
        int xCoordinate = 26 + (tile.getPCoordinate() - centerTile.getPCoordinate()) * 10;
        int yCoordinate = 15 + (2*tile.getqCoordinate() + tile. getPCoordinate()- 2*centerTile.getqCoordinate() -2*centerTile.getPCoordinate()) * 6;
        if(player.getSavedMap().getVisibilityState(tile).equals(Tile.VisibilityState.FOG_OF_WAR)){
            addFogOfWar(showingMap, tile, xCoordinate, yCoordinate);
        }
        else if(player.getSavedMap().getVisibilityState(tile).equals(Tile.VisibilityState.DISCOVERED)){
            addDiscovered(showingMap, tile, player, xCoordinate, yCoordinate);
        }
        else if(player.getSavedMap().getVisibilityState(tile).equals(Tile.VisibilityState.VISIBLE))
            addVisible(showingMap, tile, xCoordinate, yCoordinate);
    }
    public void addFogOfWar(ConsoleMap showingMap, Tile tile, int xCoordinate, int yCoordinate){
        String fogOfWarColor = ConsoleMap.colorCharacter.FOG_OF_WAR.color + " " + ConsoleMap.colorCharacter.RESET.color;
        //number
        String number = ConsoleMap.colorCharacter.RED.setTextColor(String.format("%4d", tile.getMapNumber()));
        setNumber(number, showingMap, xCoordinate, yCoordinate);
        //background
        setBackground(fogOfWarColor, showingMap, xCoordinate, yCoordinate);
    }
    public void addDiscovered(ConsoleMap showingMap, Tile tile, Player player, int xCoordinate, int yCoordinate){
        String terrainColor = ConsoleMap.getRepresentation(player.getSavedMap().getTerrain(tile));
        String number = ConsoleMap.colorCharacter.RED.setTextColor(String.format("%4d", tile.getMapNumber())) ;
        // TODO Implement Cities in checkpoint2
        // resource
        if(player.getSavedMap().getResourceTypes(tile) != null){
            String resourceColor = ConsoleMap.getRepresentation(player.getSavedMap().getResourceTypes(tile));
            setResource(resourceColor, showingMap, xCoordinate, yCoordinate);
        }
        // number
        setNumber(number, showingMap, xCoordinate, yCoordinate);
        // feature
        if(player.getSavedMap().getFeature(tile) != null){
            String featureColor = ConsoleMap.getRepresentation(player.getSavedMap().getFeature(tile));
            setFeature(featureColor, showingMap, xCoordinate ,yCoordinate);
        }
        // terrain
        setBackground(terrainColor, showingMap, xCoordinate, yCoordinate);
        // boarder
        setBoarder(tile,showingMap,xCoordinate,yCoordinate);
    }
    public void addVisible(ConsoleMap showingMap, Tile tile, int xCoordinate, int yCoordinate){
        String terrainColor = ConsoleMap.getRepresentation(tile.getTerrain());
        // improvements
        setImprovement(tile, showingMap, xCoordinate, yCoordinate);
        // TODO Implement Cities in checkpoint2
        // resource
        if(tile.getAvailableResource() != null) {
            String resourceColor = ConsoleMap.getRepresentation(tile.getAvailableResource().getType());
            setResource(resourceColor, showingMap, xCoordinate, yCoordinate);
        }
        // number
        String number = ConsoleMap.colorCharacter.RED.setTextColor(String.format("%4d", tile.getMapNumber()));
        setNumber(number, showingMap, xCoordinate, yCoordinate);
        //units
        setUnits(tile, showingMap, xCoordinate ,yCoordinate);
        // feature
        if(tile.getFeature() != null){
            String featureColor = ConsoleMap.getRepresentation(tile.getFeature());
            setFeature(featureColor, showingMap, xCoordinate, yCoordinate);
        }
        // terrain
        setBackground(terrainColor, showingMap, xCoordinate, yCoordinate);
        // boarder
        setBoarder(tile, showingMap, xCoordinate, yCoordinate);
    }
    public void setBackground(String color, ConsoleMap showingMap, int xCoordinate, int yCoordinate){
        for(int i = xCoordinate - 3; i < (xCoordinate + 4); i++){
            for(int j = yCoordinate - 2; j < yCoordinate + 3; j++)
                showingMap.setScreenMapIndex(i, j, color + showingMap.getScreenMap().get(j).get(i));
        }
        int k = 0;
        for(int i = xCoordinate - 6; i < xCoordinate - 3; i++){
            for(int j = yCoordinate - k - 1; j <= yCoordinate + k; j++)
                showingMap.setScreenMapIndex(i, j, color + showingMap.getScreenMap().get(j).get(i));
            k++;
        }
        k = 0;
        for(int i = xCoordinate + 6; i > xCoordinate + 3; i--){
            for(int j = yCoordinate - k - 1; j <= yCoordinate + k; j++)
                showingMap.setScreenMapIndex(i, j, color + showingMap.getScreenMap().get(j).get(i));
            k++;
        }
    }
    public void setFeature(String name, ConsoleMap showMap, int xCoordinate, int yCoordinate){
        showMap.setScreenMapIndex(xCoordinate - 1,yCoordinate + 2, name);
        showMap.setScreenMapIndex(xCoordinate -1, yCoordinate + 3, ConsoleMap.colorCharacter.RESET.color);
    }

    private void setNumber(String number, ConsoleMap showMap, int xCoordinate, int yCoordinate){
        showMap.setScreenMapIndex(xCoordinate - 2, yCoordinate, number);
        showMap.setScreenMapIndex(xCoordinate - 1, yCoordinate, ConsoleMap.colorCharacter.RESET.color);
        showMap.setScreenMapIndex(xCoordinate, yCoordinate, ConsoleMap.colorCharacter.RESET.color);
        showMap.setScreenMapIndex(xCoordinate + 1, yCoordinate, ConsoleMap.colorCharacter.RESET.color);
    }

    private void setUnits(Tile tile, ConsoleMap showMap, int xCoordinate, int yCoordinate){
        Unit armedUnit = tile.getArmedUnit();
        Unit civilianUnit = tile.getCivilianUnit();
        if(armedUnit != null){
            String name = ConsoleMap.getRepresentation(armedUnit.getType());
            name = armedUnit.getOwnerCivilization().getCivilization().getColor() + name;
            showMap.setScreenMapIndex(xCoordinate - 4, yCoordinate - 1, name);
            showMap.setScreenMapIndex(xCoordinate - 3, yCoordinate - 1, ConsoleMap.colorCharacter.RESET.color );
        }
        if(civilianUnit != null){
            String name = ConsoleMap.getRepresentation(civilianUnit.getType());
            name = civilianUnit.getOwnerCivilization().getCivilization().getColor() + name;
            showMap.setScreenMapIndex(xCoordinate + 3, yCoordinate - 1, name);
            showMap.setScreenMapIndex(xCoordinate + 4, yCoordinate - 1, ConsoleMap.colorCharacter.RESET.color );
        }
    }

    private void setImprovement(Tile tile, ConsoleMap showMap, int xCoordinate, int yCoordinate){
        if(tile.getBuiltImprovement() == null)
            return;
        String name = ConsoleMap.getRepresentation(tile.getBuiltImprovement().getType());
        showMap.setScreenMapIndex(xCoordinate - 1, yCoordinate - 2, name);
        showMap.setScreenMapIndex(xCoordinate, yCoordinate - 2, ConsoleMap.colorCharacter.RESET.color);
    }
    private void setResource(String color, ConsoleMap showMap, int xCoordinate, int yCoordinate){
        showMap.setScreenMapIndex(xCoordinate - 1, yCoordinate - 1, color);
        showMap.setScreenMapIndex(xCoordinate, yCoordinate - 1, ConsoleMap.colorCharacter.RESET.color);
    }
    private void setBoarder(Tile tile, ConsoleMap showMap, int xCoordinate, int yCoordinate){
        boolean isRiver;
        isRiver = tile.getBoarderInfo(0).isRiver();
        String boarder = ConsoleMap.getRepresentation("_");
        if(isRiver)
            boarder = ConsoleMap.colorCharacter.BLUE_BACKGROUND + boarder;
        for(int x = xCoordinate - 3; x < xCoordinate + 4; x++){
            showMap.setScreenMapIndex(x, yCoordinate - 3, boarder);
        }
        isRiver = tile.getBoarderInfo(3).isRiver();
        boarder = ConsoleMap.getRepresentation("_");
        if(isRiver)
            boarder = ConsoleMap.colorCharacter.BLUE_BACKGROUND + boarder;
        for(int x = xCoordinate - 3; x < xCoordinate + 4; x++){
            showMap.setScreenMapIndex(x, yCoordinate + 3, boarder);
        }
        // set sides
        boarder = ConsoleMap.getRepresentation("/");
        if(tile.getBoarderInfo(5).isRiver())
            boarder = ConsoleMap.colorCharacter.BLUE_BACKGROUND + boarder;
        for(int i = 0; i < 3; i++){
            showMap.setScreenMapIndex(xCoordinate - 6 + i, yCoordinate - i, boarder);
        }
        boarder = ConsoleMap.getRepresentation("/");
        if(tile.getBoarderInfo(2).isRiver())
            boarder = ConsoleMap.colorCharacter.BLUE_BACKGROUND + boarder;
        for(int i = 0; i < 3; i++){
            showMap.setScreenMapIndex(xCoordinate + 6 - i, yCoordinate + i + 1, boarder);
        }
        boarder = ConsoleMap.getRepresentation("\\");
        if(tile.getBoarderInfo(1).isRiver())
            boarder = ConsoleMap.colorCharacter.BLUE_BACKGROUND + boarder;
        for(int i = 0; i < 3; i++){
            showMap.setScreenMapIndex(xCoordinate + 6 - i, yCoordinate - i, boarder);
        }
        boarder = ConsoleMap.getRepresentation("\\");
        if(tile.getBoarderInfo(4).isRiver())
            boarder = ConsoleMap.colorCharacter.BLUE_BACKGROUND + boarder;
        for(int i = 0; i < 3; i++){
            showMap.setScreenMapIndex(xCoordinate - 6 + i, yCoordinate + i + 1, boarder);
        }
    }

}
