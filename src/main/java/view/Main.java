package view;

import controller.*;
import model.User;
import model.technology.TechnologyType;


public class Main {
    public static void main(String[] args)   {
        ProgramController pc = new ProgramController();
        ProgramController.setLoggedInUser(new User("Amnam","Amnam","Amnam","amnam"));
//        GUIController guiController = new GUIController();
//        guiController.run();
        UnitViewThread unitViewThread = new UnitViewThread();
        unitViewThread.start();
        pc.run();
    }
}
