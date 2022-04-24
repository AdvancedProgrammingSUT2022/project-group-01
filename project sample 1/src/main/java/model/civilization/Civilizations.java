package model.civilization;

public enum Civilizations {
	CIVILIZATION_ONE("FIRST NAME", "ICON.jpg", "LEADER_NAME", "red");
	//TODO add others here

	String name;
	String picture;
	String leaderName;
	String color;
	Civilizations(String name, String picture, String leaderName, String color) {
		this.name = name;
		this.picture = picture;
		this.leaderName = leaderName;
		this.color = color;
	}

}