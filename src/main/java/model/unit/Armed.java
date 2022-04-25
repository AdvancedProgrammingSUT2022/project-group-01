package model.unit;

import model.civilization.city.City;
import model.tile.Tile;

public class Armed extends Unit {
	private int XP = 0;

	public Armed(UnitType type){
		super(type);
	}

	public void attackTile(Tile destinationTile){}
	public boolean isInAttackRange(Tile targetTile){ return false; }
	public void addXP(int XP){
		this.XP += XP;
	}
	public int getDefensePower(){ return 0; }
	public int getAttackPower(){ return 0; }

	public void upgradeUnit(){}
	public void garrisonCity(City city){}
	public void attackToCity(City city){}
}