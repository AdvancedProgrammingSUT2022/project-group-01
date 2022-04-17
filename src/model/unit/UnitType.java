package unit;

import Resource.ResourceList;
import Resource.ResourceType;
import technology.TechnologyList;
import technology.TechnologyType;

public enum UnitType {

	ARCHER(70, CombatType.ARCHERY, 4, 6, 2, 2, new ResourceList(), new TechnologyList(TechnologyType.ARCHERY)),
	CHARIOT_ARCHER(60, CombatType.MOUNTED, 3, 6, 2, 4, new ResourceList(ResourceType.HORSES), new TechnologyList(TechnologyType.THE_WHEEL)),
	SCOUT(25, CombatType.RECON, 4, -1, -1, 2, new ResourceList(), new TechnologyList()),
	SETTLER(89, CombatType.CIVILIAN, -1, -1, -1, 2, new ResourceList(), new TechnologyList()),

	SPEARMAN(50, CombatType.MELEE, 7, -1, -1, 2, new ResourceList(), new TechnologyList()),
	WARRIOR(40, CombatType.MELEE, 6, -1, -1, 2, new ResourceList(), new TechnologyList()),
	WORKER(70, CombatType.CIVILIAN, -1, -1, -1, 2, new ResourceList(), new TechnologyList());
	
	UnitType(int cost, CombatType combatType, int combatStrength, int rangedCombatStrength, int range, int movement,
			 ResourceList requiredResources,
			 TechnologyList requiredTechs){
	}

	public boolean canCreate(City city){ return true; }
	public Unit create(){
		return null;
	}
}
