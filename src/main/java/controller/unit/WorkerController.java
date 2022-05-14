package controller.unit;

import lombok.AllArgsConstructor;
import model.Game;
import model.improvement.ImprovementType;
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

	public String buildImprovement(String improvementName){
		ImprovementType improvementType = null;
		for (ImprovementType improvement : ImprovementType.values()) {
			if(improvement.name().toLowerCase().equals(improvementName))
				improvementType = improvement;
		}
		if(improvementType == null)
			return "invalid Improvement !";

		Worker worker = getWorker();
		if(!improvementType.isEligibleToBuild(game.getCurrentPlayer().getCivilization(), worker.getCurrentTile()))
			return "not Eligible to Build this improvement here";

		worker.improveTile(improvementType);
		return "Improvement is constructing";
	}
}
