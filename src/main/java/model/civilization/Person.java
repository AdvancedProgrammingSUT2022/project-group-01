package model.civilization;

import model.tile.Tile;

public class Person {

	private Tile tile;//TODO: merge with parham
	private Currency currency;

	public Person(Tile tile) {
		currency = new Currency(0,1,-1);
	}

	/**
	 * 
	 * @param newTile
	 */
	public void changeTile(Tile newTile) {

	}

	/**
	 * 
	 * @param newJob
	 */


}