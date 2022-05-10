package controller;

import model.Player;
import model.TurnBasedLogic;
import model.civilization.Civilization;
import model.improvement.ImprovementType;
import model.resource.ResourceType;
import model.technology.TechnologyType;
import model.tile.Terrain;
import model.tile.TerrainFeature;
import model.tile.Tile;
import model.unit.Unit;
import model.unit.civilian.Worker;

public class TileController extends Controller {

    public static int tileDistanceFinder(Tile firstTile, Tile secondTile) {
        int pDistance = Math.abs(firstTile.getPCoordinate() - secondTile.getPCoordinate());
        int qDistance = Math.abs(firstTile.getQCoordinate() - secondTile.getQCoordinate());
        int rDistance = Math.abs(firstTile.getPCoordinate() - secondTile.getPCoordinate() + firstTile.getQCoordinate() - secondTile.getQCoordinate());
        return (pDistance + qDistance + rDistance) / 2;
    }

    public static void initializeEnums() {
        Terrain terrain;
        TerrainFeature terrainFeature;
        ResourceType resourceType;
        ImprovementType improvement;
        for (Terrain terrain1 : Terrain.values()) {
            terrain = terrain1;
        }
        for (TerrainFeature feature1 : TerrainFeature.values()) {
            terrainFeature = feature1;
        }
        for (ResourceType resource : ResourceType.values()) {
            resourceType = resource;
        }
        for (ImprovementType improvementType : ImprovementType.values()) {
            improvement = improvementType;
        }
        for (Terrain terrain1 : Terrain.values()) {
            terrain1.initializeVectors();
        }
        for (TerrainFeature feature : TerrainFeature.values()) {
            feature.initializeVectors();
        }
        for (ResourceType resourceType1 : ResourceType.values()) {
            resourceType1.initializeVectors();
        }
        for (ImprovementType improvementType : ImprovementType.values()) {
            improvementType.initializeVectors();
        }
    }

    public String orderBuildingImprovement(Worker worker, Tile tile, ImprovementType improvementType) {
        if (!tile.getCivilization().equals(worker.getOwnerCivilization()))
            return "you should own the tile to build improvement on it";
        if (!improvementType.isEligibleToBuild(worker.getOwnerCivilization(), tile))
            return "you can't build this improvement here";
        //TODO add to production list
        worker.improveTile(improvementType);
        return "improvement ordered";
    }

//    public String buildImprovement(Tile tile, ImprovementType newImprovement) {
//        newImprovement.improvementSpecialAction(tile);
//        tile.buildImprovement(newImprovement);
//        return "improvement " + newImprovement.name() + "was built successfully";
//    }

    public static boolean isAbleToBuildRoad(Unit unit){
        return unit.getOwnerCivilization().getResearchTree().isResearched(TechnologyType.THE_WHEEL);
    }

    public static boolean isAbleToBuildRailRoad(Unit unit){
        return unit.getOwnerCivilization().getResearchTree().isResearched(TechnologyType.RAILROAD);
    }
}
