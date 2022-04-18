package model.technology;

import java.util.HashMap;
import java.util.Vector;

public class TechTree {
	private HashMap<TechnologyType, Integer> researchedTechs;
	private TechnologyType currentResearch;
	public TechTree(){
		researchedTechs = new HashMap<>();
		currentResearch = null;
		for (TechnologyType tech : TechnologyType.values())
			researchedTechs.put(tech, 0);
	}
	public Vector<TechnologyType> getResearchableTechs(){
		return null;
	}
	public void research(TechnologyType tech){}
	public void changeResearch(TechnologyType newTech){}
	public void addScience(){}
	public boolean isResearched(TechnologyType tech){ return false; }
	public boolean isResearched(TechnologyList techs){ return false; }
	public boolean isResearching(){ return false; }
}
