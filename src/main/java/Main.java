import controller.*;
import model.Game;
import model.User;
import model.unit.Unit;
import model.unit.UnitType;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import utils.StringUtils;
import view.chatroom.SelectMemberDialog;
import view.components.ImagesAddress;

import java.util.ArrayList;


public class Main {
    public static void main(String[] args)   {
        /*ProgramController pc = new ProgramController();
        ProgramController.setLoggedInUser(new User("Amnam","Amnam","Amnam", ImagesAddress.GAME_BACKGROUND.getAddress()));
        GUIController guiController = new GUIController();
        guiController.run();*/
        SelectMemberDialog smd = new SelectMemberDialog();
        smd.run();
        //ProgramController pc = new ProgramController();
        //pc.run();
        //this is a test

        /*graphicTest gr = new graphicTest();
        gr.run();*/
    }

}
