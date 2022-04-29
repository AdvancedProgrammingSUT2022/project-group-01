package controller;

import model.Game;
import model.Player;
import model.civilization.Currency;
import model.tile.Tile;
import model.unit.Civilian;
import model.unit.Unit;
import model.unit.UnitType;
import model.unit.Worker;

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
        //TODO handle here for armed unit in next checkpoint
        game.setSelectedUnit(game.getMap().getTileByNumber(Integer.parseInt(args.get("position"))).getCivilianUnit());
        return "unit selected";
    }

    public String selectCity(HashMap<String, String> args) {
        //todo handle here for next checkpoint
        return null;
    }

    //UNIT:
    public String unitMove(HashMap<String, String> args) {
        //TODO handle for armed unit in next checkpoint
        int position = Integer.parseInt(args.get("position"));
        Tile tile = game.getMap().getTileByNumber(position);
        game.getSelectedUnit().setDestTile(tile);
        return "Done!";
    }

    public String unitSleep(HashMap<String, String> args) {
        //TODO implement here in next checkpoint
        return "boro checkpointe badi bia!";
    }

    public String unitAlert(HashMap<String, String> args) {
        //TODO implement here in next checkpoint
        return "boro checkpointe badi bia!";
    }

    public String unitFortify(HashMap<String, String> args) {
        //TODO implement here in next checkpoint
        return "boro checkpointe badi bia!";
    }

    public String unitFortifyUntilHeal(HashMap<String, String> args) {
        game.getSelectedUnit().fortifyUntilHeal();
        return "Done!";
    }

    public String unitGarrison(HashMap<String, String> args) {
        //TODO implement here in next checkpoint
        return "boro checkpointe badi bia!";
    }

    public String unitSetup(HashMap<String, String> args) {
        //TODO implement here in next checkpoint
        return "boro checkpointe badi bia!";
    }

    public String unitAttack(HashMap<String, String> args) {
        //TODO implement here in next checkpoint
        return "boro checkpointe badi bia!";
    }

    public String unitFoundCity(HashMap<String, String> args) {
        //TODO implement here in next checkpoint
        return "boro checkpointe badi bia!";
    }

    public String unitCancelMission(HashMap<String, String> args) {
        //TODO implement here in next checkpoint
        return "boro checkpointe badi bia!";
    }

    public String unitWake(HashMap<String, String> args) {
        //TODO implement here in next checkpoint
        return "boro checkpointe badi bia!";
    }

    public String unitDelete(HashMap<String, String> args) {
        //TODO implement here in next checkpoint
        return "boro checkpointe badi bia!";
    }

    public String unitBuild(HashMap<String, String> args) {
        //TODO implement here in next checkpoint
        return "boro checkpointe badi bia!";
    }

    public String unitRemove(HashMap<String, String> args) {
        //TODO implement here in next checkpoint
        return "boro checkpointe badi bia!";
    }

    public String unitRepair(HashMap<String, String> args) {
        //TODO implement here in next checkpoint
        return "boro checkpointe badi bia!";
    }

    //MAP:
    public String mapShow(HashMap<String, String> args) {
        if(args.containsKey("position")){
            int position = Integer.parseInt(args.get("position"));
            return mapController.setPosition(position);
        }else if(args.containsKey("cityname")){
            //TODO boro positionesho peyda kon va mesle bala
            //TODO in phase1
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
        String section = args.get("section");
        int amount = Integer.parseInt(args.get("amount"));
        Currency currency = new Currency(0,0,0);
        if(section.equals("gold")){
            currency.setGold(amount);
        }else if(section.equals("product")){
            currency.setProduct(amount);
        }else if(section.equals("food")){
            currency.setFood(amount);
        }else{
            return "invalid resource";
        }
        game.getCurrentPlayer().getCivilization().increaseCurrency(currency);
        return "Done!";
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