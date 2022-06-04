package model.unit;


import controller.MapController;
import controller.ProgramController;
import lombok.Getter;
import lombok.Setter;
import model.Map;
import model.civilization.Civilization;
import model.civilization.city.City;
import model.technology.TechnologyList;
import model.tile.Boarder;
import model.unit.action.Action;
import model.unit.action.Actions;
import model.unit.action.ActionsQueue;
import model.unit.armed.Armed;
import model.unit.civilian.Civilian;
import model.unit.trait.TraitsList;
import model.unit.trait.UnitTraits;
import utils.OrderedPair;
import model.tile.*;

import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Vector;

@Getter
@Setter
public class Unit {
	public final static int maxHealth = 10;
	protected Civilization ownerCivilization;
	protected Tile currentTile;
	protected ActionsQueue actionsQueue;
	private double health;
	private TechnologyList requiredTechnologies;
	private int cost;
	private UnitType type;
	private double movementPoint;

	private double remainingMP;

	public Unit(UnitType type, Tile tile, Civilization civilization) {
		this.health = maxHealth;
		this.movementPoint = type.getMovement();
		this.requiredTechnologies = type.getRequiredTechs();
		this.currentTile = tile;
		this.ownerCivilization = civilization;
		actionsQueue = new ActionsQueue();
		this.type = type;
		remainingMP = movementPoint;
		civilization.addUnit(this);
	}

	public static void produceUnit(UnitType type, City city) {
		Unit unit = spawnUnit(type, city.getCenter(), city.getCivilization());
		if (unit instanceof Armed)
			city.getCenter().setArmedUnit((Armed) unit);
		else
			city.getCenter().setCivilianUnit((Civilian) unit);
		updateMapAfterMove();
	}

	public static Unit spawnUnit(UnitType type, Tile tile, Civilization ownerCivilization) {
		if (type.getCombatType() == CombatType.CIVILIAN)
			return Civilian.spawnCivilian(type, tile, ownerCivilization);
		return Armed.spawnArmed(type, tile, ownerCivilization);
	}

	public void nextTurn() {
		actionsQueue.doAction();
		remainingMP = movementPoint;
	}

	public void defendAgainstMelee(Unit enemy) {
		// TODO implement this
	}

	public void defendAgainstRanged(Unit enemy) {
		// TODO implement this
	}

	public void moveTo(Tile tile) {}

	public TraitsList getTraitsList() {
		return type.getUnitTraits();
	}

	/**
	 * kill unit immediately
	 */
	public void suicide() {
		ownerCivilization.removeUnit(this);
		currentTile.removeUnit(this);
		if (ProgramController.getGame().getSelectedObject() == this) {
			ProgramController.getGame().setSelectedObject(null);
		}

		updateMapAfterMove();
	}

	/**
	 * to change unit health
	 *
	 * @param deltaHealth change amount of health,
	 *                    positive for increase and negative for decrease
	 */
	public void changeHealth(double deltaHealth) {
		health += deltaHealth;
		health = Math.min(maxHealth, health);
		if (health <= 0)
			suicide();
	}

	private double lastDijkstraRemMP;
	private Vector<Tile> dijkstra(Tile destination) {
		lastDijkstraRemMP = -1;
		Map gameMap = ProgramController.getGame().getMap();//TODO get user map in next checkpoint

		if(!destination.isPassable() || destination.getSameTypeUnit(this) != null)
			return null;

		PriorityQueue<OrderedPair<Distance, Integer>> heap = new PriorityQueue<>();
		int mapSize = gameMap.getMapSize() * gameMap.getMapSize();
		Vector<Distance> dis = new Vector<>(mapSize);
		Vector<Integer> par = new Vector<>(mapSize);

		for (int i = 0; i < mapSize; i++) {
			dis.add(new Distance(Distance.infinity, -1));
			par.add(-1);
		}
//		System.err.printf("?!?!?!? %d\n", dis.size());
		int start = currentTile.getMapNumber();
		dis.set(start, new Distance(0, 0));
		heap.add(new OrderedPair<>(dis.get(start), start));

		while (!heap.isEmpty()) {
			OrderedPair<Distance, Integer> min = heap.poll();
			Distance distance = min.getFirst();
			Tile node = gameMap.getTileByNumber(min.getSecond());

//			System.err.printf("## (%d, %d)\n", distance.turn, distance.remainedMP);

			for (int i = 0; i < 6; i++) {
				Boarder boarder = node.getBoarder(i);
				Tile adjacentTile = boarder.getOtherTile(node);
				if (adjacentTile == null) continue;
//				System.err.printf("$$ (%d)\n", adjacentTile.getMapNumber());

				Distance nextDistance = distance.getDistanceAfter(node, boarder);
				int adjacentTileID = adjacentTile.getMapNumber();
//				System.err.printf("^^ (%d, %d)\n", nextDistance.turn, nextDistance.remainedMP);
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
		path.add(destination);
		Collections.reverse(path);
		Vector<Tile> stopTiles = new Vector<>();
		for (int i = 1; i + 1 < path.size(); i++)
			if (dis.get(path.get(i).getMapNumber()).getTurn() < dis.get(path.get(i + 1).getMapNumber()).getTurn())
				stopTiles.add(path.get(i));
		stopTiles.add(path.lastElement());
		lastDijkstraRemMP = dis.get(stopTiles.get(0).getMapNumber()).remainedMP;
		return stopTiles;
	}

	private boolean ZOC(Tile destTile) {
		Vector<Tile> sight = destTile.getSight(1);
		for (Tile tile : sight) {
			if (tile.getArmedUnit() == null) continue;
			if (tile.getArmedUnit().getOwnerCivilization() != ownerCivilization)
				return true;
		}
		return false;
	}

	/**
	 * check that there is any enemy close to unit or not
	 *
	 * @return true if any enemy is visible by this unit, false otherwise
	 */
	public boolean isEnemyNear() {
		Vector<Tile> sight = currentTile.getSight(2);
		for (Tile tile : sight) {
			if (tile.getArmedUnit() == null) continue;
			if (tile.getArmedUnit().getOwnerCivilization() != ownerCivilization)
				return true;
		}
		return false;
	}

	/**
	 * fortify unit until complete healing
	 */
	public void fortifyUntilHeal() {
		actionsQueue.resetQueue();
		actionsQueue.addAction(new Action(this, Actions.FORTIFY_UNTIL_HEAL));
	}

	// Commands

	/**
	 * pillage unit current tile
	 */
	public void pillage() {
		actionsQueue.resetQueue();
		actionsQueue.addAction(new Action(this, Actions.PILLAGE_IMPROVEMENT));
		actionsQueue.doActionInTurn();
	}

	/**
	 * fortify unit for 1 turn
	 */
	public void fortify() {
		actionsQueue.resetQueue();
		actionsQueue.addAction(new Action(this, Actions.FORTIFY));
	}

	/**
	 * disable unit until cancel
	 */
	public void sleep() {
		actionsQueue.resetQueue();
		actionsQueue.addAction(new Action(this, Actions.SLEEP));
	}

	/**
	 * wake unit up
	 */
	public void wake() {
		actionsQueue.resetQueue();
	}

	/**
	 * change unit mode to alert
	 */
	public void alert() {
		actionsQueue.resetQueue();
		actionsQueue.addAction(new Action(this, Actions.ALERT));
	}

	/**
	 * cancel all queued actions
	 */
	public void cancel() {
		actionsQueue.resetQueue();
	}

	public boolean canGoTo(Tile destination) {
		if (!destination.isPassable())
			return false;
		return dijkstra(destination) != null;
	}

	/**
	 * move this unit to destination
	 *
	 * @param destTile is destination of this unit
	 */
	public void goTo(Tile destTile) {
		Vector<Tile> stopPoints = dijkstra(destTile);
		assert stopPoints != null;
		if(stopPoints.size() > 1)
			remainingMP = 0;
		else
			remainingMP = lastDijkstraRemMP;

		moveTo(stopPoints.get(0));
		actionsQueue.resetQueue();
		for (Tile stopPoint : stopPoints) {
			actionsQueue.addAction(new Action(this, Actions.MOVE, stopPoint));
		}
	}

	public boolean isSleeping() {
		return getJob() == Actions.SLEEP || getJob() == Actions.ALERT;
	}

	public Actions getJob() {
		if (actionsQueue.getCurrentAction() == null)
			return null;
		return actionsQueue.getCurrentAction().getActionType();
	}

	public boolean hasJob() {
		return !actionsQueue.isEmpty();
	}

	public void consumeMP(double delta) {
		remainingMP -= delta;
		remainingMP = Math.max(0, remainingMP);
	}

	public boolean outOfMP(){
		return remainingMP <= 0.5f;
	}

	private class Distance implements Comparable<Distance> {
		public static final int infinity = 10000000;
		private int turn;
		private double remainedMP;

		public Distance(int turn, double remainedMP) {
			this.turn = turn;
			this.remainedMP = remainedMP;
		}

		public double getRemainedMP() {
			return remainedMP;
		}

		public int getTurn() {
			return turn;
		}

		public Distance getDistanceAfter(Tile currentTile, Boarder boarder) {
			int resultTurn = turn;
			double resultRemainedMP = remainedMP;
			Tile nextTile = boarder.getOtherTile(currentTile);
			if(ProgramController.getGame().getCurrentPlayer().getSavedMap().getVisibilityState(nextTile).equals(Tile.VisibilityState.FOG_OF_WAR))
				return new Distance(infinity, -1);

			if (!nextTile.isPassable()) {
				return new Distance(infinity, -1);
			}
			if (remainedMP <= 0.01f) {
				if (turn != 0 && (Unit.this instanceof Armed) && currentTile.getArmedUnit() != null)
					return new Distance(infinity, -1);
				if (turn != 0 && (Unit.this instanceof Civilian) && currentTile.getCivilianUnit() != null)
					return new Distance(infinity, -1);
				resultTurn++;
				resultRemainedMP = turn == 0 ? remainedMP : movementPoint;
			}
			double roadModifier = 1f;
			if (currentTile.doesHaveRailRoad() && nextTile.doesHaveRailRoad())
				roadModifier = 2f / 3f;
			if (currentTile.doesHaveRoad() && nextTile.doesHaveRoad())
				roadModifier = 1f / 3f;

			double movementCost = roadModifier * nextTile.getMovementCost(type.getUnitTraits().contains(UnitTraits.NO_TERRAIN_COST));
			resultRemainedMP = Math.max(0, resultRemainedMP - (boarder.isRiver() || ZOC(nextTile) ?
					resultRemainedMP : roadModifier * movementCost));
			return new Distance(resultTurn, resultRemainedMP);
		}

		@Override
		public int compareTo(Distance o) {
			if (turn != o.getTurn())
				return Integer.compare(turn, o.getTurn());
			return -Double.compare(remainedMP, o.getRemainedMP());
		}

	}

	protected static void updateMapAfterMove(){
		MapController mapController = new MapController(ProgramController.getGame());
		mapController.updateCurrentPlayersMap();
	}
}
