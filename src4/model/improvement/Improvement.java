package model.improvement;
import model.civilization.production.Production;
import model.resource.ResourceType;
import model.technology.TechnologyType;


import java.util.Vector;

public class Improvement extends Production {

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
		this.type = type;
	}

	public int getFoodYield() {
		return this.type.foodYield;
	}

	public int getGoldYield() {
		return this.type.goldYield;
	}

	public int getProductionYield() {
		return this.type.productionYield;
	}
	public TechnologyType getNecessaryTechnology(){
		return this.type.preRequisiteTech;
	}
	public Vector<ResourceType> affectingResources (){
		return this.type.affectingResources;
	}

}