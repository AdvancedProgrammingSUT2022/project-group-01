package view;

import controller.*;
import model.User;


public class Main {
    public static void main(String[] args)   {
        ProgramController pc = new ProgramController();
        ProgramController.setLoggedInUser(new User("amnam","amnam","amnam","amnam"));
        GUIController guiController = new GUIController();
        guiController.run();
    }
}
