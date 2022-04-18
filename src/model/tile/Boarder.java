package model.tile;

public class Boarder {
    Tile[] adjacentTiles = new Tile[2];
    boolean isRiver;
    Boarder(Tile firstTile, Tile secondTile,boolean isRiver){
        adjacentTiles[0] = firstTile;
        adjacentTiles[1] = secondTile;
        this.isRiver = isRiver;
    }
    public boolean isNearTile(Tile tile){
        return (adjacentTiles[0] == tile) | (adjacentTiles[1] == tile);
    }
    public void destroyRiver(){}
    public void setRiver(){}

}
