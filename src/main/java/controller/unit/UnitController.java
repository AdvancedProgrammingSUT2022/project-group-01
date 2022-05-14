package controller.unit;

import lombok.AllArgsConstructor;
import model.Game;
import model.civilization.Currency;
import model.tile.Tile;
import model.unit.Unit;
import model.unit.armed.Armed;
import model.unit.armed.RangedUnit;
import model.unit.armed.Siege;
import model.unit.civilian.Civilian;
import model.unit.civilian.Settler;
import model.unit.trait.UnitTraits;


@AllArgsConstructor
public class UnitController {
	private Game game;

	public String foundCity(Settler settler) {
		if (!settler.canSettle())
			return "Cannot Settle new city here";

		settler.settle();
		return "City Founded";
	}

	public String delete(Unit unit) {
		// gold refund TODO
		unit.getOwnerCivilization().increaseCurrency(new Currency(unit.getType().getRawCost() / 10f, 0, 0));
		unit.suicide();
		return "ma ro dor nandaz, ma inghadram be dard nakhor nistim";
	}

	public String sleep(Unit unit) {
		if (unit.isSleeping())
			return "this unit is already sleeping";
		unit.sleep();
		return "Zzz";
	}

	public String cancel(Unit unit) {
		if (!unit.hasJob())
			return "this unit is not doing anything\n";
		unit.cancel();
		return "actions canceled";
	}

	public String wake(Unit unit) {
		if (!unit.isSleeping())
			return "this unit is already awake";
		unit.wake();
		return "ready for orders";
	}

	public String info(Unit unit) {
		// more info maybe ? TODO
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(String.format("Owner is %s\n", unit.getOwnerCivilization().getCivilization().getName()));
		stringBuilder.append(String.format("Unit is at %d\n", unit.getCurrentTile().getMapNumber()));
		stringBuilder.append(String.format("Current Health is %d / 10\n", unit.getHealth()));
		stringBuilder.append(String.format("Unit Type : %s\n", unit.getType().toString()));
		stringBuilder.append(String.format("remaining Movement Point is %f\n", unit.getMovementPoint()));
		stringBuilder.append(String.format("current action is %s\n", unit.getJob() == null ? "null" : unit.getJob().toString()));
		return stringBuilder.toString();
	}

	public String fortify(Unit unit) {
		if (unit instanceof Civilian)
			return "this is not an armed unit";
		unit.fortify();
		return "fortified";
	}

	public String fortifyUntilHeal(Unit unit) {
		if (unit instanceof Civilian)
			return "this is not an armed unit";
		if (unit.getHealth() == Unit.maxHealth)
			return "already at max health";
		unit.fortifyUntilHeal();
		return "fortified until heal";
	}

	public String damage(Unit unit, int amount) {
		unit.changeHealth(-amount);
		return "damage applied successfully";
	}

	public String pillage(Unit unit) {
		Tile tile = unit.getCurrentTile();
		// maybe road ? todo
		if(tile.getBuiltImprovement() == null)
			return "there is nothing here to pillage";
		unit.pillage();
		return "your savage unit pillaged this tile !!";
	}

	public String teleport(Unit unit, Tile tile) {
		if(tile.getSameTypeUnit(unit) != null)
			return "you can't have 2 unit of same type in one tile";
		if(!tile.isPassable())
			return "this tile is not passable";
		unit.moveTo(tile);
		return "teleported";
	}

	public String alert(Unit unit) {
		if (unit instanceof Civilian)
			return "this is not an armed unit";
		unit.alert();
		return "alert\n";
	}

	public String meleeAttack(Armed armed, Tile tile) {
		if(armed.getTraitsList().contains(UnitTraits.NO_MELEE))
			return "this unit can't melee attack";
		if(!armed.getCurrentTile().getSight(1).contains(tile))
			return "this unit is not nearby";
		// combat unit, phase 2 TODO
		if(tile.getInnerCity() == null)
			return "no city in destination tile";
		if(tile.getInnerCity().getCivilization() == armed.getOwnerCivilization())
			return "why you want to betray your people ??";
		// combat with city todo

		double cityAttackModifier = 1;
		if(armed.getTraitsList().contains(UnitTraits.BONUS_VS_CITY))
			cityAttackModifier *= 1.25f;
		if(armed.getTraitsList().contains(UnitTraits.PENALTY_VS_CITIES))
			cityAttackModifier *= 0.5f;
		if(armed.getTraitsList().contains(UnitTraits.ROUGH_TERRAIN_PENALTY)
				&& (armed.getCurrentTile().getTerrain().isRough()
				|| (armed.getCurrentTile().getFeature() != null && armed.getCurrentTile().getFeature().isRough())))
			cityAttackModifier *= 0.5f;
		return "knives out";
	}

	public String rangedAttack(RangedUnit ranged, Tile tile) {
		if(ranged.getTraitsList().contains(UnitTraits.NEED_SETUP)
				&& !(((Siege) ranged).readyToAttack()))
			return "this unit need setup before attack";

		if(ranged.getTraitsList().contains(UnitTraits.NO_MELEE))
			return "this unit can't melee attack";
		boolean haveIndirectFire = ranged.getTraitsList().contains(UnitTraits.INDIRECT_FIRE);
		if(!ranged.getCurrentTile().getAttackingArea(ranged.getType().getRange(), haveIndirectFire).contains(tile))
			return "this unit is not in attack range";
		// combat unit, phase 2 TODO
		if(tile.getInnerCity() == null)
			return "no city in destination tile";
		if(tile.getInnerCity().getCivilization() == ranged.getOwnerCivilization())
			return "why you want to betray your people ??";
		// combat with city todo
		double cityAttackModifier = 1;
		if(ranged.getTraitsList().contains(UnitTraits.BONUS_VS_CITY))
			cityAttackModifier *= 1.25f;
		if(ranged.getTraitsList().contains(UnitTraits.PENALTY_VS_CITIES))
			cityAttackModifier *= 0.5f;
		if(ranged.getTraitsList().contains(UnitTraits.ROUGH_TERRAIN_PENALTY)
				&& (ranged.getCurrentTile().getTerrain().isRough()
				|| (ranged.getCurrentTile().getFeature() != null && ranged.getCurrentTile().getFeature().isRough())))
			cityAttackModifier *= 0.5f;
		return "bows out";
	}

	public String setup(Siege siege) {
		if(!siege.getTraitsList().contains(UnitTraits.NEED_SETUP))
			return "this type of siege doesn't need setup";
		if(siege.readyToAttack())
			return "this unit is  already ready to attack";
		siege.setup();
		return "setup unit done";
	}
}
