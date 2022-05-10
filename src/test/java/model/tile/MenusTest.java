package model.tile;

import controller.Controller;
import controller.LoginMenuController;
import controller.MainMenuController;
import controller.ProgramController;
import model.Database;
import model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.when;

public class MenusTest {


    LoginMenuController loginMenuController;
    MainMenuController mainMenuController;

    Database database;

    @BeforeEach
    public void init(){
        database = new Database();
        loginMenuController = new LoginMenuController(database);
        mainMenuController = new MainMenuController(database);
    }

    @Mock
    User user;

    @Mock
    HashMap<String, String> args;

    @Test
    public void testUserCreate() {
        HashMap args = Mockito.mock(HashMap.class);
        when(args.get("username")).thenReturn("testUsername");
        when(args.get("password")).thenReturn("testPassword");
        when(args.get("nickname")).thenReturn("testNickname");
        loginMenuController.register(args);
        Assertions.assertNotNull(database.findUserByNickname("testNickname"));
    }

    @Test
    public void testUserLogin(){
        HashMap args = Mockito.mock(HashMap.class);
        database.load();
        when(args.get("username")).thenReturn("testUsername");
        when(args.get("password")).thenReturn("testPassword");
        String result = loginMenuController.login(args);
        Assertions.assertEquals("user logged in successfully!",result);
    }

    @Test
    public void testMenuEnterBeforeLogin(){
        HashMap args = Mockito.mock(HashMap.class);
        when(args.get("section")).thenReturn("main");
        String result = loginMenuController.menuEnter(args);
        Assertions.assertEquals(result, "login first!");
    }

    @Test
    public void testValidMenuEnterAfterLogin(){
        HashMap args = Mockito.mock(HashMap.class);
        when(args.get("section")).thenReturn("main");
        try (MockedStatic<ProgramController> theMock = Mockito.mockStatic(ProgramController.class)) {
            theMock.when(() -> ProgramController.getLoggedInUser())
                    .thenReturn(new User("tst", "tst", "tst"));
            String result = loginMenuController.menuEnter(args);
            Assertions.assertEquals(result, "Done!");
        }
    }

    @Test
    public void testInvalidMenuEnterAfterLogin(){
        HashMap args = Mockito.mock(HashMap.class);
        when(args.get("section")).thenReturn("random");
        try (MockedStatic<ProgramController> theMock = Mockito.mockStatic(ProgramController.class)) {
            theMock.when(ProgramController::getLoggedInUser)
                    .thenReturn(new User("tst", "tst", "tst"));
            String result = loginMenuController.menuEnter(args);
            Assertions.assertEquals(result, "invalid navigation!");
        }
    }

    @Test
    public void currentLoginMenu(){
        String answer = "Login Menu";
        Assertions.assertEquals(answer, loginMenuController.currentMenu(null));
    }

    @Test
    public void loginMenuExit(){
        String answer = "goodbye :)";
        Assertions.assertEquals(answer, loginMenuController.menuExit(null));
    }

    @Test
    public void loginMenuConstructor(){
        Assertions.assertNotNull(loginMenuController.getDatabase());
    }

    @Test
    public void mainMenuConstructor(){
        Assertions.assertNotNull(mainMenuController.getDatabase());
    }

    @Test
    public void invalidMenuEnterFromMain(){
        HashMap args = Mockito.mock(HashMap.class);
        when(args.get("section")).thenReturn("random");
        String answer = "invalid navigation!";
        Assertions.assertEquals(answer, mainMenuController.menuEnter(args));
    }

    @Test
    public void menuEnterProfile(){
        HashMap args = Mockito.mock(HashMap.class);
        when(args.get("section")).thenReturn("profile");
        String answer = "Done!";
    }

    @Test
    public void invalidGameMenuEnterGame(){
        HashMap args = Mockito.mock(HashMap.class);
        when(args.get("section")).thenReturn("game");
        String answer = "user play game [players] instead";
        Assertions.assertEquals(answer, mainMenuController.menuEnter(args));
    }

    @Test
    public void loginMenuEnterFromMain(){
        HashMap args = Mockito.mock(HashMap.class);
        when(args.get("section")).thenReturn("login");
        String answer = "Done!";
        Assertions.assertEquals(answer, mainMenuController.menuEnter(args));
    }

    @Test
    public void menuExitMain(){
        String answer = "Done!";
        Assertions.assertEquals(answer, mainMenuController.menuExit(null));
    }

    @Test
    public void currentMenuMain(){
        String answer = "Main Menu";
        Assertions.assertEquals(answer, mainMenuController.
    }
}
