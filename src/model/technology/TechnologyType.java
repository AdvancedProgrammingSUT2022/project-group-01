package technology;

import java.util.List;

public enum TechnologyType {
	// Ancient Era
	AGRICULTURE(20, new TechnologyList()),
	ANIMAL_HUSBANDRY(35, new TechnologyList(AGRICULTURE)),
	ARCHERY(35, new TechnologyList(AGRICULTURE)),
	MINING(35, new TechnologyList(AGRICULTURE)),
	POTTERY(35, new TechnologyList(AGRICULTURE)),
	MASONRY(55, new TechnologyList(MINING)),
	BRONZE_WORKING(55, new TechnologyList(MINING)),
	CALENDAR(70, new TechnologyList(POTTERY)),
	THE_WHEEL(55, new TechnologyList(ANIMAL_HUSBANDRY)),
	TRAPPING(55, new TechnologyList(ANIMAL_HUSBANDRY)),
	WRITING(55, new TechnologyList(POTTERY)),

	// Classical Era
	CONSTRUCTION(100, new TechnologyList(MASONRY)),
	HORSEBACK_RIDING(100, new TechnologyList(MASONRY)),
	IRON_WORKING(100, new TechnologyList(BRONZE_WORKING)),
	MATHEMATICS(100, new TechnologyList(THE_WHEEL, ARCHERY)),
	PHILOSOPHY(100, new TechnologyList(WRITING)),

	// Medieval Era
	CURRENCY(250, new TechnologyList(MATHEMATICS)),
	METAL_CASTING(240, new TechnologyList(IRON_WORKING)),
	ENGINEERING(250, new TechnologyList(MATHEMATICS, CONSTRUCTION)),
	CIVIL_SERVICE(400, new TechnologyList(PHILOSOPHY, TRAPPING)),
	THEOLOGY(250, new TechnologyList(CALENDAR, PHILOSOPHY)),
	STEEL(440, new TechnologyList(METAL_CASTING)),
	PHYSICS(440, new TechnologyList(ENGINEERING, METAL_CASTING)),
	MACHINERY(440, new TechnologyList(ENGINEERING)),
	EDUCATION(440, new TechnologyList(THEOLOGY)),
	CHIVALRY(440, new TechnologyList(CIVIL_SERVICE, HORSEBACK_RIDING, CURRENCY)),

	// Renaissance Era
	ACOUSTICS(650, new TechnologyList(EDUCATION)),
	ARCHAEOLOGY(1300, new TechnologyList(ACOUSTICS)),
	BANKING(650, new TechnologyList(EDUCATION, CHIVALRY)),
	GUNPOWDER(680, new TechnologyList(PHYSICS, STEEL)),
	METALLURGY(900, new TechnologyList(GUNPOWDER)),
	RIFLING(1425, new TechnologyList(METALLURGY)),
	SCIENTIFIC_THEORY(1300, new TechnologyList(ACOUSTICS)),
	PRINTING_PRESS(650, new TechnologyList(MACHINERY, PHYSICS)),
	CHEMISTRY(900, new TechnologyList(GUNPOWDER)),
	ECONOMICS(900, new TechnologyList(BANKING, PRINTING_PRESS)),
	FERTILIZER(1300, new TechnologyList(CHEMISTRY)),
	MILITARY_SCIENCE(1300, new TechnologyList(ECONOMICS, CHEMISTRY)),

	// Industrial Era
	BIOLOGY(1680, new TechnologyList(ARCHAEOLOGY, SCIENTIFIC_THEORY)),
	DYNAMITE(1900, new TechnologyList(FERTILIZER, RIFLING)),
	STEAM_POWER(1680, new TechnologyList(SCIENTIFIC_THEORY, MILITARY_SCIENCE)),
	ELECTRICITY(1900, new TechnologyList(BIOLOGY, STEAM_POWER)),
	RADIO(2200, new TechnologyList(ELECTRICITY)),
	RAILROAD(1900, new TechnologyList(STEAM_POWER)),
	REPLACEABLE_PARTS(1900, new TechnologyList(STEAM_POWER)),
	TELEGRAPH(2200, new TechnologyList(ELECTRICITY)),
	COMBUSTION(2200, new TechnologyList(REPLACEABLE_PARTS, RAILROAD, DYNAMITE));

	static { // TODO
		AGRICULTURE.unlocks = List.of();
		ANIMAL_HUSBANDRY.unlocks = List.of();
		ARCHERY.unlocks = List.of();
		MINING.unlocks = List.of();
		POTTERY.unlocks = List.of();
		MASONRY.unlocks = List.of();
		BRONZE_WORKING.unlocks = List.of();
		CALENDAR.unlocks = List.of();
		THE_WHEEL.unlocks = List.of();
		TRAPPING.unlocks = List.of();
		WRITING.unlocks = List.of();

		// Classical Era
		CONSTRUCTION.unlocks = List.of();
		HORSEBACK_RIDING.unlocks = List.of();
		IRON_WORKING.unlocks = List.of();
		MATHEMATICS.unlocks = List.of();
		PHILOSOPHY.unlocks = List.of();

		// Medieval Era
		CURRENCY.unlocks = List.of();
		METAL_CASTING.unlocks = List.of();
		ENGINEERING.unlocks = List.of();
		CIVIL_SERVICE.unlocks = List.of();
		THEOLOGY.unlocks = List.of();
		STEEL.unlocks = List.of();
		PHYSICS.unlocks = List.of();
		MACHINERY.unlocks = List.of();
		EDUCATION.unlocks = List.of();
		CHIVALRY.unlocks = List.of();

		// Renaissance Era
		ACOUSTICS.unlocks = List.of();
		ARCHAEOLOGY.unlocks = List.of();
		BANKING.unlocks = List.of();
		GUNPOWDER.unlocks = List.of();
		METALLURGY.unlocks = List.of();
		RIFLING.unlocks = List.of();
		SCIENTIFIC_THEORY.unlocks = List.of();
		PRINTING_PRESS.unlocks = List.of();
		CHEMISTRY.unlocks = List.of();
		ECONOMICS.unlocks = List.of();
		FERTILIZER.unlocks = List.of();
		MILITARY_SCIENCE.unlocks = List.of();

		// Industrial Era
		BIOLOGY.unlocks = List.of();
		DYNAMITE.unlocks = List.of();
		STEAM_POWER.unlocks = List.of();
		ELECTRICITY.unlocks = List.of();
		RADIO.unlocks = List.of();
		RAILROAD.unlocks = List.of();
		REPLACEABLE_PARTS.unlocks = List.of();
		TELEGRAPH.unlocks = List.of();
		COMBUSTION.unlocks = List.of();
	}

	private final int cost;
	private final TechnologyList prerequisiteTechs;
	private List<Object> unlocks;
	TechnologyType(int cost, TechnologyList prerequisiteTechs){
		this.cost = cost;
		this.prerequisiteTechs = prerequisiteTechs;
	}
}
