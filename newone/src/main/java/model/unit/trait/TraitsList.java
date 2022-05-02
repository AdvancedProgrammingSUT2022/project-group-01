package model.unit.trait;

import java.util.List;
import java.util.Vector;


/**
 * TraitsList is used to store list of Traits
 *
 * @author Ali Safari
 */
public class TraitsList {
	/**
	 * a container that store trait
	 */
	private final Vector<UnitTraits> traits;

	/**
	 * construct new Traits list which elements are trait
	 *
	 * @param traits sequence of UnitTraits Objects
	 */
	public TraitsList(UnitTraits... traits) {
		this.traits = new Vector<>(List.of(traits));
	}

	/**
	 * @param unitTraits to check that this trait is in the list of not
	 * @return true if this list contains given trait, false otherwise
	 */
	public boolean contains(UnitTraits unitTraits) {
		for (UnitTraits trait : traits) {
			if (trait == unitTraits)
				return true;
		}
		return false;
	}

	/**
	 * method to add new Trait to this TraitList
	 *
	 * @param trait new Trait
	 */
	public void addTrait(UnitTraits trait) {
		traits.add(trait);
	}

	/**
	 * @return vector of Traits stored in this Object
	 */
	public Vector<UnitTraits> getTechs() {
		return traits;
	}
}
