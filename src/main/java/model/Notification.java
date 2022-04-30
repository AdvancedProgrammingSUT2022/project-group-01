package model;

public class Notification {
	private String text;
	private boolean isRead;
	public Notification(String text){
		this.text = text;
		isRead = false;
	}

	public void readNotification(){}
	public String getText(){
		return text;
	}
}
