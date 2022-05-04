import controller.*;
import model.Game;
import model.Player;
import model.User;
import model.civilization.Civilization;
import model.civilization.city.City;
import model.improvement.Improvement;
import model.improvement.ImprovementType;
import model.tile.Tile;
import model.unit.UnitType;
import java.util.List;

import java.util.Scanner;
import java.util.Vector;

public class Main {
    public static void main(String[] args)   {
        //ProgramController pc = new ProgramController();
        //pc.run();
        //to check map uncomment here

        TileController.initializeEnums();
        User user1 = new User("ali","a123","al");
        User user2 = new User("mamad","m123","mam");
        Vector<User> okUser = new Vector<>(List.of(user1,user2));
        Game game = new GameInitializer().startGame(okUser, 31);
        MapController mcontrol = new MapController(game);
        game.getMap().getTileByNumber(301).setInnerCity(new City("hello", game.getPlayers().get(0).getCivilization(),game.getMap()
                .getTileByNumber(301)));
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        //game.getMap().getTile(game.getMap().getMapSize(),11).buildImprovement(new Improvement(ImprovementType.CAMP));
        Vector<Tile> revealTiles = new Vector<>();
        for(int p = 15; p < 20; p++){
            for(int q = 10; q < 15; q++){
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
