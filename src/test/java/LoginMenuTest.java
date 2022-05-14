import controller.LoginMenuController;
import controller.MainMenuController;
import controller.ProgramController;
import model.Database;
import model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.util.HashMap;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class LoginMenuTest {
    LoginMenuController loginMenuController;

    @Mock
    Database database;

    @BeforeEach
    public void init(){
        database = new Database();
        loginMenuController = new LoginMenuController(database);
    }

    @Test
    public void validUserCreate() {
        HashMap args = Mockito.mock(HashMap.class);
        when(args.get("username")).thenReturn("testUsername");
        when(args.get("password")).thenReturn("testPassword");
        when(args.get("nickname")).thenReturn("testNickname");
        String result = loginMenuController.register(args);
        Assertions.assertEquals(result,"user created successfully!" );
    }

    @Test
    public void invalidUserCreate(){
        HashMap<String, String> args = mock(HashMap.class);
        Database database = mock(Database.class);
        LoginMenuController loginMenuController = new LoginMenuController(database);
        when(args.get("username")).thenReturn("username");
        when(args.get("password")).thenReturn("password");
        when(args.get("nickname")).thenReturn("nickname");
        User existUser = mock(User.class);
        when(database.findUserByUsername("username")).thenReturn(existUser);
        String result = loginMenuController.register(args);
        Assertions.assertEquals("user with username username already exist!", result);
    }

    @Test
    public void testValidUserLogin(){
        HashMap args = Mockito.mock(HashMap.class);
        Database database = mock(Database.class);
        User user = new User("testUsername", "testPassword","testNickname");
        when(database.findUserByUsername("testUsername")).thenReturn(user);
        when(args.get("username")).thenReturn("testUsername");
        when(args.get("password")).thenReturn("testPassword");
        LoginMenuController sampleLoginMenuController = new LoginMenuController(database);
        String result = sampleLoginMenuController.login(args);
        Assertions.assertEquals("user logged in successfully!",result);
    }

    @Test
    public void testInvalidUserLogin(){
        HashMap args = Mockito.mock(HashMap.class);
        Database database = mock(Database.class);
        User user = new User("testUsername", "testPassword2","testNickname");
        when(database.findUserByUsername("testUsername")).thenReturn(user);
        when(args.get("username")).thenReturn("testUsername");
        when(args.get("password")).thenReturn("testPassword");
        LoginMenuController sampleLoginMenuController = new LoginMenuController(database);
        String result = sampleLoginMenuController.login(args);
        Assertions.assertEquals("Username and password didn’t match!",result);
    }

    @Test
    public void MenuEnterBeforeLogin(){
        HashMap args = Mockito.mock(HashMap.class);
        ProgramController.setLoggedInUser(null);
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



}
