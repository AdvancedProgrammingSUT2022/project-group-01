package model.improvement;

import model.civilization.Civilization;
import model.resource.ResourceType;
import model.technology.TechnologyType;
import model.tile.Terrain;
import model.tile.TerrainFeature;
import model.tile.Tile;
import utils.StringUtils;

import java.util.List;
import java.util.Vector;

public enum ImprovementType {
    CAMP(0, 0, 0) {
        @Override
        public void initializeVectors() {
            this.canBuiltOnTerrains = new Vector<>(List.of(Terrain.TUNDRA, Terrain.PLAINS, Terrain.HILLS));
            this.canBuiltOnTerrainFeatures = new Vector<>(List.of(TerrainFeature.FOREST));
            this.affectingResources = new Vector<>(List.of(ResourceType.IVORY, ResourceType.FURS, ResourceType.DEER));
            this.preRequisiteTech = TechnologyType.TRAPPING;
        }
        @Override
        public int getProductionTime(Tile tile) {
            return 6;
        }


    },
    FARM(1, 0, 0) {
        @Override
        public void initializeVectors() {
            this.canBuiltOnTerrains = new Vector<>(List.of(Terrain.GRASSLAND, Terrain.PLAINS, Terrain.DESERT));
            this.canBuiltOnTerrainFeatures = new Vector<>();
            this.affectingResources = new Vector<>(List.of(ResourceType.WHEAT));
            this.preRequisiteTech = TechnologyType.AGRICULTURE;
        }
        @Override
        public boolean isEligibleToBuild(Civilization civilization, Tile tile) {
            if (!(this.canBuiltOnTerrains.contains(tile.getTerrain())
                    || this.canBuiltOnTerrainFeatures.contains(tile.getFeature())))
                return false;
            if(!civilization.getResearchTree().isResearched(this.preRequisiteTech))
                return false;
            if(tile.getFeature() != null){
                if(tile.getFeature().equals(TerrainFeature.FOREST))
                    return civilization.getResearchTree().isResearched(TechnologyType.MINING);
                if(tile.getFeature().equals(TerrainFeature.JUNGLE))
                    return civilization.getResearchTree().isResearched(TechnologyType.BRONZE_WORKING);
                if(tile.getFeature().equals(TerrainFeature.MARSH))
                    return civilization.getResearchTree().isResearched(TechnologyType.MASONRY);
            }
            return true;
        }

        @Override
        public void improvementSpecialAction(Tile tile) {
            if(tile.getFeature() == null) return;
            if (tile.getFeature().equals(TerrainFeature.JUNGLE)
                    || tile.getFeature().equals(TerrainFeature.FOREST)
                    || tile.getFeature().equals(TerrainFeature.MARSH))
                tile.removeFeature();
        }

        @Override
        public int getProductionTime(Tile tile) {
            if (tile.getFeature().equals(TerrainFeature.FOREST))
                return 10;
            if (tile.getFeature().equals(TerrainFeature.JUNGLE))
                return 13;
            if (tile.getFeature().equals(TerrainFeature.MARSH))
                return 12;
            return 6;
        }
    },
    LUMBER_MILL(0, 0, 1) {
        @Override
        public void initializeVectors() {
            this.canBuiltOnTerrains = new Vector<>();
            this.canBuiltOnTerrainFeatures = new Vector<>(List.of(TerrainFeature.FOREST));
            this.affectingResources = new Vector<>();
            this.preRequisiteTech = TechnologyType.ENGINEERING;
        }

        @Override
        public int getProductionTime(Tile tile) {
            return 6;
        }
    },
    MINE(0, 0, 1) {
        @Override
        public void initializeVectors() {
            this.canBuiltOnTerrains = new Vector<>(List.of(Terrain.GRASSLAND, Terrain.PLAINS, Terrain.DESERT, Terrain.TUNDRA, Terrain.SNOW, Terrain.HILLS));
            this.canBuiltOnTerrainFeatures = new Vector<>(List.of(TerrainFeature.JUNGLE));
            this.affectingResources = new Vector<>(List.of(ResourceType.IRON, ResourceType.COAL, ResourceType.GEMS, ResourceType.GOLD, ResourceType.SILVER));
            this.preRequisiteTech = TechnologyType.MINING;
        }

        @Override
        public void improvementSpecialAction(Tile tile) {
            if(tile.getFeature() == null) return;
            if (tile.getFeature().equals(TerrainFeature.JUNGLE)
                    || tile.getFeature().equals(TerrainFeature.FOREST)
                    || tile.getFeature().equals(TerrainFeature.MARSH))
                tile.removeFeature();
        }
        @Override
        public boolean isEligibleToBuild(Civilization civilization, Tile tile) {
            if (!(this.canBuiltOnTerrains.contains(tile.getTerrain())
                    || this.canBuiltOnTerrainFeatures.contains(tile.getFeature())))
                return false;
            if(!civilization.getResearchTree().isResearched(this.preRequisiteTech))
                return false;
            if(tile.getFeature() != null){
                if(tile.getFeature().equals(TerrainFeature.JUNGLE))
                    return civilization.getResearchTree().isResearched(TechnologyType.BRONZE_WORKING);
                if(tile.getFeature().equals(TerrainFeature.MARSH))
                    return civilization.getResearchTree().isResearched(TechnologyType.MASONRY);
            }
            return true;
        }

        @Override
        public int getProductionTime(Tile tile) {
            if(tile.getFeature() == null)
                return 6;
            if (tile.getFeature().equals(TerrainFeature.FOREST))
                return 10;
            if (tile.getFeature().equals(TerrainFeature.JUNGLE))
                return 13;
            if (tile.getFeature().equals(TerrainFeature.MARSH))
                return 12;
            return 6;
        }
    },
    PASTURE(0, 0, 0) {
        @Override
        public void initializeVectors() {
            this.canBuiltOnTerrains = new Vector<>(List.of(Terrain.GRASSLAND, Terrain.PLAINS, Terrain.DESERT, Terrain.TUNDRA, Terrain.HILLS));
            this.canBuiltOnTerrainFeatures = new Vector<>();
            this.affectingResources = new Vector<>(List.of(ResourceType.SHEEP, ResourceType.CATTLE, ResourceType.HORSES));
            this.preRequisiteTech = TechnologyType.ANIMAL_HUSBANDRY;
        }

        @Override
        public int getProductionTime(Tile tile) {
            return 7;
        }
    },
    PLANTATION(0, 0, 0) {
        @Override
        public void initializeVectors() {
            this.canBuiltOnTerrains = new Vector<>(List.of(Terrain.GRASSLAND, Terrain.PLAINS, Terrain.DESERT));
            this.canBuiltOnTerrainFeatures = new Vector<>(List.of(TerrainFeature.FOREST, TerrainFeature.MARSH, TerrainFeature.FLOOD_PLAINS, TerrainFeature.JUNGLE));
            this.affectingResources = new Vector<>(List.of(ResourceType.BANANAS, ResourceType.DYES, ResourceType.SILK, ResourceType.SUGAR, ResourceType.COTTON, ResourceType.INCENSE));
            this.preRequisiteTech = TechnologyType.CALENDAR;
        }

        @Override
        public int getProductionTime(Tile tile) {
            return 5;
        }
    },
    QUARRY(0, 0, 0) {
        @Override
        public void initializeVectors() {
            this.canBuiltOnTerrains = new Vector<>(List.of(Terrain.GRASSLAND, Terrain.PLAINS, Terrain.DESERT, Terrain.TUNDRA, Terrain.HILLS));
            this.canBuiltOnTerrainFeatures = new Vector<>();
            this.affectingResources = new Vector<>(List.of(ResourceType.MARBLE));
            this.preRequisiteTech = TechnologyType.MASONRY;
        }

        @Override
        public int getProductionTime(Tile tile) {
            return 7;
        }
    },
    TRADING_POST(0, 1, 0) {
        @Override
        public void initializeVectors() {
            this.canBuiltOnTerrains = new Vector<>(List.of(Terrain.GRASSLAND, Terrain.PLAINS, Terrain.DESERT, Terrain.TUNDRA));
            this.canBuiltOnTerrainFeatures = new Vector<>();
            this.affectingResources = new Vector<>();
            this.preRequisiteTech = TechnologyType.TRAPPING;
        }

        @Override
        public int getProductionTime(Tile tile) {
            return 8;
        }
    },
    MANUFACTORY(0, 0, 2) {
        @Override
        public void initializeVectors() {
            this.canBuiltOnTerrains = new Vector<>(List.of(Terrain.GRASSLAND, Terrain.PLAINS, Terrain.DESERT, Terrain.TUNDRA, Terrain.SNOW));
            this.canBuiltOnTerrainFeatures = new Vector<>();
            this.affectingResources = new Vector<>();
            this.preRequisiteTech = TechnologyType.ENGINEERING;
        }
    };

    public final int foodYield;
    public final int goldYield;
    public final int productionYield;
    public TechnologyType preRequisiteTech;
    public Vector<ResourceType> affectingResources;
    public Vector<Terrain> canBuiltOnTerrains;
    public Vector<TerrainFeature> canBuiltOnTerrainFeatures;

    ImprovementType(int foodYield, int goldYield, int productionYield) {
        this.foodYield = foodYield;
        this.goldYield = goldYield;
        this.productionYield = productionYield;
    }

    public int getProductionTime(Tile tile) {
        return 3;
    }

    public boolean isEligibleToBuild(Civilization civilization, Tile tile) {
        if (!(this.canBuiltOnTerrains.contains(tile.getTerrain())
                || this.canBuiltOnTerrainFeatures.contains(tile.getFeature())))
            return false;
        if(tile.getCivilization() != civilization)
            return false;
        return civilization.getResearchTree().isResearched(this.preRequisiteTech);
    }

    public void improvementSpecialAction(Tile tile) {
    }


    public void initializeVectors() {
    }

    @Override
    public String toString() {
        return StringUtils.convertToPascalCase(this.name()).replaceAll("_", " ");
    }
}