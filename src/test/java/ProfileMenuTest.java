import controller.ProgramController;
import model.Database;
import model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.util.HashMap;

import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.when;

public class ProfileMenuTest {

    @Mock
    Database database;
    @Mock
    User user;
    @Mock
    HashMap<String, String> input;
    @Test
    //change password : success
    public void test1(){
        //implement static getLoggedInUser;
        User user = new User("salam", "currentPassword", "someNickname");
        try (MockedStatic<ProgramController> theMock = Mockito.mockStatic(ProgramController.class)) {
            theMock.when(() -> ProgramController.getLoggedInUser())
                    .thenReturn(user);

            Assertions.assertSame(user, ProgramController.getLoggedInUser());
        }
        /*User loggedInUser = ProgramController.getLoggedInUser();
        Assertions.assertSame(loggedInUser, user);
        when(input.get("new")).thenReturn("newPass");
        when(input.get("current")).thenReturn("currentPassword");
        when(input.get("section")).thenReturn("--password");*/

    }
}
