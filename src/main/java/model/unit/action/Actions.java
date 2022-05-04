package model.unit.action;

import com.sun.source.doctree.SeeTree;
import model.unit.armed.Siege;
import model.unit.Unit;
import model.unit.civilian.Settler;

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
	FORTIFY_UNTIL_HEAL(-1,
			FORTIFY.isPossibleFunc,
			action -> {
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
	);

	private final int requiredTurns;
	private final Function<Action, Boolean> isPossibleFunc;
	private final Consumer<Action> doActionFunc;

	Actions(int requiredTurns, Function<Action, Boolean> isPossibleFunc, Consumer<Action> doActionFunc) {
		this.requiredTurns = requiredTurns;
		this.isPossibleFunc = isPossibleFunc;
		this.doActionFunc = doActionFunc;
	}

	public int getRequiredTurns() {
		return requiredTurns;
	}

	public Function<Action, Boolean> getIsPossibleFunc() {
		return isPossibleFunc;
	}

	public Consumer<Action> getDoActionFunc() {
		return doActionFunc;
	}
}