import model.Database;
import model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DatabaseTest {


    Database database;

    @BeforeEach
    public void init(){
        database = new Database();
    }

    @Test
    public void constructorTest(){
        Assertions.assertNotNull(database.getUsers());
    }

    @Test
    public void addUserTest(){
        Assertions.assertEquals(database.getUsers().size(),0);
        database.addUser("username", "nickname","password");
        Assertions.assertEquals(database.getUsers().size(),1);
    }

    @Test
    public void validFindUserByUsername(){
        database.addUser("username","nickname", "password");
        Assertions.assertNotNull(database.findUserByUsername("username"));
    }

    @Test
    public void invalidFindUserByUsername(){
        database.addUser("username1","nickname1", "password1");
        Assertions.assertNull(database.findUserByUsername("username"));
    }

    @Test
    public void validFindUserByNickname(){
        database.addUser("username","nickname", "password");
        Assertions.assertNotNull(database.findUserByNickname("nickname"));
    }

    @Test
    public void invalidFindUserByNickname(){
        database.addUser("username1","nickname1", "password1");
        Assertions.assertNull(database.findUserByNickname("nickname"));
    }
}
