package model.civilization;

import java.util.Vector;

public class Trade {
	Civilization first;
	Civilization second;
	Vector<TradeList> firstImport;
	Vector<TradeList> secondImport;
	int remainedTurn;

	public Trade(Vector<TradeList> firstImport, Vector<TradeList> secondImport, Civilization first, Civilization second, int turn) {

	}


	//if returns true means needs to be deleted
	public boolean nextTurn() {
		//TODO: implement here
		return false;
	}

	public void applyEffect() {

	}
}
