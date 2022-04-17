package technology;

import java.util.List;

public class TechnologyList {
	private List<TechnologyType> techs;

	public TechnologyList(TechnologyType... techs){
		this.techs = List.of(techs);
	}

	public boolean isResearched(){
		return true;
	}
}
