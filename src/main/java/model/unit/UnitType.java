package model.unit;

import model.civilization.city.City;
import model.civilization.production.Producible;
import model.resource.ResourceList;
import model.resource.ResourceType;
import model.technology.TechnologyList;
import model.technology.TechnologyType;
import model.unit.trait.TraitsList;
import model.unit.trait.UnitTraits;

public enum UnitType implements Producible {
	// Ancient Era
	ARCHER(70, CombatType.ARCHERY, 4, 6, 2, 2, new ResourceList(), new TechnologyList(TechnologyType.ARCHERY), new TraitsList(UnitTraits.NO_MELEE)),
	CHARIOT_ARCHER(60, CombatType.MOUNTED, 3, 6, 2, 4, new ResourceList(ResourceType.HORSES), new TechnologyList(TechnologyType.THE_WHEEL), new TraitsList(UnitTraits.NO_MELEE, UnitTraits.ROUGH_TERRAIN_PENALTY, UnitTraits.NO_DEFENSIVE_BONUS)),
	SCOUT(25, CombatType.RECON, 4, -1, -1, 2, new ResourceList(), new TechnologyList(), new TraitsList(UnitTraits.NO_TERRAIN_COST)),
	SETTLER(89, CombatType.CIVILIAN, -1, -1, -1, 2, new ResourceList(), new TechnologyList(), new TraitsList(UnitTraits.CAN_SETTLE)),
	SPEARMAN(50, CombatType.MELEE, 7, -1, -1, 2, new ResourceList(), new TechnologyList(), new TraitsList(UnitTraits.BONUS_VS_MOUNTED)),
	WARRIOR(40, CombatType.MELEE, 6, -1, -1, 2, new ResourceList(), new TechnologyList(), new TraitsList()),
	WORKER(70, CombatType.CIVILIAN, -1, -1, -1, 2, new ResourceList(), new TechnologyList(), new TraitsList(UnitTraits.CAN_CREATE_AND_REPAIR)),

	// Classical Era
	CATAPULT(100, CombatType.SIEGE, 4, 14, 2, 2, new ResourceList(ResourceType.IRON), new TechnologyList(TechnologyType.MATHEMATICS), new TraitsList(UnitTraits.NO_MELEE, UnitTraits.BONUS_VS_CITY, UnitTraits.NO_DEFENSIVE_BONUS, UnitTraits.LIMITED_VISIBILITY, UnitTraits.NEED_SETUP)),
	HORSEMAN(80, CombatType.MOUNTED, 12, -1, -1, 4, new ResourceList(ResourceType.HORSES), new TechnologyList(TechnologyType.HORSEBACK_RIDING), new TraitsList(UnitTraits.NO_DEFENSIVE_BONUS, UnitTraits.MOVE_AFTER_ATTACK)),
	SWORDSMAN(80, CombatType.MELEE, 11, -1, -1, 2, new ResourceList(ResourceType.IRON), new TechnologyList(TechnologyType.IRON_WORKING), new TraitsList()),

	// Medieval Era
	CROSSBOW_MAN(120, CombatType.ARCHERY, 6, 12, 2, 2, new ResourceList(), new TechnologyList(TechnologyType.MACHINERY), new TraitsList(UnitTraits.NO_MELEE)),
	KNIGHT(150, CombatType.MOUNTED, 18, -1, -1, 3, new ResourceList(ResourceType.HORSES), new TechnologyList(TechnologyType.CHIVALRY), new TraitsList(UnitTraits.NO_DEFENSIVE_BONUS, UnitTraits.MOVE_AFTER_ATTACK)),
	LONG_SWORDSMAN(150, CombatType.MELEE, 18, -1, -1, 3, new ResourceList(ResourceType.IRON), new TechnologyList(TechnologyType.STEEL), new TraitsList()),
	PIKE_MAN(100, CombatType.MELEE, 10, -1, -1, 2, new ResourceList(), new TechnologyList(TechnologyType.CIVIL_SERVICE), new TraitsList(UnitTraits.BONUS_VS_MOUNTED)),
	TREBUCHET(170, CombatType.SIEGE, 6, 20, 2, 2, new ResourceList(ResourceType.IRON), new TechnologyList(TechnologyType.PHYSICS), new TraitsList(UnitTraits.NO_MELEE, UnitTraits.BONUS_VS_CITY, UnitTraits.NO_DEFENSIVE_BONUS, UnitTraits.NEED_SETUP)),

	// Renaissance Era
	CANON(250, CombatType.SIEGE, 10, 26, 2, 2, new ResourceList(), new TechnologyList(TechnologyType.CHEMISTRY), new TraitsList(UnitTraits.NO_MELEE, UnitTraits.BONUS_VS_CITY, UnitTraits.NO_DEFENSIVE_BONUS, UnitTraits.NEED_SETUP)),
	CAVALRY(260, CombatType.MOUNTED, 25, -1, -1, 3, new ResourceList(ResourceType.HORSES), new TechnologyList(TechnologyType.MILITARY_SCIENCE), new TraitsList(UnitTraits.NO_DEFENSIVE_BONUS, UnitTraits.MOVE_AFTER_ATTACK)),
	LANCER(220, CombatType.MOUNTED, 22, -1, -1, 4, new ResourceList(ResourceType.HORSES), new TechnologyList(TechnologyType.METALLURGY), new TraitsList(UnitTraits.NO_DEFENSIVE_BONUS, UnitTraits.MOVE_AFTER_ATTACK)),
	MUSKET_MAN(120, CombatType.GUNPOWDER, 16, -1, -1, 2, new ResourceList(), new TechnologyList(TechnologyType.GUNPOWDER), new TraitsList()),
	RIFLE_MAN(200, CombatType.GUNPOWDER, 25, -1, -1, 2, new ResourceList(), new TechnologyList(TechnologyType.RIFLING), new TraitsList()),

	// Industrial Era
	ANTI_TANK_GUN(300, CombatType.GUNPOWDER, 32, -1, -1, 2, new ResourceList(), new TechnologyList(TechnologyType.REPLACEABLE_PARTS), new TraitsList(UnitTraits.BONUS_VS_TANK)),
	ARTILLERY(420, CombatType.SIEGE, 16, 32, 3, 2, new ResourceList(), new TechnologyList(TechnologyType.DYNAMITE), new TraitsList(UnitTraits.INDIRECT_FIRE, UnitTraits.NO_MELEE, UnitTraits.BONUS_VS_CITY, UnitTraits.NEED_SETUP)),
	INFANTRY(300, CombatType.GUNPOWDER, 36, -1, -1, 2, new ResourceList(), new TechnologyList(TechnologyType.REPLACEABLE_PARTS), new TraitsList()),
	PANZER(450, CombatType.ARMORED, 60, -1, -1, 5, new ResourceList(), new TechnologyList(TechnologyType.COMBUSTION), new TraitsList(UnitTraits.NO_DEFENSIVE_BONUS, UnitTraits.LIMITED_VISIBILITY, UnitTraits.MOVE_AFTER_ATTACK)),
	TANK(450, CombatType.ARMORED, 50, -1, -1, 4, new ResourceList(), new TechnologyList(TechnologyType.COMBUSTION), new TraitsList(UnitTraits.PENALTY_VS_CITIES, UnitTraits.NO_DEFENSIVE_BONUS, UnitTraits.MOVE_AFTER_ATTACK));

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

		Producible.productions.add(this);
	}

	public boolean canCreate(City city) {
		return requiredTechs.isResearched(city.getCivilization());
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

	@Override
	public int getCost(City city) {
		// TODO : implement city effect on cost
		return this.cost;
	}

	@Override
	public void produce(City city) {
		return;
	}

	@Override
	public boolean isProducible(City city) {
		return false;
	}
}
