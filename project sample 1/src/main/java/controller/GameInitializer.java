package controller;

import model.Game;
import model.Player;
import model.User;
import model.tile.Terrain;
import model.tile.Tile;
import model.unit.Settler;
import model.unit.UnitType;

import java.util.Random;
import java.util.Vector;

public class GameInitializer{
    Game game;

    public GameInitializer(Game game) {
        this.game = game;
    }

    public void setUpCivilizations(Vector<Player> players){
        Vector<Player> playerVector = new Vector<>(players);
        Vector<Tile> possibleTiles = new Vector<Tile>(game.getMap().getReachableTiles());
        possibleTiles.removeIf(tile -> tile.getTerrain().equals(Terrain.MOUNTAIN));

        Random rand = new Random();
        int randomIndex;
        while(!playerVector.isEmpty()){
            randomIndex = rand.nextInt(possibleTiles.size());
            Player currentPlayer = playerVector.get(0);
            Tile foundingTile = possibleTiles.get(randomIndex);
            // TODO: Implement warrior
            Settler starter = new Settler(UnitType.SETTLER);
            // set settler and ... data
            // add settler to tile & civilization

            possibleTiles.remove(foundingTile);
            playerVector.remove(currentPlayer);
        }

    }
}
