package model;

import lombok.Getter;
import lombok.Setter;
import model.civilization.city.City;
import model.tile.Tile;
import view.components.popup.PopUp;
import view.components.popup.PopUpStates;

@Getter @Setter
public class Notification {
	private String text;
	private boolean isRead;
	@Setter
	private int announcementTurn;
	public Notification(String text, int announcementTurn){
		this.text = text;
		isRead = false;
		this.announcementTurn = announcementTurn;
		new PopUp().run(PopUpStates.WARNING, text);
	}

	public Notification(Tile tile,NotificationTexts notificationTexts){
		if(notificationTexts.equals(NotificationTexts.IMPROVEMENT_BUILT))
			this.text = NotificationTexts.IMPROVEMENT_BUILT.getImprovementBuiltText(tile);
		isRead = false;
		this.announcementTurn = -1;
	}
	public Notification(City city,NotificationTexts notificationTexts){
		if(notificationTexts.equals(NotificationTexts.PRODUCTION_BUILT))
			this.text = NotificationTexts.PRODUCTION_BUILT.getProductionBuiltText(city);
		isRead = false;
		this.announcementTurn = -1;
	}

	public void readNotification(){
		this.isRead = true;
	}
	public String getText(){
		return text;
	}

	public enum NotificationTexts{
		ZERO_GOLD("You have no gold! Go study the documentation to learn how to play."),
		NEARBY_ENEMY("You have an enemy near your tiles!"),
		GETTING_ATTACKED("You are getting attacked."),
		ZERO_FOOD("You have no food!"),
		ZERO_PRODUCTION("You have no production"),
		HAPPINESS_ALERT("A city in your civilization is unhappy. Do something immediately to prevent a rebel."),
		DUMB("U SUCK ..."),
		IMPROVEMENT_BUILT("The improvement in build on tile number : "),
		PRODUCTION_BUILT("A production is built on your city ");

		private final String text;
		NotificationTexts(String text){
			this.text = text;
		}

		public String getImprovementBuiltText(Tile tile){
			return text + tile.getMapNumber();
		}
		public String getProductionBuiltText(City city){
			return text + city.getName();
		}


		public String getText(){
			return this.text;
					}
	}
}
