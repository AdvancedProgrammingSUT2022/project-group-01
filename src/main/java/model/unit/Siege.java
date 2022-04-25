package model.unit;

public class Siege extends RangedUnit {
	private boolean canAttack = false;

	public Siege(UnitType type){
		super(type);
	}
	public void preAttack(){}
}
