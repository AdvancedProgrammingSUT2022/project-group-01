package model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Vector;

@ExtendWith(MockitoExtension.class)
class PlayerTest {

    @Mock
    private User mockUser;

    @Test
    void testGetUser() {
        Player player = new Player(mockUser);
        player.getUser();
        Assertions.assertEquals(player.getUser(),mockUser);
    }
}
