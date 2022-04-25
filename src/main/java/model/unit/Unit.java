package model.unit;

import model.Game;
import model.civilization.Civilization;
import model.civilization.city.City;
import model.civilization.production.Production;
import model.Map;
import model.technology.TechnologyList;
import model.tile.Boarder;
import model.tile.Tile;
import utils.Pair;

import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Vector;

public class Unit extends Production {
	private final static int maxHealth = 10;
	private Civilization ownerCivilization;
	private int health;
	private int movementPoint;
	private TechnologyList requiredTechnologies;
	private int cost;
	private Tile currentTile;
	private boolean isHealing;
	private Game game;
	private UnitType unitType;
	Tile destTile;

	public Unit(UnitType type, Tile tile, Civilization civilization, Game game){
		this.health = maxHealth;
		this.movementPoint = type.getMovement();
		this.requiredTechnologies = type.getRequiredTechs();
		this.cost = type.getCost();
		this.currentTile = tile;
		this.isHealing = false;
		this.ownerCivilization = civilization;
		this.game = game;
		unitType = type;
		destTile = null;
	}

	public UnitType getType() {
		return unitType;
	}

	public Tile getCurrentTile() {
		return currentTile;
	}

	public void fortifyUntilHeal(){
		if(health == maxHealth) return ;
		isHealing = true;
		destTile = null;
	}
	public boolean nextTurn(Civilization civilization, City city){
		// TODO : add other functions
		if(isHealing){
			health ++;
			if(health == maxHealth){
				isHealing = false;
			}
		}
		if(destTile != null){
			Tile nextTile = dijkstra(destTile);
			if(nextTile == null){
				destTile = null;
				return false;
			}
			this.moveTo(nextTile);
			return true;
		}
		return false;
	}
	public void defendAgainstMelee(Unit enemy){}
	public void defendAgainstRanged(Unit enemy){}

	public void setDestTile(Tile tile){
		//todo make it boolean
		this.destTile = tile;
	}

	private void moveTo(Tile tile){
		//todo implement here for armed
		currentTile.removeUnit(this);
		tile.setCivilianUnit((Civilian) this);
		this.currentTile = tile;
	}

	public void execute(ActionsType actionType){}

	private class Distance implements Comparable<Distance> {
		public static final int infinity = 10000000;
		private int turn;
		private int remainedMP;
		public Distance(int turn, int remainedMP){
			this.turn = turn;
			this.remainedMP = remainedMP;
		}

		public int getRemainedMP() {
			return remainedMP;
		}

		public int getTurn() {
			return turn;
		}

		public Distance getDistanceAfter(Tile currentTile, Boarder boarder){
			int resultTurn = turn;
			int resultRemainedMP = remainedMP;
			Tile nextTile = boarder.getOtherTile(currentTile);
			if(remainedMP == 0){
				if((Unit.this instanceof Armed) && currentTile.getArmedUnit() != null)
					return new Distance(infinity, -1);
				if((Unit.this instanceof Civilian) && currentTile.getCivilianUnit() != null)
					return new Distance(infinity, -1);
				resultTurn ++;
				resultRemainedMP = movementPoint;
			}
			resultRemainedMP = Math.max(0, resultRemainedMP - (boarder.isRiver() ? resultRemainedMP : nextTile.getMovementCost()));
			return new Distance(resultTurn, resultRemainedMP);
		}

		@Override
		public int compareTo(Distance o) {
			if(turn != o.getTurn())
				return Integer.compare(turn, o.getTurn());
			return -Integer.compare(remainedMP, o.getRemainedMP());
		}
	}



	private Tile dijkstra(Tile destination){
		Map gameMap = game.getMap();//TODO get user map in next checkpoint

		PriorityQueue<Pair<Distance, Integer>> heap = new PriorityQueue<>();
		int mapSize = gameMap.getMapSize();
		Vector<Distance> dis = new Vector<>(mapSize);
		Vector<Integer> par = new Vector<>(mapSize);

		for(int i = 0; i < mapSize; i++){
			dis.add(new Distance(Distance.infinity, -1));
			par.add(-1);
		}

		int start = currentTile.getMapNumber();
		dis.set(start, new Distance(0, 0));
		heap.add(new Pair<>(dis.get(start), start));

		while(!heap.isEmpty()){
			Pair<Distance, Integer> min = heap.poll();
			Distance distance = min.getFirst();
			Tile node = gameMap.getTileByNumber( min.getSecond() );

			for(int i = 0; i < 6; i++) {
				Boarder boarder = node.getBoarder(i);
				Tile adjacentTile = boarder.getOtherTile(node);
				if(adjacentTile == null) continue;
				Distance nextDistance = distance.getDistanceAfter(currentTile, boarder);
				int adjacentTileID = adjacentTile.getMapNumber();

				if(dis.get(adjacentTileID).compareTo(nextDistance) > 0){
					par.set(adjacentTileID, node.getMapNumber());

					heap.remove(new Pair<>(dis.get(adjacentTileID), adjacentTileID));
					dis.set(adjacentTileID, nextDistance);
					heap.add(new Pair<>(dis.get(adjacentTileID), adjacentTileID));
				}
			}
		}
		if(par.get(destination.getMapNumber()) == -1){
			return null;
		}

		Vector<Tile> path = new Vector<>();
		while(currentTile.getMapNumber() != destination.getMapNumber()){
			path.add(destination);
			destination = gameMap.getTileByNumber(par.get(destination.getMapNumber()));
		}
		Collections.reverse(path);
		Tile firstStop = currentTile;
		for (Tile tile : path) {
			if(dis.get(tile.getMapNumber()).getTurn() <= 1)
				firstStop = tile;
		}
		return  firstStop;
	}

	public Civilization getOwnerCivilization() {
		return ownerCivilization;
	}
}
