package model.technology;

import model.civilization.city.City;

import java.util.List;

public class TechnologyList {
	private List<TechnologyType> techs;

	public TechnologyList(TechnologyType... techs){
		this.techs = List.of(techs);
	}

	public boolean isResearched(City city){
		return city.getCivilization().getResearchTree().isResearched(this);
	}

	public void addTech(TechnologyType tech){
		techs.add(tech);
	}

	public List<TechnologyType> getTechs() {
		return techs;
	}
}
