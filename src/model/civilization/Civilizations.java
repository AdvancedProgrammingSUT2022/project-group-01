package model.civilization;

public enum Civilizations {
	CIVILIZATION_ONE("FIRST NAME", "ICON.jpg", "LEADER_NAME");
	//TODO add others here

	String name;
	String picture;
	String leaderName;

	Civilizations(String name, String picture, String leaderName) {
		this.name = name;
		this.picture = picture;
		this.leaderName = leaderName;
	}

}