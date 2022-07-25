package view;

import controller.*;
import model.User;
import network.AccessLevel;


public class Main {
    public static void main(String[] args)   {
        ProgramController pc = new ProgramController();
        ProgramController.setLoggedInUser(new User("Amnam","Amnam","Amnam","amnam", AccessLevel.USER));
        GUIController guiController = new GUIController();
        guiController.run();
    }
}
