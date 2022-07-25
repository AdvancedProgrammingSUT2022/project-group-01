package model.civilization;

public enum TradeList {
	GOLD() {
		@Override
		public void effect(Civilization firstCivilization, Civilization secondCivilization) {

		}

		@Override
		public void isValidTrade(Civilization firstCivilization, Civilization secondCivilization) {

		}
	},
	ACCEPT_EMBASSY() {
		@Override
		public void effect(Civilization firstCivilization, Civilization secondCivilization) {

		}

		@Override
		public void isValidTrade(Civilization firstCivilization, Civilization secondCivilization) {

		}
	},
	SOMETHING_ELSE() {

	};

	public void effect(Civilization firstCivilization, Civilization secondCivilization) {

	}

	public void isValidTrade(Civilization firstCivilization, Civilization secondCivilization) {

	}


}
