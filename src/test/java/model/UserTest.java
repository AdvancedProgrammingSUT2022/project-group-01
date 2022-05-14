package model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UserTest {

    private User userUnderTest;

    @BeforeEach
    void setUp() {
        userUnderTest = new User("username", "password", "nickname");
    }

    @Test
    void testIncreaseScore() {
        userUnderTest.increaseScore(10);
        Assertions.assertEquals(userUnderTest.getScore(),10);
    }

    @Test
    void setUsernamePasswordTest(){
        userUnderTest.setUsername("ABCD");
        userUnderTest.setPassword("!@#$%");
        userUnderTest.setNickname("NICKNICK");
        Assertions.assertEquals(userUnderTest.getUsername(),"ABCD");
    }
}
