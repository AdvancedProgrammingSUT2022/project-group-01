package model.unit.action;

import com.sun.source.doctree.SeeTree;
import model.ProgressState;
import model.unit.armed.Siege;
import model.unit.Unit;
import model.unit.civilian.Settler;
import utils.StringUtils;

import java.util.function.Consumer;
import java.util.function.Function;

public enum Actions {
	FORTIFY(1,
			action -> action.getUnit().getHealth() < Unit.maxHealth,
			action -> {
				action.getUnit().changeHealth(+1);
				action.decreaseTurn();
			}
	),
	FORTIFY_UNTIL_HEAL(10,
			FORTIFY.isPossibleFunc,
			action -> {
				System.out.println("!!!!!!!!!!!!!!!!!!!".repeat(1));
				action.getUnit().changeHealth(+1);
				if(action.getUnit().getHealth() == Unit.maxHealth)
					action.completeAction();
			}
	),
	SLEEP(-1,
			action -> true,
			action -> {}
	),
	SETUP(1,
			action -> true,
			action -> {
				((Siege) action.getUnit()).completeSetup();
				action.decreaseTurn();
			}
	),
	ALERT(-1,
			action -> true,
			action -> {
				if(action.getUnit().isEnemyNear())
					action.completeAction();
			}
	),
	MOVE(1,
			action -> action.getTile().getSameTypeUnit(action.getUnit()) == null,
			action -> {
				action.getUnit().moveTo(action.getTile());
				action.decreaseTurn();
			}
	),
	SETTLE(1,
			action -> ((Settler) action.getUnit()).canSettle(),
			action -> {
				((Settler) action.getUnit()).settle();
			}
	),
	BUILD_IMPROVEMENT(null,
			action -> action.getImprovementType().isEligibleToBuild(action.getUnit().getOwnerCivilization(), action.getUnit().getCurrentTile()),
			action -> {
				action.getUnit().getCurrentTile().buildImprovement(action.getImprovementType());
				System.err.println("123 : " + action.getUnit().getCurrentTile().getImprovementTurnsLeft());
				action.decreaseTurn();
			}
	),
	PILLAGE_IMPROVEMENT(1,
			action -> action.getUnit().getCurrentTile().getBuiltImprovement() != null,
			action -> action.getUnit().getCurrentTile().pillageImprovement()
	),
	REMOVE_IMPROVEMENT(null,
			PILLAGE_IMPROVEMENT.isPossibleFunc,
			action -> action.getUnit().getCurrentTile().removeImprovement()
	),
	// TODO : need repair method in Tile
	REPAIR_IMPROVEMENT(null,
			null,
			null
	),
	PAUSE_IMPROVEMENT(1,
			action -> action.getUnit().getCurrentTile().getImprovementInventoryState().equals(ProgressState.IN_PROGRESS),
			action -> action.getUnit().getCurrentTile().stopImprovementProgress()
	),
	BUILD_ROAD(1,
			action -> !action.getUnit().getCurrentTile().doesHaveRoad(),
			action -> action.getUnit().getCurrentTile().buildRoad()
	),
	BUILD_RAIL(null,
			action -> !action.getUnit().getCurrentTile().doesHaveRailRoad(),
			action -> action.getUnit().getCurrentTile().buildRailRoad()
	),
	REMOVE_ROAD(1,
			action -> action.getUnit().getCurrentTile().doesHaveRoad(),
			action -> action.getUnit().getCurrentTile().removeRoads()
	),
	REMOVE_RAIL(1,
			action -> action.getUnit().getCurrentTile().doesHaveRailRoad(),
			action -> action.getUnit().getCurrentTile().removeRoads()
	);

	private final Integer requiredTurns;
	private final Function<Action, Boolean> isPossibleFunc;
	private final Consumer<Action> doActionFunc;

	Actions(Integer requiredTurns, Function<Action, Boolean> isPossibleFunc, Consumer<Action> doActionFunc) {
		this.requiredTurns = requiredTurns;
		this.isPossibleFunc = isPossibleFunc;
		this.doActionFunc = doActionFunc;
	}

	public Integer getRequiredTurns() {
		return requiredTurns;
	}

	public Function<Action, Boolean> getIsPossibleFunc() {
		return isPossibleFunc;
	}

	public Consumer<Action> getDoActionFunc() {
		return doActionFunc;
	}

	@Override
	public String toString() {
		return StringUtils.convertToPascalCase(this.name()).replaceAll("_", " ");
	}
}
