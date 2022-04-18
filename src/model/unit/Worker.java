package model.unit;

import model.improvement.Improvement;

public class Worker extends Civilian {
	public Worker(UnitType type){
		super(type);
	}
	public boolean canImprove(){
		return false;
	}
	public boolean improveTile(Improvement improvement){
		return false;
	}
}
