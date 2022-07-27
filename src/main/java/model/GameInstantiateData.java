package model;

import controller.*;
import controller.civilization.city.CityController;
import controller.unit.UnitController;
import controller.unit.WorkerController;
import lombok.Setter;
import view.components.MusicHandler;
import view.components.mapComponents.GameMapController;
import view.components.mapComponents.UserMapBuilder.MapUserBuilder;
import view.components.mapComponents.UserMapBuilder.PreBuiltMap;

import java.util.Vector;
@Setter
public class GameInstantiateData {
    private Vector<User> players = new Vector<>();
    private User creator;
    private Vector<User> invitedPlayers = new Vector<>();
    private int mapSize;
    private String id;
    private int preSetNumberOfUsers;
    private int autoSaveNumber = 0;

    public GameInstantiateData(User creator) {
        this.creator = creator;
        players.add(creator);
    }

    public void addPlayer(User user){
        players.add(user);
    }

    public void invitePlayer(User user){
        invitedPlayers.add(user);
        players.add(user); // in cherte hazf kon TODO
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
        game.setAutoSaveNumber(autoSaveNumber);
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
        MusicHandler.GAME.playMusic();
    }

    public static void loadGame(Game game){
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
        MusicHandler.GAME.playMusic();
    }

    public void startMapBuilder(){
        GameInitializer gameInitializer = new GameInitializer();
        Game game = gameInitializer.startGame(players, mapSize);
        game.setAutoSaveNumber(autoSaveNumber);
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

    public void loadBuiltMap(PreBuiltMap map){
        GameInitializer gameInitializer = new GameInitializer();
        Game game = gameInitializer.startGame(players, mapSize);
        game.setAutoSaveNumber(autoSaveNumber);
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
        MusicHandler.GAME.playMusic();
    }

    public void setMapSizeToPref(){
        mapSize = getMapPrefSize();
    }

    public Vector<User> getPlayers() {
        return players;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPreSetNumberOfUsers(int preSetNumberOfUsers) {
        this.preSetNumberOfUsers = preSetNumberOfUsers;
    }

    public String getId() {
        return id;
    }

    public int getPreSetNumberOfUsers() {
        return preSetNumberOfUsers;
    }
}
