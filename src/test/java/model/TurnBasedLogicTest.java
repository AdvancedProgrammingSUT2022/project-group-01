package model;

import model.civilization.Civilization;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TurnBasedLogicTest {
     public class A implements TurnBasedLogic{
         public void nextTurn(Civilization civilization){
             return;
         }
         public void remove(){
             removeFromList();
         }
         public void add(){
             addToList();
         }
     }

     @Test
    public void removeFromListTest(){
         A a = new A();
         a.add();
         a.remove();
     }
}