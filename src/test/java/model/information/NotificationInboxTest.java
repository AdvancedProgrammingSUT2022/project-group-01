package model.information;

import model.Notification;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Vector;

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
        assertTrue(result);
    }

    @Test
    void testMarkAllAsRead() {
        // Setup
        // Run the test
        notificationInboxUnderTest.markAllAsRead();

        // Verify the results
    }

    @Test
    void testSetUnsetTurns() {
        // Setup
        // Run the test
        notificationInboxUnderTest.setUnsetTurns(0);

        // Verify the results
    }
}
