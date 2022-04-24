import controller.MapGenerationController;
import model.Game;
import model.resource.ResourceType;
import model.tile.Terrain;
import model.tile.TerrainFeature;

import java.util.Vector;

public class Main {
    public static void main(String[] args){
        callAllEnums();
        Game game = new Game(new Vector<>(), 21);
        MapGenerationController mapController = new MapGenerationController(game);
        mapController.generateMap(21);
    }

    public static void callAllEnums(){
        Vector<ResourceType> e;
        e = TerrainFeature.FOREST.possibleResources;

    }
}
