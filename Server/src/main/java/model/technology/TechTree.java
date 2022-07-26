package model.technology;



import lombok.Getter;

import java.util.HashMap;
import java.util.Vector;

@Getter
public class TechTree {
	private HashMap<TechnologyType, Integer> researchedTechs;
	private TechnologyType currentResearch;

	public TechTree() {
		researchedTechs = new HashMap<>();
		currentResearch = null;
		for (TechnologyType tech : TechnologyType.values())
			researchedTechs.put(tech, 0);
	}

	public boolean isResearchable(TechnologyType type) {
		if(isResearched(type)) return false;
		return isResearched(type.getPrerequisiteTechs());
	}

	public Vector<TechnologyType> getResearchableTechs() {
		Vector<TechnologyType> researchableTechs = new Vector<>();
		for (TechnologyType value : TechnologyType.values())
			if(isResearchable(value))
				researchableTechs.add(value);
		return researchableTechs;
	}

	public void research(TechnologyType tech) {
		if(!isResearchable(tech)) return;
		currentResearch = tech;
	}

	public void changeResearch(TechnologyType newTech) {
		if(!isResearchable(newTech)) return ;
		currentResearch = newTech;
	}

	public void addScience(int science) {
		if(currentResearch == null) return ;
		researchedTechs.put(currentResearch, researchedTechs.get(currentResearch) + science);
		if(isResearched(currentResearch))
			currentResearch = null;
	}

	public boolean isResearched(TechnologyType tech) {
		if(tech == null)
			return true;
		return researchedTechs.get(tech) >= tech.getCost();
	}

	public boolean isResearched(TechnologyList techs) {
		for (TechnologyType tech : techs.getTechs())
			if (!isResearched(tech))
				return false;
		return true;
	}

	public boolean isResearching() {
		return currentResearch != null;
	}

	public void completeResearch(TechnologyType technologyType) {
		researchedTechs.put(technologyType, technologyType.getCost());
	}

	public Integer getRemainingScience(){
		if(currentResearch == null) return null;
		return Math.max(0, currentResearch.getCost() - researchedTechs.get(currentResearch));
	}
}
