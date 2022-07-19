package controller;

import controller.UnitTestGui;
import model.Game;

public class UnitViewThread extends Thread {
    Game game;
    public UnitTestGui unitTestGui;

    public UnitViewThread(Game game) {
        this.game = game;
        this.unitTestGui = new UnitTestGui();
    }

    @Override
    public void run() {
        unitTestGui.run(game);
    }
}
