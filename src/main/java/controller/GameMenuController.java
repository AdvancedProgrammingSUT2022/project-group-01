package controller;

import model.Game;
import model.Player;
import model.civilization.Civilization;
import model.civilization.Currency;
import model.tile.Tile;
import model.unit.*;
import model.unit.armed.Armed;
import model.unit.civilian.Civilian;

import java.util.HashMap;

public class GameMenuController {

    private Game game;
    private MapController mapController;
    private GameController gameController;
    /**
     * @param
     */
    public GameMenuController(Game game, GameController gameController) {
        this.game = game;
        mapController = new MapController(game);
        this.gameController = gameController;
    }

    //SELECT:
    public String selectUnit(HashMap<String, String> args) {//todo: safar implement here
        String selectingType = args.get("section");//armed or civilian or garbage
        Tile destTile = game.getMap().getTileByNumber(Integer.parseInt(args.get("position")));
        //man inja ye chizayi neveshtam vali bebar too controllere khodet inja khalvat she
        if(destTile == null)
            return "invalid position";
        if(selectingType.equals("armed")){
            Armed armed = destTile.getArmedUnit();
            if(armed == null)
                return "there is no armed unit here";
            game.setSelectedObject(armed);
        }else if(selectingType.equals("civilian")){
            Civilian civilian = destTile.getCivilianUnit();
            if(civilian == null)
                return "there is no civilian unit here";
            game.setSelectedObject(civilian);
        }else{
            return "invalid unit type, types are [armed, civilian]";
        }
        return "unit selected";
    }

    public String selectCity(HashMap<String, String> args) {
        if(args.containsKey("position"))
            return gameController.selectCity("position", args.get("position"));
        else if(args.containsKey("cityname"))
            return gameController.selectCity("cityname", args.get("cityname"));
        else
            return "invalid command!";
    }

    //UNIT:
    public String unitMove(HashMap<String, String> args) {
        //TODO handle for armed unit in next checkpoint
        int position = Integer.parseInt(args.get("position"));
        Tile tile = game.getMap().getTileByNumber(position);
        if(tile == null)
            return "invalid position";
        //todo safar: move game.selectedObject -> tile
        return "Done!";
    }

    public String unitSleep(HashMap<String, String> args) {
        //todo safar : sleep game.selectedObject
        return null;//todo return ro dorost kon
    }

    public String unitAlert(HashMap<String, String> args) {
        //todo safar : alert game.selectedObject
        return null;//todo return ro dorost kon
    }

    public String unitFortify(HashMap<String, String> args) {
        //todo safar : fortify game.selectedObject
        return null;//todo return ro dorost kon
    }

    public String unitFortifyUntilHeal(HashMap<String, String> args) {
        //todo safar : fortify until heal game.selectedObject
        return null;//todo return ro dorost kon
    }

    public String unitGarrison(HashMap<String, String> args) {
        //todo safar : garisson game.selectedObject
        return null;//todo return ro dorost kon
    }

    public String unitSetup(HashMap<String, String> args) {
        //todo safar : setup game.selectedObject
        return null;//todo return ro dorost kon
    }

    public String unitAttack(HashMap<String, String> args) {
        int position = Integer.parseInt(args.get("position"));
        Tile tile = game.getMap().getTileByNumber(position);
        if(tile == null)
            return "invalid position!";
        //todo safar : call your function here
        return null;// return ro ham dorost kon =)
    }

    public String unitFoundCity(HashMap<String, String> args) {
        //todo safar : call your function here
        return null;// return ro ham dorost kon =)
    }

    public String unitCancelMission(HashMap<String, String> args) {
        //todo safar : call your function here
        return null;// return ro ham dorost kon =)
    }

    public String unitWake(HashMap<String, String> args) {
        //todo safar : call your function here
        return null;// return ro ham dorost kon =)
    }

    public String unitDelete(HashMap<String, String> args) {
        //todo safar : call your function here
        return null;// return ro ham dorost kon =)
    }

    public String unitBuild(HashMap<String, String> args) {
        String buildingItem = args.get("section");//road || rail road ...
        //todo safar : call your function here
        return null;// return ro ham dorost kon =)
    }

    public String unitRemove(HashMap<String, String> args) {
        String removingItem = args.get("section");//jungle || route
        //todo safar : call your function here
        return null;// return ro ham dorost kon =)
    }

    public String unitRepair(HashMap<String, String> args) {
        //todo safar : call your function here
        return null;// return ro ham dorost kon =)
    }

    //MAP:
    public String mapShow(HashMap<String, String> args) {
        if(args.containsKey("position")){
            return gameController.mapShow("position", args.get("position"));
        }else if(args.containsKey("cityname")){
            return gameController.mapShow("cityname", args.get("cityname"));
        }
        return "invalid command!";
    }

    public String mapMove(HashMap<String, String> args) {
        String section = args.get("section");
        int count = Integer.parseInt(args.get("count"));
        return mapController.moveMap(section, count);
    }

	public String menuEnter(HashMap<String, String> args) {
		String menuName = args.get("section");
		if(menuName.equals("info")){
			ProgramController.setCurrentMenu(Menus.INFO_MENU);
		}
		return "invalid navigation!";
	}
    public String menuExit(HashMap<String, String> args) {
        ProgramController.setCurrentMenu(Menus.MAIN_MENU);
        return "Done!";
    }

    public String currentMenu(HashMap<String, String> args) {
        return "Game Menu";
    }

    public String increaseResource(HashMap<String, String> args) {
        /*String section = args.get("section");
        int amount = Integer.parseInt(args.get("amount"));
        Currency currency = new Currency(0,0,0);
        if(section.equals("gold")){
            currency.increase(amount,0,0);
        }else if(section.equals("product")){
            currency.increase(0,amount,0);
        }else if(section.equals("food")){
            currency.increase(0,0,amount);
        }else{
            return "invalid resource";
        }
        game.getSelectedCity().getCurrency().add(currency);
        return "Done!";*/
        return null;
    }

    public String spawnUnit(HashMap<String, String> args) {
        //todo next Checkpoint
        HashMap<String, UnitType> units = new HashMap<>(){{
            put("worker", UnitType.WORKER);
            put("scout", UnitType.SCOUT);
            put("settler", UnitType.SETTLER);
        }};
        String type = args.get("section");
        int position = Integer.parseInt(args.get("position"));
        if(!units.containsKey(type))
            return "invalid unit type";
        Tile tile = game.getMap().getTileByNumber(position);
        Civilian civilian = new Civilian(units.get(type), tile, game.getCurrentPlayer().getCivilization(), game);
        game.getCurrentPlayer().getCivilization().addUnit(civilian);
        tile.setCivilianUnit(civilian);
        return "you're cheater!";
    }
}