package controller;

import model.Player;

import java.util.HashMap;
import java.util.Vector;

public class GameMenuController {

    private Vector<Player> players;

    /**
     * @param players
     */
    public GameMenuController(Vector<Player> players) {
        // TODO - implement controller.GameMenuController.controller.GameMenuController
        throw new UnsupportedOperationException();
    }

    //SELECT:
    public String selectUnit(HashMap<String, String> args) {
        return null;
    }

    public String selectCity(HashMap<String, String> args) {
        return null;
    }

    //UNIT:
    public String unitMove(HashMap<String, String> args) {
        return null;
    }

    public String unitSleep(HashMap<String, String> args) {
        return null;
    }

    public String unitAlert(HashMap<String, String> args) {
        return null;
    }

    public String unitFortify(HashMap<String, String> args) {
        return null;
    }

    public String unitFortifyUntilHeal(HashMap<String, String> args) {
        return null;
    }

    public String unitGarrison(HashMap<String, String> args) {
        return null;
    }

    public String unitSetup(HashMap<String, String> args) {
        return null;
    }

    public String unitAttack(HashMap<String, String> args) {
        return null;
    }

    public String unitFoundCity(HashMap<String, String> args) {
        return null;
    }

    public String unitCancelMission(HashMap<String, String> args) {
        return null;
    }

    public String unitWake(HashMap<String, String> args) {
        return null;
    }

    public String unitDelete(HashMap<String, String> args) {
        return null;
    }

    public String unitBuild(HashMap<String, String> args) {
        return null;
    }

    public String unitRemove(HashMap<String, String> args) {
        return null;
    }

    public String unitRepair(HashMap<String, String> args) {
        return null;
    }

    //MAP:
    public String mapShow(HashMap<String, String> args) {
        return null;
    }

    public String mapMove(HashMap<String, String> args) {
        return null;
    }

    //GLOBAL:
    public String menuEnter(HashMap<String, String> args) {

    }

    public String menuExit(HashMap<String, String> args) {
        ProgramController.setCurrentMenu(Menus.MAIN_MENU);
        return "Done!";
    }

    public String currentMenu(HashMap<String, String> args) {
        return "Game Menu";
    }
}