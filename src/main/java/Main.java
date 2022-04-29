import controller.MapController;
import controller.MapGenerationController;
import controller.ProgramController;
import controller.TileController;
import model.Game;
import model.Player;
import model.improvement.Improvement;
import model.improvement.ImprovementType;
import model.resource.ResourceType;
import model.tile.Terrain;
import model.tile.TerrainFeature;
import model.tile.Tile;
import model.unit.Armed;
import model.unit.UnitType;

import java.util.Scanner;
import java.util.Vector;

public class Main {
    public static void main(String[] args)   {
        //ProgramController pc = new ProgramController();
        //pc.run();
        //to check map uncomment here

        TileController.initializeEnums();
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
        game.getMap().getTile(11,11).buildImprovement(new Improvement(ImprovementType.CAMP));
        Vector<Tile> revealTiles = new Vector<>();
        for(int p = 7; p < 10; p++){
            for(int q = 7; q < 10; q++){
                revealTiles.add(game.getMap().getTile(p, q));
            }
        }
        mcontrol.updateSavedMap(game.getCurrentPlayer(),revealTiles, game.getMap());
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
