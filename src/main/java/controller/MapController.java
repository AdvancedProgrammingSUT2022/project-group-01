package controller;

import model.Game;
import model.Map;
import model.Player;
import model.ProgressState;
import model.map.ConsoleMap;
import model.map.SavedMap;
import model.tile.Tile;
import model.unit.Unit;
import org.mockito.Mock;

import java.util.Vector;

public class MapController extends Controller {
    Game game;

    public MapController(Game game) {
        this.game = game;
    }

    public String setPosition(int x) {
        int mapSize = game.getMap().getMapSize();
        if ((x < 0) | (x >= mapSize * mapSize)) return "invalid position";
        Tile mysteryTile;
        if ((mysteryTile = game.getMap().getTileByNumber(x)) == null) return "invalid position";
        game.getCurrentPlayer().setMapCenterTile(mysteryTile);
        return "position is set";
    }

    public String moveMap(String direction, int c) {
        //  TODO : IMPLEMENT BY VOROODI AMNAM
        if (!(direction.equals("up") || direction.equals("down") || direction.equals("left") || direction.equals("right")))
            return "invalid direction";
        moveCenterTile(c, direction);
        return "changed map position";
    }

    public void moveCenterTile(int c, String direction) {
        int directionNumber;
        switch(direction){
            case "left" : directionNumber = 4;break;
            case "right" : directionNumber = 1;break;
            case "up" : directionNumber = 0;break;
            case "down" : directionNumber = 3;break;
            default: directionNumber = 2;break;
        }
        Tile endPointTile = game.getCurrentPlayer().getMapCenterTile();

        for (int i = 0; i < c; i++) {
            if (endPointTile.getBoarderInfo(directionNumber).getOtherTile(endPointTile) != null)
                endPointTile = endPointTile.getBoarderInfo(directionNumber).getOtherTile(endPointTile);
            else break;

        }
        game.getCurrentPlayer().setMapCenterTile(endPointTile);
    }


    public String getConsoleMap(Tile currentTile) {
        ConsoleMap newConsole = new ConsoleMap(game.getMap().getMapSize());
        setMap(currentTile, newConsole, game.getCurrentPlayer(), game.getMap());
        StringBuilder output = new StringBuilder();
        for(Vector<String> v : newConsole.getScreenMap()){
            for(String s : v){
                output.append(s);
            }
            output.append("\n");
        }
        return output.toString();
    }

    public void updateCurrentPlayersMap(){
        updateSavedMap(game.getCurrentPlayer(), game.getCurrentPlayer().getCivilization().visibleTiles(), game.getMap());
    }

    public void updateSavedMap(Player player, Vector<Tile> visibleTiles, Map map) {
        SavedMap savedMap = player.getSavedMap();
        for (Tile tile : map.getTiles()) {
            if (savedMap.getVisibilityState(tile).equals(Tile.VisibilityState.VISIBLE))
                savedMap.setVisibilityState(tile, Tile.VisibilityState.DISCOVERED);
        }
        for (Tile tile : visibleTiles) {
            savedMap.updateData(tile, Tile.VisibilityState.VISIBLE, tile.getTerrain(), tile.getFeature(), tile.getAvailableResource(),tile.getOwnerCity());
        }
    }

    private void setMap(Tile centerTile, ConsoleMap showingMap, Player player, Map map) {
        Vector<Tile> showingTiles = new Vector<>();
        showingTiles.add(centerTile);
        int maxCoordinate = map.getMapSize() - 1;
        int minSum = (map.getMapSize() - 1) / 2;
        int maxSum = 3 * minSum;

        for (int q = centerTile.getQCoordinate() - 2; q < centerTile.getQCoordinate() + 3; q++) {
            for (int p = centerTile.getPCoordinate() - 2; p < centerTile.getPCoordinate() + 3; p++) {
                if ((q < 0) | (p < 0) | (q > maxCoordinate) | (p > maxCoordinate)) continue;
                if (p + q > maxSum) continue;
                if (p + q < minSum) continue;
                if (Math.abs(p + q - centerTile.getPCoordinate() - centerTile.getQCoordinate()) > 2) continue;
                showingTiles.add(map.getTile(p, q));
            }
        }
        for (Tile tile : showingTiles) {
                addTileToShowingMap(showingMap, tile, player, centerTile);
        }
    }

    private void addTileToShowingMap(ConsoleMap showingMap, Tile tile, Player player, Tile centerTile) {
        int xCoordinate = 26 + (tile.getPCoordinate() - centerTile.getPCoordinate() + tile.getQCoordinate() - centerTile.getQCoordinate()) * 10;
        int yCoordinate = 15 + (tile.getQCoordinate() - tile.getPCoordinate() - centerTile.getQCoordinate() + centerTile.getPCoordinate()) * 3;
        if (player.getSavedMap().getVisibilityState(tile).equals(Tile.VisibilityState.FOG_OF_WAR)) {
            addFogOfWar(showingMap, tile, xCoordinate, yCoordinate);
        } else if (player.getSavedMap().getVisibilityState(tile).equals(Tile.VisibilityState.DISCOVERED)) {
            addDiscovered(showingMap, tile, player, xCoordinate, yCoordinate);
        } else if (player.getSavedMap().getVisibilityState(tile).equals(Tile.VisibilityState.VISIBLE))
            addVisible(showingMap, tile, xCoordinate, yCoordinate);
    }

    private void addFogOfWar(ConsoleMap showingMap, Tile tile, int xCoordinate, int yCoordinate) {
        String fogOfWarColor = ConsoleMap.colorCharacter.FOG_OF_WAR.color;
        //number
        String number = ConsoleMap.colorCharacter.WHITE_BOLD.setTextColor(String.format("%4d", tile.getMapNumber()));
        setNumber(number, showingMap, xCoordinate, yCoordinate);
        //coordinates
        String x = ConsoleMap.colorCharacter.BLACK.setTextColor(String.format("%2d", tile.getPCoordinate() + tile.getQCoordinate()));
        String y = ConsoleMap.colorCharacter.BLACK.setTextColor(String.format("%2d", tile.getQCoordinate() - tile.getPCoordinate() + (game.getMap().getMapSize() - 1)));
        setCoordinates(x,y,showingMap,xCoordinate,yCoordinate);
        //background
        setBackground(fogOfWarColor, showingMap, xCoordinate, yCoordinate);
        setBoarder(tile, showingMap, xCoordinate, yCoordinate);
    }

    private void addDiscovered(ConsoleMap showingMap, Tile tile, Player player, int xCoordinate, int yCoordinate) {
        String terrainColor = ConsoleMap.getRepresentation(player.getSavedMap().getTerrain(tile));
        String number = ConsoleMap.colorCharacter.WHITE_BOLD.setTextColor(String.format("%4d", tile.getMapNumber()));

        // resource
        if ((player.getSavedMap().getResourceTypes(tile) != null) && (tile.getAvailableResource().isVisible(game.getCurrentPlayer().getCivilization()))) {
            String resourceColor = ConsoleMap.getRepresentation(player.getSavedMap().getResourceTypes(tile));
            setResource(resourceColor, showingMap, xCoordinate, yCoordinate);
        }
        // number
        setNumber(number, showingMap, xCoordinate, yCoordinate);
        //coordinates
        String x = ConsoleMap.colorCharacter.BLACK.setTextColor(String.format("%2d",2 * tile.getPCoordinate() + tile.getQCoordinate()));
        String y = ConsoleMap.colorCharacter.BLACK.setTextColor(String.format("%2d", 2 * tile.getQCoordinate() + tile.getPCoordinate()));
        setCoordinates(x,y,showingMap,xCoordinate,yCoordinate);
        // feature
        if (player.getSavedMap().getFeature(tile) != null) {
            String featureColor = ConsoleMap.getRepresentation(player.getSavedMap().getFeature(tile));
            setFeature(featureColor, showingMap, xCoordinate, yCoordinate);
        }
        // terrain
        setBackground(terrainColor, showingMap, xCoordinate, yCoordinate);
        // boarder
        setBoarder(tile, showingMap, xCoordinate, yCoordinate);
    }

    private void addVisible(ConsoleMap showingMap, Tile tile, int xCoordinate, int yCoordinate) {
        String terrainColor = ConsoleMap.getRepresentation(tile.getTerrain());
        // improvements
        setImprovement(tile, showingMap, xCoordinate, yCoordinate);
        // city
        if(tile.getOwnerCity() != null){
            String cityName = tile.getOwnerCity().getName();
            cityName = cityName.substring(0,2);
            String cityColor = tile.getOwnerCity().getCivilization().getCivilization().getColor().setTextColor(cityName);
            setCity(cityColor, showingMap, xCoordinate, yCoordinate);
        }
        // resource
        if ((tile.getAvailableResource() != null) && (tile.getAvailableResource().isVisible(game.getCurrentPlayer().getCivilization()))) {
            String resourceColor = ConsoleMap.getRepresentation(tile.getAvailableResource());
            setResource(resourceColor, showingMap, xCoordinate, yCoordinate);
        }
        // number
        String number;
        if((tile.getOwnerCity()!=null) && tile.getOwnerCity().getCenterTile().equals(tile))
            number = tile.getOwnerCity().getCivilization().getCivilization().getColor().setTextColor(String.format("%4d", tile.getMapNumber()));
        else
            number = ConsoleMap.colorCharacter.WHITE_BOLD.setTextColor(String.format("%4d", tile.getMapNumber()));
        setNumber(number, showingMap, xCoordinate, yCoordinate);
        //coordinates
        String x = ConsoleMap.colorCharacter.BLACK.setTextColor(String.format("%2d",2 * tile.getPCoordinate() + tile.getQCoordinate()));
        String y = ConsoleMap.colorCharacter.BLACK.setTextColor(String.format("%2d", 2 * tile.getQCoordinate() + tile.getPCoordinate()));
        setCoordinates(x,y,showingMap,xCoordinate,yCoordinate);
        //units
        setUnits(tile, showingMap, xCoordinate, yCoordinate);
        // feature
        if (tile.getFeature() != null) {
            String featureColor = ConsoleMap.getRepresentation(tile.getFeature());
            setFeature(featureColor, showingMap, xCoordinate, yCoordinate);
        }
        // terrain
        setBackground(terrainColor, showingMap, xCoordinate, yCoordinate);
        // boarder
        setBoarder(tile, showingMap, xCoordinate, yCoordinate);
    }

    private void setBackground(String color, ConsoleMap showingMap, int xCoordinate, int yCoordinate) {
        for (int i = xCoordinate - 3; i < (xCoordinate + 4); i++) {
            for (int j = yCoordinate - 2; j < yCoordinate + 3; j++) {
                showingMap.setScreenMapIndex(i, j, color + showingMap.getScreenMap().get(j).get(i) + ConsoleMap.colorCharacter.RESET.color);
            }
        }
        int k = 0;
        for (int i = xCoordinate - 5; i < xCoordinate - 3; i++) {
            for (int j = yCoordinate - k - 1; j <= yCoordinate + k; j++)
                showingMap.setScreenMapIndex(i, j, color + showingMap.getScreenMap().get(j).get(i) + ConsoleMap.colorCharacter.RESET.color);
            k++;
        }
        k = 0;
        for (int i = xCoordinate + 5; i > xCoordinate + 3; i--) {
            for (int j = yCoordinate - k - 1; j <= yCoordinate + k; j++)
                showingMap.setScreenMapIndex(i, j, color + showingMap.getScreenMap().get(j).get(i) + ConsoleMap.colorCharacter.RESET.color);
            k++;
        }
    }

    private void setFeature(String name, ConsoleMap showMap, int xCoordinate, int yCoordinate) {
        showMap.setScreenMapIndex(xCoordinate - 1, yCoordinate + 2, name);
        showMap.setScreenMapIndex(xCoordinate, yCoordinate + 2, ConsoleMap.colorCharacter.RESET.color);
    }

    private void setNumber(String number, ConsoleMap showMap, int xCoordinate, int yCoordinate) {
        showMap.setScreenMapIndex(xCoordinate - 2, yCoordinate, number);
        showMap.setScreenMapIndex(xCoordinate - 1, yCoordinate, ConsoleMap.colorCharacter.RESET.color);
        showMap.setScreenMapIndex(xCoordinate, yCoordinate, ConsoleMap.colorCharacter.RESET.color);
        showMap.setScreenMapIndex(xCoordinate + 1, yCoordinate, ConsoleMap.colorCharacter.RESET.color);
    }

    private void setCoordinates(String x, String y, ConsoleMap showMap, int xCoordinate, int yCoordinate){
        showMap.setScreenMapIndex(xCoordinate - 5, yCoordinate, x);
        showMap.setScreenMapIndex(xCoordinate + 4, yCoordinate, y);
        showMap.setScreenMapIndex(xCoordinate - 4,yCoordinate,ConsoleMap.colorCharacter.RESET.color);
        showMap.setScreenMapIndex(xCoordinate + 5,yCoordinate,ConsoleMap.colorCharacter.RESET.color);
    }

    private void setUnits(Tile tile, ConsoleMap showMap, int xCoordinate, int yCoordinate) {
        Unit armedUnit = tile.getArmedUnit();
        Unit civilianUnit = tile.getCivilianUnit();
        if (armedUnit != null) {
            String name = ConsoleMap.getRepresentation(armedUnit.getType());
            name = armedUnit.getOwnerCivilization().getCivilization().getColor().setTextColor(name);
            showMap.setScreenMapIndex(xCoordinate - 4, yCoordinate - 1, name);
            showMap.setScreenMapIndex(xCoordinate - 3, yCoordinate - 1, ConsoleMap.colorCharacter.RESET.color);
        }
        if (civilianUnit != null) {
            String name = ConsoleMap.getRepresentation(civilianUnit.getType());
            name = civilianUnit.getOwnerCivilization().getCivilization().getColor().setTextColor(name);
            showMap.setScreenMapIndex(xCoordinate + 3, yCoordinate - 1, name);
            showMap.setScreenMapIndex(xCoordinate + 4, yCoordinate - 1, ConsoleMap.colorCharacter.RESET.color);
        }
    }

    private void setImprovement(Tile tile, ConsoleMap showMap, int xCoordinate, int yCoordinate) {
//        if (tile.getBuiltImprovement() == null) return;
//        String name = ConsoleMap.getRepresentation(tile.getBuiltImprovement());
//        showMap.setScreenMapIndex(xCoordinate - 1, yCoordinate - 2, name);
//        showMap.setScreenMapIndex(xCoordinate, yCoordinate - 2, ConsoleMap.colorCharacter.RESET.color);
        if (tile.getBuiltImprovement() != null) {
            String name = ConsoleMap.getRepresentation(tile.getBuiltImprovement());
            showMap.setScreenMapIndex(xCoordinate - 1, yCoordinate - 2, name);
            showMap.setScreenMapIndex(xCoordinate, yCoordinate - 2, ConsoleMap.colorCharacter.RESET.color);
        }else if(tile.getImprovementInventoryState().equals(ProgressState.DAMAGED)){
            if(tile.getImprovementInventory().getImprovement() != null){
                String name = ConsoleMap.getRepresentation("_");
                showMap.setScreenMapIndex(xCoordinate - 1, yCoordinate - 2, name);
            }
        }
    }

    private void setResource(String color, ConsoleMap showMap, int xCoordinate, int yCoordinate) {
        showMap.setScreenMapIndex(xCoordinate - 1, yCoordinate - 1, color);
        showMap.setScreenMapIndex(xCoordinate, yCoordinate - 1, ConsoleMap.colorCharacter.RESET.color);
    }
    private void setCity(String colorName, ConsoleMap showMap, int xCoordinate, int yCoordinate){
        showMap.setScreenMapIndex(xCoordinate - 1, yCoordinate + 1, colorName);
        showMap.setScreenMapIndex(xCoordinate, yCoordinate + 1, ConsoleMap.colorCharacter.RESET.color);
    }

    private void setBoarder(Tile tile, ConsoleMap showMap, int xCoordinate, int yCoordinate) {
        boolean isRiver;
        boolean isTileFogOfWar = game.getCurrentPlayer().getSavedMap().getVisibilityState(tile).equals(Tile.VisibilityState.FOG_OF_WAR);
        isRiver = tile.getBoarderInfo(0).isRiver();
        String boarder = ConsoleMap.getRepresentation("_");
        if (isRiver &&(!isTileFogOfWar)) boarder = ConsoleMap.getRepresentation("_R");
        for (int x = xCoordinate - 3; x < xCoordinate + 4; x++) {
            showMap.setScreenMapIndex(x, yCoordinate - 3, boarder);
        }
        isRiver = tile.getBoarderInfo(3).isRiver();
        boarder = ConsoleMap.getRepresentation("_");
        if (isRiver &&(!isTileFogOfWar)) boarder = ConsoleMap.getRepresentation("_R");
        for (int x = xCoordinate - 3; x < xCoordinate + 4; x++) {
            showMap.setScreenMapIndex(x, yCoordinate + 3, boarder);
        }
        // set sides
        boarder = ConsoleMap.getRepresentation("/");
        if (tile.getBoarderInfo(5).isRiver() &&(!isTileFogOfWar)) boarder = ConsoleMap.getRepresentation("/R");
        for (int i = 0; i < 3; i++) {
            showMap.setScreenMapIndex(xCoordinate - 6 + i, yCoordinate - i, boarder);
        }
        boarder = ConsoleMap.getRepresentation("/");
        if (tile.getBoarderInfo(2).isRiver() && (!isTileFogOfWar)) boarder = ConsoleMap.getRepresentation("/R");
        for (int i = 0; i < 3; i++) {
            showMap.setScreenMapIndex(xCoordinate + 6 - i, yCoordinate + i + 1, boarder);
        }
        boarder = ConsoleMap.getRepresentation("\\");
        if (tile.getBoarderInfo(1).isRiver() && (!isTileFogOfWar)) boarder = ConsoleMap.getRepresentation("\\R");
        for (int i = 0; i < 3; i++) {
            showMap.setScreenMapIndex(xCoordinate + 6 - i, yCoordinate - i, boarder);
        }
        boarder = ConsoleMap.getRepresentation("\\");
        if (tile.getBoarderInfo(4).isRiver() && (!isTileFogOfWar)) boarder = ConsoleMap.getRepresentation("\\R");
        for (int i = 0; i < 3; i++) {
            showMap.setScreenMapIndex(xCoordinate - 6 + i, yCoordinate + i + 1, boarder);
        }

    }

}
