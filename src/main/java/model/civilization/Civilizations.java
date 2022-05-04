package model.civilization;

import model.map.ConsoleMap;

//TODO added COlOR and its getter
public enum Civilizations {
	CIVILIZATION_ONE("FIRST NAME", "ICON.jpg", "LEADER_NAME", ConsoleMap.colorCharacter.PURPLE, new String[]{"city1", "city2"}),
	CIVILIZATION_TWO("NAMEEE","ICON2.jpg", "LEADER_TWO",ConsoleMap.colorCharacter.RED,new String[]{});
	//TODO add others here

	String name;
	String picture;
	String leaderName;
	ConsoleMap.colorCharacter color;
	String[] cityNames;
	Civilizations(String name, String picture, String leaderName, ConsoleMap.colorCharacter color, String[] cityNames) {
		this.name = name;
		this.picture = picture;
		this.leaderName = leaderName;
		this.color = color;
		this.cityNames = cityNames;
	}

	public ConsoleMap.colorCharacter getColor() {
		return color;
	}

	public String[] getCityNames() {
		return cityNames;
	}
}