package model.civilization;

import model.tile.Tile;

public class Person {

	private Tile tile = null;//TODO: merge with parham
	private Currency currency;

	public Person() {
		currency = new Currency(0,1,-2);
	}

	/**
	 * 
	 * @param newTile
	 */
	public void setTile(Tile newTile) {
		if(tile != null){
			tile.removePerson(this);
		}
		if(newTile != null)
			newTile.addPerson(this);
		tile = newTile;
	}

	public Tile getTile() {
		return tile;
	}


}