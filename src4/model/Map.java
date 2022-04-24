package model;
// TODO ADDED GET map
// TODO ADDED GET TILES
// TODO ADDED int MAP SIZE and getter
// TODO : ADDED NEW CONSTRUCTOR
// TODO : ADDED map SETTER
// TODO : ADDED get Tile by number
import model.tile.Tile;

import java.util.Vector;

public class Map {
	private int mapSize;
	private Vector<Vector<Tile>> map;
	public Map(int mapSize){
		this.mapSize = mapSize;
	}
	public Map(int height, int width){}
	public Tile getTile(int p, int q){
		return map.get(q).get(p);
	}
	private void BFS(int startX, int startY){}
	private Vector<Tile> getShortestPath(int startX, int startY, int destinationX, int destinationY){
		return null;
	}
	private void generateMap(int height, int width){}
	public Vector<Vector<Tile>> getViewForPlayer(Player player){return null;}
	public void updateMap(){}

	public Vector<Vector<Tile>> getMap() {
		return map;
	}
	public Vector<Tile> getTiles(){
		Vector<Tile> tiles = new Vector<>();
		for (Vector<Tile> vector : map) {
			for(Tile tile : vector){
				if(tile != null)
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

	public Tile getTileByNumber(int x){
		for (Vector<Tile> vect : map) {
			for(Tile tile : vect){
				if(tile == null)
					continue;
				if(tile.getMapNumber() == x)
					return tile;
			}
		}
		return null;
	}
}
