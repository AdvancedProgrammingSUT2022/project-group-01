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

}
