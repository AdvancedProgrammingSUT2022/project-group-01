package model.unit;

import model.civilization.city.City;
import model.resource.ResourceList;
import model.resource.ResourceType;
import model.technology.TechnologyList;
import model.technology.TechnologyType;
import model.unit.specialEffect.SpecialEffect;

public enum UnitType {
	// Ancient Era
	ARCHER(70, CombatType.ARCHERY, 4, 6, 2, 2, new ResourceList(), new TechnologyList(TechnologyType.ARCHERY), null),
	CHARIOT_ARCHER(60, CombatType.MOUNTED, 3, 6, 2, 4, new ResourceList(ResourceType.HORSES), new TechnologyList(TechnologyType.THE_WHEEL), null),
	SCOUT(25, CombatType.RECON, 4, -1, -1, 2, new ResourceList(), new TechnologyList(), null),
	SETTLER(89, CombatType.CIVILIAN, -1, -1, -1, 2, new ResourceList(), new TechnologyList(), null),
	SPEARMAN(50, CombatType.MELEE, 7, -1, -1, 2, new ResourceList(), new TechnologyList(), null),
	WARRIOR(40, CombatType.MELEE, 6, -1, -1, 2, new ResourceList(), new TechnologyList(), null),
	WORKER(70, CombatType.CIVILIAN, -1, -1, -1, 2, new ResourceList(), new TechnologyList(), null),

	// Classical Era
	CATAPULT(0, null, 0,0,0,0, null, null, null),
	HORSEMAN(0, null, 0,0,0,0, null, null, null),
	SWORDSMAN(0, null, 0,0,0,0, null, null, null),

	// Medieval Era
	CROSSBOW_MAN(0, null, 0,0,0,0, null, null, null),
	KNIGHT(0, null, 0,0,0,0, null, null, null),
	LONG_SWORDSMAN(0, null, 0,0,0,0, null, null, null),
	PIKE_MAN(0, null, 0,0,0,0, null, null, null),
	TREBUCHET(0, null, 0,0,0,0, null, null, null),

	// Renaissance Era
	CANON(0, null, 0,0,0,0, null, null, null),
	CAVALRY(0, null, 0,0,0,0, null, null, null),
	LANCER(0, null, 0,0,0,0, null, null, null),
	MUSKET_MAN(0, null, 0,0,0,0, null, null, null),
	RIFLE_MAN(0, null, 0,0,0,0, null, null, null),

	// Industrial Era
	ANTI_TANK_GUN(0, null, 0,0,0,0, null, null, null),
	ARTILLERY(0, null, 0,0,0,0, null, null, null),
	INFANTRY(0, null, 0,0,0,0, null, null, null),
	PANZER(0, null, 0,0,0,0, null, null, null),
	TANK(0, null, 0,0,0,0, null, null, null);

	UnitType(int cost, CombatType combatType, int combatStrength, int rangedCombatStrength, int range, int movement,
			 ResourceList requiredResources,
			 TechnologyList requiredTechs,
			 SpecialEffect effect){
	}

	public boolean canCreate(City city){ return true; }
	public Unit create(){
		return null;
	}
}
