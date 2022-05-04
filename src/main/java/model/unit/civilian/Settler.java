package model.unit.civilian;

import model.Game;
import model.Map;
import model.civilization.Civilization;
import model.civilization.city.City;
import model.tile.Tile;
import model.unit.UnitType;
import model.unit.action.Action;
import model.unit.action.Actions;

import java.util.List;
import java.util.Vector;

/**
 * Settler is used to found cities,
 * one of the basic units of game
 *
 * @author Ali SAfari
 */
public class Settler extends Civilian {
	/**
	 * minimum distance between every two cities
	 */
	private static final int minimumCityDistance = 4;

	public Settler(UnitType type, Tile tile, Civilization civilization, Game game) {
		super(type, tile, civilization, game);
	}

	/**
	 * check possibility of finding city in current Tile
	 * @return true if all conditions are met, false otherwise
	 */
	public boolean canSettle() {
		if(currentTile.getCivilization() != null && currentTile.getCivilization() != ownerCivilization)
			return false;
		Vector<Tile> closeTiles = new Vector<>(List.of(currentTile));
		for(int i = 1; i < minimumCityDistance; i++){
			Vector<Tile> tmp = new Vector<>();
			for (Tile closeTile : closeTiles)
				tmp.addAll(closeTile.getAdjacentTiles());
			closeTiles = tmp;
			for (Tile closeTile : closeTiles) {
				if(closeTile.getInnerCity() != null)
					return false;
			}
		}
		return true;
	}

	/**
	 * Settle new City in current Tile8
	 * also unit will be consumed
	 */
	public void settle() {
		actionsQueue.resetQueue();

		String name = ownerCivilization.getCivilization().getCityNames()[ownerCivilization.getCities().size()];
		City newCity = new City(name, ownerCivilization, currentTile);
		ownerCivilization.addNewCity(newCity);
		suicide();
	}

	/**
	 * create new action to found new city
	 */
	public void foundCity() {
		actionsQueue.resetQueue();
		actionsQueue.addAction(new Action(this, Actions.SETTLE));
	}
}