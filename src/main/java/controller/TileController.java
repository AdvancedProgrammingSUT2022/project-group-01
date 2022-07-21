package controller;

import model.building.BuildingType;
import model.improvement.ImprovementType;
import model.resource.ResourceType;
import model.technology.TechnologyType;
import model.tile.Terrain;
import model.tile.TerrainFeature;
import model.unit.Unit;

public class TileController extends Controller {

    public static void initializeEnums() {
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
        for (BuildingType buildingType : BuildingType.values()){
            buildingType.initializeEnum();
        }
    }


    public static boolean isAbleToBuildRoad(Unit unit){
        return unit.getOwnerCivilization().getResearchTree().isResearched(TechnologyType.THE_WHEEL);
    }

    public static boolean isAbleToBuildRailRoad(Unit unit){
        return unit.getOwnerCivilization().getResearchTree().isResearched(TechnologyType.RAILROAD);
    }
}
