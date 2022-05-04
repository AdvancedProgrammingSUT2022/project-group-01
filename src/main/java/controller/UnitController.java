package controller;

import model.Game;
import model.tile.Tile;
import model.unit.Unit;
import model.unit.armed.Armed;
import model.unit.armed.Siege;
import model.unit.trait.TraitsList;
import model.unit.trait.UnitTraits;

import javax.security.auth.kerberos.KerberosTicket;
import java.util.ArrayList;
import java.util.HashMap;


/**
 * UnitController handle all Unit related commands
 *
 * @author Ali Safari
 */
public class UnitController {
	private Game game;

	public UnitController(Game game){
		this.game = game;
	}

	/**
	 * private method to get selected unit
	 * @return current selected unit
	 */
	private Unit getSelectedUnit(){
		return game.getSelectedUnit();
	}

	/**
	 * move selected unit to given destination
	 * @param args required arg is "number : destination tile number"
	 * @return result of this action as a String
	 */
	public String moveUnit(HashMap<String, String> args){
		int number = Integer.parseInt(args.get("number"));
		Tile destTile = game.getMap().getTileByNumber(number);
		Unit unit = getSelectedUnit();
		if(unit == null)
			return "no unit is selected !";
		if(unit.getOwnerCivilization() != game.getCurrentPlayer().getCivilization())
			return "this is not your unit !";
		// TODO : add description for error !
		if(!unit.isReachable(destTile))
			return "there is no path to destination !";
		unit.goTo(destTile);
		return "unit is on his/her way to destination :)";
	}

	/**
	 * unit is sleeping until wake or cancel command
	 * @param args None
	 * @return result of this action as String
	 */
	public String sleepUnit(HashMap<String, String> args){
		Unit unit = getSelectedUnit();
		if(unit == null)
			return "no unit is selected !";
		if(unit.getOwnerCivilization() != game.getCurrentPlayer().getCivilization())
			return "this is not your unit !";

		unit.sleep();
		return "unit is sleeping. Zzz";
	}

	/**
	 * unit doesn't need command until some enemy is nearby
	 * or cancel mission is given
	 *
	 * @param args None
	 * @return result of action as String
	 */
	public String alertUnit(HashMap<String, String> args){
		Unit unit = getSelectedUnit();
		if(unit == null)
			return "no unit is selected !";
		if(unit.getOwnerCivilization() != game.getCurrentPlayer().getCivilization())
			return "this is not your unit !";
		if(!(unit instanceof Armed))
			return "this unit is just shattered Civilian :(";
		unit.alert();
		return "unit is completely alert";
	}

	/**
	 * fortify unit for one turn, also give defensive bonus
	 *
	 * @param args None
	 * @return result of action as String
	 */
	public String fortify(HashMap<String, String> args){
		Unit unit = getSelectedUnit();
		if(unit == null)
			return "no unit is selected !";
		if(unit.getOwnerCivilization() != game.getCurrentPlayer().getCivilization())
			return "this is not your unit !";
		if(!(unit instanceof Armed))
			return "this unit is just shattered Civilian :(";
		unit.fortify();
		return "-- fortified --";
	}

	/**
	 * fortify unit until complete heal, also give defensive bonus
	 *
	 * @param args None
	 * @return result of action as String
	 */
	public String fortifyUntilHeal(HashMap<String, String> args){
		Unit unit = getSelectedUnit();
		if(unit == null)
			return "no unit is selected !";
		if(unit.getOwnerCivilization() != game.getCurrentPlayer().getCivilization())
			return "this is not your unit !";
		if(!(unit instanceof Armed))
			return "this unit is just shattered Civilian :(";
		unit.fortifyUntilHeal();
		return "-- fortified until heal --";
	}


	/**
	 * setup unit for attack,
	 * this is necessary for Siege to be able to attack
	 *
	 * @param args None
	 * @return result of action as String
	 */
	public String setup(HashMap<String, String> args){
		Unit unit = getSelectedUnit();
		if(unit == null)
			return "no unit is selected !";
		if(unit.getOwnerCivilization() != game.getCurrentPlayer().getCivilization())
			return "this is not your unit !";
		if(!(unit instanceof Armed))
			return "this unit is just shattered Civilian :(";
		if (!unit.getTraitsList().contains(UnitTraits.NEED_SETUP))
			return "this unit doesn't need setup for attack";

		((Siege) unit).setup();
		return "Sir, this unit is ready for attack command";
	}

	/**
	 * cancel all stacked commands
	 *
	 * @param args None
	 * @return result of action as String
	 */
	public String cancel(HashMap<String, String> args){
		Unit unit = getSelectedUnit();
		if(unit == null)
			return "no unit is selected !";
		if(unit.getOwnerCivilization() != game.getCurrentPlayer().getCivilization())
			return "this is not your unit !";

		unit.cancel();
		return "Action Complete";
	}

	/**
	 * wake unit up if unit is sleeping
	 *
	 * @param args None
	 * @return result of action as String
	 */
	public String wake(HashMap<String, String> args){
		Unit unit = getSelectedUnit();
		if(unit == null)
			return "no unit is selected !";
		if(unit.getOwnerCivilization() != game.getCurrentPlayer().getCivilization())
			return "this is not your unit !";

		unit.wake();
		return "Good Morning";
	}

	/**
	 * kills the unit
	 *
	 * @param args None
	 * @return result of action as String
	 */
	public String delete(HashMap<String, String> args){
		Unit unit = getSelectedUnit();
		if(unit == null)
			return "no unit is selected !";
		if(unit.getOwnerCivilization() != game.getCurrentPlayer().getCivilization())
			return "this is not your unit !";

		unit.suicide();
		return "RIP";
	}

}
