import controller.*;
import model.User;
import view.components.ImagesAddress;

import java.util.ArrayList;


public class Main {
    public static void main(String[] args)   {
        ProgramController pc = new ProgramController();
        ProgramController.setLoggedInUser(new User("Amnam","Amnam","Amnam", ImagesAddress.GAME_BACKGROUND.getAddress()));
        GUIController guiController = new GUIController();
        guiController.run();
    }
}
