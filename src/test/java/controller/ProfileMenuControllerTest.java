package controller;

import model.Database;
import model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProfileMenuControllerTest {

    @Mock
    private Database mockDatabase;

    private ProfileMenuController profileMenuControllerUnderTest;

    @BeforeEach
    void setUp() {
        profileMenuControllerUnderTest = new ProfileMenuController(mockDatabase);
    }


    @Test
    void testMenuEnter() {
        assertEquals("menu navigation is not possible", profileMenuControllerUnderTest.menuEnter(new HashMap<>(
                Map.ofEntries(Map.entry("value", "value")))));
    }

    @Test
    void testCurrentMenu() {
        assertEquals("Profile Menu", profileMenuControllerUnderTest.currentMenu(new HashMap<>(
                Map.ofEntries(Map.entry("value", "value")))));
    }

    @Test
    void testMenuExit() {
        assertEquals("Done!", profileMenuControllerUnderTest.menuExit(new HashMap<>(
                Map.ofEntries(Map.entry("value", "value")))));
    }


}
