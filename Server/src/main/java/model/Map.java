package model;

import model.tile.Tile;

import java.util.Vector;

public class Map {
	private int mapSize;
	private Vector<Vector<Tile>> map;
	private Vector<Tile> reachableTiles;

	public Map(int mapSize) {
		this.mapSize = mapSize;
	}

	public Tile getTile(int p, int q) {
		return map.get(q).get(p);
	}

	public Vector<Vector<Tile>> getMap() {
		return map;
	}

	public Vector<Tile> getTiles() {
		Vector<Tile> tiles = new Vector<>();
		for (Vector<Tile> vector : map) {
			for (Tile tile : vector) {
				if (tile != null)
					tiles.add(tile);
			}
		}

		return tiles;
	}

	public void setMap(Vector<Vector<Tile>> map) {
		this.map = map;
	}

	public int getMapSize() {
		return mapSize;
	}

	public Tile getTileByNumber(int x) {
		for (Vector<Tile> vect : map) {
			for (Tile tile : vect) {
				if (tile == null)
					continue;
				if (tile.getMapNumber() == x)
					return tile;
			}
		}
		return null;
	}

	public Vector<Tile> getReachableTiles() {
		return reachableTiles;
	}

	public void setReachableTiles(Vector<Tile> reachableTiles) {
		this.reachableTiles = reachableTiles;
	}
}
