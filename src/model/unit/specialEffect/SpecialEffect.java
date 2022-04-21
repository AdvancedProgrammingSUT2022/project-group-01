package model.unit.specialEffect;

import model.technology.TechnologyType;

import java.util.List;

public class SpecialEffect {
	private List<SpecialEffectType> effects;

	public SpecialEffect(SpecialEffectType... effects){
		this.effects = List.of(effects);
	}

	public boolean isResearched(){
		return true;
	}
}
