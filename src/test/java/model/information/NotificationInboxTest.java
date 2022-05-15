package model.information;

import model.Notification;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Vector;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class NotificationInboxTest {

    private NotificationInbox notificationInboxUnderTest;

    @BeforeEach
    void setUp() {
        notificationInboxUnderTest = new NotificationInbox();
        notificationInboxUnderTest.notifications = mock(Vector.class);
    }

    @Test
    void testAddNotification() {
        // Setup
        final Notification notification = new Notification("text", 0);
        when(notificationInboxUnderTest.notifications.add(any(Notification.class))).thenReturn(false);

        // Run the test
        notificationInboxUnderTest.addNotification(notification);

        // Verify the results
        verify(notificationInboxUnderTest.notifications).add(any(Notification.class));
    }

    @Test
    void testIsInboxEmpty() {
        // Setup
        when(notificationInboxUnderTest.notifications.isEmpty()).thenReturn(false);

        // Run the test
        final boolean result = notificationInboxUnderTest.isInboxEmpty();

        // Verify the results
        assertFalse(result);
    }


    @Test
    void testSetUnsetTurns() {
        NotificationInbox notificationInbox1 = new NotificationInbox();
        Notification notification = new Notification(Notification.NotificationTexts.ZERO_GOLD.getText(),-1);
        notificationInbox1.addNotification(notification);
        notificationInbox1.setUnsetTurns(3);
        notificationInbox1.markAllAsRead();
        Assertions.assertEquals(3, notificationInbox1.getNotifications().get(0).getAnnouncementTurn());
    }
}
