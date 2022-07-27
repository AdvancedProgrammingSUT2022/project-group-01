package controller;

import controller.UnitTestGui;
import model.Game;

public class UnitViewThread extends Thread {
    Game game;
    public UnitTestGui unitTestGui;
    public GUIController guiController;
    public UnitViewThread() {
        //this.unitTestGui = new UnitTestGui();
        guiController = new GUIController();
    }

    @Override
    public void run() {
        //unitTestGui.run(game);
        guiController.run();
    }
}
