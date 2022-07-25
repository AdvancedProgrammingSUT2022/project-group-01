package model;
// TODO added MAP map and getter setter
// TODO added get current Player

import com.thoughtworks.xstream.XStream;
import lombok.Getter;
import model.civilization.Civilization;
import model.civilization.Currency;
import model.civilization.Trade;
import model.information.NotificationBuilder;
import model.tile.TerrainFeature;
import model.tile.Tile;
import utils.Converter;

import java.util.Vector;

@Getter
public class Game {
	private final Information informationPanel;
	private Map map;
	Vector<Player> players;
	Vector<Trade> trades;
	Player currentPlayer;
	@Getter
	int turn = 0;
	private Object selectedObject;
	private Vector<Object> allObjects = new Vector<>();

	public void addObject(Identifiable identifiable) {
		identifiable.setId(allObjects.size());
		allObjects.add(identifiable);
	}

	public Object getObject(int id) {
		return allObjects.get(id);
	}

	public Game(Vector<Player> players, int mapSize) {
		//TODO : ADDED MAP FIRST INITIALIZE AND map size
		this.players = players;
		map = new Map(mapSize);
		NotificationBuilder.setGame(this);
		this.informationPanel = new Information(this);
	}

	public void nextTurn() {
		if (currentPlayer == players.get(players.size() - 1))
			turn++;
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

	public void notifyUsers() {
		XStream xStream = new XStream();
		String xml = Converter.toXML(this);
		for (Player player : players) {
			player.getUser().sendMessage(xml);
		}
	}
}
