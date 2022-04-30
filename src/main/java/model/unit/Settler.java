package model.unit;

import model.Game;
import model.Map;
import model.civilization.Civilization;
import model.civilization.Civilizations;
import model.civilization.city.City;
import model.tile.Tile;
import model.unit.action.ActionsQueue;


public class Settler extends Civilian {

	public Settler(UnitType type, Tile tile, Civilization civilization, Game game){
		super(type, tile, civilization, game);
	}
	public boolean canSettle(Map map){
		return false;
	}
	public void settle(){
		actionsQueue.resetQueue();

		String name = ownerCivilization.getCivilization().getCityNames()[ownerCivilization.getCities().size()];
		City newCity = new City(name, ownerCivilization, currentTile);
		ownerCivilization.addNewCity(newCity);
		changeHealth(-getHealth());
	}
}
