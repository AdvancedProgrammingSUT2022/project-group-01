package model.technology;

import model.civilization.Civilization;

import java.util.List;
import java.util.Vector;

/**
 * TechnologyList is used to store list of Technologies
 *
 * @author Ali Safari
 */
public class TechnologyList {
	/**
	 * a container that store Technologies
	 */
	private final Vector<TechnologyType> techs;

	/**
	 * construct new Technology list which elements are techs
	 *
	 * @param techs sequence of TechnologyType Objects
	 */
	public TechnologyList(TechnologyType... techs) {
		this.techs = new Vector<>(List.of(techs));
	}

	/**
	 * @param civilization this is the civilization to check that
	 *                     they have these Technologies or not
	 * @return true if every element of this List is researched, false otherwise
	 */
	public boolean isResearched(Civilization civilization) {
		return civilization.getResearchTree().isResearched(this);
	}

	/**
	 * method to add new Technology to this TechnologyList
	 *
	 * @param tech new Technology
	 */
	public void addTech(TechnologyType tech) {
		techs.add(tech);
	}

	/**
	 * @return vector of Technologies stored in this Object
	 */
	public Vector<TechnologyType> getTechs() {
		return techs;
	}
}
