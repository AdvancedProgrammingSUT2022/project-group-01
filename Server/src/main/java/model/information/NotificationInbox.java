package model.information;

import lombok.Getter;
import model.Notification;

import java.util.Vector;

public class NotificationInbox {
	@Getter
	Vector<Notification> notifications;

	public NotificationInbox() {
		this.notifications = new Vector<>();
	}

	public void addNotification(Notification notification) {
		this.notifications.add(notification);
	}

	public boolean isInboxEmpty() {
		return this.notifications.isEmpty();
	}

	public void markAllAsRead() {
		for (Notification notification : notifications)
			notification.readNotification();
	}

	public void setUnsetTurns(int i) {
		for (Notification notification : notifications) {
			if (notification.getAnnouncementTurn() == -1)
				notification.setAnnouncementTurn(i);
		}
	}


}
