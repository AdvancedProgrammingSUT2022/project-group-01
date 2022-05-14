package controller.unit;

import lombok.AllArgsConstructor;
import model.Game;
import model.ProgressState;
import model.improvement.ImprovementType;
import model.tile.Tile;
import model.unit.civilian.Worker;

@AllArgsConstructor
public class WorkerController {
	private final Game game;

	private Worker getWorker(){
		assert checkSelectedObject();
		return (Worker) game.getSelectedObject();
	}
	public boolean checkSelectedObject(){
		return game.getSelectedObject() != null && game.getSelectedObject() instanceof Worker;
	}

	public String buildImprovement(Worker worker, ImprovementType improvement){
		if(improvement == null)
			return "invalid Improvement !";
		if(!improvement.isEligibleToBuild(game.getCurrentPlayer().getCivilization(), worker.getCurrentTile()))
			return "not Eligible to Build this improvement here";

		worker.improveTile(improvement);
		return "Improvement is constructing";
	}

	public String removeFeature(Worker worker){
		if(worker.getCurrentTile().getFeature() == null)
			return "this tile doesn't have any feature";
		worker.removeFeature();
		return "operation remove started";
	}

	public String repair(Worker worker) {
		Tile tile = worker.getCurrentTile();
		if(!tile.getImprovementInventoryState().equals(ProgressState.DAMAGED))
			return "this tile is not pillaged";
		worker.repairImprovement();
		return "this tile is under maintenance";
	}

	public String removeImprovement(Worker worker) {
		Tile tile = worker.getCurrentTile();
		if(tile.getBuiltImprovement() == null)
			return "this tile don't have any improvement";
		worker.removeImprovement();
		return "this tile is being remove";
	}

	public String buildRoad(Worker worker) {
		if(worker.getCurrentTile().doesHaveRoad() || worker.getCurrentTile().doesHaveRailRoad())
			return "you can't build road here";
		worker.buildRoad();
		return "road is under construction";
	}

	public String buildRail(Worker worker) {
		if(worker.getCurrentTile().doesHaveRoad() || worker.getCurrentTile().doesHaveRailRoad())
			return "you can't build rail road here";
		worker.buildRail();
		return "rail road is under construction";
	}

	public String removeRoute(Worker worker) {
		if(!(worker.getCurrentTile().doesHaveRoad() || worker.getCurrentTile().doesHaveRailRoad()))
			return "this tile doesn't have any route";
		worker.removeRoute();
		return "route is being removed now";
	}
}
