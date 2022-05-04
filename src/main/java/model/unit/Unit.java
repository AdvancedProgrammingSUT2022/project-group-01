package model.unit;

import model.Game;
import model.Map;
import model.civilization.Civilization;
import model.civilization.city.City;
import model.technology.TechnologyList;
import model.tile.Boarder;
import model.tile.Tile;
import model.unit.action.*;
import model.unit.armed.Armed;
import model.unit.civilian.Civilian;
import model.unit.trait.TraitsList;
import utils.OrderedPair;

import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Vector;

public class Unit{
	public final static int maxHealth = 10;
	protected Civilization ownerCivilization;
	private int health;
	private int movementPoint;
	private TechnologyList requiredTechnologies;
	private int cost;
	protected Tile currentTile;
	private boolean isHealing;
	private Game game;
	private UnitType unitType;
	protected ActionsQueue actionsQueue;

	public Unit(UnitType type, Tile tile, Civilization civilization, Game game) {
		this.health = maxHealth;
		this.movementPoint = type.getMovement();
		this.requiredTechnologies = type.getRequiredTechs();
		this.currentTile = tile;
		this.isHealing = false;
		this.ownerCivilization = civilization;
		this.game = game;
		actionsQueue = new ActionsQueue();
		unitType = type;
	}

	public void produceUnit(UnitType type, City city){
		// TODO

	}

	public UnitType getType() {
		return unitType;
	}

	public Tile getCurrentTile() {
		return currentTile;
	}

	public boolean nextTurn(Civilization civilization, City city) {

		return false;
	}

	public void defendAgainstMelee(Unit enemy) {
		// TODO implement this
	}

	public void defendAgainstRanged(Unit enemy) {
		// TODO implement this
	}

	public void moveTo(Tile tile) {}

	public TraitsList getTraitsList(){
		return unitType.getUnitTraits();
	}

	public int getHealth() {
		return health;
	}

	/**
	 * kill unit immediately
	 */
	public void suicide() {
		currentTile.removeUnit(this);
		changeHealth(-getHealth());
	}

	/**
	 * to change unit health
	 *
	 * @param deltaHealth change amount of health,
	 *                       positive for increase and negative for decrease
	 */
	public void changeHealth(int deltaHealth){
		health += deltaHealth;
	}

	private Vector<Tile> dijkstra(Tile destination) {
		Map gameMap = game.getMap();//TODO get user map in next checkpoint

		PriorityQueue<OrderedPair<Distance, Integer>> heap = new PriorityQueue<>();
		int mapSize = gameMap.getMapSize();
		Vector<Distance> dis = new Vector<>(mapSize);
		Vector<Integer> par = new Vector<>(mapSize);

		for (int i = 0; i < mapSize; i++) {
			dis.add(new Distance(Distance.infinity, -1));
			par.add(-1);
		}

		int start = currentTile.getMapNumber();
		dis.set(start, new Distance(0, 0));
		heap.add(new OrderedPair<>(dis.get(start), start));

		while (!heap.isEmpty()) {
			OrderedPair<Distance, Integer> min = heap.poll();
			Distance distance = min.getFirst();
			Tile node = gameMap.getTileByNumber(min.getSecond());

			for (int i = 0; i < 6; i++) {
				Boarder boarder = node.getBoarder(i);
				Tile adjacentTile = boarder.getOtherTile(node);
				if (adjacentTile == null) continue;
				Distance nextDistance = distance.getDistanceAfter(currentTile, boarder);
				int adjacentTileID = adjacentTile.getMapNumber();

				if (dis.get(adjacentTileID).compareTo(nextDistance) > 0) {
					par.set(adjacentTileID, node.getMapNumber());

					heap.remove(new OrderedPair<>(dis.get(adjacentTileID), adjacentTileID));
					dis.set(adjacentTileID, nextDistance);
					heap.add(new OrderedPair<>(dis.get(adjacentTileID), adjacentTileID));
				}
			}
		}
		if (par.get(destination.getMapNumber()) == -1) {
			return null;
		}

		Vector<Tile> path = new Vector<>();
		while (currentTile.getMapNumber() != destination.getMapNumber()) {
			path.add(destination);
			destination = gameMap.getTileByNumber(par.get(destination.getMapNumber()));
		}
		Collections.reverse(path);
		Vector<Tile> stopTiles = new Vector<>();
		for(int i = 1; i + 1 < path.size(); i++)
			if(dis.get(path.get(i).getMapNumber()).getTurn() < dis.get(path.get(i + 1).getMapNumber()).getTurn())
				stopTiles.add(path.get(i));
		stopTiles.add(destination);
		return stopTiles;
	}

	public Civilization getOwnerCivilization() {
		return ownerCivilization;
	}

	private class Distance implements Comparable<Distance> {
		public static final int infinity = 10000000;
		private int turn;
		private int remainedMP;

		public Distance(int turn, int remainedMP) {
			this.turn = turn;
			this.remainedMP = remainedMP;
		}

		public int getRemainedMP() {
			return remainedMP;
		}

		public int getTurn() {
			return turn;
		}

		public Distance getDistanceAfter(Tile currentTile, Boarder boarder) {
			int resultTurn = turn;
			int resultRemainedMP = remainedMP;
			Tile nextTile = boarder.getOtherTile(currentTile);
			if (remainedMP == 0) {
				if ((Unit.this instanceof Armed) && currentTile.getArmedUnit() != null)
					return new Distance(infinity, -1);
				if ((Unit.this instanceof Civilian) && currentTile.getCivilianUnit() != null)
					return new Distance(infinity, -1);
				resultTurn++;
				resultRemainedMP = movementPoint;
			}
			resultRemainedMP = Math.max(0, resultRemainedMP - (boarder.isRiver() ? resultRemainedMP : nextTile.getMovementCost()));
			return new Distance(resultTurn, resultRemainedMP);
		}

		@Override
		public int compareTo(Distance o) {
			if (turn != o.getTurn())
				return Integer.compare(turn, o.getTurn());
			return -Integer.compare(remainedMP, o.getRemainedMP());
		}
	}

	/**
	 * check that there is any enemy close to unit or not
	 *
	 * @return true if any enemy is visible by this unit, false otherwise
	 */
	public boolean isEnemyNear(){
		Vector<Tile> sight = currentTile.getSight(2);
		for (Tile tile : sight) {
			if(tile.getArmedUnit().getOwnerCivilization() != ownerCivilization)
				return true;
		}
		return false;
	}

	public boolean isReachable(Tile destTile){
		return dijkstra(destTile) != null;
	}

	// Commands

	/**
	 * fortify unit until complete healing
	 */
	public void fortifyUntilHeal() {
		actionsQueue.resetQueue();
		actionsQueue.addAction(new Action(this, Actions.FORTIFY_UNTIL_HEAL));
	}

	/**
	 * fortify unit for 1 turn
	 */
	public void fortify(){
		actionsQueue.resetQueue();
		actionsQueue.addAction(new Action(this, Actions.FORTIFY));
	}

	/**
	 * disable unit until cancel
	 */
	public void sleep(){
		actionsQueue.resetQueue();
		actionsQueue.addAction(new Action(this, Actions.SLEEP));
	}

	/**
	 * wake unit up
	 */
	public void wake(){
		actionsQueue.resetQueue();
	}

	/**
	 * change unit mode to alert
	 */
	public void alert(){
		actionsQueue.resetQueue();
		actionsQueue.addAction(new Action(this, Actions.ALERT));
	}

	/**
	 * cancel all queued actions
	 */
	public void cancel(){
		actionsQueue.resetQueue();
	}

	/**
	 * move this unit to destination
	 * @param destTile is destination of this unit
	 */
	public void goTo(Tile destTile){
		Vector<Tile> stopPoints = dijkstra(destTile);
		// TODO : handle no path condition
		assert stopPoints != null;

		actionsQueue.resetQueue();
		for (Tile stopPoint : stopPoints) {
			actionsQueue.addAction(new Action(this, Actions.MOVE, stopPoint));
		}
	}

}
