import controller.*;
import model.User;
import view.components.ImagesAddress;


public class Main {
    public static void main(String[] args)   {
        ProgramController pc = new ProgramController();
        ProgramController.setLoggedInUser(new User("amnam","amnam","amnam", ImagesAddress.GAME_BACKGROUND.getAddress()));
        GUIController guiController = new GUIController();
        guiController.run();
    }
}
