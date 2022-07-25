package controller;

import model.Game;
import model.Player;
import model.User;
import model.civilization.Civilization;
import model.civilization.Civilizations;
import model.tile.Terrain;
import model.tile.Tile;
import model.unit.UnitType;
import model.unit.armed.Armed;
import model.unit.civilian.Settler;

import java.util.Random;
import java.util.Vector;

public class GameInitializer extends Controller{
    Game game;
    public Game startGame(Vector<User> users, int mapSize){
        initializeGame(mapSize);
        setGameData(users);
        return game;
    }
    private void initializeGame(int mapSize){
        this.game = new Game(new Vector<>(),mapSize);
        MapGenerationController mapGenerationController = new MapGenerationController(game);
        mapGenerationController.generateMap(mapSize);
    }
    private void setGameData(Vector<User> users){
        playerInitializer(users);
        civilizationInitializer();
        unitInitializer();
        game.setCurrentPlayer(game.getPlayers().get(0));
        game.getCurrentPlayer().setMapCenterTile(game.getCurrentPlayer().getCivilization().getUnits().get(0).getCurrentTile());
    }

    private void playerInitializer(Vector<User> users){
        for(User user : users){
            Player userPlayer = new Player(user);
            this.game.addPlayer(userPlayer);
            userPlayer.initializeSavedMap(game);
            userPlayer.setMapCenterTile(game.getMap().getTile((game.getMap().getMapSize() + 1)/2 , (game.getMap().getMapSize() + 1)/2));
        }
    }
    private void civilizationInitializer(){
        int i = 0;
        for(Player player : game.getPlayers()){
            Civilization civ = new Civilization(Civilizations.values()[i],null, player);
            i++;
            player.setCivilization(civ);
        }
    }
    private void unitInitializer(){
        Random random = new Random(58);
        Vector<Tile> freeLandList = new Vector<>(game.getMap().getReachableTiles());
        for(Player player : game.getPlayers()){
            Tile occupyTile = freeLandList.get(random.nextInt(freeLandList.size()));
            Armed firstWarrior = new Armed(UnitType.WARRIOR, occupyTile,player.getCivilization());
            Settler firstSettler = new Settler(UnitType.SETTLER, occupyTile, player.getCivilization());

            occupyTile.setArmedUnit(firstWarrior);
            occupyTile.setCivilianUnit(firstSettler);
            freeLandList.remove(occupyTile);
            player.setMapCenterTile(occupyTile);
        }
    }
}
