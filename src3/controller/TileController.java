package controller;

import model.Player;
import model.improvement.Improvement;
import model.improvement.ImprovementType;
import model.tile.Tile;

public class TileController extends Controller{

    public String orderBuildingImprovement(Player player, Tile tile, ImprovementType improvementType){
        if(!tile.getCivilization().equals(player.getCivilization()))
            return "you should own a tile to build improvement on it";
        if(!improvementType.isEligibleToBuild(player.getCivilization(), tile))
            return "you can't build this improvement here";
        //TODO add to production list
        return "improvement ordered";
    }


    public String buildImprovement(Tile tile, Improvement newImprovement){
        newImprovement.getType().improvementSpecialAction(tile);
        tile.buildImprovement(newImprovement);
        return "improvement " + newImprovement.getType().name() + "was built successfully";
    }

    public static int tileDistanceFinder(Tile firstTile, Tile secondTile){
        int pDistance = Math.abs(firstTile.getPCoordinate() - secondTile.getPCoordinate());
        int qDistance = Math.abs(firstTile.getqCoordinate() - secondTile.getqCoordinate());
        int rDistance = Math.abs(firstTile.getPCoordinate() - secondTile.getPCoordinate()
                + firstTile.getqCoordinate() - secondTile.getqCoordinate());
        return (pDistance + qDistance + rDistance)/2;
    }
}
