package model.civilization;

import model.map.ConsoleMap;

//TODO added COlOR and its getter
public enum Civilizations {
	MONGOLIAN("Mongolian", "ICON.jpg", "Genghis Khan", ConsoleMap.colorCharacter.PURPLE, new String[]{"Karakorum", "Beshbalik","Turfan","Hsia","Old Sarai"}),
	MAYAN("Mayan","ICON2.jpg", "Pacal",ConsoleMap.colorCharacter.RED,new String[]{"Palenque","Tikal","Chichen Itza","Uxmal","Tulum"}),
	EGYPTIAN("Egyptian","ICON2.jpg", "Ramesses II",ConsoleMap.colorCharacter.GREEN,new String[]{"Thebes","Memphis","Heliopolis","Elephantine","Alexandria"}),
	CHINESE("Chinese","ICON2.jpg", "Wu Zetian",ConsoleMap.colorCharacter.YELLOW,new String[]{"Beijing","Shanghai","Guangzhou","Nanjing","Xian","Chengdu"}),
	AZTECH("Aztech","ICON2.jpg", "Montezuma",ConsoleMap.colorCharacter.BRIGHT_WHITE,new String[]{"Tenochtitlan","Teotihuacan","Tlatelolco","Texcoco","Tlaxcala"}),
	ASSYRIAN("Assyrian","ICON2.jpg", "Ashurbanipal",ConsoleMap.colorCharacter.BLACK,new String[]{"Assur","Nineveh","Nimrud","Halab","Carchemish","Kanesh"}),
	PERSIAN("Persian","ICON2.jpg", "Darius I",ConsoleMap.colorCharacter.BLUE,new String[]{"Persepolis","Pasargadae","Susa","Ecbatana","Tarsus"});
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
}