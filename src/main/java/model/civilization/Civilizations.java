package model.civilization;
//TODO added COlOR and its getter
public enum Civilizations {
	CIVILIZATION_ONE("FIRST NAME", "ICON.jpg", "LEADER_NAME", "Color", new String[]{"city1", "city2"});
	//TODO add others here

	String name;
	String picture;
	String leaderName;
	String color;
	String[] cityNames;
	Civilizations(String name, String picture, String leaderName, String color, String[] cityNames) {
		this.name = name;
		this.picture = picture;
		this.leaderName = leaderName;
		this.color = color;
		this.cityNames = cityNames;
	}

	public String getColor() {
		return color;
	}

	public String[] getCityNames() {
		return cityNames;
	}
}