package view.components.mapComponents;

import javafx.scene.layout.Pane;
import model.resource.ResourceType;
import model.tile.Terrain;
import model.tile.TerrainFeature;
import model.tile.Tile;

public class MapViewController {

    public Pane backPane;

    public void initialize(){
        Tile t = new Tile(Terrain.GRASSLAND, TerrainFeature.FOREST,null, ResourceType.SUGAR,0,0,2);
        MapTileComponent tileComponent = new MapTileComponent(t);

        Pane pane1 = new MapTileComponent(t).initialize();
        backPane.getChildren().add(pane1);
        pane1.setTranslateX(640);
        pane1.setTranslateY(340);

//        for(int i = -2; i <= 2; i++){
//            for(int j = -2; j<=2; j++){
//                Pane pane2 = new MapTileComponent(t).initialize();
//                backPane.getChildren().add(pane2);
//                pane2.setTranslateX(640 + 100 * i);
//                pane2.setTranslateY(340 + 100 * j);
//            }
//        }

    }
}
