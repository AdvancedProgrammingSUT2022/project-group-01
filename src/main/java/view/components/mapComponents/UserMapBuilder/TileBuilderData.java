package view.components.mapComponents.UserMapBuilder;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Polygon;
import model.resource.ResourceType;
import model.tile.Terrain;
import model.tile.TerrainFeature;
import model.tile.Tile;
import view.components.ImagesAddress;
import view.components.mapComponents.MapTileComponent;


public class TileBuilderData {
    Pane pane = new Pane();
    private final double radius = 200;
    ImageView resource;
    Polygon backgroundShape;
    private TileReservedData tileReservedData = new TileReservedData();
    private boolean[] rivers = new boolean[6];
    private boolean isDone = false;

    public Pane initialize(){
        pane = new Pane();
        backgroundShape = new MapTileComponent.Hexagon(radius, getBackgroundImage()).getPolygon();
        pane.getChildren().add(backgroundShape);
        return pane;
    }

    public void UpdatePane(){
        backgroundShape = new MapTileComponent.Hexagon(radius, getBackImage()).getPolygon();
        pane.getChildren().add(backgroundShape);
        addResourceImage();
        addRivers();
    }

    public Image getBackImage(){
        if(tileReservedData.getFeature() == null){
            switch (tileReservedData.getTerrain()){
                case DESERT:
                    return ImagesAddress.DESERT.getImage();
                case GRASSLAND:
                    return ImagesAddress.GRASSLAND.getImage();
                case HILLS:
                    return ImagesAddress.HILLS.getImage();
                case MOUNTAIN:
                    return ImagesAddress.MOUNTAIN.getImage();
                case OCEAN:
                    return ImagesAddress.OCEAN.getImage();
                case PLAINS:
                    return ImagesAddress.PLAINS.getImage();
                case TUNDRA:
                    return ImagesAddress.TUNDRA.getImage();
                case SNOW:
                    return ImagesAddress.SNOW.getImage();
            }
        }
        else {
            switch (tileReservedData.getTerrain()) {
                case DESERT:
                    switch (tileReservedData.getFeature()) {
                        case FLOOD_PLAINS:
                            return ImagesAddress.DESERT_FLOODPLAINS.getImage();
                        case OASIS:
                            return ImagesAddress.DESERT_OASIS.getImage();
                        default:
                            return ImagesAddress.DESERT.getImage();
                    }
                case GRASSLAND:
                    switch (tileReservedData.getFeature()) {
                        case FOREST:
                            return ImagesAddress.GRASSLAND_FOREST.getImage();
                        case MARSH:
                            return ImagesAddress.GRASSLAND_MARSH.getImage();
                        default:
                            return ImagesAddress.GRASSLAND.getImage();
                    }
                case HILLS:
                    switch (tileReservedData.getFeature()) {
                        case JUNGLE:
                            return ImagesAddress.HILLS_JUNGLE.getImage();
                        case FOREST:
                            return ImagesAddress.HILLS_FOREST.getImage();
                        default:
                            return ImagesAddress.HILLS.getImage();
                    }
                case MOUNTAIN:
                    return ImagesAddress.MOUNTAIN.getImage();
                case OCEAN:
                    if (tileReservedData.getFeature().equals(TerrainFeature.ICE)) return ImagesAddress.OCEAN_ICE.getImage();
                    return ImagesAddress.OCEAN.getImage();
                case PLAINS:
                    switch (tileReservedData.getFeature()) {
                        case JUNGLE:
                            return ImagesAddress.PLAINS_JUNGLE.getImage();
                        case FOREST:
                            return ImagesAddress.PLAINS_FOREST.getImage();
                        default:
                            return ImagesAddress.PLAINS.getImage();
                    }
                case SNOW:
                    return ImagesAddress.SNOW.getImage();
                case TUNDRA:
                    if (tileReservedData.getFeature().equals(TerrainFeature.FOREST)) return ImagesAddress.TUNDRA_FOREST.getImage();
                    return ImagesAddress.TUNDRA.getImage();
                default:
                    return null;
            }
        }
        return null;
    }

    private void addResourceImage(){
        if (tileReservedData.getResourceType() == null) return;
        switch (tileReservedData.getResourceType()) {
            case BANANAS:
                resource = new ImageView(ImagesAddress.BANANAS.getImage());
                break;
            case CATTLE:
                resource = new ImageView(ImagesAddress.CATTLE.getImage());
                break;
            case DEER:
                resource = new ImageView(ImagesAddress.DEER.getImage());
                break;
            case SHEEP:
                resource = new ImageView(ImagesAddress.SHEEP.getImage());
                break;
            case WHEAT:
                resource = new ImageView(ImagesAddress.WHEAT.getImage());
                break;
            case COAL:
                resource = new ImageView(ImagesAddress.COAL.getImage());
                break;
            case HORSES:
                resource = new ImageView(ImagesAddress.HORSES.getImage());
                break;
            case IRON:
                resource = new ImageView(ImagesAddress.IRON.getImage());
                break;
            case COTTON:
                resource = new ImageView(ImagesAddress.COTTON.getImage());
                break;
            case DYES:
                resource = new ImageView(ImagesAddress.DYES.getImage());
                break;
            case FURS:
                resource = new ImageView(ImagesAddress.FURS.getImage());
                break;
            case GEMS:
                resource = new ImageView(ImagesAddress.GEMS.getImage());
                break;
            case GOLD:
                resource = new ImageView(ImagesAddress.GOLD.getImage());
                break;
            case INCENSE:
                resource = new ImageView(ImagesAddress.INCENSE.getImage());
                break;
            case IVORY:
                resource = new ImageView(ImagesAddress.IVORY.getImage());
                break;
            case MARBLE:
                resource = new ImageView(ImagesAddress.MARBLE.getImage());
                break;
            case SILK:
                resource = new ImageView(ImagesAddress.SILK.getImage());
                break;
            case SILVER:
                resource = new ImageView(ImagesAddress.SILVER.getImage());
                break;
            case SUGAR:
                resource = new ImageView(ImagesAddress.SUGAR.getImage());
        }
        pane.getChildren().add(resource);
        resource.setTranslateX(radius);
        resource.setTranslateY(radius * 1.5);
        resource.maxHeight(32);
        resource.maxWidth(32);
    }

    private void addRivers(){
        if(tileReservedData.getRivers()[0]){
            ImageView river = new ImageView(ImagesAddress.RIVER.getImage());
            pane.getChildren().add(river);
            river.setTranslateX(50);
            river.setTranslateY(0);
        }
        if(tileReservedData.getRivers()[3]){
            ImageView river = new ImageView(ImagesAddress.RIVER.getImage());
            pane.getChildren().add(river);
            river.setTranslateX(70);
            river.setRotate(180);
            river.setTranslateY(2*radius - 50);
            river.maxHeight(10);
            river.maxWidth(32);
        }
        if(tileReservedData.getRivers()[1]){
            ImageView river = new ImageView(ImagesAddress.RIVER.getImage());
            pane.getChildren().add(river);
            river.setRotate(60);
            river.setTranslateX(radius + 25);
            river.setTranslateY(radius/2 - 10);
            river.maxHeight(32);
            river.maxWidth(10);
        }
        if(tileReservedData.getRivers()[2]){
            ImageView river = new ImageView(ImagesAddress.RIVER.getImage());
            pane.getChildren().add(river);
            river.setRotate(120);
            river.setTranslateX(radius + 20);
            river.setTranslateY(3*radius/2 - 25);
            river.maxHeight(32);
            river.maxWidth(10);
        }
        if(tileReservedData.getRivers()[4]){
            ImageView river = new ImageView(ImagesAddress.RIVER.getImage());
            pane.getChildren().add(river);
            river.setRotate(240);
            river.setTranslateX(-70);
            river.setTranslateY(3 *radius/2 - 25);
            river.maxHeight(10);
            river.maxWidth(32);
        }
        if(tileReservedData.getRivers()[5]){
            ImageView river = new ImageView(ImagesAddress.RIVER.getImage());
            pane.getChildren().add(river);
            river.setRotate(300);
            river.setTranslateX(-80);
            river.setTranslateY(radius/2 - 10);
            river.maxHeight(32);
            river.maxWidth(10);
        }
    }
    private Image getBackgroundImage() {
        return ImagesAddress.FOG_OF_WAR.getImage();
    }

    public TileReservedData getTileReservedData(){
        return tileReservedData;
    }

    public boolean[] getRivers() {
        return rivers;
    }

    public void setRivers(boolean[] rivers) {
        this.rivers = rivers;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }
}
