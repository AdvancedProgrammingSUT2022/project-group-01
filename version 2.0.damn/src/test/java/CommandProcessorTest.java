import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import utils.CommandProcessor;
import utils.Commands;

import java.util.HashMap;

public class CommandProcessorTest {


    @Test
    //test without key value and with single arg
    public void testOne(){
        HashMap<String, String> answer = new HashMap<String, String>() {{
            put("section", "city");
        }};
        String input = "INFO CITY";
        HashMap<String, String> result = CommandProcessor.extractCommand(input, Commands.INFO);
        System.out.println(result);
        Assertions.assertEquals(answer, result);
    }

    @Test
    //test with full-form key value and without single arg
    public void testTwo(){
        HashMap<String, String> answer = new HashMap<String, String>() {{
            put("position", "2");
        }};
        String input = "select unit --position 2";
        HashMap<String, String> result = CommandProcessor.extractCommand(input, Commands.SELECT_UNIT);
        Assertions.assertEquals(answer,result);
    }

    @Test
    //test with abbreviated form key value and without single arg
    public void testThree(){
        HashMap<String, String> answer = new HashMap<String, String>() {{
            put("position", "2");
        }};
        String input = "select unit -p 2";
        HashMap<String, String> result = CommandProcessor.extractCommand(input, Commands.SELECT_UNIT);
        Assertions.assertEquals(answer,result);
    }

    @Test
    //test with abbreviated form key value and with single arg
    public void testFour(){
        HashMap<String, String> answer = new HashMap<String, String>() {{
            put("count", "2");
            put("section", "l");
        }};
        String input = "MaP move L -c 2";
        HashMap<String, String> result = CommandProcessor.extractCommand(input, Commands.MAP_MOVE);
        Assertions.assertEquals(answer,result);
    }
    @Test
    //test with single arg and without key value (command includes no single arg)
    public void testFive(){
        String input = "menu enter";
        HashMap<String, String> result = CommandProcessor.extractCommand(input, Commands.MENU_ENTER);
        Assertions.assertNull(result);
    }

    @Test
    //test with single arg and with key value (command includes no single arg)
    public void testSix(){
        String input = "map move --count 2";
        HashMap<String, String> result = CommandProcessor.extractCommand(input, Commands.MAP_MOVE);
        Assertions.assertNull(result);
    }

    @Test
    //test with empty value
    public void testSeven(){
        String input = "user create -u   -p salam -n nickname";
        HashMap<String, String> result = CommandProcessor.extractCommand(input, Commands.REGISTER);
        Assertions.assertNull(result);
    }

    @Test
    //test without any necessary arg or key value
    public void testEight(){
        String input = "play game -p1 salam -p2 hello";
        HashMap<String, String> answer = new HashMap<>(){{
            put("player1", "salam");
            put("player2", "hello");
        }};
        HashMap<String, String> result = CommandProcessor.extractCommand(input, Commands.PLAY_GAME);
        Assertions.assertEquals(answer, result);
    }

    @Test
    public void testNine(){
        String input = "play game --player1 salam -p2 hello";
        HashMap<String, String> answer = new HashMap<>(){{
            put("player1", "salam");
            put("player2", "hello");
        }};
        HashMap<String, String> result = CommandProcessor.extractCommand(input, Commands.PLAY_GAME);
        Assertions.assertEquals(answer, result);
    }

}
