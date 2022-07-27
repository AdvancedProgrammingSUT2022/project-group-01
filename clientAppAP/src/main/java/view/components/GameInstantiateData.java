package view.components;

import controller.*;
import controller.civilization.city.CityController;
import controller.unit.UnitController;
import controller.unit.WorkerController;
import model.Game;
import model.User;
import view.components.mapComponents.GameMapController;
import view.components.mapComponents.UserMapBuilder.MapUserBuilder;
import view.components.mapComponents.UserMapBuilder.PreBuiltMap;

import java.util.Vector;

public class GameInstantiateData {
    private Vector<User> players = new Vector<>();
    private User creator;
    private Vector<User> invitedPlayers = new Vector<>();
    private int mapSize;

    public GameInstantiateData(User creator) {
        this.creator = creator;
        players.add(creator);
    }

    public void addPlayer(User user){
        players.add(user);
    }

    public void invitePlayer(User user){
        invitedPlayers.add(user);
        players.add(user); // in cherte hazf komn TODO
    }

    public void removePlayer(User user){
        players.remove(user);
    }

    public void removeInvitationToPlayer(User user){
        invitedPlayers.remove(user);
    }

    public int getMapSize() {
        return mapSize;
    }

    public void setMapSize(int mapSize) {
        this.mapSize = mapSize;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public boolean userIsInvited(User user){
        return invitedPlayers.contains(user);
    }

    public boolean isUserInGame(User user){
        return players.contains(user);
    }

    public int numberOfPlayersReady(){
        return players.size();
    }

    public int getMapPrefSize(){
        return players.size() * 2 + 1;
    }

    public void changeMapSize(int i){
        mapSize += i;
    }

    public void startGame() {
        GameInitializer gameInitializer = new GameInitializer();
        Game game = gameInitializer.startGame(players, mapSize);
//        ProgramController.setCurrentMenu(Menus.GAME_MENU);
        ProgramController.setGame(game);
        MapController mapController = new MapController(game);
        GameController gameController = new GameController(game, mapController);
        CityController cityController = new CityController(game);
        UnitController unitController = new UnitController(game);
        WorkerController workerController = new WorkerController(game);
        GameMenuController gameMenuController = new GameMenuController(game,gameController,cityController, unitController, workerController);
        ProgramController.setCurrentMenu(Menus.GAME_MENU);
        GameMapController gameMapController = new GameMapController(gameMenuController);
        GUIController.changeMenuManually(gameMapController.getBackground());
    }

    public static void startGameStatic(Game game){
        ProgramController.setGame(game);
        MapController mapController = new MapController(game);
        GameController gameController = new GameController(game, mapController);
        CityController cityController = new CityController(game);
        UnitController unitController = new UnitController(game);
        WorkerController workerController = new WorkerController(game);
        GameMenuController gameMenuController = new GameMenuController(game,gameController,cityController, unitController, workerController);
        ProgramController.setCurrentMenu(Menus.GAME_MENU);
        GameMapController gameMapController = new GameMapController(gameMenuController);
        GUIController.changeMenuManually(gameMapController.getBackground());
    }

    public void startMapBuilder(){
        GameInitializer gameInitializer = new GameInitializer();
        Game game = gameInitializer.startGame(players, mapSize);
//        ProgramController.setCurrentMenu(Menus.GAME_MENU);
        ProgramController.setGame(game);
        MapController mapController = new MapController(game);
        GameController gameController = new GameController(game, mapController);
        CityController cityController = new CityController(game);
        UnitController unitController = new UnitController(game);
        WorkerController workerController = new WorkerController(game);
        GameMenuController gameMenuController = new GameMenuController(game,gameController,cityController, unitController, workerController);
        //GameMapController gameMapController = new GameMapController(gameMenuController);
        //GUIController.changeMenuManually(gameMapController.getBackPane());
        MapUserBuilder mapUserBuilder = new MapUserBuilder(gameMenuController);
        GUIController.changeMenuManually(mapUserBuilder.getBackPane());
    }

    public void loadMap(PreBuiltMap map){
        GameInitializer gameInitializer = new GameInitializer();
        Game game = gameInitializer.startGame(players, mapSize);
//        ProgramController.setCurrentMenu(Menus.GAME_MENU);
        ProgramController.setGame(game);
        MapController mapController = new MapController(game);
        GameController gameController = new GameController(game, mapController);
        CityController cityController = new CityController(game);
        UnitController unitController = new UnitController(game);
        WorkerController workerController = new WorkerController(game);
        GameMenuController gameMenuController = new GameMenuController(game,gameController,cityController, unitController, workerController);
        GameMapController gameMapController = new GameMapController(gameMenuController);
        for(int i = 0; i < map.getTiles().length; i++){
            for(int j = 0; j < map.getTiles()[i].length; j++){
                if(map.getTiles()[i][j] != null && map.getTiles()[i][j].getTerrain() != null) {
                    game.getMap().getTile(i, j).setTerrain(map.getTiles()[i][j].getTerrain());
                    game.getMap().getTile(i,j).setFeature(map.getTiles()[i][j].getFeature());
                    game.getMap().getTile(i,j).setAvailableResource(map.getTiles()[i][j].getResourceType());
                }
            }
        }
        GUIController.changeMenuManually(gameMapController.getBackground());
    }

    public void setMapSizeToPref(){
        mapSize = getMapPrefSize();
    }

    public Vector<User> getPlayers() {
        return players;
    }
}
