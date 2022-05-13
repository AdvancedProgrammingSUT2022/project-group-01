package model;

import lombok.Getter;

public class Notification {
	private final String text;
	@Getter
	private boolean isRead;
	@Getter
	private final int announcementTurn;
	public Notification(String text, int announcementTurn){
		this.text = text;
		isRead = false;
		this.announcementTurn = announcementTurn;
	}

	public void readNotification(){}
	public String getText(){
		return text;
	}
	public void setRead(){
		isRead = true;
	}

	public enum NotificationTexts{
		ZERO_GOLD("You have no gold! Go study the documentation to learn how to play."),
		NEARBY_ENEMY("You have an enemy near your tiles!"),
		GETTING_ATTACKED("You are getting attacked."),
		ZERO_FOOD("You have no food!"),
		HAPPINESS_ALERT("A city in your civilization is unhappy. Do something immediately to prevent a rebel."),
		DUMB("U SUCK ...");

		private final String text;
		NotificationTexts(String text){
			this.text = text;
		}

		public String getText(){
			return this.text;
		}
	}
}
