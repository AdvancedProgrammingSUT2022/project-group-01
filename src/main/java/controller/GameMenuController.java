package controller;

import model.Game;
import model.Player;

import java.util.HashMap;
import java.util.Vector;

public class GameMenuController {

    private Game game;
    private MapController mapController;
    /**
     * @param
     */
    public GameMenuController(Game game) {
        this.game = game;
        mapController = new MapController(game);
    }

    //SELECT:
    public String selectUnit(HashMap<String, String> args) {
        return null;
    }

    public String selectCity(HashMap<String, String> args) {
        //name selecting

        //position selecting
        //TODO implement here
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
        if(args.containsKey("position")){
            int position = Integer.parseInt(args.get("position"));
            return mapController.setPosition(position);
        }else if(args.containsKey("cityname")){
            //TODO boro positionesho peyda kon va mesle bala
        }
        return "invalid command!";
    }

    public String mapMove(HashMap<String, String> args) {
        String section = args.get("section");
        int count = Integer.parseInt(args.get("count"));
        return mapController.moveMap(section, count);
    }

    //GLOBAL:
    public String menuEnter(HashMap<String, String> args) {
        return "invalid navigation";
    }

    public String menuExit(HashMap<String, String> args) {
        ProgramController.setCurrentMenu(Menus.MAIN_MENU);
        return "Done!";
    }

    public String currentMenu(HashMap<String, String> args) {
        return "Game Menu";
    }

    public String increaseResource(HashMap<String, String> args) {

    }

    public String spawnUnit(HashMap<String, String> args) {

    }
}