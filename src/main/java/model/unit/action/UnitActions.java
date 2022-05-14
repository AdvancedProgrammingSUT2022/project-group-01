package model.unit.action;
import model.improvement.ImprovementType;
import model.tile.Terrain;
import model.tile.TerrainFeature;
import model.tile.Tile;

public enum UnitActions {
    //worker actions
    BUILD_ROAD(3,null){
        @Override
        public void doAction(Tile tile){
            tile.buildRoad();
        }
        @Override
        public boolean checkIfActionIsDoable(Tile tile){
            if(tile.getTerrain().equals(Terrain.MOUNTAIN) || tile.getTerrain().equals(Terrain.OCEAN))
                return false;
            return (tile.getFeature() == null) || (!tile.getFeature().equals(TerrainFeature.ICE));
        }
    },
    BUILD_RAILROAD(3,null){
        @Override
        public void doAction(Tile tile){
            tile.buildRailRoad();
        }
        @Override
        public boolean checkIfActionIsDoable(Tile tile){
            if(tile.getTerrain().equals(Terrain.MOUNTAIN) || tile.getTerrain().equals(Terrain.OCEAN))
                return false;
            return (tile.getFeature() == null) || (!tile.getFeature().equals(TerrainFeature.ICE));
        }
    },
    REMOVE_ROADS(3,null){
        @Override
        public void doAction(Tile tile){
            tile.removeRoads();
        }
        @Override
        public boolean checkIfActionIsDoable(Tile tile){
            return tile.doesHaveRoad() || tile.doesHaveRailRoad();
        }
    },
    REMOVE_JUNGLE(6,null){
        @Override
        public void doAction(Tile tile){
            tile.removeFeature();
        }
        @Override
        public boolean checkIfActionIsDoable(Tile tile){
            if(tile.getFeature() == null) return false;
            return tile.getFeature().equals(TerrainFeature.JUNGLE);
        }
    },
    REMOVE_FOREST(3,null){
        @Override
        public void doAction(Tile tile){
            tile.removeFeature();
        }
        @Override
        public boolean checkIfActionIsDoable(Tile tile){
            if(tile.getFeature() == null) return false;
            return tile.getFeature().equals(TerrainFeature.FOREST);
        }
    },
    REMOVE_MARSH(5,null){
        @Override
        public void doAction(Tile tile){
            tile.removeFeature();
        }
        @Override
        public boolean checkIfActionIsDoable(Tile tile){
            if(tile.getFeature() == null) return false;
            return tile.getFeature().equals(TerrainFeature.MARSH);
        }
    },
    REPAIR_ROAD(3,null){
        @Override
        public void doAction(Tile tile){tile.buildRoad();}
        @Override
        public boolean checkIfActionIsDoable(Tile tile){
            return tile.getMiscellaneousTileActionsInventory().isRoadPillaged();
        }
    },
    REPAIR_RAILROAD(3,null){
        @Override
        public void doAction(Tile tile){tile.buildRailRoad();}

        @Override
        public boolean checkIfActionIsDoable(Tile tile){
            return tile.getMiscellaneousTileActionsInventory().isRailRoadPillaged();
        }
    },
    PILLAGE_ROADS(3,null){
        @Override
        public void doAction(Tile tile){tile.getMiscellaneousTileActionsInventory().pillage();}
        @Override
        public boolean checkIfActionIsDoable(Tile tile){
            return tile.doesHaveRoad() || tile.doesHaveRailRoad();
        }
    },
    BUILD_CAMP(-1, ImprovementType.CAMP),
    BUILD_FARM(-1,ImprovementType.FARM),
    BUILD_MINE(-1,ImprovementType.MINE),
    BUILD_TRADING_POST(-1,ImprovementType.TRADING_POST),
    BUILD_LUMBER_MILL(-1,ImprovementType.LUMBER_MILL),
    BUILD_PASTURE(-1,ImprovementType.PASTURE),
    BUILD_PLANTATION(-1,ImprovementType.PLANTATION),
    BUILD_QUARRY(-1,ImprovementType.QUARRY),
    MOVE_TO(1,null),
    SETTLE(1,null);



    public final int time;
    public final Object mainKind;
    UnitActions(int time, Object mainKind){
        this.time = time;
        this.mainKind =mainKind;
    }

    public void doAction(Tile tile){
    }

    public boolean checkIfActionIsDoable(Tile tile){return true;}
}
