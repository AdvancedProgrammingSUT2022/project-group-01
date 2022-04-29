package model.unit;

import model.civilization.city.City;
import model.resource.ResourceList;
import model.resource.ResourceType;
import model.technology.TechnologyList;
import model.technology.TechnologyType;
import model.unit.unitTraits.TraitsList;
import model.unit.unitTraits.UnitTraits;

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
	CATAPULT(100, CombatType.SIEGE, 4, 14, 2, 2, new ResourceList(ResourceType.IRON), new TechnologyList(TechnologyType.MATHEMATICS), null),
	HORSEMAN(80, CombatType.MOUNTED, 12, -1, -1, 4, new ResourceList(ResourceType.HORSES), new TechnologyList(TechnologyType.HORSEBACK_RIDING), null),
	SWORDSMAN(80, CombatType.MELEE, 11, -1, -1, 2, new ResourceList(ResourceType.IRON), new TechnologyList(TechnologyType.IRON_WORKING), null),

	// Medieval Era
	CROSSBOW_MAN(120, CombatType.ARCHERY, 6, 12, 2, 2, new ResourceList(), new TechnologyList(TechnologyType.MACHINERY), null),
	KNIGHT(150, CombatType.MOUNTED, 18, -1, -1, 3, new ResourceList(ResourceType.HORSES), new TechnologyList(TechnologyType.CHIVALRY), null),
	LONG_SWORDSMAN(150, CombatType.MELEE, 18, -1, -1, 3, new ResourceList(ResourceType.IRON), new TechnologyList(TechnologyType.STEEL), null),
	PIKE_MAN(100, CombatType.MELEE, 10, -1, -1, 2, new ResourceList(), new TechnologyList(TechnologyType.CIVIL_SERVICE), null),
	TREBUCHET(170, CombatType.SIEGE, 6, 20, 2, 2, new ResourceList(ResourceType.IRON), new TechnologyList(TechnologyType.PHYSICS), null),

	// Renaissance Era
	CANON(250, CombatType.SIEGE, 10, 26, 2, 2, new ResourceList(), new TechnologyList(TechnologyType.CHEMISTRY), null),
	CAVALRY(260, CombatType.MOUNTED, 25, -1, -1, 3, new ResourceList(ResourceType.HORSES), new TechnologyList(TechnologyType.MILITARY_SCIENCE), null),
	LANCER(220, CombatType.MOUNTED, 22, -1, -1, 4, new ResourceList(ResourceType.HORSES), new TechnologyList(TechnologyType.METALLURGY), null),
	MUSKET_MAN(120, CombatType.GUNPOWDER, 16, -1, -1, 2, new ResourceList(), new TechnologyList(TechnologyType.GUNPOWDER), null),
	RIFLE_MAN(200, CombatType.GUNPOWDER, 25, -1, -1, 2, new ResourceList(), new TechnologyList(TechnologyType.RIFLING), null),

	// Industrial Era
	ANTI_TANK_GUN(300, CombatType.GUNPOWDER, 32, -1, -1, 2, new ResourceList(), new TechnologyList(TechnologyType.REPLACEABLE_PARTS), null),
	ARTILLERY(420, CombatType.SIEGE, 16, 32, 3, 2, new ResourceList(), new TechnologyList(TechnologyType.DYNAMITE), null),
	INFANTRY(300, CombatType.GUNPOWDER, 36, -1, -1, 2, new ResourceList(), new TechnologyList(TechnologyType.REPLACEABLE_PARTS), null),
	PANZER(450, CombatType.ARMORED, 60, -1, -1, 5, new ResourceList(), new TechnologyList(TechnologyType.COMBUSTION), null),
	TANK(450, CombatType.ARMORED, 50, -1, -1, 4, new ResourceList(), new TechnologyList(TechnologyType.COMBUSTION), null);

	private final int cost;
	private final CombatType combatType;
	private final int combatStrength;
	private final int rangedCombatStrength;
	private final int range;
	private final int movement;
	private final ResourceList requiredResources;
	private final TechnologyList requiredTechs;
	private final TraitsList traits;

	UnitType(int cost, CombatType combatType, int combatStrength, int rangedCombatStrength, int range, int movement,
			 ResourceList requiredResources,
			 TechnologyList requiredTechs,
			 TraitsList traits) {
		this.cost = cost;
		this.combatType = combatType;
		this.combatStrength = combatStrength;
		this.rangedCombatStrength = rangedCombatStrength;
		this.range = range;
		this.movement = movement;
		this.requiredResources = requiredResources;
		this.requiredTechs = requiredTechs;
		this.traits = traits;
	}

	public boolean canCreate(City city) {
		return requiredTechs.isResearched(city.getCivilization());
	}

	public Unit create() {
		return null;
	}


	public int getCost() {
		return cost;
	}

	public CombatType getCombatType() {
		return combatType;
	}

	public int getCombatStrength() {
		return combatStrength;
	}

	public int getRangedCombatStrength() {
		return rangedCombatStrength;
	}

	public int getRange() {
		return range;
	}

	public int getMovement() {
		return movement;
	}

	public ResourceList getRequiredResources() {
		return requiredResources;
	}

	public TechnologyList getRequiredTechs() {
		return requiredTechs;
	}

	public TraitsList getUnitTraits() {
		return traits;
	}
}
