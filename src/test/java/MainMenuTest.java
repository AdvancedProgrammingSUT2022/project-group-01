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
import utils.CommandProcessor;
import utils.Commands;

import javax.xml.crypto.Data;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class MainMenuTest {


    controller.MainMenuController mainMenuController;

    @Mock
    Database database;

    @BeforeEach
    public void init(){
        database = new Database();
        mainMenuController = new controller.MainMenuController(database);
        ProgramController.setGame(null);
    }

    @Mock
    User user;

    @Mock
    HashMap<String, String> args;

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
    public void invalidNavigation(){
        HashMap args = Mockito.mock(HashMap.class);
        when(args.get("section")).thenReturn("randomMenu");
        String answer = "invalid navigation!";
        Assertions.assertEquals(answer, mainMenuController.menuEnter(args));
    }

    @Test
    public void menuExitMain(){
        String answer = "Done!";
        Assertions.assertEquals(answer, mainMenuController.menuExit(args));
    }

    @Test
    public void currentMenuMain(){
        String answer = "Main Menu";
        String result = mainMenuController.currentMenu(null);
        Assertions.assertEquals(answer, result);
    }

    @Test
    public void validPlayGame(){
        Database mockedDatabase =mock(Database.class);
        when(mockedDatabase.findUserByUsername("player1")).thenReturn(new User("player1","password1", "nickname1"));
        when(mockedDatabase.findUserByUsername("player2")).thenReturn(new User("player2","password2", "nickname2"));
        when(mockedDatabase.findUserByUsername("player3")).thenReturn(new User("player3","password3", "nickname3"));
        MainMenuController mainMenuController = new MainMenuController(mockedDatabase);
        String input = "play game -p1 player1 -p2 player2 -player3 player3";
        mainMenuController.playGame(CommandProcessor.extractCommand(input, Commands.PLAY_GAME));
        Assertions.assertNotNull(ProgramController.getGame());
        Assertions.assertEquals(1,1);
    }

    @Test
    public void invalidPlayGame(){
        Database mockedDatabase =mock(Database.class);
        when(mockedDatabase.findUserByUsername("player2")).thenReturn(new User("player2","password2", "nickname2"));
        when(mockedDatabase.findUserByUsername("player3")).thenReturn(new User("player3","password3", "nickname3"));
        when(mockedDatabase.findUserByUsername("player1")).thenReturn(null);
        controller.MainMenuController mainMenuController = new controller.MainMenuController(mockedDatabase);
        String input = "play game -p1 player1 -p2 player2 -player3 player3";
        mainMenuController.playGame(CommandProcessor.extractCommand(input, Commands.PLAY_GAME));
        Assertions.assertNull(ProgramController.getGame());
    }

    @Test
    public void logoutTest(){
        User user = mock(User.class);
        ProgramController.setLoggedInUser(user);
        Assertions.assertNotNull(ProgramController.getLoggedInUser());
        mainMenuController.logOut(null);
        Assertions.assertNull(ProgramController.getLoggedInUser());
    }

}