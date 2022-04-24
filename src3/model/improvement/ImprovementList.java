package model.improvement;

import java.util.List;

public class ImprovementList {
	private List<ImprovementType> improvements;

	public ImprovementList(ImprovementType... improvement){
		this.improvements = List.of(improvement);
	}
	public boolean isResearched(){
		return true;
	}

}