package model.unit.armed;

import model.Game;
import model.civilization.Civilization;
import model.civilization.city.City;
import model.tile.Tile;
import model.unit.Unit;
import model.unit.UnitType;

public class Armed extends Unit {
	private int XP = 0;

	public Armed(UnitType type, Tile tile, Civilization civilization, Game game){
		super(type, tile, civilization, game);
	}

    public static Unit spawnArmed(UnitType type, Tile tile, Civilization ownerCivilization) {
		return null;
    }

    @Override
	public void moveTo(Tile tile) {
		//todo implement here for armed
		currentTile.removeUnit(this);
		tile.setArmedUnit(this);

		this.currentTile = tile;
		tile.setArmedUnit(this);
	}

	public void attackTile(Tile destinationTile){
		if(this instanceof Siege){
			if(!((Siege) this).readyToAttack())
				return ;
		}
		// TODO handle attack

	}
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
