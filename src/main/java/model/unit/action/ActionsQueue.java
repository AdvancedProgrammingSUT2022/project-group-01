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
	private final Vector<Pair<Action, Integer>> queue;
	private final Unit unit;

	/**
	 * construct new empty action queue
	 * @param unit unit which should do this queue
	 */
	public ActionsQueue(Unit unit){
		queue = new Vector<>();
		this.unit = unit;
	}

	/**
	 * method to check that queue has any action in it or not
	 *
	 * @return whatever queue is empty or not
	 */
	public boolean isEmpty(){
		return queue.isEmpty();
	}

	/**
	 * method to remove actions that have been completed
	 */
	private void removeCompletedAction(){
		while(!isEmpty()){
			if(queue.firstElement().getSecond() > 0)
				break;
			queue.remove(0);
		}
	}

	public void nextTurn(){
		//TODO next ture
	}
	public void doThisTurnFunctions(){
		//TODO do functions
	}

	/**
	 * add new action to queue
	 *
	 * @param action new action to be added
	 */
	public void addAction(Action action){
		queue.add(new Pair<>(action, action.getRequiredTurns()));
	}

	/**
	 * check that all actions of queue is possible or not
	 *
	 * @return whatever all of this queue is valid or not
	 */
	public boolean validateQueue(){
		for (Pair<Action, Integer> action : queue)
			if(!action.getFirst().isPossible())
				return false;
		return true;
	}

	/**
	 * calculates sum of required turns
	 *
	 * @return total number of required turns to complete this actions
	 */
	public int calculateNecessaryTurns(){
		int turns = 0;
		for (Pair<Action, Integer> action : queue)
			turns += action.getSecond();
		return turns;
	}

	/**
	 * remove all element of queue
	 */
	public void resetQueue(){
		queue.clear();
	}

	private static class StackData{
		private Unit unit;
		private UnitActions actionType;
		private Integer remainedTime;

		public StackData(Unit unit, UnitActions actionType, Integer remainedTime) {
			this.unit = unit;
			this.actionType = actionType;
			this.remainedTime = remainedTime;
		}

		public Integer getRemainedTime() {
			return remainedTime;
		}

		public void setRemainedTime(Integer remainedTime) {
			this.remainedTime = remainedTime;
		}

		public Unit getUnit() {
			return unit;
		}

		public void setUnit(Unit unit) {
			this.unit = unit;
		}

		public Object getActionType() {
			return actionType;
		}

		public void setActionType(UnitActions actionType) {
			this.actionType = actionType;
		}
	}
}
