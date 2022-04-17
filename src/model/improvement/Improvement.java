package improvement;
import technology.TechnologyType;

import java.util.Vector;

public class Improvement {

	private ImprovementType type;

	public ImprovementType getType() {
		return type;
	}

	public void setType(ImprovementType type) {
		this.type = type;
	}

	/**
	 * 
	 * @param type
	 */
	public Improvement(ImprovementType type) {
		// TODO - implement Improvement.Improvement
		throw new UnsupportedOperationException();
	}

	public int getFoodYield() {
		// TODO - implement Improvement.getFoodYield
		throw new UnsupportedOperationException();
	}

	public int getGoldYield() {
		// TODO - implement Improvement.getGoldYield
		throw new UnsupportedOperationException();
	}

	public int getProductionYield() {
		// TODO - implement Improvement.getProductionYield
		throw new UnsupportedOperationException();
	}
	public TechnologyType getNecessaryTechnology(){
		return this.type.preRequisiteTech;
	}
	public Vector<ResourceType> affecctingResources (){
		return this.type.affectingResources;
	}

}