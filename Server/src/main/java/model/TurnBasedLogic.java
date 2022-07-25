package model;

import model.civilization.Civilization;

import java.util.Vector;

public interface TurnBasedLogic {
	static Vector<TurnBasedLogic> turnBasedObjects = new Vector<>();

	void nextTurn(Civilization civilization);

	static void callNextTurns(Civilization civilization) {
		for (int i = 0; i < turnBasedObjects.size(); i++) {
			turnBasedObjects.get(i).nextTurn(civilization);
		}
	}

	default void addToList() {
		turnBasedObjects.add(this);
	}

	default void removeFromList() {
		turnBasedObjects.remove(this);
	}
}
