package model.resource;

import model.civilization.Civilization;
import model.civilization.Currency;
import model.improvement.ImprovementType;
import model.technology.TechnologyType;
import model.tile.Terrain;
import model.tile.TerrainFeature;
import model.tile.Tile;

import java.util.List;
import java.util.Vector;

public enum ResourceType {
    //Bonus Resources
    BANANAS(1, 0, 0, false, KindsOfResource.BONUS) {
        @Override
        public void initializeVectors() {
            this.possibleTerrains = new Vector<>();
            this.possibleLandFeatures = new Vector<>(List.of(TerrainFeature.JUNGLE));
            this.visibilityTechnology = null;
            this.necessaryImprovement = ImprovementType.PLANTATION;
        }
    },
    CATTLE(1, 0, 0, false, KindsOfResource.BONUS) {
        @Override
        public void initializeVectors() {
            this.possibleTerrains = new Vector<>();
            this.possibleLandFeatures = new Vector<>(List.of(TerrainFeature.JUNGLE));
            this.visibilityTechnology = null;
            this.necessaryImprovement = ImprovementType.PASTURE;
        }
    },
    DEER(1, 0, 0, false, KindsOfResource.BONUS) {
        @Override
        public void initializeVectors() {
            this.possibleTerrains = new Vector<>(List.of(Terrain.TUNDRA, Terrain.HILLS));
            this.possibleLandFeatures = new Vector<>(List.of(TerrainFeature.FOREST));
            this.visibilityTechnology = null;
            this.necessaryImprovement = ImprovementType.CAMP;
        }
    },
    SHEEP(1, 0, 0, false, KindsOfResource.BONUS) {
        @Override
        public void initializeVectors() {
            this.possibleTerrains = new Vector<>(List.of(Terrain.GRASSLAND, Terrain.PLAINS, Terrain.DESERT, Terrain.HILLS));
            this.possibleLandFeatures = new Vector<>();
            this.visibilityTechnology = null;
            this.necessaryImprovement = ImprovementType.PASTURE;
        }
    },
    WHEAT(1, 0, 0, false, KindsOfResource.BONUS) {
        @Override
        public void initializeVectors() {
            this.possibleTerrains = new Vector<>(List.of(Terrain.PLAINS));
            this.possibleLandFeatures = new Vector<>(List.of(TerrainFeature.FLOOD_PLAINS));
            this.visibilityTechnology = null;
            this.necessaryImprovement = ImprovementType.FARM;
        }
    },
    //Strategic Resources
    COAL(0, 1, 0, true, KindsOfResource.STRATEGIC) {
        @Override
        public void initializeVectors() {
            this.possibleTerrains = new Vector<>(List.of(Terrain.GRASSLAND, Terrain.PLAINS, Terrain.HILLS));
            this.possibleLandFeatures = new Vector<>();
            this.visibilityTechnology = TechnologyType.SCIENTIFIC_THEORY;
            this.necessaryImprovement = ImprovementType.MINE;
        }
    },
    HORSES(0, 1, 0, true, KindsOfResource.STRATEGIC) {
        @Override
        public void initializeVectors() {
            this.possibleTerrains = new Vector<>(List.of(Terrain.GRASSLAND, Terrain.PLAINS, Terrain.TUNDRA));
            this.possibleLandFeatures = new Vector<>();
            this.visibilityTechnology = TechnologyType.ANIMAL_HUSBANDRY;
            this.necessaryImprovement = ImprovementType.PASTURE;
        }
    },
    IRON(0, 1, 0, true, KindsOfResource.STRATEGIC) {
        @Override
        public void initializeVectors() {
            this.possibleTerrains = new Vector<>(List.of(Terrain.GRASSLAND, Terrain.PLAINS, Terrain.DESERT, Terrain.TUNDRA, Terrain.SNOW, Terrain.HILLS));
            this.possibleLandFeatures = new Vector<>();
            this.visibilityTechnology = TechnologyType.IRON_WORKING;
            this.necessaryImprovement = ImprovementType.MINE;
        }
    },
    // Luxury Resources
    COTTON(0, 0, 2, false, KindsOfResource.LUXURY) {
        @Override
        public void initializeVectors() {
            this.possibleTerrains = new Vector<>(List.of(Terrain.GRASSLAND, Terrain.PLAINS, Terrain.DESERT));
            this.possibleLandFeatures = new Vector<>();
            this.visibilityTechnology = null;
            this.necessaryImprovement = ImprovementType.PLANTATION;
        }
    },
    DYES(0, 0, 2, false, KindsOfResource.LUXURY) {
        @Override
        public void initializeVectors() {
            this.possibleTerrains = new Vector<>();
            this.possibleLandFeatures = new Vector<>(List.of(TerrainFeature.JUNGLE, TerrainFeature.FOREST));
            this.visibilityTechnology = null;
            this.necessaryImprovement = ImprovementType.PLANTATION;
        }
    },
    FURS(0, 0, 2, false, KindsOfResource.LUXURY) {
        @Override
        public void initializeVectors() {
            this.possibleTerrains = new Vector<>(List.of(Terrain.TUNDRA));
            this.possibleLandFeatures = new Vector<>(List.of(TerrainFeature.FOREST));
            this.visibilityTechnology = null;
            this.necessaryImprovement = ImprovementType.CAMP;
        }
    },
    GEMS(0, 0, 3, false, KindsOfResource.LUXURY) {
        @Override
        public void initializeVectors() {
            this.possibleTerrains = new Vector<>(List.of(Terrain.GRASSLAND, Terrain.PLAINS, Terrain.DESERT, Terrain.TUNDRA, Terrain.HILLS));
            this.possibleLandFeatures = new Vector<>(List.of(TerrainFeature.JUNGLE));
            this.visibilityTechnology = null;
            this.necessaryImprovement = ImprovementType.MINE;
        }
    },
    GOLD(0, 0, 2, false, KindsOfResource.LUXURY) {
        @Override
        public void initializeVectors() {
            this.possibleTerrains = new Vector<>(List.of(Terrain.GRASSLAND, Terrain.PLAINS, Terrain.DESERT, Terrain.HILLS));
            this.possibleLandFeatures = new Vector<>();
            this.visibilityTechnology = null;
            this.necessaryImprovement = ImprovementType.MINE;
        }
    },
    INCENSE(0, 0, 2, false, KindsOfResource.LUXURY) {
        @Override
        public void initializeVectors() {
            this.possibleTerrains = new Vector<>(List.of(Terrain.PLAINS, Terrain.DESERT));
            this.possibleLandFeatures = new Vector<>();
            this.visibilityTechnology = null;
            this.necessaryImprovement = ImprovementType.PLANTATION;
        }
    },
    IVORY(0, 0, 2, false, KindsOfResource.LUXURY) {
        @Override
        public void initializeVectors() {
            this.possibleTerrains = new Vector<>(List.of(Terrain.PLAINS));
            this.possibleLandFeatures = new Vector<>();
            this.visibilityTechnology = null;
            this.necessaryImprovement = ImprovementType.CAMP;
        }
    },
    MARBLE(0, 0, 2, false, KindsOfResource.LUXURY) {
        @Override
        public void initializeVectors() {
            this.possibleTerrains = new Vector<>(List.of(Terrain.GRASSLAND, Terrain.PLAINS, Terrain.DESERT, Terrain.TUNDRA, Terrain.HILLS));
            this.possibleLandFeatures = new Vector<>();
            this.visibilityTechnology = null;
            this.necessaryImprovement = ImprovementType.QUARRY;
        }
    },
    SILK(0, 0, 2, false, KindsOfResource.LUXURY) {
        @Override
        public void initializeVectors() {
            this.possibleTerrains = new Vector<>();
            this.possibleLandFeatures = new Vector<>(List.of(TerrainFeature.FOREST));
            this.visibilityTechnology = null;
            this.necessaryImprovement = ImprovementType.PLANTATION;
        }
    },
    SILVER(0, 0, 2, false, KindsOfResource.LUXURY) {
        @Override
        public void initializeVectors() {
            this.possibleTerrains = new Vector<>(List.of(Terrain.DESERT, Terrain.TUNDRA, Terrain.HILLS));
            this.possibleLandFeatures = new Vector<>();
            this.visibilityTechnology = null;
            this.necessaryImprovement = ImprovementType.MINE;
        }
    },
    SUGAR(0, 0, 2, false, KindsOfResource.LUXURY) {
        @Override
        public void initializeVectors() {
            this.possibleTerrains = new Vector<>();
            this.possibleLandFeatures = new Vector<>(List.of(TerrainFeature.FLOOD_PLAINS, TerrainFeature.MARSH));
            this.visibilityTechnology = null;
            this.necessaryImprovement = ImprovementType.PLANTATION;
        }
    };

    static {
        for (ResourceType value : ResourceType.values())
            value.initializeVectors();
    }


    public final int food;
    public final int production;
    public final int gold;
    public final boolean tradable;
    public final KindsOfResource resourceKind;
    public Vector<Terrain> possibleTerrains = new Vector<>();
    public Vector<TerrainFeature> possibleLandFeatures = new Vector<>();
    public ImprovementType necessaryImprovement;
    public TechnologyType visibilityTechnology;
    public final int outputNumberToCivilization = 4;

    ResourceType(int food, int production, int gold, boolean tradable, KindsOfResource resourceKind) {
        this.food = food;
        this.production = production;
        this.gold = gold;
        this.tradable = tradable;
        this.resourceKind = resourceKind;
    }
    public boolean isVisible(Civilization civilization){
        if(this.visibilityTechnology != null){
            return civilization.getResearchTree().isResearched(this.visibilityTechnology);
        }
        return true;
    }

    public void initializeVectors() {
    }

    public Currency getCurrency(Tile tile){
        Currency currency = new Currency(0,0,0);
        if(tile.getCivilization() == null) return currency;
        if(!isVisible(tile.getCivilization())) return currency;
        if(tile.getPeopleInside().isEmpty()) return currency;
        currency.increase(gold,production,food);
        return currency;
    }
}