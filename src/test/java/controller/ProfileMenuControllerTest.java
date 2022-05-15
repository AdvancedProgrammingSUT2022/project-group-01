package controller;

import model.Database;
import model.User;
import net.bytebuddy.implementation.auxiliary.MethodCallProxy;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProfileMenuControllerTest {

    @Mock
    private Database mockedDatabase;

    private ProfileMenuController profileMenuControllerUnderTest;

    @BeforeEach
    void setUp() {

        profileMenuControllerUnderTest = new ProfileMenuController(mockedDatabase);
        ProgramController.setLoggedInUser(new User("user","pass","nickname"));
        ProgramController.setDatabase(mockedDatabase);
    }

    @Test
    void passwordChangeTestOne(){
        HashMap args = mock(HashMap.class);
        when(args.get("section")).thenReturn("password");
        when(args.get("new")).thenReturn("newPass");
        when(args.get("current")).thenReturn("password");
        String result = profileMenuControllerUnderTest.profileChange(args);
        String answer = "current password is invalid!";
        Assertions.assertEquals(answer,result);

    }

    @Test
    void passwordChangeTestTwo(){
        HashMap args = mock(HashMap.class);
        when(args.get("section")).thenReturn("password");
        when(args.get("new")).thenReturn("pass");
        when(args.get("current")).thenReturn("pass");
        String result = profileMenuControllerUnderTest.profileChange(args);
        String answer = "please enter a new password!";
        Assertions.assertEquals(answer,result);
    }

    @Test
    void passwordChangeTestThree(){
        HashMap args = mock(HashMap.class);
        when(args.get("section")).thenReturn("password");
        when(args.get("new")).thenReturn("password");
        when(args.get("current")).thenReturn("pass");
        String result = profileMenuControllerUnderTest.profileChange(args);
        String answer = "password changed successfully!";
        Assertions.assertEquals(answer,result);
    }

    @Test
    void nicknameChangeTestOne(){
        HashMap args = mock(HashMap.class);
        when(args.get("section")).thenReturn("nickname");
        when(args.get("new")).thenReturn("newNick");
        when(mockedDatabase.findUserByNickname("newNick")).thenReturn(new User("username","password","nickname"));
        String result = profileMenuControllerUnderTest.profileChange(args);
        String answer = "user with nickname newNick already exist!";
        Assertions.assertEquals(answer,result);
    }

    @Test
    void nicknameChangeTestTwo(){
        HashMap args = mock(HashMap.class);
        when(args.get("section")).thenReturn("nickname");
        when(args.get("new")).thenReturn("newNick");
        when(mockedDatabase.findUserByNickname("newNick")).thenReturn(null);
        String result = profileMenuControllerUnderTest.profileChange(args);
        String answer = "nickname changed successfully!";
        Assertions.assertEquals(answer,result);
    }

    @Test
    void usernameChangeTest(){
        HashMap args = mock(HashMap.class);
        when(args.get("section")).thenReturn("username");
        String result = profileMenuControllerUnderTest.profileChange(args);
        String answer = "you can't change your username!";
        Assertions.assertEquals(answer,result);
    }

    @Test
    void menuEnterTest(){
        String result = profileMenuControllerUnderTest.menuEnter(null);
        String answer = "menu navigation is not possible!";
        Assertions.assertEquals(answer,result);
    }

    @Test
    void currentMenuTest(){
        String answer = "profile menu";
        String result = profileMenuControllerUnderTest.currentMenu(null);
        Assertions.assertEquals(answer,result);
    }

    @Test
    void menuExitTest(){
        String answer = "done!";
        String result = profileMenuControllerUnderTest.menuExit(null);
    }



}
