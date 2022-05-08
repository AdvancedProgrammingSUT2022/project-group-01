package controller;

import model.Player;
import model.TurnBasedLogic;
import model.improvement.ImprovementType;
import model.resource.ResourceType;
import model.tile.Terrain;
import model.tile.TerrainFeature;
import model.tile.Tile;

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

    public String orderBuildingImprovement(Player player, Tile tile, ImprovementType improvementType) {
        if (!tile.getCivilization().equals(player.getCivilization()))
            return "you should own a tile to build improvement on it";
        if (!improvementType.isEligibleToBuild(player.getCivilization(), tile))
            return "you can't build this improvement here";
        //TODO add to production list
        return "improvement ordered";
    }

    public String buildImprovement(Tile tile, ImprovementType newImprovement) {
        newImprovement.improvementSpecialAction(tile);
        tile.buildImprovement(newImprovement);
        return "improvement " + newImprovement.name() + "was built successfully";
    }
}
