package model;
// TODO added MAP map and getter setter
// TODO added get current Player

import controller.GameMenuController;
import lombok.Getter;
import lombok.Setter;
import model.civilization.Civilization;
import model.civilization.Currency;
import model.civilization.Trade;
import model.information.NotificationBuilder;
import model.tile.TerrainFeature;
import model.tile.Tile;

import java.util.Vector;

@Getter
public class Game {
    private final Information informationPanel;
    Vector<Player> players;
    Vector<Trade> trades;
    Player currentPlayer;
    @Getter
    int turn = 0;
    private Object selectedObject;
    private Map map;
    private boolean isGameEnded = false;
    @Getter @Setter
    private int autoSaveNumber;
    public Game(Vector<Player> players, int mapSize) {
        //TODO : ADDED MAP FIRST INITIALIZE AND map size
        this.players = players;
        map = new Map(mapSize);
        NotificationBuilder.setGame(this);
        this.informationPanel = new Information(this);
    }

    public void nextTurn() {
        if (currentPlayer == players.get(players.size() - 1)) {
            decreaseWarTurns();
            turn++;
            if(autoSaveNumber > 0 && (turn % autoSaveNumber == 0))
                GameMenuController.saveToFile(this);
        }
        TurnBasedLogic.callNextTurns(currentPlayer.getCivilization());
        currentPlayer = players.get((players.indexOf(currentPlayer) + 1) % players.size());
        // ADDED BY PRCR
        NotificationBuilder.buildNotifications(getCurrentPlayer().getCivilization());
    }


    public Trade getTradeForCivilization(Civilization civilization) {
        return null; //TODO Implement here
    }

    //cheats
    public void increaseTurn(int amount) {
        this.turn += amount;
    }

    public void increaseCurrency(Currency currency, Civilization civilization) {
    }

    public void increaseScience(double amount, Civilization civilization) {
    }

    public void increaseHappiness(int amount, Civilization civilization) {
    }

    public void setFeature(Tile tile, TerrainFeature feature) {

    }

    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    // TODO: method ziro paak kon
    public void setCurrentPlayer(Player player) {
        this.currentPlayer = player;
    }

    public void addPlayer(Player player) {
        this.players.add(player);
    }

    public Vector<Player> getPlayers() {
        return players;
    }

    public Object getSelectedObject() {
        return selectedObject;
    }

    public void setSelectedObject(Object selectedObject) {
        this.selectedObject = selectedObject;
    }

    public void end(){
        isGameEnded = true;
    }

    public boolean isGameEnded(){
        return isGameEnded;
    }

    private void decreaseWarTurns() {
        for(Player p : getPlayers()){
            p.getCivilization().warsNextTurn();
        }
    }
}
