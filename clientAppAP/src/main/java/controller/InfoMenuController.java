package controller;

import model.Information;
import model.Notification;
import model.civilization.Civilization;
import model.technology.TechnologyType;
import model.unit.Unit;
import model.Game;

import java.util.HashMap;
import java.util.Vector;

public class InfoMenuController {
	private Information information;
	private InfoSubMenu currSubMenu;
	private Game game;
	public InfoMenuController(Game game){
		information = new Information(game);
		currSubMenu = InfoSubMenu.HOME;
		this.game = game;
	}
	public TechnologyType getCurrentResearch(){return null; }
	public int getResearchRemainingTurn(){return 0; }

	public Vector<Unit> getAllUnits(){ return null; }
	public Vector<Unit> getAllCities(){ return null; }

	public int getGameScore(){ return 0; }
	public Vector<Civilization> getOtherCivilizations(){ return null; }

	public Vector<Notification> getNotifications(){return null; }
	public Information getInfo(){
		information.updateInformation();
		return information;
	}

	public String menuEnter(HashMap<String, String> args){
		String subMenu = args.get("section");
		switch(subMenu){
			case "research":{
				currSubMenu = InfoSubMenu.RESEARCH;
				return game.getInformationPanel().researchInfo();
			}
			case "unit":{
				currSubMenu = InfoSubMenu.UNIT;
				return game.getInformationPanel().unitListPanel();
			}
			case "city":{
				currSubMenu = InfoSubMenu.CITY;
				return game.getInformationPanel().cityListPanel();
			}
			case "notification":{
				return game.getInformationPanel().getNotificationsHistory();
			}
			case "demographic":{
				return game.getInformationPanel().demographicScreen();
			}
			default:{
				return "invalid sub menu, options are: {research, unit, city, notification, demographic}\n or if you are in a sub menu run 'menu exit'";
			}
		}
	}

	public String menuExit(HashMap<String, String> args){
		if(currSubMenu == InfoSubMenu.HOME){
			ProgramController.setCurrentMenu(Menus.GAME_MENU);
		}else{
			currSubMenu = InfoSubMenu.HOME;
		}
		return "done!";
	}

	public String currentMenu(HashMap<String, String> args){
		if(currSubMenu == InfoSubMenu.HOME)
			return "information menu";
		else
			return currSubMenu.name().toLowerCase() + " panel";
	}

	public String activeUnit(HashMap<String, String> args){
		if(currSubMenu != InfoSubMenu.UNIT)
			return "invalid command!";
		int index = Integer.parseInt(args.get("section"));
		return game.getInformationPanel().activeUnit(index);
	}

	public String militaryOverview(HashMap<String, String> args){
		if(currSubMenu != InfoSubMenu.UNIT)
			return "invalid command!";
		return game.getInformationPanel().militaryOverview();
	}

	public String cityScreen(HashMap<String, String> args){
		if(currSubMenu != InfoSubMenu.CITY)
			return "invalid command!";
		String cityName = args.get("name");
		return game.getInformationPanel().getCityPanelByName(cityName);
	}

	public String economicOverview(HashMap<String, String> args){
		if(currSubMenu != InfoSubMenu.CITY)
			return "invalid command!";
		return game.getInformationPanel().economicOverview();
	}

}
enum InfoSubMenu{
	HOME,
	RESEARCH,
	UNIT,
	CITY,
}