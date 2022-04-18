package model;

import model.tile.Tile;

import java.util.Vector;

public class Map {
	private Vector<Vector<Tile>> map;

	public Map(int height, int width){}
	public Tile getTile(int x, int y){return null; }
	private void BFS(int startX, int startY){}
	private Vector<Tile> getShortestPath(int startX, int startY, int destinationX, int destinationY){
		return null;
	}
	private void generateMap(int height, int width){}
	public Vector<Vector<Tile>> getViewForPlayer(Player player){return null;}
	public void updateMap(){}
}
