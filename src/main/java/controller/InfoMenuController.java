package controller;

import model.Information;
import model.Notification;
import model.civilization.Civilization;
import model.technology.TechnologyType;
import model.unit.Unit;

import java.util.Vector;

public class InfoMenuController {
	private Information information;
	public InfoMenuController(Civilization civilization){
		information = new Information(civilization);
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
}
