package model.unit;

import model.map.*;

public class Settler extends Civilian {

	public Settler(UnitType type){
		super(type);
	}
	public boolean canSettle(Map map){
		return false;
	}
	public void settle(){}
}
