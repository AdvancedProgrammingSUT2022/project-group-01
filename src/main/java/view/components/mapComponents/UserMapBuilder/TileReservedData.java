package view.components.mapComponents.UserMapBuilder;

import model.resource.ResourceType;
import model.tile.Terrain;
import model.tile.TerrainFeature;

public class TileReservedData {
    private Terrain terrain;
    private TerrainFeature feature;
    private ResourceType resourceType;

    public Terrain getTerrain() {
        return terrain;
    }

    public void setTerrain(Terrain terrain) {
        this.terrain = terrain;
    }

    public TerrainFeature getFeature() {
        return feature;
    }

    public void setFeature(TerrainFeature feature) {
        this.feature = feature;
    }

    public ResourceType getResourceType() {
        return resourceType;
    }

    public void setResourceType(ResourceType resourceType) {
        this.resourceType = resourceType;
    }
}
