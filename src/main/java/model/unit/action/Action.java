package model.unit.action;

public interface Action {

	int getRequiredTurns();
	boolean isPossible();
	void doAction();
}
