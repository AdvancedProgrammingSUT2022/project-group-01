package utils;


import org.junit.jupiter.api.Assertions;
import org.junit.Test;
import org.mockito.Mock;

public class test {
    @Test
    public static int sum(int a,int b){
        return a+b;
    }

    @Test
    public static void mainPrime(){
        Assertions.assertEquals(4,sum(1,2));
    }

}
