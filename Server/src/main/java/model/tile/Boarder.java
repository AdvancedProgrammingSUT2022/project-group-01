package model.tile;

public class Boarder {
	Tile[] adjacentTiles = new Tile[2];
	boolean isRiver;
	int mapSize;
	int weight;

	public Boarder(Tile firstTile, Tile secondTile, boolean isRiver, int mapSize) {
		setBoarder(firstTile, secondTile, isRiver, mapSize);
	}

	public boolean isNearTile(Tile tile) {
		return (adjacentTiles[0] == tile) | (adjacentTiles[1] == tile);
	}

	public void destroyRiver() {
		this.isRiver = false;
	}

	public void setRiver() {
		this.isRiver = true;
	}

	public boolean isRiver() {
		return isRiver;
	}

	private void setWeight() {
		if ((adjacentTiles[0] == null) || (adjacentTiles[1] == null)) {
			this.weight = 0;
			return;
		}
		int firstMin = Math.min(adjacentTiles[0].getPCoordinate(), Math.abs(mapSize - 1 - adjacentTiles[0].getPCoordinate()));
		int secondMin = Math.min(adjacentTiles[0].getQCoordinate(), Math.abs(mapSize - 1 - adjacentTiles[0].getQCoordinate()));
		int thirdMin = Math.min(adjacentTiles[1].getPCoordinate(), Math.abs(mapSize - 1 - adjacentTiles[1].getPCoordinate()));
		int fourthMin = Math.min(adjacentTiles[1].getQCoordinate(), Math.abs(mapSize - 1 - adjacentTiles[1].getQCoordinate()));
		this.weight = Math.min(firstMin, secondMin) + Math.min(thirdMin, fourthMin);
	}

	public Tile getOtherTile(Tile tile) {
		if (adjacentTiles[0].equals(tile))
			return adjacentTiles[1];
		return adjacentTiles[0];
	}

	public void setBoarder(Tile firstTile, Tile secondTile, boolean isRiver, int mapSize) {
		adjacentTiles[0] = firstTile;
		adjacentTiles[1] = secondTile;
		this.isRiver = isRiver;
		this.mapSize = mapSize;
		setWeight();
	}

	public int getWeight() {
		return weight;
	}

	public Tile[] getAdjacentTiles() {
		return adjacentTiles;
	}
}
