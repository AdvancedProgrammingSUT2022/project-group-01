package model.unit;

import model.civilization.city.City;
import model.resource.ResourceList;
import model.resource.ResourceType;
import model.technology.TechnologyList;
import model.technology.TechnologyType;
import model.unit.specialEffect.SpecialEffect;

public enum UnitType {

	ARCHER(70, CombatType.ARCHERY, 4, 6, 2, 2, new ResourceList(), new TechnologyList(TechnologyType.ARCHERY), null),
	CHARIOT_ARCHER(60, CombatType.MOUNTED, 3, 6, 2, 4, new ResourceList(ResourceType.HORSES), new TechnologyList(TechnologyType.THE_WHEEL), null),
	SCOUT(25, CombatType.RECON, 4, -1, -1, 2, new ResourceList(), new TechnologyList(), null),
	SETTLER(89, CombatType.CIVILIAN, -1, -1, -1, 2, new ResourceList(), new TechnologyList(), null),

	SPEARMAN(50, CombatType.MELEE, 7, -1, -1, 2, new ResourceList(), new TechnologyList(), null),
	WARRIOR(40, CombatType.MELEE, 6, -1, -1, 2, new ResourceList(), new TechnologyList(), null),
	WORKER(70, CombatType.CIVILIAN, -1, -1, -1, 2, new ResourceList(), new TechnologyList(), null);
	
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
