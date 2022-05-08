package controller;

import model.Game;
import model.tile.Tile;
import model.unit.Armed;
import model.unit.Civilian;

public class GameController {

    private final Game game;
    public GameController(Game game){
        this.game = game;
    }

    public String selectUnit(String type, Integer position){
        Tile tile = game.getMap().getTileByNumber(position);
        if(tile == null)
            return "Invalid tile!";
        Tile.VisibilityState visibilityState = game.getCurrentPlayer().getSavedMap().getVisibilityState(tile);
        if(visibilityState == Tile.VisibilityState.FOG_OF_WAR || visibilityState == Tile.VisibilityState.DISCOVERED)
            return "you can't select any unit here!";
        if(type.equals("civilian")){
            Civilian civilian = tile.getCivilianUnit();
            if(civilian == null)
                return "there is no civilian here!";
            game.setSelectedObject(civilian);
        }else if(type.equals("armed")){
            Armed armed = tile.getArmedUnit();
            if(armed == null)
                return "there is no armed here!";
            game.setSelectedObject(armed);
        }else{
            return "invalid unit type, types are [armed, civilian]";
        }
        return "unit selected";
    }

    public String selectCity(String selectingType, String value){
        if(selectingType.equals("position")){
            Tile tile = game.getMap().getTileByNumber(Integer.parseInt(value));
            if(tile == null)
                return "Invalid position";
            if(tile.getOwnerCity() == null)
                return "";
        }
        return null;
    }
}
