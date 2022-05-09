package model.unit.civilian;

import model.Game;
import model.civilization.Civilization;
import model.improvement.ImprovementType;
import model.tile.Tile;
import model.unit.UnitType;
import model.unit.civilian.Civilian;

public class Worker extends Civilian {
	public Worker(UnitType type, Tile tile, Civilization civilization, Game game){
		super(type, tile, civilization, game);
	}
	public boolean canImprove(){
		return false;
	}
	public void improveTile(ImprovementType improvement){

	}
	public void buildRoad(){}
	public void pauseImprovement(){}
	public void repairImprovement(){}
	public void removeImprovement(){}
	public void removeRoad(){}
}
