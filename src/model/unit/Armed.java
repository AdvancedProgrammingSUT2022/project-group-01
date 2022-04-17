package unit;

public class Armed extends Unit {
	private int XP = 0;
	public void AttackCell(Cell destinationCell){}
	public boolean isInAttackRange(Cell targetCell){ return false; }
	public void addXP(int XP){
		this.XP += XP;
	}
	public int getDefensePower(){ return 0; }
}
