package model.unit.civilian;

import model.Game;
import model.civilization.Civilization;
import model.improvement.ImprovementType;
import model.tile.TerrainFeature;
import model.tile.Tile;
import model.unit.UnitType;
import model.unit.action.Action;
import model.unit.action.Actions;
import model.unit.civilian.Civilian;

import java.util.Random;

public class Worker extends Civilian {
	public Worker(UnitType type, Tile tile, Civilization civilization){
		super(type, tile, civilization);
	}

	public static Worker spawnWorker(UnitType type, Tile tile, Civilization civilization) {
		return new Worker(type, tile, civilization);
	}

	public boolean canImprove(ImprovementType improvementType){
		return improvementType.isEligibleToBuild(ownerCivilization, currentTile);
	}
	public void improveTile(ImprovementType improvement){
		actionsQueue.resetQueue();
		actionsQueue.addAction(new Action(this, Actions.BUILD_IMPROVEMENT, improvement));
	}
	public void buildRoad(){
		actionsQueue.resetQueue();
		actionsQueue.addAction(new Action(this, Actions.BUILD_ROAD));
	}
	public void buildRail(){
		actionsQueue.resetQueue();
		actionsQueue.addAction(new Action(this, Actions.BUILD_RAIL));
	}
	public void pauseImprovement(){
		actionsQueue.resetQueue();
		actionsQueue.addAction(new Action(this, Actions.PAUSE_IMPROVEMENT));
	}
	public void repairImprovement(){
		// TODO : update with parham's code
		actionsQueue.resetQueue();
		actionsQueue.addAction(new Action(this, Actions.REPAIR_IMPROVEMENT));
	}
	public void removeImprovement(){
		actionsQueue.resetQueue();
		actionsQueue.addAction(new Action(this, Actions.REMOVE_IMPROVEMENT));
	}

	public boolean canRemoveFeature(){
		final TerrainFeature feature = currentTile.getFeature();
		return feature == TerrainFeature.FOREST || feature == TerrainFeature.JUNGLE || feature == TerrainFeature.MARSH;
	}
	public void removeFeature(){

	}
	public void removeRoad(){

		actionsQueue.resetQueue();
		actionsQueue.addAction(new Action(this, Actions.REMOVE_ROAD));
	}
	public void removeRail(){
		actionsQueue.resetQueue();
		actionsQueue.addAction(new Action(this, Actions.REMOVE_RAIL));
	}

	public boolean canRemoveRoad() {
		// todo
		return false;
	}
}
