package controller;

import model.Game;
import model.Player;
import model.tile.Terrain;
import model.tile.Tile;
import model.unit.civilian.Settler;
import model.unit.UnitType;

import java.util.Random;
import java.util.Vector;

public class GameInitializer extends Controller{
    Game game;
    public GameInitializer(Game game) {
        this.game = game;
    }

    public void setUpCivilizations(Vector<Player> players){
        Vector<Player> playerVector = new Vector<>(players);
        Vector<Tile> possibleTiles = new Vector<>(game.getMap().getReachableTiles());
        possibleTiles.removeIf(tile -> tile.getTerrain().equals(Terrain.MOUNTAIN));

        Random rand = new Random();
        int randomIndex;
        while(!playerVector.isEmpty()){
            randomIndex = rand.nextInt(possibleTiles.size());
            Player currentPlayer = playerVector.get(0);
            Tile foundingTile = possibleTiles.get(randomIndex);
            // TODO: Implement warrior
            //TODO check settler args
            Settler starter = new Settler(UnitType.SETTLER,foundingTile, currentPlayer.getCivilization(),game);
            // set settler and ... data
            // add settler to tile & civilization

            possibleTiles.remove(foundingTile);
            playerVector.remove(currentPlayer);
        }

    }
}
