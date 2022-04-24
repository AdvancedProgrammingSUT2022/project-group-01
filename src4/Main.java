import controller.MapController;
import controller.MapGenerationController;
import model.Game;
import model.Player;
import model.resource.ResourceType;
import model.tile.Terrain;
import model.tile.TerrainFeature;
import model.tile.Tile;

import java.util.Scanner;
import java.util.Vector;

public class Main {
    public static void main(String[] args){
        Game game = new Game(new Vector<>(), 21);
        MapGenerationController mapgenController = new MapGenerationController(game);
        mapgenController.generateMap(21);
        Player mamad = new Player();
        mamad.initializeSavedMap(game);
        MapController mcontrol = new MapController(game);
        game.setCurrentPlayer(mamad);
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        game.getCurrentPlayer().setMapCenterTile(game.getMap().getTile(10, 10));
        while(!input.contains("end")) {
            System.out.println(mcontrol.moveMap(input, 1));
            Vector<Vector<String>> mop = mcontrol.getConsoleMap(game.getCurrentPlayer().getMapCenterTile());
            for (Vector<String> vec : mop) {
                for (String s : vec) {
                    System.out.print(s);
                }
                System.out.println();
            }
            input = scanner.nextLine();
        }
    }

}
