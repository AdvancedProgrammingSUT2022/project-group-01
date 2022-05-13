import model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UserTest {

    @Test
    public void constructorCheck(){
        User user = new User("username", "password","nickname");
        Assertions.assertEquals(user.getUsername(),"username");
        Assertions.assertEquals(user.getPassword(),"password");
        Assertions.assertEquals(user.getNickname(),"nickname");
        Assertions.assertEquals(user.getScore(),"0");
    }

}
