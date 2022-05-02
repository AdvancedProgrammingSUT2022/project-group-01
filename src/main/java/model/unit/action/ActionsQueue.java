package model.unit.action;

import model.unit.Unit;
import utils.Pair;

import java.util.Vector;

/**
 * container to store actions and handle events
 *
 * @author Ali Safari
 */
public class ActionsQueue {

	/**
	 * Vector of Unit Action and remaining turns Pair to store actions
	 */
	private final Vector<Action> queue;

	/**
	 * construct new empty action queue
	 */
	public ActionsQueue() {
		queue = new Vector<>();
	}

	/**
	 * method to check that queue has any action in it or not
	 *
	 * @return whatever queue is empty or not
	 */
	public boolean isEmpty() {
		return queue.isEmpty();
	}

	/**
	 * method to remove actions that have been completed
	 */
	private void removeCompletedAction() {
		while (!isEmpty()) {
			if (queue.firstElement().getRequiredTurns() > 0)
				break;
			queue.remove(0);
		}
	}

	public void nextTurn() {
		//TODO next ture
	}

	/**
	 * do the first action in queue
	 */
	public void doAction() {
		if (isEmpty()) return;

		queue.firstElement().doAction();
		removeCompletedAction();
	}

	/**
	 * add new action to queue
	 *
	 * @param action new action to be added
	 */
	public void addAction(Action action) {
		queue.add(action);
	}

	/**
	 * check that all actions of queue is possible or not
	 *
	 * @return whatever all of this queue is valid or not
	 */
	public boolean validateQueue() {
		for (Action action : queue)
			if (!action.isPossible())
				return false;
		return true;
	}

	/**
	 * calculates sum of required turns
	 *
	 * @return total number of required turns to complete this actions
	 */
	public int calculateNecessaryTurns() {
		int turns = 0;
		for (Action action : queue)
			turns += action.getRequiredTurns();
		return turns;
	}

	/**
	 * remove all element of queue
	 */
	public void resetQueue() {
		queue.clear();
	}
}
