package model.map;

import model.Map;
import model.civilization.Civilization;
import model.civilization.city.City;
import model.resource.Resource;
import model.resource.ResourceType;
import model.tile.Terrain;
import model.tile.TerrainFeature;
import model.tile.Tile;

import java.util.HashMap;
import java.util.Vector;

public class SavedMap {
    HashMap<Tile, SavedMapData> savedMap;

    public SavedMap(Map map, int mapSize) {
        savedMap = new HashMap<>();
        for(Tile tile : map.getTiles()){
            savedMap.put(tile, new SavedMapData(Tile.VisibilityState.FOG_OF_WAR, tile.getTerrain(), tile.getFeature(), tile.getAvailableResource()));
        }
    }
    public void updateData(Tile tile, Tile.VisibilityState state, Terrain terrain, TerrainFeature terrainFeature, Resource resource){
        savedMap.get(tile).updateData(state, terrain, terrainFeature, resource);
    }

    public Tile.VisibilityState getVisibilityState(Tile tile){
        return savedMap.get(tile).getVisibilityState();
    }
    public Terrain getTerrain(Tile tile){
        return savedMap.get(tile).getTerrainType();
    }
    public TerrainFeature getFeature(Tile tile){
        return savedMap.get(tile).getTerrainFeature();
    }
    public void setVisibilityState(Tile tile, Tile.VisibilityState state){
        savedMap.get(tile).setVisibilityState(state);
    }
    public ResourceType getResourceTypes(Tile tile){
        if(savedMap.get(tile).getResource() == null)
            return null;
        return savedMap.get(tile).getResource().getType();
    }

    private static class SavedMapData{
        private Tile.VisibilityState visibilityState;
        private Terrain terrainType;
        private TerrainFeature terrainFeature;
        private City city;
        private Civilization owner;
        private Resource resource;

        public SavedMapData(Tile.VisibilityState visibilityState, Terrain terrainType, TerrainFeature terrainFeature, Resource resource) {
            updateData(visibilityState,terrainType,terrainFeature, resource);
        }

        public Tile.VisibilityState getVisibilityState() {
            return visibilityState;
        }


        public Terrain getTerrainType() {
            return terrainType;
        }

        public Resource getResource(){return this.resource;}
        public TerrainFeature getTerrainFeature() {
            return terrainFeature;
        }

        public void setVisibilityState(Tile.VisibilityState visibilityState) {
            this.visibilityState = visibilityState;
        }

        public void updateData(Tile.VisibilityState visibilityState, Terrain terrainType, TerrainFeature terrainFeature, Resource resource) {
            this.visibilityState = visibilityState;
            this.terrainType = terrainType;
            this.terrainFeature = terrainFeature;
            this.resource = resource;
        }
    }
}
